---
layout: page
title: Developer Guide
---
* [**Setting up, getting started**](#setup)
* [**Design**](#design)
   * [Architecture](#architecture)
   * [UI component](#ui_component)
   * [Logic component](#logic_component)
   * [Model component](#model_component)
   * [Storage component](#storage_component)
   * [Common classes](#common_classes)
* [**Implementation**](#implementation)
   * [[Proposed] Undo/redo feature](#undo_redo)
     * [Proposed Implementation](#proposed_implementation)
     * [Design consideration:](#design_consideration)
       * [Aspect: How undo & redo executes](#aspect_undo_redo)
  * [[Proposed] Data archiving](#data_archiving)
* [**Documentation, logging, testing, configuration, dev-ops**](#documentation_etc)
* [**Appendix: Requirements**](#requirements)
  * [Product scope](#product_scope)
  * [User stories](#user_stories)
  * [Use cases](#use_cases)
  * [Non-Functional Requirements](#nfr)
  * [Glossary](#glossary)
* [**Appendix: Instructions for manual testing**](#manual_testing)
  * [Launch and shutdown](#launch_shutdown)
  * [Deleting a student](#deleting_a_flashcard)
  * [Saving data](#saving_data)

--------------------------------------------------------------------------------------------------------------------

## <a name="setup"></a>**Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

## <a name="design"></a>**Design**

### <a name="architecture"></a>Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S1-CS2103T-W16-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103T-W16-2/tp/blob/master/src/main/java/seedu/forgetfulnus/Main.java) and [`MainApp`](https://github.com/AY2021S1-CS2103T-W16-4/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initialises the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common_classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui_component): The UI of the App.
* [**`Logic`**](#logic_component): The command executor.
* [**`Model`**](#model_component): Holds the data of the App in memory.
* [**`Storage`**](#storage_component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.
For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### <a name="ui_component"></a>UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103T-W16-2/tp/tree/master/src/main/java/seedu/forgetfulnus/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### <a name="logic_component"></a>Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103T-W16-2/tp/tree/master/src/main/java/seedu/forgetfulnus/logic/Logic.java)

1. `Logic` uses the `GlossaryBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a Flashcard).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("add g/German e/English")` API call.

![Interactions Inside the Logic Component for the `add g/German e/English` Command](images/AddSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### <a name="model_component"></a>Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-W16-2/tp/tree/master/src/main/java/seedu/forgetfulnus/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the glossary data.
* exposes an unmodifiable `ObservableList<Flashcard>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `Glossary`, which `Flashcard` references. This allows `Glossary` to only require one `Tag` object per unique `Tag`, instead of each `Flashcard` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### <a name="storage_component"></a>Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103T-W16-2/tp/tree/master/src/main/java/seedu/forgetfulnus/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### <a name="common_classes"></a>Common classes

Classes used by multiple components are in the `seedu.forgetfulnus.commons` package.
--------------------------------------------------------------------------------------------------------------------
## <a name="implementation"></a>**Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Randomly generating a specified number of flashcards from the glossary to self-test

This feature is facilitated by `RandomQuizCommand` and `RandomQuizCommandParser` and `Model`.

`RandomQuizCommand` implements the method:

* `RandomQuizCommand#execute(Model)` — Returns a `CommandResult` which begins a round of vocabulary quiz containing the specified number of flashcards randomly selected from the existing glossary.

`RandomQuizCommandParser` implements the method:

* `RandomQuizCommandParser#parse(String)` — Returns a `RandomQuizCommand` which ensures the specified number of flashcards is valid.

`Model` implements the method:

* `Model#setRandomQuizMode(boolean)` — Sets the state of Model to randomQuizMode and backs up or retrieves the original glossary depending on the boolean value.

Given below is an example usage scenario and how the random quiz mechanism behaves at each step.

Step 1. The user launches the application with an existing glossary of flashcards that the user added previously.

Step 2. The user executes `random 5` command to randomly select 5 flashcards from the existing glossary to test his/her own vocabulary. The `random 5` command calls `RandomQuizCommandParser#parse(String)` which checks the validity of the argument given to `random` command. This then leads to the calling of `RandomQuizCommand#execute(Model)`, which in turn calls the `Model#setRandomQuizMode(boolean)`.

Step 3. The change of state of the Model resulting from `Model#setRandomQuizMode(boolean)` starts a round of vocabulary quiz for the user when the boolean parameter provided is true.

The following sequence diagram shows how the random quiz mechanism works:

![RandomQuizSequenceDiagram](images/RandomQuizSequenceDiagram.png)

The following activity diagram summarises what happens when a user executes the random command:

![RandomQuizActivityDiagram](images/RandomQuizActivityDiagram.png)


### <a name="score"></a>\[Proposed\] Score report feature:

The proposed feature saves scores from previous iterations of the quiz mode, which can be accessed by the user with the `ViewScoreCommand`. Scores are saved as percentage of questions answered correctly in a local file, which is exposed in the `Storage` interface as `Storage#getScoreFilePath()`.

Each time the quiz mode is entered and ended, a score is calculated as a percentage of correct answers input by the user and encapsulated by a `Score` object. The `Score` is added to a `ScoreList`, where the following methods are implemented:

`ScoreList#addScore()`
`ScoreList#getScores()`
`ScoreList#deleteScores()`

These operations are exposed in the Model interface as `Model#addScore()`, `Model#getScores()` and `Model#deleteScores()` respectively.

The following sequence diagram shows how the score is saved:

### Predefined tags feature

There are two types of predefined tags for each flash cards. They are the `DifficultyTag` and `GenderTag`. 

The following activity diagram summarises what happens for the `DifficultyTag` when a user executes the Add command:

![DifficultyTagActivityDiagram](images/DifficultyTagActivityDiagram.png)

For `GenderTag` the activity diagram is similar, with the default tag being `NONE`.

The following class diagram outlines the structure of the predefined tags and how it interacts with other `Model` components.

![PredefinedTagClassDiagram](images/PredefinedTagClassDiagram.png)

As both the `DifficultyTag` and `GenderTag` can share similar code, they are extended from `PredefinedTags`.


### <a name="data_archiving"></a>\[Implemented\] Quizzing
The proposed quiz feature for users to test their vocabulary is facilitated by `Model` and `Command`. It does so by allowing a command to set `Model` to quiz mode. When the model is in quiz mode, it will take in commands allowing users to attempt to type the correct definition, skip the flashcard under test or end the quiz.

It implements the following operations:

* `Glossary#quiz(Model)` — Starts the quizzing with the displayed flashcard list.
* `Glossary#next(Model)` — Attempt to type the correct English definition of the German phrase on the current flashcard.
* `Glossary#next(Model)` — Skips the current flashcard under test.
* `Glossary#end(Model)` — Ends the quiz.

These operations are exposed in `Ui` as commands. They are implemented by `QuizCommand`, `TryCommand`, `NextCommand` and `EndQuizCommand` respectively.

Given below is an example usage scenario and how the quiz mechanism behaves at each step.

Step 1: The user launch the application with an existing list of flashcard. Flashcards from this list will be tested on in the order of their index, and the list can be customised by using the `find <search phrase>` command.

Step 2: User enters `quiz` and the program will execute the QuizCommand on the current model. The model will be set to quiz mode and will be expecting quiz commands like `try <attempt>`, `next` and `end`. The Ui will update to hide all the English definitions on the flashcards. The first flashcard on the list will be tested when the quiz begins.

Step 3: The user enters `try <attempt>` and the `GlossaryParser#parse(String)` will parse input into a TryCommand with the attempt. If the attempt matches the English definition of the flashcard, the flashcard index, score and question count in `model` increment. The Ui will update to show the English definition of the current flashcard. The next flashcard on the list will be tested. 

If the attempt does not match, step 3 will repeat.
Alternatively, the user can enter `next` to execute the NextCommand on the model. The flashcard index and question count in `model` will increment, the Ui will update to show the English definition of the current flashcard andthe next flashcard and the next flashcard will be tested. 

Step 4: The quiz mode will end when there is no next flashcard i.e. current flashcard is the last on the list, and the user attempts the English definition correctly with `try <attempt>` or the user skips the card with `next`. Alternatively, the quiz can be ended early at any point during the quiz when the user enters `end`, letting the program execute the EndQuizCommand on the current model. The Ui will update to show the English definitions on all the flashcards in the flashcard list.

The following activity diagram outlines the process of quizzing:
![QuizActivityDiagram](images/QuizActivityDiagram.png)

The following sequence diagram shows how the quiz operation works:

![QuizCommandSequenceDiagram](images/QuizCommandSequenceDiagram.png)

### \[Implemented\] Sort Feature

The Sort feature is implemented as a way for users to further customise the glossary and make it easier for them to find phrases they want.

Sorting is implemented as a `SortCommand` class which extends from the abstract `Command` class and makes use of a `SortCommandParser` to parse the parameters input by the user.
This is in line with the original AddressBook3's Command pattern.

`SortCommand` relies on several pre-defined `Comparator`s to execute the sorting, one of which is selected for use when
the user's input is successfully parsed. For example, when the user inputs `sort english`, a SortCommand object is created
with a `Comparator` to compare the `EnglishPhrase`s of each `FlashCard` object in the `Glossary`.

This class diagram outlines the structure of `SortCommand` and `SortCommand` and how they interact with 
other aspects of the program.

![SortCommandClassDiagram](images/SortCommandClassDiagram.png)

The following sequence diagram briefly outlines the execution process when a user enters the command "sort english":

![SortCommandSequenceDiagram](images/SortCommandSequenceDiagram.png)

1. The user command is first passed into `LogicManager`, which calls upon `GlossaryParser` to parse the command.
1. `GlossaryParser` identifies the input as a command to sort the glossary, creates a `SortCommandParser` and calls its `parse(String)` method.
1. The new `SortCommandParser` parses the parameter and creates a new `SortCommand`.
1. `LogicManager` calls the new `SortCommand`'s `execute(model)` method.
1. `execute()` calls `SortCommand`'s own `getSortedGlossary()` method to obtain a sorted `Glossary`.
1. The sorted `Glossary` replaces the current `Glossary` in `Model`.
1. The result of the command execution is encapsulated as a CommandResult object which is passed back to the `Ui`.

**Note:** The lifeline for `SortCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

#### Alternatives:
1. Sorting replaces the entire Glossary with a new sorted Glossary (current implementation)

   - Pros: Fairly adaptable from existing commands
   - Cons: Large glossary size may lead to computational delays and overhead

1. Sorting sorts the current Glossary in place instead of creating a new Glossary

   - Pros: Less computational overhead
   - Cons: Original AB3 implementation uses immutable Glossary equivalent, requires very significant refactoring of code to achieve.
   Using a mutable Glossary also makes the code more vulnerable.

--------------------------------------------------------------------------------------------------------------------
## <a name="documentation_etc"></a>**Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)
--------------------------------------------------------------------------------------------------------------------
## <a name="requirements"></a>**Appendix: Requirements**

### <a name="product_scope"></a>Product scope

#### **Target user profile**:

ForgetfulNUS is targeted at students taking level 1000-2000 German language modules at the NUS Center of Language Studies who can type fast and prefer typing to mouse interactions.

#### **Value proposition**: 

A flashcard CLI app designed to cater to the specific needs of the target user to help them learn their German vocabularies.  

### <a name="user_stories"></a>User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

Priority | As a... | I want to... | So that I...
--- | ---------- | --------- | ---- |
*** | user |add a flashcard with German phrase and meaning | can refer it or use it to test myself later.
*** | user | list out all the flashcards with index | can look through the phrases and their meanings to study.
*** | user | delete a flash card by index
*** | user | test myself with the flashcards | can be quizzed on the phrases and their meanings.
** | user | my flashcards to be saved (storage) | can use them when I next launch the app.

### <a name="use_cases"></a>Use Cases

(For all use cases below, the **System** is `ForgetfulNUS` and the **Actor** is the `user`, unless specified otherwise)

#### **Use Case: UC1- Add a flashcard**

**MSS:**

1.  User adds a flashcard with German phrase and meaning.
2.  ForgetfulNUS adds the flashcard and display the newly-added flashcard.

    Use case ends.

**Extensions:**

- 1a. ForgetfulNUS detects less than 2 fields for the flashcard.

    - 1a1. ForgetfulNUS requests the User to input phrase and meaning for the flashcard.     
    - 1a2. User enters a new flashcard or terminates the process.
    
    Steps 1a1-1a2 are repeated until the user input is correct or the user terminates the process.

   Use case ends.

#### **Use case: UC2 - List all flashcards**

**MSS:**

1.  User requests ForgetfulNUS to list all the flashcards.
2.  ForgetfulNUS shows the list of flashcards.
    
    Use case ends.

**Extensions:**

- 1a. ForgetfulNUS detects incorrect command.

    - 1a1. ForgetfulNUS shows error and asks for a command in the correct format. 
    
    - 1a2. User enters a command.

   Use case ends.

#### **Use case: UC3 - Delete a flashcard**

**MSS:**

1.  User deletes a flashcard by the index.
2.  ForgetfulNUS displays the flashcard to be deleted and asks for confirmation.
3.  User confirms deletion of flashcard.
4.  ForgetfulNUS deletes the flashcard.

    Use case ends.

**Extensions:**

- 3a. User chooses to not delete the flashcard at confirmation.

    - 3a1. ForgetfulNUS terminates the process. 
    
   Use case ends.
   
#### **Use case: UC4 - Self-testing with flashcards**

**MSS:**

1. User requests to start self-testing.
2. ForgetfulNUS displays a german word.
3. User inputs the corresponding english translation.
4. ForgetfulNUS displays the results of User's answer.

    Steps 2-4 are repeated until there are no more words to be tested.    

    Use case ends.

**Extensions:**

- 4a. At any time, User chooses to stop self-testing.

   - 4a1. ForgetfulNUS stops self-testing.

   Use case ends.

*{More to be added soon}*

### Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. A user with above average typing speed for regular English text should be able to accomplish most of the tasks faster using commands than using the mouse.
3. German diacritics (eg. ä) should be fully supported in being saved and displayed by the UI.
4. Verification of user input in testing mode should not take more than 2 seconds.

### <a name="glossary"></a>Glossary

* **Mainstream OS:** Windows, Linux, Unix, OS-X
* **Flashcard:** An item containing (a) a German phrase (b) the corresponding English definition
* **German phrase:** German text of any length
* **Index:** Position of flashcard in the list of flashcards displayed to the user
* **CLI:** Command Line Interface
--------------------------------------------------------------------------------------------------------------------
## <a name="manual_testing"></a>**Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### <a name="launch_shutdown"></a>Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample flashcards. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### <a name="deleting_a_flashcard"></a>Deleting a flashcard

1. Deleting a flashcard while all flashcards are being shown

   1. Prerequisites: List all flashcards using the `list` command. Multiple flashcards in the list.

   1. Test case: `delete 1`<br>
      Expected: First flashcard is deleted from the list. Details of the deleted flashcard shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No flashcard is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### <a name="saving_data"></a>Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
