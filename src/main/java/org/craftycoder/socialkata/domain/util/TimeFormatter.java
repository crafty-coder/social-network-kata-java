package org.craftycoder.socialkata.domain.util;

public class TimeFormatter {

    public static String timeAgoFormatter(Long current, Long ago) {

        Long diff = current - ago;

        Long secs = diff / 1000;
        Long mins = (diff / 1000) / 60;
        Long hours = (diff / 1000) / (60 * 60);
        Long days = ((diff / 1000) / (60 * 60)) / 24;

        if (days > 1) return "(" + days + " days ago)";
        else if (days == 1) return "(" + days + " day ago)";
        else if (hours > 1) return "(" + hours + " hours ago)";
        else if (hours == 1) return "(" + hours + " hour ago)";
        else if (mins > 1) return "(" + mins + " minutes ago)";
        else if (mins == 1) return "(" + mins + " minute ago)";
        else if (secs > 1) return "(" + secs + " seconds ago)";
        else if (secs == 1) return "(" + secs + " second ago)";
        else return "(an instant ago)";

    }
}
