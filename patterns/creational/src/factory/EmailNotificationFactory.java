package factory;

public class EmailNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}
