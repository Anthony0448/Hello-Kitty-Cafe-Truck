import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginWindow extends Frame {
    TextField username;
    TextField password;

    public LoginWindow() {
        Label usernameLabel = new Label("Username:");

        username = new TextField(10);
        add(username);

//        password = new TextField("Password:");
//        add(password);

        setLayout(new GridLayout(3, 1));
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