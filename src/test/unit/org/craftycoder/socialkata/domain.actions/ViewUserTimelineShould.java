package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.PostRepository;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ViewUserTimelineShould {

    @Mock
    private PostRepository postRepositoryMock;
    @Mock
    private Clock clockMock;


    @Test
    public void recover_the_timeline_of_a_user_without_post() {

        ViewUserTimeline viewUserTimeline = new ViewUserTimeline(postRepositoryMock, clockMock);

        when(postRepositoryMock.findByUser("Alice"))
                .thenReturn(Collections.emptyList());


        List<String> result = viewUserTimeline.view("Alice");
        List<String> expectedResult = Collections.emptyList();


        Assert.assertEquals(expectedResult, result);


    }

    @Test
    public void recover_the_timeline_of_a_user_with_one_post() {

        ViewUserTimeline viewUserTimeline = new ViewUserTimeline(postRepositoryMock, clockMock);
        Long NOW = 1506167145_000L;
        Long FEW_SECONDS_BEFORE = 1506167130_000L;

        when(clockMock.now())
                .thenReturn(NOW);
        when(postRepositoryMock.findByUser("Alice"))
                .thenReturn(Collections.singletonList(new Post("Alice", "I love the weather today", FEW_SECONDS_BEFORE)));


        List<String> result = viewUserTimeline.view("Alice");
        List<String> expectedResult = Collections.singletonList("I love the weather today (15 seconds ago)");


        Assert.assertEquals(expectedResult, result);

    }

}