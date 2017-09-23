import org.craftycoder.socialkata.delivery.Console;
import org.craftycoder.socialkata.delivery.SocialClient;
import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.craftycoder.socialkata.domain.actions.ViewUserTimeline;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.infrastructure.InMemoryTimeline;
import org.craftycoder.socialkata.infrastructure.SystemClock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PublishAndViewUserTimeline {

    @Mock
    private Console consoleMock;


    @Test
    public void should_publish_a_post_and_then_view_timeline_with_that_post() {

        Mockito.when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("Alice")
                .thenReturn("exit");

        Timeline timeline = new InMemoryTimeline();
        Clock clock = new SystemClock();

        PublishPostToTimeline publishPostToTimeline = new PublishPostToTimeline(
                timeline,
                clock
        );

        ViewUserTimeline viewUserTimeline = new ViewUserTimeline(
                timeline,
                clock
        );


        SocialClient sc = new SocialClient(consoleMock, publishPostToTimeline, viewUserTimeline);


        sc.start();

        verify(consoleMock, times(3)).read();
        verify(consoleMock, times(1)).println("I love the weather today (an instant ago)");
    }

    @Test
    public void should_view_userTimeline_in_reverse_order() {
        Mockito.when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("Alice -> I love London's weather!")
                .thenReturn("Alice")
                .thenReturn("exit");

        Timeline timeline = new InMemoryTimeline();
        Clock clock = new SystemClock();

        PublishPostToTimeline publishPostToTimeline = new PublishPostToTimeline(
                timeline,
                clock
        );

        ViewUserTimeline viewUserTimeline = new ViewUserTimeline(
                timeline,
                clock
        );


        SocialClient sc = new SocialClient(consoleMock, publishPostToTimeline, viewUserTimeline);


        sc.start();
        InOrder mInOrder = inOrder(consoleMock);

        verify(consoleMock, times(4)).read();
        mInOrder.verify(consoleMock, times(1)).println("I love London's weather! (an instant ago)");
        mInOrder.verify(consoleMock, times(1)).println("I love the weather today (an instant ago)");
    }

}
