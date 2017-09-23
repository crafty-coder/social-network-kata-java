package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Follows;

public class FollowUser {

    private final Follows follows;

    public FollowUser(final Follows follows) {

        this.follows = follows;
    }

    public void follow(String follower, String followed) {
        follows.addFollow(follower, followed);
    }
}
