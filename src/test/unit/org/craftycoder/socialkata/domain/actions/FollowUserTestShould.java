package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Follows;
import org.craftycoder.socialkata.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FollowUserTestShould {

    @Mock
    private Follows followsMock;

    @Test
    public void persist_a_follow() {

        String follower = "Charlie";
        String followed = "Alice";

        FollowUser followUser = new FollowUser(followsMock);
        followUser.follow(follower, followed);

        verify(followsMock, times(1)).addFollow(new User(follower), new User(followed));
    }

}