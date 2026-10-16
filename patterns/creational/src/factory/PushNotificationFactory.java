package factory;

public class PushNotificationFactory implements NotificationFactory {

    @Override
    public Notification createNotification() {
        return new PushNotification();
    }
}
