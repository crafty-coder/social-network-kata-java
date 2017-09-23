package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.PostRepository;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.util.TimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class ViewUserTimeline {

    private final PostRepository postRepositoryMock;
    private final Clock clockMock;

    public ViewUserTimeline(PostRepository postRepositoryMock, Clock clockMock) {

        this.postRepositoryMock = postRepositoryMock;
        this.clockMock = clockMock;
    }

    public List<String> view(String user) {

        return postRepositoryMock.findByUserReverseSorting(user).stream()
                .map(this::formatPost)
                .collect(Collectors.toList());
    }

    private String formatPost(Post post) {
        return post.message + " " + TimeFormatter.timeAgoFormatter(clockMock.now(), post.timestamp);
    }

}
