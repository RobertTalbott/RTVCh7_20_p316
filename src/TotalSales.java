//////////////////////////////////////////////////////////
// Exercise 7.20
// Use 2-Dim Array.
// 4 Sales Persons w/ each person submitting 0-5 slips/day.
// 5 Products
//
// Store Totals for EACH salesperson and by product
// data in 2-Dim array "sales."
// 1st DIM product
// 2nd DIM salesperson
//////////////////////////////////////////////////////////
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

//////////////////////////////////////////////////////////
// Problem 7.20 on page 316
public class TotalSales {
    // Constants.
    final static int STOP_EXECUTION_FLAG   = -1;   // Sentinel to end execution.
    final static int PRODUCT_TOTAL         = 6;    // 5 products -- include/ignore zero index.
    final static int NUMBER_OF_SALESPEOPLE = 5;    // 4 salespeople -- include/ignore zero index.

    public static void main(String[] args){
        int         salesPersonID               = 0;    // 0 is ignored.
        int         productID                   = 0;    // 0 is ignored.
        double      totalProductSalesForTheDay  = 0.0;
        double[][]  sales                       = new double[PRODUCT_TOTAL][NUMBER_OF_SALESPEOPLE];

        // Open Scanner & Get ID from user.
        Scanner userInput = new Scanner(System.in);
        salesPersonID = getSalesPersonID(userInput);

        // Loop while the user inputs slip information.
        while( salesPersonID != STOP_EXECUTION_FLAG ){
            // Get slip info from the user.
            productID = getProductID(userInput);
            totalProductSalesForTheDay = getTotalProductSalesForDay(userInput);
            sales[productID][salesPersonID] += totalProductSalesForTheDay;  // Sum the daily totals.
            salesPersonID = getSalesPersonID(userInput);
        }

        // Print the sales array.
        System.out.printf("%20s%20s%20s%20s%20s%20s",
                          "Product#",
                          "SalesPerson 1",
                          "SalesPerson 2",
                          "SalesPerson 3",
                          "SalesPerson 4",
                          "Total"
                          );
        for(productID = 1; productID < PRODUCT_TOTAL; productID++){
            System.out.printf("%n%20d", productID);
            double productTotal = 0.0; // Init.
            for(salesPersonID = 1; salesPersonID < NUMBER_OF_SALESPEOPLE; salesPersonID++){
                System.out.printf("%20.2f", sales[productID][salesPersonID]);
                productTotal += sales[productID][salesPersonID];
            }

            // Print the total for the row.
            System.out.printf("%20.2f", productTotal);
        }

        // Print the LAST totals row
        System.out.printf("%n%20s","Total");
        double grandTotal = 0.0; // Init.
        for(salesPersonID = 1; salesPersonID < NUMBER_OF_SALESPEOPLE; salesPersonID++){
            double salesPersonTotal = 0.0; // Init.
            for( productID = 1; productID < PRODUCT_TOTAL; productID++){
                salesPersonTotal += sales[productID][salesPersonID];
            }
            System.out.printf("%20.2f", salesPersonTotal);
            grandTotal += salesPersonTotal;
        }

        // Print the grand total.
        System.out.printf("%20.2f",  grandTotal);

    } // END: Main

    // This method gets a valid salesperson ID from the user (i.e., slip).
    private static int getSalesPersonID(Scanner userInput){
        int salesPersonID = 0;

        // Loop until you get a valid response.
        while(salesPersonID != STOP_EXECUTION_FLAG && (salesPersonID < 1 || salesPersonID > 5)){

            // Get the ID from the user.
            System.out.print("Enter Salesperson ID# (-1 ends program): ");
            salesPersonID = userInput.nextInt();

            // Validate Input.
            if( salesPersonID != STOP_EXECUTION_FLAG && (salesPersonID < 1 || salesPersonID > 5 )){
                System.out.printf("Invalid entry.%n");
            }
        }

        return salesPersonID;
    }

    // This method gets a valid product ID from the user.
    private static int getProductID(Scanner userInput){
        int productID = 0;
        // Get product #
        System.out.print("Enter Product ID#: ");
        productID = userInput.nextInt();

        // Validate Input
        while(productID < 1 || productID > 5){
            System.out.print("Invalid entry.  Please re-enter:");
            productID = userInput.nextInt();
        }

        return productID;
    }

    // This method gets total day's sales for the product.
    private static double getTotalProductSalesForDay(Scanner userInput){
        double totalProductSalesForTheDay = 0.0;  // Init.

        // Get total $ value of the product sold that day.
        System.out.print("Enter total dollars for that day for the Product ID#: ");
        totalProductSalesForTheDay = userInput.nextDouble();

        // Validate Input
        while (totalProductSalesForTheDay < 0.0){
            System.out.print("Invalid entry.  Please re-enter: ");
            totalProductSalesForTheDay = userInput.nextDouble();
        }

        return totalProductSalesForTheDay;
    }
} // End of Class
