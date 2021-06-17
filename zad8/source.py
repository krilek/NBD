import riak

client = riak.RiakClient()

abc = {"nickname": "abc", "password": "password1","age": 11, "sex": False}

b = client.bucket('DB')

record = b.new(abc["nickname"], data=abc).store()
print(f"Zapisano w bazie klucz: {record.key},\nwartosc: {record.data}")

record = b.get(record.key)
print(f"Odczytany wpis w bazie:\n{record.data}")

data = record.data
data["sex"] = True
record.data = data
record.store()

record = b.get(record.key)
print(f"Odczytany wpis po aktualizacji:\n{record.data}")

b.delete(record.key)
deleted_record = b.get(record.key)
print(f"Proba odczytu usunietego wpisu: {deleted_record.data}")