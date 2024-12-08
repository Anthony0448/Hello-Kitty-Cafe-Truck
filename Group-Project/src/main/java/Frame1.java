import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.ZonedDateTime;

public class Frame1 extends Frame {
    protected JsonToObject jsonToObject;

    // Top panel
    protected TextField firstNameTextField;
    protected TextField lastNameTextField;
    protected Button startShiftButton;
    protected Button endShiftButton;

    protected String firstName;
    protected String lastName;

    // Middle panel
    protected Button loadInventoryButton;
    protected Button showInventoryButton;
    protected TextField startTime;
    protected TextField endTime;

    // Bottom panel
    protected TextField productTextField;
    protected TextField codeTextField;
    protected TextField quantityTextField;
    protected Button addButton;
    protected Button removeButton;

    // Constructor for Frame1 to call each Panel that makes up Frame1
    public Frame1(JsonToObject jsonToObject) {
        // By reference
        this.jsonToObject = jsonToObject;

        setLayout(new BorderLayout());

        // Add the composite panels into the main Frame
        add(createTopPanel(), BorderLayout.NORTH);
        add(createMiddlePanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setTitle("Login");
        setSize(400, 400);
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
        Panel topPanel = new Panel(new GridLayout(3, 1));

        // namePanel to hold a 2x2 of first name, first name input, last name, last name input
        Panel namePanel = new Panel(new GridLayout(2, 2));

        // Label shows the text on the Panel
        namePanel.add(new Label("First Name:"));
        firstNameTextField = new TextField(10);
        namePanel.add(firstNameTextField);

        namePanel.add(new Label("Last Name:"));
        lastNameTextField = new TextField(10);
        namePanel.add(lastNameTextField);

        // ************************************************************************************************************
        Panel shiftPanel = new Panel(new FlowLayout());

        startShiftButton = new Button("Start Shift");
        shiftPanel.add(startShiftButton);

        endShiftButton = new Button("End Shift");
        shiftPanel.add(endShiftButton);

        // Action listener for saving first name and last name to corresponding variable on button press
        startShiftButton.addActionListener(e -> {
            firstName = firstNameTextField.getText();
            lastName = lastNameTextField.getText();

            startTime.setText(ZonedDateTime.now().toString());
            System.out.println("Shift started for: " + firstName + " " + lastName + " at: " + startTime.getText());
        });

        endShiftButton.addActionListener(e -> {
            endTime.setText(ZonedDateTime.now().toString());
        });

        // ************************************************************************************************************

        // Time display
        Panel timePanel = new Panel(new GridLayout(2, 2));
        timePanel.add(new Label("Start Time:"));
        startTime = new TextField(20);
        timePanel.add(startTime);
        timePanel.add(new Label("End Time:"));
        endTime = new TextField(20);
        timePanel.add(endTime);

        // ************************************************************************************************************
        // Add top of the first panel with the employee login
        topPanel.add(namePanel);
        // Add the second part of the first panel with the start and end shift button
        topPanel.add(shiftPanel);
        // Panel that shows the start and end times
        topPanel.add(timePanel);

        // Pass back the Panel object so it can be added to the main Frame with add();
        return topPanel;
    }

    /* Start of second panel of the first frame
     * This will contain the load inventory and show list buttons */
    private Panel createMiddlePanel() {
        Panel middlePanel = new Panel(new FlowLayout());

        loadInventoryButton = new Button("Load Inventory");
        middlePanel.add(loadInventoryButton);

        showInventoryButton = new Button("Show Inventory");
        middlePanel.add(showInventoryButton);

        // Action listener for start shift button that reads the JSON file and puts the data into objects Product and StoreInfo
        loadInventoryButton.addActionListener(e -> {
            jsonToObject.parseJson();

            System.out.println("Store information loaded: " + jsonToObject.storeInfo.getStore_name());
            System.out.println("Inventory loaded: " + jsonToObject.listOfProducts.size() + " items.");
        });

        showInventoryButton.addActionListener(e -> {
            Frame inventoryFrame = new Frame("Inventory");
            inventoryFrame.setSize(400, 600);
            inventoryFrame.setLayout(new BorderLayout());
            // Makes a text area to display inventory
            TextArea inventoryTextArea = new TextArea();
            // Cannot be edited manually when run
            inventoryTextArea.setEditable(false);

            inventoryTextArea.append("Store Name: " + jsonToObject.storeInfo.getStore_name() + '\n');
            inventoryTextArea.append("Phone Number: " + jsonToObject.storeInfo.getPhone_number() + '\n');
            inventoryTextArea.append("City: " + jsonToObject.storeInfo.getCity() + '\n');
            inventoryTextArea.append("State: " + jsonToObject.storeInfo.getState() + '\n');
            inventoryTextArea.append("Tax Percentage: " + jsonToObject.storeInfo.getTax_percentage() + '\n');

            // Extra line break for clarity
            inventoryTextArea.append("\n");

            for (Product product : jsonToObject.listOfProducts) {
                inventoryTextArea.append("Product Name: " + product.getProductName() + '\n');
                inventoryTextArea.append("Product Code: " + product.getProductCode() + '\n');
                inventoryTextArea.append("Price: " + product.getPrice() + '\n');
                inventoryTextArea.append("Description: " + product.getDescription() + '\n');
                inventoryTextArea.append("---------------" + '\n');
            }

            Button closeInventoryButton = new Button("Close");
            closeInventoryButton.addActionListener(e1 -> {
                inventoryFrame.dispose();
            });

            inventoryFrame.add(inventoryTextArea, BorderLayout.CENTER);
            inventoryFrame.add(closeInventoryButton, BorderLayout.SOUTH);
            inventoryFrame.setVisible(true);;

            // Closes windows of inventory
            inventoryFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    // Release resources used by selected Frame
                    inventoryFrame.dispose();
                }
            });
        });

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
        Panel bottomPanel = new Panel(new GridLayout(3, 1));

        // Product Code
        Panel codePanel = new Panel(new FlowLayout());

        codePanel.add(new Label("Product Code:"));

        codeTextField = new TextField(10);
        codePanel.add(codeTextField);

        // Quantity
        Panel quantityPanel = new Panel(new FlowLayout());

        quantityPanel.add(new Label("Quantity:"));

        quantityTextField = new TextField(10);
        quantityPanel.add(quantityTextField);

        bottomPanel.add(codePanel);
        bottomPanel.add(quantityPanel);

        // ************************************************************************************************************
        // Add and remove buttons for the bottom part of the bottom panel
        Panel addRemoveButtonGroup = new Panel(new FlowLayout());
        addButton = new Button("Add");
        addRemoveButtonGroup.add(addButton);
        removeButton = new Button("Remove");
        addRemoveButtonGroup.add(removeButton);
        bottomPanel.add(addRemoveButtonGroup);
    
        // Adding action listeners to buttons
        addButton.addActionListener(e -> {
            // Code to handle adding a product
            String productCode = codeTextField.getText();
            int quantity = Integer.parseInt(quantityTextField.getText());

            // If object is found
            if (jsonToObject.getProductByCode(productCode) != null) {
                System.out.println(jsonToObject.getProductByCode(productCode).getQuantity());
                // Change object quantity of matching productCode
                jsonToObject.getProductByCode(productCode).setQuantity(quantity);
                System.out.println(jsonToObject.getProductByCode(productCode).getQuantity());


                System.out.println("Added product with code: " + productCode + " and quantity: " + quantity);
            }
            else {
                System.out.println("ERROR 404 PRODUCT CODE: " + productCode + " NOT FOUND!");
            }
        });
    
        removeButton.addActionListener(e -> {
            // Code to handle removing a product
            String productCode = codeTextField.getText();

            if (jsonToObject.getProductByCode(productCode) != null) {
                jsonToObject.getProductByCode(productCode).setQuantity(0);
            }

                System.out.println("Removed product with code: " + productCode);
        });

        return bottomPanel;
    }
}