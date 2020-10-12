---
layout: page
title: User Guide
---

ForgetfulNUS is a **desktop app for helping students taking German 1** (LAG1201) **and German 2** (LAG2201) **in NUS to practise their vocabulary, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ForgetfulNUS can get your German revision done faster than traditional GUI apps.

## Table of Contents

- [Quick Start](#qs)
- [Features](#features)
    - [Add Flashcard](#add)
    - [Delete Flashcard](#delete)
    - [View All Flashcards](#list)
    - [Test Yourself](#quiz) (coming soon)
        - Start Testing (coming soon)
        - Testing (coming soon)
        - End Testing (coming soon)
    - Save and Exit (coming soon)
- [FAQ](#faq)
- [Command Summary](#cmdsum)

--------------------------------------------------------------------------------------------------------------------

## <a name="qs"></a>Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `forgetfulnus.jar` from [here](https://github.com/AY2021S1-CS2103T-W16-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ForgetfulNUS.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   [GUI coming soon]

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all flashcards.

   * **`add`**`g/Vergesslichkeit ee/Forgetfulness` : Adds a flashcard with German word `Vergesslichkeit` with English translation `Forgetfulness` to the glossary.

   * **`delete`**`3` : Deletes the 3rd flashcard shown in the glossary.

   * **`quiz`** : Starts a round of vocabulary testing.
   
   * **`end quiz`** : Ends a round of vocabulary testing.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## <a name="features"></a>Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add g/<GERMAN WORD> ee/<ENGLISH WORD>`, `GERMAN WORD` and `ENGLISH WORD` are parameters which can be used as `add g/Vergesslichkeit ee/Forgetfulness`.

</div>

### <a name="add"></a>Adding a flashcard: `add`

Adds a flashcard to the glossary.

Format: `add g/<GERMAN WORD> ee/<ENGLISH WORD>`

Example:
* `add g/Vergesslichkeit ee/Forgetfulness`

### <a name="delete"></a>Deleting a flashcard : `delete`

Deletes the specified flashcard from the glossary.

Format: `delete <INDEX>`

* Deletes the flashcard at the specified `INDEX`.
* The index refers to the index number shown in the displayed glossary.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd flashcard in the glossary.

### <a name="list"></a>Listing all flashcards : `list`

Shows a list of all flashcards in the glossary.

Format: `list`

### <a name="quiz"></a>Test Yourself : `quiz` `end quiz`

Starts a round of vocabulary testing and ends a round of vocabulary testing respectively.

Format: `quiz` `end quiz`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## <a name="faq"></a>FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## <a name="cmdsum"></a>Command summary

Action | Format, Examples
--------|------------------
**Add** | `g/<GERMAN WORD> ee/<ENGLISH WORD>` <br> e.g., `add g/Vergesslichkeit ee/Forgetfulness`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**End Quiz** | `end quiz`
**Exit** | `exit`
**List** | `list`
**Start Quiz** | `quiz`
