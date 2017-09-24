package org.craftycoder.socialkata.domain.service;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.model.User;

import java.util.List;

public class TimelineService {

    private final Posts posts;

    public TimelineService(Posts posts) {

        this.posts = posts;
    }

    public Timeline getTimeline(User user) {

        List<Post> userPosts = posts.filterByUserReverseSorting(user);
        return new Timeline(userPosts);
    }
}
