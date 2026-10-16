package factory;

public class EmailNotification implements Notification {

    @Override
    public void send(String content) {
        System.out.println("Email notification sent: " + content);
    }
}
