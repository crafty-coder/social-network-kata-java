package org.craftycoder.socialkata.delivery;

import org.craftycoder.socialkata.domain.actions.FollowUser;
import org.craftycoder.socialkata.domain.actions.PublishPost;
import org.craftycoder.socialkata.domain.actions.ViewTimeline;

public class SocialClient {

    private static final String EXIT = "exit";

    private final Console console;
    private final ActionDispatcher actionDispatcher;

    public SocialClient(Console console, PublishPost publishPost, ViewTimeline viewTimeline, FollowUser followUser) {
        this.console = console;
        this.actionDispatcher = new ActionDispatcher(console, publishPost, viewTimeline, followUser);
    }

    public void start() {
        String textRead;
        do {
            textRead = console.read();
            actionDispatcher.dispatch(textRead);
        } while (!EXIT.equalsIgnoreCase(textRead));
    }

}
