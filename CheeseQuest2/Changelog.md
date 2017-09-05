# Cheese Quest 2: The Plague of Kashkaval

Evan Quan August 01, 2017
Single-player text adventure game

Developed in Java 1.8

## Changelog
### 0.2.0 - September 5, 2017
- Creating, loading and deleting game saves in LoadMenu implemented and functions properly
- Items have serialVersionUIDs
### 0.1.0 - September 3, 2017
- Game driver CheeseQuest implemented and moved to main package
- Inventory removes items if item count is non-positive
- Person implemented
- Speech, Title and Direction font added
- Menu input processing changed
    - No longer breaks input by verb and remaining words, as verb may be multiple words
    - Now works similar to Cheese Quest 1, where input is processed by constantly stripping away words from the start of the inputString
- Room:
    - No longer uses Coordinates class
    - Has directionBlocked and directionBlockedReason implemented
    - Room description, item description, direction output methods implemented
- Added Outputable class so all GameObject's and Menu's can output directly
- Player moved from system package to person package
### 0.0.4a - August 19, 2017
- Changing between menus now works.
### 0.0.4 - August 18, 2017
- InputPanel can now retrieve previous input like command line
- InputPanel's JTextField regains focus when MainFrame regains focus
- OutputPanel now uses color
    - Implemented in MainMenu
### 0.0.3 - August 8, 2017
- Implemented GUI skeleton
    - InputPanel and OutputPanel in MainFrame
### 0.0.2 - August 4, 2017
- Implemented WriteObject and ReadObject to save games
### 0.0.1 - August 1-3, 2017
- Started organizing package structure and implementing classes
