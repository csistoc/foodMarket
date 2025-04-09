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
    with open(file_path, 'r', encoding = 'utf-8') as file:
        text = file.read()
    words = text.split()  # Splits on whitespace by default
    return words

def generate_strong_random_string(length = 12):
    chars = string.ascii_letters + string.digits + string.punctuation
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