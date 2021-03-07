package com.org.mockrestservice;

import com.rest.restservice.*;

import java.util.Optional;

// -Dsun.security.krb5.debug=true -Djava.security.auth.login.config=/home/sbartkowski/projects/MockRestService/src/main/resources/server_jaas.conf

// -Djavax.net.debug=all
// 9800 /home/sbartkowski/projects/MockRestService/src/test/resources/secure.properties

public class MockRestService extends RestStart {

    private static void P(String s) {
        System.out.println(s);
    }

    // 9800 src/main/resources/secure.properties

    public static void main(String[] args) throws Exception {
        Optional<Params.RestParams> par = Params.buildCmd(args);
        if (!par.isPresent()) System.exit(4);
        RestStart(par.get().getPORT(), (server) -> new RestServices().registerServices(server),
                (!par.get().getSSLFile().isPresent()) ?
                        new String[]{} :
                        SSLParam.readConf(par.get().getSSLFile().get())
        );
    }
}
