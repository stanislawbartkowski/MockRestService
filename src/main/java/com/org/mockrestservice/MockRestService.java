package com.org.mockrestservice;

import com.rest.restservice.*;

// -Dsun.security.krb5.debug=true -Djava.security.auth.login.config=/home/sbartkowski/projects/MockRestService/src/main/resources/server_jaas.conf

// -Djavax.net.debug=all
// 9800 /home/sbartkowski/projects/MockRestService/src/test/resources/secure.properties

public class MockRestService extends RestStart {

    private static void P(String s) {
        System.out.println(s);
    }

    // 9800 src/main/resources/secure.properties

    private static void help() {
        P(" Non-secure HTTP connection:");
        P("   Parameters: /port number/");
        P("     Example");
        P("       java ...  9800 ");
        P("");
        P(" Secure HTTP connection:");
        P("      Parameters: /port number/ /secure conf/");
        System.exit(4);
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1 && args.length != 2) {
            help();
        }
        int PORT = Integer.parseInt(args[0]);
        RestStart(PORT, (server) -> new RestServices().registerServices(server),
                (args.length == 1) ?
                        new String[]{} :
                        SSLParam.readConf(args[1])
        );
    }
}
