package org.craftycoder.socialkata.domain.model;

public class Post {

    public final String user;
    public final String message;

    public Post(final String user, final String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!user.equals(post.user)) return false;
        return message.equals(post.message);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }


}
