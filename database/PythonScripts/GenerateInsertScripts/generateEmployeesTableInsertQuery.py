import sys

from database.PythonScripts.auxScripts import write_string_to_file
from database.PythonScripts.sqlAuxScripts import generate_many_to_many_inserts, get_ids_from_table, \
    get_user_id_for_employee_inserts

"""
    sys.argv[1] = Database username
    sys.argv[2] = Database password
    sys.argv[3] = First table name
    sys.argv[4] = Second table name
    sys.argv[5] = Join table name
    sys.argv[6] = Number of generated inserts
    sys.argv[7] = Name of the file in which we write the list of sql commands
"""

write_string_to_file(
    generate_many_to_many_inserts(
        sys.argv[5],
        get_user_id_for_employee_inserts(sys.argv[1], sys.argv[2], sys.argv[3]),
        get_ids_from_table(sys.argv[1], sys.argv[2], sys.argv[4]),
        sys.argv[6]),
    sys.argv[7]
)