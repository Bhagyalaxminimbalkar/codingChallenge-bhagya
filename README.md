# Company Merger
This is a solution to a problem where the Product catalogs from 2 companies have to be merged after acquisition.
I have developed this application in SpringBoot, keeping in mind that the BAU activities could be carried out using 
the Rest API to update the products in future. 
Within the application :-
1. I have created the utility for parsing csv files. 
2. A Service which will merge the catalog
3. Update Product feature in future

# Pre-requisite
* Java 8, SpringBoot , gradle 

# Library Usage
* Create the `CsvReader` providing the Reader reading the csv and `CsvParser` Implementation DefaultCsvParser(). 
* CsvReader reads the data from the reader and return the object as CsvRow having map of column to field value which can
either integer or string. `CsvRow::getFields()` returning the maps created. 

# Build and Test 
* Package the library as a jar for usage. Run command `mvnw clean package` creating jar in dist folder.
* This library is using maven with development and test dependencies. 
To the run the application run command `mvnw clean compile exec:java`
* To run unit test run `mvnw test`


<b>Solution :-</b>
<br>
Create a Java application 
a. which has a utility to read csv files
b. Merge the catalog via a Service
c. Update Product feature

For merging basic algo :-
1. get  the SKU from Catalog a, compare that SKU in barcode A, get the barcode
2. Check the barcode A in barcode B csv, if its found then the merged catalog will have Product from A
3. 
