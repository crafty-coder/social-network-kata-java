package org.craftycoder.socialkata.domain.actions;


import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.ports.Clock;

public class PublishPost {

    private final Posts posts;
    private final Clock clock;

    public PublishPost(Posts posts, Clock clock) {
        this.posts = posts;
        this.clock = clock;
    }

    public void publishPost(String user, String text) {
        posts.save(new Post(user, text, clock.now()));
    }

}
