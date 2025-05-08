import sys

from database.PythonScripts.sqlAuxScripts import refresh_database

"""
    sys.argv[1]: database username
    sys.argv[2]: database password
    sys.argv[3]: sql files location
"""

refresh_database(sys.argv[1], sys.argv[2], sys.argv[3])