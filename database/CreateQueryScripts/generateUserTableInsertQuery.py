import random
import sys

from database.CreateQueryScripts.auxScripts import get_string_list_by_words, generate_strong_random_string, \
    generate_random_date

# first argument is the username read file
# second argument is the country read file
# third argument is the write file
# fourth argument is the name of the insert table
# fifth argument is the number of queries generated

name_list = get_string_list_by_words(sys.argv[1])
country_list = get_string_list_by_words(sys.argv[2])
filedata_write = ""

with (open(sys.argv[3], "w") as fw):
    for i in range(int(sys.argv[5])):
        rnd_name = random.sample(name_list, 2)  # Pick 2 unique names
        username = rnd_name[0] + rnd_name[1]
        password = generate_strong_random_string(12)
        email = rnd_name[0] + "." + rnd_name[1] + "@email.com"
        first_name = rnd_name[0]
        last_name = rnd_name[1]
        address = random.choice(country_list)
        date_of_birth = generate_random_date("01-01-1950", "01-01-2000")
        filedata_write += "INSERT INTO " + sys.argv[4] + " VALUES (DEFAULT, '" + username + "', '" + password + "', '" + email + "', '" + first_name + "', '" + last_name + "', '" + address + "', " + str(date_of_birth) + ", 0, 0);\n"
    fw.write(filedata_write)