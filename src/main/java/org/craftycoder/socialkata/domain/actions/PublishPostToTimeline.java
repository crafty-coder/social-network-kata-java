package org.craftycoder.socialkata.domain.actions;


import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.ports.Clock;

public class PublishPostToTimeline {

    private final Timeline timeline;
    private final Clock clock;

    public PublishPostToTimeline(Timeline timeline, Clock clock) {
        this.timeline = timeline;
        this.clock = clock;
    }

    public void publishPost(String user, String text) {
        timeline.save(new Post(user, text, clock.now()));
    }

}
