package delivery.pack.exception;

public class UserNotificationException extends RuntimeException{
    private String description;

    public UserNotificationException(String description) {
        super();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
