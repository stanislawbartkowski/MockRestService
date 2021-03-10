package com.org.mockrestservice;

import org.apache.commons.cli.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class Params {

    private static void P(String s) {
        System.out.println(s);
    }

    public static class RestParams {

        private final Optional<String> sslfile;
        private final int PORT;
        private final CommandLine cmd;

        RestParams(CommandLine cmd) {
            this.cmd = cmd;
            sslfile = cmd.getOptionValue('s') == null ? Optional.empty() : Optional.of(cmd.getOptionValue('s'));
            PORT = Integer.parseInt(cmd.getOptionValue('p'));
        }

        public int getPORT() {
            return PORT;
        }

        public CommandLine getCmd() {
            return cmd;
        }

        public Optional<String> getSSLFile() {
            return sslfile;
        }
    }

    public static Optional<RestParams> buildCmd(String[] args) {

        final Options options = new Options();
        Option port = Option.builder("p").longOpt("port").desc("Port number").numberOfArgs(1).type(Integer.class).required().build();
        Option par = Option.builder("s").longOpt("secureconf").desc("Secure configuration").numberOfArgs(1).build();
        options.addOption(port).addOption(par);
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            return Optional.of(new RestParams(cmd));
        } catch (ParseException e) {
            P(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java ... MockRestService", options);
            P("");
            P("Example:");
            P(" -s /home/sbartkowski/run/ssl.properties -p 9800");
            return Optional.empty();
        }
    }

}
