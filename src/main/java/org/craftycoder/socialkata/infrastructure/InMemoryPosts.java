package org.craftycoder.socialkata.infrastructure;


import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Posts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPosts implements Posts {

    private final List<Post> posts = new ArrayList<>();


    @Override
    public void save(Post post) {
        posts.add(post);
    }

    @Override
    public List<Post> filterByUserReverseSorting(String user) {

        List<Post> userPosts = posts.stream()
                .filter(p -> p.user.equals(user))
                .collect(Collectors.toList());
        Collections.reverse(userPosts);

        return userPosts;
    }
}
