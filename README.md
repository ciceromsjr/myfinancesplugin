[![Release](https://img.shields.io/github/release/current_release.svg?style=flat)](https://jitpack.io/#ciceromsjr/myfinancesplugin)  
[![API](https://img.shields.io/badge/API-15%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=15)

# My Finances Official Plugin
Plugin for My Finances Official app


Features
-----
- Add transactions (incomes, expenses and transfers).
 
Usage
-----

In order to use the library, there are 4 different options:

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
	implementation 'com.github.PhilJay:MPAndroidChart:v3.0.3'
}
```

**2. Maven**
- Add the following to the `<repositories>` section of your `pom.xml`:

 ```xml
<repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
</repository>
```
- Add the following to the `<dependencies>` section of your `pom.xml`:

 ```xml
<dependency>
        <groupId>com.github.PhilJay</groupId>
        <artifactId>MPAndroidChart</artifactId>
        <version>v3.0.3</version>
</dependency>
```
	
**3. clone whole repository** (not recommended)

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
