[![Release](https://github.com/ciceromsjr/myfinancesplugin/blob/master/release/current_release.svg)](https://jitpack.io/#ciceromsjr/myfinancesplugin)  
[![API](https://img.shields.io/badge/API-15%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=15)

# My Finances Official Plugin
Plugin for My Finances Official app


Features
-----
- Add transactions (incomes, expenses and transfers).
 
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
	implementation 'com.github.ciceromsjr:myfinancesplugin:v0.0.1'
}
```
	
**2. clone whole repository** (not recommended)

Adding Transactions
-----

You can add three transaction types:

- Income
- Expense
- Transfer

All dependecies such as category/subcategory and credi card are searched by description.
I means that if a dependendy is not found you will get an error.

**Add an Income**

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
income.setRecurrence(Transaction.TransactionRecurrence.NONE);

new TransactionSender.Builder(this)
                    .notification(true)
                    .transaction(income)
                    .build()
                    .send();

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
