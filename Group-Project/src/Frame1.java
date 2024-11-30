import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.ZonedDateTime;

public class Frame1 extends Frame {
    // Top panel
    protected TextField firstName;
    protected TextField lastName;
    protected Button startShiftButton;
    protected Button endShiftButton;

    // Middle panel
    protected Button loadInventoryButton;
    protected Button showListButton;

    // Bottom panel
    protected TextField productTextField;
    protected TextField codeTextField;
    protected TextField quantityTextField;
    protected Button addButton;
    protected Button removeButton;

    // Constructor for Frame1 to call each Panel that makes up Frame1
    public Frame1() {
        setLayout(new GridLayout(3,1));

        // Add the composite panels into the main Frame
        add(createTopPanel());
        add(createMiddlePanel());
        add(createBottomPanel());

        setTitle("Login");
        setSize(300, 700);
        setVisible(true);

        // Stops program when closing Frame
        addWindowListener(new WindowAdapter() {
            // Window closing method
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private Panel createTopPanel() {
        // The composite top Panel
        Panel topPanel = new Panel(new GridLayout(2, 1));

        // The first and last name labels will be within their own panel so that they stay next to each other when resizing
        // The FlowLayout.Center aligns the components of the frame to the center
        Panel firstNamePanel = new Panel(new FlowLayout(FlowLayout.CENTER));

        /* Adds the name label on the left of the first name panel
        * I did not make this a new Label variable since this is never updated */
        firstNamePanel.add(new Label("First name:"));

        /* Adds the name text field on the right side
        * This is a class variable since we will need to pass the value of what is inputted in the text field */
        firstName = new TextField(10);
        firstNamePanel.add(firstName);

        Panel lastNamePanel = new Panel(new FlowLayout(FlowLayout.CENTER));

        lastNamePanel.add(new Label("Last name:"));

        lastName = new TextField(10);
        lastNamePanel.add(lastName);

        // Now we put the panels for the name within the panel
        // The panel within the frame that is contained in a border layout but is grid layout itself.
        Panel namesPanel = new Panel(new GridLayout(2, 1));

        namesPanel.add(firstNamePanel);
        namesPanel.add(lastNamePanel);


        Panel shiftPanel = new Panel(new GridLayout(2,2));

        shiftPanel.add(new Label("Date:"));

        // Timestamp [THIS IS NOT WORKING AS INTENDED THIS IS A PLACEHOLDER]
        //shiftPanel.add(new Label(ZonedDateTime.now().toString()));
        shiftPanel.add(new Label("TIMESTAMP PLACEHOLDER"));

        // The buttons will be class variables since they will be used for action listeners
        startShiftButton = new Button("Start");
        shiftPanel.add(startShiftButton);

        endShiftButton = new Button("End");
        shiftPanel.add(endShiftButton);

        // Add half of the first panel with the employee login
        topPanel.add(namesPanel);
        // Add the second half of the first panel with the start and end shift button
        topPanel.add(shiftPanel);

        // Put the composite(?) panel with both both frames on the top
        //add(topPanel);

        // Pass back the Panel object so it can be added to the main Frame with add();
        return topPanel;
    }

    /* Start of second panel of the first frame
     * This will contain the load inventory and show list buttons */
    private Panel createMiddlePanel() {
        // 3 rows for the spacer between the top and middle panel
        Panel middlePanel = new Panel(new GridLayout(3, 1));

        // This is a spacer
        middlePanel.add(new Label());

        // Buttons will use event listeners; hence, class variables
        loadInventoryButton = new Button("Load Inventory");
        middlePanel.add(loadInventoryButton);

        showListButton = new Button("Show List");
        middlePanel.add(showListButton);

        return middlePanel;
    }

    /* Start of the third panel for the first frame
     * Contains:
     * A text field to enter a product code number (has a label too)
     * A text field to add the quantity for that product
     *
     * Two buttons:
     * An "add" button to add the item that is typed in the text field
     * A "remove" button that removes the item by using the specific line number of that product shown in the invoice
     */
    private Panel createBottomPanel() {
        Panel bottomPanel = new Panel(new GridLayout(4, 1));

        // Group for the product label and text field
        Panel productGroup = new Panel(new FlowLayout());

        productGroup.add(new Label("Product:"));

        productTextField = new TextField(10);
        productGroup.add(productTextField);

        // Now for the code label and the text input
        Panel codeGroup = new Panel(new FlowLayout());

        codeGroup.add(new Label("Code:"));

        codeTextField = new TextField(10);
        codeGroup.add(codeTextField);

        // To contain the flow panels for product and code
        Panel composite_Product_Code = new Panel(new GridLayout(2,1));

        // The composite panel of the label and text field for product is added to the top of the bottom panel
        composite_Product_Code.add(productGroup);
        composite_Product_Code.add(codeGroup);


        // Quantity middle panel for the bottom panel
        Panel quantityGroup = new Panel(new FlowLayout());

        quantityGroup.add(new Label("Quantity:"));

        quantityTextField = new TextField(10);
        quantityGroup.add(quantityTextField);

        // Add and remove buttons for the bottom part of the bottom panel
        Panel addRemoveButtonGroup = new Panel(new FlowLayout());

        addButton = new Button("Add");
        addRemoveButtonGroup.add(addButton);

        removeButton = new Button("Remove");
        addRemoveButtonGroup.add(removeButton);

        // Spacer
        bottomPanel.add(new Label());

        bottomPanel.add(composite_Product_Code);
        bottomPanel.add(quantityGroup);
        bottomPanel.add(addRemoveButtonGroup);

        return bottomPanel;
    }
}