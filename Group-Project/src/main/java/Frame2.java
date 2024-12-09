import java.awt.*;
import java.awt.event.*;

public class Frame2 extends Frame {
    // The button and text fields/area are now all class variables, so that other methods can access them if needed
    Button printReceiptButton;
    TextArea itemsTextArea;
    TextField totalBeforeField;
    TextField totalWithTaxField;
    TextField discountAppliedField;
    TextField grandTotalField;
    private TextField salesTaxField;
    private TextField discountField;
    private Checkbox discountCheckbox;

    protected JsonToObject jsonToObject;

    public Frame2(JsonToObject jsonToObject) {
        this.jsonToObject = jsonToObject;

        setLayout(new GridLayout(2, 1));

        add(createItemsPanel());
        add(createSummaryPanel());

        setTitle("Invoice");
        setSize(460, 500);
        setVisible(true);

        // Stops program when closing Frame
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private Panel createItemsPanel() {
        Panel itemsPanel = new Panel(new BorderLayout());
        itemsPanel.add(new Label("Items and Quantities"), BorderLayout.NORTH);

        itemsTextArea = new TextArea();
        itemsTextArea.setEditable(false);
        itemsPanel.add(itemsTextArea, BorderLayout.CENTER);

        return itemsPanel;
    }

    public void updateItemsTextArea() {
        // Clear the existing text to essentially repaint the text area
        itemsTextArea.setText("");

        // Checks the quantity of each item in the listOfProducts to add to invoice if > 0
        for (Product product : jsonToObject.listOfProducts) {
            // If an item has been added then the quantity is greater than 0
            if (product.getQuantity() > 0) {
                itemsTextArea.append("Item: " + product.getProductName() + ", Quantity: " + product.getQuantity() + "\n");
            }
        }

        recalculateTotals();
    }

    private Panel createSummaryPanel() {
        Panel summaryPanel = new Panel(new GridLayout(2, 1));
        // Sub-panel for tax, discount, and totals
        Panel taxDiscountPanel = new Panel(new GridLayout(5, 2));

        taxDiscountPanel.add(new Label("Sales Tax (%):"));
        salesTaxField = new TextField("9.38");
        taxDiscountPanel.add(salesTaxField);

        taxDiscountPanel.add(new Label("Discount (%):"));
        discountField = new TextField("5");
        taxDiscountPanel.add(discountField);

        discountCheckbox = new Checkbox("Apply Discount");
        taxDiscountPanel.add(discountCheckbox);
        taxDiscountPanel.add(new Label(""));

        taxDiscountPanel.add(new Label("Total Before Tax/Discount:"));
        totalBeforeField = new TextField("0.00");
        totalBeforeField.setEditable(false);
        taxDiscountPanel.add(totalBeforeField);

        taxDiscountPanel.add(new Label("Total with Tax:"));
        totalWithTaxField = new TextField("0.00");
        totalWithTaxField.setEditable(false);
        taxDiscountPanel.add(totalWithTaxField);

        taxDiscountPanel.add(new Label("Discount Applied:"));
        discountAppliedField = new TextField("0.00");
        discountAppliedField.setEditable(false);
        taxDiscountPanel.add(discountAppliedField);

        taxDiscountPanel.add(new Label("Grand Total:"));
        grandTotalField = new TextField("0.00");
        grandTotalField.setEditable(false);
        taxDiscountPanel.add(grandTotalField);

        summaryPanel.add(taxDiscountPanel);

        Panel buttonPanel = new Panel();
        printReceiptButton = new Button("Print Receipt");
        buttonPanel.add(printReceiptButton);
        summaryPanel.add(buttonPanel);

        // Action listeners call local methods for clarity
        printReceiptButton.addActionListener(e -> printReceipt());
        discountCheckbox.addItemListener(e -> recalculateTotals());
        salesTaxField.addTextListener(e -> recalculateTotals());
        discountField.addTextListener(e -> recalculateTotals());

        return summaryPanel;
    }

    private void recalculateTotals() {
        double totalBeforeTax = 0.0;

        // get item prices from the JSON
        for (Product product : jsonToObject.listOfProducts) {
            totalBeforeTax += product.getPrice() * product.getQuantity();
        }

        double taxRate = Double.parseDouble(salesTaxField.getText()) / 100.0;
        double discountRate = discountCheckbox.getState() ? Double.parseDouble(discountField.getText()) / 100.0 : 0;

        double totalWithTax = totalBeforeTax * (1 + taxRate);
        double discountApplied = totalWithTax * discountRate;
        double grandTotal = totalWithTax - discountApplied;

        // Update text fields with values
        totalBeforeField.setText(String.format("%.2f", totalBeforeTax));
        totalWithTaxField.setText(String.format("%.2f", totalWithTax));
        discountAppliedField.setText(String.format("%.2f", discountApplied));
        grandTotalField.setText(String.format("%.2f", grandTotal));
    }

    private void printReceipt() {
        Frame receiptFrame = new Frame("Receipt");
        receiptFrame.setSize(400, 500);
        receiptFrame.setLayout(new BorderLayout());

        TextArea receiptArea = new TextArea();
        receiptArea.setEditable(false);

        StringBuilder receiptContent = new StringBuilder();
        receiptContent.append("Store Name: " + jsonToObject.storeInfo.getStore_name() + "\n");
        receiptContent.append("Phone Number: " + jsonToObject.storeInfo.getPhone_number() + "\n");
        receiptContent.append("City: " + jsonToObject.storeInfo.getCity() + "\n");
        receiptContent.append("State: " + jsonToObject.storeInfo.getState() + "\n");
        receiptContent.append("Tax Percentage: " + salesTaxField.getText() + "\n\n");

        receiptContent.append("Items:\n");
        for (Product product : jsonToObject.listOfProducts) {
            if (product.getQuantity() > 0) {
                receiptContent.append("- " + product.getProductName() + ": " + product.getQuantity() + " @ $" + product.getPrice() + " each\n");
            }
        }

        receiptContent.append("\nTotal Before Tax/Discount: " + totalBeforeField.getText() + "\n");
        receiptContent.append("Total with Tax: " + totalWithTaxField.getText() + "\n");
        receiptContent.append("Discount Applied: " + discountAppliedField.getText() + "\n");
        receiptContent.append("Grand Total: " + grandTotalField.getText() + "\n\n");

        receiptContent.append("Thank you for shopping with us!\n");

        receiptArea.setText(receiptContent.toString());
        receiptFrame.add(receiptArea, BorderLayout.CENTER);

        receiptFrame.setVisible(true);
        receiptFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                receiptFrame.dispose();
            }
        });
    }
}
