# SwagLabs
Automated testing for SwagLabs https://www.saucedemo.com/ web application.

### Technologies & Stack
- *Programming Language:* Java 21
- *Testing Framework:* TestNG
- *Automation Library:* Selenium WebDriver
- *Dependency Management:* Maven
- *Other Tools:*
    - WebDriverManager – for automatic WebDriver management
    - Allure – for test reporting


## 📋 Project Structure
```
📂 src
├── 📂 main
│   ├── 📂 java
│   │   ├── 📂 com
│   │   │   ├── 📂 swaglabs
│   │   │   │   ├── 📂 components   # UI components
│   │   │   │   ├── 📂 modal        # Ui modal windows
│   │   │   │   ├── 📂 pages        # POM classes
├── 📂 test
│   ├── 📂 java
│   │   ├── 📂 com
│   │   │   ├── 📂 swaglabs
│   │   │   │   ├── 📂 tests
│   │   │   │   │   ├── 📂 dataprovider   # Data providers
│   │   │   │   │   ├── 📂 ui             # UI tests
│   │   │   │   │   ├── 📂 utils          # Test utilities
│   ├── 📂 resources                  # Test resources
└── 📄 pom.xml                        # Maven dependencies
 ```



### 1) Clone the repository
````
git clone https://github.com/viktoriiafylyk/SwagLabs.git
cd SwagLabs
````

#### config.properties:
```properties
base.ui.url=https://www.saucedemo.com/
implicitlyWait=1
user.email=standard_user
user.name=secret_sauce
```

### 2) Install dependencies
Run the following command to install all necessary dependencies:

````
mvn clean install
````

### 3) Run tests

Run all tests:
````
mvn clean test
````

### 4) Generate AllureReport
To view test reports in Allure, run:
````
allure serve target/allure-results
````
