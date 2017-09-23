package org.craftycoder.socialkata.infrastructure;

import org.craftycoder.socialkata.domain.model.Post;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InMemoryPostsShould {

    private Post ALICE_POST = new Post("Alice", "I love the weather today", System.currentTimeMillis());
    private Post BOB_POST_1 = new Post("Bob", "Good game though", System.currentTimeMillis());
    private Post BOB_POST_2 = new Post("Bob", "Damn! We lost!", System.currentTimeMillis());


    @Test
    public void retrieve_nothing_if_no_post_added() {
        InMemoryPosts inMemoryPostRepository = new InMemoryPosts();

        List result = inMemoryPostRepository.filterByUserReverseSorting("Alice");

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void retrieve_alice_post_when_there_are_only_alice_posts() {
        InMemoryPosts inMemoryPostRepository = new InMemoryPosts();

        inMemoryPostRepository.save(ALICE_POST);

        List result = inMemoryPostRepository.filterByUserReverseSorting("Alice");

        assertEquals(Collections.singletonList(ALICE_POST), result);
    }

    @Test
    public void retrieve_only_alice_post_when_there_are_post_of_different_users() {
        InMemoryPosts inMemoryPostRepository = new InMemoryPosts();

        inMemoryPostRepository.save(ALICE_POST);
        inMemoryPostRepository.save(BOB_POST_1);
        inMemoryPostRepository.save(BOB_POST_2);

        List result = inMemoryPostRepository.filterByUserReverseSorting("Alice");

        assertEquals(Collections.singletonList(ALICE_POST), result);
    }

    @Test
    public void retrieve_user_post_in_reverse_order() {

        InMemoryPosts inMemoryPostRepository = new InMemoryPosts();

        inMemoryPostRepository.save(BOB_POST_1);
        inMemoryPostRepository.save(BOB_POST_2);

        List result = inMemoryPostRepository.filterByUserReverseSorting("Bob");

        assertEquals(Arrays.asList(BOB_POST_2, BOB_POST_1), result);

    }


}