package org.craftycoder.socialkata.domain.model;

import java.util.Set;

public interface Follows {
    void addFollow(final User follower, final User followed);

    Set<User> followedBy(final User user);
}
