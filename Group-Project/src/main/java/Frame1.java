import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.ZonedDateTime;

public class Frame1 extends Frame {
    protected JsonToObject jsonToObject;

    protected TextField firstNameTextField;
    protected TextField lastNameTextField;
    protected Button startShiftButton;
    protected Button endShiftButton;
    protected Button loadInventoryButton;
    protected Button showInventoryButton;
    protected TextField startTime;
    protected TextField endTime;
    protected TextField codeTextField;
    protected TextField quantityTextField;
    protected Button addButton;
    protected Button removeButton;
    private Frame2 frame2;

    public Frame1(JsonToObject jsonToObject, Frame2 frame2) {
        this.jsonToObject = jsonToObject;
        this.frame2 = frame2;

        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.NORTH);
        add(createMiddlePanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setTitle("Login");
        setSize(400, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private Panel createTopPanel() {
        Panel topPanel = new Panel(new GridLayout(3, 1));

        Panel namePanel = new Panel(new GridLayout(2, 2));
        namePanel.add(new Label("First Name:"));
        firstNameTextField = new TextField(10);
        namePanel.add(firstNameTextField);

        namePanel.add(new Label("Last Name:"));
        lastNameTextField = new TextField(10);
        namePanel.add(lastNameTextField);

        Panel shiftPanel = new Panel(new FlowLayout());
        startShiftButton = new Button("Start Shift");
        shiftPanel.add(startShiftButton);

        endShiftButton = new Button("End Shift");
        shiftPanel.add(endShiftButton);

        startShiftButton.addActionListener(e -> {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            startTime.setText(ZonedDateTime.now().toString());
            System.out.println("Shift started for: " + firstName + " " + lastName);
        });

        endShiftButton.addActionListener(e -> {
            endTime.setText(ZonedDateTime.now().toString());
        });

        Panel timePanel = new Panel(new GridLayout(2, 2));
        timePanel.add(new Label("Start Time:"));
        startTime = new TextField(20);
        timePanel.add(startTime);
        timePanel.add(new Label("End Time:"));
        endTime = new TextField(20);
        timePanel.add(endTime);

        topPanel.add(namePanel);
        topPanel.add(shiftPanel);
        topPanel.add(timePanel);

        return topPanel;
    }

    private Panel createMiddlePanel() {
        Panel middlePanel = new Panel(new FlowLayout());

        loadInventoryButton = new Button("Load Inventory");
        middlePanel.add(loadInventoryButton);

        showInventoryButton = new Button("Show Inventory");
        middlePanel.add(showInventoryButton);

        loadInventoryButton.addActionListener(e -> {
            jsonToObject.parseJson();
            System.out.println("Store information loaded: " + jsonToObject.storeInfo.getStore_name());
            System.out.println("Inventory loaded: " + jsonToObject.listOfProducts.size() + " items.");
            frame2.updateItemsTextArea();
        });

        showInventoryButton.addActionListener(e -> {
            Frame inventoryFrame = new Frame("Inventory");
            inventoryFrame.setSize(400, 600);
            inventoryFrame.setLayout(new BorderLayout());

            TextArea inventoryTextArea = new TextArea();
            inventoryTextArea.setEditable(false);

            inventoryTextArea.append("Store Name: " + jsonToObject.storeInfo.getStore_name() + '\n');
            inventoryTextArea.append("Phone Number: " + jsonToObject.storeInfo.getPhone_number() + '\n');
            inventoryTextArea.append("City: " + jsonToObject.storeInfo.getCity() + '\n');
            inventoryTextArea.append("State: " + jsonToObject.storeInfo.getState() + '\n');
            inventoryTextArea.append("Tax Percentage: " + jsonToObject.storeInfo.getTax_percentage() + '\n');

            inventoryTextArea.append("\n");

            for (Product product : jsonToObject.listOfProducts) {
                inventoryTextArea.append("Product Name: " + product.getProductName() + '\n');
                inventoryTextArea.append("Product Code: " + product.getProductCode() + '\n');
                inventoryTextArea.append("Price: " + product.getPrice() + '\n');
                inventoryTextArea.append("Description: " + product.getDescription() + '\n');
                inventoryTextArea.append("---------------" + '\n');
            }

            Button closeInventoryButton = new Button("Close");
            closeInventoryButton.addActionListener(e1 -> inventoryFrame.dispose());

            inventoryFrame.add(inventoryTextArea, BorderLayout.CENTER);
            inventoryFrame.add(closeInventoryButton, BorderLayout.SOUTH);
            inventoryFrame.setVisible(true);
        });

        return middlePanel;
    }

    private Panel createBottomPanel() {
        Panel bottomPanel = new Panel(new GridLayout(3, 1));

        Panel codePanel = new Panel(new FlowLayout());
        codePanel.add(new Label("Product Code:"));
        codeTextField = new TextField(10);
        codePanel.add(codeTextField);

        Panel quantityPanel = new Panel(new FlowLayout());
        quantityPanel.add(new Label("Quantity:"));
        quantityTextField = new TextField(10);
        quantityPanel.add(quantityTextField);

        bottomPanel.add(codePanel);
        bottomPanel.add(quantityPanel);

        Panel addRemoveButtonGroup = new Panel(new FlowLayout());
        addButton = new Button("Add");
        addRemoveButtonGroup.add(addButton);
        removeButton = new Button("Remove");
        addRemoveButtonGroup.add(removeButton);
        bottomPanel.add(addRemoveButtonGroup);

        addButton.addActionListener(e -> {
            try {
                String productCode = codeTextField.getText();
                int quantity = Integer.parseInt(quantityTextField.getText());

                Product product = jsonToObject.getProductByCode(productCode);
                if (product != null) {
                    product.setQuantity(product.getQuantity() + quantity);
                    System.out.println("Added product with code: " + productCode + ", Quantity: " + quantity);
                    frame2.updateItemsTextArea();
                } else {
                    System.out.println("ERROR: Product code not found: " + productCode);
                }
            } catch (NumberFormatException ex) {
                System.out.println("ERROR: Invalid quantity");
            }
        });

        removeButton.addActionListener(e -> {
            try {
                String productCode = codeTextField.getText();
                Product product = jsonToObject.getProductByCode(productCode);
                if (product != null) {
                    product.setQuantity(0);
                    System.out.println("Removed product with code: " + productCode);
                    frame2.updateItemsTextArea();
                } else {
                    System.out.println("ERROR: Product code not found: " + productCode);
                }
            } catch (Exception ex) {
                System.out.println("ERROR: Unexpected error occurred");
            }
        });

        return bottomPanel;
    }
}