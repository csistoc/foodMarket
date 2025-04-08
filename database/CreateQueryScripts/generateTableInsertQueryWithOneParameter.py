import sys

from database.CreateQueryScripts.auxScripts import get_string_list_by_words

# first argument is the ingredient read file
# second argument is the write file
# third argument is the name of the insert table

ingredient_list = get_string_list_by_words(sys.argv[1])
filedata_write = ""

with open(sys.argv[1], "r") as fr:
    with open(sys.argv[2], "w") as fw:
        for line in fr:
            filedata_write += "INSERT INTO " + sys.argv[3] + " VALUES (DEFAULT, '" + line.strip('\n') + "');\n"
        fw.write(filedata_write)