package org.craftycoder.socialkata.infrastructure;

import org.craftycoder.socialkata.domain.model.Follows;
import org.craftycoder.socialkata.domain.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryFollows implements Follows {

    private final Map<User, Set<User>> follows = new HashMap<>();

    @Override
    public void addFollow(User follower, User followed) {

        Set<User> followedUsers = followedBy(follower);

        followedUsers.add(followed);

        follows.put(follower, followedUsers);

    }

    public Set<User> followedBy(User user) {
        return follows
                .getOrDefault(user, new HashSet<>());
    }
}
