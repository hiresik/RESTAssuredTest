Feature: Get Employees

 Scenario: Get all Employees
 Given User is on Rest API console
 When User login to the Rest API console with creds
 Then All the users are listed
 And All the users are listed with name, salary & age
