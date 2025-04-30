import sys

from database.PythonScripts.auxScripts import refresh_database

# first argument is database username
# second argument is database password
# third argument is sql files location

refresh_database(sys.argv[1], sys.argv[2], sys.argv[3])