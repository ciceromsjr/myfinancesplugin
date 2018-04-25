package cicero.minhasfinancas.myfinancesplugin.model;

/**
 * Created by cicero.moura on 24/04/18.
 * Project: myfinancesplugin
 */

public class ExpenseTransaction extends MainTransaction {

    private String creditCard;

    public ExpenseTransaction() {
        setTransactionType(TransactionType.EXPENSE);
    }


    public String getCreditCard() {
        return creditCard;
    }

    public ExpenseTransaction setCreditCard(String creditCard) {
        this.creditCard = creditCard;
        return this;
    }
}
