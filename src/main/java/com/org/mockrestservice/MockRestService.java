package com.org.mockrestservice;

import com.rest.restservice.*;

import java.io.IOException;


public class MockRestService extends RestStart {

    private static void P(String s) {
        System.out.println(s);
    }

    private static void help() {
        P("Parameters: /port number");
        P("Example");
        P(" java ...  9800 ");
        System.exit(4);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            help();
        }
        int PORT = Integer.parseInt(args[0]);
        RestStart(PORT, (server) -> new RestServices().resgisterServices(server));
    }
}
