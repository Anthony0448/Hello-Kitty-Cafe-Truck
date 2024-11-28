import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginWindow extends Frame {

    TextField firstName;
    TextField lastName;

    public LoginWindow() {
        // The border layout for the whole frame
        setLayout(new BorderLayout());

        // The first and last name labels will be within their own panel so that they stay next to each other when resizing
        // The FlowLayout.Center aligns the components of the frame to the center
        Panel firstNamePanel = new Panel(new FlowLayout(FlowLayout.CENTER));

        // Adds the name label on the left of the first name panel
        Label firstNameLabel = new Label("First name:");
        firstNamePanel.add(firstNameLabel);

        // Adds the name text field on the right
        firstName = new TextField(10);
        firstNamePanel.add(firstName);

        Panel lastNamePanel = new Panel(new FlowLayout(FlowLayout.CENTER));

        Label lastNameLabel = new Label("Last name:");
        lastNamePanel.add(lastNameLabel);

        lastName = new TextField(10);
        lastNamePanel.add(lastName);

        // Now we put the panels for the name within the panel
        // The panel within the frame that is contained in a border layout but is grid layout itself.
        Panel namesPanel = new Panel();
        namesPanel.setLayout(new GridLayout(2, 1));

        namesPanel.add(firstNamePanel);
        namesPanel.add(lastNamePanel);

        // Put the composite(?) panel with both labels on the North of the frame
        add(namesPanel, BorderLayout.NORTH);

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