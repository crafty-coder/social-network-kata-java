import org.craftycoder.socialkata.delivery.Console;
import org.craftycoder.socialkata.delivery.SocialClient;
import org.craftycoder.socialkata.domain.actions.FollowUser;
import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.craftycoder.socialkata.domain.actions.ViewUserTimeline;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.infrastructure.InMemoryTimeline;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ViewWall {

    @Mock
    private Console consoleMock;

    @Mock
    private Clock clockMock;


    @Ignore("Work in porgress") @Test
    public void should_view_a_wall_aggregating_followings_timeline() {


        Mockito.when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("Charlie -> I'm in New York today! Anyone wants to have a coffee?")
                .thenReturn("Charlie follows Alice")
                .thenReturn("Charlie wall")
                .thenReturn("exit");

        Mockito.when(clockMock.now())
                .thenReturn(0L)
                .thenReturn(298_000L)
                .thenReturn(300_000L);



        Timeline timeline = new InMemoryTimeline();

        PublishPostToTimeline publishPostToTimeline = new PublishPostToTimeline(
                timeline,
                clockMock
        );

        ViewUserTimeline viewUserTimeline = new ViewUserTimeline(
                timeline,
                clockMock
        );

        FollowUser followUser = new FollowUser();


        SocialClient sc = new SocialClient(consoleMock, publishPostToTimeline, viewUserTimeline, followUser);


        sc.start();

        verify(consoleMock, times(3)).read();
        verify(consoleMock, times(1)).println("Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)");
        verify(consoleMock, times(1)).println("Alice - I love the weather today (5 minutes ago)");


    }
}
