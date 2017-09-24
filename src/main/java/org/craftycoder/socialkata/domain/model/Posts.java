package org.craftycoder.socialkata.domain.model;

import java.util.List;

public interface Posts {

    void save(Post post);

    List<Post> filterByUserReverseSorting(User user);
}
