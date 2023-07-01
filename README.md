# Bank case

Now that we have covered some basic programming skills, like syntax and compiling an application, it is time to move on
to the next steps. As applications grow, it becomes harder to find the code you need to change. Also, when changing
code, how do you know each part of the application is functioning correctly?

The goal of this module is to understand the basic layout of a project. Therefore, you will set up your own basic
project which will consist of two modules (which in this case coincide with layers): one for the presentation and one
for the business logic. After creating a basic project, each module must be able to run its own unit tests.

Finally, you will be able to correctly implement the single most import capability of a bank, the transfer of money. The
goal here to prove, using unit tests, this capability is implemented correctly.

## Goals

* Create a project structure using Maven or Gradle.
* Identify layers, specifically the presentation and business logic layer, within the application.
* Refactoring; extracting the presentation layer from the business logic layer.
* Write the first unit tests.
* Using an IDE and its debugger.

One final note: The code in this repository mimics code in the real world. Please be aware that some parts of it are not 
up to coding standards. 

# Part I - Project layout

## Assignment 1

Identify which part of the provided code is presentation logic and which part is domain logic.

## Assignment 2

Create
a [Maven](https://git.sogyo.nl/academy/mastercourses/softwareengineering/exercises/build-automation-debugging-unit-testing/build-automation/-/blob/master/README.maven.md#introduction-to-maven)
or [Gradle](https://git.sogyo.nl/academy/mastercourses/softwareengineering/exercises/build-automation-debugging-unit-testing/build-automation/-/blob/master/README.gradle.md)
multi-module project.

* The project should be committed to Git.
* The project should contain an overall module or multi-project build, which contains two submodules. One module for 
  the business logic, called 'domain' and one module for the presentation, called 'client'. Make sure to link the 
  modules to their parent.
* Create a dependency from the 'client' module to the 'domain' module. When building, make sure the 'domain' module is
  the first to be built.
* Move the source code you have identified as presentation and business logic into their respective modules. When
  completed, all code should compile correctly using ```mvn clean package``` when using Maven or ```gradle clean build```
  when using Gradle. Also make sure that you can run and debug the bank app using your IDE.

## Assignment 3

Add [unit testing capabilities](https://git.sogyo.nl/academy/mastercourses/softwareengineering/exercises/build-automation-debugging-unit-testing/build-automation/-/blob/master/README.maven.md#32-domain-tests)
for both 'domain' and 'client' modules.

* Add the appropriate plugins (JUnit 5) to the module(s).
* Create a single failing test (using ```org.junit.jupiter.Assertions.fail()```)

<details>
  <summary>Hint</summary> 

When using Maven, make sure to use the maven-surefire-plugin version 2.22.0 or higher.
</details>

When completed, the build should compile all the source code but should fail when running the unit tests. If this is the
case, you can remove the failing test.

## Assignment 4

Make sure
to [define the dependency on the testing plugin in a single spot](https://git.sogyo.nl/academy/mastercourses/softwareengineering/exercises/build-automation-debugging-unit-testing/build-automation/-/blob/master/README.maven.md#5-sharing-and-enforcing-dependencies-over-the-multi-module-project)
, so we can easily update the version used. We don't want to update several files to do so.
<details>
  <summary>Hint</summary> 

Use parent build file.
</details>

When completed, the project should compile and test successfully.

# Part II - Debugging

## Assignment 5

Find out why the application fails to properly create clients. All we know, by running the application, is that clients
can be created, but clients with full information are not able to be found when creating the account.

The goal of this is assignment is to find the cause of the problem by using the debugger (step in, step over and step
out). Try to find the cause of the problem with a debugger while creating client(s) and account(s).

Which expression is the cause of the problem?

<details>
<summary>Key takeaway</summary>

When debugging, you will have to start a complete application. Every time we need to create the conditions necessary to
trigger the bug we are chasing, which is a lot of work. If we want to find the cause of a specific problem faster, we
will need to be able to start and test smaller parts of the application.
</details>

## Assignment 6

This project makes use of 'value objects'. Value objects are meant to be serialized (converted to Strings, so they can
be sent as text or binary over the network). However, when two instances are created with the same value Java, by
default, does not recognize them as equal. The code in this repository violates
[the rules concerning `equals` and `hashCode`](https://www.baeldung.com/java-equals-hashcode-contracts). This problem 
becomes apparent when adding these objects to a set.

To verify correct behaviour, we will be writing [unit tests](https://www.baeldung.com/junit-5).
<details>
<summary>Hint</summary>

Place the unit tests in a directory where they will be picked up by your build tool automatically. Every unit test
should adhere to the following pattern

```java
public class Test {
    @Test
    void shouldOrMustDoSomething() {
        // Arrange (some data)

        // Act (on something)

        // Assert (something is (not) true, equal or the same)
    }
}
```

</details>

### Test equality

Create a unit test where object equality is tested. The unit test should create one Client, two CheckingAccounts and use
an assertion to test if they are equal. This test should pass.

### Test hashcode

Create a unit test where the object's hash code is tested. The unit test should create one Client, two CheckingAccounts
and add them to a Set, e.g. a HashSet. Use an assertion to test if the set contains one item. Another option is to
compare the hash codes of the two CheckingAccounts.

If this test fails, modify the code so the test passes.

<details>
<summary>Key takeaway</summary>

Never use == to compare objects! When using equals, make sure to override both ```equals()``` and ```hashCode()```.
[Preferably](https://www.baeldung.com/java-comparator-comparable), use the ```java.lang.Comparable``` for sorting
objects and write a ```java.util.Comparator``` to compare objects.
</details>

# Part III - Transferring money

The application provided contains no (unit) tests whatsoever. The goal is to implement the logic needed to transfer an
amount of money from one bank account to another. However, business rules dictate that certain conditions must be met.

## Assignment 7

Create multiple unit tests which verify the use case/requirements below and implement the code accordingly. Start by
writing a unit test for a CheckingAccount as there is a (naive) implementation present for this use case.

### Use case - Money transfer

#### Actors

* Customer
* Bank host

#### Preconditions

1. The account from which the amount is withdrawn cannot be overdrawn and therefore must have sufficient funds 
available. If there are insufficient funds available, the transfer should be aborted.
2. The transferred amount must be a positive decimal number, rounded to two decimals.

<details>
<summary>See hint</summary>

```
// Use a simple function to round to two decimals
Math.round(amount * 100d) / 100d;
```

</details>

3. The destination account (to which the amount is to be transferred) is selected.
4. When transferring money to an account, which is not your own, the account from which the money is withdrawn should be
   a CheckingAccount.

#### Postconditions

1. The amount withdrawn is the exact amount transferred to the destination account. No money may be created or lost
   during this process.

If you can think of anything else to write a test case for, please feel free to do so.

<details>
<summary>Key takeaway</summary>

Testing, especially in case of subclasses, requires us to think about changes in the future. If we consider a specific
account as a black box, i.e., we don't know that it is a subclass of ```BankAccount``` or will be in the future, we have
to perform (some of) the same tests on all account types.
</details>

# Part IV - Increase testability

This part is optional, if you've finished the other parts and want an extra challenge.

## Assignment 8

In Assignment 5, we found a bug in the client using the debugger, but we did not fix it, or even write tests for it. As
things stand, the client part of the bank app is very hard to test, because user interaction (taking input from the 
console, printing messages) is mixed with logic. The goal of this assignment is to make the client more testable, 
starting with the bug we found in Assignment 5. Use the following steps:

1. Isolate the code that was responsible for the bug in Assignment 5, and make it into a separate method.

<details>
<summary>See hint</summary>

You'll want a method with the following declaration:

```
static List<String> breakUpString(String input)
```

</details>

2. Write unit tests for your method.
3. Change the code so that the unit tests pass.
