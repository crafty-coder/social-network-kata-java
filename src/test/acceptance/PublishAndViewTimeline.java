import org.craftycoder.socialkata.delivery.Console;
import org.craftycoder.socialkata.delivery.SocialClient;
import org.craftycoder.socialkata.domain.actions.FollowUser;
import org.craftycoder.socialkata.domain.actions.PublishPost;
import org.craftycoder.socialkata.domain.actions.ViewTimeline;
import org.craftycoder.socialkata.domain.actions.ViewWall;
import org.craftycoder.socialkata.domain.model.Follows;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.service.TimelineService;
import org.craftycoder.socialkata.domain.service.WallService;
import org.craftycoder.socialkata.infrastructure.InMemoryFollows;
import org.craftycoder.socialkata.infrastructure.InMemoryPosts;
import org.craftycoder.socialkata.infrastructure.SystemClock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PublishAndViewTimeline {

    @Mock
    private Console consoleMock;


    @Test
    public void should_publish_a_post_and_then_view_timeline_with_that_post() {

        Mockito.when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("Alice")
                .thenReturn("exit");

        Posts posts = new InMemoryPosts();
        Follows follows = new InMemoryFollows();
        Clock clock = new SystemClock();
        TimelineService timelineService = new TimelineService(posts);
        WallService wallService = new WallService(timelineService,follows);

        PublishPost publishPost = new PublishPost(
                posts,
                clock
        );

        ViewTimeline viewTimeline = new ViewTimeline(
                timelineService,
                clock
        );

        FollowUser followUser = new FollowUser(follows);

        ViewWall viewWall = new ViewWall(wallService, clock);


        SocialClient sc = new SocialClient(consoleMock, publishPost, viewTimeline, followUser, viewWall);


        sc.start();

        verify(consoleMock, times(3)).read();
        verify(consoleMock, times(1)).println("I love the weather today (an instant ago)");
    }

    @Test
    public void should_view_timeline_in_reverse_order() {
        Mockito.when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("Alice -> I love London's weather!")
                .thenReturn("Alice")
                .thenReturn("exit");

        Posts posts = new InMemoryPosts();
        Follows follows = new InMemoryFollows();
        Clock clock = new SystemClock();
        TimelineService timelineService = new TimelineService(posts);
        WallService wallService = new WallService(timelineService,follows);

        PublishPost publishPost = new PublishPost(
                posts,
                clock
        );

        ViewTimeline viewTimeline = new ViewTimeline(
                timelineService,
                clock
        );

        FollowUser followUser = new FollowUser(follows);
        ViewWall viewWall = new ViewWall(wallService, clock);

        SocialClient sc = new SocialClient(consoleMock, publishPost, viewTimeline, followUser, viewWall);


        sc.start();
        InOrder mInOrder = inOrder(consoleMock);

        verify(consoleMock, times(4)).read();
        mInOrder.verify(consoleMock, times(1)).println("I love London's weather! (an instant ago)");
        mInOrder.verify(consoleMock, times(1)).println("I love the weather today (an instant ago)");
    }

}
