package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.PostRepository;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PublishPostToTimelineShould {

    @Mock
    private PostRepository postRepositoryMock;
    @Mock
    private Clock clockMock;

    @Test
    public void persist_post_when_a_post_is_publish() {

        PublishPostToTimeline publishPostToTimeline = new PublishPostToTimeline(postRepositoryMock, clockMock);
        Long NOW = 15061671450000L;
        when(clockMock.now()).thenReturn(NOW);

        publishPostToTimeline.publishPost("Alice", "I love the weather today");

        verify(postRepositoryMock, times(1)).save(new Post("Alice", "I love the weather today", NOW));
    }


}