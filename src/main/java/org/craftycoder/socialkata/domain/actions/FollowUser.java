package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Follows;
import org.craftycoder.socialkata.domain.model.User;

public class FollowUser {

    private final Follows follows;

    public FollowUser(final Follows follows) {

        this.follows = follows;
    }

    public void follow(String follower, String followed) {
        follows.addFollow(new User(follower), new User(followed));
    }
}
