package org.craftycoder.socialkata.domain.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class WallShould {

    private Long NOW = 1506167145_000L;
    private Long ONE_MINUTE_BEFORE = 1506167080_000L;
    private Long TWO_MINUTES_BEFORE = 1506167015_000L;

    private Post ALICE_POST = new Post("Alice", "I love the weather today", NOW);
    private Post BOB_POST_1 = new Post("Bob", "Good game though.", ONE_MINUTE_BEFORE);
    private Post BOB_POST_2 = new Post("Bob", "Damn! We lost!", TWO_MINUTES_BEFORE);

    private Timeline BOB_Timeline = new Timeline(Arrays.asList(BOB_POST_1, BOB_POST_2));

    private Timeline Alice_Timeline = new Timeline(Collections.singletonList(ALICE_POST));
    private Wall Alice_Wall = new Wall(Arrays.asList(BOB_Timeline, Alice_Timeline));


    private Wall EMPTY_WALL = new Wall(Collections.singletonList(new Timeline(Collections.emptyList())));

    @Test
    public void return_aggregated_post_of_empty_wall() {
        assertEquals(
                Collections.emptyList(),
                EMPTY_WALL.aggregatedPost()
        );
    }

    @Test
    public void return_aggregated_post() {

        assertEquals(
                Arrays.asList(ALICE_POST, BOB_POST_1, BOB_POST_2),
                Alice_Wall.aggregatedPost()
        );


    }

}