# Unit Testing Documentation template

Authors: Antonino Angi, Giacomo Blanco, Erich Malan, Klodiana Cika

Date: 31/05/2019

Version: 1.5

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)




- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and the correspondence with the JUnit black box test case name/number>

 ### Class *FileManager*

 ### method *readEmployees*

**Criteria for method *readEmployees* :**
	

 - Employees written on file

**Predicates for method *readEmployees*:**

| Criteria                  | Predicate |
| ------------------------- | --------- |
| Employees written on file | 0         |
|                           | > 0       |

**Boundaries**: No boundary condition

**Combination of predicates**:


| Employees written on file | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|0|V|readEmployees() with some employees written on file|it.polito.latazza.data.<br />TestFileManager.testWriteEmployee|
|> 0|V|readEmployees() from empty file|it.polito.latazza.data.<br />TestFileManager.testEmployeesNull|

###  method *readBeverages*



**Criteria for method *readBeverages*:**
	

 - Beverages written on file



**Predicates for method *readBeverages*:**

| Criteria                  | Predicate |
| ------------------------- | --------- |
| Beverages written on file | 0         |
|                           | > 0       |



**Boundaries**: No boundary values

**Combination of predicates**:


| Beverages written on file | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|0|V|readBeverages() with some beverages written on file|it.polito.latazza.data.<br />TestFileManager.testWriteBeverage|
|> 0|V|readBeverages() from empty file|it.polito.latazza.data.<br />TestFileManager.testBeverageNull|

 ### method *readBalance*

**Criteria for method *readBalance* :**
	

 - balance already written on file

**Predicates for method *readBalance*:**

| Criteria                  | Predicate |
| ------------------------- | --------- |
| balance already written on file | yes         |
|                           | no       |

**Boundaries**: No boundary condition

**Combination of predicates**:


| Balance already written on file | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|yes|V|readBalance() on an already existing file|it.polito.latazza.data.<br />TestFileManager.testReadBalance|
|no|V|readBalance() when no file exists|it.polito.latazza.data.<br />TestFileManager.testReadBalanceNull|

### method *writeSaleReportEmployee*



**Criteria for method *writeSaleReportEmployee*:**
	

 - Number of reports in file
 - employee id valid (must be > 0)



**Predicates for method *writeSaleReportEmployee*:**

| Criteria                  | Predicate |
| ------------------------- | --------- |
| Number of reports in file | 0         |
|                           | > 0       |
| employee id valid         | yes       |
|                           | no        |



**Boundaries**: No boundary conditions



**Combination of predicates**:


| Number of reports in file | Employee id valid | Valid / Invalid | Description of the test case                                 | JUnit test case                                              |
| ------------------------- | ----------------- | --------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 0                         | yes               | V               | writeSaleReprotemployee() on a file containing some reports  | it.polito.latazza.data.<br />TestFileManager.testEmployeeReport |
| >0                        | yes               | V               | writeSaleReportEmployee() on a empty file                    | it.polito.latazza.data.<br />TestFileManager.testOneEmployeeReport |
| 0                         | no                | V               | writeSaleReportEmployee() on a empty file<br />-->EmployeeException | it.polito.latazza.data.<br />TestFileManager.testWrongEmployeeReport |

### method *writeSaleReportVisitor*



**Criteria for method *writeSaleReportVisitor*:**
	

 - Number of reports in file



**Predicates for method *writeSaleReportVisitor*:**

| Criteria                  | Predicate |
| ------------------------- | --------- |
| Number of reports in file | 0         |
|                           | > 0       |



**Boundaries**: No boundary conditions



**Combination of predicates**:


| Number of reports in file | Valid / Invalid | Description of the test case                               | JUnit test case                                              |
| ------------------------- | --------------- | ---------------------------------------------------------- | ------------------------------------------------------------ |
| 0                         | V               | writeSaleReportVisitor() on a file containing some reports | it.polito.latazza.data.<br />TestFileManager.testVisitorReport |
| >0                        | V               | writeSaleReportVisitor() on a empty file                   | it.polito.latazza.data.<br />TestFileManager.testVisitorReport |

### method *writeBuyReport*



**Criteria for method *writeBuyReport:***
	

 - Number of reports in file



**Predicates for method *writeBuyReport*:**

| Criteria                  | Predicate |
| ------------------------- | --------- |
| Number of reports in file | 0         |
|                           | > 0       |



**Boundaries**: No boundary conditions



**Combination of predicates**:


| Number of reports in file | Valid / Invalid | Description of the test case                       | JUnit test case                                            |
| ------------------------- | --------------- | -------------------------------------------------- | ---------------------------------------------------------- |
| 0                         | V               | writeBuyReport() on a file containing some reports | it.polito.latazza.data.<br />TestFileManager.testBuyReport |
| >0                        | V               | writeBuyReport() on a empty file                   | it.polito.latazza.data.<br />TestFileManager.testBuyReport |


### method *writeEmployee*



**Criteria for method *writeEmployee*:**
	
 - Employee id is valid (must be > 0)
 - Number of existing employees



**Predicates for method *writeEmployee*:**

| Criteria | Predicate |
| -------- | --------- |
| Employee id is valid | yes |
| | no |
| Number of existing employees | 0 |
| | > 0 |

**Boundaries**:no Boundary conditions


**Combination of predicates**:


| Employee id is valid | Number of existing Employees | Valid / Invalid | Description of the test case                           | JUnit test case                                              |
| -------------------- | ---------------------------- | --------------- | ------------------------------------------------------ | ------------------------------------------------------------ |
| yes                  | 0                            | V               | writeEmployee(); on empty file                         | it.polito.latazza.data.<br />TestFileManager.testWriteEmployee |
| yes                  | >0                           | V               | writeEmployee() on a file already containing employees | it.polito.latazza.data.<br />TestFileManager.<br />testWriteEmployeeAppend |
| no                   | 0                            | I               | writeEmployee() with a employee having id < 0          | it.polito.latazza.data.<br />TestFileManager.<br />testWriteWrongEmployee |


### method *updateEmployee*



**Criteria for method *updateEmployee*:**
	

 - Employee present in file
 - Employee id is valid (must be > 0)





**Predicates for method *updateEmployee*:**

| Criteria | Predicate |
| -------- | --------- |
|    Employee present in file    | yes |
|          | no |
| employee id is valid | yes |
| | no |

**Boundaries**:no Boundary conditions


**Combination of predicates**:


| Employee present in file | Employee id is valid | Valid / Invalid | Description of the test case        | JUnit test case                                              |
| ------------------------ | -------------------- | --------------- | ----------------------------------- | ------------------------------------------------------------ |
| yes                      | yes                  | V               | update an employee already existing | it.polito.latazza.data.<br />TestFileManager.testUpdateEmployee |
| no                       | yes                  | V               | update of an non-existing employee  | it.polito.latazza.data.<br />TestFileManager.testUpdateEmployeeNotExisting |
| no                       | no                   | I               | update of an employee with id <= 0  | it.polito.latazza.data.<br />TestFileManager.<br />testUpdateWrongEmployee |



### method *updateBalance*

**Criteria for method *updateBalance* :**
	

 - balance already written on file

**Predicates for method *readBalance*:**

| Criteria                        | Predicate |
| ------------------------------- | --------- |
| balance already written on file | yes       |
|                                 | no        |

**Boundaries**: No boundary condition

**Combination of predicates**:


| Balance already written on file | Valid / Invalid | Description of the test case                | JUnit test case                                              |
| ------------------------------- | --------------- | ------------------------------------------- | ------------------------------------------------------------ |
| yes                             | V               | updateBalance() on an already existing file | it.polito.latazza.data.<br />TestFileManager.testUpdateBalance |
| no                              | V               | updateBalance() when no file exists         | it.polito.latazza.data.<br />TestFileManager.testUpdateBalanceNull |

### method *employeeReport*



**Criteria for method *employeeReport*:**
	

 - Transaction written on a file



**Predicates for method *employeeReport*:**

| Criteria                  | Predicate |
| ------------------------- | --------- |
| Transaction written on a file                      | 0         |
|                           | > 0       |



**Boundaries**: No boundary conditions



**Combination of predicates**:


| Transaction written on a file | Valid / Invalid | Description of the test case                       | JUnit test case                                              |
| ------------------------- | --------------- | -------------------------------------------------- | ------------------------------------------------------------ |
| 0                         | V               | employeeReport() on a file containing some reports | it.polito.latazza.data.<br />TestFileManager.testemployeeReport |
| >0                        | V               | EmployeeReport() on a empty file                   | it.polito.latazza.data.<br />TestFileManager.testEmployeeReportNull |


### method *report*



**Criteria for method *report*:**
	

 - Transaction written on a file



**Predicates for method *report*:**

| Criteria                  | Predicate |
| ------------------------- | --------- |
| Transaction written on a file                      | 0         |
|                           | > 0       |



**Boundaries**: No boundary conditions



**Combination of predicates**:


| Transaction written on a file | Valid / Invalid | Description of the test case                       | JUnit test case                                              |
| ------------------------- | --------------- | -------------------------------------------------- | ------------------------------------------------------------ |
| 0                         | V               | report() on a file containing some reports | it.polito.latazza.data.<br />TestFileManager.testReport |
| >0                        | V               | report() on a empty file                   | it.polito.latazza.data.<br />TestFileManager.testReportNull |


### method *updateBeverage*



**Criteria for method *updateBeverage*:**
	

 - Beverage present in file
 - Beverage id is valid (must be > 0)





**Predicates for method *updateEmployee*:**

| Criteria | Predicate |
| -------- | --------- |
|    Beverage present in file    | yes |
|          | no |
| Beverage id is valid | yes |
| | no |

**Boundaries**:no Boundary conditions


**Combination of predicates**:


| Beverage present in file | Beverage id is valid | Valid / Invalid | Description of the test case        | JUnit test case                                              |
| ------------------------ | -------------------- | --------------- | ----------------------------------- | ------------------------------------------------------------ |
| yes                      | yes                  | V               | update an employee already existing | it.polito.latazza.data.<br />TestFileManager.testUpdateBeverage |
| no                       | yes                  | V               | update of an non-existing employee  | it.polito.latazza.data.<br />TestFileManager.testUpdateNullBeverages |
| no                       | no                   | I               | update of an employee with id <= 0  | it.polito.latazza.data.<br />TestFileManager.<br />testUpdateWrongBeverage |

### method *writeBeverage*



**Criteria for method *writeBeverage*:**
	
 - Beverage id is valid (must be > 0)
 - Number of existing beverages





**Predicates for method *writeBeverage*:**

| Criteria | Predicate |
| -------- | --------- |
| Beverage id is valid | yes |
| | no |
| Number of existing beverages | 0 |
| | > 0 |

**Boundaries**:no Boundary conditions


**Combination of predicates**:


| Beverage id is valid | Number of existing beverages | Valid / Invalid | Description of the test case                           | JUnit test case                                              |
| -------------------- | ---------------------------- | --------------- | ------------------------------------------------------ | ------------------------------------------------------------ |
| yes                  | 0                            | V               | writeBeverage(); on empty file                         | it.polito.latazza.data.<br />TestFileManager.testWriteBeverage |
| yes                  | >0                           | V               | writeBeverage() on a file already containing beverages | it.polito.latazza.data.<br />TestFileManager.<br />testWriteBeverageAppend |
| no                   | 0                            | I               | writeBeverage() with a beverage having id < 0          | it.polito.latazza.data.<br />TestFileManager.<br />testWriteWrongBeverage |

 ### Class *DataImpl*

 ### method *sellCapsules* 



**Criteria for method *sellCapsules*:**
	
 - Employee id valid
 - Beverage id valid
 - Number of capsules
 - from account


**Predicates for method *sellCapsules*:**

| Criteria | Predicate |
| -------- | --------- |
|    Employee id valid     |  yes         |
|          |     no      |
| Beverage id valid | yes |
|          | no |
| Number of capsules | >  available |
| | <  available |
| from account | yes |
| | no |



**Boundaries**:

| Criteria           | Boundary values |
| ------------------ | --------------- |
| Number of capsules | ==  available   |



**Combination of predicates**:


| Employee id valid | Beverage id valid | Number of capsules | from account | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|-------|
|yes|yes|< available|no|V|rechargeAccount()<br />BuyBoxes()<br />sellCapsules()|it.polito.latazza.data.<br />TestDataImpl.testSellCapsule|
|no|yes|< available|no|I|rechargeAccount()<br />BuyBoxes()<br />sellCapsules()<br />--> EmployeeException|it.polito.latazza.data.<br />TestDataImpl.<br />testSellCapsuleToNonEmployee|
|yes|no|< available|no|I|rechargeAccount()<br />BuyBoxes()<br />sellCapsules()<br />--> BeverageException|it.polito.latazza.data.<br />TestDataImpl.<br />testSellCapsuleToNonBeverage|
|yes|yes|> available|no|I|rechargeAccount()<br />sellCapsules()<br />--> NotEnoughCapsules|it.polito.latazza.data.<br />TestDataImpl.<br />testSellCapsuleToNonCapsules|
|yes|yes|< available|yes|V|rechargeAccount()<br />BuyBoxes()<br />sellCapsules()|it.polito.latazza.data.<br />TestDataImpl.<br />testSellCapsuleAccount|
|yes|yes|==  available|no|V|rechargeAccount()<br />BuyBoxes()<br />sellCapsules()|it.polito.latazza.data.<br />TestDataImpl.<br />testSellCapsules<br />EqualNumberOfAvailable|

### method *sellCapsulesToVisitor*



**Criteria for method *sellCapsulesToVisitor*:**
	

 - beverage id valid
 - number of capsules



**Predicates for method *sellCapsulesToVisitor*:**

| Criteria           | Predicate    |
| ------------------ | ------------ |
| beverage id valid  | yes          |
|                    | no           |
| number of capsules | <  available |
|                    | >  available |

**Boundaries**:

| Criteria           | Boundary values |
| ------------------ | --------------- |
| number of capsules | ==  available   |



**Combination of predicates**:


| Beverage id valid | Number of capsules | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|yes|<  available|V|rechargeAccount()<br />BuyBoxes()<br />sellCapsulesToVisitor()|it.polito.latazza.data.<br />TestDataImpl.testSellCapsuleToVisitor|
|yes|> available|I|rechargeAccount()<br />sellCapsulesToVisitor()<br />--> NotEnoughCapsules|it.polito.latazza.data.<br />TestDataImpl.testSellCapsuleVisitor<br />ToNonCapsules|
|no|< available|I|rechargeAccount()<br />sellCapsulesToVisitor()<br />--> BeverageException|it.polito.latazza.data.<br />TestDataImpl.testSellCapsuleVisitor<br />ToNonBeverage|
|yes|== available|V|rechargeAccount()<br />BuyBoxes()<br />sellCapsulesToVisitor()|it.polito.latazza.data.<br />TestDataImpl.<br />testSellCapsulesVisitor<br />EqualNumberOfAvailable|


### method *rechargeAccount*



**Criteria for method *rechargeAccount*:**
	

 - Employee id valid
 - Amount is valid (must be > 0)





**Predicates for method *rechargeAccount*:**

| Criteria          | Predicate |
| ----------------- | --------- |
| Employee id valid | yes       |
|                   | no        |
| Amount is valid   | yes       |
|                   | no        |



**Boundaries**: no boundary conditions



**Combination of predicates**:


| Employee id valid | Amount is valid | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|yes|yes|V|rechargeAccount()|it.polito.latazza.data.<br />TestDataImpl.<br />testRechargeAccount|
|no|yes|I|rechargeAccount with wrong EmployeeId<br />--> EmployeeException|it.polito.latazza.data.<br />TestDataImpl.<br />testRechargeAccount<br />ToNonEmployee|
|yes|no|I|rechargeAccount with amount negative or 0<br />--> EmployeeException|it.polito.latazza.data.<br />TestDataImpl.<br />testRechargeAccount<br />InvalidAmount|


### method *updateEmployee*



**Criteria for method *updateEmployee*:**
	

 - employee id valid




**Predicates for method *updateEmployee*:**

| Criteria           | Predicate    |
| ------------------ | ------------ |
| employee id valid  | yes          |
|                    | no           |




**Combination of predicates**:


| Employee id valid | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|yes|V|updateEmployee()|it.polito.latazza.data.<br />TestDataImpl.<br />testUpdateEmployee|
|no|I|UpdateEmployee()<br />--> EmployeeException|it.polito.latazza.data.<br />TestDataImpl.<br />testUpdateEmployee<br />NotExisting|

### method *getEmployeeName*



**Criteria for method *getEmployeeName*:**
	

 - employee id valid




**Predicates for method *getEmployeeName*:**

| Criteria           | Predicate    |
| ------------------ | ------------ |
| employee id valid  | yes          |
|                    | no           |




**Combination of predicates**:


| Employee id valid | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|yes|V|getEmployeeName()|it.polito.latazza.data.<br />TestDataImpl.<br />testEmployeeName|
|no|I|testgetEmployeeName<br />--> EmployeeException|it.polito.latazza.data.<br />TestDataImpl.<br />testgetEmployeeName<br />NonExisting|



### method *getEmployeeSurname*



**Criteria for method *getEmployeeSurname*:**
	

 - employee id valid




**Predicates for method *getEmployeeSurname*:**

| Criteria           | Predicate    |
| ------------------ | ------------ |
| employee id valid  | yes          |
|                    | no           |




**Combination of predicates**:


| Employee id valid | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|yes|V|getEmployeeSurname()|it.polito.latazza.data.<br />TestDataImpl.<br />testEmployeeSurname|
|no|I|getEmployeeSurame()<br />--> EmployeeException|it.polito.latazza.data.<br />TestDataImpl.<br />testgetEmployeeSurname<br />NonExisting|


### method *getEmployeeBalance*


**Criteria for method *getEmployeeBalance*:**
	

 - employee id valid




**Predicates for method *getEmployeeBalance*:**

| Criteria           | Predicate    |
| ------------------ | ------------ |
| employee id valid  | yes          |
|                    | no           |




**Combination of predicates**:


| Employee id valid | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|yes|V|getEmployeeBalance()|it.polito.latazza.data.<br />TestDataImpl.<br />testEmployeeBalance|
|no|I|getEmployeeBalance()<br />--> EmployeeException|it.polito.latazza.data.<br />TestDataImpl.<br />testEmployeeBalance<br />NonExisting|


### method *getEmployeesId*


**Criteria for method *getEmployeesId*:**
	

 - existing employees




**Predicates for method *getEmployeesId*:**

| Criteria           | Predicate    |
| ------------------ | ------------ |
| Existing employees | > 0     |
|                    | 0           |




**Combination of predicates**:


| Existing employees | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
| > 0                |V|getEmployeesId()|it.polito.latazza.data.<br />TestDataImpl.<br />testEmployeesId|
|0|V|getEmployeesId() with no employees|it.polito.latazza.data.<br />TestDataImpl.<br />testEmployeesId<br />Empty|

### method *getEmployees*


**Criteria for method *getEmployees*:**
	

 - existing employees

   


**Predicates for method *getEmployees*:**

| Criteria           | Predicate    |
| ------------------ | ------------ |
| existing employees | > 0        |
|                    | 0           |




**Combination of predicates**:


| Existing employees | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|> 0|V|getEmployees()|it.polito.latazza.data.<br />TestDataImpl.<br />testEmployees|
|0|V|getEmployees() with no employees|it.polito.latazza.data.<br />TestDataImpl.<br />testgetEmployees<br />Empty|


### method *buyBoxes*



**Criteria for method *buyBoxes*:**
	

 - Beverage id valid
 - Balance valid


**Predicates for method *buyBoxes*:**

| Criteria          | Predicate |
| ----------------- | --------- |
| Beverage id valid | yes       |
|                   | no        |
| Balance valid| >= price |
| | < price |



**Boundaries**: Account balance = boxes price



**Combination of predicates**:

| Beverage id valid | Balance valid | Valid / Invalid | Description of the test case                       | JUnit test case                                              |
| ------------------------- | --------------- | -------------------------------------------------- | ------------------------- | ------------------------ |
|yes|>= price|V|buyBoxes()|it.polito.latazza.data.<br />TestDataImpl.<br />testBuyBoxes <br /> Valid|
|no|>= price|I|buyBoxes()<br />-->BeverageException|it.polito.latazza.data.<br />TestDataImpl.<br />testBuyBoxes<br /> NotValidBeverageId|
|yes|< price|I|buyBoxes()<br />-->NotEnoughBalance|it.polito.latazza.data.<br />TestDataImpl.<br />testBuyBoxes<br /> NotValidBalance|
|yes|= price|V|buyBoxes()|it.polito.latazza.data.<br />TestDataImpl.<br />testBuyBoxes <br /> ValidEqualNumberOfAvailable|



### method *getEmployeeReport*



**Criteria for method *getEmployeeReport*:**
	

 - Employee id valid
 - Start date valid (must be before or equals to the current day and not null)
 - End date valid (must be before or equals to the current day and not null)
 - Start-End Date valid (End date must be after start date)


**Predicates for method *getEmployeeReport*:**

| Criteria          | Predicate |
| ----------------- | --------- |
| Employee id valid | yes       |
|                   | no        |
| Start Date valid| yes |
| | no |
| End Date valid | yes|
| | no |
| Start-End Date valid | yes |
| | no |

**Boundaries**: Start and End Date are equals

**Combination of predicates**:

| Employee id valid | Start Date valid | End Date valid | Start-End Date valid | Valid / Invalid | Description of the test case                       | JUnit test case                                              |
| ------------------------- | --------------- | -------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| yes| yes | yes | yes | V | getEmployeeReport() | it.polito.latazza.data.<br />TestDataImpl.<br />testEmployeeReport |
| yes | yes | yes | no | I |  getEmployeeReport()<br />-->DateException | it.polito.latazza.data.<br />TestDataImpl.<br />testDateEmployeeException |
| yes | yes | no | yes | I | getEmployeeReport()<br />-->DateException | it.polito.latazza.data.<br />TestDataImpl.<br />testDateEmployeeException |
| yes | no | yes | yes | I | getEmployeeReport()<br />-->DateException | it.polito.latazza.data.<br />TestDataImpl.<br />testDateEmployeeException |
| no | yes | yes | yes | I | getEmployeeReport()<br />-->EmployeeException | it.polito.latazza.data.<br />TestDataImpl.<br />testDateEmployeeException |
| yes | yes | yes | equals | V | getEmployeeReport() with start date equals to endDate | it.polito.latazza.data.<br />TestDataImpl.<br />testEmployeeReportEqualDates |

### method *getReport*



**Criteria for method *getReport*:**
	

 - Start date valid (must be before or equals to the current day and not null)
 - End date valid (must be before or equals to the current day and not null)
 - Start-End Date valid (End date must be after Start date)

**Predicates for method *getReport*:**

| Criteria          | Predicate |
| ----------------- | --------- |
| Start Date valid| yes |
| | no |
| End Date valid | yes|
| | no |
| Start-End Date valid | yes |
| | no |




**Boundaries**: Start and End Date are equals



**Combination of predicates**:

| Start Date valid | End Date valid | Start-End Date valid | Valid / Invalid | Description of the test case                       | JUnit test case                                              |
| --------------- | -------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| yes | yes | yes | V | doing some transaction;<br />getReport(); | it.polito.latazza.data.<br />TestDataImpl.<br />testGetReport |
| no | yes | yes | I | doing some transaction;<br />getReport();<br />--> DateException | it.polito.latazza.data.<br />TestDataImpl.<br />testGetReportUnvalidDates |
| yes | no | yes | I | doing some transaction;<br />getReport();<br />--> DateException | it.polito.latazza.data.<br />TestDataImpl.<br />testGetReportUnvalidDates |
| yes | yes | no | I | doing some transaction;<br />getReport();<br />--> DateException | it.polito.latazza.data.<br />TestDataImpl.<br />testGetReportUnvalidDates |
| yes | yes | equals | V | getEmployeeReport() with start date equals to endDate | it.polito.latazza.data.<br />TestDataImpl.<br />testReportEqualDates |


### method *updateBeverage*



**Criteria for method *updateBeverage*:**
	

 - Beverage id valid
 - box price is valid (must be > 0)
 - capsules per box is valid (must be > 0 )


**Predicates for method *updateBeverage*:**

| Criteria          | Predicate |
| ----------------- | --------- |
| Beverage id valid | yes |
| | no |
| boxPrice is valid | yes |
| | no |
| capusule per box is valid | yes |
| | no |




**Boundaries**: No boundary condition



**Combination of predicates**:

| Beverage id valid | box price is valid | capsules per box is valid | Valid / Invalid | Description of the test case                       | JUnit test case                                              |
| ------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
|yes|yes|yes| V | updateBeverage() | it.polito.latazza.data.<br />TestDataImpl.<br />testupdateBeverageUpdating|
|no|yes|yes| I | updateBeverage()<br />--> BeverageExcpetion | it.polito.latazza.data.<br />TestDataImpl.<br />testupdateBeverageNotExistBev|
|yes|no|yes| I | updateBeverage() with box price < 0<br />-->BeverageException | it.polito.latazza.data.<br />TestDataImpl.<br />testupdateBeverage<br />WrongBoxPrice |
|yes|yes|no| I | updateBeverage() with capsules per box < 0<br />-->BeverageException | it.polito.latazza.data.<br />TestDataImpl.<br />testupdateBeverage<br />WrongCapsules |

### method *createBeverage*



**Criteria for method *createBeverage*:**
	

 - box price is valid (must be > 0)
 - capsules per box is valid (must be > 0 )


**Predicates for method *updateBeverage*:**

| Criteria          | Predicate |
| ----------------- | --------- |
| boxPrice is valid | yes |
| | no |
| capusule per box is valid | yes |
| | no |




**Boundaries**: No boundary condition



**Combination of predicates**:

| box price is valid | capsules per box is valid | Valid / Invalid | Description of the test case                       | JUnit test case                                              |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
|yes|yes| V | createBeverage() | it.polito.latazza.data.<br />TestDataImpl.<br />testcreateBeverage |
|no|yes| I | createBeverage() with box price < 0<br />-->BeverageException | it.polito.latazza.data.<br />TestDataImpl.<br />testCreateBeverage<br />WrongBoxPrice |
|yes|no| I | createBeverage() with capsules per box < 0<br />-->BeverageException | it.polito.latazza.data.<br />TestDataImpl.<br />testCreateBeverage<br />WrongCapsules |


### method *getBeverageName*



**Criteria for method *getBeverageName*:**
	

 - beverage id valid



**Predicates for method *getBeverageName*:**

| Criteria | Predicate |
| -------- | --------- |
|    beverage id valid      | yes |
|          | no |



**Boundaries**: No boundary conditions

**Combination of predicates**:


| beverage id valid | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|yes|V|getBeverageName()|it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeverageNameCheck|
|no|I|getBeverageName()<br />--> BeverageException|it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeverageName<br />NotExistBev|



### method *getBeverageCapsulesPerBox*



**Criteria for method *getBeverageCapsulesPerBox*:**
	

 - beverage id valid



**Predicates for method *getBeverageCapsulesPerBox*:**

| Criteria | Predicate |
| -------- | --------- |
|    beverage id valid    | yes |
|          | no |



**Boundaries**: No boundary conditions

**Combination of predicates**:


| beverage id valid | Valid / Invalid | Description of the test case                           | JUnit test case                                              |
| ----------------- | --------------- | ------------------------------------------------------ | ------------------------------------------------------------ |
| yes               | V               | getBeverageCapsulesPerBox()                            | it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeverageCapsulesPerBox |
| no                | I               | getBeverageCapsulesPerBox()<br />--> BeverageException | it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeverageCapsulesPerBox<br />NotExist |

### method *getBeverageBoxPrice*



**Criteria for method *getBeverageBoxPrice*:**
	

 - beverage id valid

**Predicates for method *getBeverageBoxPrice*:**

| Criteria | Predicate |
| -------- | --------- |
|    beverage id valid    | yes |
|          | no |



**Boundaries**: No boundary conditions


**Combination of predicates**:


| beverage id valid | Valid / Invalid | Description of the test case                             | JUnit test case                                              |
| ----------------- | --------------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| yes               | V               | getBeverageBoxPrice()                                    | it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeverageBoxPrice |
| no                | I               | getBeverageCapsulesBoxPrice()<br />--> BeverageException | it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeverageBoxPrice<br />NotExist |

### method *getBeveragesId*



**Criteria for method *getBeveragesId*:**
	

 - existing beverages

**Predicates for method *getBeveragesId*:**

| Criteria           | Predicate |
| ------------------ | --------- |
| existing beverages | 0         |
|                    | > 0       |


**Boundaries**: No boundary values

**Combination of predicates**:


| existing beverages | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|0|V|getBeveragesId() with no beverages|it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeveragesIdEmpty|
|> 0|V|getBeveragesId()|it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeveragesId|


### method *getBeverages*



**Criteria for method *getBeverages*:**
	

 - existing beverages

**Predicates for method *getBeverages*:**

| Criteria           | Predicate |
| ------------------ | --------- |
| existing beverages | 0         |
|                    | > 0       |



**Boundaries**: No boundary values



**Combination of predicates**:


| existing beverages | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|0|V|getBeverages() with no beverages|it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeveragesEmpty|
|> 0|V|getBeverages()|it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeverages|



### method *getBeverageCapsules*



**Criteria for method *getBeverageCapsules*:**

 - beverages id valid 



**Predicates for method *getBeverageCapsules*:**

| Criteria          | Predicate |
| ----------------- | --------- |
| beverage id valid | yes       |
|                   | no        |

**Boundaries**: No boundary values

**Combination of predicates**:


| beverage id valid | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|yes|V|getBeverageCapsules()|it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeverageCapsules|
|no|I|getBeverageCapsules()<br />--> BeverageException|it.polito.latazza.data.<br />TestDataImpl.<br />testgetBeverageCapsulesNotExist|


# White Box Unit Tests

### Test cases definition

| Unit name | JUnit test case |
|--|--|
|DataImpl|it.polito.latazza.data.TestDataImpl|
|FileManager|it.polito.latazza.data.TestFileManager|

### Code coverage report

   ![Coverage](Coverage.PNG "")



### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|FileManager.readEmployees|35-42|0|it.polito.latazza.data.TestFileManager.<br />testEmployeesNull|
|||1|it.polito.latazza.data.TestFileManager.<br />testReadOneEmployee|
|||>1|it.polito.latazza.data.TestFileManager.<br />testWriteEmployee|
|FileManager.readBeverages|65-74|0|it.polito.latazza.data.TestFileManager.<br />testBeverageNull|
| | | 1 | it.polito.latazza.data.TestFileManager.<br />testReadOneBeverage |
|||>1|it.polito.latazza.data.TestFileManager.<br />testWriteBeverage|
|FileManager.updateEmployee|175-188|0|it.polito.latazza.data.TestFileManager.<br />testUpdateNullEmployees|
| |  | 1 | it.polito.latazza.data.TestFileManager.<br />testUpdateFirstEmployee |
| |  | > 1 | it.polito.latazza.data.TestFileManager.<br />testUpdateEmployee |
| FileManager.updateBeverage | 224-236 | 0 | it.polito.latazza.data.TestFileManager.<br />testUpdateNullBeverages |
| |  | 1 | it.polito.latazza.data.TestFileManager.<br />testUpdateFirstBeverage |
| |  | > 1 | it.polito.latazza.data.TestFileManager.<br />testUpdateBeverage |
| FileManager.employeeReport | 320 - 332 | 0 | it.polito.latazza.data.TestFileManager.<br />testEmployeeReportNull |
| |  | 1 | it.polito.latazza.data.TestFileManager.<br />testOneEmployeeReport |
| |  | > 1 | it.polito.latazza.data.TestFileManager.<br />testEmployeeReport |
| FileManager.report | 355-366 | 0 | it.polito.latazza.data.TestFileManager.<br />testReportNull |
| |  | 1 | it.polito.latazza.data.TestFileManager.<br />testOneReport |
| |  | > 1 | it.polito.latazza.data.TestFileManager.<br />testReport |


