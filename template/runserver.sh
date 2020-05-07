PORT=9800
#SECURITY=secure.properties
#SECURITY=
KERBEROS="-Djava.security.auth.login.config=$PWD/server_jaas.conf"
#KERBEROSDEBUG=-Dsun.security.krb5.debug=true
java $KERBEROS $KERBEROSDEBUG -cp target/MockRestService-1.0-SNAPSHOT-jar-with-dependencies.jar com.org.mockrestservice.MockRestService $PORT $SECURITY
