import java.sql.Timestamp;

public class Post {
    private int id;
    private String message = "";
    private User user;
    private User wall;
    private Timestamp publishingDate;
    private User[] likedBy = new User[0];

    public Post(int id, String message, User user, User wall, Timestamp publishingDate, User[] likedBy) {
        this.id = id;
        this.message = message;
        this.user = user;
        this.wall = wall;
        this.publishingDate = publishingDate;
        this.likedBy = likedBy;
    }

    /** @return the id */
    public int getId() {
        return id;
    }

    /** @return the message */
    public String getMessage() {
        return message;
    }

    /** @return the user */
    public User getUser() {
        return user;
    }

    /** @return the userid */
    public int getUserId() {
        return user.getId();
    }

    /** @return the username */
    public String getUsername() {
        return user.getUsername();
    }

    /** @return the wall */
    public User getWall() {
        return wall;
    }

    /** @return the publishingDate */
    public Timestamp getPublishingDate() {
        return publishingDate;
    }

    /** @return the likedBy */
    public User[] getLikedBy() {
        return likedBy;
    }
}
