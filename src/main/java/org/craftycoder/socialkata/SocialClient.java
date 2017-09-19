package org.craftycoder.socialkata;

import org.craftycoder.socialkata.actions.PublishToTimeline;

public class SocialClient {

    public SocialClient(Console console, PublishToTimeline publishToTimeline) {

        String textReaded;
        do{
            textReaded = console.read();
            ifActionThenDispatch(textReaded,publishToTimeline);

        }while (!"exit".equalsIgnoreCase(textReaded));

    }

    private void ifActionThenDispatch(String text, PublishToTimeline publishToTimeline){
        if (!"exit".equalsIgnoreCase(text)){
            publishToTimeline.publish("Alice","I love the weather today");
        }
    }
}
