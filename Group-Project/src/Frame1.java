import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame1 extends Frame {
    TextField firstName;
    TextField lastName;

    public Frame1() {
        // The grid layout for the whole frame
        setLayout(new GridLayout(3,1));

        // Panel 1
        Panel topPanel = new Panel(new GridLayout(2,1));

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

        // Add half of the first panel with the employee login
        topPanel.add(namesPanel);

        Panel shiftPanel = new Panel(new GridLayout(2,2));

        Label dateLabel = new Label("Date:");
        shiftPanel.add(dateLabel);

        Label timestamp = new Label("timestamp holder");
        shiftPanel.add(timestamp);

        Button startShift = new Button("Start");
        shiftPanel.add(startShift);

        Button endShift = new Button("End");
        shiftPanel.add(endShift);

        // Second half of the first panel with te start and end shift button
        topPanel.add(shiftPanel);

        // Put the composite(?) panel with both both frames on the top
        add(topPanel);

        /* Start of second panel of the first frame
         * This will contain the load inventory and show list buttons */

        // 3 rows for the spacer between the top and middle panel
        Panel middlePanel = new Panel(new GridLayout(3,1));

        // This is a spacer
        middlePanel.add(new Label());

        Button loadInventoryButton = new Button("Load Inventory");
        middlePanel.add(loadInventoryButton);

        Button showListButton = new Button("Show List");
        middlePanel.add(showListButton);

        // Adds to the general Frame
        add(middlePanel);

        /* Start of the third panel for the first frame
        * Contains:
        * A text field to enter a product code number (has a label too)
        * A text field to add the quantity for that product
        *
        * Two buttons:
        * An "add" button to add the item that is typed in the text field
        * A "remove" button that removes the item by using the specific line number of that product shown in the invoice
         */

        Panel productGroup = new Panel(new FlowLayout());

        Label productLabel = new Label("Product:");
        productGroup.add(productLabel);

        TextField productTextField = new TextField(10);
        productGroup.add(productTextField);

        // To contain the flow panels for product and code
        Panel composite_Product_Code = new Panel(new GridLayout(2,1));

        // The composite panel of the label and text field for product is added to the top of the bottom panel
        composite_Product_Code.add(productGroup);

        // Now for the code label and the text input
        Panel codeGroup = new Panel(new FlowLayout());

        Label codeLabel = new Label("Code:");
        codeGroup.add(codeLabel);

        TextField codeTextField = new TextField(10);
        codeGroup.add(codeTextField);

        composite_Product_Code.add(codeGroup);

        Panel bottomPannel = new Panel(new GridLayout(4,1));

        // Quantity middle panel for the bottom panel
        Panel quantityGroup = new Panel(new FlowLayout());

        Label quantityLabel = new Label("Quantity:");
        quantityGroup.add(quantityLabel);

        TextField quantityTextField = new TextField(10);
        quantityGroup.add(quantityTextField);

        // Add and remove buttons for the bottom part of the bottom panel
        Panel addRemoveButtons = new Panel(new FlowLayout());

        Button addButton = new Button("Add");
        addRemoveButtons.add(addButton);

        Button removeButton = new Button("Remove");
        addRemoveButtons.add(removeButton);

        // Spacer
        bottomPannel.add(new Label());

        bottomPannel.add(composite_Product_Code);
        bottomPannel.add(quantityGroup);
        bottomPannel.add(addRemoveButtons);

        add(bottomPannel);


        setTitle("Login");
        setSize(300, 700);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            // Window closing method
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}