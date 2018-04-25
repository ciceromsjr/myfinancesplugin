package cicero.minhasfinancas.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by cicero.moura on 23/04/18.
 * Project: AddTransaction
 */

public class MfResultBroadcastReceiver extends BroadcastReceiver {

    public static final String ADD_TRANSACTION_RESULT = "cicero.minhasfinancas.action.ADD_TRANSACTION_RESULT";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && intent.getAction().equals(ADD_TRANSACTION_RESULT)) {
            String result = intent.getStringExtra("result");
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }
}
