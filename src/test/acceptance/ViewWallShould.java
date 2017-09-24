import org.craftycoder.socialkata.delivery.Console;
import org.craftycoder.socialkata.delivery.SocialClient;
import org.craftycoder.socialkata.domain.actions.FollowUser;
import org.craftycoder.socialkata.domain.actions.PublishPost;
import org.craftycoder.socialkata.domain.actions.ViewTimeline;
import org.craftycoder.socialkata.domain.actions.ViewWall;
import org.craftycoder.socialkata.domain.model.Follows;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.infrastructure.InMemoryFollows;
import org.craftycoder.socialkata.infrastructure.InMemoryPosts;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ViewWallShould {

    @Mock
    private Console consoleMock;

    @Mock
    private Clock clockMock;


    @Ignore("Work in porgress")
    @Test
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


        Posts posts = new InMemoryPosts();
        Follows follows = new InMemoryFollows();

        PublishPost publishPost = new PublishPost(
                posts,
                clockMock
        );

        ViewTimeline viewTimeline = new ViewTimeline(
                posts,
                clockMock
        );

        FollowUser followUser = new FollowUser(follows);
        ViewWall viewWall = new ViewWall(posts,clockMock);

        SocialClient sc = new SocialClient(consoleMock, publishPost, viewTimeline, followUser, viewWall);


        sc.start();

        verify(consoleMock, times(3)).read();
        verify(consoleMock, times(1)).println("Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)");
        verify(consoleMock, times(1)).println("Alice - I love the weather today (5 minutes ago)");


    }
}
