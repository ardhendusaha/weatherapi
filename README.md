To retrieve weather information
Use API - http://localhost:8080/api/weather/123452
Used H2 DB to store weather information. data.sql file will execute at the time server start and insert weather information into DB.
We can also add weather information to the DB using following api
Method:- POST,
API:- http://localhost:8080/api/weather
Request Body
{
    "zipcode":123456,
    "temperature":23.5
}
