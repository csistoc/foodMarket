import re
from datetime import datetime, timedelta
import random
import string


def strip_non_alphanumerical_from_file(file_in, file_out):
    filedata = ""
    with open(file_in, "r") as f:
        for line in f:
            # strip from each line any character that is not alphanumerical or _
            line = re.sub(r'\W+', '', line) + "\n"
            filedata += line
    with open(file_out, "w") as f:
        f.write(filedata)


def get_string_list_by_words(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        text = file.read()
    words = text.split()  # Splits on whitespace by default
    return words


def get_file_lines_as_list(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        lines = [line.strip() for line in file]  # .strip() removes newline characters
    return lines


def strip_special_characters(text):
    # Keeps only letters, numbers, and spaces
    return re.sub(r'[^A-Za-z0-9 ]+', '', text)


def generate_strong_random_string(length=12):
    chars = string.ascii_letters + string.digits  # + string.punctuation
    return ''.join(random.choice(chars) for _ in range(length))


def generate_random_date(start_date, end_date):
    # Convert strings to datetime objects if needed
    if isinstance(start_date, str):
        start_date = datetime.strptime(start_date, "%d-%m-%Y")
    if isinstance(end_date, str):
        end_date = datetime.strptime(end_date, "%d-%m-%Y")

    # Calculate time between dates
    delta = end_date - start_date
    random_days = random.randint(0, delta.days)

    return start_date + timedelta(days = random_days)


def generate_fake_phone_number():
    country_code = "+1"
    area_code = "555"  # Reserved for test numbers
    first_three = random.randint(100, 999)
    last_four = random.randint(1000, 9999)
    return f"{country_code}-{area_code}-{first_three}-{last_four}"


def escape_sql(value):
    """
    Escapes all SQL special characters to prevent SQL injection or syntax errors.
    """
    value = value.replace("'", "''")  # Escape single quotes by doubling them
    value = value.replace("\\", "\\\\")  # Escape backslashes by doubling them
    value = value.replace('"', '\\"')  # Escape double quotes
    value = value.replace(";", "\\;")  # Escape semicolon
    value = value.replace("--", "\\--")  # Escape double dashes (used for comments in SQL)
    value = value.replace("/*", "\\/*")  # Escape block comment start
    value = value.replace("*/", "\\*/")  # Escape block comment end
    value = value.replace("/*", "\\/*")  # Escape start of block comment
    value = value.replace("*/", "\\*/")  # Escape end of block comment
    value = value.replace("\n", "\\n")  # Escape newlines
    value = value.replace("\r", "\\r")  # Escape carriage returns
    value = value.replace("\t", "\\t")  # Escape tabs
    value = value.replace("|", "\\|")  # Escape pipe character
    value = value.replace("_", "\\_")  # Escape underline
    value = value.replace(">", "\\>").replace("<", "\\<")  # Escape greater-than and less-than signs
    value = value.replace("&", "\\&")  # Escape ampersands (&)
    value = value.replace("!", "\\!")  # Escape exclamation marks (!)
    value = value.replace("@", "\\@")  # Escape the @ symbol

    return str(value)