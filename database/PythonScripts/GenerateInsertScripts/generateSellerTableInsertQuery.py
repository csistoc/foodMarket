import sys

from database.PythonScripts.sqlAuxScripts import generate_seller_table_insert_queries

generate_seller_table_insert_queries(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4], sys.argv[5])