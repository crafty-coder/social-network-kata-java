package org.craftycoder.socialkata.domain.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.craftycoder.socialkata.domain.util.TimeFormatter.timeAgoFormatter;

@RunWith(Parameterized.class)
public class TimeFormatterShould {

    private Long now;
    private Long ago;
    private String expected;

    public TimeFormatterShould(Long now, Long ago, String expected) {
        this.now = now;
        this.ago = ago;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection inputs() {
        return Arrays.asList(new Object[][]{
                {2_000L, 2_000L, "(an instant ago)"},
                {2_000L, 1_000L, "(1 second ago)"},
                {4_000L, 1_000L, "(3 seconds ago)"},
                {3 * 60 * 1_000L, 2 * 60 * 1_000L, "(1 minute ago)"},
                {5 * 60 * 1_000L, 2 * 60 * 1_000L, "(3 minutes ago)"},
                {5 * 60 * 60 * 1_000L, 4 * 60 * 60 * 1_000L, "(1 hour ago)"},
                {5 * 60 * 60 * 1_000L, 2 * 60 * 60 * 1_000L, "(3 hours ago)"},
                {7 * 24 * 60 * 60 * 1_000L, 6 * 24 * 60 * 60 * 1_000L, "(1 day ago)"},
                {5 * 24 * 60 * 60 * 1_000L, 2 * 24 * 60 * 60 * 1_000L, "(3 days ago)"},
        });
    }

    @Test
    public void format_time_difference() {

        Assert.assertEquals(expected, timeAgoFormatter(now, ago));

    }


}