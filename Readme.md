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
