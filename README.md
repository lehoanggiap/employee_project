# Installation

To run this project, you need to have:

`Java 17`: https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html <br>
`Maven` >= 3.0

# API

<b>Create employee</b>
```
POST: http://localhost:8080/employees

Request body:
{
  "name": "Giap",
  "birthdate": "2001-03-04",
  ...
}
```


<b>Update employee</b>
```
PUT: http://localhost:8080/employees

Request body:
{
  "name": "Giap",
  "birthdate": "2001-03-04",
  ...
}
```

<b>Update list employees</b>
```
PUT: http://localhost:8080/employees/update-batch

Request body:
[
  {
  "id":1,
  "name": "Giap",
  "birthdate": "2001-03-04",
  ...
  },
  {
  "id": 2,
  "name": "Hoang",
  "birthdate": "2001-04-04",
  ...
  }
]
```

<b>Get employee by id</b>
```
GET: http://localhost:8080/employees/{id}
```

<b>Get all employees</b>
```
GET: http://localhost:8080/employees
```






