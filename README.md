# Welcome 👏 to RentYe app!


* [The WHY](#rentye)
* [Technologies](#technologies)
* [Architecture Design Pattern](#architecture-design-pattern)
* [Features](#features)
* [Setup](#setup)
* [Development status](#development-status)
* [Contact](#contact)


## RentYe
🔹 RentYe was created to automate managing investment leases for individual investors.\
🔹 Anticipated benefits:
  - process automation ✅,
  - precise reporting ❌,
  - valuation and forecasting ❌,
  - investment portfolio management ❌.


## Technologies

**Backend**\
🔹 Java SE 21,\
🔹 Spring Boot Framework 3.3.0,\
🔹 Spring Data JPA,\
🔹 Hibernate Framework 6.1.4,\
🔹 Spring Security 6.3.1,

**Database**\
🔹 MySQL 8.0.36,

**Application server**\
🔹 Tomcat 10.1.24,

**Frontend**\
🔹 HTML 5,\
🔹 CSS,\
🔹 Bootstrap Framework v. 5.3.3 (menu, buttons styling),


## Architecture Design Pattern
🔹 Model - View - Controller (MVC),


## Features
🔹 Apartments management ✅,\
🔹 Contractor management ✅,\
🔹 Management of lease agreements & tenant data ✅,\
🔹 Transaction settlement ❌,\
🔹 Reporting ❌,\
🔹 Valuation and forecasting ❌,


## Setup
🔹 Clone repository from GitHub.\
🔹 Prepare database server and create _rentye_ schema (execute dbcreate.sql).\
🔹 Update detabase connection details in _application.properties_.\
🔹 Compile sources with Maven.\
🔹 Execute application _jar_ file.\
🔹 Optional: execute rentyeconfig.sql (initializes dictionaries).\
🔹 Optional: execute testdata.sql (adds test contractor and apartment data).

Initially application contains user _admin_ with password _password_. It is recommended to create new administration user and delete the _admin_ user during the first use of the application.


## Development Status
🔹 **The Minimum Viable Product** was completed.\
🔹 Features **_in progress_**:
  - monitoring of revenues, costs and rental income,
  - assessing and forecasting the profitability of private housing investments,
  - REST API,
  - UX.


## Contact
**Feel free to connect & contact me!**

👉 [LinkedIn](https://linkedin.com/in/annaherer) \
👉 [Twitter](https://twitter.com/hereranna) \
👉 [Facebook](https://fb.com/annaherer) \
👉 [Instagram](https://instagram.com/anna.herer)


## License
This project is open source and available under the [MIT License]().