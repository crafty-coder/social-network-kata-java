package org.craftycoder.socialkata.delivery;

import org.craftycoder.socialkata.domain.actions.FollowUser;
import org.craftycoder.socialkata.domain.actions.PublishPost;
import org.craftycoder.socialkata.domain.actions.ViewTimeline;
import org.craftycoder.socialkata.domain.actions.ViewWall;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SocialClientShould {

    @Mock
    private Console consoleMock;

    @Mock
    private PublishPost publishPostMock;

    @Mock
    private ViewTimeline viewTimelineMock;

    @Mock

    private FollowUser followUserMock;

    @Mock
    private ViewWall viewWallMock;

    @Test
    public void stop_reading_commands_when_exit() {

        when(consoleMock.read())
                .thenReturn("exit")
                .thenThrow(new RuntimeException("Not Expected call"));

        new SocialClient(consoleMock, publishPostMock, viewTimelineMock, followUserMock, viewWallMock).start();

        verify(consoleMock, times(1)).read();

    }

    @Test
    public void send_publish_commands_to_publish_to_timeline() {

        when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("exit");

        new SocialClient(consoleMock, publishPostMock, viewTimelineMock, followUserMock, viewWallMock).start();

        verify(publishPostMock, times(1)).publishPost("Alice", "I love the weather today");

    }

    @Test
    public void send_two_publish_commands_to_publish_to_timeline() {

        when(consoleMock.read())
                .thenReturn("Alice -> I love the weather today")
                .thenReturn("Bob -> Damn! We lost!")
                .thenReturn("exit");

        new SocialClient(consoleMock, publishPostMock, viewTimelineMock, followUserMock, viewWallMock).start();

        verify(publishPostMock, times(1)).publishPost("Alice", "I love the weather today");
        verify(publishPostMock, times(1)).publishPost("Bob", "Damn! We lost!");

    }

    @Test
    public void recover_and_print_timeline() {

        when(consoleMock.read())
                .thenReturn("Alice")
                .thenReturn("exit");


        when(viewTimelineMock.view("Alice"))
                .thenReturn(Collections.singletonList("I Love the weather today (5 minutes ago)"));

        new SocialClient(consoleMock, publishPostMock, viewTimelineMock, followUserMock, viewWallMock).start();

        verify(consoleMock, times(1)).println("I Love the weather today (5 minutes ago)");

    }

    @Test
    public void send_follow_commands() {

        when(consoleMock.read())
                .thenReturn("Charlie follows Alice")
                .thenReturn("exit");


        when(viewTimelineMock.view("Alice"))
                .thenReturn(Collections.singletonList("I Love the weather today (5 minutes ago)"));

        new SocialClient(consoleMock, publishPostMock, viewTimelineMock, followUserMock, viewWallMock).start();

        verify(followUserMock, times(1)).follow("Charlie", "Alice");

    }

    @Test
    public void recover_and_print_wall() {

        when(consoleMock.read())
                .thenReturn("Alice wall")
                .thenReturn("exit");


        when(viewWallMock.view("Alice"))
                .thenReturn(Arrays.asList(
                        "Bob - Good game though. (1 minutes ago)",
                        "Alice - I Love the weather today (5 minutes ago)"
                ));

        new SocialClient(consoleMock, publishPostMock, viewTimelineMock, followUserMock, viewWallMock).start();

        verify(consoleMock, times(1)).println("Bob - Good game though. (1 minutes ago)");
        verify(consoleMock, times(1)).println("Alice - I Love the weather today (5 minutes ago)");

    }


}