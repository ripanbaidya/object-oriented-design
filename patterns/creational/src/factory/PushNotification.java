package factory;

public class PushNotification implements Notification {

    @Override
    public void send(String content) {
        System.out.println("Push notification sent: " + content);
    }
}
