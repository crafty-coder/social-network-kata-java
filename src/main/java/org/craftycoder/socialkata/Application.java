package org.craftycoder.socialkata;

import org.craftycoder.socialkata.delivery.Console;
import org.craftycoder.socialkata.delivery.SocialClient;
import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.craftycoder.socialkata.domain.actions.ViewUserTimeline;
import org.craftycoder.socialkata.domain.model.PostRepository;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.infrastructure.ConsoleShell;
import org.craftycoder.socialkata.infrastructure.InMemoryPostRepository;
import org.craftycoder.socialkata.infrastructure.SystemClock;

public class Application {

    public static void main(String[] args) {

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

        Console console = new ConsoleShell(System.in, System.out);

        SocialClient sc = new SocialClient(console, publishPostToTimeline, viewUserTimeline);
        sc.start();

    }
}
