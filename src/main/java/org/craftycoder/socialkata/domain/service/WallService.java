package org.craftycoder.socialkata.domain.service;

import org.craftycoder.socialkata.domain.model.Follows;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.model.User;
import org.craftycoder.socialkata.domain.model.Wall;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WallService {

    private final TimelineService timelineService;
    private final Follows follows;

    public WallService(final TimelineService timelineService, final Follows follows) {

        this.timelineService = timelineService;
        this.follows = follows;
    }

    public Wall getWall(final User user) {

        Set<User> users = follows.followedBy(user);
        users.add(user);

        List<Timeline> timelines = users.stream()
                .map(timelineService::getTimeline)
                .collect(Collectors.toList());

        return new Wall(timelines);
    }
}
