--------This project is about consuming third party web services using ProducerTemplate----------

Checkout the project and Run as a springboot application

The public APIs are taken from below website

https://gorest.co.in/rest-console    


The implemented APIs in the project,

      URL       :     http://localhost:9090/services/getAllUsers

      Method    :      GET


    ------------------------------------------------------------------------------------------------------- 


      URL       :     http://localhost:9090/services/getUserById/3563

      Method    :      GET


   --------------------------------------------------------------------------------------------------------

      URL       :     http://localhost:9090/services/addUser

      Method    :      POST


      Request Body

      {
        "id": 3563,
        "name": "testUser",
        "email": "testUser@barton.com",
        "gender": "male",
        "status": "active"
    }   

   ----------------------------------------------------------------------------------------------------------


      URL       :     http://localhost:9090/services/UpdateUser/3479

      Method    :      PUT


      Request body



      {
        "id": 3563,
        "name": "testUser",
        "email": "testUser@barton.com",
        "gender": "male",
        "status": "active"
    }

   -----------------------------------------------------------------------------------------------------------   







