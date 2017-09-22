package org.craftycoder.socialkata;

import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SocialClientShould {

    @Mock
    private Console consoleMock;

    @Mock
    private PublishPostToTimeline publishPostToTimelineMock;

    @Test
    public void stop_reading_commands_when_exit() {

        when(consoleMock.read())
                .thenReturn("exit")
                .thenThrow(new RuntimeException("Not Expected call"));

        new SocialClient(consoleMock, publishPostToTimelineMock).start();

        verify(consoleMock, times(1)).read();

    }

    @Test
    public void send_publish_commands_to_publish_to_timeline() {

        when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("exit");

        new SocialClient(consoleMock, publishPostToTimelineMock).start();

        verify(publishPostToTimelineMock, times(1)).publishPost("Alice", "I love the weather today");

    }

    @Test
    public void send_two_publish_commands_to_publish_to_timeline() {

        when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("Bob -> Damn! We lost!")
                .thenReturn("exit");

        new SocialClient(consoleMock, publishPostToTimelineMock).start();

        verify(publishPostToTimelineMock, times(1)).publishPost("Alice", "I love the weather today");
        verify(publishPostToTimelineMock, times(1)).publishPost("Bob", "Damn! We lost!");

    }


}