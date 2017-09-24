package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.service.TimelineService;
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
    private TimelineService timelineServiceMock;
    @Mock
    private Clock clockMock;

    private Long NOW = 1506167145_000L;
    private Long ONE_MINUTE_BEFORE = 1506167080_000L;
    private Long TWO_MINUTES_BEFORE = 1506167015_000L;

    private Post ALICE_POST = new Post("Alice", "I love the weather today", System.currentTimeMillis());
    private Post BOB_POST_1 = new Post("Bob", "Good game though.", ONE_MINUTE_BEFORE);
    private Post BOB_POST_2 = new Post("Bob", "Damn! We lost!", TWO_MINUTES_BEFORE);


    @Test
    public void recover_an_empty_wall() {

        when(timelineServiceMock.getTimeline("Bob"))
                .thenReturn(new Timeline(Collections.emptyList()));

        ViewWall viewWall = new ViewWall(timelineServiceMock, clockMock);

        List<String> result = viewWall.view("Bob");

        assertEquals(Collections.emptyList(), result);

    }

    @Test
    public void recover_a_wall_of_a_user_who_follows_no_one_with_two_posts() {

        when(timelineServiceMock.getTimeline("Bob"))
                .thenReturn(new Timeline(Arrays.asList(BOB_POST_1, BOB_POST_2)));

        when(clockMock.now())
                .thenReturn(NOW);

        ViewWall viewWall = new ViewWall(timelineServiceMock, clockMock);

        List<String> result = viewWall.view("Bob");

        assertEquals(Arrays.asList(
                "Bob - Good game though. (1 minute ago)",
                "Bob - Damn! We lost! (2 minutes ago)"
        ), result);

    }


}