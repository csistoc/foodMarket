import sys

from database.PythonScripts.auxScripts import write_string_to_file
from database.PythonScripts.sqlAuxScripts import generate_user_table_insert_query

"""
    Generate a string of sql insert statements to populate the USERS table with random data and write it into a file.

    sys.argv[1]: Path to the file from which we take the user's first and second name
    sys.argv[2]: Path to the file from which we take the country
    sys.argv[3]: Name of the file in which we write
    sys.argv[4]: Name of the insert table
    sys.argv[5]: Number of generated queries
"""

write_string_to_file(
    generate_user_table_insert_query(sys.argv[1], sys.argv[2], sys.argv[4], sys.argv[5]),
    sys.argv[3]
)