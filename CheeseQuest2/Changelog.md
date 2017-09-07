Change Log
==========
This project adheres to [Semantic Versioning](http://semver.org/).


[v0.6.0] - September 6, 2017
---------------------------
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
- Creating, loading and deleting game saves in LoadMenu implemented and functions properly
- Items have serialVersionUIDs


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
### Fixed
- Changing between menus now works.


[v0.3.0] - August 18, 2017
--------------------------
### Added
- InputPanel can now retrieve previous input like command line
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
