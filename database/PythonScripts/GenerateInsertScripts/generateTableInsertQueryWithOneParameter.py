import sys

from database.PythonScripts.auxScripts import get_string_list_by_words

# first argument is the ingredient read file
# second argument is the write file
# third argument is the name of the insert table

ingredient_list = get_string_list_by_words(sys.argv[1])
insert = ""

with open(sys.argv[1], "r") as fr:
    for line in fr:
        insert += (
                f"INSERT INTO " + sys.argv[3] + " VALUES (DEFAULT, '" + line.strip('\n') + "');\n"
        )

with open(sys.argv[2], "w") as fw:
    fw.write(insert)