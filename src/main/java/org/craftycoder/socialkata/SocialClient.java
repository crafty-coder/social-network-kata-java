package org.craftycoder.socialkata;

import org.craftycoder.socialkata.actions.PublishPostToTimeline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialClient {

    private static final String EXIT = "exit";
    private static final Pattern userPattern = Pattern.compile("^(.+)\\s->.*$");
    private static final Pattern msgPattern = Pattern.compile("^.+\\s->\\s(.+)$");

    private final Console console;
    private final PublishPostToTimeline publishPostToTimeline;

    public SocialClient(Console console, PublishPostToTimeline publishPostToTimeline) {
        this.console = console;
        this.publishPostToTimeline = publishPostToTimeline;
    }

    void start() {
        String textRead;
        do {
            textRead = console.read();
            ifActionThenDispatch(textRead);
        } while (!EXIT.equalsIgnoreCase(textRead));
    }

    private void ifActionThenDispatch(String text) {
        if (!EXIT.equalsIgnoreCase(text)) {
            String user = userExtractor(text);
            String msg = msgExtractor(text);
            publishPostToTimeline.publishPost(user, msg);
        }
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
