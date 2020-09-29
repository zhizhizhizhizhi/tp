# **ForgetfulNUS: Developer Guide**

## Product scope
#### **Target user profile**:

ForgetfulNUS is targeted at students taking level 1000-2000 German language modules at the NUS Center of Language Studies who can type fast and prefer typing to mouse interactions.

#### **Value proposition**: 

A flashcard CLI app designed to cater to the specific needs of the target user to help them learn their German vocabularies.  

## User Stories

Priority | As a... | I want to... | So that I...
--- | ---------- | --------- | ---- |
*** | user |add a flashcard with German phrase and meaning | can refer it or use it to test myself later.
*** | user | list out all the flashcards with index | can look through the phrases and their meanings to study.
*** | user | delete a flash card by index
*** | user | test myself with the flashcards | can be quizzed on the phrases and their meanings.
** | user | my flashcards to be saved (storage) | can use them when I next launch the app.

## Use Cases

(For all use cases below, the **System** is `ForgetfulNUS` and the **Actor** is the `user`, unless specified otherwise)

#### **Use Case: Add a flashcard**

**MSS:**

1.  User adds a flashcard with German phrase and meaning.
2.  System adds the flashcard and display the newly-added flashcard.

    Use case ends.

**Extensions:**

* 1a. System detects less than 2 fields for flashcard.

    * 1a1. System requests the user to input phrase and meaning for flashcard. 
    
    * 1a2. User enters a new flashcard or terminates the process.
    
    Steps 1a1-1a2 are repeated until the user input is correct or the user terminates the process.

   Use case ends.

#### **Use case: UC2 - Listing all flashcards**

**MSS:**

1.  User requests system to list all the flashcards.
2.  System shows the list of flashcards.
    
    Use case ends.

**Extensions:**

* 1a. System detects incorrect command.

    * 1a1. System shows error and asks for a command in the correct format. 
    
    * 1a2. User enters a command.

   Use case ends.

#### Use case: UC3 - Delete a flashcard**

**MSS:**

1.  User deletes a flashcard by index.
2.  System displays the flashcard to be deleted and asks for confirmation.
3.  User confirms deletion of flashcard.
4.  System deletes the flashcard.
    
    Use case ends.

**Extensions:**

* 3a. User chooses to not delete the flashcard at confirmation.

    * 3a1. System terminates the process. 
    
   Use case ends.

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. A user with above average typing speed for regular English text should be able to accomplish most of the tasks faster using commands than using the mouse.
3. German diacritics (eg. ä) should be fully supported in being saved and displayed by the UI.
4. Verification of user input in testing mode should not take more than 2 seconds.

## Glossary

* **Mainstream OS:** Windows, Linux, Unix, OS-X
* **Flashcard:** An item containing (a) a German phrase (b) the corresponding English definition
* **German phrase:** German text of any length
* **Index:** Position of flashcard in the list of flashcards displayed to the user
* **CLI:** Command Line Interface
--------------------------------------------------------------------------------------------------------------------
