# Project Title

This is a project which simulates a production line manager software which was developed for competing in SLIIT - CodeFest Hackathon

## Getting Started

First clone the project using "git clone https://gitlab.com/codefestderhackerz/codefest.git"
Then run the project using a prefered IDE

### Prerequisites

Install the latest version of Java and Maven
Live mysql server must be running in default port : 3306 for database access
Please refer to databaseConnection.java
Specify a username and a password for your mysql server


### Running

To begin the process, compile and run the file located in testpackage/src/main/java which will start the production line simulation.
Then compile and run the main.java file located in codefest/src/main/java/sample
A simple gui with the related charts and options will be shown.
The chart will be updated every 10 seconds.

## Running the tests

Project can be tested for employee data by editing the csv file in codefest/src/main/java/csv_new_reader
The file is in the format,
   * Production Line, No of production lines
   * Managers, Managers names
   * Session Length, Time in hours and minutes in ISO international format
   * Stages, No of stages in the production lines
   * Employee Name,Session Start Time,Stage,Production Line
 To test, edit this file according to the related format.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Ranika Madurawe**
* **Dilan Sachintha**
* **Manusha Karunathilake**
* **Sachith Senanayake**