package org.craftycoder.socialkata.domain.model;

import java.util.List;
import java.util.stream.Collectors;

public class Wall {

    private final List<Timeline> timelines;

    public Wall(List<Timeline> timelines) {
        this.timelines = timelines;
    }

    public List<Post> aggregatedPosts() {
        return timelines.stream()
                .flatMap(t -> t.posts.stream())
                .sorted((p1, p2) -> Long.compare(p2.timestamp, p1.timestamp))
                .collect(Collectors.toList());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wall wall = (Wall) o;

        return timelines.equals(wall.timelines);
    }

    @Override
    public int hashCode() {
        return timelines.hashCode();
    }
}
