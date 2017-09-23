package org.craftycoder.socialkata.domain.model;

import java.util.List;

public interface Timeline {

    void save(Post post);

    List<Post> filterByUserReverseSorting(String user);
}
