Development Plan
================

Priority features
-----------------
What's currently being worked on in descending order.
### Short term
- Improve input parsing
- Basic commands
- Room interaction
- In-game saving and loading
- GameOverMenu
### Long term
- Other GUI components
- Art for each room (and art panel)
- Music/sound effects



Problems
--------
If left unresolved, will create major long-term development issues
### Unresolved
- How to implement loop-like behavior for GUI input
    - GUI input works fundamentally different than command line standard input/output and does not "wait" for the user to input within for/while loops.
    - **Solution**: Have ad hoc boolean variables that bypass if/else-if statements to reach desired input processing step
        - Downside: Extremely messy and bug-prone if enough are added into processInput().
    - **Solution**: Have boolean variables for processInput() menu states (acts similarly to just menu swapping)
        - Downside: Somewhat messy and difficult to track.
    - **Solution**: Use menu swapping, as already implemented. Certain menus will have sub-menus to act as input loops.
        - **Downside**: Extra work (at least in the short term), as singleton methods and MenuManager menu swapping methods need to be implemented.
- How to implement other GUI components
    - Movement panel
        - Movement creates predefined user input, which can easily be done by directly interacting with output panel
    - Inventory panel
        - player taking, dropping updates inventory text pane
    - Stat panel
        - End of every turn updates stats as they are depleted/replenished
    - Spell panel
        - player learning spells updates spell text pane, both in name and in spell meaning
    - Current room/menu title/panel
    - Send button for input panel
        - Send button either needs to directly send an enter key press into the input text field, or get text from input text field, directly interact with output panel and clear input text field



Brainstorming
-------------
Rough ideas to implement.
# New Mechanics
## Day and Night cycle
Instead of a cave (or in addition to a cave), there is a day and night cycle, which requires the player to use a lantern to traverse outside of Airedale or be caught by the monster.
## Combat
Randomly in certain areas an enemy  will appear. It will initiate combat, which will enter a combat menu, and show player and enemy stats. It will be turn-based, and be similar to KOL combat.
## Talking with NPCs
Two ways to implement
1. Free-form dialogue like in H2G2. More immersive. More difficult to implement, especially with invalid phrases.
2. Fixed dialogue options. Easier to implement and allows for conversations to be directed as they should. Less immersive (locks player in dialogue mode).
## World-altering events
Entirely towns can be altered by game events. Killed off, changed faction etc.
## Time travel
Visit areas in different periods of time, which can allow for time-based puzzles with movement and item placement.

# GUI
## Basic
- Inventory panel
    - Contains items and item count
- Stat panel
    - Health
    - Mana
    - Corruption
    - Hunger
- Quest panel
    - Main quests
    - Side quests
- Movement panel
    - NESW, Up, Down, Wait
- Save/Load panel
## Advanced
- Current room picture panel (great for spatial awareness)
# Story
1. Escape from prison
    - Tutorial for basic commands and mechanics

2. Travel to Airedale
    - Introduction to mysterious figure
    - Learn combat

3. Explore Airedale
    - Introduction to money and shops
    - Side quests for money

4. Find magical sword (with special name)
    - Broken into shards
    - Shards acquires in miniquests
# World
Airedale
- Intro town
Snofrisk
- North viking town

# Characters
- Sigurd Ogleshield
- Mysterious figure
    - Give name?
