[![Release](https://jitpack.io/v/ciceromsjr/myfinancesplugin.svg)](https://jitpack.io/#ciceromsjr/myfinancesplugin)
[![API](https://img.shields.io/badge/API-15%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=15)

# My Finances Official Plugin
Plugin for My Finances Official app.
This plugin makes it possible  to you to develop mechanisms to import transactions to My Finances app.
If you are Brazillian, you can develop a special way to import a SMS or bank notification for example.

**It works with My Finances app version 4.4.5 or above.**

First Of All
------

You have to install the My Finances Official app to test this library.
It is available on Google Play Store.

**Download My Finances**

[![PlayStore](https://play.google.com/intl/en_us/badges/images/badge_new.png)](https://play.google.com/store/apps/details?id=cicero.minhasfinancas)

**1. Install [My Finances Offcial](https://play.google.com/store/apps/details?id=cicero.minhasfinancas) app**

**2. Go to Settings > Security > Enable MF Plugin**

Features
-----
- Add transactions (incomes, expenses and transfers) to the app.
 
Usage
-----

In order to use the library, there are 2 different options:

**1. Gradle dependency** (recommended)

  -  Add the following to your project level `build.gradle`:
 
```gradle
allprojects {
	repositories {
		maven { url "https://jitpack.io" }
	}
}
```
  -  Add this to your app `build.gradle`:
 
```gradle
dependencies {
	implementation 'com.github.ciceromsjr:myfinancesplugin:v0.0.2'
}
```
	
**2. clone whole repository** (not recommended)

ADDING TRANSACTIONS
-----

You can add three transaction types:

- Income
- Expense
- Transfer

All dependecies such as account, category/subcategory, and credid card are found by description
(credit cards can also be found by its 4 final digits registered on MY Finances app).
It means that if a dependency is not found you will get an error.
Nevertheless, you can set the ```createDependenciesIfNeeded``` transaction property as true tells My Finances to create the transaction dependencies if they are not found.  
**It does not work for credit cards because credit cards needs more information to be created. You must do it on the My Finances app**

**ADDING AN INCOME**

```
IncomeTransaction income = new IncomeTransaction();
income.setDescription("Sale Bonus");
income.setAmount(50d);
income.setConfirmationDate(new Date());
income.setCreationDate(new Date());
income.setDueDate(new Date());
income.setAccount("My Account");
income.setCategory("Sales");
income.setSubcategory("Others");
income.setRecurrence(Transaction.TransactionRecurrence.NONE); //default

new TransactionSender.Builder(this)
                    .notification(true) //only for incomes and expenses
                    .transaction(income)
                    .build()
                    .send();

```

**ADDING AN EXPENSE**

```
ExpenseTransaction expense = new ExpenseTransaction();
expense.setDescription("Nike Shoes");
expense.setAmount(90d);
expense.setConfirmationDate(new Date());
expense.setCreationDate(new Date());
expense.setDueDate(new Date());
expense.setAccount("My Account");
expense.setCategory("Chothing");
expense.setSubcategory("Shoes");
//it will create three expenses of $30
//if the due date is old the expense will be marked as paid event if you do not set confirmation date
expense.setRecurrence(Transaction.TransactionRecurrence.INSTALLMENTS);
expense.setInstallmentsCount(3); 

new TransactionSender.Builder(this)
                    .notification(true)
                    .transaction(expense)
                    .build()
                    .send();

```

**ADDING A TRANSFER BETWEEN ACCOUNTS**

```
TransferTransaction transfer = new TransferTransaction();
transfer.setDescription("Got some money at ATM");
transfer.setAmount(100d);
transfer.setConfirmationDate(new Date());
transfer.setCreationDate(new Date());
transfer.setDueDate(new Date());
transfer.setSourceAccount("My Account");
transfer.setTargetAccount("My Wallet");

new TransactionSender.Builder(this)
                    .transaction(transfer)
                    .build()
                    .send();

```

**ADDING MULTIPLE TRANSACTIONS**

You can also add as much transactions as you want to.

```

new TransactionSender.Builder(this)
                    .transactions(income, expense, transfer)
                    .build()
                    .send();

```

or

```

ArrayList<Transaction> transactions = new ArrayList<>();
transactions.add(income);
transactions.add(expense);
transactions.add(transfer);

new TransactionSender.Builder(this)
                    .transactions(transactions)
                    .build()
                    .send();

```

**Be careful about how much transactions you send to MF. This process is done using BroadcastReceiver and although it is carried out in background using ```goAsync()```, the time to execute any task inside a BroadcastReceiver is limited and it gets harder in Android Oreo. See the [Android Documentation](https://developer.android.com/reference/android/content/BroadcastReceiver.html#goAsync()) about it.**

GETTING THE REQUEST RESULT
-----

You have to register a broadcast receiver on your manifest with the action  ```cicero.minhasfinancas.action.ADD_TRANSACTION_RESULT``` to get the request result.

You can get the result calling ```intent.getStringExtra("result")``` which is a message that tells you about your request.


**MyBroadcastReceiver.java**

```

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static final String ADD_TRANSACTION_RESULT = "cicero.minhasfinancas.action.ADD_TRANSACTION_RESULT";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && intent.getAction().equals(ADD_TRANSACTION_RESULT)) {
            
	    String result = intent.getStringExtra("result");
            
	    //do something (log, toast, etc.)
	    Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }
}

```


**AndroidManifest.xml**

```

<application ...>
	
	...
	
       <receiver
            android:name=".MyBroadcastReceiver"
            android:enabled="true"
            android:exported="true">
            <intent-filter>
                <action android:name="cicero.minhasfinancas.action.ADD_TRANSACTION_RESULT" />
            </intent-filter>
        </receiver>

</application>

```

License
=======
Copyright 2018 Cícero Moura

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
