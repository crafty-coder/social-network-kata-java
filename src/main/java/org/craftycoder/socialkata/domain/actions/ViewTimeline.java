package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.service.TimelineService;
import org.craftycoder.socialkata.domain.util.TimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class ViewTimeline {

    private final Clock clock;
    private final TimelineService timelineService;

    public ViewTimeline(final TimelineService timelineService, final Clock clock) {
        this.timelineService = timelineService;

        this.clock = clock;
    }

    public List<String> view(final String user) {

        return timelineService.getTimeline(user).posts.stream()
                .map(this::formatPost)
                .collect(Collectors.toList());
    }

    private String formatPost(final Post post) {
        return post.message + " " + TimeFormatter.timeAgoFormatter(clock.now(), post.timestamp);
    }

}
