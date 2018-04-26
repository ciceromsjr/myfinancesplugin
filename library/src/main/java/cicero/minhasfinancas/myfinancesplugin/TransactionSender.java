package cicero.minhasfinancas.myfinancesplugin;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import cicero.minhasfinancas.myfinancesplugin.model.Transaction;

/**
 * Created by cicero.moura on 24/04/18.
 * Project: myfinancesplugin
 */

public final class TransactionSender {

    private Context context;
    private boolean createDependenciesIfNeeded;
    private boolean notification;
    private ArrayList<Transaction> transactions;
    private Class<?> resultReceiver;

    public TransactionSender(Context context, ArrayList<Transaction> transactions) {
        this(context, null, transactions, false, false);
    }

    public TransactionSender(Context context, ArrayList<Transaction> transactions, boolean createDependenciesIfNeeded) {
        this(context, null, transactions, createDependenciesIfNeeded, false);
    }

    public TransactionSender(Context context, Class<?> resultReceiver, ArrayList<Transaction> transactions, boolean createDependenciesIfNeeded, boolean notification) {

        if (context == null) {
            throw new RuntimeException("Context cannot be null.");
        }


        if (transactions == null) {
            throw new RuntimeException("Transactions cannot be null.");
        }

        if (transactions.isEmpty()) {
            throw new RuntimeException("Transactions collection is empty.");
        }

        this.context = context;
        this.resultReceiver = resultReceiver;
        this.createDependenciesIfNeeded = createDependenciesIfNeeded;
        this.notification = notification;
        this.transactions = transactions;
    }

    public void send() {

        String json;
        String keyData;
        Gson gson = new GsonBuilder().create();

        if (transactions.size() == 1) {
            json = gson.toJson(transactions.get(0));
            keyData = "transaction";
        } else {
            json = gson.toJson(transactions);
            keyData = "transactions";
        }

        Intent intent = new Intent();
        intent.setAction("cicero.minhasfinancas.action.ADD_TRANSACTION_SEND");
        intent.putExtra("package_sender", context.getPackageName());

        if (resultReceiver != null) {
            intent.putExtra("result_receiver", resultReceiver.getCanonicalName());
        } else {
            intent.putExtra("result_receiver", "");
        }
        intent.setComponent(new ComponentName("cicero.minhasfinancas", "cicero.minhasfinancas.broadcast.AddTransactionMFReceiver"));
        intent.putExtra(keyData, json);
        intent.putExtra("notification", notification);
        intent.putExtra("createDependenciesIfNeeded", createDependenciesIfNeeded);

        //sends the broadcast
        context.sendBroadcast(intent);
    }

    public final static class Builder {

        private Context context;
        private Class<?> resultReceiver;
        private ArrayList<Transaction> transactions;
        private boolean notification;
        private boolean createDependenciesIfNeeded;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * It will send just one transaction
         */
        public Builder transaction(Transaction transaction) {
            this.transactions = new ArrayList<>();
            this.transactions.add(transaction);
            return this;
        }

        /**
         * It will send just a transaction collection.
         * It must not be null or empty.
         */
        public Builder transactions(ArrayList<Transaction> transactions) {
            this.transactions = new ArrayList<>(transactions);
            return this;
        }

        public Builder transactions(Transaction... transactions) {
            this.transactions = new ArrayList<>(Arrays.asList(transactions));
            return this;
        }

        /**
         * If not null, this receiver will be the only one which will receive the result
         * otherwise any other receiver registered to the result action will receive the result.
         */
        public <T extends BroadcastReceiver> Builder resultReceiver(Class<T> resultReceiver) {
            this.resultReceiver = resultReceiver;
            return this;
        }

        /**
         * If you would like to see a notification when a transaction is added
         * It works only for single expense or income
         */
        public Builder notification(boolean notification) {
            this.notification = notification;
            return this;
        }

        /**
         * It means that if dependencies such as account, category/subcategory, and credit card
         * are not found, My Finances will create it.
         */
        public Builder createDependeciesIfNeeded(boolean createDependenciesIfNeeded) {
            this.createDependenciesIfNeeded = createDependenciesIfNeeded;
            return this;
        }

        public TransactionSender build() {
            return new TransactionSender(context, resultReceiver, transactions, createDependenciesIfNeeded, notification);
        }
    }
}
