package com.org.mockrestservice;

import com.rest.restservice.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

// -Djava.security.auth.login.config=/home/sbartkowski/projects/MockRestService/src/main/resources/server_jaas.conf
// -Dsun.security.krb5.debug=true

class RestServices {

    private AtomicInteger counter = new AtomicInteger(0);

    private abstract class MockServiceHelper extends RestHelper.RestServiceHelper {

        MockServiceHelper(String url) {
            super(url, false);
        }

        @Override
        public RestParams getParams(HttpExchange httpExchange) throws IOException {
            List<String> methods = new ArrayList<String>();
            methods.add(RestHelper.GET);
            methods.add(RestHelper.POST);
            return new RestParams(RestHelper.GET, Optional.of(RestParams.CONTENT.TEXT), false, methods);
        }
    }

    class ResetCounter extends MockServiceHelper {

        ResetCounter() {
            super("resetcounter");
        }

        @Override
        public void servicehandle(RestHelper.IQueryInterface v) throws IOException {
            counter = new AtomicInteger(0);
            produceNODATAResponse(v);
        }
    }

    class Counter extends MockServiceHelper {

        Counter() {
            super("counter");
        }

        @Override
        public void servicehandle(RestHelper.IQueryInterface v) throws IOException {
            String s = Integer.toString(counter.get());
            produceOKResponse(v, s);
        }
    }

    class IncCounter extends RestHelper.RestServiceHelper {

        IncCounter() {
            super("rest", false);
        }

        @Override
        public RestParams getParams(HttpExchange httpExchange) throws IOException {
            List<String> methods = new ArrayList<String>();
            methods.add(RestHelper.POST);
            RestParams par = new RestParams(RestHelper.POST, Optional.of(RestParams.CONTENT.TEXT), false, methods);
            par.addParam("content", PARAMTYPE.STRING);
            return par;
        }

        @Override
        public void servicehandle(RestHelper.IQueryInterface v) throws IOException {
            counter.incrementAndGet();
            String content = getStringParam(v, "content");
            RestLogger.info(counter + " received=" + content.length());
            produceOKResponse(v, "received (" + counter.get() + ")");
        }
    }

    class UploadFile extends RestHelper.RestServiceHelper {

        UploadFile() {
            super("upload", false);
        }

        @Override
        public RestParams getParams(HttpExchange httpExchange) throws IOException {
            List<String> methods = new ArrayList<String>();
            methods.add(RestHelper.POST);
            RestParams par = new RestParams(RestHelper.POST, Optional.empty(), false, methods);
            return par;
        }

        @Override
        public void servicehandle(RestHelper.IQueryInterface v) throws IOException {

            String s = getRequestBodyString(v);
            // only print uploaded data
            RestLogger.info(s);
            produceNODATAResponse(v);
        }
    }

    void registerServices(HttpServer server) {
        RestHelper.registerService(server, new ResetCounter());
        RestHelper.registerService(server, new Counter());
        RestHelper.registerService(server, new IncCounter());
        RestHelper.registerService(server, new UploadFile());
    }

}
