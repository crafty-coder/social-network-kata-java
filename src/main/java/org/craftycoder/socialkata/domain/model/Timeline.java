package org.craftycoder.socialkata.domain.model;

import java.util.List;

public class Timeline {
    public final List<Post> posts;

    public Timeline(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timeline timeline = (Timeline) o;

        return posts.equals(timeline.posts);
    }

    @Override
    public int hashCode() {
        return posts.hashCode();
    }
}
