Change Log
==========
This project adheres to [Semantic Versioning](http://semver.org/).


[v0.9.0] - February 26, 2018
----------------------------
### Added
- GameObject now has properties field
    - This is to make checking if objects have properties more generalized
    - Reduces the amount of object sub-classing
    - Descriptions can now be iterated over, where the description indices are set with setNextDescription() and setPreviousDescription().
- ObjectFactory
    - Returns object singletons (reduce redundant information)
- Printer interface
    - StandardPrinter prints to standard output (console) as possible alternative to GUI
        - Potentially down the road the game may be playable in console
- Exit class
    - Added to Location to replace N/E/S/W/Up/Down rooms
### Changed
- GameObject no longer extends Printer
    - Text output is now delegated to Menu
- Output methods refactored to "print"
- Outputable refactored to GUIPrinter
- Room
    - refactored to Location
    - Removed all north/east/west/south/up/down rooms, blocked, blockedReason fields. Replaced with exits ArrayList<Exit>.
- Collectable refactored to Collectible (whoops!)


[v0.8.1] - November 6, 2017
----------------------------
### Added
- Non IDE recursive compiling instructions in `DESIGN_NOTES.md`.
### Fixed
- Typo in AskToSaveMenu

[v0.8.0] - September 10, 2017
----------------------------
### Added
- AskToSaveMenu
- sortByLongest(String[]) and sortByLongest(ArrayList<String>) for TextUtility
### Fixed
- Order of elements in arrayList for inputStartsWithChoice() does not matter.
    - Uses sortByLongest(ArrayList<String>)



[v0.7.0] - September 9, 2017
---------------------------
### Added
- Load menu drastically improved
    - Create command for creating new games.
    - Cannot create games by name if already exist.
    - Prompt for when game is created, when game name is invalid, or if game already exists.
    - Formatting for game information improved.
- GhostMenu acts as a sub menu within a menu. Swapping to and from it does not impact previous menu, and thus normal menu swapping. GhostMenu's are not "tied-to" other menus, and as such, can be called from any menu and function correctly.
    - MenuManager now has ghostPreviousMenu property, and changes to setPreviousMenu(), getPreviousMenu() for this to be implemented.
- toLowerTitleCase(), which converts to lower case before title case, which is important for converting upper case or mixed case to title case.
     - Implemented for game save names.
- TextUtility, which Outputable extends
    - Better for serialized classes, which don't need the Outputable properties (the GUI OutputPanel).
### Changed
- Merged game.system and file packages to game.system
    - World changed to Save and now has name property, which is always in title case
- Verb does not need to be explicitly created
- Verb is automatically created on first input check, and is reset between player inputs
    - Verb can still be manually set incase it's needed to change mid-turn
- MenuManager's lastMenu renamed to previousMenu
- Input checking now ignores case entirely
### Fixed
- getStringArrayList(), inputStartsWithChoice() now work when input ArrayList is empty
- LoadMenu now works correctly
- SaveManager now instantiates saves as an empty array when determineExistingSaveNums() is called if it is null (when there are no existing save files).



[v0.6.0] - September 6, 2017
---------------------------
### Added
- Revamped game saving
    - Implemented SaveManager
    - Removed FileObject, WriteObject, ReadObject
    - Games saved by String saveName as well as int saveNum
    - Maximum saves changed from 5 to 50 (MAXIMUM_SAVES)
    - LoadMenu is revamped to accommodate
- Improved input parsing
    - Added imputRemains() for Menu
    - Verb reimplemented, and originalInputWords added
        - Verb is defined post-input check in case it is multiple words
        - Verb pre-input check is either previous verb or null if first turn
- Implemented getStringArrayList() and getIntegerArrayList() utility functions to Menu
- Overrode all input parsing and output methods with integer and double equivalents



[v0.5.0] - September 5, 2017
---------------------------
### Added
- Items have serialVersionUIDs
### Fixed
- Creating, loading and deleting game saves in LoadMenu implemented and functions properly



[v0.4.0] - September 3, 2017
---------------------------
### Added
- Game driver CheeseQuest implemented and moved to main package
- Inventory removes items if item count is non-positive
- Person implemented
- Speech, Title and Direction font added
### Changed
- Menu input processing changed
    - No longer breaks input by verb and remaining words, as verb may be multiple words
    - Now works similar to Cheese Quest 1, where input is processed by constantly stripping away words from the start of the inputString
- Room:
    - No longer uses Coordinates class
    - Has directionBlocked and directionBlockedReason implemented
    - Room description, item description, direction output methods implemented
- Added Outputable class so all GameObject's and Menu's can output directly
- Player moved from system package to person package



[v0.3.1] - August 19, 2017
--------------------------
### Fixed
- Changing between menus now works.



[v0.3.0] - August 18, 2017
--------------------------
### Added
- InputPanel can now retrieve previous input like command line
    - Scrolling up with up arrow/left arrow keys moves up from most previous commands to further back, and cycles back to most previous once all commands are traversed through.
    - Scrolling down with down/right arrow keys moves down from first command to most previous, and cycles back to first command once all commands are traversed through.
- InputPanel's JTextField regains focus when MainFrame regains focus
- OutputPanel now uses color
    - Implemented in MainMenu



[v0.2.0] - August 8, 2017
-------------------------
### Added
- Implemented GUI skeleton
    - InputPanel and OutputPanel in MainFrame



[v0.2.0] - August 4, 2017
-------------------------
### Added
- WriteObject and ReadObject to save games



[v0.1.0] - August 1, 2017
-------------------------
Started organizing package structure and implementing classes
