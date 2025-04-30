import sys

from database.PythonScripts.sqlAuxScripts import generate_user_table_insert_query

generate_user_table_insert_query(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4], sys.argv[5])