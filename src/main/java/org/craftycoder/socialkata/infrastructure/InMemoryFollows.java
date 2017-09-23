package org.craftycoder.socialkata.infrastructure;

import org.craftycoder.socialkata.domain.model.Follows;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryFollows implements Follows {

    private final Map<String, Set<String>> follows = new HashMap<>();

    @Override
    public void addFollow(String follower, String followed) {

        Set<String> followedUsers = followedBy(follower);

        followedUsers.add(followed);

        follows.put(follower, followedUsers);

    }

    public Set<String> followedBy(String user) {
        return follows
                .getOrDefault(user, new HashSet<>());
    }
}
