package org.craftycoder.socialkata.domain.service;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.model.Timeline;

import java.util.List;

public class TimelineService {

    private final Posts posts;

    public TimelineService(Posts posts) {

        this.posts = posts;
    }

    public Timeline getTimeline(String user) {

        List<Post> userPosts = posts.filterByUserReverseSorting(user);
        return new Timeline(userPosts);
    }
}
