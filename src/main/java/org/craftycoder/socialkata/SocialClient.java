package org.craftycoder.socialkata;

public class SocialClient {

    public SocialClient(Console console) {

        String command;
        do{
            command = console.read();
        }while (!"exit".equalsIgnoreCase(command));

    }
}
