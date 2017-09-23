package org.craftycoder.socialkata.delivery;

import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.craftycoder.socialkata.domain.actions.ViewUserTimeline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SocialClientShould {

    @Mock
    private Console consoleMock;

    @Mock
    private PublishPostToTimeline publishPostToTimelineMock;

    @Mock
    private ViewUserTimeline viewUserTimelineMock;

    @Test
    public void stop_reading_commands_when_exit() {

        when(consoleMock.read())
                .thenReturn("exit")
                .thenThrow(new RuntimeException("Not Expected call"));

        new SocialClient(consoleMock, publishPostToTimelineMock, viewUserTimelineMock).start();

        verify(consoleMock, times(1)).read();

    }

    @Test
    public void send_publish_commands_to_publish_to_timeline() {

        when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("exit");

        new SocialClient(consoleMock, publishPostToTimelineMock, viewUserTimelineMock).start();

        verify(publishPostToTimelineMock, times(1)).publishPost("Alice", "I love the weather today");

    }

    @Test
    public void send_two_publish_commands_to_publish_to_timeline() {

        when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("Bob -> Damn! We lost!")
                .thenReturn("exit");

        new SocialClient(consoleMock, publishPostToTimelineMock, viewUserTimelineMock).start();

        verify(publishPostToTimelineMock, times(1)).publishPost("Alice", "I love the weather today");
        verify(publishPostToTimelineMock, times(1)).publishPost("Bob", "Damn! We lost!");

    }

    @Test
    public void recover_and_print_user_timeline() {

        when(consoleMock.read())
                .thenReturn("Alice")
                .thenReturn("exit");


        when(viewUserTimelineMock.view("Alice"))
                .thenReturn(Collections.singletonList("I Love the weather today (5 minutes ago)"));

        new SocialClient(consoleMock, publishPostToTimelineMock, viewUserTimelineMock).start();

        verify(consoleMock, times(1)).println("I Love the weather today (5 minutes ago)");

    }


}