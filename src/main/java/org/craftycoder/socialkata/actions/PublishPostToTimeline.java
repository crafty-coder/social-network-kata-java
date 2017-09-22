package org.craftycoder.socialkata.actions;


import org.craftycoder.socialkata.model.Post;
import org.craftycoder.socialkata.model.PostRepository;

public class PublishPostToTimeline {

    private final PostRepository postRepository;

    public PublishPostToTimeline(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void publishPost(String user, String text) {
        postRepository.save(new Post(user,text));
    }



}
