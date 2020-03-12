PORT=9800
SECURITY=secure.properties
#SECURITY=
java -cp target/MockRestService-1.0-SNAPSHOT-jar-with-dependencies.jar com.org.mockrestservice.MockRestService $PORT $SECURITY
