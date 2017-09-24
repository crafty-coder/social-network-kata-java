package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.service.TimelineService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ViewTimelineShould {

    @Mock
    private TimelineService timelineServiceMock;
    @Mock
    private Clock clockMock;


    @Test
    public void recover_the_timeline_of_a_user_without_post() {

        ViewTimeline viewTimeline = new ViewTimeline(timelineServiceMock, clockMock);

        when(timelineServiceMock.getTimeline("Alice"))
                .thenReturn(new Timeline(Collections.emptyList()));

        List<String> result = viewTimeline.view("Alice");
        List<String> expectedResult = Collections.emptyList();

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void recover_the_timeline_of_a_user_with_one_post() {

        ViewTimeline viewTimeline = new ViewTimeline(timelineServiceMock, clockMock);
        Long NOW = 1506167145_000L;
        Long FEW_SECONDS_BEFORE = 1506167130_000L;

        when(clockMock.now())
                .thenReturn(NOW);
        when(timelineServiceMock.getTimeline("Alice"))
                .thenReturn(new Timeline(Collections.singletonList(
                        new Post("Alice", "I love the weather today", FEW_SECONDS_BEFORE))
                ));


        List<String> result = viewTimeline.view("Alice");
        List<String> expectedResult = Collections.singletonList("I love the weather today (15 seconds ago)");


        Assert.assertEquals(expectedResult, result);

    }

    @Test
    public void recover_the_timeline_of_a_user_with_two_post() {

        ViewTimeline viewTimeline = new ViewTimeline(timelineServiceMock, clockMock);
        Long NOW = 1506167145_000L;
        Long ONE_MINUTE_BEFORE = 1506167080_000L;
        Long TWO_MINUTES_BEFORE = 1506167015_000L;

        when(clockMock.now())
                .thenReturn(NOW);
        when(timelineServiceMock.getTimeline("Bob"))
                .thenReturn(new Timeline(
                        Arrays.asList(
                                new Post("Bob", "Good game though.", ONE_MINUTE_BEFORE),
                                new Post("Bob", "Damn! We lost!", TWO_MINUTES_BEFORE)
                        )));


        List<String> result = viewTimeline.view("Bob");
        List<String> expectedResult = Arrays.asList(
                "Good game though. (1 minute ago)",
                "Damn! We lost! (2 minutes ago)"
        );


        Assert.assertEquals(expectedResult, result);

    }


}