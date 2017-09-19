package org.craftycoder.socialkata;

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

    @Test
    public void read_commands_from_the_console() {

        when(consoleMock.read()).thenReturn("exit");

        new SocialClient(consoleMock);

        verify(consoleMock,times(1)).read();

    }

    @Test
    public void stop_reading_commands_when_exit() {

        when(consoleMock.read())
                .thenReturn("Alice")
                .thenReturn("exit")
                .thenThrow(new RuntimeException("Not Expected call"));

        new SocialClient(consoleMock);

        verify(consoleMock, times(2)).read();

    }


}