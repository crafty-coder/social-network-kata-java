package org.craftycoder.socialkata.domain.model;

import java.util.Set;

public interface Follows {
    void addFollow(String follower, String followed);

    Set<String> followedBy(String user);
}
