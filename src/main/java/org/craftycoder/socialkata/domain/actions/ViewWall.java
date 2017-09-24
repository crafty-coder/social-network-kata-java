package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.service.TimelineService;
import org.craftycoder.socialkata.domain.util.TimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class ViewWall {


    private final TimelineService timelineService;
    private final Clock clock;

    public ViewWall(final TimelineService timelineService, final Clock clock) {
        this.timelineService = timelineService;
        this.clock = clock;
    }

    public List<String> view(String user) {

        return timelineService.getTimeline(user).posts.stream()
                .map(this::formatPost)
                .collect(Collectors.toList());
    }

    private String formatPost(Post post) {
        return post.user + " - " + post.message + " " + TimeFormatter.timeAgoFormatter(clock.now(), post.timestamp);
    }


}
