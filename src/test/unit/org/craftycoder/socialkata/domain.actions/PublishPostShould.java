package org.craftycoder.socialkata.domain.actions;

import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PublishPostShould {

    @Mock
    private Posts postsMock;
    @Mock
    private Clock clockMock;

    @Test
    public void persist_post_when_a_post_is_publish() {

        PublishPost publishPost = new PublishPost(postsMock, clockMock);
        Long NOW = 15061671450000L;
        when(clockMock.now()).thenReturn(NOW);

        publishPost.publishPost("Alice", "I love the weather today");

        verify(postsMock, times(1)).save(new Post("Alice", "I love the weather today", NOW));
    }


}