package org.craftycoder.socialkata;

import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.craftycoder.socialkata.domain.actions.ViewUserTimeline;

public class SocialClient {

    private static final String EXIT = "exit";

    private final Console console;
    private final ActionDispatcher actionDispatcher;

    public SocialClient(Console console, PublishPostToTimeline publishPostToTimeline, ViewUserTimeline viewUserTimeline) {
        this.console = console;
        this.actionDispatcher = new ActionDispatcher(console, publishPostToTimeline, viewUserTimeline);
    }

    void start() {
        String textRead;
        do {
            textRead = console.read();
            actionDispatcher.dispatch(textRead);
        } while (!EXIT.equalsIgnoreCase(textRead));
    }

}
