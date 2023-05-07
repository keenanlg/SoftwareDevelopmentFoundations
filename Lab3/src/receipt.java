
/**
 *
 * <p>Consider the class Receipt</p>
 *
 * <p>This class is designed to track the cost for each item a client has bought and provides a method to compute the total amount they must pay.
 </p>
 *
 * @author Ryan Gibson
 * @author Diego Velez
 * @author Keenan Grant
 * @author Mason Tate
 *
 * @version 1.0
 * @invariant  taxRate {@code >= } 0 AND taxableSubtotals {@code>= } 0 AND nonTaxableSubtotals {@code>= } 0
 *
 */

public int class Receipt{

    private  double taxableSubtotals;
    private double nonTaxableSubtotals;
    private double taxRate;
    private double totalSubtotal;


    /**
     *
     *@param taxRate The tax rate applied.
     *@pre taxRate {@code >} 0
     *@post taxRate = #taxRate AND taxableSubtotal = 0 AND nonTaxableSubtotal = 0
     */

    public Receipt(double taxRate){

    }

    /**
     *add an item(s) to the appropriate subtotal depending on whether the item is
     taxable or not.
     *@param cost Cost of the item.
     *@param quantity Quantity of the item.
     *@param isTaxable If the item is taxable or not.
     *@return void
     *@pre taxRate {@code >} 0
     *@post taxableSubtotals = #taxableSubtotals + ((taxRate + 1) * cost * quantity) iff isTaxable = true AND nonTaxableSubtotal = #nonTaxableSubtotal + (cost * quantity) iff isTaxable = false AND taxRate = #taxRate

     */

    public void addToReceipt(double  cost,  int  quantity,  boolean isTaxable){

    }

    /**
     *Getter function that gets the current subtotal of the non-taxable items.
     *@return the current subtotal of the nontaxable items.
     *@pre
     * 	None
     *@post
     * 	nonTaxableSubtotals = #nonTaxableSubtotals
     */

    public double getNonTaxableSubtotal(){

    }

    /**
     *Getter function that gets the current subtotal of the items that
     are subject to tax.
     *@return the current subtotal of the items that are subject to tax.
     *@pre
    NONE
     *@post
    taxableSubtotals = #taxableSubtotals
     */

    public double getTaxableSubtotal(){

    }
    /**
     *Getter function that gets the tax rate that will be applied.
     *@return The taxRate that will be applied to all taxable items.
     *@pre
    NONE
     *@post
    taxRate = #taxRate
     */

    public double getTaxRate(){

    }

    /**
     *computes the total amount charged for all items,
     including any tax that needs to be applied
     *@return the total amount for all charged items, including tax
     *@pre
    NONE
     *@post
     *	totalSubtotal = nonTaxableSubtotals + taxableSubtotals AND
     *	taxableSubtotals = #taxableSubtotals AND
     *	nonTaxableSubtotals = #nonTaxableSubtotals
     */
    public  double  computeTotal(){

    }
}

• public double getNonTaxableSubtotal(): returns the current subtotal of the non-
        taxable items.

        • public double getTaxableSubtotal(): returns the current subtotal of the items that
        are subject to tax.

        • public double getTaxRate(): returns the tax rate that will be applied

        • public  double  computeTotal():  returns  the  total  amount  charged  for  all  items,
        including any tax that needs to be applied.




