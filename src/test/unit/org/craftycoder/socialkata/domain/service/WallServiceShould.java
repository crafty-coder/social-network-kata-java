package org.craftycoder.socialkata.domain.service;

import org.craftycoder.socialkata.domain.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallServiceShould {

    @Mock
    private TimelineService timelineServiceMock;

    @Mock
    private Follows follows;

    private Long NOW = 1506167145_000L;
    private Long ONE_MINUTE_BEFORE = 1506167080_000L;
    private Long TWO_MINUTES_BEFORE = 1506167015_000L;

    private Post ALICE_POST = new Post(new User("Alice"), "I love the weather today", NOW);
    private Post BOB_POST_1 = new Post(new User("Bob"), "Good game though.", ONE_MINUTE_BEFORE);
    private Post BOB_POST_2 = new Post(new User("Bob"), "Damn! We lost!", TWO_MINUTES_BEFORE);

    private Timeline Charlie_Timeline = new Timeline(Collections.emptyList());
    private Wall Charlie_Wall = new Wall(Collections.singletonList(Charlie_Timeline));

    private Timeline BOB_Timeline = new Timeline(Arrays.asList(BOB_POST_1, BOB_POST_2));
    private Wall BOB_Wall = new Wall(Collections.singletonList(BOB_Timeline));

    private Timeline Alice_Timeline = new Timeline(Collections.singletonList(ALICE_POST));
    private Wall Alice_Wall = new Wall(Arrays.asList(BOB_Timeline, Alice_Timeline));


    @Test
    public void recover_a_empty_wall() {

        when(timelineServiceMock.getTimeline(new User("Charlie"))).thenReturn(Charlie_Timeline);

        WallService wallService = new WallService(timelineServiceMock, follows);

        Wall result = wallService.getWall(new User("Charlie"));

        Assert.assertEquals(Charlie_Wall, result);

    }

    @Test
    public void recover_a_wall_of_a_user_who_follows_no_one() {

        when(timelineServiceMock.getTimeline(new User("Bob"))).thenReturn(BOB_Timeline);

        WallService wallService = new WallService(timelineServiceMock, follows);

        Wall result = wallService.getWall(new User("Bob"));

        Assert.assertEquals(BOB_Wall, result);

    }

    @Test
    public void recover_a_wall_user_who_follows_someone() {

        Set<User> followedByAlice = new HashSet<User>() {{
            add(new User("Bob"));
        }};

        when(follows.followedBy(new User("Alice"))).thenReturn(followedByAlice);

        when(timelineServiceMock.getTimeline(new User("Bob"))).thenReturn(BOB_Timeline);
        when(timelineServiceMock.getTimeline(new User("Alice"))).thenReturn(Alice_Timeline);


        WallService wallService = new WallService(timelineServiceMock, follows);

        Wall result = wallService.getWall(new User("Alice"));

        Assert.assertEquals(Alice_Wall, result);

    }


}