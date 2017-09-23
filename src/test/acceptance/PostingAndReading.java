import org.craftycoder.socialkata.delivery.Console;
import org.craftycoder.socialkata.delivery.SocialClient;
import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.craftycoder.socialkata.domain.actions.ViewUserTimeline;
import org.craftycoder.socialkata.domain.model.PostRepository;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.infrastructure.InMemoryPostRepository;
import org.craftycoder.socialkata.infrastructure.SystemClock;
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

        Mockito.when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("Alice")
                .thenReturn("exit");

        PostRepository postRepository = new InMemoryPostRepository();
        Clock clock = new SystemClock();

        PublishPostToTimeline publishPostToTimeline = new PublishPostToTimeline(
                postRepository,
                clock
        );

        ViewUserTimeline viewUserTimeline = new ViewUserTimeline(
                postRepository,
                clock
        );


        SocialClient sc = new SocialClient(consoleMock, publishPostToTimeline, viewUserTimeline);
        sc.start();

        Mockito.verify(consoleMock, times(3)).read();
        Mockito.verify(consoleMock, times(1)).println("I love the weather today (an instant ago)");
    }


}
