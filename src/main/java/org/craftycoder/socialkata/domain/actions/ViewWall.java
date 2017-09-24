package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.util.TimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class ViewWall {


    private final Posts posts;
    private final Clock clock;

    public ViewWall(final Posts posts, final Clock clock) {
        this.posts = posts;
        this.clock = clock;
    }

    public List<String> view(String user) {

        return posts.filterByUserReverseSorting(user).stream()
                .map(this::formatPost)
                .collect(Collectors.toList());
    }

    private String formatPost(Post post) {
        return post.user + " - " + post.message + " " + TimeFormatter.timeAgoFormatter(clock.now(), post.timestamp);
    }


}
