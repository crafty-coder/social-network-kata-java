package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.util.TimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class ViewTimeline {

    private final Posts postsMock;
    private final Clock clockMock;

    public ViewTimeline(Posts postsMock, Clock clockMock) {

        this.postsMock = postsMock;
        this.clockMock = clockMock;
    }

    public List<String> view(String user) {

        return postsMock.filterByUserReverseSorting(user).stream()
                .map(this::formatPost)
                .collect(Collectors.toList());
    }

    private String formatPost(Post post) {
        return post.message + " " + TimeFormatter.timeAgoFormatter(clockMock.now(), post.timestamp);
    }

}
