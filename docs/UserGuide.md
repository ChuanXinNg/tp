---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# Tutorium User Guide

Tutorium is a **desktop application for tuition centre staff to obtain data analysis to plan marketing strategies**. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `tutorium.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Tutorium.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tutorium.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a student's data : `edit`

Edits an existing student's data in the address book.

Format: 
1. `edit n/[NAME] or edit e/[EMAIL]` (firstly specify for which student you want to edit)
2. `e/[NEW_EMAIL] or n/[NEW_NAME] or a/[NEW_ADDRESS] or t/[NEW_TAG]`  (change a particular field)
3. `n/[NEW_NAME] e/[NEW_EMAIL]  or e/[NEW_EMAIL] t/[NEW_TAG] a/[NEW_ADRESS]` (change multiple fields)

* Edits the person with a specific name or email.
  - edit n/[NAME] 
  - edit e/[EMAIL]
* After the prompt “OK! Now you can edit NAME”
  - e/[NEW_EMAIL]
  - n/[NEW_NAME]
  - a/[NEW_ADDRESS]
  - t/[NEW_TAG]
* Or you change multiple fields within one command:
  - n/[NEW_NAME] e/[NEW_EMAIL] 
  - e/[NEW_EMAIL] t/[NEW_TAG] a/[NEW_ADRESS]
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit n/joe` specify that you want to do edit operation on the student "joe", if there are more than one student named "joe", you will be prompted to specify student email further: `edit n/joe123@example.com`.
*  `n/joey e/joey123@example.com` Edits the name and email of the student you specified earlier to be `joey` and `joey123@example.com`.

### Searching for data : `search`

Search for people whose names contain any of the given keywords.

Format: `search [KEYWORD]`

* The search is case-insensitive. e.g `joe` will match `Joe`
* Only the name is searched.
* Partial words can be matched. e.g. `Han` will match `Hans`
* All persons matching the keyword will be returned (i.e. `OR` search).
  e.g. `Alfred` will return `alfred`, `Alfred Tan`

Examples:
* `search Yuan` returns `Chuan Yuan` and `Li Yuan`<br>
  ![result for 'search Yuan'](images/ug_images/searchYuanResult.png)

### Deleting data : `delete`

Deletes data of a specified student.

Format: `delete n/[NAME]` or `delete e/[EMAIL]`

* Deletes the person with the specified `NAME` or `EMAIL`.
* `NAME`: The deletion is case-insensitive. e.g `joe` will match `Joe`.
* `EMAIL`: username@domain

Examples:
* `delete n/John Doe`
* `delete e/johndoe@gmail.com` 
<br></br>
* `delete n/Chuan Yuan` deletes student data that contains name `Chuan Yuan`
  ![result for 'delete Chuan Yuan'](images/ug_images/deleteChuanYuanResult.png)

### Grouping data : `group`

Shows the list of students data that holds a particular tag.

Format: `group /by [TAG_NAME]`

* TAG_NAME: Case sensitive string
* The student list shows only the students whose data is tagged with “SubjectA” tag.
* Case couldn’t find any data with the tag
  Example: `group /by QWERTY`
  Shows error message: `Tag does not exist.`
* Case using wrong format
  Example: `group SubjectA` or `group by SubjectA`
  Shows error message: `Group usage: group /by [TAG_NAME]`

Examples:
* `group Maths` returns `Chuan Yuan`, `Li Yuan` and `Alfred` <br>
  ![result for 'group Maths'](images/ug_images/groupMathsResult.png)

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
