package org.craftycoder.socialkata;

import org.craftycoder.socialkata.actions.PublishToTimeline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SocialClientShould {

    @Mock
    private Console consoleMock;

    @Mock
    private PublishToTimeline publishToTimelineMock;

    @Test
    public void stop_reading_commands_when_exit() {

        when(consoleMock.read())
                .thenReturn("exit")
                .thenThrow(new RuntimeException("Not Expected call"));

        new SocialClient(consoleMock);

        verify(consoleMock, times(1)).read();

    }
    @Test
    public void send_publish_commands_to_publish_to_timeline() {

        when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("exit");

        new SocialClient(consoleMock);

        verify(publishToTimelineMock,times(1)).publish("Alice","I love the weather today");

    }



}