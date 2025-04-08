import sys

# first argument is number of table values

file_output_name = "createTableInsertWith" + sys.argv[0] + "Values.py"
filedata_write = ""

with (open(file_output_name, "w") as fw):
    filedata_write += ("import sys \n"
                       "# 0 -> n-3 are the read files \n"
                       "# n-2 argument is the write file \n"
                       "# n-1 argument is the name of the table \n\n"
                       "filedata_write = '"''"' \n\n")
    for i in sys.argv[0]:
        filedata_write += r'with open(sys.argv[' + i + r'], "r") as fr" + i + ":' + "\n"
    filedata_write += r'"with open(sys.argv[' + str(int(sys.argv[0]) + 1) + r'], "w") as fw:' + "\n"
    for i in sys.argv[0]:
        filedata_write += "for " + i + " in fr" + i + ":\n"
    filedata_write += r'filedata_write += "INSERT INTO " + sys.argv[" + str(int(sys.argv[0]) + 2) + " VALUES (DEFAULT, "'
    for i in sys.argv[0]:
        filedata_write += "i.strip('\n') + "
    #"INSERT INTO " + sys.argv[4] + " VALUES (DEFAULT, " + i.strip('\n') + ", " + j.strip('\n') + "');\n"
    filedata_write += ""
    filedata_write += "else:\nfw.write(filedata_write)"