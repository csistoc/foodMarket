import random
import sys

from database.PythonScripts.auxScripts import get_string_list_by_words, generate_strong_random_string, \
    generate_random_date

# first and second argument are the table names
# third argument is the write file
# fourth argument is the name of the insert table
# fifth argument is the number of queries generated per relation
# sixth argument is the number of overall generated queries

'''
table1 = get_string_list_by_words(sys.argv[1])
table2 = get_string_list_by_words(sys.argv[2])
filedata_write = ""

with (open(sys.argv[3], "w") as fw):
    for i in range(int(sys.argv[5])):
        rnd_name = random.sample(name_list, 2)  # Pick 2 unique names
        username = rnd_name[0] + rnd_name[1]
        password = generate_strong_random_string(12)
        email = rnd_name[0] + "." + rnd_name[1] + "@email.com"
        first_name = rnd_name[0]
        last_name = rnd_name[1]
        address = random.choice(country_list)
        date_of_birth = generate_random_date("01-01-1950", "01-01-2000")
        filedata_write += "INSERT INTO " + sys.argv[4] + " VALUES (DEFAULT, '" + username + "', '" + password + "', '" + email + "', '" + first_name + "', '" + last_name + "', '" + address + "', " + str(date_of_birth) + ", 0, 0);\n"
    fw.write(filedata_write)
'''

import random

def generate_many_to_many_inserts(join_table, left_table_col, right_table_col, left_ids, right_ids, num_links):
    """
    Generate SQL INSERT statements for a many-to-many relationship.

    :param join_table: Name of the join table
    :param left_table_col: Column name for the left table's ID
    :param right_table_col: Column name for the right table's ID
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
            f"INSERT INTO {join_table} ({left_table_col}, {right_table_col}) VALUES ({left_id}, {right_id});"
        )
    return "\n".join(inserts)

# Example usage
students = list(range(1, 11))    # student_id from 1 to 10
courses = list(range(101, 106))  # course_id from 101 to 105

sql_output = generate_many_to_many_inserts(
    join_table="student_courses",
    left_table_col="student_id",
    right_table_col="course_id",
    left_ids=students,
    right_ids=courses,
    num_links=20  # generate 20 random student-course assignments
)

# Save to file or print
with open("insert_student_courses.sql", "w") as file:
    file.write(sql_output)

print("Generated SQL insert queries:\n")
print(sql_output)
