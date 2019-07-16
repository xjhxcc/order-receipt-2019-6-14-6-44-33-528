package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;
    private final double TAX_PERCENT =.10;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        printHeaders(output);
        printNameAndAddress(output);
        printLineItems(output);
        return output.toString();
    }

    private void printLineItems(StringBuilder output) {
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            printLineItem(output, lineItem);
            double salesTax = lineItem.caculateTotalAmount() * TAX_PERCENT;
            totSalesTx += salesTax;
            tot += lineItem.caculateTotalAmount() + salesTax;
        }
        printSalesTaxAndTotalAmount(output,totSalesTx,tot);
    }

    private void printLineItem(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription()+'\t'+lineItem.getPrice()+'\t'+lineItem.getQuantity()+'\t'+lineItem.caculateTotalAmount()+'\n');
    }

    private void printHeaders(StringBuilder output) {
        output.append("======Printing Orders======\n");
    }

    private void printNameAndAddress(StringBuilder output){
        output.append(order.getCustomerName()+order.getCustomerAddress());
    }
    private void printSalesTaxAndTotalAmount(StringBuilder output, double totSalesTx, double tot) {
        output.append("Sales Tax").append('\t').append(totSalesTx);
        output.append("Total Amount").append('\t').append(tot);
    }
}