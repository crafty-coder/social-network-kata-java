package org.craftycoder.socialkata.domain.service;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TimelineServiceShould {

    @Mock
    private Posts postsMock;

    private Long ONE_MINUTE_BEFORE = 1506167080_000L;
    private Long TWO_MINUTES_BEFORE = 1506167015_000L;

    private Post BOB_POST_1 = new Post(new User("Bob"), "Good game though.", ONE_MINUTE_BEFORE);
    private Post BOB_POST_2 = new Post(new User("Bob"), "Damn! We lost!", TWO_MINUTES_BEFORE);

    @Test
    public void should_retrieve_user_timeline() {

        when(postsMock.filterByUserReverseSorting(new User("Bob")))
                .thenReturn(Arrays.asList(BOB_POST_1, BOB_POST_2));


        TimelineService timelineService = new TimelineService(postsMock);

        Timeline result = timelineService.getTimeline(new User("Bob"));
        Timeline expectedResult = new Timeline(Arrays.asList(
                BOB_POST_1,
                BOB_POST_2
        ));

        assertEquals(expectedResult, result);

    }
}