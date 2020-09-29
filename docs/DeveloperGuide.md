## **Developer Guide for ForgetfulNUS**

### Product scope

**Target user profile**:

ForgetfulNUS is targeted at students taking level 1000-2000 German language modules at the NUS Center of Language Studies who can type fast and prefers typing to mouse interactions.

**Value proposition**: 

A flashcard CLI app designed to cater to the specific needs of the target user to help them learn their German vocabularies.  

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                     | I want to …​                                                    | So that I …​                                                 |
| -------- | ------------------------------------------ | ---------------------------------------------------------------| ------------------------------------------------------------|
| `* * *`  | user                                       | add a flashcard with german phrase and meaning                 | can refer it or use it to test myself later.                |
| `* * *`  | user                                       | list out all the flashcards with index                         | can look through the phrases and their meanings to study.   |                                                  |
| `* * *`  | user                                       | delete a flash card by index                                   |                                                             |
| `* * *`  | user                                       | test myself with the flashcards                                | can be quizzed on the phrases and their meanings.           |
| `* *`    | user                                       | my flashcards to be saved (storage)                            | can use them when I next launch the app.                    |

*{More to be added soon}*

### Use cases

(For all use cases below, the **System** is `ForgetfulNUS` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 - Adding a flashcard**

**MSS:**

1.  User adds a flashcard with German phrase and meaning.
2.  System adds the flashcard and display the newly-added flashcard.

    Use case ends.

**Extensions:**

* 1a. System detects less than 2 fields for flashcard.

    1a1. System requests the user to input phrase and meaning for flashcard. 
    
    1a2. User enters a new flashcard or terminates the process.
    
    Steps 1a1-1a2 are repeated until the user input is correct or the user terminates the process.

   Use case ends.

**Use case: UC2 - Listing the flashcards**

**MSS:**

1.  User requests system to list all the flashcards.
2.  System shows the list of flashcards.
    
    Use case ends.

**Extensions:**

* 1a. System detects incorrect command.

    1a1. System shows error and asks for a command in the correct format. 
    
    1a2. User enters a command.

   Use case ends.

**Use case: UC3 - Delete a flashcard**

**MSS:**

1.  User deletes a flashcard by index.
2.  System displays the flashcard to be deleted and asks for confirmation.
3.  User confirms deletion of flashcard.
4.  System deletes the flashcard.
    
    Use case ends.

**Extensions:**

* 3a. User chooses to not delete the flashcard at confirmation.

    3a1. System terminates the process. 
    
   Use case ends.

--------------------------------------------------------------------------------------------------------------------
