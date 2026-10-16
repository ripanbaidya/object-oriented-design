package factory;

public class SimpleNotificationFactory {

    private SimpleNotificationFactory() {
    }

    public static Notification createNotification(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Notification type cannot be null");
        }

        return switch (type.toLowerCase()) {
            case "email" -> new EmailNotification();
            case "push" -> new PushNotification();
            default -> throw new IllegalArgumentException("Invalid notification type: " + type);
        };
    }
}
