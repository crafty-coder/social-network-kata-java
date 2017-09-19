import org.craftycoder.socialkata.Console;
import org.craftycoder.socialkata.SocialClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class PostingAndReading {

    @Mock
    private Console consoleMock;

    @Test
    public void should_be_able_to_post_and_then_read_the_wall_with_that_post() {

        Mockito.when(consoleMock.read()).thenReturn("Alice -> I love the weather today");
        Mockito.when(consoleMock.read()).thenReturn("Alice");

        new SocialClient(consoleMock);

        Mockito.verify(consoleMock,times(1)).println("I love the weather today (less than one second ago)");
    }


}