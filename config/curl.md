#### get All Users

`curl -s http://localhost:8080/topjava/rest/admin/users`

#### get Users 100000

`curl -s http://localhost:8080/topjava/rest/admin/users/100000`

#### delete Users 100000

`curl -s -X DELETE http://localhost:8080/topjava/rest/admin/users/100000`

#### update User

`curl -s -X PUT -d '{"email":"update@gmail.com", "name":"UpdatedName", "caloriesPerDay":330, "password":"newPass", "enabled": false, roles:{"ADMIN"}}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/admin/users/100001`

#### get Meal 100003

`curl -s http://localhost:8080/topjava/rest/meals/100003`

#### get All Meals

`curl -s http://localhost:8080/topjava/rest/meals`

#### delete Meal 100003

`curl -s -X DELETE http://localhost:8080/topjava/rest/meals/100003`

#### filter Meals

`curl -s http://localhost:8080/topjava/rest/meals/between?startDateTime=2020-01-30T10:00&endDateTime=2020-01-30T14:00:00`

#### create Meal

`curl -s -X POST -d '{"dateTime":"2023-03-25T10:00","description":"User breakfast","calories":500}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/meals`