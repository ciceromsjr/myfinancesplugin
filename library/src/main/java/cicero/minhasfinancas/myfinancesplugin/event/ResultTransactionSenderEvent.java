package cicero.minhasfinancas.myfinancesplugin.event;

/**
 * Created by cicero.moura on 24/04/18.
 * Project: myfinancesplugin
 */

public class ResultTransactionSenderEvent {

    public final String message;

    public ResultTransactionSenderEvent(String message) {
        this.message = message;
    }
}
