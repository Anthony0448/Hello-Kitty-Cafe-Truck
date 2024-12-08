import java.awt.*;
import java.awt.event.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Frame1_final extends Frame {
    private List<Product> inventory = new ArrayList<>(); // List to hold inventory data
    private JsonToObject jsonToObject = new JsonToObject(); // Load JSON data

    // Top panel components
    protected TextField firstNameTextField;
    protected TextField lastNameTextField;
    protected Button startShiftButton;
    protected Button endShiftButton;
    protected TextField startTime;
    protected TextField endTime;

    protected String firstName;
    protected String lastName;

    // Middle panel components
    protected Button loadInventoryButton;

    // Bottom panel components
    protected TextField codeTextField;
    protected TextField quantityTextField;
    protected Button addButton;

    public Frame1_final() {
        // Set up frame
        setTitle("Inventory Manager");
        setLayout(new BorderLayout());
        setSize(400, 500);

        // Add Panels
        add(createTopPanel(), BorderLayout.NORTH);
        add(createMiddlePanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        // Window close action
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private Panel createTopPanel() {
        Panel topPanel = new Panel(new GridLayout(3, 1));

        // Name input
        Panel namePanel = new Panel(new GridLayout(2, 2));
        namePanel.add(new Label("First Name:"));
        firstNameTextField = new TextField(10);
        namePanel.add(firstNameTextField);
        namePanel.add(new Label("Last Name:"));
        lastNameTextField = new TextField(10);
        namePanel.add(lastNameTextField);

        // Shift buttons
        Panel shiftPanel = new Panel(new FlowLayout());
        startShiftButton = new Button("Start Shift");
        endShiftButton = new Button("End Shift");
        shiftPanel.add(startShiftButton);
        shiftPanel.add(endShiftButton);

        // Time display
        Panel timePanel = new Panel(new GridLayout(2, 2));
        timePanel.add(new Label("Start Time:"));
        startTime = new TextField(20);
        timePanel.add(startTime);
        timePanel.add(new Label("End Time:"));
        endTime = new TextField(20);
        timePanel.add(endTime);

        // Button actions
        startShiftButton.addActionListener(e -> {
            firstName = firstNameTextField.getText();
            lastName = lastNameTextField.getText();
            startTime.setText(ZonedDateTime.now().toString());
            System.out.println("Shift started for: " + firstName + " " + lastName);
        });

        endShiftButton.addActionListener(e -> endTime.setText(ZonedDateTime.now().toString()));

        // Add to top panel
        topPanel.add(namePanel);
        topPanel.add(shiftPanel);
        topPanel.add(timePanel);

        return topPanel;
    }

    private Panel createMiddlePanel() {
        Panel middlePanel = new Panel(new FlowLayout());
        Button addInventoryButton;
        Button showInventoryButton;

        addInventoryButton = new Button("Add");
        showInventoryButton = new Button("Show");
        middlePanel.add(addInventoryButton);
        middlePanel.add(showInventoryButton);


        // Load inventory action
        addInventoryButton.addActionListener(e -> {
            inventory.clear();
            inventory.addAll(jsonToObject.getProducts());
            System.out.println("Inventory loaded: " + inventory.size() + " items.");
        });

        //show the inventory 
        showInventoryButton.addActionListener(e -> {
            System.out.println("Current Inventory:");
            for (Product product : inventory) {
                System.out.println(product.getCode() + " - " + product.getName() + ": " + product.getQuantity());
            }
        });

        return middlePanel;
    }

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
        
        return bottomPanel;
    }
    private Product findProductByCode(String code) {
        for (Product product : inventory) {
            if (product.getCode().equals(code)) {
                return product;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new Frame1_final();
    }
}

// Dummy Product class
class Product {
    private String code;
    private String name;
    private int quantity;

    public Product(String code, String name, int quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

// Dummy JsonToObject class
class JsonToObject {
    List<Product> products;
    public List<Product> getProducts() {
        return products;
    }
}
