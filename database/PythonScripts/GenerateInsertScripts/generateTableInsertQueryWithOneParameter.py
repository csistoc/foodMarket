import sys

from database.PythonScripts.auxScripts import write_string_to_file
from database.PythonScripts.sqlAuxScripts import generate_table_insert_with_one_row

"""
    Generate a string of sql insert statements to populate with random data a table with only 1 row and write it into a file.

    sys.argv[1]: Name of the file from which we take the data
    sys.argv[2]: Name of the file in which we write the sql inserts
    sys.argv[3]: Name of the table in which we insert
"""

write_string_to_file(
    generate_table_insert_with_one_row(sys.argv[1], sys.argv[3]),
    sys.argv[2]
)