package org.craftycoder.socialkata.delivery;

import org.craftycoder.socialkata.domain.actions.FollowUser;
import org.craftycoder.socialkata.domain.actions.PublishPost;
import org.craftycoder.socialkata.domain.actions.ViewTimeline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionDispatcher {

    private static final String EXIT = "exit";
    private static final Pattern followPattern = Pattern.compile("^(.+)\\sfollows\\s(.+)$");
    private static final Pattern followedPattern = Pattern.compile("^.+\\sfollows\\s(.+)$");
    private static final Pattern publishToTimeLinePattern = Pattern.compile("^.+\\s->.*$");
    private static final Pattern viewTimeLinePattern = Pattern.compile("^.+$");
    private static final Pattern userPattern = Pattern.compile("^(.+)\\s->.*$");
    private static final Pattern msgPattern = Pattern.compile("^.+\\s->\\s(.+)$");

    private Console console;
    private PublishPost publishPost;
    private ViewTimeline viewTimeline;
    private FollowUser followUser;

    public ActionDispatcher(Console console, PublishPost publishPost, ViewTimeline viewTimeline, FollowUser followUser) {
        this.console = console;
        this.publishPost = publishPost;
        this.viewTimeline = viewTimeline;
        this.followUser = followUser;
    }

    public void dispatch(String text) {

        if (EXIT.equalsIgnoreCase(text)) {
            return;
        } else if (isPublishTimelineAction(text)) {
            String user = userPublishExtractor(text);
            String msg = msgPublishExtractor(text);
            publishPost.publishPost(user, msg);
        } else if (isFollowAction(text)) {
            String follower = followerExtractor(text);
            String followed = followedExtractor(text);

            followUser.follow(follower, followed);
        } else if (isViewTimelineAction(text)) {
            String user = text;
            viewTimeline.view(user)
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

    private Boolean isFollowAction(String text) {
        Matcher m = followPattern.matcher(text);
        return m.find();
    }

    private String userPublishExtractor(String text) {
        return extract(text, userPattern);
    }

    private String msgPublishExtractor(String text) {
        return extract(text, msgPattern);
    }

    private String followerExtractor(String text) {
        return extract(text, followPattern);
    }

    private String followedExtractor(String text) {
        return extract(text, followedPattern);
    }

    private String extract(String text, Pattern msgPattern) {
        Matcher m = msgPattern.matcher(text);
        if (m.find()) {
            return m.group(1);
        }
        return "";

    }

}
