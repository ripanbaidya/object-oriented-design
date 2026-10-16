package factory;

public interface NotificationFactory {

    Notification createNotification();

    default void sendNotification(String content) {
        Notification notification = createNotification();
        notification.send(content);
    }
}
