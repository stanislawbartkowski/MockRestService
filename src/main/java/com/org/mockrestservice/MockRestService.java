package com.org.mockrestservice;

import com.rest.restservice.*;

import java.io.*;
import java.util.Properties;


public class MockRestService extends RestStart {

    private static void P(String s) {
        System.out.println(s);
    }

    private static final String STOREKEY = "store.key.filename";
    private static final String STOREPASSWORD = "key.store.password";
    private static final String ALIAS = "alias";

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

    private static String getParam(Properties prop, String key) throws IOException {
        String res = prop.getProperty(key);
        if (res == null || "".equals(res)) {
            String mess = "Parameter " + key + " not found in the secure property file";
            RestLogger.L.severe(mess);
            throw new IOException(mess);
        }
        return res;
    }

    private static String[] readConf(String filename) throws IOException {
        try (InputStream input = new FileInputStream(filename)) {
            Properties prop = new Properties();
            prop.load(input);
            return new String[]{
                    getParam(prop, STOREKEY),
                    getParam(prop, STOREPASSWORD),
                    getParam(prop, STOREPASSWORD),
                    getParam(prop, ALIAS)
            };
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1 && args.length != 2) {
            help();
        }
        int PORT = Integer.parseInt(args[0]);
        RestStart(PORT, (server) -> new RestServices().registerServices(server),
                (args.length == 1) ?
                        new String[]{} :
                        readConf(args[1])
        );
    }
}
