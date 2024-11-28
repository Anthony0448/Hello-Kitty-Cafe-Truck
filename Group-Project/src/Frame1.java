import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame1 extends Frame {

    TextField firstName;
    TextField lastName;

    public Frame1() {
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

        Panel shiftPanel = new Panel(new GridLayout(2,2));

        Label dateLabel = new Label("Date:");
        shiftPanel.add(dateLabel);

        Label timestamp = new Label("timestamp holder");
        shiftPanel.add(timestamp);

        Button startShift = new Button("Start");
        shiftPanel.add(startShift);

        Button endShift = new Button("End");
        shiftPanel.add(endShift);

        Panel employeeLogin = new Panel(new GridLayout(2,1));

        employeeLogin.add(namesPanel);
        employeeLogin.add(shiftPanel);

        // Put the composite(?) panel with both both frames on the top
        add(employeeLogin, BorderLayout.NORTH);

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