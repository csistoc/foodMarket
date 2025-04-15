import sys
import jaydebeapi
import os
import glob

# first argument is database username
# second argument is database password
# third argument is sql files location

# configuration
sql_folder_path = sys.argv[3]  # Folder with .sql files
h2_jar_path = r"C:\Program Files (x86)\H2\bin\h2-2.3.232.jar"
jdbc_url = "jdbc:h2:~/testdb"  # File-based DB in home folder
jdbc_driver = "org.h2.Driver"
username = sys.argv[1]
password = sys.argv[2]

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