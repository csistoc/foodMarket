import random
import sys

# first argument is the first table name
# second argument is the second table name
# third argument is the write file
# fourth argument is the name of the insert table
# fifth argument is the number of queries generated per relation
# sixth argument is the total number of generated queries


import random

def generate_many_to_many_inserts(join_table, left_ids, right_ids, num_links):
    """
    Generate SQL INSERT statements for a many-to-many relationship.

    :param join_table: Name of the join table
    :param left_ids: List of left-side IDs (e.g., student IDs)
    :param right_ids: List of right-side IDs (e.g., course IDs)
    :param num_links: Number of link entries to generate
    :return: String of SQL INSERT statements
    """
    inserts = []
    for _ in range(num_links):
        left_id = random.choice(left_ids)
        right_id = random.choice(right_ids)
        inserts.append(
            f"INSERT INTO {join_table} VALUES ({left_id}, {right_id});"
        )
    return "\n".join(inserts)

# Example usage
students = list(range(1, 11))    # student_id from 1 to 10
courses = list(range(101, 106))  # course_id from 101 to 105

sql_output = generate_many_to_many_inserts(
    join_table="student_courses",
    left_ids=students,
    right_ids=courses,
    num_links=20  # generate 20 random student-course assignments
)

# Save to file or print
with open("insert_student_courses.sql", "w") as file:
    file.write(sql_output)

print("Generated SQL insert queries:\n")
print(sql_output)