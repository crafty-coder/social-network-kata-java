package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.service.WallService;
import org.craftycoder.socialkata.domain.util.TimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class ViewWall {


    private final WallService wallService;
    private final Clock clock;

    public ViewWall(final WallService wallService, final Clock clock) {
        this.wallService = wallService;
        this.clock = clock;
    }

    public List<String> view(final String user) {

        return wallService.getWall(user)
                .aggregatedPosts()
                .stream()
                .map(this::formatPost)
                .collect(Collectors.toList());
    }

    private String formatPost(final Post post) {
        return post.user + " - " + post.message + " " + TimeFormatter.timeAgoFormatter(clock.now(), post.timestamp);
    }


}
