package org.craftycoder.socialkata;

import org.craftycoder.socialkata.delivery.Console;
import org.craftycoder.socialkata.delivery.SocialClient;
import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.craftycoder.socialkata.domain.actions.ViewUserTimeline;
import org.craftycoder.socialkata.domain.model.Timeline;
import org.craftycoder.socialkata.domain.ports.Clock;
import org.craftycoder.socialkata.infrastructure.ConsoleShell;
import org.craftycoder.socialkata.infrastructure.InMemoryTimeline;
import org.craftycoder.socialkata.infrastructure.SystemClock;

public class Application {

    public static void main(String[] args) {

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

        Console console = new ConsoleShell(System.in, System.out);

        SocialClient sc = new SocialClient(console, publishPostToTimeline, viewUserTimeline);
        sc.start();

    }
}
