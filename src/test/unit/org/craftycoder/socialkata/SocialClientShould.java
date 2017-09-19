package org.craftycoder.socialkata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SocialClientShould {

    @Mock
    private Console consoleMock;

    @Test
    public void read_commands_from_the_console(){

        new SocialClient(consoleMock);

        Mockito.verify(consoleMock).read();

    }

}