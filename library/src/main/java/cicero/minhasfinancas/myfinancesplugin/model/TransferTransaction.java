package cicero.minhasfinancas.myfinancesplugin.model;

/**
 * Created by cicero.moura on 24/04/18.
 * Project: myfinancesplugin
 */

public class TransferTransaction extends Transaction {

    private String sourceAccount;
    private String targetAccount;

    public TransferTransaction() {
        setTransactionType(TransactionType.TRANSFER);
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }
}
