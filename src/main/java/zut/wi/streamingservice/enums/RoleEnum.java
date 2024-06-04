package zut.wi.streamingservice.enums;

public enum RoleEnum {
    ADMIN("Administrator - full access"),
    USER("User - basic access"),
    MODERATOR("Moderator - can manage content"),
    SUBSCRIBED_USER("Subscribed User - has paid subscription");

    private final String description;

    RoleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
