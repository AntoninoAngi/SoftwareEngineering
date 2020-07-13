# Design Document Template

Authors: Giacomo Blanco, Antonino Angi, Klodiana Cika, Erich Malan

Date: 16/04/2019

Version: 1.0

# Contents

- [Package diagram](#package-diagram)
- [Class diagram](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Instructions

The design document has to comply with:
1. [Official Requirement Document](../Official\ Requirements\ Document.md)
2. [DataInterface.java](../src/main/java/it/polito/latazza/data/DataInterface.java)

UML diagrams **MUST** be written using plantuml notation.

# Package diagram



``` plantuml 

left to right direction
skinparam packageStyle folder

package data {

}

package gui
{

}

package exceptions
{ 

}

gui --> data
data --> exceptions
```

The architectural pattern which fits most the current situation is the MVC, Model-View-Controller. The *data* package acts like model because it is in charge of managing the data used by the application. The *gui* package acts like the View because it manages the user interface and also like Controller because it handle the external events to perform operations.


# Class diagram

### Data package

``` plantuml

class DataImpl
{
    employees : Map<Integer, Employee>
    beverages : Map<Integer, Beverage>
    account : LaTazzaAccount
    +sellCapsules()
    +sellCapsulesToVisitor()
    +rechargeAccount()
    +buyBoxes()
    +getEmployeeReport()
    +getReport()
    +createBeverage()
    +updateBeverage()
    +getBeverageName()
    +getBeverageCapsulesPerBox()
    +getBeverageBoxPrice()
    +getBeveragesId()
    +getBeverages()
    +getBeverageCapsules()
    +createEmployee()
    +updateEmployee()
    +getEmployeeName()
    +getEmployeeSurname()
    +getEmployeeBalance()
    +getEmployeesId()
    +getEmployees()
    +getBalance()
    +reset()
    +clearFiles()
    +destroyAll()
    +matchId()
    +matchDates()
    +validDates()
    +IntegerNullOrNegative()
    +stringNullOrNegative()
}

class FileManager
{
    +readEmployees()
    +readBeverages()
    +writeSaleReportEmployee()
    +writeSaleReportVisitor()
    +updateEmployee()
    +updateBalance()
    +writeBuyReport()
    +employeeReport()
    +report()
    +writeEmployee()
    +writeBeverage()
    +updateBeverage()
    +readBalance()
    +deleteEmployees()
    +deleteBeverages()
    +deleteReports()
    +removeId()
}

class Beverage
{
    Id : Integer
    name : String
    capsulesPerbox : Integer
    boxPrice : Integer
    unitPrice : Integer
    availability : Integer
    +getId()
    +getName()
    +getCapsulesPerBox()
    +getBoxPrice()
    +getUnitPrice()
    +getCapsules()
    +updateAvailable()
    +setName()
    +setCapsulesPerBox()
    +setBoxPrice()
}

class laTazzaAccount
{
    balance : int
    +getBalance()
    +updateBalance()
    +delete()
}

class Employee
{
    Id : int
    name : String
    surname : String
    balance : int
    +getId()
    +getName()
    +getSurname()
    +getBalance()
    +updateBalance()
    +setName()
    +setSurname()
    +getNameSurname()
}

DataImpl "1" -- "1" FileManager : exploits
FileManager "1" -- "1..*" Employee : manage
FileManager "1" -- "1..*" Beverage : manage
FileManager "1" -- "1..*" laTazzaAccount : manage
DataImpl "1" -- "1..*" Employee : read
DataImpl "1" -- "1..*" Beverage : read
```

The class DataImpl is a representation of the Facade pattern. In fact, it is a facade class for the whole package, all the classes outside the *data* package must interact with this class in order to perform all the operations offered by this package.


# Verification traceability matrix


|  | DataImpl | Employee  | Beverage | laTazzaAccount | FileManager |
| ------------- |:-------------:| :----:| :----:| :----:| :----:|
| Functional requirement 1 | X | X | X | X |X|
| Functional requirement 2 | X |  | X | X |X|
| Functional requirement 3 | X | X | | X |X|
| Functional requirement 4 | X |  | X | X |X|
| Functional requirement 5 | X | X | | |X|
| Functional requirement 6 | X |  | | |X|
| Functional requirement 7 | X |  | X | |X|
| Functional requirement 8 | X | X | | |X|

# Verification sequence diagrams 

### Scenario 1

``` plantuml
"DataImpl" -> ": FileManager": "1: readEmployees()"
"DataImpl" -> ": FileManager": "2: readBeverages()"
"DataImpl" -> ": Employee": "3: getBalance()"
"DataImpl" -> ": Beverage": "4: getUnitPrice()"
"DataImpl" -> ": Beverage": "5: updateAvailable()"
"DataImpl" -> ": Employee": "6: updateBalance()"
"DataImpl" -> ": FileManager": "7: updateBeverage()"
"DataImpl" -> ": FileManager": "8: updateEmployee()"
"DataImpl" -> ": laTazzaAccount": "9: updateBalance()"
"DataImpl" -> ": FileManager": "10: updateBalance()"
"DataImpl" -> ": FileManager": "11: writeSaleReportEmployee()"
```

### Scenario 2

``` plantuml
"DataImpl" -> ": FileManager": "1: readEmployees()"
"DataImpl" -> ": FileManager": "2: readBeverages()"
"DataImpl" -> ": Employee": "3: getBalance()"
"DataImpl" -> ": Beverage": "4: getUnitPrice()"
"DataImpl" -> ": NotEnoughBalanceException": "5: throw()"
```

