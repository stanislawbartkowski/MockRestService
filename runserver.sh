PORT=9800
# secure connection, uncomment below
KEYSTOREFILE=/home/sbartkowski/x/mykey.keystore
KEYPASSWORD=secret
STOREPASSWORD=secret
ALIAS=alias
# ---------
java -cp target/MockRestService-1.0-SNAPSHOT-jar-with-dependencies.jar com.org.mockrestservice.MockRestService $PORT $KEYSTOREFILE $KEYPASSWORD $STOREPASSWORD $ALIAS
