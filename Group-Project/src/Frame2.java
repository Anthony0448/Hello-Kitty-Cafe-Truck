import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Frame2 {

    public static void main(String[] args) {
        Frame frame = new Frame("Invoice System");
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(2, 1));

        // Panel 1: Items and quantities panel
        Panel itemsPanel = new Panel(new BorderLayout());
        itemsPanel.add(new Label("Items and Quantities"), BorderLayout.NORTH);
        TextArea itemsTextArea = new TextArea();
        itemsPanel.add(itemsTextArea, BorderLayout.CENTER);
        frame.add(itemsPanel);

        // Panel 2: Summary and controls panel
        Panel summaryPanel = new Panel(new GridLayout(2, 1));

        // Sub-panel for tax, discount, and totals
        Panel taxDiscountPanel = new Panel(new GridLayout(5, 2));
        taxDiscountPanel.add(new Label("Sales Tax (%):"));
        TextField salesTaxField = new TextField("9.38");
        taxDiscountPanel.add(salesTaxField);

        taxDiscountPanel.add(new Label("City and State:"));
        TextField cityStateField = new TextField("San Jose, California");
        taxDiscountPanel.add(cityStateField);

        taxDiscountPanel.add(new Label("Discount (%):"));
        TextField discountField = new TextField("5");
        taxDiscountPanel.add(discountField);

        Checkbox discountCheckbox = new Checkbox("Apply Discount");
        taxDiscountPanel.add(discountCheckbox);
        taxDiscountPanel.add(new Label(""));

        taxDiscountPanel.add(new Label("Total Before Tax/Discount:"));
        TextField totalBeforeField = new TextField("0.00");
        totalBeforeField.setEditable(false);
        taxDiscountPanel.add(totalBeforeField);

        taxDiscountPanel.add(new Label("Total with Tax:"));
        TextField totalWithTaxField = new TextField("0.00");
        totalWithTaxField.setEditable(false);
        taxDiscountPanel.add(totalWithTaxField);

        taxDiscountPanel.add(new Label("Discount Applied:"));
        TextField discountAppliedField = new TextField("0.00");
        discountAppliedField.setEditable(false);
        taxDiscountPanel.add(discountAppliedField);

        taxDiscountPanel.add(new Label("Grand Total:"));
        TextField grandTotalField = new TextField("0.00");
        grandTotalField.setEditable(false);
        taxDiscountPanel.add(grandTotalField);

        summaryPanel.add(taxDiscountPanel);

        // Sub-panel for Print Receipt button
        Panel buttonPanel = new Panel();
        Button printReceiptButton = new Button("Print Receipt");
        buttonPanel.add(printReceiptButton);
        summaryPanel.add(buttonPanel);

        frame.add(summaryPanel);

        // Receipt Frame Logic
        printReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame receiptFrame = new Frame("Receipt");
                receiptFrame.setSize(400, 500);
                receiptFrame.setLayout(new BorderLayout());

                TextArea receiptArea = new TextArea();
                receiptArea.setEditable(false);

                // Create the receipt content
                StringBuilder receiptContent = new StringBuilder();
                receiptContent.append("Hello Kitty Stationary Shop\n");
                receiptContent.append("San Jose, California\n");
                receiptContent.append("Phone: 123-456-7890\n");
                receiptContent.append("Date: " + new Date().toString() + "\n\n");

                receiptContent.append("Items:\n");
                receiptContent.append(itemsTextArea.getText()).append("\n\n");

                receiptContent.append("Total Before Tax/Discount: " + totalBeforeField.getText() + "\n");
                receiptContent.append("Total with Tax: " + totalWithTaxField.getText() + "\n");
                receiptContent.append("Discount Applied: " + discountAppliedField.getText() + "\n");
                receiptContent.append("Grand Total: " + grandTotalField.getText() + "\n\n");

                receiptContent.append("Thank you for shopping with us!\n");

                receiptArea.setText(receiptContent.toString());
                receiptFrame.add(receiptArea, BorderLayout.CENTER);

                receiptFrame.setVisible(true);
            }
        });

        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}


