package org.craftycoder.socialkata;

import org.craftycoder.socialkata.domain.actions.PublishPostToTimeline;
import org.craftycoder.socialkata.domain.actions.ViewUserTimeline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialClient {

    private static final String EXIT = "exit";
    private static final Pattern publishToTimeLinePattern = Pattern.compile("^.+\\s->.*$");
    private static final Pattern viewTimeLinePattern = Pattern.compile("^.+$");
    private static final Pattern userPattern = Pattern.compile("^(.+)\\s->.*$");
    private static final Pattern msgPattern = Pattern.compile("^.+\\s->\\s(.+)$");

    private final Console console;
    private final PublishPostToTimeline publishPostToTimeline;
    private final ViewUserTimeline viewUserTimeline;

    public SocialClient(Console console, PublishPostToTimeline publishPostToTimeline, ViewUserTimeline viewUserTimeline) {
        this.console = console;
        this.publishPostToTimeline = publishPostToTimeline;
        this.viewUserTimeline = viewUserTimeline;
    }

    void start() {
        String textRead;
        do {
            textRead = console.read();
            ifActionThenDispatch(textRead);
        } while (!EXIT.equalsIgnoreCase(textRead));
    }

    private void ifActionThenDispatch(String text) {
        if (EXIT.equalsIgnoreCase(text)) {
           return;
        }
        if (isPublishTimelineAction(text)){
            String user = userExtractor(text);
            String msg = msgExtractor(text);
            publishPostToTimeline.publishPost(user, msg);
        }

        if (isViewTimelineAction(text)){
            String user = text;
            viewUserTimeline.view(user).forEach(console::println);
        }

    }

    private Boolean isPublishTimelineAction(String text){
        Matcher m =  publishToTimeLinePattern.matcher(text);
        return m.find();
    }

    private Boolean isViewTimelineAction(String text){
        Matcher m =  viewTimeLinePattern.matcher(text);
        return m.find();
    }

    private String userExtractor(String text) {
        return extract(text, userPattern);
    }

    private String msgExtractor(String text) {
        return extract(text, msgPattern);

    }

    private String extract(String text, Pattern msgPattern) {
        Matcher m =  msgPattern.matcher(text);
        if (m.find()){
            return m.group(1);
        }
        return "";
    }
}
