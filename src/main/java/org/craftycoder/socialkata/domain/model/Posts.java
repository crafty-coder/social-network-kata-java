package org.craftycoder.socialkata.domain.model;

import java.util.List;

public interface Posts {

    void save(final Post post);

    List<Post> filterByUserReverseSorting(final User user);
}
