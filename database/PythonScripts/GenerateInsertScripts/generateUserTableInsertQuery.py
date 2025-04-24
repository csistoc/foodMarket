import random
import sys

from database.PythonScripts.auxScripts import get_string_list_by_words, generate_strong_random_string, \
    generate_random_date, generate_fake_phone_number, escape_sql

# first argument is the username read file
# second argument is the country read file
# third argument is the write file
# fourth argument is the name of the insert table
# fifth argument is the number of queries generated

name_list = get_string_list_by_words(sys.argv[1])
country_list = get_string_list_by_words(sys.argv[2])
insert = ""

for i in range(int(sys.argv[5])):
    rnd_name = random.sample(name_list, 2)  # Pick 2 unique names
    username = escape_sql(rnd_name[0] + rnd_name[1])
    password = escape_sql(generate_strong_random_string(12))
    email = escape_sql(rnd_name[0] + "." + rnd_name[1]) + "@email.com"
    first_name = escape_sql(rnd_name[0])
    last_name = escape_sql(rnd_name[1])
    address = escape_sql(random.choice(country_list))
    phone_number = escape_sql(generate_fake_phone_number())
    date_of_birth = generate_random_date("01-01-1950", "01-01-2000").strftime('%Y-%m-%d')
    #filedata_write += "INSERT INTO " + sys.argv[4] + " VALUES (DEFAULT, '" + username + "', '" + password + "', '" + email + "', '" + first_name + "', '" + last_name + "', '" + address + "', " + str(date_of_birth) + ", 0, 0);\n"
    insert += (
        f"INSERT INTO {sys.argv[4]} VALUES (DEFAULT, '{username}', '{password}', "
        f"'{email}', '{first_name}', '{last_name}', '{address}', '{str(phone_number)}', "
        f"'{date_of_birth}', FALSE, FALSE);\n"
    )

with (open(sys.argv[3], "w") as fw):
    fw.write(insert)