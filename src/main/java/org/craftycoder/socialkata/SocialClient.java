package org.craftycoder.socialkata;

public class SocialClient {

    public SocialClient(Console console) {

        String command;
        do{
            command = console.read();
            System.out.println("reading " + command);
        }while (!"exit".equalsIgnoreCase(command));


    }
}
