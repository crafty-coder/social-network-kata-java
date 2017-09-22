package org.craftycoder.socialkata.domain.actions;


import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.PostRepository;

public class PublishPostToTimeline {

    private final PostRepository postRepository;

    public PublishPostToTimeline(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void publishPost(String user, String text) {
        postRepository.save(new Post(user,text));
    }



}
