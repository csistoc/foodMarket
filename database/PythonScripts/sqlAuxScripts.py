import glob
import os
import random

import jaydebeapi

from database.PythonScripts.auxScripts import get_string_list_by_words, escape_sql, generate_strong_random_string, \
    generate_fake_phone_number, generate_random_date, get_file_lines_as_list, strip_special_characters


def generate_user_table_insert_query(username_file, country_name_file, out_file, table_name, number_of_queries):
    """
    Generate a list of sql insert statements to populate the USERS table with random data.

    :param username_file: Path to the file from which we take the user's first and second name
    :param country_name_file: Path to the file from which we take the country
    :param out_file: Name of the file in which we write
    :param table_name: Name of the insert table
    :param number_of_queries: Number of generated queries
    """

    name_list = get_string_list_by_words(username_file)
    country_list = get_string_list_by_words(country_name_file)
    insert = ""

    for i in range(int(number_of_queries)):
        rnd_name = random.sample(name_list, 2)  # Pick 2 unique names
        username = escape_sql(rnd_name[0] + rnd_name[1])
        password = escape_sql(generate_strong_random_string(12))
        email = escape_sql(rnd_name[0] + "." + rnd_name[1]) + "@email.com"
        first_name = escape_sql(rnd_name[0])
        last_name = escape_sql(rnd_name[1])
        address = escape_sql(random.choice(country_list))
        phone_number = escape_sql(generate_fake_phone_number())
        date_of_birth = generate_random_date("01-01-1950", "01-01-2000").strftime('%Y-%m-%d')
        insert += (
            f"INSERT INTO {table_name} VALUES (DEFAULT, '{username}', '{password}', "
            f"'{email}', '{first_name}', '{last_name}', '{address}', '{str(phone_number)}', "
            f"'{date_of_birth}', FALSE, FALSE);\n"
        )

    with (open(out_file, "w") as fw):
        fw.write(insert)


def generate_seller_table_insert_queries(seller_file, country_file, output_file, table_name, number_of_queries):
    """
    Generate a list of sql insert statements to populate the SELLERS table with random data.

    :param seller_file: Path to the file from which we take the seller's name
    :param country_file: Path to the file from which we take the seller's country
    :param output_file: Name of the file in which we write the sql inserts
    :param table_name: Name of the table in which we plan to insert
    :param number_of_queries: Number of queries we plan to generate
    """

    name_list = get_file_lines_as_list(seller_file)
    country_list = get_string_list_by_words(country_file)
    inserts = ""

    for i in range(int(number_of_queries)):
        name = strip_special_characters(random.choice(name_list))
        address = escape_sql(random.choice(country_list))
        phone_number = escape_sql(generate_fake_phone_number())
        inserts += (
            f"INSERT INTO {table_name} VALUES (DEFAULT, '{name}', '{address}', '{str(phone_number)}');\n"
        )

    with (open(output_file, "w") as fw):
        fw.write(inserts)


def generate_table_insert_with_one_row(input_file, output_file, table_name):
    """
    Generate sql insert statements to populate with random data a table with only 1 row.

    :param input_file: Name of the file from which we take the data
    :param output_file: Name of the file in which we write the sql inserts
    :param table_name: Name of the table in which we insert
    """

    inserts = ""

    with open(input_file, "r") as fr:
        for line in fr:
            inserts += (
                    f"INSERT INTO " + table_name + " VALUES (DEFAULT, '" + line.strip('\n') + "');\n"
            )

    with open(output_file, "w") as fw:
        fw.write(inserts)


def get_table_row_count(db_username, db_password, table_name):
    """
    Get the number of rows of a table.

    :param db_username: Database username
    :param db_password: Database password
    :param table_name: Name of the table
    :return: Number of rows
    """

    # configuration
    h2_jar_path = r"C:\Program Files (x86)\H2\bin\h2-2.3.232.jar"
    jdbc_url = "jdbc:h2:~/testdb"  # File-based DB in home folder

    # JDBC driver class
    jdbc_driver = "org.h2.Driver"

    # Connect to the H2 database
    conn = jaydebeapi.connect(jdbc_driver,
                              jdbc_url,
                              [db_username, db_password],
                              h2_jar_path)

    # Create a cursor
    cursor = conn.cursor()

    row_count = []

    # Execute a COUNT query
    cursor.execute(f"SELECT COUNT(*) FROM {table_name}")
    row_count[0] = cursor.fetchone()[0]

    print(f"Total rows in '{table_name}': {row_count[0]}")

    # Clean up
    cursor.close()
    conn.close()

    return row_count


def refresh_database(username, password, sql_file_location):
    """
    Run a set of scripts to recreate and repopulate the database with new random data.

    :param username: Database username
    :param password: Database password
    :param sql_file_location: Location of the sql scripts
    """
    # configuration
    sql_folder_path = sql_file_location  # Folder with .sql files
    h2_jar_path = r"C:\Program Files (x86)\H2\bin\h2-2.3.232.jar"
    jdbc_url = "jdbc:h2:~/testdb"  # File-based DB in home folder
    jdbc_driver = "org.h2.Driver"

    # connect to H2
    conn = jaydebeapi.connect(
        jdbc_driver,
        jdbc_url,
        [username, password],
        h2_jar_path
    )
    cursor = conn.cursor()

    # get all .sql files (sorted by filename)
    sql_files = sorted(glob.glob(os.path.join(sql_folder_path, "*.sql")))

    # run each SQL file
    for sql_file in sql_files:
        print(f"\nRunning: {sql_file}")
        with open(sql_file, "r") as f:
            sql_content = f.read()
            # Split into commands (semi-naive split by semicolon)
            commands = [cmd.strip() for cmd in sql_content.split(";") if cmd.strip()]
            for command in commands:
                try:
                    print(f"SQL: {command[:60]}...")  # Print a preview
                    cursor.execute(command)
                    if command.lower().startswith("select"):
                        rows = cursor.fetchall()
                        for row in rows:
                            print("Result:", row)
                except Exception as e:
                    print(f"Error in '{sql_file}': {e}")

    cursor.close()
    conn.close()
    print("\nAll SQL files executed.")


def generate_many_to_many_inserts(join_table, left_ids, right_ids, num_links):
    """
    Generate SQL INSERT statements for a many-to-many relationship.

    :param join_table: Name of the join table
    :param left_ids: List of left-side IDs (e.g., student IDs)
    :param right_ids: List of right-side IDs (e.g., course IDs)
    :param num_links: Number of link entries to generate
    :return: String of SQL INSERT statements
    """
    inserts = []
    for _ in range(num_links):
        left_id = random.choice(left_ids)
        right_id = random.choice(right_ids)
        inserts.append(
            f"INSERT INTO {join_table} VALUES ({left_id}, {right_id});"
        )
    return "\n".join(inserts)