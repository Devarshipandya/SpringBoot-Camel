#Project is for publishing sample REST web services using mock data
#Same time will generate Swagger file also for published API

-------------------------------------------------------------------

Below scenarios are implemented,

1.     Display hello world message 

Use below URL to hit API and return constant hello-world message

Request method    :   Get
URL               :   http://localhost:9090/services/hello-world/
Response          :   "hello-world"

--------------------------------------------------------------------------------

2.    Get list of orders from a defined list

Request method    :  Get
URL               :  http://localhost:9090/services/getOrders/
Response          :
[
    {
        "id": 67,
        "name": "Mobile",
        "price": 5000.0
    },
    {
        "id": 89,
        "name": "Book",
        "price": 400.0
    },
    {
        "id": 45,
        "name": "AC",
        "price": 15000.0
    },
    {
        "id": 67,
        "name": "Shoes",
        "price": 4000.0
    }
]

--------------------------------------------------------------------------------

3.    Get order based on Id

Request method    :  Get
URL               :  http://localhost:9090/services/getOrders/{id}
Response          :
    {
        "id": 67,
        "name": "Mobile",
        "price": 5000.0
    }

-----------------------------------------------------------------------------------------------
4.    Add an order to the existing order list

Request Method     :   POST
URL                :   http://localhost:9090/services/insertOrder/
Request body       :   
  {
        "id": 17,
        "name": "Telivision",
        "price": 5000.0
    },

Response           :  
	Order created successfully 


 ----------------------------------------------------------------------------------------------------------
 5.    Generate Swagger File

Request Method     :   GET
URL                :   http://localhost:9090/services/api-doc
Response           :  Swagger file in jason format


Request Method     :   GET
URL                :   http://localhost:9090/services/api-doc/swagger.yaml
Response           :  Swagger file in Yaml format


Note : Copy content of Swagger file and past it in https://editor.swagger.io/ to test runtime in browser

	
 