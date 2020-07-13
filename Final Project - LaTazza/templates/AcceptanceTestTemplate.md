# Acceptance Testing Documentation template

Authors: Antonino Angi, Giacomo Blanco, Erich Malan, Klodiana Cika

Date:22/05/2019

Version: 1.1

# Contents

- [Scenarios](#scenarios)

- [Coverage of scenarios](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Scenarios

```
<Define here additional scenarios for the application. The two original scenarios in the Requirements Document are reported here.>
```

| Scenario ID: SC1 | Corresponds to UC1                             |
| ---------------- | ---------------------------------------------- |
| Description      | Colleague uses one capsule of type 1           |
| Precondition     | account of C has enough money to buy capsule T |
| Postcondition    | account of C updated, count of T updated       |
| Step#            | Step description                               |
| 1                | Administrator selects capsule type T           |
| 2                | Administrator selects colleague C              |
| 3                | Deduce one for quantity of capsule T           |
| 4                | Deduce price of T from account of C            |



| Scenario ID: SC2 | Corresponds to UC1                                     |
| ---------------- | ------------------------------------------------------ |
| Description      | Colleague uses one capsule of type T, account negative |
| Precondition     | account of C has not enough money to buy capsule T     |
| Postcondition    | account of C updated, count of T updated               |
| Step#            | Step description                                       |
| 1                | Administrator selects capsule type T                   |
| 2                | Administrator selects colleague C                      |
| 3                | Deduce one for quantity of capsule T                   |
| 4                | Deduce price of T from account of C                    |
| 5                | Account of C is negative, issue warning                |



| Scenario ID: SC3 | Corresponds to UC2                    |
| ---------------- | ------------------------------------- |
| Description      | Visitor uses capsules of type T       |
| Precondition     | there are enough capsules T           |
| Postcondition    | count of T updated                    |
| Step#            | Step description                      |
| 1                | Administrator selects capsule type T  |
| 2                | Administrator selects visitor         |
| 3                | Deduce selected quantity of capsule T |
| 4                | Record sale to visitor                |
| 5                | Update shared balance                 |



| Scenario ID: SC4 | Corresponds to UC2                                   |
| ---------------- | ---------------------------------------------------- |
| Description      | Visitor uses capsules of type T, not enough capsules |
| Precondition     | there are not enough capsules T                      |
| Postcondition    | issued a warning                                     |
| Step#            | Step description                                     |
| 1                | Administrator selects capsule type T                 |
| 2                | Administrator selects visitor                        |
| 3                | Deduce selected quantity of capsule T                |
| 4                | There are not enough capsules T, issue warning       |



| Scenario ID: SC5 | Corresponds to UC3                         |
| ---------------- | ------------------------------------------ |
| Description      | Employee C recharge his account            |
| Precondition     | employee account exists                    |
| Postcondition    | employee balance updated                   |
| Step#            | Step description                           |
| 1                | Administrator selects employee C           |
| 2                | Administrator selects recharge amount      |
| 3                | Administrator proceeds to account recharge |
| 4                | Record recharge                            |
| 5                | Update shared balance and employee account |



| Scenario ID: SC6 | Corresponds to UC4                              |
| ---------------- | ----------------------------------------------- |
| Description      | Administrator purchase N capsules box of T      |
| Precondition     | Shared balance is enough, capsule type T exists |
| Postcondition    | shared balance and capsules amount updated      |
| Step#            | Step description                                |
| 1                | Administrator selects capsule type T            |
| 2                | Administrator selects number of capsule boxes   |
| 3                | Administrator proceeds to purchase              |
| 4                | Record purchase                                 |
| 5                | Update shared balance and capsules amount       |



| Scenario ID: SC7 | Corresponds to UC4                              |
| ---------------- | ----------------------------------------------- |
| Description      | Administrator purchase N capsules box of T      |
| Precondition     | Shared balance is not enough, capsule type T exists|
| Postcondition    | issued a warning                                |
| Step#            | Step description                                |
| 1                | Administrator selects capsule type T            |
| 2                | Administrator selects number of capsule boxes   |
| 3                | Deduce selected quantity of N capsules box of T |
| 4                | Deduce total price of N*T from shared balance   |
| 5                | Shared balance is negative, issue warning       |



| Scenario ID: SC8 | Corresponds to UC5                         |
| ---------------- | ------------------------------------------ |
| Description      | Administrator search for transaction of an employee E in a time period|
| Precondition     | employee account exists, period is valid   |
| Postcondition    | employee transactions are shown            |
| Step#            | Step description                           |
| 1                | Administrator selects employee E           |
| 2                | Administrator selects time period T        |
| 3                | Deduce selected period                     |
| 4                | Administrator proceeds to get report       |
| 5                | Employee E records are shown               |



| Scenario ID: SC9 | Corresponds to UC5                         |
| ---------------- | ------------------------------------------ |
| Description      | Administrator search for transaction of an employee E in a time period|
| Precondition     | employee account exists, period is not valid|
| Postcondition    | issued a warning, report not shown         |
| Step#            | Step description                           |
| 1                | Administrator selects employee E           |
| 2                | Administrator selects time period T        |
| 3                | Deduce selected period                     |
| 4                | one or both dates are not valid, issue warning|



| Scenario ID: SC10 | Corresponds to UC6                         |
| ---------------- | ------------------------------------------ |
| Description      | Administrator search for transaction in a time period|
| Precondition     | time period is valid                       |
| Postcondition    | report is shown                            |
| Step#            | Step description                           |
| 1                | Administrator selects time period T        |
| 2                | Deduce selected period                     |
| 3                | Administrator proceeds to get report       |
| 4                | record is shown                            |



| Scenario ID: SC11 | Corresponds to UC6                         |
| ---------------- | ------------------------------------------ |
| Description      | Administrator search for transaction in a time period|
| Precondition     | time period is not valid                   |
| Postcondition    | issued a warning, report not shown         |
| Step#            | Step description                           |
| 1                | Administrator selects time period T        |
| 2                | Deduce selected period                     |
| 3                | one or both dates are not valid, issue warning|


# Coverage of Scenarios

```
<Report in the following table the coverage of the scenarios listed above. Report at least an API test (defined on the functions of DataImpl only) and a GUI test (created with EyeAutomate) for each of the scenarios. For each scenario, report the functional requirements it covers.
In the API Tests column, report the name of the method of the API Test JUnit class you created. In the GUI Test column, report the name of the .txt file with the test case you created.>
```

### 

| Scenario ID | Functional Requirements covered | API Test(s)                                            | GUI Test(s)                           |
| ----------- | ------------------------------- | ------------------------------------------------------ | ------------------------------------- |
| 1           | FR1                             | it.polito.latazza.data.<br />TestSystem.testScenario1 | guitests/testSC1/<br />SC1.txt |
| 2           | FR1                             | it.polito.latazza.data.<br />TestSystem.testScenario2 | guitests/testSC2/<br />SC2.txt |
| 3           | FR2                             | it.polito.latazza.data.<br />TestSystem.testScenario3 | guitests/testSC3/<br />SC3.txt |
| 4           | FR2                             | it.polito.latazza.data.<br />TestSystem.testScenario4 | guitests/testSC4/<br />SC4.txt |
| 5           | FR3                             | it.polito.latazza.data.<br />TestSystem.testScenario5 | guitests/testSC5/<br />SC5.txt |
| 6           | FR4                             | it.polito.latazza.data.<br />TestSystem.testScenario6 | guitests/testSC6/<br />SC6.txt |
| 7           | FR4                             | it.polito.latazza.data.<br />TestSystem.testScenario7 | guitests/testSC7/<br />SC7.txt |
| 8           | FR5                             | it.polito.latazza.data.<br />TestSystem.testScenario8 | guitests/testSC8/<br />SC8.txt |
| 9           | FR5                             | it.polito.latazza.data.<br />TestSystem.test1Scenario9 <br /> TestSystem.test2Scenario9| guitests/testSC9/<br />SC9.txt |
| 10 | FR6 | it.polito.latazza.data.<br />TestSystem.testScenario10 | guitests/testSC9/<br />SC10.txt |
| 11 | FR6 |it.polito.latazza.data.<br />TestSystem.test1Scenario11 <br /> TestSystem.test2Scenario11 | guitests/testSC9/<br />SC11.txt |



# Coverage of Non Functional Requirements

```
<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>
```

### 

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|               NFR2             |  testPerformanceFR1     |
|               NFR2             |  testPerformanceFR2     |
|               NFR2             |  testPerformanceFR3     |
|               NFR2             |  testPerformanceFR4     |
|               NFR2             |  testPerformanceFR5     |
|               NFR2             |  testPerformanceFR6     |
|               NFR2             |  testPerformanceFR7     |
|               NFR2             |  testPerformanceFR8     |