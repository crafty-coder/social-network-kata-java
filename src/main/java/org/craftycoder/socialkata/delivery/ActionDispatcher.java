package org.craftycoder.socialkata.delivery;

import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.craftycoder.socialkata.domain.actions.ViewUserTimeline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionDispatcher {

    private static final String EXIT = "exit";
    private static final Pattern publishToTimeLinePattern = Pattern.compile("^.+\\s->.*$");
    private static final Pattern viewTimeLinePattern = Pattern.compile("^.+$");
    private static final Pattern userPattern = Pattern.compile("^(.+)\\s->.*$");
    private static final Pattern msgPattern = Pattern.compile("^.+\\s->\\s(.+)$");

    private Console console;
    private PublishPostToTimeline publishPostToTimeline;
    private ViewUserTimeline viewUserTimeline;

    public ActionDispatcher(Console console, PublishPostToTimeline publishPostToTimeline, ViewUserTimeline viewUserTimeline) {
        this.console = console;
        this.publishPostToTimeline = publishPostToTimeline;
        this.viewUserTimeline = viewUserTimeline;
    }

    public void dispatch(String text) {
        if (EXIT.equalsIgnoreCase(text)) {
            return;
        }
        else if (isPublishTimelineAction(text)) {
            String user = userExtractor(text);
            String msg = msgExtractor(text);
            publishPostToTimeline.publishPost(user, msg);
        }

        else if (isViewTimelineAction(text)) {
            String user = text;
            viewUserTimeline.view(user)
                    .forEach(line -> console.println(line));
        }
    }

    private Boolean isPublishTimelineAction(String text) {
        Matcher m = publishToTimeLinePattern.matcher(text);
        return m.find();
    }

    private Boolean isViewTimelineAction(String text) {
        Matcher m = viewTimeLinePattern.matcher(text);
        return m.find();
    }

    private String userExtractor(String text) {
        return extract(text, userPattern);
    }

    private String msgExtractor(String text) {
        return extract(text, msgPattern);

    }

    private String extract(String text, Pattern msgPattern) {
        Matcher m = msgPattern.matcher(text);
        if (m.find()) {
            return m.group(1);
        }
        return "";

    }

}
