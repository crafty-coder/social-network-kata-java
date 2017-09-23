package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.util.TimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class ViewUserTimeline {

    private final Timeline timelineMock;
    private final Clock clockMock;

    public ViewUserTimeline(Timeline timelineMock, Clock clockMock) {

        this.timelineMock = timelineMock;
        this.clockMock = clockMock;
    }

    public List<String> view(String user) {

        return timelineMock.filterByUserReverseSorting(user).stream()
                .map(this::formatPost)
                .collect(Collectors.toList());
    }

    private String formatPost(Post post) {
        return post.message + " " + TimeFormatter.timeAgoFormatter(clockMock.now(), post.timestamp);
    }

}
