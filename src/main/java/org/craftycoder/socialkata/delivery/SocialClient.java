package org.craftycoder.socialkata.delivery;

import org.craftycoder.socialkata.domain.actions.FollowUser;
import org.craftycoder.socialkata.domain.actions.PublishPost;
import org.craftycoder.socialkata.domain.actions.ViewTimeline;
import org.craftycoder.socialkata.domain.actions.ViewWall;

public class SocialClient {

    private static final String EXIT = "exit";

    private final Console console;
    private final ActionDispatcher actionDispatcher;

    public SocialClient(final Console console, final PublishPost publishPost, final ViewTimeline viewTimeline, final FollowUser followUser, final ViewWall viewWall) {
        this.console = console;
        this.actionDispatcher = new ActionDispatcher(console, publishPost, viewTimeline, followUser, viewWall);
    }

    public void start() {
        String textRead;
        do {
            textRead = console.read();
            actionDispatcher.dispatch(textRead);
        } while (!EXIT.equalsIgnoreCase(textRead));
    }

}
