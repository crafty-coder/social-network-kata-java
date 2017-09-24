package org.craftycoder.socialkata.domain.model;

public class Post {

    public final User user;
    public final String message;
    public final Long timestamp;

    public Post(final User user, final String message, final Long timestamp) {
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!user.equals(post.user)) return false;
        if (!message.equals(post.message)) return false;
        return timestamp.equals(post.timestamp);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }
}
