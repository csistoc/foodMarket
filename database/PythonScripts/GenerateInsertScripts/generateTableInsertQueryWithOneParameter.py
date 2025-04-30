import sys

from database.PythonScripts.sqlAuxScripts import generate_table_insert_with_one_row

generate_table_insert_with_one_row(sys.argv[1], sys.argv[2], sys.argv[3])