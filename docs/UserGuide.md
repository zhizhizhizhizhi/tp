---
layout: page
title: User Guide
---

ForgetfulNUS is a **desktop app for helping students taking German 1** (LAG1201) **and German 2** (LAG2201) **in NUS to practise their vocabulary, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ForgetfulNUS can get your German revision done faster than traditional GUI apps.

## Table of Contents

- [Quick Start](#qs)
- [Features](#features)
    - Add Phrase (coming soon)
    - Delete Phrase (coming soon)
    - View All Phrases (coming soon)
    - Test Yourself (coming soon)
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

   * **`list`** : Lists all phrases.

   * **`add`**`forgetfulness | Vergesslichkeit` : Adds a phrase with English word `forgetfulness` with German translation `Vergesslichkeit` to the glossary.

   * **`delete`**`3` : Deletes the 3rd phrase shown in the glossary.

   * **`quiz`** : Starts a round of vocabulary testing.
   
   * **`end quiz`** : Ends a round of vocabulary testing.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## <a name="features"></a>Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add <ENGLISH WORD> | <GERMAN WORD>`, `ENGLISH WORD` and `GERMAN WORD` are parameters which can be used as `add forgetfulness | Vergesslichkeit`.

</div>

### Adding a phrase: `add`

Adds a phrase to the glossary.

Format: `add <ENGLISH WORD> | <GERMAN WORD>`

Example:
* `add forgetfulness | Vergesslichkeit`

### Deleting a phrase : `delete`

Deletes the specified phrase from the glossary.

Format: `delete <INDEX>`

* Deletes the phrase at the specified `INDEX`.
* The index refers to the index number shown in the displayed glossary.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd flashCard in the address book.

### Listing all phrases : `list`

Shows a list of all phrases in the glossary.

Format: `list`

### Test Yourself : `quiz` `end quiz`

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
**Add** | `ENGLISH WORD \ GERMAN WORD` <br> e.g., `add forgetfulness \ Vergesslichkeit`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**End Quiz** | `end quiz`
**Exit** | `exit`
**List** | `list`
**Start Quiz** | `quiz`
