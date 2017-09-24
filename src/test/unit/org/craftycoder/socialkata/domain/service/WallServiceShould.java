package org.craftycoder.socialkata.domain.service;

import org.craftycoder.socialkata.domain.model.Follows;
import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.model.Wall;
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

    private Post ALICE_POST = new Post("Alice", "I love the weather today", NOW);
    private Post BOB_POST_1 = new Post("Bob", "Good game though.", ONE_MINUTE_BEFORE);
    private Post BOB_POST_2 = new Post("Bob", "Damn! We lost!", TWO_MINUTES_BEFORE);

    private Timeline Charlie_Timeline = new Timeline(Collections.emptyList());
    private Wall Charlie_Wall = new Wall(Collections.singletonList(Charlie_Timeline));

    private Timeline BOB_Timeline = new Timeline(Arrays.asList(BOB_POST_1, BOB_POST_2));
    private Wall BOB_Wall = new Wall(Collections.singletonList(BOB_Timeline));

    private Timeline Alice_Timeline = new Timeline(Collections.singletonList(ALICE_POST));
    private Wall Alice_Wall = new Wall(Arrays.asList(BOB_Timeline,Alice_Timeline));


    @Test
    public void recover_a_empty_wall() {

        when(timelineServiceMock.getTimeline("Charlie")).thenReturn(Charlie_Timeline);

        WallService wallService = new WallService(timelineServiceMock, follows);

        Wall result = wallService.getWall("Charlie");

        Assert.assertEquals(Charlie_Wall, result);

    }

    @Test
    public void recover_a_wall_of_a_user_who_follows_no_one() {

        when(timelineServiceMock.getTimeline("Bob")).thenReturn(BOB_Timeline);

        WallService wallService = new WallService(timelineServiceMock, follows);

        Wall result = wallService.getWall("Bob");

        Assert.assertEquals(BOB_Wall, result);

    }

    @Test
    public void recover_a_wall_user_who_follows_someone() {

        Set<String> followedByAlice = new HashSet<String>() {{
            add("Bob");
        }};

        when(follows.followedBy("Alice")).thenReturn(followedByAlice);

        when(timelineServiceMock.getTimeline("Bob")).thenReturn(BOB_Timeline);
        when(timelineServiceMock.getTimeline("Alice")).thenReturn(Alice_Timeline);


        WallService wallService = new WallService(timelineServiceMock, follows);

        Wall result = wallService.getWall("Alice");

        Assert.assertEquals(Alice_Wall, result);

    }




}