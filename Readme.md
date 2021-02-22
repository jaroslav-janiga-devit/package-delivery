# Package delivery #

## How to install the package delivery application ##
### Prerequisites ###
- java 1.8
- maven 

### Install ###
1. download project from repository 
2. use ***mvn clean install*** to build the application
3. use package-delivery-jar-with-dependencies.jar to run the application

## How to run the application ##

1. Copy package-delivery-jar-with-dependencies.jar from target directory to any other desired directory.
2. If you want to provide a file with packages or with fees then put it into the same directory as package-delivery-jar-with-dependencies.jar. 
3. Open command line and navigate to the directory from previous steps.
4. run command: ***java -jar ./package-delivery-jar-with-dependencies.jar <package_file> <fee_file>***
    - <package_file> a file that contains packages in the specified format
    - <fee_file> a file that contains fees in specified format

#### Example: ####
java -jar ./package-delivery-jar-with-dependencies.jar packages.txt fees.txt

## Exception handling ##

It is a simple command line application, so it is just an example how it can be done.
Exception called UserNotificationException is thrown when some issue appears. 
It also contains some brief description of what has happened.

### Error states ###

1. if a file, which is provided, does not exist then a user is notified by a message in command line
The applies to both cases a file for packages, and a file for fees
2. if format of fee or package in the file is wrong then  the user is notified and application starts normally
3. if format of package is not met in command line interface then user is notified, and it can try again. 
