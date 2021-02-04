Note: Not taking results from https://www.ing.nl/api/locator/atms/ as JSON format is not correct in response.
Loaded the atms into a file and using it.

How to run:
--------------------
1. Goto repository home directory and Build the project
mvn clean install

2. Start the REST service by running the generated jar from target directory.
java -jar target\atm-provider-0.0.1-SNAPSHOT.jar

3. To list all Atms:
curl -X GET "http://localhost:8080/atms/"

4. To get Atms for a city:
curl -X GET "http://localhost:8080/atms/?city=Ede"

