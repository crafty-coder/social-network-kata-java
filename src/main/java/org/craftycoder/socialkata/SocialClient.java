package org.craftycoder.socialkata;

import org.craftycoder.socialkata.actions.PublishToTimeline;

public class SocialClient {

    private static final String EXIT = "exit";

    private final Console console;
    private final PublishToTimeline publishToTimeline;

    public SocialClient(Console console, PublishToTimeline publishToTimeline) {
        this.console = console;
        this.publishToTimeline = publishToTimeline;
    }

    void start() {
        String textRead;
        do{
            textRead = console.read();
            ifActionThenDispatch(textRead);

        }while (!EXIT.equalsIgnoreCase(textRead));
    }

    private void ifActionThenDispatch(String text){
        if (!EXIT.equalsIgnoreCase(text)){
            publishToTimeline.publish("Alice","I love the weather today");
        }
    }
}
