# Assignment 4

## Group M.EIC 05

| Student | UP Number |
| --- | --- |
| Clara Alves Martins | up201806528 |
| Daniel Filipe Amaro Monteiro | up201806185 |

## Black-box testing: Model-based Software Testing

### Clipboard

#### Use case

The main goal of this application is to help the user store and use their passwords easily and safely.
To avoid the need to edit an entry to copy information from it, the application provides the user with clipboard utilities
capable of instantly copying the URL, the username or the password of the user.
Verifying that these utilities are working correctly using only unit tests can be quite tricky, making this functionality a good candidate for model-based testing.

#### State machine
![State Machine Clipboard](img/clipboard_state.svg)

#### Transition tree
![Transition Tree Clipboard](img/clipboard_tree.svg)

#### Transition table

For ease of representation, the states, events and conditions will be abbreviated as representated in the following tables:

| State                      | Abbreviation |
| ---                        | ---          |
| Empty Clipboard            | Empty        |
| Password on Clipboard      | Pass         |
| User Name on Clipboard     | User         |
| Website URL on Clipboard   | URL          |
| Warning Screen (Empty)     | WEmpty       |
| Warning Screen (Password)  | WPass        |
| Warning Screen (User Name) | WUser        |
| Warning Screen (URL)       | WURL         |

| Event                          | Abbreviation |
| ---                            | ---          |
| Press "Clear Clipboard" Button | Clear        |
| Press "Copy Password" Button   | CopyPass     |
| Press "Copy User Name" Button  | CopyUser     |
| Press "Copy URL" Button        | CopyURL      |
| Press "Ok" or "X" Button       | Ok           |

| Condition         | Abbreviation |
| ---               | ---          |
| entry selected    | NoEntry      |
| no entry selected | Entry        |

Transition Table

| Origin State | Clear | CopyPass[Entry] | CopyUser[Entry] | CopyURL[Entry] | CopyPass[NoEntry] | CopyUser[NoEntry] | CopyURL[NoEntry] | Ok    |
|--------------|-------|-----------------|-----------------|----------------|-------------------|-------------------|------------------|-------|
| **Empty**    | Empty | Pass            | User            | URL            | WEmpty            | WEmpty            | WEmpty           | -     |
| **Pass**     | Empty | Pass            | User            | URL            | WPass             | WPass             | WPass            | -     |
| **User**     | Empty | Pass            | User            | URL            | WUser             | WUser             | WUser            | -     |
| **URL**      | Empty | Pass            | User            | URL            | WURL              | WURL              | WURL             | -     |
| **WEmpty**   | -     | -               | -               | -              | -                 | -                 | -                | Empty |
| **WPass**    | -     | -               | -               | -              | -                 | -                 | -                | Pass  |
| **WUser**    | -     | -               | -               | -              | -                 | -                 | -                | User  |
| **WURL**     | -     | -               | -               | -              | -                 | -                 | -                | URL   |

#### Test description

* These tests require an initialization since they need at least one entry.
    * One password entry will be introduced when setting up for these tests.
        |           |                      |
        | ---       | ---                  |
        | Title     | `TestEntry`          |
        | URL       | `www.example.com`    |
        | User name | `test_user`          |
        | Password  | `test_password`      |
        | Repeat    | `test_password`      |
        | Notes     | (empty)              |
* We are testing all the paths in the transition tree.
    * Our tests are numbered and correspond to the order of the paths in the transition tree from left (0) to right (16).
        * Examples
            * Test 1: Empty Clipboard (0) → Empty Clipboard (1)
            * Test 3: Empty Clipboard (0) → Password on Clipboard (0) → Empty Clipboard (3)

#### Tests
* Test 0
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy Password" Button without an entry selected
        2. Press "Ok"
    * End: Empty Clipboard (2)
    * Must verify that step 1 opens the "Warning" Window
* Test 1
    * Start: Empty Clipboard
    * Steps
        1. Press "Clear Clipboard" Button
    * End: Empty Clipboard (1)
* Test 2
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy Password" Button with an entry selected
        2. Press "Copy Password" Button without an entry selected
        3. Press "Ok"
    * End: Password on Clipboard (4)
    * Must verify that step 2 opens the "Warning" Window
* Test 3
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy Password" Button with an entry selected
        2. Press "Clear Clipboard" Button
    * End: Empty Clipboard (3)
* Test 4
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy Password" Button with an entry selected
        2. Press "Copy Password" Button with an entry selected
    * End: Password on Clipboard (1)
* Test 5
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy Password" Button with an entry selected
        2. Press "Copy User Name" Button with an entry selected
    * End: User Name on Clipboard (1)
* Test 6
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy Password" Button with an entry selected
        2. Press "Copy URL" Button with an entry selected
    * End: Website URL on Clipboard (1)
* Test 7
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy User Name" Button with an entry selected
        2. Press "Copy User Name" Button without an entry selected
        3. Press "Ok"
    * End: User Name on Clipboard (4)
    * Must verify that step 2 opens the "Warning" Window
* Test 8
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy User Name" Button with an entry selected
        2. Press "Clear Clipboard" Button
    * End: Empty Clipboard (4)
* Test 9
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy User Name" Button with an entry selected
        2. Press "Copy Password" Button with an entry selected
    * End: Password on Clipboard (2)
* Test 10
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy User Name" Button with an entry selected
        2. Press "Copy User Name" Button with an entry selected
    * End: User Name on Clipboard (2)
* Test 11
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy User Name" Button with an entry selected
        2. Press "Copy URL" Button with an entry selected
    * End: Website URL on Clipboard (2)
* Test 12
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy URL" Button with an entry selected
        2. Press "Copy URL" Button without an entry selected
        3. Press "Ok"
    * End: URL on Clipboard (4)
    * Must verify that step 2 opens the "Warning" Window
* Test 13
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy URL" Button with an entry selected
        2. Press "Clear Clipboard" Button
    * End: Empty Clipboard (5)
* Test 14
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy URL" Button with an entry selected
        2. Press "Copy Password" Button with an entry selected
    * End: Password on Clipboard (3)
* Test 15
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy URL" Button with an entry selected
        2. Press "Copy User Name" Button with an entry selected
    * End: User Name on Clipboard (3)
* Test 16
    * Start: Empty Clipboard
    * Steps
        1. Press "Copy URL" Button with an entry selected
        2. Press "Copy URL" Button with an entry selected
    * End: Website URL on Clipboard (3)

#### Test outcome
All the tests passed.

### Search for a specific entry

#### Use case

After introducing the password in the application, the user will want to search for the password to use on a specific website. They should be able to search either by the name of the website or by the URL.

#### State machine
![State Machine Find](img/find_state.svg)

#### Transition tree
![Transition Tree Find](img/find_tree.svg)

#### Transition table

| **States \ Events**                       | **Press search bar "x" button** | **Press "Edit"**                      | **Press anywhere else**            | **Press "Find Entry"**             | **Enter input into the search bar** | **Clear search bar**           |
|-------------------------------------------|---------------------------------|---------------------------------------|------------------------------------|------------------------------------|-------------------------------------|--------------------------------|
| **Main window (No search bar)**           |                                 | "Edit" submenu (No search bar)        |                                    |                                    |                                     |                                |
| **"Edit" submenu (No search bar)**        |                                 |                                       | Main window (No search bar)        | Main window (Empty search bar)     |                                     |                                |
| **Main window (Empty search bar)**        | Main window (No search bar)     | "Edit" submenu (Empty search bar)     |                                    |                                    | Main window (Filled in search bar)  |                                |
| **"Edit" submenu (Empty search bar)**     | Main window (No search bar)     |                                       | Main window (Empty search bar)     | Main window (Empty search bar)     |                                     |                                |
| **Main window (Filled in search bar)**    | Main window (No search bar)     | "Edit" submenu (Filled in search bar) |                                    |                                    |                                     | Main window (Empty search bar) |
| **"Edit" submenu (Filled in search bar)** | Main window (No search bar)     |                                       | Main window (Filled in search bar) | Main window (Filled in search bar) |                                     |                                |

#### Test description

* Test 0
    * Start: Main window (No search bar)
    * Steps
        1. Press "Edit"
        2. Press anywhere else
    * End: Main window (No search bar)

* Test 1
    * Start: Main window (No search bar)
    * Steps
        1. Press "Edit"
        2. Press "Find Entry"
        3. Press search bar "x" button
    * End: Main window (No search bar)

* Test 2
    * Start: Main window (No search bar)
    * Steps
        1. Press "Edit"
        2. Press "Find Entry"
        3. Press "Edit"
        4. Press search bar "x" button
    * End: Main window (No search bar)

* Test 3
    * Start: Main window (No search bar)
    * Steps
        1. Press "Edit"
        2. Press "Find Entry"
        3. Press "Edit"
        4. Press "Find Entry"
    * End: Main window (With empty search bar)

* Test 4
    * Start: Main window (No search bar)
    * Steps
        1. Press "Edit"
        2. Press "Find Entry"
        3. Enter input into the search bar
        4. Press search bar "x" button
    * End: Main window (No search bar)

* Test 5
    * Start: Main window (No search bar)
    * Steps
        1. Press "Edit"
        2. Press "Find Entry"
        3. Enter input into the search bar
        4. Clear search bar
    * End: Main window (With empty search bar)

* Test 6
    * Start: Main window (No search bar)
    * Steps
        1. Press "Edit"
        2. Press "Find Entry"
        3. Enter input into the search bar
        4. Press "Edit"
        5. Press search bar "x" button
    * End: Main window (No search bar)

* Test 7
    * Start: Main window (No search bar)
    * Steps
        1. Press "Edit"
        2. Press "Find Entry"
        3. Enter input into the search bar
        4. Press "Edit"
        5. Press "Find Entry"
    * End: Main window (With filled in search bar)

#### Test outcome


### Create a password entry / CRUD

#### Use case

Managing password entries is a critical functionality of this project. It's therefore critical that we perform model-based testing to verify the correctness of the UI. The user will need to add, edit and delete passwords from the application.

#### State machine
![State Machine Add](img/add_state.svg)

#### Transition tree
![Transition Tree Add](img/add_tree.svg)

#### Transition table
For ease of representation, the states, events and conditions will be abbreviated as representated in the following tables:

| State                                                    | Abbreviation |
| ---                                                      | ---          |
| "JPass Password Manager" Window                          | Main         |
| "Add New Entry" Window (Password & Repeat invisible)     | AddInvis     |
| "Warning" Window (Fill the Fields, invisible)            | WAddInvis    |
| "Generate Password" Window (Password & Repeat invisible) | GenInvis     |
| "Warning" Window (Generate Password, invisible)          | WGenInvis    |
| "Add New Entry" Window (Password & Repeat visible)       | AddVis       |
| "Warning" Window (Fill the Fields, visible)              | WAddVis      |
| "Generate Password" Window (Password & Repeat visible)   | GenVis       |
| "Warning" Window (Generate Password, visible)            | WGenVis      |

| Event                                                  | Abbreviation |
| ---                                                    | ---          |
| Press "Add New..." Button                              | AddNew       |
| Press "X"          Button                              | X            |
| Press "Ok"         Button                              | Ok           |
| Press "OK"         Button                              | OK           |
| Press "Cancel"     Button                              | Cancel       |
| Press "Copy"       Button                              | Copy         |
| Press "Generate"   Button ("Add New Entry" Window)     | Generate1    |
| Press "Generate"   Button ("Generate Password" Window) | Generate2    |
| Press "Show"       Button                              | Show         |
| Press "Accept"     Button                              | Accept       |

| Condition                    | Abbreviation |
| ---                          | ---          |
| valid Form                   | V            |
| invalid Form                 | I            |
| filled in generated password | F            |
| empty generated password     | E            |

Transition Table

| Origin State | AddNew   | X        | Ok       | Cancel   | OK[V] | OK[I]     | Copy     | Generate1 | Generate2 | Show     | Accept[F] | Accept[E] |
|--------------|----------|----------|----------|----------|-------|-----------|----------|-----------|-----------|----------|-----------|-----------|
| **Main**      | AddInvis | -        | -        | -        | -     | -         | -        | -         | -         | -        | -         | -         |
| **AddInvis**  | -        | Main     | -        | Main     | Main  | WAddInvis | AddInvis | GenInvis  | -         | AddVis   | -         | -         |
| **WAddInvis** | -        | AddInvis | AddInvis | -        | -     | -         | -        | -         | -         | -        | -         | -         |
| **GenInvis**  | -        | AddInvis | -        | AddInvis | -     | -         | -        | -         | GenInvis  | -        | AddInvis  | WGenInvis |
| **WGenInvis** | -        | GenInvis | GenInvis | -        | -     | -         | -        | -         | -         | -        | -         | -         |
| **AddVis**    | -        | Main     | -        | Main     | Main  | WAddVis   | AddVis   | GenVis    | -         | AddInvis | -         | -         |
| **WAddVis**   | -        | AddVis   | AddVis   | -        | -     | -         | -        | -         | -         | -        | -         | -         |
| **GenVis**    | -        | AddVis   | -        | AddVis   | -     | -         | -        | -         | GenVis    | -        | AddVis    | WGenVis   |
| **WGenVis**   | -        | GenVis   | GenVis   | -        | -     | -         | -        | -         | -         | -        | -         | -         |

#### Test description
* Test 0
    * Start: "JPass Password Manager" Window
    * Steps
        1. Press "Add New..." Button
        2. Press "X" Button
    * End: "JPass Password Manager" Window
* Test 1
    * Start: "JPass Password Manager" Window
    * Steps
        1. Press "Add New..." Button
        2. Press "Copy" Button
    * End: "Add New Entry" Window (Password & Repeat invisible)
* Test 2
    * Start: "JPass Password Manager" Window
    * Steps
        1. Press "Add New..." Button
        2. Press "Copy" Button
    * End: "Add New Entry" Window (Password & Repeat invisible)


#### Test outcome


### QF-Test Feedback

We felt that the QF-Test tool is pretty good and the installation process was straightforward. We didn't manage to add the tool to `PATH` with the setup tool, so we resorted to finding the executable in the project's files and executing it using the terminal. We feel that this part of the user experience could be improved upon.

The tool felt very intuitive to use.
