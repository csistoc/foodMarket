import random
import sys

from database.PythonScripts.auxScripts import get_string_list_by_words, generate_fake_phone_number, escape_sql, \
    get_file_lines_as_list, strip_special_characters

# first argument is the username read file
# second argument is the country read file
# third argument is the write file
# fourth argument is the name of the insert table
# fifth argument is the number of queries generated

name_list = get_file_lines_as_list(sys.argv[1])
country_list = get_string_list_by_words(sys.argv[2])
insert = ""

for i in range(int(sys.argv[5])):
    name = strip_special_characters(random.choice(name_list))
    address = escape_sql(random.choice(country_list))
    phone_number = escape_sql(generate_fake_phone_number())
    #filedata_write += "INSERT INTO " + sys.argv[4] + " VALUES (DEFAULT, '" + username + "', '" + password + "', '" + email + "', '" + first_name + "', '" + last_name + "', '" + address + "', " + str(date_of_birth) + ", 0, 0);\n"
    insert += (
        f"INSERT INTO {sys.argv[4]} VALUES (DEFAULT, '{name}', '{address}', '{str(phone_number)}');\n"
    )

with (open(sys.argv[3], "w") as fw):
    fw.write(insert)