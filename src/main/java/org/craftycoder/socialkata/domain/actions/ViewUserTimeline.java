package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.PostRepository;
import org.craftycoder.socialkata.domain.ports.Clock;

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

        return postRepositoryMock.findByUser(user).stream()
                .map(post -> post.message + " (15 seconds ago)")
                .collect(Collectors.toList());
    }
}
