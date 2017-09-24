package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.model.User;
import org.craftycoder.socialkata.domain.model.Wall;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.service.WallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ViewWallShould {

    @Mock
    private WallService wallServiceMock;

    @Mock
    private Clock clockMock;

    private Long NOW = 1506167145_000L;
    private Long ONE_MINUTE_BEFORE = 1506167080_000L;
    private Long TWO_MINUTES_BEFORE = 1506167015_000L;

    private Post ALICE_POST = new Post(new User("Alice"), "I love the weather today", NOW);
    private Post BOB_POST_1 = new Post(new User("Bob"), "Good game though.", ONE_MINUTE_BEFORE);
    private Post BOB_POST_2 = new Post(new User("Bob"), "Damn! We lost!", TWO_MINUTES_BEFORE);

    private Timeline BOB_Timeline = new Timeline(Arrays.asList(BOB_POST_1, BOB_POST_2));

    private Timeline Alice_Timeline = new Timeline(Collections.singletonList(ALICE_POST));
    private Wall Alice_Wall = new Wall(Arrays.asList(BOB_Timeline, Alice_Timeline));

    private Wall EMPTY_WALL = new Wall(Collections.singletonList(new Timeline(Collections.emptyList())));


    @Test
    public void recover_an_empty_wall() {

        when(wallServiceMock.getWall(new User("Charlie")))
                .thenReturn(EMPTY_WALL);

        ViewWall viewWall = new ViewWall(wallServiceMock, clockMock);

        List<String> result = viewWall.view("Charlie");

        assertEquals(Collections.emptyList(), result);

    }

    @Test
    public void recover_a_wall_of_a_user_who_follows_no_one_with_two_posts() {

        when(wallServiceMock.getWall(new User("Alice")))
                .thenReturn(Alice_Wall);

        when(clockMock.now())
                .thenReturn(NOW);

        ViewWall viewWall = new ViewWall(wallServiceMock, clockMock);

        List<String> result = viewWall.view("Alice");

        assertEquals(Arrays.asList(
                "Alice - I love the weather today (an instant ago)",
                "Bob - Good game though. (1 minute ago)",
                "Bob - Damn! We lost! (2 minutes ago)"
        ), result);

    }


}