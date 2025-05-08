import sys

from database.PythonScripts.auxScripts import write_string_to_file
from database.PythonScripts.sqlAuxScripts import generate_inserts_with_three_random_ids

"""
    Generate SQL INSERT statements for a table ORDERS.

    sys.argv[1]: Name of the table in which we insert
    sys.argv[2]: Name of the first table from which we take the id
    sys.argv[3]: Name of the second table from which we take the id
    sys.argv[4]: Name of the third table from which we take the id
    sys.argv[5]: Number of link entries to generate
    sys.argv[6]: Username for the database connection
    sys.argv[7]: Password for the database connection
    sys.argv[8]: Name of the file in which we write the generated string
"""

write_string_to_file(
    generate_inserts_with_three_random_ids(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4], sys.argv[5], sys.argv[6], sys.argv[7]),
    sys.argv[8]
)