package org.craftycoder.socialkata.domain.model;

import java.util.List;

public class Wall {

    public final List<Timeline> timelines;

    public Wall(List<Timeline> timelines) {
        this.timelines = timelines;
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
