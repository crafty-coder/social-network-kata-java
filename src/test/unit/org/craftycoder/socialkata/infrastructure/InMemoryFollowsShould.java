package org.craftycoder.socialkata.infrastructure;

import org.craftycoder.socialkata.domain.model.Follows;
import org.craftycoder.socialkata.domain.model.User;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class InMemoryFollowsShould {

    @Test
    public void return_empty_when_user_follows_no_one() {

        Follows follows = new InMemoryFollows();

        Set<User> result = follows.followedBy(new User("Bob"));

        assertEquals(Collections.emptySet(), result);

    }

    @Test
    public void return_one_user_when_user_follows_just_another_user() {

        Follows follows = new InMemoryFollows();

        follows.addFollow(new User("Bob"), new User("Alice"));

        Set<User> result = follows.followedBy(new User("Bob"));

        assertEquals(Collections.singleton(new User("Alice")), result);

    }

    @Test
    public void return_two_users_when_user_follows_two_users() {

        Follows follows = new InMemoryFollows();

        follows.addFollow(new User("Bob"), new User("Alice"));
        follows.addFollow(new User("Bob"), new User("Charlie"));

        Set<User> result = follows.followedBy(new User("Bob"));

        Set<User> expectedFollowed = new HashSet<User>() {{
            add(new User("Alice"));
            add(new User("Charlie"));
        }};

        assertEquals(expectedFollowed, result);

    }


}