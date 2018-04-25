package cicero.minhasfinancas.myfinancesplugin.model;

/**
 * Created by cicero.moura on 24/04/18.
 * Project: myfinancesplugin
 */

public class MainTransaction extends Transaction {

    private String category;
    private String subcategory;
    private String account;
    private boolean defaultAccount;

    public boolean isDefaultAccount() {
        return defaultAccount;
    }

    public Transaction setDefaultAccount(boolean defaultAccount) {
        this.defaultAccount = defaultAccount;
        return this;
    }


    public String getCategory() {
        return category;
    }

    public MainTransaction setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public MainTransaction setSubcategory(String subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public MainTransaction setAccount(String account) {
        this.account = account;
        return this;
    }
}
