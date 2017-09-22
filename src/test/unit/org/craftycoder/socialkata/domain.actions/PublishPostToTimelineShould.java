package org.craftycoder.socialkata.actions;

import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.craftycoder.socialkata.domain.model.Post;
import org.craftycoder.socialkata.domain.model.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PublishPostToTimelineShould {

    @Mock
    private PostRepository postRepositoryMock;

    @Test
    public void persist_post_when_a_post_is_publish() {

        PublishPostToTimeline publishPostToTimeline = new PublishPostToTimeline(postRepositoryMock);

        publishPostToTimeline.publishPost("Alice","I love the weather today");

        verify(postRepositoryMock, times(1)).save(new Post("Alice", "I love the weather today"));


    }


}