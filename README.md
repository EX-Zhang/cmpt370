# CMPT370_U_Daily

## Final Product

### Overview

* Our product based on the Android System and MySQL Database
* Folder "U_Daily (Final Product)" contains the final version of the source code.
* File "udaily.sql" contains the SQL query to create the database and insert the required data.

### How to build

* The MySQL Server should have user with username "u_daily" and password "123456".
* Using the SQL-Front or phpMyAdmin to import the file "udaily.sql" and execute to generate the database.
* The database is named "udaily"
* Use Android Studio to import the project. The app is running on the Android AVD.
* MySQL Server and the Android AVD should be running on the same computer if using the Android AVD for testing.

### How to run

* The URL of the database is set to be "10.0.2.22" which is the IP address of the host system connecting from the Android AVD.

### How to test

* To login the system, use the account "Username: qwe, Password: 123" for a student user and "Username: qwe, Password: 123" for a professor user.
* More details can be found in the recorded product demo and the presentation

## What is new (2020/03/25)

* Register and login functions are implemented

## What is new (2020/03/06)

* Folder App Part (milestone 8) contains the implementation of milestone8.
* The implementation can perform functions that rate a professor and post a new discussion.
* Class com.example.DBInterface.DBConnect contains the connection information. In our environment, database address is 192.168.0.13. Username is u_daily, Password is 123456
* The server part can perform the testing of insertion. It will display the comments of professors and the list of discussions.

## What is new (2020/03/05)

More test data is added to the database.

Server can get the discussion and it's replies from database.

Some functions still need to be discussed, the server part may be change a lot depend on the application in the future.

## Database
udaily.sql is the sql file of the database, generated by phpMyAdmin.

The address, username and password in the DBInterface/DBConnect.java is localhost:3306/udaily, root, 123456

## Server Implementation
Folder "CMPT370_Server" contains the server part of the whole project. Last modified: 2020/02/23 21:53

Package "DataClass" contains Classes to store the data selected from the database. It may change in the future.

Package "DBInterface" is to operate the database.

## Test Cases
BDTest.java contains the testing code for some functions.

### Currect functions

* Simulate logging with wrong Username/Password and with correct one by selecting the data from the table.
* Get the professor list.
* Display the specified professor and the comments about him/her.
* Display a discussion and its replies and the replies of replies.

## Implememtation
Implement the basic function of TextbookInfo class and inside the file it contains the test cases for the functions. 