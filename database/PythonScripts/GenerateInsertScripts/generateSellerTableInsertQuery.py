import sys

from database.PythonScripts.auxScripts import write_string_to_file
from database.PythonScripts.sqlAuxScripts import generate_seller_table_insert_queries

"""
    Generate insert statements to populate the SELLERS table with random data and write them into a file.

    sys.argv[1]: Path to the file from which we take the seller's name
    sys.argv[2]: Path to the file from which we take the seller's country
    sys.argv[3]: Name of the table in which we plan to insert
    sys.argv[4]: Number of queries we plan to generate
    sys.argv[5]: Name of the file in which we write the sql inserts
"""

write_string_to_file(
    generate_seller_table_insert_queries(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4]),
    sys.argv[5]
)