package cicero.minhasfinancas.myfinancesplugin.model;

/**
 * Created by cicero.moura on 24/04/18.
 * Project: myfinancesplugin
 */

public class IncomeTransaction extends MainTransaction {

    public IncomeTransaction() {
        setTransactionType(TransactionType.INCOME);
    }
}
