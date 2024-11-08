import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginWindow extends Frame {
    TextField username;
    TextField password;

    public LoginWindow() {
        Label usernameLabel = new Label("Username:");
        add(usernameLabel);

        username = new TextField(10);
        add(username);

        Label passwordLabel = new Label("Password:");
        add(passwordLabel);

        password = new TextField(10);
        add(password);

//        setLayout(new GridLayout(4, 1, 5, 5));
        setLayout(new FlowLayout());
        setTitle("Login");
        setSize(300, 500);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            // Window closing method
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}