package org.craftycoder.socialkata.domain.model;

import java.util.List;

public interface PostRepository {

    void save(Post post);

    List<Post> findByUser(String user);
}
