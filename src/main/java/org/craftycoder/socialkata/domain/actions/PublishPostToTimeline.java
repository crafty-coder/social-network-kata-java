package org.craftycoder.socialkata.domain.actions;


import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.PostRepository;
import org.craftycoder.socialkata.domain.ports.Clock;

public class PublishPostToTimeline {

    private final PostRepository postRepository;
    private final Clock clock;

    public PublishPostToTimeline(PostRepository postRepository, Clock clock) {
        this.postRepository = postRepository;
        this.clock = clock;
    }

    public void publishPost(String user, String text) {
        postRepository.save(new Post(user, text, clock.now()));
    }

}
