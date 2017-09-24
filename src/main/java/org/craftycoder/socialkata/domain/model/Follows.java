package org.craftycoder.socialkata.domain.model;

import java.util.Set;

public interface Follows {
    void addFollow(User follower, User followed);

    Set<User> followedBy(User user);
}
