# Requirements Document Template

Authors: Giacomo Blanco, Antonino Angi, Klodiana Cika, Erich Malan	

Date: 08/04/19

Version: 2.2

# Contents

- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
	
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases](#use-cases)
	+ [Relevant scenarios](#relevant-scenarios)
- [Glossary](#glossary)
- [System design](#system-design)

# Stakeholders


| Stakeholder name  | Description |
| ----------------- |:-----------:|
| Employee | Buy capsules either by cash or by credits associated to an account at workplace|
| Coffee Supplier | Sell boxes of capsules to manager |
| Manager | Buy boxes of capsules and sell capsules to employees or visitors |
| Software Vendor | Provide software to work environments |
| Visitor |Buy capsules from manager by cash only |

# Context Diagram and interfaces

## Context Diagram
``` plantuml
left to right direction
skinparam packageStyle rectangle

actor Manager as m
actor "Banking System" as bs
actor "Email System" as es
actor "File system" as fs

rectangle System {
(LaTazza) as lt

m -- lt
es -- lt
lt -- bs
lt -- fs
}
```



## Interfaces
| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| -----:|
| Manager | GUI |Monitor, mouse and keyboard |
| Coffee Supplier | APIs to send orders | Internet Connection (Wireless or Wired) |
| Banking System | APIs to handle payment | Internet Connection (Wireless or Wired) |
|File system | File system APIs | Main memory, Disk |


# Stories and personas
**Employee**:
It’s Monday morning.
The employee realizes he does not have any capsule left and during a break asks the manager for capsules. He wants to do it as quickly as possible since he has a lot of work to do and does not want to bother the manager too much. He decides to buy the same combination of capsules as 2 weeks ago, but less quantity of capsules since he does not have cash, his credit associated to his account is over and he prefers not to be in debt.

**Manager** (his name is Jack):
It’s the morning of a working day.
When an employee or a visitor asks for capsules, Jack logs on the LaTazza application with his username and password and he wants to be quickly done with the capsules sales since he has work to do. Jack asks for how many capsules the employee (or visitor) wants and registers the payment which, in the case of the employee, it could be done by cash or by credits associated to his account; for the visitor, instead, it could be done by cash only.
As soon as the sale is done Jack checks the inventory to decide if it is necessary to make an order, then he closes LaTazza application and goes back on working.

**Visitor**:
It’s the afternoon of a working day.
The visitor wants coffee and asks the manager for capsules. He goes to the manager and pays by cash.

**Coffee Supplier**:
It’s the evening of a working day.
He receives the order from the manager and stores the order. He is not interested in staying at the office too long and as soon as he processes the order, he leaves and goes home.

**Software Vendor**:
It’s Tuesday afternoon.
The manager asks for LaTazza application since all of the employees want to have an easy way to get capsules. The software vendor explains how to use the program and shows all the timing needed from receiving the order and process it to the deliver. At the end, the software vendor gives the Manager his credentials.


**Personas**:

*Manager*: male, middle age, skillful, high income, married with 3 children

*Employee1*: male, middle age, professional, low income, not married, with a child

*Employee2*: female, middle age, competent, married with 2 children

*Visitor1*: female, young, student, not married

*Visitor2*: male, old, retired, married with children

*Coffee Supplier*: male, middle age, experienced, not married

*Software Vendor*: female, middle age, competent, well educated, married with children


Manager, workday: wake up, take a shower, have breakfast, drive his child to school, goes to work

Manager, weekend day: wake up, work out, take a shower, relax

Employee1, workday: wake up at 5 am to work out, take a shower, have breakfast, drive his child to school, goes to work

Employee1, weekend day: go camping with his child, go back home and have a pizza

Employee2, workday: wake up, have breakfast with the family, goes to work

Employee2, weekend day: invite her family members to have lunch together, order food

Visitor1, workday: wake up, take a shower, have breakfast, goes to school

Visitor1, weekend day: wake up, goes to play volleyball, take a shower

Visitor2, workday: wake up, have breakfast with his wife, read the newspaper

Visitor2, weekend day: wake up, take a shower, have breakfast, cook

Coffee Supplier, workday: wake up, take a shower, have breakfast, goes to work

Coffee Supplier, weekend day: wake up, work out, take a shower, have breakfast, watch TV

Software Vendor, workday: wake up, have breakfast, drive his children at school, goes to work

Software Vendor, weekend day: wake up, have breakfast, listen to music


# Functional and non functional requirements

## Functional Requirements

| ID        | Description  |
| ------------- |:-------------:|
|  FR1     | Sell capsules |
|  FR1.1   | Check employee identity |
|  FR1.2   | Check capsules availability |
|  FR1.3   | Handle payment |
|  FR1.4   | Compute employee balance |
|  FR1.5   | Compute cash account balance |
|  FR2     | Buy capsules |
|  FR2.1   | Select type and quantity |
|  FR2.2   | Send order to supplier via email system |
|  FR3     | Compute and update inventory |
|  FR4     | Verify manager account |
|  FR5     | Manage employee account and balance |
|  FR5.1   | Insert employee account |
|  FR5.2   | Remove employee account |
|  FR6     | Add credits to an employee |
| FR7 | Add a new supplier |
| FR8 | Update boxes price list for a supplier |
| FR9 | Update Supplier Information |
| FR10 | Read from file system |
|FR11 | Write on file system |

## Non Functional Requirements

| ID        | Type (efficiency, reliability, ..)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NFR1     | Domain | Balance shall be computed in Euro | FR1, FR2 |
|  NFR2     | Functionality | An employee should not be in debt for more than €50 | FR1, FR5 |
|  NFR3     | Efficiency | The order email should be sent in less than 2 minutes | FR2 |
|  NFR4     | Legislative | Sensitive data should be protected during bank transactions | FR1 |
|  NFR5     | Functionality | The employee should not be in debt when closing an account | FR5, FR6 |

# Use case diagram and use cases


## Use case diagram
```plantuml
left to right direction
skinparam packageStyle rectangle

actor Visitor as v
actor Employee as e
actor Manager as m
actor Supplier as s
actor "Banking system" as bs
actor "Email system" as es

(Sell capsules - Cash) as scc
(Sell capsules - Account) as sca
(Buy capsules) as bc
(Compute and update inventory) as cui
(Add credits to employee) as bcr
(Remove employee account) as rma
(Insert new employee account) as ina
(View employee balance) as vea
(Check inventory and balance) as cib

cib <-- m
v <-- scc
e <-- scc
scc <-- m

e <-- sca
sca <-- m

sca .> (Check employee identity): include
sca .> (Check capsule availability) : include
sca .> (Handle payment) : include
sca .> (Compute Employee Balance) : include
sca .> (Compute cash account balance) : include

scc .> (Check capsule availability - cash): include
scc .> (Check employee identity): include
scc .> (Handle payment - cash): include
scc .> (Compute cash account balance - cash): include

vea .> (Check employee identity): include
vea <-- m
e <--> bcr
bcr <-- m

bcr .> (Check employee identity): include
bcr .> (Handle credits payment): include
bcr .> (Compute Employee Credits): include

bcr -- bs

bc <-- m
s <-- bc

bc .> (Select type and quantity) : include
bc .> (Send order to supplier) : include

bc -- es
bc -- bs

(Verify manager account) --> m 

ina <-- m
e <-- ina
rma <-- m
e <-- rma
rma .> (Check employee identity): include

scc .> cui : include
sca .> cui : include
bc .> cui : include
```


## Use Cases
### **Description of use cases** 

All the operations described involve the use of filesystem read and write operations.

* **Sell capsules - Account** : The manager uses LaTazza to sells capsules to an employee who wants to pay using the credits on his account. 
* **Sell capsules - Cash** : The manager uses LaTazza to sell capsules to whoever wants to pay cash (either an employee or a visitor)
* **Add credits to an employee** : The manager uses LaTazza to add credits to an employee account. The employee can buy credits using either cash or via the banking system. In the latter case the manager inserts employee's payment data into LaTazza to receive the payment.
* **Insert new employee account** : The manager can add a new employee.
* **Remove employee account** : The manager deletes an employee account.
* **View employee balance** : The manager sees the account balance of one employee.
* **Verify manager account** : The manager must log in to LaTazza before starting using it using the credentials provided by the software vendor.
* **Buy capsules** : The manager uses LaTazza to sends an order via e-mail to the Coffee Supplier. The order can be paid using cash or the banking system.
* **Check inventory and balance** : The manager just wants to see the cash balance and how many capsules remain.
* **Add a new supplier** : The manager adds the information related to a new supplier 
* **Update price list** : The manager updates the information about supplier prices.
* **Update supplier information** : The manager updates the information about a supplier.

**Compute cash account balance** and **Compute cash account balance-cash** are the same operations, they are written as different operation only for sakes of diagram comprehension.

**Check capsule availability balance** and **Check capsule availability-cash** are the same operations, they are written as different operation only for sakes of diagram comprehension.

### Use case 1, UC1
| Actors Involved        | Employee, Manager|
| ------------- |:-------------:|
|  Precondition     | An employee wants to buy one or more capsules |
|  Post condition     | The employee has its capsules |
|  Nominal Scenario     | Sell capsules to an employee who pays cash |
|  Variants     | The employee has a debt greater than 50€, the selected capsules are not available  |


### Use case 2, UC2
| Actors Involved        | Manager, Coffee Supplier, Email System, Banking system |
| ------------- |:-------------:|
|  Precondition     | The manager wants to order capsule boxes from supplier |
|  Post condition     | The payment is successful and the order is sent to supplier |
|  Nominal Scenario     | Send order to coffee supplier |
|  Variants     | The order is not received because of payment problem |

### Use case 3, UC3
| Actors Involved        | Visitor, Manager|
| ------------- |:-------------:|
|  Precondition     | A visitor wants to buy one or more capsules |
|  Post condition     | The visitor has its capsules |
|  Nominal Scenario     | Sell capsules to a visitor |
|  Variants     | The visitor has not enough cash, The requested capsules are not available |

### Use case 4 , UC4
| Actors Involved        | Employee, Manager|
| ------------- |:-------------:|
|  Precondition     | An employee wants to create an account|
|  Post condition     | The employee has its account |
|  Nominal Scenario     | Add a new Employee |
|  Variants     | The information given by the employee are not correct |

### Use case 5 , UC5
| Actors Involved        | Employee, Manager|
| ------------- |:-------------:|
|  Precondition     | An employee wants to remove his account|
|  Post condition     | The account is removed  |
|  Nominal Scenario     | Remove an Employee account |
|  Variants     | The Employee is in debt |

### Use case 6 , UC6
| Actors Involved        | Employee, Manager, Banking System|
| ------------- |:-------------:|
|  Precondition     | An employee wants to add credits to its account paying with credit card or cash |
|  Post condition     | The credits are added to employee account  |
|  Nominal Scenario     | Add credits to an employee |
|  Variants     |  The payment it has not been successful|

### Use case 7, UC7
| Actors Involved        | Employee, Manager|
| ------------- |:-------------:|
|  Precondition     | An employee wants to buy one or more capsules |
|  Post condition     | The employee has its capsules |
|  Nominal Scenario     | Sell capsules to an employee who pays using credits |
|  Variants     | The employee has a debt greater than 50€, the selected capsules are not available  |

### Use case 8, UC8
| Actors Involved        | Manager |
| ------------- |:-------------:|
|  Precondition     | The Manager wants to login to the LaTazza application |
|  Post condition     | The Manager logs in |
|  Nominal Scenario     |                    Verify Manager account                    |
|  Variants     | Login unsuccessful, the manager has to insert his credentials again |

### Use case 9, UC9
| Actors Involved        | Manager |
| ------------- |:-------------:|
|  Precondition     | The Manager wants to add a new supplier |
|  Post condition     | The information about the new supplier are stored |
|  Nominal Scenario     | Add a new supplier  |
|  Variants     | The system already has information about this supplier |

### Use case 10, UC10
| Actors Involved        | Manager |
| ------------- |:-------------:|
|  Precondition     | The Manager wants to set information about price list of a supplier |
|  Post condition     | Information stored by the system |
|  Nominal Scenario     | Update price list    |
|  Variants     | The supplier does not exists |

### Use case 11, UC11
| Actors Involved        | Manager |
| ------------- |:-------------:|
|  Precondition     | The Manager wants to update information about a supplier |
|  Post condition     | Information update stored by the system |
|  Nominal Scenario     | Update Supplier information    |
|  Variants     | The supplier does not exists |

# Relevant scenarios
## Scenario 1

| Scenario ID: SC1        | Corresponds to UC: Sells capsules to an employee who pays cash |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Manager checks employee account. |
|  2     | Manager checks capsules availability |
| 3 | Employee ask the manager for type and quantity of capsules he needs.|
|4 |Manager handles the payment |
| 5 | System updates balance and inventory |
| 6 | Manager gives capsules to Employee |

## Scenario 2
| Scenario ID: SC2       | Corresponds to UC:Send order to Coffee Supplier |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | The Manager selects the boxes quantity for each type of capsule   |
|  2    | The Manager sends order  |
| 3 | The Manager receives the confirmation about the order |
| 4 | The Manager handles the payment  |
| 5 | The Manager waits for the receipt from Coffee Supplier |

## Scenario 3
| Scenario ID: SC3       | Corresponds to UC: Buy capsules from Coffee Supplier, payment failure |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | The Manager selects the quantity in boxes for each type of capsule   |
|  2     | The Manager sends order  |
|  3     | The Manager receives the confirmation about the order |
|  4     | The Manager handles the payment  |
|  5     | The payment is not successful |
|  6     | The Manager receives a failure receipt |

## Scenario 4
| Scenario ID: SC4       | Corresponds to UC: Sell capsules to a visitor|
| ------------- |:-------------:|
| Step#        | Description  |
|  1    | Manager checks for capsules availability  |
|  2     | Visitor asks for a certain quantity and types of capsules   |
| 3 | Visitor pays cash |
| 4 | System updates balance and inventory |
| 5 | Manager gives capsules to Visitor |

## Scenario 5
| Scenario ID: SC5      | Corresponds to UC: Sell capsules to an employee who pays cash (or credits), failure because of employee's debt|
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Manager checks employee account. |
|  2     |  Employee is not able to buy capsules because has a debt greater than 50€ |
| 3 | Manager cancel the sale |

## Scenario 6
| Scenario ID: SC6       | Corresponds to UC: Sell capsules to a visitor, failure because of lack of cash|
| ------------- |:-------------:|
| Step#        | Description  |
|  1    | Manager checks for capsules availability  |
|  2     | Visitor asks for a certain quantity and types of capsules   |
| 3 | Visitor has not enough cash |
| 4 | Manager cancel the sale |

## Scenario 7
| Scenario ID: SC7       | Corresponds to UC: Sell capsules to either employee or visitor, failure because of capsules' unavailability|
| ------------- |:-------------:|
| Step#        | Description  |
|  1    | Manager checks for capsules availability  |
| 2 | Capsules are not available|
| 3 | Manager cancels the sale |

## Scenario 8
| Scenario ID: SC8       | Corresponds to UC: Insert a new employee |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Employee asks for the creation of an account   |
|  2     | Employee gives his data |
|  3     | Manager checks if it already exists  |
|  4     | The account does not exists |
|  5     | Manager creates the new account |
| 6 | Information stored by the system |

## Scenario 9
| Scenario ID: SC9       | Corresponds to UC: Insert a new employee, account already exists |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Employee asks for the creation of an account   |
|  2     | Employee gives his data |
|  3     | Manager checks if it already exists  |
|  4     | The account already exists |
|  5     | Manager does not create the new account |

## Scenario 10
| Scenario ID: SC10       | Corresponds to UC: Remove employee account |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Employee asks for the deletion of an account   |
|  2     | Employee gives his data |
|  3     | Manager checks if the account exists  |
|  4     | The account exists |
|  5     | Manager checks if the employee is in debt|
|  6     | The employee is not in debt  |
|  7     | Manager deletes the correspondent account |

## Scenario 11
| Scenario ID: SC11       | Corresponds to UC: Remove employee account, fails because of inexistent account |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Employee asks for the deletion of his account   |
|  2     | Employee gives his data |
|  3     | Manager checks if the account exists  |
|  4     | The account does not exists |
|  5     | Manager can't delete an inexistent account |

## Scenario 12
| Scenario ID: SC12       | Corresponds to UC: Remove employee account, fails because of employee's debt |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Employee asks for the deletion of an account   |
|  2     | Employee gives his ID |
|  3     | Manager checks if the account exists  |
|  4     | The account exists |
|  5     | Manager checks if the employee is in debt |
|  6     | The employee is in debt  |
|  7     | Manager does not delete the correspondent account |

## Scenario 13
| Scenario ID: SC13      | Corresponds to UC: Add Credits payed by cash |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Employee wants to buy credits by cash |
|  2    | Manager checks employee ID and account|
| 3 | Employee pays for the credits|
| 4 | Manager adds credits to employee's account |

## Scenario 14
| Scenario ID: SC14     | Corresponds to UC: Add Credits payed by credit card |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Employee wants to buy credits by credit card |
|  2    | Manager checks employee ID and account|
| 3 | Employee pays for the credits online|
| 4 | Payment is successful              |
| 5 | Manager adds credits to employee account |

## Scenario 15
| Scenario ID: SC15        | Corresponds to UC: Buy Credits by credit card, payment failure|
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Employee wants to buy credits by credit card |
|  2    | Manager checks employee ID and account|
| 3 | Employee pays for the credits online|
| 4 | Payment is not successful           |
| 5 | Manager doesn't add credits to employee's account |

## Scenario 16

| Scenario ID: SC16        | Corresponds to UC: Sells capsules to an employee who pays using credits |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Manager checks employee account. |
|  2     | Manager checks capsules availability |
| 3 | Employee ask the manager for type and quantity of capsules he needs.|
|4 |Manager handles the payment |
| 5 | System updates balance and inventory |
| 6 | Manager gives capsules to Employee |


## Scenario 17

| Scenario ID: SC17       | Corresponds to UC: Verify manager account |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | The Manager opens the LaTazza application |
|  2     | The Manager inserts his credentials |
| 3 | The Manager success on logging in |

## Scenario 18

| Scenario ID: SC18       | Corresponds to UC: Verify manager account, failure because of invalid credentials |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | The Manager opens the LaTazza application |
|  2     | The Manager inserts his credentials |
| 3 | The Manager fails on logging in |

## Scenario 20

| Scenario ID: SC20       | Corresponds to UC: Add a new supplier |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Manager selects the option to add a new supplier |
| 2 | Manager inserts supplier information |
| 3 | System checks supplier does not exists |
| 4 | Information stored by the system |

## Scenario 21

| Scenario ID: SC21       | Corresponds to UC: Add a new supplier, fail because supplier already exists  |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Manager selects the option to add a new supplier |
| 2 | Manager inserts supplier information |
| 3 | System checks supplier does not exists |
| 4 | The supplier already exists, abort operation |

## Scenario 22

| Scenario ID: SC22       | Corresponds to UC: Update price list |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Manager selects the option to update price list for a supplier |
| 2 | Manager inserts supplier identifier |
| 3 | System checks supplier exists |
| 4 | Manager inserts information about price list |
| 5 | Information stored by the system |

## Scenario 23

| Scenario ID: SC23      | Corresponds to UC: Update price list, fail because supplier does not exists |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Manager selects the option to update price list for a supplier |
| 2 | Manager inserts supplier identifier |
| 3 | System checks supplier exists |
| 4 | Supplier does not exists |
| 5 | System aborts the operation |

## Scenario 24

| Scenario ID: SC24       | Corresponds to UC: Update supplier information |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Manager selects the option to update supplier information |
| 2 | Manager inserts supplier identifier |
| 3 | System checks supplier exists |
| 4 | Manager inserts new information about supplier |
| 5 | Information stored by the system |

## Scenario 25

| Scenario ID: SC25      | Corresponds to UC: Update supplier information, fail because supplier does not exists |
| ------------- |:-------------:|
| Step#        | Description  |
|  1     | Manager selects the option to update supplier information |
| 2 | Manager inserts supplier identifier |
| 3 | System checks supplier exists |
| 4 | Supplier does not exists |
| 5 | System aborts the operation |



# Glossary
``` plantuml


class Manager{
}

class Employee{

name 
surname
ID

}

class Payment {
    ID
    amount
    date
}

class Sale{
    date
    quantity
    ID
    payment method 
}

note top of Sale: payment method must be cash for visitors
note bottom of Sale: Buyer must be Employee OR Visitor


class Visitor{

 name
 surname

}

class Account{

Account_ID
password
balance

}
class CoffeeSupplier{
 name
 e_mail
 VAT number
}

class Order{

 ID_order
 
}

class Capsule{

    ID_capsule
    brand
    type
    price
}

class CapsuleBox{
 ID
 brand
 type
}

class Quantity {
    quantity
}

class Price {
    price
}


Manager "1" -- "1" Account  : handle
Manager "1" -- "1..*" Payment : manage
Employee <|-- Manager
Employee "1" -- "1" Account: own
Payment -- "Banking System" : process
Manager "1" -- "1..*" Order : send
CoffeeSupplier "1" -- "1..*" Order : receive
Order "1" -- "1..*" CapsuleBox
(Order,CapsuleBox) .. Quantity
Capsule --o CapsuleBox
Payment "1" -- "1" Sale
Manager "1" -- "1..*" Sale: perform
Employee "0..1" -- "1..*" Sale
Visitor "0..1" -- "1..*" Sale
Capsule "1..*" -- "1" Sale
CoffeeSupplier "1" -- "1..*" CapsuleBox : sell
(CoffeeSupplier,CapsuleBox) .. Price

```



# System Design
```plantuml

class "LaTazza System" as lt {

  +handleSales()
  +handleAccounts()
    
    }
    
 class Computer{
 
 }
 

 class Software{
     
 }
 
 lt o-- Computer
 Computer -- Software

```