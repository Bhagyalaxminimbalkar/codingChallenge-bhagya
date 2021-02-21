# Company Merger
This is a solution to a problem where the Product catalogs from 2 companies have to be merged after acquisition.
I have developed this application in SpringBoot, keeping in mind that the BAU activities could be carried out using 
the Rest API to update the products in future. 
Within the application :-
1. I have created the utility for parsing csv files. 
2. A Service which will merge the catalog
3. Update Product feature in future

I have not included any logging and exception handling, 
there is scope of few improvements but limiting it based on time.


# Pre-requisite
* Java 8, junit5 , SpringBoot , gradle 

# Build and Test
Package the library as a jar for usage. Run command 
./gradlew clean build --refresh-dependencies
This library is using gradle with development and test dependencies. 
To the run the application run command - ./gradlew bootrun
To run unit test run ./gradlew test


