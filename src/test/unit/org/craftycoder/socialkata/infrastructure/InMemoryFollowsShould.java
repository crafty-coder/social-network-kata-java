package org.craftycoder.socialkata.infrastructure;

import org.craftycoder.socialkata.domain.model.Follows;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class InMemoryFollowsShould {

    @Test
    public void return_empty_when_user_follows_no_one() {

        Follows follows = new InMemoryFollows();

        Set<String> result = follows.followedBy("Bob");

        assertEquals(Collections.emptySet(), result);

    }

    @Test
    public void return_one_user_when_user_follows_just_another_user() {

        Follows follows = new InMemoryFollows();

        follows.addFollow("Bob", "Alice");

        Set<String> result = follows.followedBy("Bob");

        assertEquals(Collections.singleton("Alice"), result);

    }

    @Test
    public void return_two_users_when_user_follows_two_users() {

        Follows follows = new InMemoryFollows();

        follows.addFollow("Bob", "Alice");
        follows.addFollow("Bob", "Charlie");

        Set<String> result = follows.followedBy("Bob");

        Set<String> expectedFollowed = new HashSet<String>() {{
            add("Alice");
            add("Charlie");
        }};

        assertEquals(expectedFollowed, result);

    }


}