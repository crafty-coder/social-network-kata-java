package org.craftycoder.socialkata.domain.actions;


import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.model.User;
import org.craftycoder.socialkata.domain.ports.Clock;

public class PublishPost {

    private final Posts posts;
    private final Clock clock;

    public PublishPost(final Posts posts, final Clock clock) {
        this.posts = posts;
        this.clock = clock;
    }

    public void publishPost(final String user, final String text) {
        posts.save(new Post(new User(user), text, clock.now()));
    }

}
