# **Developer Guide for ForgetfulNUS**
## Use cases

(For all use cases below, the **System** is `ForgetfulNUS` and the **Actor** is the `User`, unless specified otherwise)

### Use case: UC1 - Adding a flashcard

**MSS:**

1.  User adds a flashcard with German phrase and meaning.
2.  System adds the flashcard and display the newly-added flashcard.

    Use case ends.

**Extensions:**

* 1a. System detects less than 2 fields for the flashcard.

    1a1. System requests the User to input phrase and meaning for the flashcard. 
    
    1a2. User enters a new flashcard or terminates the process.
    
    Steps 1a1-1a2 are repeated until the User input is correct, or the User terminates the process.

   Use case ends.

### Use case: UC2 - Listing the flashcards

**MSS:**

1.  User requests system to list all the flashcards.
2.  System shows the list of flashcards.
    
    Use case ends.

**Extensions:**

* 1a. System detects incorrect command.

    1a1. System shows error and asks for a command in the correct format. 
    
    1a2. User enters a command.

   Use case ends.

### Use case: UC3 - Delete a flashcard

**MSS:**

1.  User deletes a flashcard by the index.
2.  System displays the flashcard to be deleted and asks for confirmation.
3.  User confirms deletion of flashcard.
4.  System deletes the flashcard.
    
    Use case ends.

**Extensions:**

* 3a. User chooses to not delete the flashcard at confirmation.

    3a1. System terminates the process. 
    
   Use case ends.
   
### Use case: UC4 - Self-testing with the flashcards

**MSS:**

1. User requests to start self-testing.
2. System displays a german word.
3. User inputs the corresponding english translation.
4. System displays the results of User's answer.
    
    Steps 2-4 are repeated until there are no more words to be tested.    
    
    Use case ends.

**Extensions:**
   
* *a. At any time, User chooses to stop self-testing.
      
   *a1. System stops self-testing.
      
   Use case ends.
    

--------------------------------------------------------------------------------------------------------------------
