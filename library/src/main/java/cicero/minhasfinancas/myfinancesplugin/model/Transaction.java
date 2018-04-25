package cicero.minhasfinancas.myfinancesplugin.model;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by cicero.moura on 23/04/18.
 * Project: MinhasFinancas
 */
public class Transaction implements Serializable {

    private String description;
    private double amount;
    private int installments;
    private Date dueDate;
    private Date confirmationDate;
    private Date creationDate;

    // N = None
    // I = Installments
    // R = Recurring
    private TransactionRecurrence recurrence;

    // T = TransferTransaction
    // I = IncomeTransaction
    // E = ExpenseTransaction
    private TransactionType transactionType;


    public String getDescription() {
        return description;
    }

    public Transaction setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public int getInstallments() {
        return installments;
    }

    public Transaction setInstallments(int installments) {
        this.installments = installments;
        return this;
    }

    public Date getDueDate() {
        return dueDate != null ? dueDate : new Date();
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getConfirmationDate() {
        return confirmationDate != null ? confirmationDate : new Date();
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public Date getCreationDate() {
        return creationDate != null ? creationDate : new Date();
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public TransactionRecurrence getRecurrence() {
        return recurrence == null ? TransactionRecurrence.NONE : recurrence;
    }

    public void setRecurrence(TransactionRecurrence recurrence) {
        this.recurrence = recurrence;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public enum TransactionType implements Serializable {

        TRANSFER("T"),
        INCOME("I"),
        EXPENSE("E");

        private String type;

        TransactionType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum TransactionRecurrence implements Serializable {

        NONE("N"),
        INSTALLMENTS("I"),
        RECURRING("R");

        private String recurrenceId;

        TransactionRecurrence(String recurrenceId) {
            this.recurrenceId = recurrenceId;
        }

        public String getRecurrenceId() {
            return recurrenceId;
        }
    }
}

