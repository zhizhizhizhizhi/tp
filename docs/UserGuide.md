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
    - [Clear All Flashcards](#clear)
    - [View All Flashcards](#list)
    - Test Yourself
        - [Normal Mode](#quiz)
        - [Random Mode](#random)
        - [End Testing](#end)
    - [Save and Exit](#exit)
- [FAQ](#faq)
- [Command Summary](#cmdsum)

--------------------------------------------------------------------------------------------------------------------

## <a name="qs"></a>Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ForgetfulNUS.jar` from [here](https://github.com/AY2021S1-CS2103T-W16-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ForgetfulNUS.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   [GUI coming soon]

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all flashcards.

   * **`add`**`g/Vergesslichkeit e/Forgetfulness` : Adds a flashcard with German word `Vergesslichkeit` with English translation `Forgetfulness` to the glossary.

   * **`delete`**`3` : Deletes the 3rd flashcard shown in the glossary.

   * **`quiz`** : Starts a round of vocabulary testing with all existing flashcards in the glossary.
   
   * **`random`**`5` : Starts a round of vocabulary testing with 5 flashcards randomly chosen from the existing glossary.

   * **`end`** : Ends a round of vocabulary testing.

   * **`clear`** : Deletes all flashcards.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## <a name="features"></a>Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add g/<GERMAN WORD> e/<ENGLISH WORD>`, `GERMAN WORD` and `ENGLISH WORD` are parameters which can be used as `add g/Vergesslichkeit e/Forgetfulness`.

</div>

### <a name="add"></a>Adding a flashcard: `add`

Adds a flashcard to the glossary.

Format: `add g/<GERMAN WORD> e/<ENGLISH WORD>`

Example:
* `add g/Vergesslichkeit e/Forgetfulness`

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

### <a name="quiz"></a>Normal Test : `quiz`

Starts a round of vocabulary testing with all the flashcards that are currently in the glossary.

Format: `quiz`

### <a name="random"></a>Random Test : `random`

Starts a round of vocabulary testing with the specified number of flashcards randomly selected from the existing glossary.

Format: `random <NUMBER>`

### <a name="end"></a>End Test : `end`

Ends the round of vocabulary testing.

Format: `end`

### <a name="clear"></a>Clearing all entries : `clear`

Clears all entries from the glossary.

Format: `clear`

### <a name="exit"></a>Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ForgetfulNUS data are automatically saved in the hard disk upon exiting. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## <a name="faq"></a>FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## <a name="cmdsum"></a>Command summary

Action | Format, Examples
--------|------------------
**Add** | `g/<GERMAN WORD> e/<ENGLISH WORD>` <br> e.g., `add g/Vergesslichkeit e/Forgetfulness`
**Clear** | `clear`
**Delete** | `delete INDEX` <br> e.g., `delete 3`
**Exit** | `exit`
**List** | `list`
**Help** | `help`
**Start Normal Quiz** | `quiz`
**Start Random Quiz** | `random <NUMBER>` <br> e.g., `random 5`
**End Quiz** | `end`
