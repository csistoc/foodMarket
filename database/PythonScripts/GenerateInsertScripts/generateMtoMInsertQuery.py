import sys

from database.PythonScripts.sqlAuxScripts import generate_many_to_many_inserts, get_table_row_count

"""
    sys.argv[1] = Database username
    sys.argv[2] = Database password
    sys.argv[3] = First table name
    sys.argv[4] = Second table name
    sys.argv[5] = Join table name
    sys.argv[6] = Number of generated inserts
    sys.argv[7] = Name of the file in which we write the list of sql commands
"""

first_table_count = get_table_row_count(sys.argv[1], sys.argv[2], sys.argv[3])
second_table_count = get_table_row_count(sys.argv[1], sys.argv[2], sys.argv[4])

first_table_ids = list(range(1, first_table_count))
second_table_ids = list(range(1, second_table_count))

sql_output = generate_many_to_many_inserts(sys.argv[5], first_table_ids, second_table_ids, sys.argv[6])

with open(sys.argv[7], "w") as file:
    file.write(sql_output)

print("Generated SQL insert queries:\n")
print(sql_output)