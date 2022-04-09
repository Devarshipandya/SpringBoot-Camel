#Project is for publishing sample REST web services using mock data & timer
#Same time will generate Swagger file also for published API

-------------------------------------------------------------------

Try with below URLs

http://localhost:9090/camelrest/products
http://localhost:9090/camelrest/products/discounts/{id} i.e. http://localhost:9090/camelrest/products/discounts/1


 ----------------------------------------------------------------------------------------------------------
 5.    Generate Swagger File

Request Method     :   GET
URL                :   http://localhost:9090/camelrest/api-doc
Response           :  Swagger file in jason format


Request Method     :   GET
URL                :   http://localhost:9090/camelrest/api-doc/swagger.yaml
Response           :  Swagger file in Yaml format


Note : Copy content of Swagger file and past it in https://editor.swagger.io/ to test runtime in browser

	
 