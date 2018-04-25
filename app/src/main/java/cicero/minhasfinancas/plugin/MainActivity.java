package cicero.minhasfinancas.plugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

import cicero.minhasfinancas.myfinancesplugin.TransactionSender;
import cicero.minhasfinancas.myfinancesplugin.model.ExpenseTransaction;
import cicero.minhasfinancas.myfinancesplugin.model.IncomeTransaction;
import cicero.minhasfinancas.myfinancesplugin.model.MainTransaction;
import cicero.minhasfinancas.myfinancesplugin.model.Transaction;
import cicero.minhasfinancas.myfinancesplugin.model.TransferTransaction;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerTransactionType;
    private Spinner spinnerRecurrence;
    private AppCompatEditText txtCreditCard;
    private AppCompatEditText txtDescription;
    private AppCompatEditText txtAmount;
    private AppCompatEditText txtInstallments;
    private AppCompatEditText txtSourceAccount;
    private AppCompatEditText txtTargetAccount;
    private AppCompatEditText txtCategory;
    private AppCompatEditText txtSubcategory;
    private AppCompatEditText txtAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerTransactionType = findViewById(R.id.spinnerTransactionType);
        spinnerRecurrence = findViewById(R.id.spinnerRecurrence);
        txtCreditCard = findViewById(R.id.txtCreditCard);
        txtDescription = findViewById(R.id.txtDescription);
        txtAmount = findViewById(R.id.txtAmount);
        txtInstallments = findViewById(R.id.txtInstallments);
        txtSourceAccount = findViewById(R.id.txtSourceAccount);
        txtTargetAccount = findViewById(R.id.txtTargetAccount);
        txtCategory = findViewById(R.id.txtCategory);
        txtSubcategory = findViewById(R.id.txtSubcategory);
        txtAccount = findViewById(R.id.txtAccount);

        final View containerInstallments = findViewById(R.id.containerInstallments);
        final View containerCreditCard = findViewById(R.id.containerCreditCarad);
        final View containerTransfer = findViewById(R.id.containerTransfer);
        final View linearMainTransaction = findViewById(R.id.linearMainTransaction);

        containerCreditCard.setVisibility(View.GONE);
        containerTransfer.setVisibility(View.GONE);
        containerInstallments.setVisibility(View.GONE);

        spinnerRecurrence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 1:
                        containerInstallments.setVisibility(View.VISIBLE);

                        break;
                    default:
                        containerInstallments.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerTransactionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        containerCreditCard.setVisibility(View.GONE);
                        containerTransfer.setVisibility(View.GONE);
                        linearMainTransaction.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        containerCreditCard.setVisibility(View.VISIBLE);
                        linearMainTransaction.setVisibility(View.VISIBLE);
                        containerTransfer.setVisibility(View.GONE);
                        break;
                    case 2:
                        containerCreditCard.setVisibility(View.GONE);
                        containerTransfer.setVisibility(View.VISIBLE);
                        linearMainTransaction.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send) {
            sendTransaction();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendTransaction() {

        try {

            Transaction transaction;
            switch (spinnerTransactionType.getSelectedItemPosition()) {
                case 0:
                    transaction = new IncomeTransaction();
                    break;
                case 1:
                    transaction = new ExpenseTransaction();
                    break;
                default:
                    transaction = new TransferTransaction();
                    break;
            }

            transaction.setDescription(txtDescription.getText().toString());
            transaction.setAmount(Double.parseDouble(txtAmount.getText().toString()));
            transaction.setConfirmationDate(new Date());
            transaction.setCreationDate(new Date());
            transaction.setDueDate(new Date());

            if (transaction instanceof TransferTransaction) {
                TransferTransaction transferTransaction = (TransferTransaction) transaction;
                transferTransaction.setSourceAccount(txtSourceAccount.getText().toString());
                transferTransaction.setTargetAccount(txtTargetAccount.getText().toString());
            } else {

                MainTransaction mainTransaction = (MainTransaction) transaction;
                mainTransaction.setAccount(txtAccount.getText().toString());
                mainTransaction.setCategory(txtCategory.getText().toString());
                mainTransaction.setSubcategory(txtSubcategory.getText().toString());

                if (transaction instanceof ExpenseTransaction) {
                    ((ExpenseTransaction) transaction).setCreditCard(txtCreditCard.getText().toString());
                }
            }


            switch (spinnerRecurrence.getSelectedItemPosition()) {
                case 1:
                    transaction.setRecurrence(Transaction.TransactionRecurrence.INSTALLMENTS);
                    transaction.setInstallments(Integer.parseInt(txtInstallments.getText().toString()));
                    break;
                case 2:
                    transaction.setRecurrence(Transaction.TransactionRecurrence.RECURRING);
                    break;
                default:
                    transaction.setRecurrence(Transaction.TransactionRecurrence.NONE);
                    break;
            }

            new TransactionSender.Builder(this)
                    .notification(true)
                    //it only makes sense to cicero
                    .debug(true)
                    .transaction(transaction)
                    .resultReceiver(MfResultBroadcastReceiver.class)
                    .build()
                    .send();

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
