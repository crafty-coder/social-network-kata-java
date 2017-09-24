package org.craftycoder.socialkata;

import org.craftycoder.socialkata.delivery.Console;
import org.craftycoder.socialkata.delivery.SocialClient;
import org.craftycoder.socialkata.domain.actions.FollowUser;
import org.craftycoder.socialkata.domain.actions.PublishPost;
import org.craftycoder.socialkata.domain.actions.ViewTimeline;
import org.craftycoder.socialkata.domain.actions.ViewWall;
import org.craftycoder.socialkata.domain.model.Follows;
import org.craftycoder.socialkata.domain.model.Posts;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.domain.service.TimelineService;
import org.craftycoder.socialkata.infrastructure.ConsoleShell;
import org.craftycoder.socialkata.infrastructure.InMemoryFollows;
import org.craftycoder.socialkata.infrastructure.InMemoryPosts;
import org.craftycoder.socialkata.infrastructure.SystemClock;

public class Application {

    public static void main(String[] args) {

        Posts posts = new InMemoryPosts();
        Follows follows = new InMemoryFollows();
        Clock clock = new SystemClock();
        TimelineService timelineService = new TimelineService(posts);

        PublishPost publishPost = new PublishPost(
                posts,
                clock
        );

        ViewTimeline viewTimeline = new ViewTimeline(
                timelineService,
                clock
        );

        FollowUser followUser = new FollowUser(follows);

        ViewWall viewWall = new ViewWall(posts, clock);

        Console console = new ConsoleShell(System.in, System.out);

        SocialClient sc = new SocialClient(console, publishPost, viewTimeline, followUser, viewWall);
        sc.start();

    }
}
