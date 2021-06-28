#Person Application setup and  API Details

1.Download the Project folder from the given link
 and save it to  in any location.
2.Open the STS(Spring tool suite) IDE.
3.Right click on the Package Explorer.
4.Choose import option and click on  import.
5.One pop-up will be opened.
6.Select Maven and expand it.
7.click on Existing Maven Project.
8.Please give your (downloaded)project path.
9.Open PersonAppApplication.java class (com.person.PersonAppApplication)
10.Right click   in the PersonAppApplication.java class and
choose Run As and click Spring Boot App.
11.Now Embedded tomcat will be started.


================================================

Test the API Calls
----------------------
1.open postman
2.From the drop down select POST option
3.Give the below url
http://localhost:8080/api/person/addPerson

click on Body and select Json option.

Give the below  sample input.


{  
    "id": "1001",  
    "firstName": "Arun",  
    "surName": "B" 
	
}  


Click on Send button.We should get  status 200 OK.




4.Now from the drop down select GET and give below urls


a)http://localhost:8080/api/person/allpersons

click on GET button, we will get the response in JSON format.

b)http://localhost:8080/api/person/countall
Click on GET Button, we will get an integer value(number of persons)



5.Now from the drop down select PUT and give below urls.

a)http://localhost:8080/api/person/editPerson

Click on PUT button.

click on Body and select JSON.

give the below sample data.

{  
    "id": "1001",  
    "firstName": "Arun",  
    "surName": "Kumar" 
	
}

We will get  updated Person details.



5.Now from the drop down select DELETE and give below urls.

a)http://localhost:8080/api/person/deleteperson/1001

Click on DELETE Button and we will get 


We will get response as a String whether record got deleted or not.



  
#Junit Test Cases

Open PersonAppApplicationTests.java 
RunAs -->Junt Test

#Swagger URL

http://localhost:8080/swagger-ui.html


























