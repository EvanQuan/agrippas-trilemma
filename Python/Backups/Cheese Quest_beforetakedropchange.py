# Cheese Quest: The Plight of Kashkaval
# Evan Quan Oct. 17, 2016
# Single-player text based adventure game
# Save the world from the Cheese Mage!

# Developed in Python 3.5.2

# Inspired by: http://pages.cpsc.ucalgary.ca/~tamj/2016/231F/assignments/assignment2/index.htm

# Currently working on for 1.2.1
# Balancing numbers during playthrough
# POTENTIALLY: Change cave layout to be less confusing?
# Adding more examine text where important
# Check for text issues
# Mead
# if drink too much, go to darkness for 3 turns and wake up
# balance numbers
#   turns to find and use bandage
#   turns to find and eat biscuit
#   turns to take staff, pick and escape before creature hits you
#	   make sure creatures hits once in optimal escape
#   update roomLairWest description

# Change How to play to be more clear
#  Verb noun
# Reformat letter to be more readable
# Consider redoing jail foyer gold

# examine items if in room but not in inventory

# text formatting to seperate filler from important information
# condition checking error
#   Should be give bandage and not itemFound
# examine hunger
#   Extra message\
# Clarity to go north to mountain to get gold
# Troll goal to 1000 from 1500
# quantities for take and give
# loading and saving function
# Make movement easier to use (quick travel)
# Increased

# revamp take and drop to work with numerical values like with buy/sell
#   Change a to 1 or include a
#       a = 1
# if half-eaten funnel in room
#   counts as funnel cake
# if half eaten and normal funnel cake in room
#
VERSION = "1.2.0"
DATE = "Jan. 15, 2017"
"""
Changelog
1.2.1 - Jan. 16, 2017
    - Added hunger and health/hp commands
        - Give description and numerical turns to eat/heal
    - Improvements
        - Examine hunger and health do not count as turns
        - Added "pick up" and "grab"
        - Added "rotate"
        - Added "gamble" and to bet "all"
        - Fixed roomLake injured stranger text not applying to the right conditions
        - Fixed askName instantly killing player in roomJailCell if they do not respond
        - Letter instructions are more clear

1.2.0 - Jan. 15, 2017
    - Removed roomCave_3_llllm_crevasse and roomCave_3_lllm to make treasure chest more obvious
    - Replaced lake reward with lockbox
    - Moved Ozh ensh, Ozh vo'ses sa to roomLake
    - Moved Ozh sol fek to journal
    - Added Ozh thok alatho to roomLairEast
    - Added "light lantern" command
    - Creature will not spawn if in first cave room (roomCave_1_m)
        - If player wants to enter cave first, then light lantern
    - 2 random roomJailCorridor vault numbers will already be set to the correct answer
    - Block descriptions added along invalid road directions
    - Game balance
        - Increased max hunger from 50 to 100
        - Increased food hunger values
            - Porridge hunger increased from 50 to 60
            - Pie hunger increased from 40 to 80 (4 hunger/gold)
            - Funnel cake hunger increased from 10 to 20 (4 hunger/gold)
            - Potion hunger increased from 40 to 125 (5 hunger/gold, or 12.5 hunger/gold if refill)
            - Biscuit hunger increased from 15 to 30
                - LOOT_MID_BANDAGE decreased from 4 to 1
                - HUNGER_DARKNESS increased from 25 to 30
            - Cheese hunger decreased from 3 to 2
        - Oil duration increased from 25 to 30
        - Rubble fall chance decreased from 5% to 1%
        - Pickaxe break chance decreased from 7% to 1%
        - Lucky foot modifier increased from 25% to 100%
        - Raffle compensation increased from 150 gold to 200 gold
        - Creature roam minimum increased from 24 to 26
        - Lake gold reward increased from 143 gold to 183 gold
        - LOOT_WEST_GOLD increased from 14 to 42
    - Improvements
        - Fixed typo in various room descriptions
        - Improved clarity for various room descriptions
        - Spells with descriptions are now added to spell list if used validly
        - Shops and bridge now display player gold values upon entering
        - Staff and kill spell can be used against creature in lair and cave, but has no effect
        - Staff and kill spell can be used in carnival wheel game and jail, resulting in death
        - Blacksmith first time description added to clarify that coal is found in the Mount Magna mine
        - Examining room does not count as turn
        - Moving into blocked direction does not count as turn
        - Indented movement text
        - Added !spells and !learn debug commands
        - Capitalized final death warning messages for health and hunger
1.1.0 - Jan. 14, 2017
    - Completed:
        - Injured stranger fetch quest to roomLake to replace lake apparition
            - Provides early game reward of 143 gold and 1 pie for bandage and increases clarity of "Ozh ensh"
            - Tablet now reads non-spell, "Eyik vo'hollom"
        - Cannot eat or drink anything if hunger is above HUNGER_MAX
        - Added 5% chance of rubble falling from ceiling and injuring player when moving around in normal cave
            - Make bandages serve a purpose
        - Increased CREATURE_ROAM from 18-32 to 24-32 to compensate
    - Game balance:
        "Game was too difficult from escaping jail to passing bridge troll. Rewards were increased for entering the cave, the risk getting caught by the creature with a light source decreased."
        - Increased raffle ticket refund from 100 gold to 150 gold
        - Increased the cave body loot gold, vial and biscuit rewards
        - Swapped cave bodies so explorer with journal is closet to entrance
        - Increased dragonstone sell price from 120 gold to 150 gold
            - LOOT_A gold and biscuit from 14 and 2 to 87 and 3 respectively
            - LOOT_B gold from 29 to 76
            - LOOT_C oil from 1 to 2
        - Increased the cave treasure chest gold reward from 263 to 463
        - Increased the range of coal quantity in coal mines from 4-6 to 6-10
        - Increased cave creature roam and chase variance and average times
            - CREATURE_ROAM from 16-26 to 18-32
            - CREATURE_CHASE from 14-20 to 16-22
        - Added earlier warning for creature chase
        - Pickaxe break chance increased from 5% to 7%
        - Food hunger values increased and standardized to make food purchases more balanced and hunger/gold ratios more fair
            - Pie hunger increased from 35 to 40 hunger (2 hunger/gold)
            - Potion hunger increased from 20 to 30
                - Potion cost increased from 20 to 25 (1.2 hunger/gold)
                - Refill is now more efficient (3 hunger/gold)
    - Improvements:
        - Buying and refilling now display total money after purchase
        - Fixed lack of error message when selling invalid item
        - Examining inventory now does not count as a turn
        - Examining spells does not count as a turn
            - Convenience factor when checking for gold and other item quantities
        - Fixed grappling hook now spawning hook on opposite end of crevasse once thrown
        - Take all in lair chase counts as 2 turns to be equivalent to taking each item individually
        - Added examine text for roomRoad2North, roomFarm, roomBarnUp, roomShrine, roomHouseFoyer, roomHouseKitchen, roomHousePantry, roomHouseHallway
        - Updated barn letter
        - Fixed roomBookMirror text
        - Fixed "spells" etc. command
        - Fixed roomGate description text
        - Made invalid give, open, close not count as a turn
        - changeRoom is now default False
        - Fixed mysterious book inventory interactions with key of Ahm'domosh
        - Fixed selling dragonstone not removing dragonstone from inventory
        - Fixed giving Roquefort staff crashing the game
1.0.1 - Jan. 13, 2017
    - Fixed Alchemist's hut crash on entering a second time.
    - Fixed cave pathing
    - Fixed Roquefort telepathy message to occur after staff is acquired
    - Fixed roomTempleInside description text
	- Fixed taking backpack items in creature lair and lair chase sequence
    - Fixed using vial of lantern oil not removing vial from inventory
1.0.0 - Jan. 13, 2017 - Completed all main features to complete a playthrough of the game
	- Completed:
        - Added command prompt window resize on launch
       		- Alchemist's Hut - Activatable shop
    	   	- Dragonstone fetch quest
    	    - Potion
    	    - Flask
    	    - Fill command
	- Game balance
    	- Adjusted prices and rewards values
0.9.0 - Jan. 11, 2017
	- Completed:
		- Game ending
		- Debug mode
		- Roquefort house
		- Creature lair
		- Field roads
		- Farm and barn
		- Black knight enemy
		- Ozkavosh shrine, Mysterious book and book puzzles
		- Roquefort telepathy messages
		- Ozkavosh spells and spellbook
		- Up and down movement
		- Cheese slices
		- Biscuits
		- Lootable corpses in cave
		- Journal in cave
		- Take all and drop all
	- Improvements
		- Main menu splash art
		- Jail size and room improvements
		- Various item and room descriptions to add clarity and detail
		- Temple inner sanctum
		- Temple murals
		- Revamped jail letter text
		- Replaced wooden bird with shrubbery
		- 5% change of breaking pickaxe when mining
0.6.0 - Jan. 03, 2017
	- Completed:
		- Gate guard quest
		- General store
		- Mining system
		- Funnel cake buy limit
		- Wheel game room and ticket system
	- Improvements:
		- Give command
		- Buy command
		- Look command
		- Use staff
0.5.1 - Dec. 12, 2016
	- Completed:
		- Grappling hook mechanic
		- Cave pathing
	- Improvements:
		- Shell game gold limit
0.5.0 - Dec. 11, 2016
	- Completed:
		- Bridge and temple
	- Menu changes:
		- Changelog (Wow. Such meta.)
		- About game information
		- Restart game menu
0.1.0 - 0.4.0 - Oct. 17, 2016 - Dec. 10, 2016
	- Completed:
    	- Room and movement system
    	- Inventory and item system
    	- Buying/selling/betting system
    	- Examine/read/description system
    	- Jail, town, most of cave
    	- Cave creature and jail guard chasing mechanics
    	- Hunger and health system

"""
#____Main implemented features needed for completion______________________


# Enter darkness by either going north, or using staff on monster when it appears
# Different text if use staff on monster
# If use staff on monster in wrong place, die, own text

# 0.9 - End game
# Simple puzzles http://h2g2.com/approved_entry/A22196289


# add 1 more level to mysterious book
# find right action/verb for animal totems
# consider other interactions with mirror image
#   maybe make it do nothing but talk in reverse (how to make clear, add text?)

#____Less Important features_____

# Examine/Read
# Examine objects in all rooms

# First time descriptions
# Adds immersion, less repetitive

# Punch, kick
# If people in room

# Add to forest
# Make directionBlock for forest to add immersion
# Goblins appear randomly, hurt you (detour player from going in forest if no staff)
# Killing goblin drops necklace
# Hut in tent buys goblin necklaces for ???

# Notes
# If adding ITEM, add to:
#   Inventory
#	   examineInventory(self)
#   Take
#   Drop
#   Room
#	   itemsPresent(self)
#   Examine

# If adding ROOM, add to:
#   class roomName
#   changeRoom (at start and at end loop)
#   Instantiate

#_______Libraries_______________________________________________________________
import random,os

# Resize command line window to appropriate size
# col is width
# lines is buffer height
os.system('mode con: cols=200')
#_______Constants_______________________________________________________________

# Stats
HUNGER_MAX = 100
HUNGER_START_NORMAL = 5
HUNGER_DEBUG = HUNGER_MAX
HEALTH_MAX = 7
HUNGER_DARKNESS = 30

# Food Hunger Values
HUNGER_PORRIDGE = 60
HUNGER_FUNNELCAKE = 20 # 2 hunger/gold
HUNGER_HALFFUNNELCAKE = HUNGER_FUNNELCAKE / 2
HUNGER_PIE = 80 # 4 hunger/gold
HUNGER_BISCUIT = 30
HUNGER_POTION = 125 # 5 hunger/gold, or 12.5 hunger/gold if refill
HUNGER_CHEESE = 2

# Lantern oil turn duration
OIL_DURATION = 30
# Ball of light duration
LIGHT_DURATION = OIL_DURATION * 2
# Prices
# Buy
PRICE_BUY_PICKAXE = 20
PRICE_BUY_FUNNELCAKE = 5
PRICE_BUY_FOOT = 15
PRICE_BUY_LANTERN = 30
PRICE_BUY_OIL = 5
PRICE_BUY_PIE = 20
PRICE_BUY_HOOK = 250
PRICE_BUY_BANDAGE = 10
PRICE_BUY_TICKET = 10
PRICE_BUY_POTION = 25
PRICE_REFILL_POTION = 10
# Sell
PRICE_SELL_COAL = 25
PRICE_SELL_STONE = 150
# LAKE
LAKE_GOLD_REWARD = 183
LAKE_PIE_REWARD = 1
# CAVERN
# Coal Mine
COAL_MIN = 6
COAL_MAX = 10
PICKAXE_BREAK_CHANCE = 1
RUBBLE_FALL_CHANCE = 1
# Creature
CREATURE_ROAM_MIN = 26
CREATURE_ROAM_MAX = 32
CREATURE_CHASE_MIN = 16
CREATURE_CHASE_MAX = 22
# Cave Treasure chest gold reward
# Should offset most of equipment prices
CHEST_REWARD = 463
# Dead body loot
LOOT_A_GOLD = 87
LOOT_A_BISCUIT = 3
LOOT_B_GOLD = 76
LOOT_B_JOURNAL = 1
LOOT_C_PICKAXE = 1
LOOT_C_OIL = 2

# DARKNESS
DARKNESS_DURATION = 3

# LAIR
# How many turns it takes to break rubble
RUBBLE_DURABILITY = 2
LOOT_MID_BISCUIT = 1
LOOT_MID_BANDAGE = 2
LOOT_WEST_GOLD = 42
LOOT_WEST_BISCUIT = 2
LOOT_HOLE_OIL = 1
# creatureLairChaseCounter
# Determines how many turns before creature kills you after waking
LAIR_CHASE_DURATION = 5

# BRIDGE
# Troll Gold needed to pass at roomBridge
TROLL_GOAL = 1500
# Value multiplier compared to giving gold
TROLL_FUNNELCAKE_MULTIPLIER = PRICE_BUY_FUNNELCAKE * 2
TROLL_HALFFUNNELCAKE_MULTIPLIER = PRICE_BUY_FUNNELCAKE

# COURTYARD
LOOT_GATE_GOLD = 9
LOOT_S_GOLD = 7

# CARNIVAL
# Shell Game gold inventory at roomCarnivalShellGame
# Sybil goes out of business and shuts down tent if gold reaches 0.
SHELL_GOLD = 300
# Win multiplier in roomShellGame
SHELL_REWARD_MULTIPLIER = 2
# Percent added win chance for gambling per lucky rabbit foot
LUCKY_FOOT_MODIFIER = 100
# Funnel Cake limit
# Vendor can't sell anymore funnel cakes if you've reached limit
FUNNELCAKE_LIMIT = 50
# Wheel reward
WHEEL_GOLD = 50
# Raffle ticket reimbursement
#   How many turns raffle ticket can be exchanged at roomCarnival
RAFFLE_TIMER = 5
# Raffle ticket reward
RAFFLE_COMPENSATION = 200

# HOUSE
DIAL_START = "green"
DIAL_ANSWER = "red"
LEVER_START = "forward"
LEVER_ANSWER = "backwards"
option_list = []

# Total game counters
# Continue between playthroughs of a single sitting
turnCounter_total = 0
deaths_total = 0
# Ozkavosh spells
spell_learn = 0
spell_unlock = 0
spell_persuade = 0
spell_jump = 0
spell_light = 0
spell_heal = 0
spell_feed = 0
spell_kill = 0
spell_killself = 0
word_darkness = 0
word_reign = 0
word_stop = 0
word_servant = 0
word_mirror = 0
word_dominion = 0
word_curse = 0
word_oblivion = 0

# Help counter
help_counter = 0
#_______Functions_______________________________________________________________

def demonWords():
    global spell_learn,spell_unlock,spell_persuade,spell_jump,spell_light,spell_heal,spell_feed,spell_kill,spell_killself,word_oblivion,word_darkness,word_reign,word_stop,word_servant,word_mirror,word_dominion,word_curse
    if not (spell_learn or spell_unlock or spell_persuade or spell_jump or spell_light or spell_heal or spell_feed or spell_kill or spell_killself or word_oblivion or word_darkness or word_reign or word_stop or word_servant or word_mirror or word_dominion or word_curse):
        print("You do not know any words in Ozhkavosh.")
    else:
        if spell_learn == 2 or spell_unlock == 2 or spell_persuade == 2 or spell_jump == 2 or spell_light == 2 or spell_heal == 2 or spell_feed == 2 or spell_kill == 2 or spell_killself == 2:
            print("Spells:")
            if spell_learn == 2:
                print("    Ozh ensh")
                print("\tI learn - Translates the meaning and effects of words from Ozkavosh.")
            if spell_unlock == 2:
                print("    Ozh vo'ses sa")
                print("\tI unlock this - Opens locks.")
            if spell_persuade == 2:
                print("    Izh vo'poz")
                print("\tYou have no power - Persuades those blocking the way.")
            if spell_killself == 2:
                print("    Ozh sol fek")
                print("\tMy life ends - Kills self.")
            if spell_jump == 2:
                print("    Ozh thok alatho")
                print("\tI move forward - Crosses large gaps.")
            if spell_light == 2:
                print("    Ozh groth sol")
                print("\tI open the light - Creates a glowing ball that illuminates your surroundings.")
            if spell_heal == 2:
                print("    Ozh vo'irush")
                print("\tI am without illness - Mends all wounds and illnsesses.")
            if spell_feed == 2:
                print("    Ozh gluth nith")
                print("\tI consume the Earth - Satisfies all hunger.")
            if spell_kill == 2:
                print("    Ozh gluth izh sol")
                print("\tI consume your soul - Kills your enemies.")
        if word_oblivion == 2 or word_darkness == 2 or word_reign == 2 or word_stop == 2 or word_servant == 2 or word_mirror == 2 or word_dominion == 2 or word_curse == 2:
            print("Non-spells:")
            if word_oblivion == 2:
                print("    Eyik vo'hollom - Behold oblivion.")
            if word_darkness == 2:
                print("    Omoz gloth izh - Darkness welcomes you.")
            if word_mirror == 2:
                print("    Izh tal el ozh ica rek'tal - Talk and I will mirror.")
            if word_dominion == 2:
                print("    Ahm'domosh - Highest dominion.")
            if word_reign == 2:
                print("    Ozkavosh icha domosh sa nith - Demon-kind shall reign upon this land.")
            if word_stop == 2:
                print("    Izh icha vo'fek ozh domosh - You will not stop my reign.")
            if word_servant == 2:
                print("    Ahm'fol - Servant of Vesh'arkosh.")
            if word_curse == 2:
                print("    Sof izh - Curse you.")
        if spell_learn == 1 or spell_unlock == 1 or spell_persuade == 1 or spell_jump == 1 or spell_light == 1 or spell_heal == 1 or spell_feed == 1 or spell_kill == 1 or spell_killself == 1 or word_oblivion == 1 or word_darkness == 1 or word_reign == 1 or word_stop == 1 or word_servant == 1 or word_mirror == 1 or word_dominion == 1 or word_curse == 1:
            print("Unknown:")
            if spell_learn == 1:
                print("    Ozh ensh")
            if spell_unlock == 1:
                print("    Ozh vo'ses sa")
            if spell_persuade == 1:
                print("    Izh vo'poz")
            if spell_jump == 1:
                print("    Ozh thok alatho")
            if spell_light == 1:
                print("    Ozh groth sol")
            if spell_heal == 1:
                print("    Ozh vo'irush")
            if spell_feed == 1:
                print("    Ozh gluth nith")
            if spell_kill == 1:
                print("    Ozh gluth izh sol")
            if spell_killself == 1:
                print("    Ozh sol fek")
            if word_oblivion == 1:
                print("    Eyik vo'hollom")
            if word_darkness == 1:
                print("    Omoz gloth izh")
            if word_mirror == 1:
                print("    Izh tal et ozh icha rek'tal")
            if word_dominion == 1:
                print("    Ahm'domosh")
            if word_reign == 1:
                print("    Ozkavosh icha domosh sa nith")
            if word_stop == 1:
                print("    Izh icha vo'fek ozh domosh")
            if word_servant == 1:
                print("    Ahm'fol")
            if word_curse == 1:
                print("    Sof izh")

def reverse(option):
    output_list = []
    for character in option:
        output_list.insert(0,character)
    output_string = "".join(output_list)
    return output_string

def reverse_cap(option):
    return reverse(option)[0].upper() + reverse(option)[1:].lower()

# Main Menu
# Splash Screen
# All credit goes to:
#   http://www.chris.com/ascii/index.php?art=art%20and%20design/borders
#   http://patorjk.com/software/taag/
#
def menu():
    global help_counter
    print("                                                                              .---.")
    print("                                                                             /  .  \ ")
    print("                    WELCOME TO...                                           |\_/|   |")
    print("                                                                            |   |  /|")
    print("  .-------------------------------------------------------------------------------' |")
    print(" /  .-.     _____  _   _  _____ _____ _____ _____   _____ _   _ _____ _____ _____   |")
    print("|  /   \   /  __ \| | | ||  ___|  ___/  ___|  ___| |  _  | | | |  ___/  ___|_   _|  |")
    print("| |\_.  |  | /  \/| |_| || |__ | |__ \ `--.| |__   | | | | | | | |__ \ `--.  | |    |")
    print("|\|  | /|  | |    |  _  ||  __||  __| `--. \  __|  | | | | | | |  __| `--. \ | |    |")
    print("| `---' |  | \__/\| | | || |___| |___/\__/ / |___  \ \/' / |_| | |___/\__/ / | |    |")
    print("|       |   \____/\_| |_/\____/\____/\____/\____/   \_/\_\\\___/\____/\____/  \_/    |")
    print("|       |         _____  _           ___  _  _        _    _           __           |")
    print("|       |        |_   _|| |_   ___  | _ \| |(_) __ _ | |_ | |_   ___  / _|          |")
    print("|       |          | |  | ' \ / -_) |  _/| || |/ _` || ' \|  _| / _ \|  _|          |")
    print("|       |          |_|  |_||_|\___| |_|  |_||_|\__, ||_||_|\__| \___/|_|            |")
    print("|       |              _  __           _     _ |___/               _                |")
    print("|       |             | |/ / __ _  ___| |_  | |__ __ _ __ __ __ _ | |               |")
    print("|       |             | ' < / _` |(_-<| ' \ | / // _` |\ V // _` || |               |")
    print("|       |             |_|\_\\\__,_|/__/|_||_||_\_\\\__,_| \_/ \__,_||_|               |")
    print("|       |                                                                          /")
    print("|       |-------------------------------------------------------------------------'")
    print("\       |" )
    print(" \     /                             Version",VERSION)
    print("  `---'")
    print()
    print("    1. Start Game        2. How to Play        3. About this Game        4. Quit")
    choice_made = False
    while not choice_made:
        option = input("\n> ").lower()
        if option in ("1","1.","start","start game","1. start game"):
            option = 1
            choice_made = True
        elif option in ("2","2.","how to play","2. how to play"):
            print("Basic controls:")
            print("    General")
            print("        i - Inventory")
            print("        x - Examine")
            print("        l - Look")
            print("        g - Repeat previous commmand")
            print("    Movement")
            print("        n - North")
            print("        e - East")
            print("        w - West")
            print("        s - South")
            print("        u - Up")
            print("        d - Down")
            print("\nAlso, it's recommended to play in fullscreen.")
        elif option in ("3","3.","about","about game","about this game"):
            print("Version " + VERSION + "\n")
            print("Cheese Quest: The Plight of Kashkaval was developed by Evan Quan from on Oct. 17, 2016 to " + DATE + ". The original motivation behind the game's creation was to complete an assignment for CPSC 231 (found here: http://pages.cpsc.ucalgary.ca/~tamj/2016/231F/assignments/assignment2/index.htm). It draws inspiration from a variety of other works including:\n    - Terry Jones' and Terry Gilliam's movie, \"Monty Python and the Holy Grail.\" (1975)\n    - Douglas Adams' and Steve Meretzky's text adventure game, \"Hitchhicker's Guide to the Galaxy.\" (1984)\n    - Irrational Games' first-person shooter, \"Bioshock\" (2007)\n    - Frictional Game's survival horror game, \"Amnesia: The Dark Descent.\" (2010)\n    - Bethesda Game Studio's open world RPG, \"The Elder Scrolls V: Skyrim.\" (2011)\n    - Blizzard's online CCG \"Heartstone.\" (2014)")
        elif option in ("4","4.","quit","4. quit","q"):
            option = 4
            choice_made = True
        elif option == "debug":
            choice_made = True
        else:
            print("Select 1, 2, 3, or 4.")
    if option == 1:
        inGame(False)
    elif option == 4:
        print("Okay. Bye!")
    elif option == "debug":
        print("Type !debug to see all the debug commands.")
        inGame(True)
    else:
        print("Uh oh. You broke the game. :(")

# Repeat
def repeat():
    choice_made = False
    debug = False
    while not choice_made:
        option = input("What would you like to do?\n    1. Play Again\n    2. Return to Main Menu\n    3. Quit\n> ").lower()
        if option in ("1","play","play again"):
            choice_made = True
        elif option in ("2","return","menu","main menu","return to main menu"):
            choice_made = True
        elif option in ("3","quit","q"):
            choice_made = True
        elif option == "debug":
            debug =  True
            choice_made = True
        else:
            print("Select 1, 2, or 3.")
    if option in ("1","play","play again"):
        inGame(debug)
    elif option in ("2","return","menu","main menu","return to main menu"):
        menu()
    elif option in ("3","quit","q"):
        print("Okay. Bye!")
    elif debug:
        inGame(debug)
# In Game
def inGame(debug):
    global deaths_total,turnCounter_total,spell_learn,spell_unlock,spell_persuade,spell_jump,spell_light,spell_heal,spell_feed,spell_kill,spell_killself,word_oblivion,word_darkness,word_reign,word_stop,word_servant,word_mirror,word_dominion,word_curse
    HUNGER_START = HUNGER_START_NORMAL
#_______Player Information______________________________________________________
# Information about Player
# Stats
    class Stat(object):
        def __init__(self, name = False, hunger = HUNGER_START, health = HEALTH_MAX):
            self.name = name
            self.hunger = hunger
            self.health = health
            self.healthmax = HEALTH_MAX
        def examineHunger(self):
            if self.hunger <= 1:
                print("YOU'RE ALMOST STARVING TO DEATH.")
            elif self.hunger <= 3:
                print("You feel extremely hungry.")
            elif self.hunger <= 6:
                print("You feel very hungry.")
            elif self.hunger <= 9:
                print("You feel quite hungry.")
            elif self.hunger <= 12:
                print("You feel moderately hungry.")
            elif self.hunger <= 15:
                print("You feel a bit hungry.")
            elif self.hunger <= HUNGER_MAX:
                print("You feel satisfied.")
            else:
                print("\nYou feel full.")
        def examineHealth(self):
            if self.health == 7:
                print("You feel fine.")
            elif self.health in range(2,7):
                print("You're bleeding out.")
            elif self.health == 1:
                print("YOU'RE ALMOST DEAD.")
    # Inventory
    class Inventory(object):
        def __init__(self, name = "room", gold = 0, letter = 0, key = 0, keySkeleton = 0, pickaxe = 0, shrubbery = 0, funnelCake = 0, halfFunnelCake = 0, foot = 0, porridge = 0, bowl = 0, lantern = 0, oil = 0, pie = 0, coal = 0, biscuit = 0, hook = 0, staff = 0, ticket = 0, potato = 0, bandage = 0, journal = 0, book = 0, brie = 0, munster = 0, stilton = 0, swiss = 0, wensleydale = 0, potion = 0, flask = 0, stone = 0):
            self.name = name
            self.gold = gold
            self.goldDescription = "The edges are worn down from handling."
            self.letter = letter
            self.letterDescription = "Made out of old parchment, the message on it is written in ink."
            self.letterRead = "If you're reading this, you broke the game. :("
            self.key = key
            self.keyDescription = "The key to your jail cell."
            self.keySkeleton = keySkeleton
            self.keySkeletonDescription = "The head is a skull with glowing purple eyes."
            self.pickaxe = pickaxe
            self.pickaxeDescription = "A sturdy tool useful for mining."
            self.shrubbery = shrubbery
            self.shrubberyDescription = "It's a very nice shrubbery, and not too expensive."
            self.funnelCake = funnelCake
            self.funnelCakeDescription = "It's covered in powdered sugar. Mmmmm... and still warm."
            self.halfFunnelCake = halfFunnelCake
            self.halfFunnelCakeDescription = "Ew. You can see the bite marks."
            self.foot = foot
            self.footDescription = "A prominent symbol of RNGesus, the ancient god of gambling, luck, and salt."
            self.porridge = porridge
            self.porridgeDescription = "Bland prison food. Makes you consider if eating this is everyday was the real punishment."
            self.bowl = bowl
            self.bowlDescription = "Small and dented. Made of out of tin."
            self.lantern = lantern
            self.lanternDescription = "Perfect for lighting dark areas."
            self.oil = oil
            self.oilDescription = "Perfect for things that are perfect for lighting dark areas."
            self.pie = pie
            self.pieDescription = "Hardy and delicious. Just looking at it makes you hungry."
            self.biscuit = biscuit
            self.biscuitDescription = "Harder than a brick and probably just as tasty."
            self.hook = hook
            self.hookDescription = "Just having one makes you feel like a secret agent."
            self.staff = staff
            self.staffDescription = "Elaboratedly designed and encrusted with sapphires, this staff holds unspeakable power."
            self.ticket = ticket
            self.ticketDescription = "The number 77 is written on it."
            self.coal = coal
            self.coalDescription = "A valuable fuel source for a variety of uses."
            self.potato = potato
            self.potatoDescription = "Somewhat covered in dirt, it looks rotten."
            self.bandage = bandage
            self.bandageDescription = "Good for healing wounds."
            self.journal = journal
            self.journalDescription = "The cover is leather-bound. The whatever text there was along the spine is worn off."
            self.book = book
            self.bookDescription = "The cover is entirely black, made out of a material unbeknownst to you. It is heavier than it looks."
            self.brie = brie
            self.brieDescription = "Soft and creamy."
            self.munster = munster
            self.munsterDescription = "Milky white."
            self.stilton = stilton
            self.stiltonDescription = "Hard, with blue veins."
            self.swiss = swiss
            self.swissDescription = "It has more holes than the plot to the story of this game."
            self.wensleydale = wensleydale
            self.wensleydaleDescription = "Crumbly and a bit dry."
            self.potion = potion
            self.potionDescription = "The fluid is red, bubbling, and and foggy. Looks completely safe to drink."
            self.flask = flask
            self.flaskDescription = "It's empty."
            self.stone = stone
            self.stoneDescription = "It glows a pulsing red as if it were alive. It also seems to produces its own heat."
        def itemTypes(self):
            type_count = 0
            if self.gold:
                type_count += 1
            if self.letter:
                type_count += 1
            if self.key:
                type_count += 1
            if self.keySkeleton:
                type_count += 1
            if self.pickaxe:
                type_count += 1
            if self.shrubbery:
                type_count += 1
            if self.funnelCake:
                type_count += 1
            if self.halfFunnelCake:
                type_count += 1
            if self.foot:
                type_count += 1
            if self.porridge:
                type_count += 1
            if self.bowl:
                type_count += 1
            if self.lantern:
                type_count += 1
            if self.oil:
                type_count += 1
            if self.pie:
                type_count += 1
            if self.biscuit:
                type_count += 1
            if self.hook:
                type_count += 1
            if self.staff:
                type_count += 1
            if self.ticket:
                type_count += 1
            if self.coal:
                type_count += 1
            if self.potato:
                type_count += 1
            if self.bandage:
                type_count += 1
            if self.journal:
                type_count += 1
            if self.book:
                type_count += 1
            if self.brie:
                type_count += 1
            if self.munster:
                type_count += 1
            if self.stilton:
                type_count += 1
            if self.swiss:
                type_count += 1
            if self.wensleydale:
                type_count += 1
            if self.potion:
                type_count += 1
            if self.flask:
                type_count += 1
            if self.stone:
                type_count += 1
            return type_count
        def examineInventory(self, option):
            # Quantity and Description
            # Inventory
            if option == "inventory":
                if not self.itemTypes():
                    print("You have nothing.")
                else:
                    print("You have:")
                    if self.bandage == 1:
                        print("    a bandage")
                    elif self.bandage > 1:
                        print("   ",self.bandage,"bandages")
                    if self.porridge:
                        print("    a bowl of porridge")
                    if self.bowl:
                        print("    a bowl")
                    if self.pie == 1:
                        print("    a chicken pot pie")
                    elif self.pie > 1:
                        print("   ",self.pie,"chicken pot pies")
                    if self.stone:
                        print("    a dragonstone")
                    if self.flask == 1:
                        print("    a flask")
                    elif self.flask > 1:
                        print("   ",self.flask,"flasks")
                    if self.funnelCake == 1:
                        print("    a funnel cake")
                    elif self.funnelCake > 1:
                        print("   ",self.funnelCake,"funnel cakes")
                    if self.gold:
                        print("   ",self.gold,"gold")
                    if self.hook == 1:
                        print("    a grappling hook")
                    elif self.hook > 1:
                        print("   ",self.hook,"grappling hooks")
                    if self.halfFunnelCake == 1:
                        print("    a half-eaten funnel cake")
                    elif self.halfFunnelCake > 1:
                        print("   ",self.halfFunnelCake,"half-eaten funnel cakes")
                    if self.biscuit == 1:
                        print("    a hardtack biscuit")
                    elif self.biscuit > 1:
                        print("   ",self.biscuit,"hardtack biscuits")
                    if self.journal == 1:
                        print("    a journal")
                    elif self.journal > 1:
                        print("   ",self.journal,"journals")
                    if self.key:
                        print("    a key")
                    if self.keySkeleton:
                        print("    the key of Ahm'domosh")
                    if self.lantern == 1:
                        print("    a lantern")
                    elif self.lantern > 1:
                        print("   ",self.lantern,"lanterns")
                    if self.letter:
                        print("    a letter")
                    if self.foot == 1:
                        print("    a lucky rabbit foot")
                    elif self.foot > 1:
                        print("   ",self.foot,"lucky rabbit feet")
                    if self.book == 1:
                        print("    a mysterious book")
                    elif self.book > 1:
                        print("   ",self.book,"mysterious books")
                    if self.pickaxe == 1:
                        print("    a pickaxe")
                    elif self.pickaxe > 1:
                        print("   ",self.pickaxe,"pickaxes")
                    if self.coal == 1:
                        print("    a piece of coal")
                    elif self.coal > 1:
                        print("   ",self.coal,"pieces of coal")
                    if self.potato:
                        print("    a potato")
                    if self.potion == 1:
                        print("    a potion of rejuvination")
                    elif self.potion > 1:
                        print("   ",self.potion,"potions of rejuvination")
                    if self.ticket:
                        print("    a raffle ticket")
                    if self.shrubbery == 1:
                        print("    a shrubbery")
                    elif self.shrubbery > 1:
                        print("   ",self.shrubbery,"shrubberies")
                    if self.brie:
                        print("    a slice of brie cheese")
                    if self.munster:
                        print("    a slice of munster cheese")
                    if self.stilton:
                        print("    a slice of stilton cheese")
                    if self.swiss:
                        print("    a slice of swiss cheese")
                    if self.wensleydale:
                        print("    a slice of wensleydale cheese")
                    if self.staff:
                        print("    the staff of Garrotxa")
                    if self.oil == 1:
                        print("    a vial of lantern oil")
                    elif self.oil > 1:
                        print("   ",self.oil,"vials of lantern oil")


            # Individual items
            elif option == "gold":
                if self.gold:
                    print(self.goldDescription)
                else:
                    print("You don't have gold to examine.")
            elif option == "letter":
                if self.letter:
                    print(self.letterDescription)
                else:
                    print("You don't have a letter to examine.")
            elif option == "key":
                if self.key:
                    print(self.keyDescription)
                else:
                    print("You don't have a key to examine.")
            elif option == "keySkeleton":
                if self.keySkeleton:
                    print(self.keySkeletonDescription)
                else:
                    print("You don't have a key to examine.")
            elif option == "pickaxe":
                if self.pickaxe:
                    print(self.pickaxeDescription)
                else:
                    print("You don't have a pickaxe to examine.")
            elif option == "shrubbery":
                if self.shrubbery:
                    print(self.shrubberyDescription)
                else:
                    print("You don't have a shrubbery to examine.")
            elif option == "funnelCake":
                if self.funnelCake:
                    print(self.funnelCakeDescription)
                else:
                    print("You don't have a funnel cake to examine.")
            elif option == "halfFunnelCake":
                if self.halfFunnelCake:
                    print(self.halfFunnelCakeDescription)
                else:
                    print("You don't have a half-eaten funnel cake to examine.")
            elif option == "foot":
                if self.foot:
                    print(self.footDescription)
                else:
                    print("You don't have a lucky rabbit root to examine.")
            elif option == "porridge":
                if self.porridge:
                    print(self.porridgeDescription)
                else:
                    print("You don't have a bowl of porridge to examine.")
            elif option == "bowl":
                if self.bowl:
                    print(self.bowlDescription)
                else:
                    print("You don't have a bowl to examine.")
            elif option == "lantern":
                if self.lantern:
                    print(self.lanternDescription)
                else:
                    print("You don't have a lantern to examine.")
            elif option == "oil":
                if self.oil:
                    print(self.oilDescription)
                else:
                    print("You don't have a vial of lantern oil to examine.")
            elif option == "pie":
                if self.pie:
                    print(self.pieDescription)
                else:
                    print("You don't have a chicken pot pie to examine.")
            elif option == "biscuit":
                if self.biscuit:
                    print(self.biscuitDescription)
                else:
                    print("You don't have a chicken pot pie to examine.")
            elif option == "hook":
                if self.hook:
                    print(self.hookDescription)
                else:
                    print("You don't have a grappling hook to examine.")
            elif option == "staff":
                if self.hook:
                    print(self.staffDescription)
                else:
                    print("You examine the tears from the sadness of not having the staff of Garrotxa.")
            elif option == "ticket":
                if self.ticket:
                    print(self.ticketDescription)
                else:
                    print("You don't have a raffle ticket to examine.")
            elif option == "coal":
                if self.coal:
                    print(self.coalDescription)
                else:
                    print("You don't have a piece of coal to examine.")
            elif option == "potato":
                if self.potato:
                    print(self.potatoDescription)
                else:
                    print("You don't have a potato to examine, unless you're talking about yourself.")
            elif option == "bandage":
                if self.bandage:
                    print(self.bandageDescription)
                else:
                    print("You don't have a bandage to examine.")
            elif option == "journal":
                if self.journal:
                    print(self.journalDescription)
                else:
                    print("You don't have a journal to examine.")
            elif option == "book":
                if self.book:
                    print(self.bookDescription)
                else:
                    print("You don't have a book to examine.")
            elif option == "brie":
                if self.brie:
                    print(self.brieDescription)
                else:
                    print("You don't have a slice of brie cheese to examine.")
            elif option == "munster":
                if self.munster:
                    print(self.munsterDescription)
                else:
                    print("You don't have a slice of munster cheese to examine.")
            elif option == "stilton":
                if self.stilton:
                    print(self.stiltonDescription)
                else:
                    print("You don't have a slice of stilton cheese to examine.")
            elif option == "swiss":
                if self.swiss:
                    print(self.swissDescription)
                else:
                    print("You don't have a slice of swiss cheese to examine.")
            elif option == "wensleydale":
                if self.wensleydale:
                    print(self.wensleydaleDescription)
                else:
                    print("You don't have a slice of wensleydale cheese to examine.")
            elif option == "potion":
                if self.potion:
                    print(self.potionDescription)
                else:
                    print("You don't have a potion of rejuvination to examine")
            elif option == "flask":
                if self.flask:
                    print(self.flaskDescription)
                else:
                    print("You don't have a flask to examine.")
            elif option == "stone":
                if self.stone:
                    print(self.stoneDescription)
                else:
                    print("You don't have a dragonstone to examine.")
    DEBUG_INV = Inventory(gold = 100000, pickaxe = 1, lantern = 1, oil = 10000, pie = 30000, coal = 100000, foot = 100, hook = 10, staff = 1, ticket = 1, bandage = 100, journal = 1, book = 1, potato = 1, stone = 1)
    #______Rooms____________________________________________________
    # Room information
    #	 Description
    #	 Adjacent Rooms
    #	 Check Adjacent Rooms
    #	 Items Present

    # Room
    class room(object):
        def __init__(self, name = "name", gold = 0, letter = 0, key = 0, keySkeleton  = 0, pickaxe = 0, shrubbery = 0, funnelCake = 0, halfFunnelCake = 0, foot = 0, porridge = 0, bowl = 0, lantern = 0, oil = 0, pie = 0, biscuit = 0, hook = 0, staff = 0, ticket = 0, coal = 0, potato = 0, bandage = 0, journal = 0, book = 0, brie = 0, munster = 0, stilton = 0, swiss = 0, wensleydale = 0, potion = 0, flask = 0, stone = 0, north = False, northBlocked = False, northBlockedReason = False, east = False, eastBlocked = False, eastBlockedReason = False, south = False, southBlocked = False, southBlockedReason = False, west = False, westBlocked = False, westBlockedReason = False, up = False, upBlocked = False, upBlockedReason = False, down = False, downBlocked = False, downBlockedReason = False,  betMade = False, counterAns_1 = 0, counterAns_2 = 0, counterAns_3 = 0, counterAns_4 = 0, counter_1 = 0, counter_2 = 0, counter_3 = 0, counter_4 = 0, characterDead = True, itemFound = False, firstTime = True, isBet = False, isBuy = False, isCrevasse = False, isGive = False, isMine = False, isFill = False, isSell = False):
            # Items
            self.name = name
            self.gold = gold
            self.letter = letter
            self.key = key
            self.keySkeleton = keySkeleton
            self.pickaxe = pickaxe
            self.shrubbery = shrubbery
            self.funnelCake = funnelCake
            self.halfFunnelCake = halfFunnelCake
            self.foot = foot
            self.porridge = porridge
            self.bowl = bowl
            self.lantern = lantern
            self.oil = oil
            self.pie = pie
            self.biscuit = biscuit
            self.hook = hook
            self.staff = staff
            self.ticket = ticket
            self.coal = coal
            self.potato = potato
            self.bandage = bandage
            self.journal = journal
            self.book = book
            self.brie = brie
            self.munster = munster
            self.stilton = stilton
            self.swiss = swiss
            self.wensleydale = wensleydale
            self.potion = potion
            self.flask = flask
            self.stone = stone
            # Check Adjacent Rooms
            self.north = north
            self.northBlocked = northBlocked
            self.northBlockedReason = northBlockedReason
            self.east = east
            self.eastBlocked = eastBlocked
            self.eastBlockedReason = eastBlockedReason
            self.south = south
            self.southBlocked = southBlocked
            self.southBlockedReason = southBlockedReason
            self.west = west
            self.westBlocked = westBlocked
            self.westBlockedReason = westBlockedReason
            self.up = up
            self.upBlocked = upBlocked
            self.upBlockedReason = upBlockedReason
            self.down = down
            self.downBlocked = downBlocked
            self.downBlockedReason = downBlockedReason
            # Check if bet made
            # Applies only to betting rooms
            self.betMade = betMade
            # Room specific integer values
            # roomJailCorridor vault code
            # roomCarnival raffle return duration
            # roomCave coalmines coal amount
            self.counterAns_1 = counterAns_1
            self.counterAns_2 = counterAns_2
            self.counterAns_3 = counterAns_3
            self.counterAns_4 = counterAns_4
            self.counter_1 = counter_1
            self.counter_2 = counter_2
            self.counter_3 = counter_3
            self.counter_4 = counter_4
            # Troll dead
            # Applies only to roomBridge
            self.characterDead = characterDead
            # Item found
            # If action is done in room, item appears. If action is done again, more items do not appear.
            self.itemFound = itemFound
            # First time
            # Only applies to first time entering a room
            self.firstTime = firstTime
            # Room Type
            self.isBet = isBet
            self.isBuy = isBuy
            self.isCrevasse = isCrevasse
            self.isGive = isGive
            self.isMine = isMine
            self.isFill = isFill
            self.isSell = isSell
        # Description
        def description(self):
            print("\nRoom Name\n\nDescription placeholder.")
            # Adjacent Rooms
            print("\nTo the North is north room.")
            print("To the East is east room.")
            print("To the South is south room.")
            print("To the West is west room.")
        def itemTypes(self):
            type_count = 0
            if self.gold:
                type_count += 1
            if self.letter:
                type_count += 1
            if self.key:
                type_count += 1
            if self.keySkeleton:
                type_count += 1
            if self.pickaxe:
                type_count += 1
            if self.shrubbery:
                type_count += 1
            if self.funnelCake:
                type_count += 1
            if self.halfFunnelCake:
                type_count += 1
            if self.foot:
                type_count += 1
            if self.porridge:
                type_count += 1
            if self.bowl:
                type_count += 1
            if self.lantern:
                type_count += 1
            if self.oil:
                type_count += 1
            if self.pie:
                type_count += 1
            if self.biscuit:
                type_count += 1
            if self.hook:
                type_count += 1
            if self.staff:
                type_count += 1
            if self.ticket:
                type_count += 1
            if self.coal:
                type_count += 1
            if self.potato:
                type_count += 1
            if self.bandage:
                type_count += 1
            if self.journal:
                type_count += 1
            if self.book:
                type_count += 1
            if self.brie:
                type_count += 1
            if self.munster:
                type_count += 1
            if self.stilton:
                type_count += 1
            if self.swiss:
                type_count += 1
            if self.wensleydale:
                type_count += 1
            if self.potion:
                type_count += 1
            if self.flask:
                type_count += 1
            if self.stone:
                type_count += 1
            return type_count
        def itemsPresent(self):
            # Items Present
            if self.itemTypes():
                if self.itemTypes() > 1:
                    print("\nThere are some items here:")
                else:
                    print("\nThere is an item here:")
            if self.bandage == 1:
                print("    a bandage")
            elif self.bandage > 1:
                print("   ",self.bandage,"bandages")
            if self.porridge:
                print("    a bowl of porridge")
            if self.bowl:
                print("    a bowl")
            if self.pie == 1:
                print("    a chicken pot pie")
            elif self.pie > 1:
                print("   ",self.pie,"chicken pot pies")
            if self.stone:
                print("    a dragonstone")
            if self.flask == 1:
                print("    a flask")
            elif self.flask > 1:
                print("   ",self.flask,"flasks")
            if self.funnelCake == 1:
                print("    a funnel cake")
            elif self.funnelCake > 1:
                print("   ",self.funnelCake,"funnel cakes")
            if self.gold:
                print("   ",self.gold,"gold")
            if self.hook == 1:
                print("    a grappling hook")
            elif self.hook > 1:
                print("   ",self.hook,"grappling hooks")
            if self.halfFunnelCake == 1:
                print("    a half-eaten funnel cake")
            elif self.halfFunnelCake > 1:
                print("   ",self.halfFunnelCake,"half-eaten funnel cakes")
            if self.biscuit == 1:
                print("    a hardtack biscuit")
            elif self.biscuit > 1:
                print("   ",self.biscuit,"hardtack biscuits")
            elif self.journal == 1:
                print("    a journal")
            elif self.journal > 1:
                print("   ",self.journal,"journals")
            if self.key:
                print("    a key")
            if self.keySkeleton:
                print("    the key of Ahm'domosh")
            if self.lantern == 1:
                print("    a lantern")
            elif self.lantern > 1:
                print("   ",self.lantern,"lanterns")
            if self.letter:
                print("    a letter")
            if self.foot == 1:
                print("    a lucky rabbit foot")
            elif self.foot > 1:
                print("   ",self.foot,"lucky rabbit feet")
            if self.book == 1:
                print("    a mysterious book")
            elif self.book > 1:
                print("   ",self.book,"mysterious books")
            if self.pickaxe == 1:
                print("    a pickaxe")
            elif self.pickaxe > 1:
                print("   ",self.pickaxe,"pickaxes")
            if self.coal == 1:
                print("    a piece of coal")
            elif self.coal > 1:
                print("   ",self.coal,"pieces of coal")
            if self.potato:
                print("    a potato")
            if self.potion == 1:
                print("    a potion of rejuvination")
            elif self.potion > 1:
                print("   ",self.potion,"potions of rejuvination")
            if self.ticket:
                print("    a raffle ticket")
            if self.shrubbery == 1:
                print("    a shrubbery")
            elif self.shrubbery > 1:
                print("   ",self.shrubbery,"shrubberies")
            if self.brie:
                print("    a slice of brie cheese")
            if self.munster:
                print("    a slice of munster cheese")
            if self.stilton:
                print("    a slice of stilton cheese")
            if self.swiss:
                print("    a slice of swiss cheese")
            if self.wensleydale:
                print("    a slice of wensleydale cheese")
            if self.staff:
                print("    the staff of Garrotxa")
            if self.oil == 1:
                print("    a vial of lantern oil")
            elif self.oil > 1:
                print("   ",self.oil,"vials of lantern oil")

    #_______Jail____________________________________________________________________
    # Cell
    class roomJailCell(room):
        # Description
        def description(self):
            print("\nJail Cell\n\nThere are stone walls enclosed around you. There is a solid metal door with a small slit at eye-level peering into the corridor. There is a haystack here.\n")
            # Adjacent Rooms
            print("    To the East is the jail corridor.")
    # Corridor
    class roomJailCorridor(room):
        # Description
        def description(self):
            print("\nJail Corridor\n\nJail cells line the the hallway on both sides. There is a vault door to the north, with a combination lock. There are four large dials, each set to a number from 0 to 9.\nThe first dial reads %i.\nThe second dial reads %i.\nThe third dial reads %i.\nThe fourth dial reads %i.\n" % (self.counter_1, self.counter_2, self.counter_3, self.counter_4))
            # Adjacent Rooms
            print("    To the North is the antechamber.")
            print("    To the West is is your jail cell.")
    # Antechamber
    class roomJailAntechamber(room):
        def description(self):
            print("\nAntechamber\n\nThe stone walls are close together, leaving just enough room for you to stand in.\n")
            print("    There is a stairwell that goes down.")
            print("    To the South is the corridor.")
    # Foyer
    class roomJailFoyer(room):
        # Description
        def description(self):
            print("\nFoyer\n\nThe place is rather cozy and well-lit. Definitely a contrast from all those years in your cell.\n")
            # Adjacent Rooms
            print("    There is a stairwell that goes up.")
            print("    To the East is the hallway.")
            print("    To the West is outside.")
    # Hallway
    class roomJailHallway(room):
        # Description
        def description(self):
            print("\nHallway\n\nLight from outside pears in from the Northern windows. This is the first time you've felt sunlight in ages.\n")
            # Adjacent Rooms
            print("    To the East to the break room.")
            print("    To the West is inside.")
    # Break Room
    class roomJailBreakRoom(room):
        # Description
        def description(self):
            print("\nBreak Room\n\nThere's some comfortable chairs and tables scattered around the room.\n")
            # Adjacent Rooms
            print("    To the West is the hallway.")
            print("    To the South is the washroom.")
    # Entrance
    class roomJailEntrance(room):
        # Description
        def description(self):
            if self.firstTime:
                firsttime = "Finally, outside! This is the first time you've felt sunlight in ages. "
            else:
                firsttime = ""
            print("\nJail Entrance\n\n%sA wall surrounds the prison from all sides, with a gate leading north.\n" % (firsttime))
            # Adjacent Rooms
            print("    To the East is the foyer.")
            print("    To the West is the south courtyard.")
    #_______Town____________________________________________________________________
    # Courtyard
    class roomCourtyardNorth(room):
        # Description
        def description(self):
            if self.firstTime:
                firsttime = "A young urchin boy rings a bell, yelling \"Come one, come all, to the Wonderful Wheel of Mystery! Short on cash? Want to get rich quick? Spin the wheel and test your luck! Only at the Airedale Carnival!\""
            elif roomCarnivalWheelGame.characterDead:
                firsttime = ""
            else:
                firsttime = "A young urchin boy rings a bell, yelling \"The Wonderful Wheel of Mystery will be announcing the winner soon! Get your ticket while you can!\""
            print("\nNorth Courtyard\n\nA crowd of people fill the streets. Various buildings surround the courtyard from every angle.",firsttime,"\n")
            # Adjacent Rooms
            print("    To the North is the town gate.")
            print("    To the East is the general store.")
            print("    To the West is the carnival grounds.")
            print("    To the South is the south courtyard.")
    class roomCourtyardSouth(room):
        # Description
        def description(self):
            print("\nSouth Courtyard\n\nA large elegant fountain rests in the centre, surrounded by busy people minding their own business.\n")
            # Adjacent Rooms
            print("    To the North is the north courtyard.")
            print("    To the East is the jail entrance.")
            print("    To the West is the blacksmith.")
            print("    To the South is the alchemist's hut.")
    # Blacksmith
    class roomBlacksmith(room):
        # Description
        def description(self):
            print("\nBlacksmith\n")
            if self.characterDead:
                print("There is a charred body here. An open furnace lights up the place and several tools lay on an adjacent table.")
            else:
                print("A man in an apron rests at stand near the entrance. Behind him, an open furnace lights up the place and several of his tools lay on an adjacent table.",end = " ")
                if self.firstTime:
                    print("\"You new here mate? Don't recognize the face. Buy anyting here you'd like. Ever since the incident at the Mount Magna mine, I've been running low on coal to keep my furnace going. However, if you've got the guts to go up there, I'd be willing to buy any off from you.\"")
                else:
                    print("\"Y'here to buy anything? If not, I'd be willin' to buy any coal off you. In times like these, I could always use some more fuel for my fire.\"")
                print("\nGoods available to buy:\n    Pickaxe (%s gold)\n    Grappling Hook (%s gold)" % (PRICE_BUY_PICKAXE,PRICE_BUY_HOOK))
                print("\nGoods available to sell:\n    Coal (%s gold)" % PRICE_SELL_COAL)
            if self.isBuy:
                print("\nYou have",inv.gold,"gold.")
                if inv.coal > 1:
                    print("You have",inv.coal,"pieces of coal.")
                elif inv.coal:
                    print("You have",inv.coal,"piece of coal.")
            # Adjacent Rooms
            print("\n    To the East is the south courtyard.")
    # Alchemist
    class roomAlchemist(room):
        def description(self):
            print("\nAlchemist's Hut\n")
            if self.characterDead:
                dead = "The dead body of Tim the Enchanter is here."
            else:
                dead = "An old man, wearing black robes and a leather cap with goat horns wanders around from shelf to shelf, examining various strange ingredients."
            if self.firstTime:
                firsttime = "\"Sorry, the shop is closed. I ran out of crushed dragonstone powder, a very rare and expensive ingredient necessary for all my potions. If you could find me a dragonstone, I would be willing to buy it off from you, and I'll be able to start up my business again. How does %s gold sound? Oh, I almost forgot to introduce myself. I'm Tim. Tim the Enchanter.\"" % PRICE_SELL_STONE
            else:
                if self.isBuy:
                    firsttime = "\"Come on in. The fire's roaring. Business has been going great since you got me that dragonstone. What can I get for you?\""
                else:
                    firsttime = "\"Hello again. I'm still empty on crushed dragonstone powder so I won't be able to make any potions. If you have a dragonstone, the offer is still on.\""
            if self.isBuy:
                buy = "filled with a bubbling, red liquid, rests above hot coals."
            else:
                buy = "rests above an fire, filled only with water."
            print("A large, cast-iron cauldron",buy,dead,firsttime)
            if self.isBuy: # and self.isFill:
                print("\nGoods available to buy:\n    Potion of rejuvination (%s gold)" % PRICE_BUY_POTION)
                print("\nGoods available to fill:\n    Flask (%s gold)" % PRICE_REFILL_POTION)
            if self.isSell:
                print("\nGoods available to sell:\n    Dragonstone (%s gold)" % PRICE_SELL_STONE)
            if self.isBuy:
                print("\nYou have",inv.gold,"gold.")
            print("\n    To the North is the south courtyard.")
    # Town Gate
    class roomGate(room):
        # Description
        def description(self):
            if self.northBlocked:
                if self.firstTime:
                    firsttime = "\"Hey, you there.\" says one of the guards as he approachs you. He looks around cautiously before lowering his voice. \"I'm the one who locked you up years ago, you thievish rat. I'd call for more guards to take you back to your cell, but I need something. You're a good thief and I'm sure you have a good eye. A while back, I lost a shrubbery somewhere in this town. Retrieve it for me, one that looks nice and not too expensive, and I'll let you through the gate.\""
                else:
                    firsttime = "\"Don't think you can get pass the gate until I get that shrubbery. Try any funny business and I'll send you back to your cell.\""
            else:
                firsttime = "He recognizes you and opens it."
            print("\nTown Gate\n\nA guard stands at the gate.", firsttime)
            # Adjacent Rooms
            print("\n    To the North is a pathway.")
            print("    To the South is the north courtyard.")
    # General Store
    class roomGeneralStore(room):
        # Description
        def description(self):
            print("\nGeneral Store\n")
            if self.characterDead:
                print("There is a charred body on the floor.")
            else:
                if self.firstTime:
                    welcome = "Welcome to my shop, little human. I have goods. You have money. We make deal."
                else:
                    welcome = "I see you have returned little human. Welcome back."
                print("The shopkeeper welcomes you in the his store. \"%s\"" % welcome)
                print("\nGoods available to buy:\n    Lucky Rabbit Foot (%s gold)\n    Lantern (%s gold)\n    Vial of Lantern Oil (%s gold)\n    Bandage (%s gold)" % (PRICE_BUY_FOOT,PRICE_BUY_LANTERN,PRICE_BUY_OIL,PRICE_BUY_BANDAGE))
            if self.isBuy:
                print("\nYou have",inv.gold,"gold.")
            # Adjacent Rooms
            print("\n    To the West is the courtyard.")
    # Carnival
    class roomCarnival(room):
        # Description
        def description(self):
            if self.southBlocked:
                sClosed = "Closed: Out of Business"
            else:
                sClosed = "Sybil\'s Shell Game"
            if self.westBlocked:
                wClosed = "FRAUD"
            else:
                wClosed = "Mystery"
            print("\nCarnival Grounds\n\nFestival lights are strung above, with colourful banners swaying in the wind. A large party of adults and children alike fill the carnival grounds, bustling about from tent to tent.")
            if self.isGive:
                print("The spokesperson is sitting outside at a table with two guards. A crowd of people surround her, GIVING her their raffle tickets.")
            print('\n    To the North is a tent labelled, "Funnel Cakes Galore."')
            print("    To the East is the courtyard.")
            print('    To the West is a tent labelled, "The Wonderful Wheel of %s."' % wClosed)
            print('    To the South is a tent labelled, "%s."' % sClosed)
    # Carnival Shell Game
    class roomCarnivalShellGame(room):
        def description(self):
            print("\nSybil\'s Shell Game")
            if self.isBet:
                print("\nInside the tent is an old woman hunched at a small, fragile wooden table.")
                if self.betMade:
                    print("\nShe is waiting for you to chose a shell.")
                else:
                    print('With only one eye, she turns to you and begins to chant.\n"Hear ye, hear ye! Bet if you wish some gold out of glee! Test your luck with a guess from these shells three! Double your money if you win, you shall see!"')
            elif not self.characterDead:
                print("\nSybil is packing her stuff to leave the carnival.")
            else:
                print("\n    Inside the tent is a charred body on the ground, behind a fragile wooden table.")
            if self.isBet:
                print("\nYou have",inv.gold,"gold.")
            print("\n    To the North is the carnival grounds.")
    # Carnival Food
    class roomCarnivalFood(room):
        # Description
        def description(self):

            print("\nFunnel Cakes Galore\n")
            if self.characterDead:
                menu = ""
                print("There is a charred body here, lying by a stovetop.")
            else:
                if invFood.funnelCake <= 0:
                    menu = "\nGoods available to buy:\n    Chicken pot pie (%i gold)" % (PRICE_BUY_PIE)
                else:
                    menu = "\nGoods available to buy:\n    Funnel cake (%i gold)\n    Chicken pot pie (%i gold)" % (PRICE_BUY_FUNNELCAKE,PRICE_BUY_PIE)
                print("\nInside the tent is an older overweight vendor at a stovetop, cooking up funnel cakes.")
                if self.firstTime:
                    print('\n"Funnel cakes! Get your funnel cakes here!" he shouts with joy. "Enjoying the carnival so far? What better way to brighten your day with some freshly fried funnel cakes at Funnel Cakes Galore! Oh, we also sell pies here too, but no one these days are looking to buy pies at a carnival.')
                else:
                    if invFood.funnelCake > 0:
                        if invFood.funnelCake < FUNNELCAKE_LIMIT / 2:
                            print("\n\"Oh look, it's you again. You seem to be really liking those funnel cakes. You know, I only have so much batter for today. I didn't really expect you to buy so many.\"")
                        elif invFood.funnelCake < FUNNELCAKE_LIMIT:
                            print("\n\"You're here to buy more funnel cakes? They're delicious, I know!\"")
                        else:
                            print("\n\"Good seeing you again. Change your mind of buying some mighty fine funnel cakes?\"")
                    else:
                        print("\n\"I'm all out of funnel cake batter. You can still buy some pies if you want.\"")
            print(menu)
            if self.isBuy:
                print("\nYou have",inv.gold,"gold.")
            print("\n    To the South is the carnival grounds.")
    class roomCarnivalWheelGame(room):
        # Description
        def description(self):
            print("\nWonderful Wheel of Mystery\n\nInside the tent is a large elevated platform. Displayed in the centre is a large wheel with various numbers on it. ",end="")
            if self.characterDead:
                print("The rest of the place is empty.")
            else:
                if self.firstTime:
                    firsttime = "Get your tickets here. Only %s gold each. Winner gets 100,000 gold and their own personal yacht. We'll be drawing today's winner in just a couple of minutes! Last chance to get your tickets!" % PRICE_BUY_TICKET
                else:
                    firsttime = "Last chance to get your tickets. Today's winner will be announced soon!"
                print("A large crowd of people are all gathered around it, waving bags of gold in their hands. A man with a top hat, is collecting gold from the people nearest to him as he hands out raffle tickets in return. \"%s\"" % firsttime)
                print("\nGoods available to buy:\n    Raffle ticket (%i gold)" % PRICE_BUY_TICKET)
            if self.isBuy:
                print("\nYou have",inv.gold,"gold.")
            print("\n    To the East is the carnival grounds.")

    #_______Outside____________________________________________________________________
    # Road
    class roomRoadSouth(room):
        def description(self):
            print("\nRoad\n\nThe gravel path extends out into the forest. Pine trees hug the path closely on both ends.")
            print("\n    To the North is a crossroads.")
            print("    To the South is the town gate.")
    class roomRoadMid(room):
        def description(self):
            print("\nCrossroads\n\nFour paths meet here. There is a sign you can READ here.")
            print("\n    To the North is a road.")
            print("    To the East is a road.")
            print("    To the West is a road.")
            print("    To the South is a road.")
    class roomRoadNorth(room):
        def description(self):
            print("\nRoad\n\nThe gravel path extends out into the forest. Pine trees hug the path closely on both ends.")
            print("\n    To the North is Mount Magna.")
            print("    To the South is a crossroads.")
    class roomRoadEast(room):
        def description(self):
            print("\nRoad\n\nThe gravel path extends out into the forest. Pine trees hug the path closely on both ends.")
            print("\n    To the East is is a bridge.")
            print("    To the West is a crossroads.")
    class roomRoadWest(room):
        def description(self):
            print("\nRoad\n\nThe gravel path extends out into the forest. Pine trees hug the path closely on both ends.")
            print("\n    To the East is a crossroads.")
            print("    To the West is a lake.")
    class roomRoadCorner(room):
        def description(self):
            if not self.characterDead and self.counter_1:
                blackknight = " A knight, outfitted in black platemetal armour and equipped with a large broadsword stands in the way. He stands quietly."
            else:
                blackknight = ""
            print("\nRoad\n\nThe gravel path extends out into the forest. Pine trees hug the path closely on both ends.%s" % blackknight)
            print("\n    To the North is a temple.")
            print("    To the West is a bridge.")
    # Lake
    class roomLake(room):
        def description(self):
            if self.firstTime:
                firsttime = "There is an injured stranger, lying by the shoreline. \"Hey, you there! I think my leg is broken. I was fishing here and a wolf attacked me from the woods. If you could give me a bandage, I will give you everything I've got on me. Please, I beg of you!\""
            elif self.characterDead:
                firsttime = "There is a dead body here."
            elif not self.isGive:
                firsttime = "There is an injured stranger, lying by the shoreline. \"I think I'll make it. Might wait a bit before going home.\""
            else:
                firsttime = "There is an injured stranger, lying by the shoreline. \"Are you back with a bandage? I could still use one.\""
            print("\nLake Laguiole\n\nThe sun shimmers off the lake's surface. There is a lockbox here. There is a stone tablet you can READ near the coastline.",firsttime)
            print("\n    To the East is a road.")

    # Bridge
    class roomBridge(room):
        def description(self):
            print("\nBridge\n\nThe wooden bridge stretches across a river.",end = " ")
            if self.firstTime:
                firsttime = "He notices you, and says \"Bridge is home of Ugg. Ugg want funnel cakes. Ugg need gold to buy funnel cakes. Ugg no let you pass unless you give Ugg gold or funnel cakes. Ugg need %s gold to be happy. If you give Ugg funnel cakes, that will be like giving Ugg %s gold because it take a lot of work for Ugg to go buy funnel cakes.\"" % (TROLL_GOAL - invTroll.gold,TROLL_FUNNELCAKE_MULTIPLIER)
            else:
                firsttime = "He reminds you, \"Ugg need %s gold before you can pass. You can give Ugg funnel cakes to save Ugg the effort for %s gold each.\"" % (TROLL_GOAL - invTroll.gold,TROLL_FUNNELCAKE_MULTIPLIER)
            if self.eastBlocked:
                print("There is a troll standing on it, blocking the way. %s" % firsttime)
            elif not self.characterDead:
                print("The troll is happily sitting under the bridge, excited to see how many funnel cakes he can buy.")
            else:
                print("The body of the troll lays on the bridge, leaving some room to step over and across the other side.")
            if self.isGive:
                print("\nYou have",inv.gold,"gold.")
                if inv.funnelCake > 1:
                    print("You have",inv.funnelCake,"funnel cakes.")
                elif inv.funnelCake:
                    print("You have",inv.funnelCake,"funnel cake.")
            print("\n    To the East is a road.")
            print("    To the West is a road.")

    # Temple
    class roomTempleEntrance(room):
        def description(self):
            print("\nTemple Entrance\n\nWhite marble pillars support the temple roof, which is embellished with gold. A large stairway leads up towards the doorway entering the temple. A hedge surrounds the temple from the back and sides.")
            print("\n    To the North is inside.")
            print("    To the South is a road.")
    class roomTempleInside(room):
        def description(self):
            print("\nTemple\n\nA velvet carpet extends from the entrance to a pedestal in the center of the room. At the back is a large statue of the goddess, Garrotxa, overlooking the room. Three murals you can READ with embroided text are laid out, spanning across the West, North, and East walls.\n")
            print("    There is a stairwell that goes down.")
            print("    To the South is outside.")
    class roomTempleBasement(room):
        def description(self):
            if self.staff:
                staffpresent = "A gem-encrusted staff rests on a stand atop it."
            else:
                staffpresent = "An empty stand sits on top of it."
            if not self.characterDead:
                if self.firstTime:
                    firsttime = "A ghostly figure stands in the way. It says, \"The staff of Garrotxa stands before you. Only those who can answer my riddle are worthy to take it.\" It pauses to speak again. \"%s\"" % riddle
                else:
                    firsttime = "A ghostly figure stands in the way. It repeats the riddle, \"%s\"" % riddle
            else:
                firsttime = ""
            print("\nInner Sanctum\n\nTorches line the walls of a stone hallway. At the end sits a pedestal.",staffpresent,firsttime)
            print("    There is a stairwell that goes up.")

    #_______The Cavern______________________________________________________________
    # Entrance
    # debug cave names
    class roomMountEntrance(room):
        def description(self):
            print("\nMount Magna\n\nThe mountain towers above, casting a shadow onto the land below. The path leads into a cave entering the mountain. There is a sign you can READ here.")
            print("\n    To the North is inside.")
            print("    To the South is a road.")
    # 1
    class roomCave_1_m(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the South is outside.")
    # 2
    class roomCave_2_m(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the East is an opening.")
            print("    To the West is an opening.")
            print("    To the South is an opening.")
    class roomCave_2_mr(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the West is an opening.")
    class roomCave_2_lm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the East is an opening.")
            print("    To the West is an opening.")
    class roomCave_2_llm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction. There is a dead body here.")
            print("\n    To the North is an opening.")
            print("    To the East is an opening.")
    # 3
    class roomCave_3_m_coalmine(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            if self.counter_1:
                print("There are coal veins lined along the cavern wall.")
            print("\n    To the North is an opening.")
    class roomCave_3_mr(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the East is an opening.")
            print("    To the South is an opening.")
    class roomCave_3_mrr_coalmine(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            if self.counter_1:
                print("There are coal veins lined along the cavern wall.")
            print("\n    To the West is an opening.")
    class roomCave_3_lm_coalmine(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            if self.counter_1:
                print("There are coal veins lined along the cavern wall.")
            print("\n    To the North is an opening.")
    class roomCave_3_llm_crevasse(room):
        def description(self):
            if self.westBlocked:
                hook = ""
            else:
                hook = " with a grappling hook spanning across it"
            print("\nCavern\n\nCold stone surrounds you in every direction. You can see a treasure chest to the West.")
            print("\n    To the North is an opening.")
            print("    To the West is a crevasse%s." % hook)
            print("    To the South is an opening.")
    class roomCave__3_lllm_treasure_crevasse(room):
        def description(self):
            if self.eastBlocked:
                hook = ""
            else:
                hook = " with a grappling hook spanning across it"
            if self.itemFound:
                chest = "n open"
            else:
                chest = " closed"
            print("\nCavern\n\nCold stone surrounds you in every direction. There is a%s treasure chest here." % chest)
            print("\n    To the East is a crevasse%s." % hook)
    # 4
    class roomCave_4_m(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the East is an opening.")
            print("    To the West is an opening.")
            print("    To the South is an opening.")
    class roomCave_4_mr(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the West is an opening.")
            print("    To the South is an opening.")
    class roomCave_4_lm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the East is an opening.")
            print("    To the West is an opening.")
            print("    To the South is an opening.")
    class roomCave_4_llm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the East is an opening.")
            print("    To the South is an opening.")
    # 5
    class roomCave_5_m(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the East is an opening.")
            print("    To the South is an opening.")
    class roomCave_5_mr_coalmine(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            if self.counter_1:
                print("There are coal veins lined along the cavern wall.")
            print("\n    To the West is an opening.")
    class roomCave_5_lm_coalmine(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            if self.counter_1:
                print("There are coal veins lined along the cavern wall.")
            print("\n    To the West is an opening.")
    class roomCave_5_llm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the East is an opening.")
            print("    To the West is an opening.")
            print("    To the South is an opening.")
    class roomCave_5_lllm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the East is an opening.")
    # 6
    class roomCave_6_m(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction. There is a dead body here.")
            print("\n    To the South is an opening.")
            print("    To the East is an opening.")
    class roomCave_6_mr(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the West is an opening.")
    class roomCave_6_lm_coalmine(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            if self.counter_1:
                print("There are coal veins lined along the cavern wall.")
            print("\n    To the North is an opening.")
    class roomCave_6_llm_coalmine(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            if self.counter_1:
                print("There are coal veins lined along the cavern wall.")
            print("\n    To the West is an opening.")
    class roomCave_6_lllm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction. There is a dead body here.")
            print("\n    To the North is an opening.")
            print("    To the East is an opening.")
            print("    To the South is an opening.")
    # 7
    class roomCave_7_m(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the East is an opening.")
            print("    To the West is an opening.")
    class roomCave_7_mr(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the West is an opening.")
            print("    To the South is an opening.")
    class roomCave_7_lm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the East is an opening.")
            print("    To the West is an opening.")
            print("    To the South is an opening.")
    class roomCave_7_llm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the East is an opening.")
            print("    To the West is an opening.")
    class roomCave_7_lllm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the East is an opening.")
            print("    To the South is an opening.")
    # 8
    class roomCave_8_mr_crevasse(room):
        def description(self):
            if self.northBlocked:
                hook = ""
            else:
                hook = " with a grappling hook spanning across it"
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is a crevasse%s." % hook)
            print("    To the South is an opening.")
    class roomCave_8_llm_coalmine(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            if self.counter_1:
                print("There are coal veins lined along the cavern wall.")
            print("\n    To the South is an opening.")
    # 9
    # Creature cannot naturally spawn here.
    # If creature chase, then creature retreats (cannot pass crevasse)
    class roomCave_9_mr_crevasse(room):
        def description(self):
            if self.southBlocked:
                hook = ""
            else:
                hook = " with a grappling hook spanning across it"
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the South is a crevasse%s." % hook)
    # 10
    # Chase rooms after crossing crevasse
    class roomCave__10_mr(room):
        def description(self):
            if self.southBlocked:
                rubble = ", blocked by rubble"
            else:
                rubble = ""
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the East is an opening.")
            print("    To the West is an opening.")
            print("    To the South is an opening%s." % rubble)
    class roomCave__10_mrr(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the East is an opening.")
            print("    To the West is an opening.")
    class roomCave__10_m(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the East is an opening.")
            print("    To the West is an opening.")
    class roomCave__10_mrrr(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the West is an opening.")
    class roomCave__10_lm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the East is an opening.")
    # 11
    class roomCave__11_lm(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the South is an opening.")
    class roomCave__11_mrrr(room):
        def description(self):
            print("\nCavern\n\nCold stone surrounds you in every direction.")
            print("\n    To the North is an opening.")
            print("    To the South is an opening.")
    # Darkness
    class roomDarkness(room):
        def description(self):
            print("\nDarkness\n\nYou feel nothing.")
    # Lair
    # Have lots of stuff to examine
    #   Shrine to Vesh'kathal
    #   markings on wall?
    class roomLairMid(room): # body here with lantern oil, biscuits
        def description(self):
            print("\nLair\n\nAn obsidian fountain sits in the centre, spewing a mysterious purple fluid. Four gargoyle statues from the corners of the room look inward towards the fountain, perched upon stone pedestals. Your backpack and the creature are visible within the rubble. There is a dead body here.") # contains biscuits to prevent starve (hunger set low after waking up)
            print("\n    To the East is a doorway.") # leads to exit
            print("    To the West is a doorway.") # leads to drop puzzle and supplies in more bodies (include bandage for escape hit, pick for mining rock, more food for after escape)
            print("To the South is an opening, blocked by rubble.")
    class roomLairEast(room):
        def description(self):
            if self.northBlocked:
                rubble = ", blocked by rubble. Light from the outside peers through the cracks"
            else:
                rubble = ""
            if self.firstTime:
                eyes = "closed"
            else:
                eyes = "open"
            print("\nLair\n\nStrange text is etched across the walls. A single gargoyle statue is perched on a pedestal. Its eyes are %s." % eyes)
            print("\n    To the North is an opening%s." % rubble)
            print("    To the West is a doorway.")
    class roomLairWest(room): # body here with bandages, lantern
        def description(self):
            print("\nLair\n\nA skeleton lies on an elongated table, stained with old blood.")
            print("\n    To the East is a doorway.")
            print("    To the South is a small opening.")
    class roomLairHole(room): # get pickaxe here, only if carrying 1 thing in travel both ways
        def description(self):
            print("\nLair\n\nCold stone surrounds you in every direction. The space is extremely cramped. There is a dead body here.\n")
            print("    To the North is a small opening.")
    # Fermiere
    class roomRoad2South(room):
        def description(self):
            print("\nRoad\n\nThe dirt path extends out on the field. The shadow of Mount Magna towers from the south.\n")
            print("    To the North is a crossroads.")
            print("    To the South is inside.")
    class roomRoad2Mid(room):
        def description(self):
            print("\nCrossroads\n\nThe paths meet here. There is a sign you can READ here.\n")
            print("    To the North is a road.")
            print("    To the East is a field.")
            print("    To the West is a road.")
            print("    To the South is a road.")
    class roomFarm(room): # Get potato
        def description(self):
            print("\nFarm\n\nThe entire field is barren and the soil is mixed with ash and dead crops.\n")
            print("    To the East is a crossroads.")
            print("    To the West is a barn.")
    class roomBarn(room):
        def description(self):
            print("\nBarn\n\nThe entire interior is empty as as the walls are all burnt. The roof is entirely removed, and part of the second floor, allowing the sun to light up the area. There is some writing you can READ on the wall.\n")
            print("    There is a ladder that goes up.")
            print("    To the East is a farm.")
    class roomBarnUp(room):
        def description(self):
            print("\nBarn\n\nSeveral stacks of hay are lined across the wall. A table and chair stand at the far end. There is a dead body here.\n")
            print("    There is a ladder that goes down.")
    class roomRoad2North(room):
        def description(self):
            if self.northBlocked:
                lock = " locked"
            else:
                lock = "n unlocked"
            print("\nGate\n\nThe path leads to a%s gate with a large stone wall surrounding the house inside. There is a metal plaque just above the gate.\n" % lock)
            print("    To the North is the entrance.")
            print("    To the South is the crossroads.")
    class roomRoad2East(room):
        def description(self):
            if self.eastBlocked:
                firsttime = "The surface has no distinguishing features."
            else:
                if self.firstTime:
                    firsttime = "You hear strange whispers in every direction around you. Suddenly, the hemisphere begins to hum. An opening appears, leading inside."
                else:
                    firsttime = "An opening leads inside."
            print("\nRoad\n\nIn the middle of the field is large obsidian hemisphere. %s\n" % firsttime)
            if not self.eastBlocked:
                print("    To the East is inside.")
            print("    To the West is the crossroads.")
    class roomShrine(room):
        def description(self):
            if self.firstTime:
                firsttime = " In the centre a pedestal emerges from the water, as a voice echoes within the chamber, \"Omoz... gloth... izh...\""
            else:
                if self.book:
                    bookpresent = " with a mysterious book"
                else:
                    bookpresent = ""
                firsttime = " A pedestal is here%s." % bookpresent
            print("\nShrine\n\nSteps lead down to a circular pit, shallowly filled with water. Along the walls, floating candles dimly light the area." + firsttime + "\n")
            print("    To the West is outside.")
    # House
    class roomHouseEntrance(room):
        def description(self):
            print("\nEntrance\n\nStairs lead up to a tall door bordered by cobblestones.\n")
            print("    To the North is inside.")
            print("    To the South is the gate.")
    class roomHouseFoyer(room):
        def description(self):
            if self.northBlocked:
                lock = " locked"
            else:
                lock = "n unlocked"
            print("\nFoyer\n\nVelet carpet covers the floor as a large, glass chandilier crowned with lights hangs above. Several busts and paintings line the walls. There is a%s rectangular metal door leading north.\n" % lock)
            print("    To the North is the hallway.")
            print("    To the East is the pantry.")
            print("    To the West is the kitchen.")
    class roomHouseKitchen(room):
        def description(self):
            print("\nKitchen\n\nSeveral complicated gadgets sit on the countertops. There is a lever here that can be set in a forward or backwards position. It is currently set to the %s position.\n" % self.counter_1)
            print("    To the East is the foyer.")
    class roomHousePantry(room):
        def description(self):
            print("\nPantry\n\nThere are several cabinets containing various cheeses. On the wall is a dial with 3 settings: blue, red, and green. It is currently set to %s.\n" % self.counter_1)
            print("    To the West is the foyer.")
    class roomHouseHallway(room): # East and West blocked
        def description(self):
            print("\nHallway\n\nThe velvet carpet extends out in the hallway.\n")
            print("    To the North is the office.")
            print("    To the east is the library.")
            print("    To the West is the bedroom.")
            print("    To the South is the foyer.")
    class roomHouseOffice(room): # End game
        def description(self):
            if self.firstTime:
                firsttime = "The door locks behind you. \"The assassin has overcome my final defense, and now he's come to murder me. Tell me, what would a lowly rat from the prisons of Airedale want in killing me?\" An old bearded man wearing tattered rags, sits at the desk at the far end of the room, facing the North wall. He gets up from his chair and turns around to see you. \"So you are the one sent to kill me? Not what I expected. Then again, appearances don't mean much in times like these. It took me a while to figure out what was going on. You are an agent of the Ozkavosh, here to kill me like they've done the rest of this land. A vesh'raheen would not do, so they would need someone with a history of breaking and entering to reach me. What better choice than a criminal from the Airedale prisons? It is not too late to stop what you are doing.\" Eden Von Roquefort walks towards you with open hands. \"Give me the staff, and together we can rid the world of demon-kind.\""
            elif self.characterDead:
                firsttime = "There is a desk at the far back of the room. The body of Eden Von Roquefort lays on the floor."
            else:
                firsttime = "There is a desk at the far back of the room. Eden Von Roquefort is here."
            print("\nOffice\n\n" + firsttime + "\n")
            print("    To the South is the hallway.")
    # Mysterious book
    class roomBookAnimal(room):
        def description (self):
            if self.book:
                bookpresent = " with a mysterious book on it"
            else:
                bookpresent = ""
            print("\nBlack room\n\nThere are four totems in each direction of the room. Behind each totem is a statue of an animal. In the centre is a pedestal%.\n" % bookpresent)
            if self.counter_1 == 1:
                north = "Fox"
            elif self.counter_1 == 2:
                north = "Owl"
            elif self.counter_1 == 3:
                north = "Lion"
            elif self.counter_1 == 4:
                north = "Moose"
            if self.counter_2 == 1:
                east = "Fox"
            elif self.counter_2 == 2:
                east = "Owl"
            elif self.counter_2 == 3:
                east = "Lion"
            elif self.counter_2 == 4:
                east = "Moose"
            if self.counter_3 == 1:
                south = "Fox"
            elif self.counter_3 == 2:
                south = "Owl"
            elif self.counter_3 == 3:
                south = "Lion"
            elif self.counter_3 == 4:
                south = "Moose"
            if self.counter_4 == 1:
                west = "Fox"
            elif self.counter_4 == 2:
                west = "Owl"
            elif self.counter_4 == 3:
                west = "Lion"
            elif self.counter_4 == 4:
                west = "Moose"
            print("    North totem animal:",north)
            print("    East totem animal:",east)
            print("    West totem animal:",west)
            print("    South totem animal:",south)
    class roomBookMirror(room):
        def description (self):
            if self.book:
                bookpresent = " with a mysterious book on it"
            else:
                bookpresent = ""
            if self.counter_1:
                light = " A ball of light floats in the room."
            else:
                light = ""
            if self.itemFound:
                itemfound = " There is a pedestal here%s." % bookpresent
            else:
                itemfound = ""
            if self.characterDead:
                gargoyle = "pile of stone dust is spread across the floor"
            else:
                gargoyle = "gargoyle statue sits in the centre"
            print("\nBlack room\n\nA %s. On the wall behind it is some text.%s%s" % (gargoyle,itemfound,light))
    class roomBook_3_1(room):
        def description (self):
            print("\nRed room\n\nThere is a statue of a tortoise here.\n")
            print("    To the North is a doorway.")
            print("    To the East is a doorway.")
            print("    To the West is a doorway.")
            print("    To the South is a doorway.")
    class roomBook_3_2(room):
        def description (self):
            print("\nGreen room\n\nThere is a statue of a vulture here.\n")
            print("    To the North is a doorway.")
            print("    To the East is a doorway.")
            print("    To the West is a doorway.")
            print("    To the South is a doorway.")
    class roomBook_3_3(room):
        def description (self):
            print("\nPurple room\n\nThere is a statue of a goat here.\n")
            print("    To the North is a doorway.")
            print("    To the East is a doorway.")
            print("    To the West is a doorway.")
            print("    To the South is a doorway.")
    class roomBook_3_4(room):
        def description (self):
            print("\nOrange room\n\nThere is a statue of a cow here.\n")
            print("    To the North is a doorway.")
            print("    To the East is a doorway.")
            print("    To the West is a doorway.")
            print("    To the South is a doorway.")
    class roomBook_3_5(room):
        def description (self):
            print("\nYellow room\n\nThere is a statue of a wolf here.\n")
            print("    To the North is a doorway.")
            print("    To the East is a doorway.")
            print("    To the West is a doorway.")
            print("    To the South is a doorway.")
    class roomBook_3_6(room):
        def description (self):
            print("\nBlue room\n\nThere is a statue of a boar here.\n")
            print("    To the North is a doorway.")
            print("    To the East is a doorway.")
            print("    To the West is a doorway.")
            print("    To the South is a doorway.")
    class roomBook_3_7(room):
        def description (self):
            print("\nWhite room\n\nThere is a statue of a bear here.\n")
            print("    To the North is a doorway.")
            print("    To the East is a doorway.")
            print("    To the West is a doorway.")
            print("    To the South is a doorway.")
    class roomBook_3_8(room):
        def description(self):
            print("\nGrey room\n\nThere is a statue of a snake here.\n")
            print("    To the North is a doorway.")
            print("    To the East is a doorway.")
            print("    To the West is a doorway.")
            print("    To the South is a doorway.")
    class roomBook_3_End(room):
        def description (self):
            if self.book:
                bookpresent = " with a mysterious book on it"
            else:
                bookpresent = ""
            print("\nBlack room\n\nThere is a pedestal here%s.\n" % bookpresent)
            print("    To the East is a doorway.")
            print("    To the West is a doorway.")


    #_______Initialization
    #____________ Room Init
    # Jail
    roomJailCell = roomJailCell(name = "jail cell", porridge = 1, east = "roomJailCorridor", eastBlocked = True, eastBlockedReason = "The cell door is locked.")
    roomJailCorridor = roomJailCorridor(name = "corridor", north = "roomJailAntechamber", northBlocked = True, northBlockedReason = "The vault door is locked.", west = "roomJailCell", westBlocked = False, westBlockedReason = "The cell door is locked.", counterAns_1 = random.randint(0,9), counterAns_2 = random.randint(0,9), counterAns_3 = random.randint(0,9), counterAns_4 = random.randint(0,9), counter_1 = random.randint(0,9), counter_2 = random.randint(0,9), counter_3 = random.randint(0,9), counter_4 = random.randint(0,9))
    roomJailAntechamber = roomJailAntechamber(name = "antechamber", south = "roomJailCorridor", down = "roomJailFoyer")
    roomJailFoyer = roomJailFoyer(name = "foyer", east = "roomJailHallway", west = "roomJailEntrance", up = "roomJailAntechamber", gold = 2)
    roomJailHallway = roomJailHallway(name = "hallway", east = "roomJailBreakRoom", west = "roomJailFoyer")
    roomJailBreakRoom = roomJailBreakRoom()
    roomJailEntrance = roomJailEntrance(name = "jail entrance", east = "roomJailFoyer", west = "roomCourtyardSouth", shrubbery = 1)
    # Town
    roomCourtyardNorth = roomCourtyardNorth(name = "north courtyard", north = "roomGate", east = "roomGeneralStore", south = "roomCourtyardSouth", west = "roomCarnival", characterDead = False)
    roomCourtyardSouth = roomCourtyardSouth(name = "south courtyard", gold = LOOT_S_GOLD, north = "roomCourtyardNorth", east = "roomJailEntrance", west = "roomBlacksmith", south = "roomAlchemist", characterDead = False)
    roomBlacksmith = roomBlacksmith(name = "blacksmith", east = "roomCourtyardSouth", characterDead = False, isBuy = True, isSell = True)
    roomAlchemist = roomAlchemist(name = "alchemist's hut", north = "roomCourtyardSouth", characterDead = False, isSell = True)
    # Carnival
    roomCarnival = roomCarnival(name = "carnival", halfFunnelCake = 1, north = "roomCarnivalFood", east = "roomCourtyardNorth", south = "roomCarnivalShellGame", southBlockedReason = "The tent is closed.", west = "roomCarnivalWheelGame", westBlockedReason = "The tent is closed.", characterDead = False)
    roomCarnivalFood = roomCarnivalFood(name = "funnel cakes galore", south = "roomCarnival", characterDead = False, isBuy = True)
    roomCarnivalShellGame = roomCarnivalShellGame(name = "sybil\'s shell game", north = "roomCarnival", characterDead = False, isBet = True)
    roomCarnivalWheelGame = roomCarnivalWheelGame(east = "roomCarnival", characterDead = False, isBuy = True)
    roomGate = roomGate(name = "town gate", gold = LOOT_GATE_GOLD, north = "roomRoadSouth", northBlocked = True, northBlockedReason = "The guard prevent you from leaving the town.", south = "roomCourtyardNorth", characterDead = False, isGive = True)
    roomGeneralStore = roomGeneralStore(name = "general store", west = "roomCourtyardNorth", characterDead = False, isBuy = True)
    # Outside
    # Road
    roomRoadSouth = roomRoadSouth(name = "road", north = "roomRoadMid", eastBlocked = True, eastBlockedReason = "The foliage is too thick to traverse.", westBlocked = True, westBlockedReason = "The foliage is too thick to traverse.", south = "roomGate")
    roomRoadMid = roomRoadMid(name = "crossroads", north = "roomRoadNorth", east = "roomRoadEast", south = "roomRoadSouth", west = "roomRoadWest")
    roomRoadNorth = roomRoadNorth(name = "road", north = "roomMountEntrance", eastBlocked = True, eastBlockedReason = "The foliage is too thick to traverse.", south = "roomRoadMid", westBlocked = True, westBlockedReason = "The foliage is too thick to traverse.")
    roomRoadEast = roomRoadEast(name = "road", northBlocked = True, northBlockedReason = "The foliage is too thick to traverse.", east = "roomBridge", west = "roomRoadMid", southBlocked = True, southBlockedReason = "The foliage is too thick to traverse.")
    roomRoadWest = roomRoadWest(name = "road", northBlocked = True, northBlockedReason = "The foliage is too thick to traverse.", east = "roomRoadMid", west = "roomLake", southBlocked = True, southBlockedReason = "The foliage is too thick to traverse.")
    roomRoadCorner = roomRoadCorner(name = "road", north = "roomTempleEntrance", eastBlocked = True, eastBlockedReason = "The foliage is too thick to traverse.", west = "roomBridge", westBlockedReason = "The black knight stands in the way. He declares, \"None shall pass.\"", southBlocked = True, southBlockedReason = "The foliage is too thick to traverse.")
    # Lake
    roomLake = roomLake(name = "lake laguiole", northBlocked = True, northBlockedReason = "The foliage is too thick to traverse.", east = "roomRoadWest", southBlocked = True, southBlockedReason = "The foliage is too thick to traverse.", westBlocked = True, westBlockedReason = "The foliage is too thick to traverse.", characterDead = False, isGive = True)
    # Bridge
    roomBridge = roomBridge(name = "bridge", east = "roomRoadCorner", eastBlocked = True, eastBlockedReason = "The troll is stopping you from crossing.", west = "roomRoadEast", characterDead = False, isGive = True)
    # Temple
    roomTempleEntrance = roomTempleEntrance(name = "temple entrance", north = "roomTempleInside", south = "roomRoadCorner")
    roomTempleInside = roomTempleInside(name = "temple", south = "roomTempleEntrance", down = "roomTempleBasement")
    roomTempleBasement = roomTempleBasement(name = "inner sanctum", up = "roomTempleInside", characterDead = False, staff = 1)
    # Cave
    roomMountEntrance = roomMountEntrance(name = "mount magna", north = "roomCave_1_m", south = "roomRoadNorth")
    # 1
    roomCave_1_m = roomCave_1_m(name = "cavern", north = "roomCave_2_m", south = "roomMountEntrance")
    # 2
    roomCave_2_m = roomCave_2_m(name = "cavern", east = "roomCave_2_mr", south = "roomCave_1_m", west = "roomCave_2_lm")
    roomCave_2_mr = roomCave_2_mr(name = "cavern", north = "roomCave_3_mr", west = "roomCave_2_m")
    roomCave_2_lm = roomCave_2_lm(name = "cavern", east = "roomCave_2_m", west = "roomCave_2_llm")
    roomCave_2_llm = roomCave_2_llm(name = "cavern", north = "roomCave_3_llm_crevasse", east = "roomCave_2_lm")
    # 3
    roomCave_3_m_coalmine = roomCave_3_m_coalmine(name = "cavern", north = "roomCave_4_m", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
    roomCave_3_mr = roomCave_3_mr(name = "cavern", north = "roomCave_4_mr", east = "roomCave_3_mrr_coalmine", south = "roomCave_2_mr", counter_1 = random.randint(COAL_MIN,COAL_MAX))
    roomCave_3_mrr_coalmine = roomCave_3_mrr_coalmine(name = "cavern", west = "roomCave_3_mr", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
    roomCave_3_lm_coalmine = roomCave_3_lm_coalmine(name = "cavern", north = "roomCave_4_lm", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
    roomCave_3_llm_crevasse = roomCave_3_llm_crevasse(name = "cavern", north = "roomCave_4_llm", south = "roomCave_2_llm", west = "roomCave__3_lllm_treasure_crevasse", westBlocked = True, westBlockedReason = "The crevasse is too large to walk across.", isCrevasse = True)
    roomCave__3_lllm_treasure_crevasse = roomCave__3_lllm_treasure_crevasse(name = "cavern", east = "roomCave_3_llm_crevasse", eastBlocked = True, eastBlockedReason = "The crevasse is too large to walk across.", isCrevasse = True)
    # 4
    roomCave_4_m = roomCave_4_m(name = "cavern", north = "roomCave_5_m", east = "roomCave_4_mr", south = "roomCave_3_m_coalmine", west = "roomCave_4_lm")
    roomCave_4_mr = roomCave_4_mr(name = "cavern", south = "roomCave_3_mr", west = "roomCave_4_m")
    roomCave_4_lm = roomCave_4_lm(name = "cavern", east = "roomCave_4_m", south = "roomCave_3_lm_coalmine", west = "roomCave_4_llm")
    roomCave_4_llm = roomCave_4_llm(name = "cavern", north = "roomCave_5_llm", east = "roomCave_4_lm", south = "roomCave_3_llm_crevasse")
    # 5
    roomCave_5_m = roomCave_5_m(name = "cavern", north = "roomCave_6_m", east = "roomCave_5_mr_coalmine", south = "roomCave_4_m")
    roomCave_5_mr_coalmine = roomCave_5_mr_coalmine(name = "cavern", west = "roomCave_5_m", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
    roomCave_5_lm_coalmine = roomCave_5_lm_coalmine(name = "cavern", west = "roomCave_5_llm", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
    roomCave_5_llm = roomCave_5_llm(name = "cavern", east = "roomCave_5_lm_coalmine", south = "roomCave_4_llm", west = "roomCave_5_lllm")
    roomCave_5_lllm = roomCave_5_lllm(name = "cavern", north = "roomCave_6_lllm", east = "roomCave_5_llm")
    # 6
    roomCave_6_m = roomCave_6_m(name = "cavern", east = "roomCave_6_mr", south = "roomCave_5_m")
    roomCave_6_mr = roomCave_6_mr(name = "cavern", north = "roomCave_7_mr", west = "roomCave_6_m")
    roomCave_6_lm_coalmine = roomCave_6_lm_coalmine(name = "cavern", north = "roomCave_7_lm", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
    roomCave_6_llm_coalmine = roomCave_6_llm_coalmine(name = "cavern", west = "roomCave_6_lllm", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
    roomCave_6_lllm = roomCave_6_lllm(name = "cavern", north = "roomCave_7_lllm", east = "roomCave_6_llm_coalmine", south = "roomCave_5_lllm")
    # 7
    roomCave_7_m = roomCave_7_m(name = "cavern", east = "roomCave_7_mr", west = "roomCave_7_lm")
    roomCave_7_mr = roomCave_7_mr(name = "cavern", north = "roomCave_8_mr_crevasse", south = "roomCave_6_mr", west = "roomCave_7_m")
    roomCave_7_lm = roomCave_7_lm(name = "cavern", east = "roomCave_7_m", south = "roomCave_6_lm_coalmine", west = "roomCave_7_llm")
    roomCave_7_llm = roomCave_7_llm(name = "cavern", north = "roomCave_8_llm_coalmine", east = "roomCave_7_lm", west = "roomCave_7_lllm")
    roomCave_7_lllm = roomCave_7_lllm(name = "cavern", east = "roomCave_7_llm", south = "roomCave_6_lllm")
    # 8
    roomCave_8_mr_crevasse = roomCave_8_mr_crevasse(name = "cavern", north = "roomCave_9_mr_crevasse", northBlocked = True, northBlockedReason = "The crevasse is too large to walk across.", south = "roomCave_7_mr", isCrevasse = True)
    roomCave_8_llm_coalmine = roomCave_8_llm_coalmine(name = "cavern", south = "roomCave_7_llm", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
    # 9
    roomCave_9_mr_crevasse = roomCave_9_mr_crevasse(name = "cavern", north = "roomCave__10_mr", south = "roomCave_8_mr_crevasse", southBlocked = True, southBlockedReason = "The crevasse is too large to walk across.",  isCrevasse = True)
    # 10
    roomCave__10_m = roomCave__10_m(name = "cavern", east = "roomCave__10_mr", west = "roomCave__10_lm")
    roomCave__10_mr = roomCave__10_mr(name = "cavern", east = "roomCave__10_mrr", south = "roomCave_9_mr_crevasse", southBlocked = True, southBlockedReason = "Rubble is blocking the way.", west = "roomCave__10_m", counter_1 = RUBBLE_DURABILITY, isMine = True)
    roomCave__10_mrr = roomCave__10_mrr(name = "cavern", east = "roomCave__10_mrrr", west = "roomCave__10_mr")
    roomCave__10_mrrr = roomCave__10_mrrr(name = "cavern", north = "roomCave__11_mrrr", west = "roomCave__10_mrr")
    roomCave__10_lm = roomCave__10_lm(name = "cavern", north = "roomCave__11_lm", east = "roomCave__10_m")
    # 11
    roomCave__11_mrrr = roomCave__11_mrrr(name = "cavern", north = "roomDarkness", south = "roomCave__10_mrrr")
    roomCave__11_lm = roomCave__11_lm(name = "cavern", north = "roomDarkness", south = "roomCave__10_lm")
    # Darkness
    roomDarkness = roomDarkness(name = "darkness")
    # Lair
    roomLairMid = roomLairMid(name = "lair", east = "roomLairEast", west = "roomLairWest", southBlocked = True, southBlockedReason = "Rubble is blocking the way.", counter_1 = 5, isMine = True)
    roomLairEast = roomLairEast(name = "lair", north = "roomRoad2South", northBlocked = True, northBlockedReason = "Rubble is blocking the way.", west = "roomLairMid", counter_1 = RUBBLE_DURABILITY, isMine = True)
    roomLairWest = roomLairWest(name = "lair", east = "roomLairMid", south = "roomLairHole", southBlockedReason = "You are carrying too much to fit through the opening.", gold = LOOT_WEST_GOLD, biscuit = LOOT_WEST_BISCUIT)
    roomLairHole = roomLairHole(name = "lair", north = "roomLairWest", northBlockedReason = "You are carrying too much to fit through the opening.", lantern = 1)
    # Field
    roomRoad2South = roomRoad2South(name = "road", north = "roomRoad2Mid", south = "roomLairEast")
    roomRoad2Mid = roomRoad2Mid(name = "crossroad", north = "roomRoad2North", east = "roomRoad2East", south = "roomRoad2South", west = "roomFarm")
    roomRoad2North = roomRoad2North(name = "road", south = "roomRoad2Mid", north = "roomHouseEntrance", northBlocked = True, northBlockedReason = "The gate is locked.")
    roomFarm = roomFarm(name = "farm", east = "roomRoad2Mid", west = "roomBarn", potato = 1)
    roomBarn = roomBarn(name = "barn", east = "roomFarm", up = "roomBarnUp")
    roomBarnUp = roomBarnUp(name = "barn", down = "roomBarn")
    roomRoad2East = roomRoad2East(name = "road", east = "roomShrine", eastBlocked = True, eastBlockedReason = "You cannot go East.", west = "roomRoad2Mid")
    roomShrine = roomShrine(name = "shrine", west = "roomRoad2East", book = 1)
    # House
    roomHouseEntrance = roomHouseEntrance(name = "entrance", north = "roomHouseFoyer", northBlocked = True, northBlockedReason = "The door is locked.", south = "roomRoad2North")
    roomHouseFoyer = roomHouseFoyer(name = "foyer", north = "roomHouseHallway", northBlocked = True, northBlockedReason = "The door is locked.", east = "roomHousePantry", south = "roomHouseEntrance", west = "roomHouseKitchen")
    roomHouseKitchen = roomHouseKitchen(name = "kitchen", east = "roomHouseFoyer", pie = 1, counter_1 = LEVER_START)
    roomHousePantry = roomHousePantry(name = "pantry", west = "roomHouseFoyer", brie = 1, munster = 1, stilton = 1, swiss = 1, wensleydale = 1, counter_1 = DIAL_START)
    roomHouseHallway = roomHouseHallway(name = "hallway", north = "roomHouseOffice", eastBlocked = True, eastBlockedReason = "The door is locked.", south = "roomHouseFoyer", westBlocked = True, westBlockedReason = "The door is locked.")
    roomHouseOffice = roomHouseOffice(name = "office", south = "roomHouseHallway", southBlocked = True, southBlockedReason = "The door is locked.", characterDead = False, isGive = True)
    # Mysterious book
    # Puzzle 1 - Animal Totems
    roomBookAnimal = roomBookAnimal(name = "black room")
    # Puzzle 2 - Mirror player
    roomBookMirror = roomBookMirror(name = "black room", characterDead = False)
    # Puzzle 3 - Movement
    roomBook_3_1 = roomBook_3_1(name = "red room", north = "roomBook_3_5", east = "roomBook_3_3", south = "roomBook_3_4", west = "roomBook_3_2")
    roomBook_3_2 = roomBook_3_2(name = "green room", north = "roomBook_3_3", east = "roomBook_3_1", south = "roomBook_3_5", west = "roomBook_3_3")
    roomBook_3_3 = roomBook_3_3(name = "purple room", north = "roomBook_3_8", east = "roomBook_3_2", south = "roomBook_3_4", west = "roomBook_3_1")
    roomBook_3_4 = roomBook_3_4(name = "orange room", north = "roomBook_3_1", east = "roomBook_3_5", south = "roomBook_3_7", west = "roomBook_3_6")
    roomBook_3_5 = roomBook_3_5(name = "yellow room", north = "roomBook_3_2",  east = "roomBook_3_6", south = "roomBook_3_1", west = "roomBook_3_4")
    roomBook_3_6 = roomBook_3_6(name = "blue room", north = "roomBook_3_7", east = "roomBook_3_4", south = "roomBook_3_8", west = "roomBook_3_5")
    roomBook_3_7 = roomBook_3_7(name = "white room", north = "roomBook_3_4", east = "roomBook_3_8", south = "roomBook_3_6", west = "roomBook_3_End")
    roomBook_3_8 = roomBook_3_8(name = "grey room", north = "roomBook_3_6", east = "roomBook_3_End", south = "roomBook_3_3", west = "roomBook_3_7")
    roomBook_3_End = roomBook_3_End(name = "black room", east = "roomBook_3_7", west = "roomBook_3_8", keySkeleton = 1, book = 1)

    #_______Stat Init
    stat = Stat()

    #_______Inventory init
    inv = Inventory()
    # roomBridge troll
    invTroll = Inventory()
    # roomGate guard
    invGate = Inventory()
    # roomCarnivalShellGame
    invShell = Inventory(gold = SHELL_GOLD)
    # roomCarnivalFood
    invFood = Inventory(funnelCake = FUNNELCAKE_LIMIT)


#_______Variables Init
    # Random
    riddle = random.randint(1,4)
    if riddle == 1:
        riddle = "I don't have eyes, but once I did see. I don't have thoughts, but now I'm empty. What am I?"
        riddle_answer = ["skull","a skull"]
    elif riddle == 2:
        riddle = "The poor have me. The rich need me. If you eat me, you will die. What am I?"
        riddle_answer = ["nothing","no thing","nada"]
    elif riddle == 3:
        riddle = "You will always find me in the past. I can be created in the present, But the future can never taint me. What am I?"
        riddle_answer = ["history","the history","recorded history"]
    elif riddle == 4:
        riddle = "The more there is of me, the less you see. What am I?"
        riddle_answer = ["darkness","dark","the dark","the darkness","blackness","fog","the fog"]
    endGame = False
    changeRoom = False
    # Starting room
    # Default: roomJailCell
    if debug:
        roomID = "roomLake"
        roomCurrent = roomLake
    else:
        roomID = "roomJailCell"
        roomCurrent = roomJailCell
    direction = False
    justEntered = False
    askName = False
    jailGuards = False
    jailGuardCounter = 3
    creatureRoam = False
    creatureChase = 0
    creatureRoamCounter = random.randint(CREATURE_ROAM_MIN,CREATURE_ROAM_MAX)
    creatureChaseCounter = random.randint(CREATURE_CHASE_MIN,CREATURE_CHASE_MAX)
    creatureLairChase = False
    creatureLairChaseCounter = LAIR_CHASE_DURATION
    silenced = False
    # End Game
    win = False
    # Does not count as turn
    notTurn = True
    # Counters
    turnCounter = 0
    guardCounter = 0
    oilCounter = 0
    lightCounter = 0
#_______Start of Game text____________________________________________________________
# Current room information
    roomCurrent.description()
    roomCurrent.itemsPresent()
# Hunger
    stat.hunger -= 1
    if stat.hunger <= 0:
        endGame = True
    elif stat.hunger <= 15:
        print()
        stat.examineHunger()
# Health
    if stat.health in range(1,stat.healthmax):
        stat.health -= 1
        print()
        stat.examineHealth()
    if stat.health <= 0:
        endGame = True
# Oil warning
    if inv.lantern > 0 or roomCurrent.lantern > 0:
        if oilCounter in range(3,5):
            print("\nYour lantern flickers.")
        elif oilCounter == 2:
            print("\nYour lantern has almost died out.")
        elif oilCounter == 1:
            print("\nYour lantern has ran out of oil.")

#_______End Game_________________________________________________________________
# Loops until the game ends
# Game loop start
    while not endGame:
#_______User input______________________________________________________________
        option = input("\n> ").lower()
        if option not in ("again","do it again","do again","g"):
            option_last = option
#_______Start of turn___________________________________________________________
        # Set last room
        roomID_Last = roomID
        roomCurrent_Last = roomCurrent
        notTurn = False
        message = False
#_______Free Space Decisions____________________________________________________
# Can be made in free space

        # Repeat last option
        if option in ("again","do it again","do again","g"):
            option = option_last
        #Quit game
        if option in ("quit","quit game","end game","die","kill self","suicide", "commit suicide","end my life","end my suffering","throw in the towel","give up","give up on life"):
            endGame = True
        # debug commands
        elif option == "!debug" and debug:
            print("!stat - Prints player stats")
            print("!turn - Prints number of turns")
            print("!unblock - Unblocks all directions")
            print("!block - Blocks all directions")
            print("!characterdead - makes roomCurrent.characterDead = True")
            print("!silence - Toggles silence")
            print("!inv - Gives debug inventory")
            print("!spells - Prints status of all Ozkavosh spells and words")
            print("!learn - Learns all Ozkavosh spells and words")
            notTurn = True
        elif option == "!stat" and debug:
            print("Name:",stat.name)
            print("Hunger:",stat.hunger)
            print("Health:",stat.health)
            print("oilCounter:",oilCounter)
            print("inv.itemTypes():",inv.itemTypes())
            print("turnCounter:",turnCounter)
            notTurn = True
        elif option == "!turn" and debug:
            print("The turncounter is %s." % turnCounter)
            notTurn = True
        elif option == "!unblock" and debug:
            if roomCurrent.northBlocked:
                roomCurrent.northBlocked = False
            elif roomCurrent.eastBlocked:
                print("East is now unblocked.")
                roomCurrent.eastBlocked = False
            elif roomCurrent.southBlocked:
                print("South is now unblocked.")
                roomCurrent.southBlocked = False
            elif roomCurrent.westBlocked:
                print("west is now unblocked.")
                roomCurrent.westBlocked = False
            notTurn = True

        elif option == "!block" and debug:
            if roomCurrent.northBlockedReason and roomCurrent.north:
                roomCurrent.northBlocked = True
                print("North is now blocked.")
            if roomCurrent.eastBlockedReason and roomCurrent.east:
                roomCurrent.eastBlocked = True
                print("East is now blocked.")
            if roomCurrent.southBlockedReason and roomCurrent.south:
                roomCurrent.southBlocked = True
                print("South is now blocked.")
            if roomCurrent.westBlockedReason and roomCurrent.west:
                roomCurrent.westBlocked = True
                print("West is now blocked.")
            notTurn = True

        elif option == "!characterdead" and debug:
            roomCurrent.characterDead = True
            print("characterDead is True")
            notTurn = True
        elif option == "!oilcounter" and debug:
            print(oilCounter)
            notTurn = True
        elif option in ("!health","!hp") and debug:
            print("Health:",stat.health)
            notTurn = True
        elif option in ("!hunger","!h") and debug:
            print("Hunger:",stat.hunger)
            notTurn = True
        elif option == "!name" and debug:
            print("Name:",stat.name)
            notTurn = True
        elif option == "!room" and debug:
            print("roomID:",roomID)
            print("roomID_Last:",roomID_Last)
            print("North room:",roomCurrent.north)
            print("	North room blocked:",roomCurrent.northBlocked)
            print("	North room blocked reason:",roomCurrent.northBlockedReason)
            print("East room:",roomCurrent.east)
            print("	East room blocked:",roomCurrent.eastBlocked)
            print("	East room blocked reason:",roomCurrent.eastBlockedReason)
            print("South room:",roomCurrent.south)
            print("	South room blocked:",roomCurrent.southBlocked)
            print("	South room blocked reason:",roomCurrent.southBlockedReason)
            print("West room:",roomCurrent.west)
            print("	West room blocked:",roomCurrent.westBlocked)
            print("	West room blocked reason:",roomCurrent.westBlockedReason)
            print("counter_1:",roomCurrent.counter_1)
            print("counter_2:",roomCurrent.counter_2)
            print("counter_3:",roomCurrent.counter_3)
            print("counter_4:",roomCurrent.counter_4)
            print("isBuy:",roomCurrent.isBuy)
            print("isBet:",roomCurrent.isBet)
            print("isCrevasse:",roomCurrent.isCrevasse)
            print("isGive:",roomCurrent.isGive)
            print("isMine:",roomCurrent.isMine)
            print("isSell:",roomCurrent.isSell)
            print("itemFound:",roomCurrent.itemFound)
            print("characterDead:",roomCurrent.characterDead)
            print("creatureRoam:",creatureRoam)
            print("	creatureRoamCounter:",creatureRoamCounter)
            print("creatureChase:",creatureChase)
            print("	creatureChaseCounter:",creatureChaseCounter)
            notTurn = True
        elif option == "!silence" and debug:
            if silenced:
                silenced = False
                print("Silenced: False")
            else:
                silenced = True
                print("Silenced: True")
            notTurn = True
        elif option == "!inv" and debug:
            inv = DEBUG_INV
            stat.health = HEALTH_MAX
            stat.hunger = HUNGER_DEBUG
            print("Debug inventory given.")
            notTurn = True
        elif option == "!spells" and debug:
            print("spell_learn is:",spell_learn)
            print("spell_unlock is:",spell_unlock)
            print("spell_persuade is:",spell_persuade)
            print("spell_jump is:",spell_jump)
            print("spell_light is:",spell_light)
            print("spell_heal is:",spell_heal)
            print("spell_feed is:",spell_feed)
            print("spell_kill is:",spell_kill)
            print("spell_killself is:",spell_killself)
            print("word_darkness is:",word_darkness)
            print("word_reign is:",word_reign)
            print("word_stop is:",word_stop)
            print("word_servant is:",word_servant)
            print("word_mirror is:",word_mirror)
            print("word_dominion is:",word_dominion)
            print("word_curse is:",word_curse)
            notTurn = True
        elif option == "!learn" and debug:
            print("All Ozkavosh spells and words learned.")
            spell_learn = 2
            spell_unlock = 2
            spell_persuade = 2
            spell_jump = 2
            spell_light = 2
            spell_heal = 2
            spell_feed = 2
            spell_kill = 2
            spell_killself = 2
            word_darkness = 2
            word_reign = 2
            word_stop = 2
            word_servant = 2
            word_mirror = 2
            word_dominion = 2
            word_curse = 2
            word_oblivion = 2
            notTurn = True
        # Examine
        # Inventory
        # Objects in inventory
        # Room
        elif option.startswith("examine") or option.startswith("x") or option in ("inventory","inv","i","l","spells","spellbook","words","demon words","ozkavosh","ozkavosh words","hunger","health","hp") or option.startswith("look"):
            if option.startswith("examine") or option.startswith("look at") or option.startswith("x"):
                if option.startswith("x"):
                    option = option[2:]
                else:
                    option = option[8:]
            # Nothing
            if option == "":
                print("Examine what?")
                option = False
                notTurn = True
            # Examine room
            elif option in ("look","l"):
                roomCurrent.description()
                roomCurrent.itemsPresent()
                option = False
                notTurn = True
            # Examine spells
            elif option in ("spells","spellbook","words","demon words","ozkavosh","ozkavosh words"):
                demonWords()
                option = False
                notTurn = True
            if option:
                # Stats
                if option == "hunger":
                    stat.examineHunger()
                    print("You need to eat something in",stat.hunger,"turns.")
                    notTurn = True
                elif option in ("health","hp"):
                    stat.examineHealth()
                    if stat.health != HEALTH_MAX:
                        print("You need to heal your wounds in",stat.health,"turns.")
                    notTurn = True
                # Spells
                elif option in ("spells","spellbook","words","demon words","ozkavosh","ozkavosh words"):
                    demonWords()
                # Inventory
                elif option in ("inventory","inv","i"):
                    option = "inventory"
                    inv.examineInventory(option)
                    notTurn = True
                elif option == "gold":
                    inv.examineInventory(option)
                elif option in ("letter","the letter"):
                    option = "letter"
                    inv.examineInventory(option)
                elif option in ("key","key of ahm'domosh"):
                    if inv.keySkeleton:
                        option = "keySkeleton"
                    else:
                        option = "key"
                    inv.examineInventory(option)
                elif option in ("pickaxe","pick"):
                    option = "pickaxe"
                    inv.examineInventory(option)
                elif option in ("shrubbery","shrubberies"):
                    option = "shrubbery"
                    inv.examineInventory(option)
                elif option in ("cake","cakes","funnel cake","funnel cakes"):
                    option = "funnelCake"
                    inv.examineInventory(option)
                elif option in ("half-eaten cake","half cake","half-eaten funnel cake","half eaten funnel cake","half funnel cake","half-eaten cakes","half cakes","half-eaten funnel cakes","half eaten funnel cakes","half funnel cakes"):
                    option = "halfFunnelCake"
                    inv.examineInventory(option)
                elif option in ("lucky rabbit foot","rabbit","rabbit foot","foot","lucky rabbit","lucky foot","lucky rabbit feet","rabbit feet","feet","lucky feet"):
                    option = "foot"
                    inv.examineInventory(option)
                elif option in ("porridge","bowl of porridge","bowl","porridge bowl"):
                    if option in ("bowl", "porridge bowl") and inv.bowl or not (inv.bowl or inv.porridge):
                        option = "bowl"
                    else:
                        option = "porridge"
                    inv.examineInventory(option)
                elif option == "lantern":
                    inv.examineInventory(option)
                elif option in ("oil","vial","vials","vial of lantern oil","vials of lantern oil","vial of oil","vials of oil","lantern oil"):
                    option = "oil"
                    inv.examineInventory(option)
                elif option in ("pies","pie","chicken pies","chicken pie","chicken pot pies","chicken pot pies"):
                    option = "pie"
                    inv.examineInventory(option)
                elif option in ("hardtack biscuit","hardtack biscuits","biscuit","biscuits","hardtack"):
                    option = "biscuit"
                    inv.examineInventory(option)
                elif option in ("grappling hook","hook"):
                    option = "hook"
                    inv.examineInventory(option)
                elif option in ("the staff of garrotxa","staff of garrotxa","staff","the staff"):
                    option = "staff"
                    inv.examineInventory(option)
                elif option in ("ticket","raffle ticket"):
                    option = "ticket"
                    inv.examineInventory(option)
                elif option in ("coal","piece of coal","pieces of coal"):
                    option = "coal"
                    inv.examineInventory(option)
                elif option == "potato":
                    inv.examineInventory(option)
                elif option in ("bandages","bandage"):
                    option = "bandage"
                    inv.examineInventory(option)
                elif option in ("the journal","journal"):
                    option = "journal"
                    inv.examineInventory(option)
                elif option in ("book","mysterious book"):
                    option = "book"
                    inv.examineInventory(option)
                elif option in ("brie","slice of brie","slice of brie cheese", "brie cheese"):
                    option = "brie"
                    inv.examineInventory(option)
                elif option in ("munster","slice of munster","slice of munster cheese","munster cheese"):
                    option = "munster"
                    inv.examineInventory(option)
                elif option in ("stilton","slice of stilton","slice of stilton cheese","stilton cheese"):
                    option = "stilton"
                    inv.examineInventory(option)
                elif option in ("swiss","slice of swiss","slice of swiss cheese","swiss cheese"):
                    option = "swiss"
                    inv.examineInventory(option)
                elif option in ("wensleydale","slice of wensleydale","slice of wensleydale cheese","wensleydale cheese"):
                    option = "wensleydale"
                    inv.examineInventory(option)
                elif option in ("potion","potion of rejuvination","potions","potions of rejuvination"):
                    option = "potion"
                    inv.examineInventory(option)
                elif option in ("flask","empty flask","flasks","empty flasks"):
                    option = "flask"
                    inv.examineInventory(option)
                elif option in ("stone","dragonstone"):
                    option = "stone"
                    inv.examineInventory(option)

                # Room
                elif option in ("room","surroundings","place","area",roomCurrent.name):
                    roomCurrent.description()
                    roomCurrent.itemsPresent()
                # Objects in rooms
                # Jail
                elif roomID == "roomJailCell":
                    if option in ("walls","stone walls","wall","stone wall","stone"):
                        print("12 tally marks have been etched in, representing all the years you've been here.")
                    elif option in ("haystack","hay","stack"):
                        print("A fairly comfortable place to sleep.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            roomCurrent.gold += 1
                            print("You search through the hay and find a piece of gold.")
                            roomCurrent.itemsPresent()
                    elif option in ("solid metal door","solid door","metal door","door"):
                        print("It's made of cold steel. There is a small slit in it.")
                    elif option in ("slit","small slit"):
                        print("You can see out into the corridor. Other jail cells are visible, but you can't see inside of them.")
                        if not roomCurrent.counter_1:
                            roomCurrent.counter_1 = 1
                    elif askName:
                        if option in ("woman","her","stranger"):
                            print("She is wearing white robes, covering her face and body.")
                        elif option in ("portal","mystical portal"):
                            print("It's elliptical with a glowing purple border. Through the other side you can see stone walls.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Courtyard North
                elif roomID == "roomCourtyardNorth":
                    if option in ("crowd","people"):
                        print("Some look pretty excited for the carnival.")
                    if option in ("building","buildings"):
                        print("Stone walls. Wooden roofs. They all look pretty similar.")
                    if option in ("urchin boy","boy","young urchin boy","young boy") and not roomCarnivalWheelGame.characterDead:
                        print("He's wearing dirty, ragged clothing.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Courtyard South
                elif roomID == "roomCourtyardSouth":
                    if option in ("large elegant fountain","large fountain","elegant fountain","fountain"):
                        print("It's large... and elegant.")
                    elif option in ("crowd","busy people","people"):
                        print("You feel creepy examining everyone around you, making you quickly stop.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Carnival
                elif roomID == "roomCarnival":
                    if option in ("tents","tent"):
                        print("The tents are striped in a variety of colours.")
                    elif option in ("lights","light","lighting"):
                        print("The lights brighten the place up, as you would expect lights to do.")
                    elif option in ("banners","banner","colourful banners"):
                        print("The banners have cows on them, representing the Goddess Garrotxa.")
                    elif option in ("crowd","adults","children","people"):
                        print("The crowd is noisy and generally excited.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Shell Game
                elif roomID == "roomCarnivalShellGame":
                    if option in ("sybil","her","woman","old woman"):
                        print("Sybil is hunched at the table. She has only one eye, and a lot of missing teeth. Her ragged clothing makes her look homeless.")
                    elif option in ("table","wooden table"):
                        print("It looks like it could collapse at any moment. The table surface has a lot of scratch marks, from shuffling of shells no doubt.")
                    elif option == "shells":
                        print("They are conch shells.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Funnel Cakes Galore
                elif roomID == "roomCarnivalFood":
                    if option in ("older overweight vendor","overweight vendor","vendor","him","man"):
                        print("The vendor looks happy to see you. His apron is grease-stained, and his sleeves are rolled up.")
                    elif option in ("stove","stovetop"):
                        print("It's made of cast iron.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Bridge
                elif roomID == "roomBridge":
                    if option in ("troll","ugg","the troll"):
                        if roomCurrent.characterDead:
                            print("It's completely charred.")
                        elif roomCurrent.eastBlocked:
                            print("He's licking his lips and rubbing his belly. No doubt thinking about funnel cakes.")
                        else:
                            print("He's excited to see how many funnel cakes he can buy.")
                    elif option in ("wooden bridge","the bridge"):
                        print("It's made of wood.")
                    elif option in ("river","the river"):
                        print("The stream is flowing north to south.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Lake
                elif roomID == "roomLake":
                    if option in ("writing","tablet"):
                        print("The writing on the tablet neatly reads, \"OZH VO'SES SA.\"")
                        if not spell_unlock:
                            spell_unlock = 1
                    elif option in ("stranger","injured stranger"):
                        if roomCurrent.characterDead:
                            print("He's dead.")
                        else:
                            print("He looks hurt.")
                    elif option in ("lockbox","box"):
                        if not roomCurrent.counter_1:
                            print("It's locked.")
                        elif roomCurrent.counter_2:
                            print("It's open.")
                        else:
                            print("It's closed.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Temple Inside
                elif roomID == "roomTempleInside":
                    if option in ("figure","ghost","ghostly figure"):
                        if not roomCurrent.characterDead:
                            print("It looks like a cow, wrapped in white robes.")
                        else:
                            print("The figure is gone.")
                    elif option in ("statue","statue of garrotxa","garrotxa","large statue","large statue of garroxta","statue of garrotxa","the statue"):
                        print("The statue is made of white marble. It looks like a woman wearing white robes, with a cow's head. In one hand is a butter churn; in the other is a wheel of cheese.")
                    elif option in ("carpet","velvet carpet","velvet"):
                        print("The carpet is red, and has cows embroidered on it.")
                    elif option == "pedestal":
                        print("It's made of marble.")
                    elif option == "stand":
                        print("It's designed to hold the staff of Garrotxa")
                    elif option in ("mural","murals","text"):
                        print("Examine which one? There is a West, North, and East mural.")
                    elif option in ("west mural","west text", "west wall"):
                        print("The image shows a tall, cow-headed woman wearing white robes, standing at the top of Mount Magna with outstretched arms. A group of followers, wearing similar robes, bow down around her. Below, the text reads:\n")
                        print("Four a thousand years, the goddess of cheese, Garrotxa, was worshipped peacefully amongst the people of Kashkaval as a herald of propserity, fertility, and music. She lived atop Mount Magna and walked across the fields of Fermiere for most of her days. At the beginning of what most know to be as the First Age, her thirteen most devoted followers were given special robes by the goddess herself, made out of layers of divine cheesecloth. These followers were deemed the prophets of Garrotxa, and were representatives of the main cities throughout Kashkaval.")
                    elif option in ("north mural","north text", "north wall"):
                        print("The image shows a giant horned devil with open wings standing in a grassy field. He holds Garrotxa in one hand and a spear in the other. Flames protrude from the ground up into the sky. Below, the text reads:\n")
                        print("The demon king, Vesh'arkosh of the Underworld, jealous of Garrotxa's dominion of the Overworld, emerged to the surface to find her. After a great battle between the two, Garrotxa was slain and thrown into the ocean. The four demon lords, servants of Vesh'arkosh, terrorized the land to rid the world of all those who sided with Garrotxa. For a hundred years, the prophets of Garrotxa and the demons fought to control Kashkaval, initiating the \"War of Gods.\" In the end, Vesh'arkosh and his demon lords were killed along with all thirteen prophets of Garrotxa, leaving the Overworld without gods. This was the start of the Second Age. The people of Kashkaval, however, still continued to worship the late Garrotxa for what she represented.")
                    elif option in ("east mural","east text", "east wall"):
                        print("The image shows a set of red, angry eyes overlooking a city gleaming with light. Below, the text reads:\n")
                        print("Fifty years later after the War of Gods, lesser demons, known as vesh'raheen, began to attack the Garrotxian temples across Kashkaval. They would tear apart the bodies of those who visited the temples with their massive claws, and then escape to the safety of the Underworld. Many speculated that one of the demon lords was still alive and planning to make a return, although nothing came to fruition. All the cities and towns were threatened by the vesh'raheen, except the small town of Airedale, making people believe a one of the prophets of Garrotxa was alive as well, but in hiding. This conjecture created both great hope and fear, giving birth to the Third Age.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # roomRoadCorner
                elif roomID == "roomRoadCorner":
                    if option in ("black knight","knight") and not roomCurrent.characterDead and roomCurrent.counter_1:
                        print("He is standing upright with his arms resting on his broadsword. Sunlight reflects off his black armor.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Cavern
                elif roomID == "roomCave_2_llm":
                    if option in ("body","dead body"):
                        print("The man has several claw marks on his back and arms. The face is mangled and unrecognizable.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True

                            roomCurrent.journal += LOOT_B_JOURNAL
                            roomCurrent.gold += LOOT_B_GOLD
                            print("You search the body and find some supplies.")
                            roomCurrent.itemsPresent()
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomCave__3_lllm_treasure_crevasse":
                    if option in ("chest","treasure chest"):
                        if roomCurrent.itemFound:
                            print("It is open.")
                        else:
                            print("It is closed.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomCave_6_lllm":
                    if option in ("body","dead body","corpse"):
                        print("The man is missing an arm and has claw marks on his chest.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            roomCurrent.gold += LOOT_A_GOLD
                            roomCurrent.biscuit += LOOT_A_BISCUIT
                            print("You search the body and find some supplies.")
                            roomCurrent.itemsPresent()
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomCave_6_m":
                    if option in ("body","dead body","corpse"):
                        print("The corpse is rotted away, being here for at least a couple months.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            roomCurrent.pickaxe += LOOT_C_PICKAXE
                            roomCurrent.oil += LOOT_C_OIL
                            print("You search the body and find some supplies.")
                            roomCurrent.itemsPresent()
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Lair
                elif roomID == "roomLairWest":
                    if option in ("skeleton","the skeleton"):
                        print("The ribs are all broken, although the rest seems intact. It is spattered in old blood.")
                    elif option in ("table","the table"):
                        print("It's made out of the same stone as the cavern walls.")
                    elif option in ("blood","the blood"):
                        print("It looks fresh.")
                    elif option in ("opening","small opening"):
                        print("Just big enough for you to squeeze through, although probably not much else. You can see a body on the other side.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomLairHole":
                    if option in ("body","dead body","corpse"):
                        print("The man appears completely unscathed and has perhaps starved to death.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            roomCurrent.oil += LOOT_HOLE_OIL
                            print("You search the body and find some supplies.")
                            roomCurrent.itemsPresent()
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomLairMid":
                    if option in ("backpack","the backpack", "my backpack","creature","the creature"):
                        if roomCurrent.counter_3 and roomCurrent.counter_2:
                            roomCurrent.pickaxe += 1
                            roomCurrent.staff += 1
                            inside = "The staff and your pickaxe are the only items within armsreach, although trying to take them may wake up the creature, which lays unconcious in the way."
                        elif roomCurrent.counter_3:
                            roomCurrent.pickaxe += 1
                            inside = "Your pickaxe is the only item within armsreach, although trying to take it may wake up the creature, which lays unconcious in the way."
                        elif roomCurrent.counter_2:
                            roomCurrent.staff += 1
                            inside = "The staff is the only item within armsreach, although trying to take it may wake up the creature, which lays unconcious in the way."
                        else:
                            inside = "Nothing in the backpack is within armsreach. The creature lays unconcious in the way."
                        print("Your backpack is somewhat visible within a gap in the rubble.",inside)
                        roomCurrent.itemsPresent()
                    elif option in ("body","dead body","corpse"):
                        print("The man's head has been removed, and claw marks cover the rest of his body.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            roomCurrent.bandage += LOOT_MID_BANDAGE
                            roomCurrent.biscuit += LOOT_MID_BISCUIT
                            print("You search the body and find some supplies.")
                            roomCurrent.itemsPresent()
                    elif option in ("statue","gargoyle","gargoyle statue","pedestal","stone pedestal"):
                        print("It's made out of stone.")
                    elif option in ("statues","gargoyles","gargoyle statues","pedestals","stone pedestals"):
                        print("They're made out of stone.")
                    elif option in ("fountain","obsidian fountain"):
                        print("A mysterious purple fluid flows from it.")
                    elif option in ("fluid","purple fluid"):
                        print("You don't want to know what it is.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomLairEast":
                    if option in ("strange text","text"):
                        print("While it is difficult to make to make the text out, it reads, \"OZH THOK ALATHO.\"")
                        if not spell_jump:
                            spell_jump = 1
                    elif option in ("statue","gargoyle","gargoyle statue","pedestal","stone pedestal"):
                        print("It's made out of stone.")
                    elif option in ("crack","cracks","light","outside"):
                        print("Through the cracks, you can see a grassy field on the other end.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomShrine":
                    if option in ("candles","floating candles"):
                        print("Definitely magical.")
                    elif option in ("pedestal","the pedestal","wall","walls"):
                        print("It's made out of obsidian.")
                    elif option in ("pit","circular pit"):
                        print("It's shallowly filled with water.")
                    elif option == "water":
                        print("The spookiest water you've ever seen.")

                # Field
                elif roomID == "roomRoad2Mid":
                    if option in ("sign","the sign"):
                        print("It reads: \"North: House of Roquefort, West: Fermiere Farm, South: Mount Magna\"")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomFarm":
                    if option in ("field","barren field","soil","ash"):
                        print("The soil is mixed with ash and dead crops.")
                    elif option in ("dead crops","crops"):
                        print("Well, they're certainly not alive.")
                    else:
                        print("You cannot examine that.")
                elif roomID == "roomBarn":
                    if option in ("wall","writing"):
                        print("Burnt into the barn wall reads, \"Ozh groth sol.\"")
                        if not spell_light:
                            spell_light = 1
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomBarnUp":
                    if option in ("body","dead body"):
                        print("Most of it is burnt to a crisp.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            print("You search the body and find a letter.")
                            roomCurrent.letter += 1
                            roomCurrent.itemsPresent()
                            inv.letterDescription = "The edges are burnt and the parchment is covered in ash."
                            inv.letterRead = "It reads:\n\nTo whoever is still alive,\n\nBy the time you read this, I will probably be dead. Within the last two weeks, the Ozhkavosh have invaded most of Kashkaval. My greatest fears have come true. Vesh'kathal is alive and has returned to the Overworld. I thought keeping away from the cities of Finn and Fermiere would be a good idea, but the demon lord himself, disguised as my very own son, found me here, tricked me into letting him into the barn, and burnt the whole damn farmland to the ground. I would have travelled down to Airedale, but there's no way I'm crossing that cursed mountain. If you are still alive and well, Airedale may be your last safe haven from the Ozhkavosh. Don't make the same mistake I did."
                    elif option in ("haystacks","hay","stacks of hay","haystack","stack of hay"):
                        print("Most of them are burnt.")
                    elif option in ("table"):
                        print("It is covered in ash.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            print("You find a letter on top.")
                            roomCurrent.letter += 1
                            roomCurrent.itemsPresent()
                            inv.letterDescription = "The edges are burnt and the parchment is covered in ash."
                            inv.letterRead = "It reads:\n\nTo whoever is still alive,\n\nBy the time you read this, I will probably be dead. Within the last two weeks, the Ozhkavosh have invaded most of Kashkaval. My greatest fears have come true. Vesh'kathal is alive and has returned to the Overworld. I thought keeping away from the cities of Finn and Fermiere would be a good idea, but the demon lord himself, disguised as my very own son, found me here, tricked me into letting him into the barn, and burnt the whole damn farmland to the ground. I would have travelled down to Airedale, but there's no way I'm crossing that cursed mountain. If you are still alive and well, Airedale may be your last safe haven from the Ozhkavosh. Don't make the same mistake I did."
                    elif option in ("chair","wall","walls"):
                        print("It's made of wood.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Mysterious book
                elif roomID == "roomBookMirror":
                    if option in ("statue","gargoyle","gargoyle statue") and not roomCurrent.characterDead:
                        print("It's made out of stone.")
                    elif option in ("pile","stone","dust","pile of stone dust","stone dust","pile of dust") and roomCurrent.characterDead:
                        print("Rest in piece Mr. Gargoyle. You will be missed.")
                    elif option == "pedestal" and roomCurrent.itemFound:
                        print("It's made out of obsidian.")
                    elif option in ("text","wall","writing"):
                        print("Very faintly, you can see, \"IZH TAL ET OZH ICHA REK'TAL.\"")
                        if not word_mirror:
                            word_mirror = 1
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID.startswith("roomBook_3") and not roomID == "roomBook_3_End":
                    if option.endswith("statue") or option.startswith("statue"):
                        print("It's made out of obsidian.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # House
                elif roomID == "roomRoad2North":
                    if option == "gate":
                        print("Made out of heavy, metal bars.")
                    elif option in ("metal plaque","plaque"):
                        print("It reads: \"Property of Eden Von Roquefort\"")
                    elif option in ("wall","stone wall","large stone wall","walls","stone walls","large stone walls"):
                        print("It enclosed the entire house.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomHouseFoyer":
                    if option in ("chandilier","glass chandilier","large glass chandilier"):
                        print("It looks as heavy as it is expensive.")
                    elif option in ("carpet","velvet carpet","velvet"):
                        print("With how nice it looks, you feel oddly guilty about standing on it with your dirty shoes.")
                    elif option in ("bust","busts"):
                        print("You don't recognize any of the faces.")
                    elif option in ("paintings","painting"):
                        print("They all show images of cheese.")
                    elif option in ("door","rectangular door","metal door","rectangular metal door"):
                        print("Reminds you of your jail cell.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomHouseKitchen":
                    if option in ("gadget","complicated gadget","gadgets","complicated gadgets"):
                        print("Made out of metal, you don't recognize any of them or understand what they're for.")
                    elif option in ("lever","the lever"):
                        print("It is currently set to the",roomCurrent.counter_1,"position.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomHousePantry":
                    if option in ("cabinet","cabinets","cheese","cheeses"):
                        print("You feel your arteries getting clogged just looking at it all.")
                    elif option in ("wall"):
                        print("Out of all the things you choose to examine, you choose to examine the wall. How odd.")
                    elif option in ("dial"):
                        print("It is currently set to" + roomCurrent.counter_1 + ".")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomHouseHallway":
                    if option in ("carpet","velvet carpet","velvet"):
                        print("With how nice it looks, you feel oddly guilty about standing on it with your dirty shoes.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                else:
                    print("You cannot examine that.")
                    notTurn = True

        # Read
        elif option.startswith("read"):
            option = option[5:]
            if option in ("letter","the letter"):
                if inv.letter:
                    print(inv.letterRead)
                else:
                    print("You don't have a letter to read.")
                    notTurn = True
            elif option in ("journal","the journal","1","2","3","4","5","6"):
                if inv.journal:
                    # Journal Reading interface
                    print("There are six dated entries.\n	1. 31 Friesla 577\n	2. 39 Friesla 577\n	3. 42 Friesla 577\n	4. 43 Friesla 577\n	5. 3 Chaumes 577\n	6. 6 Chaumes 577\n\nWhat would you like to read?")
                    choice_made = False
                    while not choice_made:
                        if option not in ("1","2","3","4","5","6"):
                            option = input("\n> ").lower()
                        if option == "1":
                            choice_made = True
                            print("31 Friesla 577\n\nThis book is property of Rodney Williams of Pecorino Romano. I have been appointed to investigate reports of strange activity at the Mount Magna mine. While I have no current information about the mine itself, the locals of the nearby town named Airedale may provide some insight.")
                        elif option == "2":
                            choice_made = True
                            print("39 Friesla 577\n\nI have come across something very strange at the entrance of the mine. Someone, or something, has set up what seems to be an altar or shrine to the Ozkavosh. Various gargoyle statues sat around the room and there was a fountain oozing a mysterious purple fluid. I dared not find out what it was. Most shockingly, I found a partially-eaten human body at a table. We've either got cannibals here, or a vesh'raheen.")
                        elif option == "3":
                            choice_made = True
                            print("42 Friesla 577\n\nSpent the last few days here in the cavern. Airedale workers were all over the place mining for coal. I told them about the altar at the North entrance, but none of them believed me. I'll need to investigate further.")
                        elif option == "4":
                            choice_made = True
                            print("43 Friesla 577\n\nThe miners and I have been hearing strange noises in the cave. A few of us have reportedly gone missing. After a heated discussion concerning our safety, we've decided to abandon the mine and return to Airedale. On our way back, I found some writing along one of the cavern walls. \"OZH SOL FEK\" it read. I have no idea what it means.")
                            if not spell_killself:
                                spell_killself = 1
                        elif option == "5":
                            choice_made = True
                            print("3 Chaumes 577\n\nHaven't written an entry for a while now. The residents of the town have been very fairly nice and hospitable. Crime, however, have become more prevalent since the mine shut down, although the guards seem to keep it in check. Just learned that the mine was the town's central source of income, so a large portion of the population here are now without jobs. Maybe they should starting working in lumber with all the trees around. I tried to get more information about the mine from whoever I could although most don't like to talk about it. I've decided to return back to the cave to get to the bottom of this.")
                        elif option == "6":
                            choice_made = True
                            print("6 Chaumes 577\n\nA strange creature has been roaming the mines, killing the consuming those it comes it in contact with. While I haven't been able to obtain descriptions of its appearance, I have come across corpses covered in claw marks. Having a source of light seems to keep it away, if only for a while, which I've notified the locals of. That, along with all the growling and screeching I've been hearing, makes me fear this creature may be a vesh'raheen, servant of the Ozkavosh, Vesh'kathal. The problem is that there does not seem to be any reason for a vesh'raheen to be here as they do not live in caves, let alone, anywhere here in Kashkaval. That is, of course, unless Vesh'kathal wants to keep someone from entering or crossing the mines. I will return to Romano Pecorino to report my findings and scale up this investigation. Something very suspicious is happening here.")
                        else:
                            print("Choose 1-6.")
                else:
                    print("You don't have a journal to read.")
                    notTurn = True
            elif option in ("book","mysterious book"):
                if inv.book:
                    print("As you stare into the open book, the symbols on the pages consume your mind.")

                    if roomID == "roomBookMirror":
                        # From Mirror puzzle to Movement Puzzle
                        roomID = "roomBook_3_1"
                        roomCurrent = roomBook_3_1
                        inv = Inventory()
                    elif roomID.startswith("roomBook_3"):
                        # From Movement puzzle to outside
                        roomID = roomID_Outside
                        roomCurrent = roomCurrent_Outside
                        if inv.keySkeleton:
                            inv_Outside.keySkeleton = 1
                        else:
                            inv_Outside.keySkeleton = 0
                        if inv.book:
                            inv_Outside.book = 1
                        inv = inv_Outside
                        silenced = True
                        print("You feel the strange force take over you again.")
                    else:
                        # From outside to inside
                        roomID_Outside = roomID
                        roomCurrent_Outside = roomCurrent
                        inv_Outside = inv
                        if roomBook_3_End.firstTime: # If puzzle has not been completed
                            roomID = "roomBookMirror"
                            roomCurrent = roomBookMirror
                        else:
                            roomID = "roomBook_3_End"
                            roomCurrent = roomBook_3_End
                        inv = Inventory()
                        if inv_Outside.keySkeleton:
                            inv.keySkeleton = 1
                        if roomBook_3_End.itemFound:
                            inv.book = 1
                        silenced = False
                        print("You feel the strange force leave your body.")
                    roomCurrent.description()
                    roomCurrent.itemsPresent()
                else:
                    print("You don't have a book to read.")
                    notTurn = True
            elif option in ("sign", "the sign"):
                if roomID == "roomMountEntrance":
                    print("It reads: \"Beware the Creature. Do not enter without a light source. Enter at your own risk.\"")
                elif roomID == "roomRoadMid":
                    print("The sign reads: \"North: Mount Magna, East: Temple of Garrotxa, West: Lake Laguiole, South: Town of Airedale\"")
                elif roomID == "roomCourtyardNorth":
                    print("The sign reads: \"Come one, come all, to the Wonderful Wheel of Mystery! Want to get rich quick? Spin the wheel test your luck! Only at the Airedale Carnival!\"")
                elif roomID == "roomRoad2Mid":
                    print("It reads: \"North: House of Roquefort, West: Fermiere Farm, South: Mount Magna\"")
                else:
                    print("There is no sign here.")
            elif roomID == "roomLake":
                if option in ("writing","tablet"):
                    print("The writing on the tablet neatly reads, \"OZH VO'SES SA.\"")
                    if not spell_unlock:
                        spell_unlock = 1
            elif roomID == "roomTempleInside":
                if option in ("mural","murals","text"):
                    print("Read which one? There is a West, North, and East mural.")
                elif option in ("west mural","west text", "west wall"):
                    print("The image shows a tall woman with a head of a cow, wearing white robes, standing at the top of Mount Magna with outstretched arms. A group of followers, wearing the same robes, bow down around her. Below, the text reads:\n")
                    print("Four a thousand years, the goddess of cheese, Garrotxa, was worshipped peacefully amongst the people of Kashkaval as a herald of propserity, fertility, and music. She lived atop Mount Magna and walked across the fields of Fermiere for most of her days. At the beginning of what most know to be as the First Age, her thirteen most devoted followers were given special robes by the goddess herself, made out of layers of divine cheesecloth. These followers were deemed the prophets of Garrotxa, and were representatives of the main cities throughout Kashkaval.")
                elif option in ("north mural","north text", "north wall"):
                    print("The image shows a giant horned devil with open wings standing in a grassy field. He holds Garrotxa in one hand and a spear in the other. Flames protrude from the ground up into the sky. Below, the text reads:\n")
                    print("The demon king, Vesh'arkosh of the Underworld, jealous of Garrotxa's dominion of the Overworld, emerged to the surface to find her. After a great battle between the two, Garrotxa was slain and thrown into the ocean. The four demon lords, servants of Vesh'arkosh, terrorized the land to rid the world of all those who sided with Garrotxa. For a hundred years, the prophets of Garrotxa and the demons fought to control Kashkaval, initiating the \"War of Gods.\" In the end, Vesh'arkosh and his demon lords were killed along with all thirteen prophets of Garrotxa, leaving the Overworld without gods. This was the start of the Second Age. The people of Kashkaval, however, still continued to worship the late Garrotxa for what she represented.")
                elif option in ("east mural","east text", "east wall"):
                    print("The image shows a set of red, angry eyes overlooking a city gleaming with light. Below, the text reads:\n")
                    print("Fifty years later after the War of Gods, lesser demons, known as vesh'raheen, began to attack the Garrotxian temples across Kashkaval. They would tear apart the bodies of those who visited the temples with their massive claws, and then escape to the safety of the Underworld. Many speculated that one of the demon lords was still alive and planning to make a return, although nothing came to fruition. All the cities and towns were threatened by the vesh'raheen, except the small town of Airedale, making people believe a one of the prophets of Garrotxa was alive as well, but in hiding. This conjecture created both great hope and fear, giving birth to the Third Age.")
                else:
                    print("You cannot read that.")
                    notTurn = True
            elif roomID == "roomLairEast":
                if option in ("strange text","text","wall"):
                    print("While it is difficult to make to make the text out, it reads, \"OZH THOK ALATHO.\"")
                    if not spell_jump:
                        spell_jump = 1
                else:
                    print("You cannot read that.")
                    notTurn = True
            elif roomID == "roomBarn":
                    if option in ("wall","writing"):
                        print("Burnt into the barn wall reads, \"Ozh groth sol.\"")
                        if not spell_light:
                            spell_light = 1
                    else:
                        print("You cannot read that.")
                        notTurn = True
            elif roomID == "roomBookMirror":
                if option in ("text","wall","writing"):
                        print("Very faintly, you can see, \"Izh tal et ozh icha rek'tal.\"")
                        if not word_mirror:
                            word_mirror = 1
                else:
                    print("You cannot read that.")
                    notTurn = True
            elif roomID == "roomRoad2North":
                if option in ("metal plaque","plaque"):
                    print("It reads: \"Property of Eden Von Roquefort\"")
                else:
                    print("You cannot read that.")
                    notTurn = True
            else:
                print("You cannot read that.")
                notTurn = True
        # Walk, Move, Go
        elif option in ("north","n","east","e","south","s","west","w","up","u","down","d") or option.startswith("walk") or option.startswith("move") or option.startswith("go"):
            if option.startswith("walk") or option.startswith("move"):
                option = option[5:]
            elif option.startswith("go"):
                option = option[3:]
            # Sets walk direction
            directionBlocked = False
            if option in ("north","n"):
                direction = "North"
                if roomID == "roomLairHole":
                    if inv.itemTypes() > 1:
                        roomCurrent.northBlocked = True
                    else:
                        roomCurrent.northBlocked = False
                if roomCurrent.north and not roomCurrent.northBlocked:
                    roomID = roomCurrent.north
                    changeRoom = True
                elif roomCurrent.northBlocked:
                    directionBlocked = True
            elif option in ("east","e"):
                direction = "East"
                if roomCurrent.east and not roomCurrent.eastBlocked:
                    roomID = roomCurrent.east
                    changeRoom = True
                elif roomCurrent.eastBlocked:
                    directionBlocked = True
            elif option in ("south","s"):
                direction = "South"
                if roomID == "roomLairWest":
                    if inv.itemTypes() > 1:
                        roomCurrent.southBlocked = True
                    else:
                        roomCurrent.southBlocked = False
                if roomCurrent.south and not roomCurrent.southBlocked:
                    roomID = roomCurrent.south
                    changeRoom = True
                elif roomCurrent.southBlocked:
                    directionBlocked = True
            elif option in ("west","w"):
                direction = "West"
                if roomCurrent.west and not roomCurrent.westBlocked:
                    roomID = roomCurrent.west
                    changeRoom = True
                elif roomCurrent.westBlocked:
                    directionBlocked = True
            elif option in ("up","u"):
                direction = "up"
                if roomCurrent.up and not roomCurrent.upBlocked:
                    roomID = roomCurrent.up
                    changeRoom = True
                elif roomCurrent.upBlocked:
                    directionBlocked = True
            elif option in ("down","d"):
                direction = "down"
                if roomCurrent.down and not roomCurrent.downBlocked:
                    roomID = roomCurrent.down
                    changeRoom = True
                elif roomCurrent.downBlocked:
                    directionBlocked = True
            elif option == "":
                print("Go where?")
            else:
                print("You cannot go there.")
                notTurn = True
            if not changeRoom and direction in ("North","East","South","West","up","down") and not directionBlocked:
                print("You cannot go %s." % direction)
                notTurn = True
        # Use, Mine, Unlock
        # Mine pickaxe to get coal if in mine
        # Unlock doors
        elif option.startswith("use") or option.startswith("unlock") or option.startswith("mine") or option.startswith("throw") or option.startswith("light"):
            use = False
            unlock = False
            mine = False
            throw = False
            light = False
            if option.startswith("use"):
                option = option[4:]
                if option == "":
                    print("Use what?")
                    notTurn = True
                else:
                    use = True
            elif option.startswith("unlock"):
                option = option[7:]
                if option == "":
                    print("Unlock what?")
                    notTurn = True
                else:
                    unlock = True
            elif option.startswith("mine"):
                option = option[5:]
                if option == "":
                    print("Mine what?")
                    notTurn = True
                else:
                    mine = True
            elif option.startswith("throw"):
                option = option[6:]
                if option == "":
                    print("Throw what?")
                    notTurn = True
                else:
                    throw = True
            elif option.startswith("light"):
                option = option[6:]
                if option == "":
                    print("Light what?")
                    notTurn = True
                else:
                    light = True
            # Use/unlock key to unlock roomJailCell
            if (use and option in ("key","key on door")) or (unlock and option in ("door","door with key")):
                if roomID == "roomJailCell" and roomJailCell.eastBlocked:
                    if inv.key or inv.keySkeleton:
                        roomJailCell.eastBlocked = False
                        print("You unlock the cell door.")
                        print("You can now go East.")
                    else:
                        print("You cannot unlock the cell door without the key.")
                        notTurn = True
                elif roomID == "roomHouseEntrance" and roomHouseEntrance.northBlocked:
                    if inv.keySkeleton:
                        roomHouseEntrance.northBlocked = False
                        print("You unlock the house door. The key of Ahm'domosh disintegrates, returing to the Underworld.")
                        inv.keySkeleton -= 1
                    else:
                        print("You cannot unlock the house door without the key.")
                        notTurn = True
                elif roomID == "roomHouseHallway":
                    print("You do not have a key.")
                else:
                    print("There is nothing here to unlock.")
            # Use bandage to heal wounds
            elif use and option in ("bandage","bandages"):
                if inv.bandage:
                    if stat.health < stat.healthmax:
                        stat.health = stat.healthmax
                        inv.bandage -= 1
                        print("You apply a bandage to your wound to stop the bleeding.")
                    else:
                        print("You are not injured.")
                        notTurn = True
                else:
                    print("You don't have any bandages.")
                    notTurn = True
            # Use oil to light lantern
            elif (use and (option.startswith("oil") or option.startswith("vial") or option.endswith("oil"))) or (light and option.startswith("lantern")):
                if inv.oil:
                    if inv.lantern:
                        if oilCounter == 0:
                            print("You fill your lantern with a vial of oil, and light it. It starts to burn.")
                        else:
                            print("You top up your lantern with a vial of oil. It continues to burn.")
                        oilCounter += OIL_DURATION
                        inv.oil -= 1
                    else:
                        print("You don't have a lantern.")
                        notTurn = True
                else:
                    print("You don't have any vials of lantern oil.")
                    notTurn = True
            # Use/Throw grappling hook to get across crevasse
            elif (use or throw) and option.startswith("grappling hook") or option.startswith("hook"):
                if inv.hook:
                    if roomCurrent.isCrevasse:
                        if roomCurrent.northBlocked or roomCurrent.eastBlocked or roomCurrent.westBlocked or roomCurrent.southBlocked:
                            roomCurrent.northBlocked = False
                            roomCurrent.eastBlocked = False
                            roomCurrent.westBlocked = False
                            roomCurrent.southBlocked = False
                            print("You throw the grappling hook across the crevasse, which catches on a stalagmite on the other end. You tie the rope on a stalagmite on your end, opening a way across.")
                            inv.hook -= 1
                            roomCurrent.hook += 1
                            roomCurrent.itemsPresent()
                        else:
                            print("You can already cross the crevasse.")
                            notTurn = True
                    else:
                        print("You cannot throw a grappling hook here.")
                        notTurn = True
                else:
                    print("You don't have a grappling hook.")
                    notTurn = True
            # Use/Mine with pickaxe
            elif (use or mine) and option in ("pickaxe","pick","vein","veins","coal","coal vein","coal veins","rubble","rocks","rock"):
                if inv.pickaxe:
                    if roomCurrent.isMine:
                        if ((use and option in ("pickaxe","pick")) or (mine and option in ("vein","veins","coal","coal vein","coal veins"))) and roomID.endswith("coalmine"): # coal
                            if random.randint(1,100) > PICKAXE_BREAK_CHANCE:
                                roomCurrent.counter_1 -= 1
                                inv.coal += 1
                                print("You mine away at the coal vein and get a piece of coal.")
                                if not roomCurrent.counter_1:
                                    roomCurrent.isMine = False
                                    print("There is no more coal left.")
                            else:
                                inv.pickaxe -= 1
                                print("You mine away at the coal vein and break your pickaxe.")
                        elif (use and option in ("pickaxe","pick")) or (mine and option in ("rubble","rocks","rock")): # rubble
                            roomCurrent.counter_1 -= 1
                            print("You mine away at the rubble, breaking apart some of the rock.")
                            if not roomCurrent.counter_1:
                                roomCurrent.isMine = False
                                roomCurrent.northBlocked = False
                                roomCurrent.eastBlocked = False
                                roomCurrent.westBlocked = False
                                roomCurrent.southBlocked = False
                                print("The opening is now large enough to go through.")
                        elif mine:
                            print("You cannot mine that.")
                            notTurn = True
                        elif use:
                            print("You cannot use that.")
                            notTurn = True
                    else:
                        print("You cannot mine anything here.")
                        notTurn = True
                elif mine:
                    print("You cannot mine anything without a pickaxe.")
                    notTurn = True
                else:
                    print("You cannot use that.")
                    notTurn = True
            # Use staff on characters in room
            elif option.startswith("the staff of garrotxa") or option.startswith("staff of garrotxa") or option.startswith("staff"):
                if inv.staff:
                    if not roomCurrent.characterDead or roomID == "roomHouseOffice" or roomID.startswith("roomLair") or (roomID.startswith("roomCave") and creatureChaseCounter in [1,2]) or (roomID.startswith("roomJail") and jailGuardCounter in [1,2]):
                        roomCurrent.characterDead = True
                        print("A bolt of lightning fires out from the staff, striking ",end = "")
                        if roomID == "roomCarnivalShellGame":
                            print("the old woman. As she topples over, all her gold is spilled across the ground.")
                            roomCurrent.isBet = False
                            roomCurrent.gold += invShell.gold
                            invShell.gold = 0
                            roomCurrent.itemsPresent()
                        elif roomID.startswith("roomJail"):
                            print("a jail guard. The rest surround you and take you down.")
                            endGame = True
                        elif roomID == "roomCarnivalWheelGame":
                            print("the man. The crowd goes into panic as guards enter the tent and overwhelm you.")
                            endGame = True
                        elif roomID == "roomCarnivalFood":
                            print("the vendor. He falls over, leaving his supply of food behind.")
                            roomCurrent.isBuy = False
                            roomCurrent.funnelCake += invFood.funnelCake
                            invFood.funnelCakes = 0
                            roomCurrent.pie += 10
                            roomCurrent.itemsPresent()
                        elif roomID == "roomBridge":
                            print("the troll. It falls over, dropping the funnel cakes in its hands.")
                            roomCurrent.eastBlocked = False
                            roomCurrent.funnelCake += 1
                            roomCurrent.halfFunnelCake += 3
                            roomCurrent.itemsPresent()
                        elif roomID == "roomGeneralStore":
                            print("the shopkeeper, leaving his store supplies behind.")
                            roomCurrent.isBuy = False
                            roomCurrent.isSell = False
                            roomCurrent.oil += 5
                            roomCurrent.foot += 5
                            roomCurrent.lantern += 1
                            roomCurrent.bandage += 5
                            roomCurrent.itemsPresent()
                        elif roomID == "roomBlacksmith":
                            print("the blacksmith, leaving his supplies behind.")
                            roomCurrent.isBuy = False
                            roomCurrent.isSell = False
                            roomCurrent.pickaxe += 2
                            roomCurrent.hook += 3
                            roomCurrent.itemsPresent()
                        elif roomID == "roomAlchemist":
                            print("Tim the Enchanter, leaving his supplies behind.")
                            roomCurrent.isBuy = False
                            roomCurrent.isFill = False
                            roomCurrent.isSell = False
                            roomCurrent.potion += 3
                            roomCurrent.flask += 4
                            roomCurrent.itemsPresent()
                        elif roomID in ("roomCarnival","roomCourtyardNorth","roomCourtyardSouth"):
                            print("a random civilian. The loud noise alerts the town guards, who catch up to you and kill you.")
                            endGame = True
                        elif roomID == "roomGate":
                            print("the gate guard. Another nearby guard one draws his sword and impales you.")
                            endGame = True
                        elif roomID == "roomRoadCorner" and roomRoadCorner.counter_1:
                            print("the black knight. He shouts, \"Izh vo'poz!\" before his body vaporizes into nothing.")
                            if not spell_persuade:
                                spell_persuade = 1
                            roomRoadCorner.westBlocked = False
                        elif roomID == "roomHouseOffice":
                            roomCurrent.counter_2 += 1
                            if roomCurrent.counter_2 == 1:
                                print("Eden Von Roquefort. His dead body flies back and crashes on the desk.")
                            elif roomCurrent.counter_2 == 2:
                                print("Vesh'kathal. She resists the shock and charges towards you, knocking the staff out of your hand. Merely grazing his arm against the holy staff causes her scales to burn, making her to topple over in pain.")
                                inv.staff -= 1
                                roomCurrent.staff += 1
                                roomCurrent.itemsPresent()
                            else:
                                print("Vesh'kathal. She is severly weaked, but manages to stay alive.")
                        elif roomID.startswith("roomCave"):
                            print("the creature, which resists the spell. It only gets angrier.")
                        elif roomID.startswith("roomLair"):
                            print("the creature, which resists the shock. It only gets angrier.")
                            if not creatureLairChase:
                                creatureLairChase = True
                        elif roomID == "roomLake":
                            print("the stranger, leaving his supplies behind.")
                            roomCurrent.pie += 1
                            roomCurrent.itemsPresent()
                    else:
                        print("There's no one else here.")
                else:
                    print("You don't have the staff of Garrotxa.")
                    notTurn = True
            else:
                if use:
                    print("You cannot use that.")
                elif unlock:
                    print("You cannot unlock that.")
                elif mine:
                    print("You cannot mine that.")
                elif throw:
                    print("You cannot throw that.")
                elif light:
                    print("You cannot light that.")
                notTurn = True
        # Turn, set
        # Unlock vault in roomCorridor
        elif option.startswith("turn") or option.startswith("rotate") or option.startswith("set") or option.startswith("change") or option.startswith("push") or option.startswith("pull"):
            if option.startswith("turn"):
                option = option[5:]
                if option == "":
                    print("Turn what?")
                    notTurn = True
                elif roomID not in ("roomJailCorridor","roomHousePantry"):
                    print("You cannot turn that.")
                    notTurn = True
            elif option.startswith("rotate"):
                option = option[7:]
                if option == "":
                    print("Rotate what?")
                    notTurn = True
                elif roomID not in ("roomJailCorridor","roomHousePantry"):
                    print("You cannot rotate that.")
                    notTurn = True
            elif option.startswith("set"):
                option = option[4:]
                if option == "":
                    print("Set what?")
                    notTurn = True
                elif roomID not in ("roomJailCorridor","roomHousePantry","roomHouseKitchen"):
                    print("You cannot set that.")
                    notTurn = True
            elif option.startswith("change"):
                option = option[7:]
                if option == "":
                    print("Change what?")
                    notTurn = True
                elif roomID not in ("roomJailCorridor","roomHousePantry","roomHouseKitchen"):
                    print("You cannot change that.")
                    notTurn = True
            elif option.startswith("push"):
                option = option[5:]
                if option == "":
                    print("Push what?")
                    notTurn = True
                elif roomID not in ("roomHousePantry","roomHouseKitchen"):
                    print("You cannot push that.")
                    notTurn = True
            elif option.startswith("pull"):
                option = option[5:]
                if option == "":
                    print("Pull what?")
                    notTurn = True
                elif roomID not in ("roomHousePantry","roomHouseKitchen"):
                    print("You cannot pull that.")
                    notTurn = True
            if option != "":
                if roomID == "roomJailCorridor":
                    # Set numbers in vault in roomJailCorridor
                    change_vault_1 = False
                    change_vault_2 = False
                    change_vault_3 = False
                    change_vault_4 = False
                    if option.startswith("dial 1 to"):
                        option = option[10:]
                        change_vault_1 = True
                    elif option.startswith("first dial to"):
                        option = option[14:]
                        change_vault_1 = True
                    elif option.startswith("dial 2 to"):
                        option = option[10:]
                        change_vault_2 = True
                    elif option.startswith("second dial to"):
                        option = option[15:]
                        change_vault_2 = True
                    elif option.startswith("dial 3 to"):
                        option = option[10:]
                        change_vault_3 = True
                    elif option.startswith("third dial to"):
                        option = option[14:]
                        change_vault_3 = True
                    elif option.startswith("dial 4 to"):
                        option = option[10:]
                        change_vault_4 = True
                    elif option.startswith("fourth dial to"):
                        option = option[15:]
                        change_vault_4 = True
                    if option == "":
                        if change_vault_1:
                            print("Change first dial to what?")
                            notTurn = True
                        elif change_vault_2:
                            print("Change second dial to what?")
                            notTurn = True
                        elif change_vault_3:
                            print("Change third dial to what?")
                            notTurn = True
                        elif change_vault_4:
                            print("Change fourth dial to what?")
                            notTurn = True
                    elif option.isdigit():
                        option = int(option)
                        if option in range(10):
                            if change_vault_1:
                                roomCurrent.counter_1 = option
                                print("You turn the first dial, setting it to %i." % option)
                            elif change_vault_2:
                                roomCurrent.counter_2 = option
                                print("You turn the second dial, setting it to %i." % option)
                            elif change_vault_3:
                                roomCurrent.counter_3 = option
                                print("You turn the third dial, setting it to %i." % option)
                            elif change_vault_4:
                                roomCurrent.counter_4 = option
                                print("You turn the fourth dial, setting it to %i." % option)
                        else:
                            print("You cannot do that.")
                            notTurn = True
                    else:
                        print("You cannot do that.")
                        notTurn = True
                    if (roomCurrent.counter_1, roomCurrent.counter_2, roomCurrent.counter_3, roomCurrent.counter_4) == (roomCurrent.counterAns_1, roomCurrent.counterAns_2, roomCurrent.counterAns_3, roomCurrent.counterAns_4) and roomCurrent.northBlocked:
                        print("You hear a loud clunk, as the vault door creaks open.")
                        roomCurrent.northBlocked = False
                elif roomID == "roomHousePantry":
                    change_dial = False
                    if option.startswith("dial to"):
                        option = option[8:]
                        change_dial = True
                    if option == "":
                        print("Change dial to what?")
                        notTurn = True
                    elif option == "green":
                        roomCurrent.counter_1 = "green"
                    elif option == "red":
                        roomCurrent.counter_1 = "red"
                    elif option == "blue":
                        roomCurrent.counter_1 = "blue"
                    else:
                        print("You cannot change the dial to" + option + ".")
                    if option in ("green","red","blue"):
                        print("You turn the dial to " + option + ".")
                if roomHousePantry.counter_1 == DIAL_ANSWER and roomHouseKitchen.counter_1 == LEVER_ANSWER and roomHouseFoyer.northBlocked:
                    print("You hear a loud clunk.")
                    roomHouseFoyer.northBlocked = False
                elif roomID == "roomHouseKitchen":
                    change_lever = False
                    if option.startswith("lever to"):
                        option = option[9:]
                        change_lever = True
                    elif option.startswith("lever"):
                        option = option[6:]
                        change_lever = True
                    if option == "":
                        print("Change lever to what?")
                        notTurn = True
                    elif option in ("forward","forwards"):
                        roomCurrent.counter_1 = "forward"
                        print("You set the lever to the \"forward\" position.")
                    elif option in ("backward","back","backwards"):
                        roomCurrent.counter_1 = "backwards"
                        print("You set the lever to the \"backwards\" position.")
                    else:
                        print("You cannot change the lever to " + option + ".")
                    if roomHousePantry.counter_1 == DIAL_ANSWER and roomHouseKitchen.counter_1 == LEVER_ANSWER and roomHouseFoyer.northBlocked:
                        print("You hear a loud clunk.")
                        roomHouseFoyer.northBlocked = False


        # Open
        # Treasure chest in roomCave__3_lllm_treasure_crevasse
        elif option.startswith("open"):
            option = option[5:]
            if roomID == "roomCave__3_lllm_treasure_crevasse":
                if option in ("chest","treasure chest","the chest","the treasure chest"):
                    if not roomCurrent.itemFound:
                        roomCurrent.itemFound = True
                        roomCurrent.gold += CHEST_REWARD
                        roomCurrent.stone += 1
                        print("The rusted hinges of the chest break as the heavy lid of the chest swings back. You find something inside.")
                        roomCurrent.itemsPresent()
                    else:
                        print("The treasure chest is already open.")
                        notTurn = True
                else:
                    print("You cannot open that.")
                    notTurn = True
            elif roomID == "roomLake":
                if option in ("lockbox","box"):
                    if not roomCurrent.counter_1: # not unlocked (locked)
                        print("You cannot open the lockbox. It is locked.")
                    else: # unlocked
                        if roomCurrent.counter_2 == 1: # open
                            print("It is already open.")
                        else:
                            roomCurrent.counter_2 = 1 # is now open
                            if roomCurrent.itemFound:
                                print("You open the lockbox.")
                            else:
                                print("You open the lockbox and find some gold inside.")
                                roomCurrent.gold += LAKE_GOLD_REWARD
                                roomCurrent.itemFound = True
                                roomCurrent.itemsPresent()

            else:
                print("You cannot open that.")
                notTurn = True
        # Close
        # Treasure chest in roomCave__3_lllm_treasure_crevasse
        elif option.startswith("close"):
            option = option[6:]
            if roomID == "roomCave__3_lllm_treasure_crevasse":
                if option in ("chest","treasure chest","the chest","the treasure chest"):
                    if not roomCurrent.itemFound:
                        print("The treasure chest is already closed.")
                        notTurn = True
                    else:
                        print("The rusted hinges of the chest are broken.")
                else:
                    print("You cannot close that.")
                    notTurn = True
            elif roomID == "roomLake":
                if not roomCurrent.counter_2:
                    print("It is already closed.")
                else:
                    roomCurrent.counter_2 = 0
                    print("You close the lockbox.")
            else:
                print("You cannot close that.")
                notTurn = True
        # Eat
        # Eat food to regain hunger
        elif option.startswith("eat"):
            if stat.hunger > HUNGER_MAX:
                print("You are too full to eat anything.")
            else:
                option = option[4:]
                foodEmpty = False
                if option in ("cake","cakes","funnel cake","funnel cakes"):
                    foodName = "funnel cake"
                    if inv.funnelCake:
                        inv.funnelCake -= 1
                        stat.hunger += HUNGER_FUNNELCAKE
                    else:
                        foodEmpty = True
                elif option in ("half-eaten cake","half cake","half-eaten funnel cake","half eaten funnel cake","half funnel cake","half-eaten cakes","half cakes","half-eaten funnel cakes","half eaten funnel cakes","half funnel cakes"):
                    foodName = "the half-eaten funnel cake"
                    if inv.halfFunnelCake:
                        inv.halfFunnelCake -= 1
                        stat.hunger += HUNGER_HALFFUNNELCAKE
                    else:
                        foodEmpty = True
                elif option in ("porridge","bowl of porridge"):
                    foodName = "bowl of porridge"
                    if inv.porridge:
                        inv.porridge -= 1
                        inv.bowl += 1
                        stat.hunger += HUNGER_PORRIDGE
                    else:
                        foodEmpty = True
                elif option in ("bowl","porridge bowl"):
                    foodName = "bowl"
                    if not (inv.bowl or inv.porridge):
                        foodEmpty = True
                elif option in ("pie","pies","chicken","chicken pot","chicken pot pie"):
                    foodName = "chicken pot pie"
                    if inv.pie:
                        inv.pie -= 1
                        stat.hunger += HUNGER_PIE
                    else:
                        foodEmpty = True
                elif option in ("hardtack biscuit","hardtack biscuits","biscuit","biscuits","hardtack"):
                    foodName = "hardtack biscuit"
                    if inv.biscuit:
                        inv.biscuit -= 1
                        stat.hunger += HUNGER_BISCUIT
                    else:
                        foodEmpty = True
                elif option == "potato":
                    foodName = "potato"
                    if not inv.potato:
                        foodEmpty = True
                elif option in ("brie","slice of brie","slice of brie cheese", "brie cheese"):
                    foodName = "slice of brie cheese"
                    if inv.brie:
                        inv.brie -= 1
                        stat.hunger += HUNGER_CHEESE
                    else:
                        foodEmpty = True
                elif option in ("munster","slice of munster","slice of munster cheese","munster cheese"):
                    foodName = "slice of munster cheese"
                    if inv.munster:
                        inv.munster -= 1
                        stat.hunger += HUNGER_CHEESE
                    else:
                        foodEmpty = True
                elif option in ("stilton","slice of stilton","slice of stilton cheese","stilton cheese"):
                    foodName = "slice of stilton cheese"
                    if inv.stilton:
                        inv.stilton -= 1
                        stat.hunger += HUNGER_CHEESE
                    else:
                        foodEmpty = True
                elif option in ("swiss","slice of swiss","slice of swiss cheese","swiss cheese"):
                    foodName = "slice of swiss cheese"
                    if inv.swiss:
                        inv.swiss -= 1
                        stat.hunger += HUNGER_CHEESE
                elif option in ("wensleydale","slice of wensleydale","slice of wensleydale cheese","wensleydale cheese"):
                    foodName = "slice of wensleydale cheese"
                    if inv.wensleydale:
                        inv.wensleydale -= 1
                        stat.hunger += HUNGER_CHEESE
                elif option in ("potion","potion of rejuvination","potions","potions of rejuvination"):
                    foodName = "potion of rejuvination"
                    if not inv.potion:
                        foodEmpty = True
                elif option == "":
                    foodName = "null"
                else:
                    foodName = "invalid"
                if foodEmpty:
                    print("You have no " + foodName + " to eat.")
                    notTurn = True
                elif foodName == "potato":
                    print("You eat the potato and quickly feel sick to your stomach. You thought the five second rule was a thing? What about the 5 month rule?")
                    endGame = True
                elif foodName == "potion of rejuvination":
                    print("While the potion may be rather thick, you can't eat a liquid. Well, I guess you could, much the chewing motions would be be kind of a waste of energy.")
                    notTurn = True
                elif foodName == "bowl":
                    print("Despite how hungry you may be, you can't actually eat the bowl itself, considering it's made out of metal.")
                    notTurn = True
                elif foodName == "null":
                    print("Eat what?")
                    notTurn = True
                elif foodName == "invalid":
                    print("You cannot eat that.")
                    notTurn = True
                elif foodName not in ("null","nomessage"):
                    print("You eat a " + foodName + ".")
        # Drink
        # Potion
        elif option.startswith("drink"):
            if stat.hunger > HUNGER_MAX:
                print("You are too full to drink anything.")
            else:
                option = option[6:]
                drinkEmpty = False
                if option in ("potion","potion of rejuvination"):
                    drinkName = "potion of rejuvination"
                    if inv.potion:
                        inv.potion -= 1
                        inv.flask += 1
                        stat.hunger += HUNGER_POTION
                        stat.health = HEALTH_MAX
                    else:
                        drinkEmpty = True
                elif option in ("fluid","mysterious fluid","purple fluid","mysterious purple fluid") and roomID == "roomLairMid":
                    print("So you wake up after after being chased by a creature and find yourself in a pretty creepy place with a strange-looking fountain and you think to yourself, \"Oh geez, you know what I should do? Drink this mysterious purple fluid and see what happens. Surely nothing bad. Obviously this is how I escape. Oh wait, that's a terrible idea.\" You reconsider your live decisions.")
                    drinkName = "nomessage"
                elif option == "":
                    drinkName = "null"
                else:
                    drinkName = "invalid"
                if drinkEmpty:
                    print("You have no " + drinkName + " to drink.")
                    notTurn = True
                elif drinkName == "invalid":
                    print("You cannot drink that.")
                    notTurn = True
                elif drinkName == "null":
                    print("Drink what?")
                    notTurn = True
                elif drinkName not in ("null","nomessage"):
                    print("You drink a " + drinkName + ".")
        # Fill/Refill
        # Exchange flask for potion
        elif option.startswith("fill") or option.startswith("refill"):
            if option.startswith("fill"):
                option = option[5:]
            else:
                option = option[7:]
            if roomCurrent.isFill:
                if option == "":
                    print("Fill what?")
                    notTurn = True
                else:
                    fillFlask = False
                    if option in ("flask","empty flask"):
                        fill_count = 1
                        fillFlask = True
                    elif option in ("flasks","empty flasks","all flasks","every flask"):
                        fill_count = inv.flask
                        fillFlask = True
                    elif option.endswith("flask"):
                        fill_count = option[:len(option) - len("flask") - 1]
                        fillFlask = True
                    elif option.endswith("flasks"):
                        fill_count = option[:len(option) - len("flasks") - 1]
                        fillFlask = True
                    elif option.endswith("empty flask"):
                        fill_count = option[:len(option) - len("empty flask") - 1]
                        fillFlask = True
                    elif option.endswith("empty flasks"):
                        fill_count = option[:len(option) - len("empty flasks") - 1]
                        fillFlask = True
                    if fillFlask:
                        if fillFlask:
                            itemFillName = "flask"
                            itemsFillName = "flasks"
                            itemReturnName = "potion of rejuvination"
                            itemsReturnName = "potions of rejuvination"
                            invItemFill = inv.flask
                            invItemReturn = inv.potion
                    itemInStore = False
                    if roomID == "roomAlchemist":
                        shopKeeperName = "Tim the Enchanter"
                        if fillFlask:
                            itemInStore = True
                    if itemInStore:
                        fillItemCountOkay = False
                        try:
                            fill_count = float(fill_count)
                            if float(fill_count) % 1 == 0 and fill_count > 0:
                                fillItemCountOkay = True
                                fill_count = int(fill_count)
                            else:
                                if fill_count:
                                    print("You cannot fill",fill_count,"%s." % itemsFillName)
                                    notTurn = True
                        except ValueError:
                            print("You cannot fill \"" + fill_count + "\" %s." % itemsFillName)
                        if fillItemCountOkay:
                            if inv.flask >= fill_count:
                                if inv.gold >= fill_count * price:
                                    if fill_count > 1:
                                        print("You give",shopKeeperName,fill_count * price,"gold and",fill_count,itemsFillName,"to fill. He gives you",fill_count,"potions of rejuvination in return.")
                                    elif fill_count == 1:
                                        print("You give",shopKeeperName,fill_count * price,"gold and a",itemFillName,"to fill. He gives you a potion of rejuvination in return.")
                                    if fillFlask:
                                        inv.potion += fill_count
                                        inv.flask -= fill_count
                                        invItemFill -= fill_count
                                        invItemReturn += fill_count
                                        inv.gold -= fill_count * price
                                    if invItemReturn:
                                        if invItemReturn == 1:
                                            print("You have",invItemReturn,itemReturnName,"and",inv.gold,"gold.")
                                        else:
                                            print("You have",invItemReturn,itemsReturnName,"and",inv.gold,"gold.")
                                    if invItemFill:
                                        if invItemFill == 1:
                                            print("You have",invItemFill,itemFillName + ".")
                                        else:
                                            print("You have",invItemFill,itemsFillName + ".")
                                    print("You have",inv.gold,"gold.")
                                else:
                                    if fill_count > 1:
                                        print("You don't have enough gold to fill",fill_count,"%s." % itemsName)
                                    else:
                                        print("You don't have enough gold to fill a %s." % itemName)
                            else:
                                if fill_count > 1:
                                    print("You don't have",fill_count,"flasks to fill.")
                                    notTurn = True
                                else:
                                    print("You don't have any flasks to fill.")
                                    notTurn = True

            else:
                # If not isFill
                print("You cannot fill anything here.")
                notTurn = True
        # Take, Get
        # Move items from room to inventory
        elif option.startswith("take") or option.startswith("get") or option.startswith("pick up") or option.startswith("grab"):
            if option.startswith("take"):
                option = option[5:]
            elif option.startswith("get"):
                option = option[4:]
            elif option.startswith("pick up"):
                option = option[9:]
            elif option.startswith("grab"):
                option = option[5:]
            # All/Everything
            if option in ("all","everything") and roomCurrent.itemTypes():
                if roomID == "roomTempleBasement" and not roomCurrent.characterDead and roomCurrent.staff:
                    print("\"You are not worthy to take the staff!\" The figure zaps you with a bolt of electricity.")
                    stat.health -= 1
                elif roomID == "roomLairMid" and roomCurrent.counter_2 and not creatureLairChase and roomCurrent.staff and roomCurrent.pickaxe:
                    creatureLairChase = True
                    creatureLairChaseCounter -= 1 # To make it balanced with taking them individually
                    roomCurrent.counter_2 = 0
                    roomCurrent.counter_1 = 0
                    inv.staff += roomCurrent.staff
                    inv.pickaxe += roomCurrent.pickaxe
                    roomCurrent.staff = 0
                    roomCurrent.pickaxe = 0
                    print("Your arm brushes up against the creature as you take the staff and pickaxe, awakening it.")
                    if oilCounter or lightCounter:
                        if oilCounter:
                            source = "your lantern"
                        else:
                            source = "the ball of light"
                        print("Luckily, the light from %s blinds it, giving you some extra time." % source)
                elif roomID == "roomLairMid" and roomCurrent.counter_2 and not creatureLairChase and roomCurrent.staff:
                    creatureLairChase = True
                    roomCurrent.counter_2 = 0
                    inv.staff += roomCurrent.staff
                    roomCurrent.staff = 0
                    print("Your arm brushes up against the creature as you take the staff, awakening it.")
                    if oilCounter or lightCounter:
                        if oilCounter:
                            source = "your lantern"
                        else:
                            source = "the ball of light"
                        print("Luckily, the light from %s blinds it, giving you some extra time." % source)
                elif roomID == "roomLairMid" and roomCurrent.counter_1 and not creatureLairChase and roomCurrent.pickaxe:
                    creatureLairChase = True
                    roomCurrent.counter_1 = 0
                    inv.pickaxe += roomCurrent.pickaxe
                    roomCurrent.pickaxe = 0
                    print("Your arm brushes up against the creature as you take the pickaxe, awakening it.")
                    if oilCounter or lightCounter:
                        if oilCounter:
                            source = "your lantern"
                        else:
                            source = "the ball of light"
                        print("Luckily, the light from %s blinds it, giving you some extra time." % source)
                else:
                    inv.gold += roomCurrent.gold
                    inv.letter += roomCurrent.letter
                    inv.key += roomCurrent.key
                    inv.keySkeleton += roomCurrent.keySkeleton
                    inv.pickaxe += roomCurrent.pickaxe
                    inv.shrubbery += roomCurrent.shrubbery
                    inv.funnelCake += roomCurrent.funnelCake
                    inv.halfFunnelCake += roomCurrent.halfFunnelCake
                    inv.foot += roomCurrent.foot
                    inv.porridge += roomCurrent.porridge
                    inv.bowl += roomCurrent.bowl
                    inv.lantern += roomCurrent.lantern
                    inv.oil += roomCurrent.oil
                    inv.pie += roomCurrent.pie
                    inv.biscuit += roomCurrent.biscuit
                    inv.hook += roomCurrent.hook
                    inv.staff += roomCurrent.staff
                    inv.ticket += roomCurrent.ticket
                    inv.coal += roomCurrent.coal
                    inv.potato += roomCurrent.potato
                    inv.bandage += roomCurrent.bandage
                    inv.journal += roomCurrent.journal
                    inv.book += roomCurrent.book
                    inv.brie += roomCurrent.brie
                    inv.munster += roomCurrent.munster
                    inv.stilton += roomCurrent.stilton
                    inv.swiss += roomCurrent.swiss
                    inv.wensleydale += roomCurrent.wensleydale
                    inv.potion += roomCurrent.potion
                    inv.flask += roomCurrent.flask
                    inv.stone += roomCurrent.stone
                    roomCurrent.gold = 0
                    roomCurrent.letter = 0
                    roomCurrent.key = 0
                    roomCurrent.keySkeleton = 0
                    roomCurrent.pickaxe = 0
                    roomCurrent.shrubbery = 0
                    roomCurrent.funnelCake = 0
                    roomCurrent.halfFunnelCake = 0
                    roomCurrent.foot = 0
                    roomCurrent.porridge = 0
                    roomCurrent.bowl = 0
                    roomCurrent.lantern = 0
                    roomCurrent.oil = 0
                    roomCurrent.pie = 0
                    roomCurrent.biscuit = 0
                    roomCurrent.hook = 0
                    roomCurrent.staff = 0
                    roomCurrent.ticket = 0
                    roomCurrent.coal = 0
                    roomCurrent.potato = 0
                    roomCurrent.bandage = 0
                    roomCurrent.journal = 0
                    roomCurrent.book = 0
                    roomCurrent.brie = 0
                    roomCurrent.munster = 0
                    roomCurrent.stilton = 0
                    roomCurrent.swiss = 0
                    roomCurrent.wensleydale = 0
                    roomCurrent.potion = 0
                    roomCurrent.flask = 0
                    roomCurrent.stone = 0
                    print("Taken.")
            # Gold # continue add quantities like in buy and sell
            elif option.endswith("gold") and roomCurrent.gold:
                # option = option[]
                inv.gold += roomCurrent.gold
                roomCurrent.gold = 0
                print("Taken.")
            # Letter
            elif option == "letter" and roomCurrent.letter:
                inv.letter += roomCurrent.letter
                roomCurrent.letter = 0
                print("Taken.")
            # Key
            elif option == "key" and (roomCurrent.key or roomCurrent.keySkeleton):
                if roomID == "roomBook_3_End" and not roomBook_3_End.itemFound:
                    roomBook_3_End.itemFound = True
                inv.key += roomCurrent.key
                inv.keySkeleton += roomCurrent.keySkeleton
                roomCurrent.key = 0
                roomCurrent.keySkeleton = 0
                print("Taken.")
            # Skeleton key
            elif option == "key of am'domosh" and roomCurrent.keySkeleton:
                if roomID == "roomBook_3_End" and not roomBook_3_End.itemFound:
                    roomBook_3_End.itemFound = True
                inv.keySkeleton += roomCurrent.keySkeleton
                roomCurrent.keySkeleton = 0
                print("Taken.")
            # Pickaxe
            elif option in ("pickaxe","pick") and roomCurrent.pickaxe:
                if roomID == "roomLairMid" and roomCurrent.counter_1 and not creatureLairChase:
                    creatureLairChase = True
                    roomCurrent.counter_1 = 0
                    inv.pickaxe += roomCurrent.pickaxe
                    roomCurrent.pickaxe = 0
                    print("Your arm brushes up against the creature as you take the pickaxe, awakening it.")
                    if oilCounter or lightCounter:
                        if oilCounter:
                            source = "your lantern"
                        else:
                            source = "the ball of light"
                        print("Luckily, the light from %s blinds it, giving you some extra time." % source)
                else:
                    print("Taken.")
                inv.pickaxe += roomCurrent.pickaxe
                roomCurrent.pickaxe = 0
            # shrubbery
            elif option in ("shrubberies","shrubbery") and roomCurrent.shrubbery:
                inv.shrubbery += roomCurrent.shrubbery
                roomCurrent.shrubbery = 0
                print("Taken.")
            # Funnel cake
            elif option in ("cakes","cake","funnel cakes","funnel cake") and roomCurrent.funnelCake > 0:
                inv.funnelCake += roomCurrent.funnelCake
                roomCurrent.funnelCake = 0
                print("Taken.")
            # Half-eaten funnel cake
            elif option in ("half-eaten cake","half cake","half-eaten funnel cake","half eaten funnel cake","half funnel cake","half-eaten cakes","half cakes","half-eaten funnel cakes","half eaten funnel cakes","half funnel cakes") and roomCurrent.halfFunnelCake:
                inv.halfFunnelCake += roomCurrent.halfFunnelCake
                roomCurrent.halfFunnelCake = 0
                print("Taken.")
            # Lucky rabbit foot
            elif option in ("lucky rabbit foot","rabbit","rabbit foot","foot","lucky rabbit","lucky foot","lucky rabbit foot","rabbit foot","lucky rabbit feet","rabbit feet","feet","lucky feet","lucky rabbit feet","rabbit feet") and roomCurrent.foot:
                inv.foot += roomCurrent.foot
                roomCurrent.foot = 0
                print("Taken.")
            # Bowl of porridge
            elif option in ("porridge","bowl of porridge","bowl","porridge bowl") and (roomCurrent.porridge or roomCurrent.bowl):
                if option in ("porridge", "bowl of porridge") and not roomCurrent.porridge:
                    print("There is no %s here." % option)
                    notTurn = True
                else:
                    inv.porridge += roomCurrent.porridge
                    roomCurrent.porridge = 0
                    if option in ("bowl", "porridge bowl"):
                        inv.bowl += roomCurrent.bowl
                        roomCurrent.bowl = 0
                    print("Taken.")
            # Lantern
            elif option == "lantern" and roomCurrent.lantern:
                inv.lantern += roomCurrent.lantern
                roomCurrent.lantern = 0
                print("Taken.")
            # Vial of lantern oil
            elif option in ("oil","vial","vials","vial of lantern oil","vials of lantern oil","vial of oil","vials of oil","lantern oil") and roomCurrent.oil:
                inv.oil += roomCurrent.oil
                roomCurrent.oil = 0
                print("Taken.")
            # Chicken poe pie
            elif option in ("pies","pie","chicken","chicken pot","chicken pot pies","chicken pot pies") and roomCurrent.pie:
                inv.pie += roomCurrent.pie
                roomCurrent.pie = 0
                print("Taken.")
            # Hardtack biscuit
            elif option in ("hardtack biscuit","hardtack biscuits","biscuit","biscuits","hardtack") and roomCurrent.biscuit:
                inv.biscuit += roomCurrent.biscuit
                roomCurrent.biscuit = 0
                print("Taken.")
            # Coil of hook
            elif option in ("grappling hook","hook") and roomCurrent.hook:
                inv.hook += roomCurrent.hook
                roomCurrent.hook = 0
                print("Taken.")
            # The staff of Garrotxa
            elif option in ("the staff of garrotxa","staff of garrotxa","staff") and roomCurrent.staff:
                if roomID == "roomTempleBasement" and not roomCurrent.characterDead:
                    print("\"You are not worthy to take the staff!\" The figure zaps you with a bolt of electricity.")
                    stat.health -= 1
                elif roomID == "roomLairMid" and roomCurrent.counter_2 and not creatureLairChase:
                    creatureLairChase = True
                    roomCurrent.counter_2 = 0
                    inv.staff += roomCurrent.staff
                    roomCurrent.staff = 0
                    print("Your arm brushes up against the creature as you take the staff, awakening it.")
                    if oilCounter or lightCounter:
                        if oilCounter:
                            source = "your lantern"
                        else:
                            source = "the ball of light"
                        print("Luckily, the light from %s blinds it, giving you some extra time." % source)
                else:
                    inv.staff += roomCurrent.staff
                    roomCurrent.staff = 0
                    print("Taken.")
            # Raffle ticket
            elif option in ("ticket","raffle ticket") and roomCurrent.ticket:
                inv.ticket += roomCurrent.ticket
                roomCurrent.ticket = 0
                print("Taken.")
            # Piece of coal
            elif option in ("coal","pieces of coal") and roomCurrent.coal:
                inv.coal += roomCurrent.coal
                roomCurrent.coal = 0
                print("Taken.")
            # Potato
            elif option == "potato" and roomCurrent.potato:
                inv.potato += roomCurrent.potato
                roomCurrent.potato = 0
                print("Taken.")
            # Bandage
            elif option in ("bandages","bandage") and roomCurrent.bandage:
                inv.bandage += roomCurrent.bandage
                roomCurrent.bandage = 0
                print("Taken.")
            # Journal
            elif option in ("the journal","journal") and roomCurrent.journal:
                inv.journal += roomCurrent.journal
                roomCurrent.journal = 0
                print("Taken.")
            # Mysterious book
            elif option in ("book","mysterious book") and roomCurrent.book:
                inv.book += roomCurrent.book
                roomCurrent.book = 0
                print("Taken.")
             # Cheeses
            elif option in ("brie","slice of brie","slice of brie cheese", "brie cheese"):
                inv.brie += roomCurrent.brie
                roomCurrent.brie = 0
                print("Taken.")
            elif option in ("munster","slice of munster","slice of munster cheese","munster cheese"):
                inv.munster += roomCurrent.munster
                roomCurrent.munster = 0
                print("Taken.")
            elif option in ("stilton","slice of stilton","slice of stilton cheese","stilton cheese"):
                inv.stilton += roomCurrent.stilton
                roomCurrent.stilton = 0
                print("Taken.")
            elif option in ("swiss","slice of swiss","slice of swiss cheese","swiss cheese"):
                inv.swiss += roomCurrent.swiss
                roomCurrent.swiss = 0
                print("Taken.")
            elif option in ("wensleydale","slice of wensleydale","slice of wensleydale cheese","wensleydale cheese"):
                inv.wensleydale += roomCurrent.wensleydale
                roomCurrent.wensleydale = 0
                print("Taken.")
            # Potion
            elif option in ("potion","potion of rejuvination","potions","potions of rejuvination"):
                inv.potion += roomCurrent.potion
                roomCurrent.potion = 0
                print("Taken.")
            # Flask
            elif option in ("flask","empty flask","flasks","empty flasks"):
                inv.flask += roomCurrent.flask
                roomCurrent.flask = 0
            # Dragonstone
            elif option in ("stone","dragonstone"):
                inv.stone += roomCurrent.stone
                roomCurrent.stone = 0
                print("Taken.")
            elif option == "":
                print("Take what?")
                notTurn = True
            else:
                if option in ("all","everything"):
                    print("You cannot take anything here.")
                else:
                    print("You cannot take any %s here." % option)
                notTurn = True
        # Drop
        elif option.startswith("drop"):
            option = option[5:].lower()
            # All/Everything
            if option in ("all","everything") and inv.itemTypes():
                roomCurrent.gold += inv.gold
                roomCurrent.letter += inv.letter
                roomCurrent.key += inv.key
                roomCurrent.keySkeleton += inv.keySkeleton
                roomCurrent.pickaxe += inv.pickaxe
                roomCurrent.shrubbery += inv.shrubbery
                roomCurrent.funnelCake += inv.funnelCake
                roomCurrent.halfFunnelCake += inv.halfFunnelCake
                roomCurrent.foot += inv.foot
                roomCurrent.porridge += inv.porridge
                roomCurrent.bowl += inv.bowl
                roomCurrent.lantern += inv.lantern
                roomCurrent.oil += inv.oil
                roomCurrent.pie += inv.pie
                roomCurrent.biscuit += inv.biscuit
                roomCurrent.hook += inv.hook
                roomCurrent.staff += inv.staff
                roomCurrent.ticket += inv.ticket
                roomCurrent.coal += inv.coal
                roomCurrent.potato += inv.potato
                roomCurrent.bandage += inv.bandage
                roomCurrent.journal += inv.journal
                roomCurrent.book += inv.book
                roomCurrent.brie += inv.brie
                roomCurrent.munster += inv.munster
                roomCurrent.stilton += inv.stilton
                roomCurrent.swiss += inv.swiss
                roomCurrent.wensleydale += inv.wensleydale
                roomCurrent.potion += inv.potion
                roomCurrent.flask += roomCurrent.flask
                roomCurrent.stone += roomCurrent.stone
                inv = Inventory()
                print("Dropped.")
            # Gold
            elif option == "gold" and inv.gold:
                roomCurrent.gold += inv.gold
                inv.gold = 0
                print("Dropped.")
            # Letter
            elif option == "letter" and inv.letter:
                roomCurrent.letter += inv.letter
                inv.letter = 0
                print("Dropped.")
            # Key
            elif option == "key" and (inv.key or inv.keySkeleton):
                roomCurrent.key += inv.key
                roomCurrent.keySkeleton += inv.keySkeleton
                inv.key = 0
                inv.keySkeleton = 0
                print("Dropped.")
            # Skeleton key
            elif option == "key of ahm'domosh" and inv.keySkeleton:
                roomCurrent.keySkeleton += inv.keySkeleton
                inv.keySkeleton = 0
                print("Dropped.")
            # Pickaxe
            elif option in ("pickaxe","pick") and inv.pickaxe:
                roomCurrent.pickaxe += inv.pickaxe
                inv.pickaxe = 0
                print("Dropped.")
            # shrubbery
            elif option in ("shrubberies","shrubbery") and inv.shrubbery:
                roomCurrent.shrubbery += inv.shrubbery
                inv.shrubbery = 0
                print("Dropped.")
            # Funnel cakes
            elif option in ("cakes","cake","funnel cakes","funnel cake") and inv.funnelCake:
                roomCurrent.funnelCake += inv.funnelCake
                inv.funnelCake = 0
                print("Dropped.")
            # Half-eaten funnel cakes
            elif option in ("half-eaten cake","half cake","half-eaten funnel cake","half eaten funnel cake","half funnel cake","half-eaten cakes","half cakes","half-eaten funnel cakes","half eaten funnel cakes","half funnel cakes") and inv.halfFunnelCake:
                roomCurrent.halfFunnelCake += inv.halfFunnelCake
                inv.halfFunnelCake = 0
                print("Dropped.")
            # Lucky rabbit foot
            elif option in ("lucky rabbit foot","rabbit","rabbit foot","foot","lucky rabbit","lucky foot","lucky rabbit foot","rabbit foot","lucky rabbit feet","rabbit feet","feet","lucky feet","lucky rabbit feet","rabbit feet") and inv.foot:
                roomCurrent.foot += inv.foot
                inv.foot = 0
                print("Dropped.")
            # Bowl of porridge
            elif option in("porridge","bowl of porridge","bowl","porridge bowl") and (inv.porridge or inv.bowl):
                if option in ("porridge", "bowl of porridge") and not inv.porridge:
                    print("You have no %s to drop." % option)
                    notTurn = True
                else:
                    roomCurrent.porridge += inv.porridge
                    inv.porridge = 0
                    if option in ("bowl", "porridge bowl"):
                        roomCurrent.bowl += inv.bowl
                        inv.bowl = 0
                    print("Dropped")
            # Lantern
            elif option == "lantern" and inv.lantern:
                roomCurrent.lantern += inv.lantern
                inv.lantern = 0
                print("Dropped.")
            # Vial of lantern oil
            elif option in ("oil","vial","vials","vial of lantern oil","vials of lantern oil","vial of oil","vials of oil","lantern oil") and inv.oil:
                roomCurrent.oil += inv.oil
                inv.oil = 0
                print("Dropped.")
            # Chicken pot pie
            elif option in ("pies","pie","chicken","chicken pot","chicken pot pies","chicken pot pies") and inv.pie:
                roomCurrent.pie += inv.pie
                inv.pie = 0
                print("Dropped.")
            # Hardtack biscuit
            elif option in ("hardtack biscuit","hardtack biscuits","biscuit","biscuits","hardtack") and inv.biscuit:
                roomCurrent.biscuit += inv.biscuit
                inv.biscuit = 0
                print("Dropped.")
            # Coil of hook
            elif option in ("grappling hook","hook") and inv.hook:
                roomCurrent.hook += inv.hook
                inv.hook = 0
                print("Dropped.")
            # The staff of Garrotxa
            elif option in ("the staff of garrotxa","staff of garrotxa","staff") and inv.staff:
                roomCurrent.staff += inv.staff
                inv.staff = 0
                print("Dropped.")
            # Raffle ticket
            elif option in ("ticket","raffle ticket") and inv.ticket:
                roomCurrent.ticket += inv.ticket
                inv.ticket = 0
                print("Dropped.")
            # Piece of coal
            elif option in ("coal","pieces of coal") and inv.coal:
                roomCurrent.coal += inv.coal
                inv.coal = 0
                print("Dropped.")
            # Potato
            elif option in ("potatoes","potatos","potato") and inv.potato:
                roomCurrent.potato += inv.potato
                inv.potato = 0
                print("Dropped.")
            # Bandage
            elif option in ("bandages","bandage") and inv.bandage:
                roomCurrent.bandage += inv.bandage
                inv.bandage = 0
                print("Dropped.")
            # Journal
            elif option in ("the journal","journal") and inv.journal:
                roomCurrent.journal += inv.journal
                inv.journal = 0
                print("Dropped.")
            # Mysterious book
            elif option in ("book","mysterious book") and inv.book:
                roomCurrent.book += inv.book
                inv.book = 0
                print("Dropped.")
            # Cheese
            elif option in ("brie","slice of brie","slice of brie cheese", "brie cheese") and inv.brie:
                roomCurrent.brie += inv.brie
                inv.brie = 0
                print("Dropped")
            elif option in ("munster","slice of munster","slice of munster cheese","munster cheese") and inv.munster:
                roomCurrent.munster += inv.munster
                inv.munster = 0
                print("Dropped.")
            elif option in ("stilton","slice of stilton","slice of stilton cheese","stilton cheese") and inv.stilton:
                roomCurrent.stilton += inv.stilton
                inv.stilton = 0
                print("Dropped.")
            elif option in ("swiss","slice of swiss","slice of swiss cheese","swiss cheese") and inv.swiss:
                roomCurrent.swiss += inv.swiss
                inv.swiss = 0
                print("Dropped.")
            elif option in ("wensleydale","slice of wensleydale","slice of wensleydale cheese","wensleydale cheese") and inv.wensleydale:
                roomCurrent.wensleydale += inv.wensleydale
                inv.wensleydale = 0
                print("Dropped.")
            # Potion
            elif option in ("potion","potion of rejuvination","potions","potions of rejuvination") and inv.potion:
                roomCurrent.potion += inv.potion
                inv.potion = 0
                print("Dropped.")
            # Flask
            elif option in ("flask","empty flask","flasks","empty flasks") and inv.flask:
                roomCurrent.flask += inv.flask
                inv.flask = 0
                print("Dropped.")
            # Dragonstone
            elif option in ("stone","dragonstone") and inv.stone:
                roomCurrent.stone += inv.stone
                inv.stone = 0
                print("Dropped.")
            elif option == "":
                print("Drop what?")
                notTurn = True
            else:
                if option in ("all","everything"):
                    print("You have nothing to drop.")
                else:
                    print("You have no %s to drop." % option)
                notTurn = True
        # Hurt, Punch, Kick
        elif option.startswith("punch") or option.startswith("kick"):
            hurt = False
            if option.startswith("punch"):
                if option == "punch":
                    print("Punch what?")
                    notTurn = True
                else:
                    option = option[6:]
                    hurt = "punch"
            elif option.startswith("kick"):
                if option == "kick":
                    print("Kick what?")
                    notTurn = True
                else:
                    option = option[5:]
                    hurt = "kick"
            if hurt:
                if option in ("self","myself","me"):
                    if hurt == "punch":
                        print("You punch yourself.")
                        stat.health -= 1
                    else:
                        print("You try to kick yourself, however that would work, and quickly get frustrated. You end up kicking the back of one of your calves with your other foot, causing you to fall over and hit your head.")
                        stat.health -= 1
                elif roomID == "roomCarnivalFood":
                    if option in ("vendor","man","the vendor","the man"):
                        print("You %s the vendor.\n\nHe panicly shouts, \"Help, I'm being attacked!\" Town guards enter the tent and beat you to death." % hurt)
                        endGame = True
                    else:
                        print("You can't %s that." % hurt)
                elif roomID == "roomCarnivalShellGame":
                    if option in ("sybil","woman","old woman"):
                        print("You %s Sybil.\n\nShe falls over on her back and yells, \"Azara Telkathena!\" Green lightning shoots out from her hands, frying you to death." % hurt)
                        endGame = True
                    else:
                        print("You can't %s that." % hurt)
                elif roomID == "roomGeneralStore":
                    if option in ("shopkeeper","man","the shopkeeper","the man"):
                        print("You %s the shopkeeper. \n\n\"What do you think you're doing?\" He grabs your arm and breaks it. \"That'll teach you a lesson.\"")
                        endGame = True
                # add interactions with all rooms with people
                elif roomID == "roomBridge":
                    if option in ("troll","the troll","ugg"):
                        if roomCurrent.characterDead:
                            print("You %s the troll's charred remains. Your hand gets covered in ash.")
                        else:
                            print("You %s the troll. He gets angry and punches you back, breaking one of your ribs." % hurt)
                            stat.health -= 1
                    else:
                        print("You can't %s that." % hurt)
                elif roomID == "roomTempleInside":
                    if option in ("ghostly figure","ghost","figure"):
                        if roomCurrent.characterDead:
                            print("There's no %s here to %s." % (option,hurt))
                        else:
                            print("You %s the ghostly figure, going right through it. \"Do not think you can defeat me with mere physical attacks.\" It zaps you, cutting open a wound in your chest." % hurt)
                            stat.health -= 1
                else:
                    print("You cannot",hurt,"that.")
                    notTurn = True

        # Filler commands
        # Do nothing important
        # Say, Yell, Shout, Scream
        elif option.startswith("say") or option.startswith("yell") or option.startswith("shout") or option.startswith("scream"):
            spell = False
            valid = False
            say = False
            if silenced:
                print("You try to speak but cannot say anything.")
            else:
                if option.startswith("say"):
                    option = option[4:5].upper() + option[5:]
                    if option.startswith("\""):
                        option = option[1:2].upper() + option[2:]
                    say = True
                elif option.startswith("yell"):
                    option = option[5:].upper()
                elif option.startswith("shout"):
                    option = option[6:].upper()
                elif option.startswith("scream"):
                    option = option[7:].upper()
                option = option.strip("\"").strip("\'")
                if askName:
                    mynameis = False
                    iam = False
                    im = False
                    if option.lower().startswith("my name is "):
                        option2 = option[11:].title()
                        mynameis = True
                    elif option.lower().startswith("i am "):
                        option2 = option[5:].title()
                        iam = True
                    elif option.lower().startswith("i'm "):
                        option2 = option[4:].title()
                        im = True
                    else:
                        option2 = option.title()
                    if mynameis:
                        option = "My name is " + option2
                    elif iam:
                        option = "I am " + option2
                    elif im:
                        option = "I'm " + option2
                    else:
                        option = option2
                    stat.name = option2
                if option == "":
                    print("Say what?")
                    notTurn = True
                elif say:
                    print("You say, \"%s.\"" % option)
                    option = option.lower()
                else:
                    print('You shout "%s!" at the top of your lungs!' % option)
                    option = option.lower()
                # Game responsive
                if option in ("raan mir tah", "laas yah nir", "mid vur shaan", "feim zii gron", "gol hah dov", "od ah viing", "lok vah koor", "ven gaar nos", "zun haal viik", "faas ru maar", "mul qah diiv", "joor zah frul", "gaan lah haas", "yol toor shul", "fo krah diin", "liz slen nus", "kaan drem ov", "krii lun aus", "rii vaaz zol", "tiid klo ul", "strun bah qo", "dur neh viir", "zul mey gut", "fus ro dah", "wuld nah kest", "zii los dii du", "slen tiid vo", "nahl dal vus", "diil qoth zaam", "ven mul riik", "riik lo sah"):
                    print("You promptly remind yourself this is not Skyrim.")
                # Room dependent
                elif roomID == "roomTempleBasement":
                    if not roomCurrent.characterDead:
                        if stat.health < stat.healthmax:
                            heal = "Your wounds are magically healed. "
                        else:
                            heal = ""
                        if option in riddle_answer:
                            print("\"Good. You have proven yourself worthy. The staff is yours.\" %sThe figure fades away." % heal)
                            stat.health = stat.healthmax
                            roomCurrent.characterDead = True
                            # Roquefort message
                            roomTempleEntrance.firstTime = True
                            # Black knight spawn
                            roomRoadCorner.counter_1 = 1
                            roomRoadCorner.characterDead = False
                            roomRoadCorner.westBlocked = True
                        else:
                            if option.startswith("ozh ensh") or option in ("ozh vo'ses sa", "izh vo'poz", "ozh groth sol", "ozh vo'irush", "ozh gluth nith", "ozh gluth izh sol"):
                                print("\"You dare speak that devilish tongue in this sacred temple? Think again, Ozkavosh fiend.\" The figure zaps you with a bolt of electricity.")
                                option = ""
                                endGame = True
                            else:
                                print("\"Incorrect.\" The figure zaps you with a bolt of electricity.")
                                stat.health -= 1
                # Spells
                if option.startswith("ozh ensh") or option in ("ozh vo'ses sa","izh vo'poz","ozh thok alatho","ozh groth sol","ozh vo'irush","ozh gluth nith","ozh gluth izh sol","ozh sol fek"):
                    spell = True
                # Learn (I learn)
                # roomLake
                if option.startswith("ozh ensh"):
                    option = option[9:].lower()
                    if option == "ozh ensh":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I learn.\n    Spell - Translates the meaning and effects of words from Ozkavosh.")
                        valid = True
                        spell_learn = 2
                    elif option == "ozh vo'ses sa":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I unlock this.\n    Spell - Opens locks.")
                        valid = True
                        spell_unlock = 2
                    elif option == "izh vo'poz":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    You have no power.\n    Spell - Persuades those blocking the way.")
                        valid = True
                        spell_persuade = 2
                    elif option == "ozh thok alatho":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I move forward\n    Spell - Crosses large gaps.")
                        valid = True
                        spell_jump = 2
                    elif option == "ozh groth sol":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I open the light.\n    Spell - Creates a glowing ball that illuminates your surroundings.")
                        valid = True
                        spell_light = 2
                    elif option == "ozh vo'irush":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I am without illness.\n    Spell - Mends all wounds and illnesses.")
                        valid = True
                        spell_heal = 2
                    elif option == "ozh gluth nith":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I consume the Earth.\n    Spell - Satifsies all hunger.")
                        valid = True
                        spell_feed = 2
                    elif option == "ozh gluth izh sol":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I consume your soul.\n    Spell - Kills your enemies.")
                        valid = True
                        spell_kill = 2
                    elif option == "ozh sol fek":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    My life ends.\n    Spell - Kills self.")
                        valid = True
                        spell_killself = 2
                    # Not spells
                    # Not used currently
                    elif option == "eyik vo'hollom":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Behold oblivion.")
                        valid = True
                        word_oblivion = 2
                    # roomShrine.firstTime
                    elif option == "omoz gloth izh":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Darkness welcomes you.")
                        valid = True
                        word_darkness = 2
                    # roomHouseOffice
                    # Vesh'kathal says after Eden dies in dialogue (either you kill or she makes you kill him)
                    elif option == "ozkavosh icha domosh sa nith":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Demon-kind shall reign upon this land.")
                        valid = True
                        word_reign = 2
                    elif option == "izh icha vo'fek ozh domosh":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    You will not stop my reign.")
                        valid = True
                        word_stop = 2
                    elif option == "ahm'fol":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Servant of Vesh'arkosh")
                        valid = True
                        word_servant = 2
                    elif option == "sof izh":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Curse you.")
                        valid = True
                        word_curse = 2
                    # roomBookMirror
                    # Hints the puzzle solution
                    elif option == "izh tal et ozh icha rek'tal":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Talk and I will mirror.")
                        valid = True
                        word_mirror = 2
                    elif option == "ahm'domosh":
                        print("    Highest dominion.")
                        valid = True
                        word_dominion = 2
                    if valid:
                        spell_learn = 2
                # Unlock (I unhide this)
                # roomLake after saving injured stranger
                elif option == "ozh vo'ses sa":
                    if roomID in ("roomJailCell","roomJailCorridor","roomRoad2North","roomHouseEntrance") and roomCurrent.northBlocked or roomCurrent.eastBlocked or roomCurrent.southBlocked or roomCurrent.westBlocked or (roomID == "roomLake" and not roomCurrent.counter_1):
                        print("You feel a surge of energy emit from the utterance of your words into the lock, unlocking it.")
                        if roomCurrent.northBlocked:
                            print("You can now go North.")
                            roomCurrent.northBlocked = False
                        if roomCurrent.eastBlocked:
                            print("You can now go East.")
                            roomCurrent.eastBlocked = False
                        if roomCurrent.westBlocked:
                            print("You can now go West.")
                            roomCurrent.southBlocked = False
                        if roomCurrent.southBlocked:
                            print("You can now go South.")
                            roomCurrent.westBlocked = False
                        if roomID == "roomJailCell":
                            if not roomCurrent.counter_1 or inv.letter:
                                roomJailCorridor.letter += 1
                            if not stat.name:
                                stat.name = "the hero of Kashkaval"
                            inv.letterRead = "It reads:\n\nTo " + stat.name + ",\n\nWord has gotten out that a certain Eden Von Roquefort has set up residence north of Mount Magna. While he purports to be a lowly cheese mage, reliable sources claim him to be the demon lord, Vesh'kathal the Deceiver, a shapeshifter infamous of manipulating the minds and bending the wills of others. Legend tells of a saviour, deemed the Monterey Messiah, who will save all of Kashkaval from his wickedness. It has be brought to my attention that you are that saviour that the legends speak of. While I have very important matters to attend to, the best I can do is help instruct you in how to defeat this demon.\n\nFIRST, you must acquire the staff from the Garrotxian temple northeast of this town, for it is the only weapon capable of defeating such a powerful demon.\n\nNEXT, once you have the staff, go north, through the mines of Mount Magna and find him at his house on the other end.\n\nFINALLY, kill Roquefort and Kashkaval will be saved from his wrath.\n\nI know this is probably a lot to digest at once, but you are our only hope. I fear in your attempt to complete this task, Vesh'kathal will attempt to thwart you. He may attempt to contact and manipulate you, or have his minions work to stop you. Either way, you must persevere.\n\nMay you be blessed by the will of Garrotxa,\n\nA friend\n\nP.S. The code to the vault door is %i%i%i%i." % (roomJailCorridor.counterAns_1, roomJailCorridor.counterAns_2, roomJailCorridor.counterAns_3, roomJailCorridor.counterAns_4)
                        if roomID == "roomLake":
                            roomCurrent.counter_1 = 1
                        valid = True
                        spell_unlock = 2

                # Persuade (You have no power)
                # roomRoadCorner after defeating black knight
                elif option == "izh vo'poz":
                    if roomID in ("roomGate","roomBridge","roomTempleInside","roomRoadCorner"):
                        # roomGate
                        if roomID == "roomGate" and roomCurrent.northBlocked:
                            print("You feel a surge of energy emit from the utterance of your words towards the guard.")
                            print("The guard is convinced you gave him his shrubbery and lets you through the gate.")
                            roomGate.northBlocked = False
                            roomGate.isGive = False
                            valid = True
                        # roomBridge
                        elif roomID == "roomBridge" and roomCurrent.eastBlocked:
                            print("You feel a surge of energy emit from the utterance of your words towards the troll.")
                            print('The troll is convinced you gave him all the funnel cakes in the world and goes back under the bridge.')
                            roomBridge.eastBlocked = False
                            valid = True
                        # roomTempleInside
                        elif roomID == "roomTempleInside" and not roomCurrent.characterDead:
                            print("You feel a surge of energy emit from the utterance of your words towards the ghostly figure.")
                            valid = True
                        # roomRoadCorner
                        elif roomID == "roomRoadCorner" and not roomCurrent.characterDead:
                            print("You feel a surge of energy emit from the utterance of your words towards the ghostly figure.")
                            print("The black knights say, \"One shall pass.\" before walking off into the forest.")
                            roomCurrent.characterDead = True
                            valid = True
                        if valid:
                            spell_persuade = 2
                # Jump (I move forward)
                # roomLairEast
                elif option == "ozh thok alatho":
                    if roomID.endswith("crevasse"):
                        # roomCave_3_llm_crevasse
                        if roomID == "roomCave_3_llm_crevasse":
                            direction = "West"
                            roomID = roomCurrent.west
                            changeRoom = True
                            valid = True
                        elif roomID == "roomCave__3_lllm_treasure_crevasse":
                            direction = "East"
                            roomID = roomCurrent.east
                            changeRoom = True
                            valid = True
                        elif roomID == "roomCave_8_mr_crevasse":
                            direction = "north"
                            roomID = roomCurrent.north
                            changeRoom = True
                            valid = True
                        elif roomID == "roomCave_9_mr_crevasse":
                            direction = "south"
                            roomID = roomCurrent.south
                            changeRoom = True
                            valid = True
                        if valid:
                            spell_persuade = 2
                        print("You feel a great sense of power from the utterance of your words, causing you to jump across the crevasse.")
                # Light (I open light)
                # Obtained from Barn
                elif option == "ozh groth sol":
                    if lightCounter < LIGHT_DURATION:
                        if lightCounter:
                            print("You feel a surge of energy emit from the utterance of your words as the ball of light brightens.")
                        else:
                            print("You feel a surge of energy emit from the utterance of your words as a ball of light appears in front of you.")
                        lightCounter = LIGHT_DURATION
                    valid = True
                    spell_light = 2
                # Heal (I without illness)
                # Obtained at House/Never
                elif option == "ozh vo'irush":
                    if not stat.health == HEALTH_MAX:
                        stat.health = HEALTH_MAX
                        print("You feel a surge of energy emit from the utterance of your words into your wounds.")
                        valid = True
                        spell_heal = 2
                # Feed (I eat earth)
                # Obtained at House/Never
                elif option == "ozh gluth nith":
                    stat.hunger = HUNGER_MAX
                    print("You feel a surge of energy emit from the utterance of your words into your stomach.")
                    valid = True
                    spell_feed = 2
                # Kill (I eat your soul)
                # From losing the game at the end (implicit)
                elif option == "ozh gluth izh sol":
                    if not roomCurrent.characterDead or roomID.startswith("roomLair") or (roomID.startswith("roomCave") and creatureChaseCounter in [1,2]) or (roomID.startswith("roomJail") and jailGuardCounter in [1,2]):
                        roomCurrent.characterDead = True
                        print("You feel a surge of energy emit from the utterance of your words, consuming the soul of ", end = "")
                        valid = True
                        spell_kill = 2
                        if roomID == "roomCarnivalShellGame":
                            print("the old woman. As she topples over, all her gold is spilled across the ground.")
                            roomCurrent.isBet = False
                            roomCurrent.gold += invShell.gold
                            invShell.gold = 0
                            roomCurrent.itemsPresent()
                        elif roomID.startswith("roomJail"):
                            print("a jail guard. The rest surround you and take you down.")
                            endGame = True
                        elif roomID == "roomCarnivalWheelGame":
                            print("the man. The crowd goes into panic as guards enter the tent and overwhelm you.")
                            endGame = True
                        elif roomID == "roomCarnivalFood":
                            print("the vendor. He falls over, leaving his supply of food behind.")
                            roomCurrent.isBuy = False
                            roomCurrent.funnelCake += invFood.funnelCake
                            invFood.funnelCakes = 0
                            roomCurrent.pie += 10
                            roomCurrent.itemsPresent()
                        elif roomID == "roomBridge":
                            print("the troll. It falls over, dropping the funnel cakes in its hands.")
                            roomCurrent.eastBlocked = False
                            roomCurrent.funnelCake += 1
                            roomCurrent.halfFunnelCake += 3
                            roomCurrent.itemsPresent()
                        elif roomID == "roomGeneralStore":
                            print("the shopkeeper, leaving his store supplies behind.")
                            roomCurrent.isBuy = False
                            roomCurrent.isSell = False
                            roomCurrent.oil += 5
                            roomCurrent.foot += 5
                            roomCurrent.lantern += 1
                            roomCurrent.bandage += 5
                            roomCurrent.itemsPresent()
                        elif roomID == "roomBlacksmith":
                            print("the blacksmith, leaving his supplies behind.")
                            roomCurrent.isBuy = False
                            roomCurrent.isSell = False
                            roomCurrent.pickaxe += 2
                            roomCurrent.hook += 3
                            roomCurrent.itemsPresent()
                        elif roomID == "roomAlchemist":
                            print("Tim the Enchanter, leaving his supplies behind.")
                            roomCurrent.isBuy = False
                            roomCurrent.isFill = False
                            roomCurrent.isSell = False
                            roomCurrent.potion += 3
                            roomCurrent.flask += 4
                            roomCurrent.itemsPresent()
                        elif roomID in ("roomCarnival","roomCourtyardNorth","roomCourtyardSouth"):
                            print("a random civilian. The loud noise alerts the town guards, who catch up to you and kill you.")
                            endGame = True
                        elif roomID == "roomGate":
                            print("the gate guard. Another nearby guard one draws his sword and impales you.")
                            endGame = True
                        elif roomID == "roomRoadCorner" and roomRoadCorner.counter_1:
                            print("the black knight. He shouts, \"Izh vo'poz!\" before his body vaporizes into nothing.")
                            if not spell_persuade:
                                spell_persuade = 1
                            roomRoadCorner.westBlocked = False
                        elif roomID == "roomHouseOffice":
                            roomCurrent.counter_2 += 1
                            if roomCurrent.counter_2 == 1:
                                print("Eden Von Roquefort. His body flies back and crashes on the desk.")
                            elif roomCurrent.counter_2 == 2:
                                print("Vesh'kathal. She resists the shock and charges towards you, knocking the staff out of your hand. Merely grazing her arm against the holy staff causes her scales to burn, making her to topple over in pain.")
                                inv.staff -= 1
                                roomCurrent.staff += 1
                                roomCurrent.itemsPresent()
                            else:
                                print("Vesh'kathal. She is severly weaked, but manages to stay alive.")
                        elif roomID.startswith("roomCave"):
                            print("the creature, which resists the spell. It only gets angrier.")
                        elif roomID.startswith("roomLair"):
                            print("the creature, which resists the spell. It only gets angrier.")
                            if not creatureLairChase:
                                creatureLairChase = True
                        elif roomID == "roomLake":
                            print("the stranger, leaving his supplies behind.")
                            roomCurrent.pie += 1
                            roomCurrent.itemsPresent()
                # Kill self (My life ends)
                # From Journal
                elif option == "ozh sol fek":
                    print("You feel a surge of energy emit from the utterance of your words as your own heart stops.")
                    endGame = True
                    valid = True
                    spell_killself = 2
                if spell and not valid:
                    print("You feel a strange sense of energy flow throughout your body although nothing else happens.")

                # Responses
                if roomID == "roomRoadCorner" and not roomCurrent.characterDead and roomCurrent.counter_1 and option not in ("ozh gluth izh sol","izh vo'poz"):
                    print("The black knight stands silently, ignoring your words.")
                elif roomID == "roomBookMirror" and not roomCurrent.characterDead:
                    print("The gargoyle says, \"%s\" in return." % reverse_cap(option))
                    if option == "los hzi htulg hzo":
                        print("You are overwhelmed with pain and agony as you fall to your knees.")
                        endGame = True
                    elif option == "zop'ov hzi":
                        print("You stand motionless... forever.")
                        endGame = True
                    elif option == "los htorg hzo":
                        if not roomCurrent.counter_1:
                            print("A ball of light appears in the room.")
                        else:
                            print("The ball of light brightens.")
                        roomCurrent.counter_1
                    elif option == "kef los hzo":
                        print("It crumbles into pieces. A pedestal emerges from the floor, with a mysterious book on it.")
                        roomCurrent.characterDead = True
                        roomCurrent.itemFound = True
                        roomCurrent.book += 1
                        roomCurrent.itemsPresent()
        # Lie down
        elif option.startswith("lie down on") or option.startswith("lie down in"):
            option = option[11:]
            if option in ("floor","ground","back"):
                    print("You lie down. It'x pretty relaxing.")
            elif roomID == "roomJailCell":
                if option == "haystack":
                    print("You lie down. The haystack makes your back a bit itchy, but you've gotten used to it by now.")
            else:
                print("You can't do that.")
        elif option.endswith("lie down"):
            print("You lie down. It's pretty relaxing.")
        # Wait
        elif option.startswith("wait") or option == "z":
            print("Time passes.")
        # Do Nothing
        elif option == "do nothing":
            print("Nothing done.")
        # Dance
        elif option in ("dance","jig"):
            print("You have the time of your life.")
        # Sing
        elif option.startswith("sing"):
            option = option[5:]
            print('You sing, "%s" to your heart\'s content.')
        # Super powers
        elif option.startswith("fly") or option.startswith("teleport") or option.startswith("turn invisible") or option.startswith("breathe fire"):
            print("As cool as it would be to %s, you can't. Sorry you don't have super powers." % option)
        # Hug
        elif option.startswith("hug"):
            print("For super convoluted lore reasons, you can't %s. Something about an evil witch and ancient curses. Pretty deep stuff." % option)
        # Emoticons
        elif option == "d:":
            print("No need to be so sad.")
        elif option in (":)",":>)",":D"):
            print(":)")
        # Buy
        # Exhange money for items in shops
        elif option.startswith("buy") or option.startswith("purchase"):
            if option.startwith("buy"):
                option = option[4:]
            elif option.startswith("purchase"):
                optoin = option[9:]
            if roomCurrent.isBuy:
                if option == "":
                    print("Buy what?")
                    notTurn = True
                else:
                    buyPickaxe = False
                    buyFunnelCake = False
                    buyFoot = False
                    buyLantern = False
                    buyOil = False
                    buyPie = False
                    buyHook = False
                    buyBandage = False
                    buyTicket = False
                    buyPotion = False
                    # Checking what is being bought and how many
                    # Buy pickaxe
                    if option in ("pick","pickaxe","a pick","a pickaxe"):
                        buy_count = 1
                        buyPickaxe = True
                    elif option.endswith("pickaxe"):
                        buy_count = option[:len(option) - len("pickaxe") - 1]
                        buyPickaxe = True
                    elif option.endswith("pickaxes"):
                        buy_count = option[:len(option) - len("pickaxes") - 1]
                        buyPickaxe = True
                    elif option.endswith("pick"):
                        buy_count = option[:len(option) - len("pick") - 1]
                        buyPickaxe = True
                    elif option.endswith("picks"):
                        buy_count = option[:len(option) - len("picks") - 1]
                        buyPickaxe = True
                    # Buy funnel cake
                    elif option.startswith("funnel") or option.startswith("cake"):
                        buy_count = 1
                        buyFunnelCake = True
                    elif option.endswith("funnel cake"):
                        buy_count = option[:len(option) - len("funnel cake") - 1]
                        buyFunnelCake = True
                    elif option.endswith("funnel cakes"):
                        buy_count = option[:len(option) - len("funnel cakes") - 1]
                        buyFunnelCake = True
                    elif option.endswith("cake"):
                        buy_count = option[:len(option) - len("cake") - 1]
                        buyFunnelCake = True
                    elif option.endswith("cakes"):
                        buy_count = option[:len(option) - len("cakes") - 1]
                        buyFunnelCake = True
                    # Buy lucky rabbit foot
                    elif option.startswith("lucky") or option.startswith("rabbit") or option.startswith("foot") or option.startswith("feet"):
                        buy_count = 1
                        buyFoot = True
                    elif option.endswith("lucky rabbit foot") or option.endswith("lucky rabbit feet"):
                        buy_count = option[:len(option) - len("lucky rabbit foot") - 1]
                        buyFoot = True
                    elif option.endswith("lucky rabbit"):
                        buy_count = option[:len(option) - len("lucky rabbit") - 1]
                        buyFoot = True
                    elif option.endswith("lucky foot") or option.endswith("lucky feet"):
                        buy_count = option[:len(option) - len("lucky foot") - 1]
                        butFoot = True
                    elif option.endswith("foot") or option.endswith("feet"):
                        buy_count = option[:len(option) - len("foot") - 1]
                        buyFoot = True
                    # Buy lantern
                    elif option == "lantern":
                        buy_count = 1
                        buyLantern = True
                    elif option == "lanterns":
                        buy_count = 1
                        buyLantern = True
                    elif option.endswith("lantern"):
                        buy_count = option[:len(option) - len("lantern") - 1]
                        buyLantern = True
                    elif option.endswith("lanterns"):
                        buy_count = option[:len(option) - len("lanterns") - 1]
                        buyLantern = True
                    # Buy vial of lantern oil
                    elif option.startswith("vial") or option.startswith("oil") or option.startswith("lantern oil"):
                        buy_count = 1
                        buyOil = True
                    elif option.endswith("vials of lantern oil"):
                        buy_count = option[:len(option) - len("vials of lantern oil") - 1]
                        buyOil = True
                    elif option.endswith("vial of lantern oil"):
                        buy_count = option[:len(option) - len("vial of lantern oil") - 1]
                        buyOil =True
                    elif option.endswith("vial of oil"):
                        buy_count = option[:len(option) - len("vial of oil") - 1]
                        buyOil =True
                    elif option.endswith("vials of oil"):
                        buy_count = option[:len(option) - len("vials of oil") - 1]
                        buyOil =True
                    elif option.endswith("oil"):
                        buy_count = option[:len(option) - len("oil") - 1]
                        buyOil = True
                    elif option.endswith("vial"):
                        buy_count = option[:len(option) - len("vial") - 1]
                        buyOil =True
                    elif option.endswith("vials"):
                        buy_count = option[:len(option) - len("vials") - 1]
                        buyOil =True
                    # Buy pie
                    elif option.startswith("chicken") or option.startswith("pie"):
                        buy_count = 1
                        buyPie = True
                    elif option.endswith("chicken pot pie"):
                        buy_count = option[:len(option) - len("chicken pot pie") - 1]
                        buyPie = True
                    elif option.endswith("chicken pot pies"):
                        buy_count = option[:len(option) - len("chicken pot pies") - 1]
                        buyPie = True
                    elif option.endswith("chicken pie"):
                        buy_count = option[:len(option) - len("chicken pie") - 1]
                        buyPie = True
                    elif option.endswith("chicken pies"):
                        buy_count = option[:len(option) - len("chicken pies") - 1]
                        buyPie = True
                    elif option.endswith("pie"):
                        buy_count = option[:len(option) - len("pie") - 1]
                        buyPie = True
                    elif option.endswith("pies"):
                        buy_count = option[:len(option) - len("pies") - 1]
                        buyPie = True
                    # Buy grappling hook
                    elif option.startswith("grappling") or option.startswith("hook"):
                        buy_count = 1
                        buyHook = True
                    elif option.endswith("grappling hook"):
                        buy_count = option[:len(option) - len("grappling hook") - 1]
                        buyHook = True
                    elif option.endswith("grappling hooks"):
                        buy_count = option[:len(option) - len("grappling hooks") - 1]
                        buyHook = True
                    elif option.endswith("hook"):
                        buy_count = option[:len(option) - len("hook") - 1]
                        buyHook = True
                    elif option.endswith("hooks"):
                        buy_count = option[:len(option) - len("hooks") - 1]
                        buyHook = True
                    # Buy bandage
                    elif option in ("bandage","bandages"):
                        buy_count = 1
                        buyBandage = True
                    elif option.endswith("bandage"):
                        buy_count = option[:len(option) - len("bandage") - 1]
                        buyBandage = True
                    elif option.endswith("bandages"):
                        buy_count = option[:len(option) - len("bandages") - 1]
                        buyBandage = True
                    # Buy ticket
                    elif option in ("raffle ticket","ticket","raffle"):
                        buy_count = 1
                        buyTicket = True
                    elif option.endswith("raffle ticket"):
                        buy_count = option[:len(option) - len("raffle ticket") - 1]
                        buyTicket = True
                    elif option.endswith("ticket"):
                        buy_count = option[:len(option) - len("ticket") - 1]
                        buyTicket = True
                    elif option.endswith("raffle tickets"):
                        buy_count = option[:len(option) - len("raffle tickets") - 1]
                        buyTicket = True
                    elif option.endswith("tickets"):
                        buy_count = option[:len(option) - len("tickets") - 1]
                        buyTicket = True
                    # Potion
                    elif option in ("potion","potion of rejuvination"):
                        buy_count = 1
                        buyPotion = True
                    elif option.endswith("potion of rejuvination"):
                        buy_count = option[:len(option) - len("potion of rejuvination") - 1]
                        buyPotion = True
                    elif option.endswith("potions of rejuvination"):
                        buy_count = option[:len(option) - len("potions of rejuvination") - 1]
                        buyPotion = True
                    elif option.endswith("potion"):
                        buy_count = option[:len(option) - len("potion") - 1]
                        buyPotion = True
                    elif option.endswith("potions"):
                        buy_count = option[:len(option) - len("potions") - 1]
                        buyPotion = True
                    else:
                        print("You cannot buy that.")
                        notTurn = True
                    if (buyPickaxe or buyFunnelCake or buyFoot or buyLantern or buyOil or buyPie or buyHook or buyBandage or buyTicket or buyPotion):
                        # Item information
                        if buyFunnelCake:
                            itemName = "funnel cake"
                            itemsName = "funnel cakes"
                            invItem = inv.funnelCake
                            price = PRICE_BUY_FUNNELCAKE
                        elif buyPie:
                            itemName = "chicken pot pie"
                            itemsName = "chicken pot pies"
                            invItem = inv.pie
                            price = PRICE_BUY_PIE
                        elif buyFoot:
                            itemName = "lucky rabbit foot"
                            itemsName = "lucky rabbit feet"
                            invItem = inv.foot
                            price = PRICE_BUY_FOOT
                        elif buyLantern:
                            itemName = "lantern"
                            itemsName = "lanterns"
                            invItem = inv.lantern
                            price = PRICE_BUY_LANTERN
                        elif buyOil:
                            itemName = "vial of lantern oil"
                            itemsName = "vials of lantern oil"
                            invItem = inv.oil
                            price = PRICE_BUY_OIL
                        elif buyBandage:
                            itemName = "bandage"
                            itemsName = "bandages"
                            invItem = inv.bandage
                            price = PRICE_BUY_BANDAGE
                        elif buyPickaxe:
                            itemName = "pickaxe"
                            itemsName = "pickaxes"
                            invItem = inv.pickaxe
                            price = PRICE_BUY_PICKAXE
                        elif buyHook:
                            itemName = "grappling hook"
                            itemsName = "grappling hooks"
                            invItem = inv.hook
                            price = PRICE_BUY_HOOK
                        elif buyTicket:
                            itemName = "raffle ticket"
                            itemsName = "raffle tickets"
                            invItem = inv.ticket
                            price = PRICE_BUY_TICKET
                        elif buyPotion:
                            itemName = "potion of rejuvination"
                            itemsName = "potions of rejuvination"
                            invItem = inv.potion
                            price = PRICE_BUY_POTION
                        itemInStore = False
                        # Funnel Cakes Galore
                        if roomID == "roomCarnivalFood":
                            shopKeeperName = "vendor"
                            if invFood.funnelCake:
                                if buyFunnelCake or buyPie:
                                    itemInStore = True
                            else:
                                if buyPie:
                                    itemInStore = True
                        # General Store
                        elif roomID == "roomGeneralStore":
                            shopKeeperName = "the shopkeeper"
                            if buyFoot or buyLantern or buyOil or buyBandage:
                                itemInStore = True
                        # Blacksmith
                        elif roomID == "roomBlacksmith":
                            shopKeeperName = "the blacksmith"
                            if buyPickaxe or buyHook:
                                itemInStore = True
                        # Wheel of Mystery
                        elif roomID == "roomCarnivalWheelGame":
                            shopKeeperName = "the man"
                            if buyTicket:
                                itemInStore = True
                        # Alchemist's hut
                        elif roomID == "roomAlchemist":
                            shopKeeperName = "Tim the Enchanter"
                            if buyPotion:
                                itemInStore = True
                            # Checks if invItem buy_count is valid
                            # Must be equivalent to an integer
                            # Must not be negative
                            # With cost, must not exceed inv.gold
                        if itemInStore:
                            buyItemCountOkay = False
                            try:
                                buy_count = float(buy_count)
                                if float(buy_count) % 1 == 0 and buy_count > 0:
                                    buyItemCountOkay = True
                                    buy_count = int(buy_count)
                                else:
                                    print("You cannot buy",buy_count,"%s." % itemsName)
                                    notTurn = True
                            except ValueError:
                                print("You cannot buy \"" + buy_count + "\" %s." % itemsName)
                                notTurn = True
                            # Buying funnel cakes
                            if buyItemCountOkay:
                                # Checks if inventory has enough money
                                if inv.gold >= buy_count * price:
                                    # If buying funnel cakes past limit
                                    if buyFunnelCake and buy_count >= invFood.funnelCake:
                                        if buy_count > invFood.funnelCake:
                                            print("\n\"It looks like you bought out my final batch of funnel cakes for today. There's only %s cakes left, so I guess that'll do.\"" % invFood.funnelCake)
                                        else:
                                            print("\n\"It looks like you bought out my final batch of funnel cakes. I've run out of batter so I won't be able to make any more for today.\"")
                                        buy_count = invFood.funnelCake
                                    inv.gold -= buy_count * price
                                    if buy_count > 1:
                                        print("You give",shopKeeperName,buy_count * price,'gold. He gives you %s %s.' % (buy_count, itemsName))
                                    elif buy_count == 1:
                                        print("You give",shopKeeperName,buy_count * price,'gold. He gives you a %s.' % itemName)
                                    # Adds purchased items to inventory
                                    if buyFunnelCake:
                                        inv.funnelCake += buy_count
                                        invItem += buy_count
                                        invFood.funnelCake -= buy_count
                                    elif buyPie:
                                        inv.pie += buy_count
                                        invItem += buy_count
                                    elif buyPickaxe:
                                        inv.pickaxe += buy_count
                                        invItem += buy_count
                                    elif buyFoot:
                                        inv.foot += buy_count
                                        invItem += buy_count
                                    elif buyLantern:
                                        inv.lantern += buy_count
                                        invItem += buy_count
                                    elif buyOil:
                                        inv.oil += buy_count
                                        invItem += buy_count
                                    elif buyHook:
                                        inv.hook += buy_count
                                        invItem += buy_count
                                    elif buyBandage:
                                        inv.bandage += buy_count
                                        invItem += buy_count
                                    elif buyTicket:
                                        inv.ticket += buy_count
                                        invItem += buy_count
                                    elif buyPotion:
                                        inv.potion += buy_count
                                        invItem += buy_count
                                    # Tells users item count
                                    if invItem >= 1:
                                        if invItem > 1:
                                            print("You have",invItem,"%s." % itemsName)
                                        else:
                                            print("You have",invItem,"%s." % itemName)
                                        print("You have",inv.gold,"gold.")
                                        if roomID == "roomCarnivalWheelGame" and inv.ticket >= 1 and roomCarnivalWheelGame.isBuy:
                                            # Can't buy more tickets
                                            roomCarnivalWheelGame.isBuy = False
                                            # No one in room
                                            roomCarnivalWheelGame.characterDead = True
                                            # Can return ticket in roomCarnival
                                            roomCarnival.isGive = True
                                            # Wheel game is closed
                                            roomCarnival.westBlocked = True
                                            print("\"Settle down everyone. We've sold our last ticket. It's time to spin the wonderful wheel of mystery!\" The man takes hold of the large wheel at the centre of the stage and with all his weight, pushes one end down, causing it to turn. The crowd cheers as the numbers along the edge pass the ticker at the top of the wheel. It finally stops at 42. \"I have the ticket! I have it!\" shouts a bearded man in the crowd. Just as he approaches the stage, someone grabs hold of him. \"Wait a minute, I've seen you before.\" His beard is torn off, revealing him in disguise. \"You won yesterday's raffle. In fact, you've been winning the raffle ever since this bloody carnival began. This raffle is rigged! Get 'em!\" Guards outside the tent notice the commotion and enter, grabbing hold of the raffle host and his accomplice to arrest them. \n\nA spokesperson enters the tent. She announces to the crowd, \"I apologize on behalf of the carnival for what has happened here. If you would like to be compensated for your tickets, it will be arranged outside in the fair grounds. Now, everyone out. These two will be taken to the town jail for their crimes.\" The crowd begrungenly walks out.")
                                    else:
                                        print("You have no %s." % itemsName)
                                else:
                                    if buy_count > 1:
                                        print("You don't have enough gold to buy",buy_count,"%s." % itemsName)
                                        notTurn = True
                                    else:
                                        print("You don't have enough gold to buy a %s." % itemName)
                                        notTurn = True
                        else:
                            print("You cannot buy any",itemsName,"here.")
                            notTurn = True
            # If not isBuy
            else:
                print("You cannot buy anything here.")
                notTurn = True
        # Bet
        # For gambling in shops
        # Carnival Shell Game
        # Carnival Wheel Game
        elif option.startswith("bet") or option.startswith("gamble"):
            if option.startswith("bet"):
                option = option[4:]
            elif option.startswith("gamble"):
                option = option[7:]
            if roomCurrent.isBet:
                if option == "":
                    print("Bet what?")
                    option = False
                    notTurn = True
                else:
                    try:
                        if float(option) % 1 == 0:
                            option = int(option)
                        else:
                            option = float(option)
                        print("Bet",option,"what?")
                        notTurn = True
                        option = False
                    except ValueError:
                        if option == "all":
                            option = inv.gold
                        else:
                            option = False
                if option:
                    # Sybil's Shell Game
                    if roomID == "roomCarnivalShellGame":
                        # If bet is already made
                        betGold = True
                        if roomCarnivalShellGame.betMade:
                            print("You've already bet",s_bet,"gold. Sybil is waiting for you to choose the left, middle, or right shell.")
                            notTurn = True
                        # If bet is not made
                        else:
                            if option.endswith("gold"):
                                s_bet = option[:len(option) - len("gold") - 1]
                            else:
                                print("You cannot bet that.")
                                betGold = False
                                notTurn = True
                            # Checks if bet s_bet is valid
                            betGoldOkay = False
                            if betGold:
                                if s_bet == "":
                                    print("Bet how much gold?")
                                    notTurn = True
                                else:
                                    try:
                                        if float(s_bet) % 1 == 0 and float(s_bet) >= 0:
                                            betGoldOkay = True
                                            s_bet = int(float(s_bet))
                                        else:
                                            betGold = False
                                            print('You cannot bet "' + s_bet + '" gold.')
                                            notTurn = True
                                    except ValueError:
                                        print('You cannot bet "' + s_bet + '" gold.')
                                        notTurn = True
                            if betGoldOkay:
                                if inv.gold >= s_bet:
                                    inv.gold -= s_bet
                                    invShell.gold += s_bet
                                    if s_bet > 0:
                                        print("You bet",s_bet,"gold.",end=" ")
                                    else:
                                        print('You bet nothing. "Playing just for fun, I see. So it shall be."',end=" ")
                                    print('Sybil takes out a stone, places it under one of the shells, and elaborately shuffles them all on the table. "Choose a shell," she says, pointing to the left, middle and right shells.')
                                    print("You have %s gold." % inv.gold)
                                    roomCarnivalShellGame.betMade = True
                                else:
                                    print("You don't have enough gold to bet",s_bet,"gold.")
                                    notTurn = True
            else:
                print("You cannot bet on anything here.")
                notTurn = True
        # Choose
        # For gambling in shops
        # Carnival Shell Game
        elif option.startswith("choose"):
            option = option[7:]
            if roomCurrent.isBet:
                if option == "":
                    print("Choose what?")
                    notTurn = True
                else:
                    # Sybil's Shell Game
                    if roomID == "roomCarnivalShellGame":
                        if roomCurrent.betMade:
                            # If bet is already made
                            if "left" in option:
                                s_choice = "left"
                            elif "middle" in option:
                                s_choice = "middle"
                            elif "right" in option:
                                s_choice = "right"
                            else:
                                s_choice = "invalid"
                            if s_choice != "invalid":
                                # If win
                                if random.randint(1,100) < (33 + LUCKY_FOOT_MODIFIER * inv.foot):
                                    if roomCurrent.characterDead:
                                        print("You choose the",s_choice,"shell. As you just killed Sybil, you take a look under the shell and see that you have won. Congratulations for being a terrible person.")
                                        roomCurrent.betMade = False
                                    else:
                                        print("Sybil raises the",s_choice,"shell and reveals the stone underneath.")
                                        print('"Ahh! Luck is on your side," she exclaims.',end=" ")
                                        if s_bet > 0:
                                            if (s_bet * SHELL_REWARD_MULTIPLIER) >= invShell.gold:
                                                print("\"You seem you have run my coffers dry. My days of carnival work here is over. This is all I can give you.\" She hands you %s gold and begins packing her stuff to leave." % invShell.gold)
                                                inv.gold += invShell.gold
                                                invShell.gold = 0
                                                roomCurrent.isBet = False
                                                roomCarnival.southBlocked = True
                                            else:
                                                inv.gold += s_bet * SHELL_REWARD_MULTIPLIER
                                                invShell.gold -= s_bet * SHELL_REWARD_MULTIPLIER
                                                print("\"Here's your %s gold.\"" % (s_bet * SHELL_REWARD_MULTIPLIER))
                                        else:
                                            print('"If only you bet something, you could reap your reward. Oh well."')
                                        print("You have",inv.gold,"gold.")
                                        roomCurrent.betMade = False
                                # If lose
                                else:
                                    print("Sybil raises the",s_choice,"shell and reveals nothing underneath.")
                                    print('"Luck is not on your side today," she exclaims.',end=" ")
                                    if s_bet > 0:
                                        print('"I guess your gold belongs to me."')
                                    else:
                                        print('"I guess you are fortunate not to bet anything."')
                                    roomCurrent.betMade = False
                            else:
                                print("Choose the left, middle, or right shell.")
                        # If bet is not made
                        else:
                            print("You cannot choose a shell until you bet gold.")
                            notTurn = True
        elif option.startswith("give"):
            if option == "give":
                option = ""
            else:
                option = option[len("give "):]
            if roomID == "roomBridge":
                # name X gold -> X gold
                if option.startswith("troll"):
                    if option == "troll":
                        option = ""
                    else:
                        option = option[len("troll "):]
                elif option.startswith("ugg"):
                    if option == "ugg":
                        option = ""
                    else:
                        option = option[len("ugg "):]
                # X gold to name -> X gold
                elif option.endswith("to troll"):
                    option = option[:len(option) - len(" to troll")]
                elif option.endswith("to ugg"):
                    option = option[:len(option) - len(" to ugg")]
            elif roomID == "roomGate":
                # name X shrubbery
                if option.startswith("guard"):
                    if option == "guard":
                        option = ""
                    else:
                        option = option[len("guard "):]
                elif option.startswith("the guard"):
                    if option == "the guard":
                        option = ""
                    else:
                        option = option[len("the guard"):]
                # X shrubbery to name
                elif option.endswith("to guard"):
                    option = option[:len(option) - len(" to guard")]
                elif option.endswith("to the guard"):
                    option = option[:len(option) - len(" to the guard")]
            elif roomID == "roomCarnival":
                # spokesperson X ticket
                if option.startswith("spokesperson"):
                    if option == "spokesperson":
                        option = ""
                    else:
                        option = option[len("spokesperson "):]
                elif option.startswith("the spokesperson"):
                    if option == "the spokesperson":
                        option = ""
                    else:
                        option = option[len("the spokesperson ")]
                # X ticket to spokesperson
                elif option.endswith("to spokesperson"):
                    option = option[:len(option) - len(" to spokesperson")]
                elif option.endswith("to the spokesperson"):
                    option = option[:len(option) - len(" to the spokesperson")]
            elif roomID == "roomLake":
                if option.startswith("stranger"):
                    if option == "stranger":
                        option = ""
                    else:
                        option = option[len("stranger ")]
                elif option.startswith("injured stranger"):
                    if option == "injured stranger":
                        option = ""
                    else:
                        option = option[len("injured stranger ")]
                elif option.endswith("to injured stranger"):
                    option = option[:len(option) - len(" to injured stranger")]
                elif option.endswith("to stranger"):
                    option = option[:len(option) - len(" to stranger")]
            elif roomID == "roomHouseOffice":
                # eden X staff
                if option.startswith("eden von roquefort"):
                    if option == "eden von roquefort":
                        option = ""
                    else:
                        option = option[len("eden von roquefort")]
                elif option.startswith("eden"):
                    if option == "eden":
                        option = ""
                    else:
                        option = option[len("eden ")]
                elif option.startswith("roquefort"):
                    if option == "roquefort":
                        option = ""
                    else:
                        option = option[len("roquefort ")]
                # X staff to eden
                elif option.endswith("to eden von roquefort"):
                    option = option[:len(option) - len(" to eden von roquefort")]
                elif option.endswith("to eden"):
                    option = option[:len(option) - len(" to eden")]
                elif option.endswith("to roquefort"):
                    option = option[:len(option) - len(" to roquefort")]
            if roomCurrent.isGive:
                giveGold = False
                giveFunnelCake = False
                giveHalfFunnelCake = False
                giveshrubbery = False
                giveTicket = False
                giveStaff = False
                giveBandage = False
                if option == "":
                    print("Give what?")
                    notTurn = True
                else:
                    # Gold
                    if option == "gold":
                        give_count = 1
                        giveGold = True
                    elif option.endswith("gold"):
                        give_count = option[:len(option) - len("gold") - 1]
                        giveGold = True
                    # Half-eaten funnel cake
                    elif option in ("half-eaten cake","half cake","half-eaten funnel cake","half eaten funnel cake","half funnel cake","half-eaten cakes","half cakes","half-eaten funnel cakes","half eaten funnel cakes","half funnel cakes"):
                        give_count = 1
                        giveHalfFunnelCake = True
                    # Funnel cake
                    elif option in ("cake","cakes","funnel cake","funnel cakes"):
                        give_count = 1
                        giveFunnelCake = True
                    elif option.endswith("funnel cake"):
                        give_count = option[:len(option) - len("funnel cake") - 1]
                        giveFunnelCake = True
                    elif option.endswith("funnel cakes"):
                        give_count = option[:len(option) - len("funnel cakes") - 1]
                        giveFunnelCake = True
                    elif option.endswith("cake"):
                        give_count = option[:len(option) - len("cake") - 1]
                        giveFunnelCake = True
                    elif option.endswith("cakes"):
                        give_count = option[:len(option) - len("cakes") - 1]
                        giveFunnelCake = True
                    # shrubbery
                    elif option in ("shrubberies","shrubbery"):
                        give_count = 1
                        giveshrubbery = True
                    elif option.endswith("shrubbery"):
                        give_count = option[:len(option) - len("shrubbery") - 1]
                        giveshrubbery = True
                    elif option.endswith("shrubberies"):
                        give_count = option[:len(option) - len("shrubberies") - 1]
                        giveshrubbery = True
                    # Raffle ticket
                    elif option in ("raffle ticket","ticket","raffle tickets","tickets"):
                        give_count = 1
                        giveTicket = True
                    elif option.endswith("raffle ticket"):
                        give_count = option[:len(option) - len("raffle ticket") - 1]
                        giveTicket = True
                    elif option.endswith("raffle tickets"):
                        give_count = option[:len(option) - len("raffle tickets") - 1]
                        giveTicket = True
                    elif option.endswith("ticket"):
                        give_count = option[:len(option) - len("ticket") - 1]
                        giveTicket = True
                    elif option.endswith("tickets"):
                        give_count = option[:len(option) - len("tickets") - 1]
                        giveTicket = True
                    # Staff
                    elif option in ("staff","the staff","staff of garrotxa","the staff of garrotxa"):
                        give_count = 1
                        giveStaff = True
                    elif option.endswith("staff"):
                        give_count = option[:len(option) - len("staff") - 1]
                        giveStaff = True
                    elif option.endswith("the staff of garrotxa"):
                        give_count = option[:len(option) - len("the staff of garrotxa") - 1]
                        giveStaff = True
                    elif option.endswith("staff of garrotxa"):
                        give_count = option[:len(option) - len("staff of garrotxa") - 1]
                        giveStaff = True
                    # Bandage
                    elif option == "bandage":
                        give_count = 1
                        giveBandage = True
                    elif option.endswith("bandage"):
                        give_count = option[:len(option) - len("bandage") - 1]
                        giveBandage = True
                    else:
                        print("You cannot give that.")
                        notTurn = True
                    if (giveGold or giveFunnelCake or giveHalfFunnelCake or giveshrubbery or giveTicket or giveStaff or giveBandage):
                        # Item Information
                        if giveGold:
                            itemName = "gold"
                            itemsName = "gold"
                            invItem = inv.gold
                        elif giveFunnelCake:
                            itemName = "a funnel cake"
                            itemsName = "funnel cakes"
                            invItem = inv.funnelCake
                        elif giveHalfFunnelCake:
                            itemName = "a half-eaten funnel cake"
                            itemsName = "half-eaten funnel cakes"
                            invItem = inv.halfFunnelCake
                        elif giveshrubbery:
                            itemName = "a shrubbery"
                            itemsName = "shrubberies"
                            invItem = inv.shrubbery
                        elif giveTicket:
                            itemName = "your raffle ticket"
                            itemsName = "raffle tickets"
                            invItem = inv.ticket
                        elif giveStaff:
                            itemName = "the staff of Garrotxa"
                            itemsName = "the staves of Garrotxa"
                            invItem = inv.staff
                        elif giveBandage:
                            itemName = "bandage"
                            itemsName = "bandages"
                            invItem = inv.bandage
                        itemCanGive = False
                        # Bridge
                        if roomID == "roomBridge":
                            receiverName = "the troll"
                            if giveGold or giveFunnelCake or giveHalfFunnelCake:
                                itemCanGive = True
                        # Gate
                        if roomID == "roomGate":
                            receiverName = "the guard"
                            if giveshrubbery:
                                itemCanGive = True
                        # Carnival
                        if roomID == "roomCarnival":
                            receiverName = "the spokesperson"
                            if giveTicket:
                                itemCanGive = True
                        # Office
                        if roomID == "roomHouseOffice":
                            receiverName = "Eden Von Roquefort"
                            if giveStaff:
                                itemCanGive = True
                        # Lake
                        if roomID == "roomLake":
                            receiverName = "the stranger"
                            if giveBandage:
                                itemCanGive = True
                        if itemCanGive:
                            giveItemCountOkay = False
                            try:
                                give_count = float(give_count)
                                if float(give_count) % 1 == 0 and give_count > 0:
                                    giveItemCountOkay = True
                                    give_count = int(give_count)
                                else:
                                    if give_count == 1:
                                        print("You can't give a %s." % itemName)
                                    else:
                                        print("You can't give",give_count,"%s." % itemsName)
                            except ValueError:
                                print("You cannot give that.")
                                notTurn = True
                            if giveItemCountOkay:
                                # Checks if inventory has enough item
                                if invItem >= give_count:
                                    invItem -= give_count
                                    if giveGold:
                                        inv.gold -= give_count
                                        invTroll.gold += give_count
                                    elif giveFunnelCake:
                                        inv.funnelCake -= give_count
                                        invTroll.gold += give_count * TROLL_FUNNELCAKE_MULTIPLIER
                                    elif giveHalfFunnelCake:
                                        inv.halfFunnelCake -= give_count
                                        invTroll.gold += give_count * TROLL_HALFFUNNELCAKE_MULTIPLIER
                                    elif giveshrubbery:
                                        inv.shrubbery -= give_count
                                        invGate.shrubbery += give_count
                                    elif giveTicket:
                                        inv.ticket -= give_count
                                    elif giveStaff:
                                        inv.staff -= give_count
                                    elif giveBandage:
                                        inv.bandage -= give_count
                                    # Give response
                                    if roomCurrent.characterDead and roomID == "roomBridge":
                                        print('You place %s %s by the troll\'s body to pay respects.' % (give_count,itemsName))
                                    else:
                                        if give_count == 1 and not giveGold:
                                            print('You give %s %s.' % (receiverName,itemName))
                                        elif give_count > 1 and not giveGold:
                                            print('You give %s %s %s.' % (receiverName,give_count,itemsName))
                                        # Response once item given
                                        if not roomBridge.eastBlocked and roomID == "roomBridge":
                                            print('"Thank you human! Ugg never have enough funnel cakes."')
                                        elif roomID == "roomCarnival":
                                            print("Amongst the crowd of people handing her tickets, she miscounts and gives you %s gold in compensation for your troubles." % RAFFLE_COMPENSATION)
                                            inv.gold += RAFFLE_COMPENSATION
                                            print("You have %s gold." % inv.gold)
                                        elif roomID == "roomHouseOffice":
                                            roomCurrent.counter_1 = 1
                                            roomCurrent.isGive = False
                                            roomCurrent.characterDead = True # spawns vesh, which kills Eden
                                            roomCurrent.counter_2 += 1
                                        elif roomID == "roomLake":
                                            print("\"Thank you! Here, take this. I'm sure it will be of use to you.\" He gives a chicken pot pie. \"One more thing. You see that stone tablet? Whatever is written there is in language of demons, or the tongue of the Ozkavosh. Some phrases are mere words. However, some are spells and can grant you great powers when spoken.\n\nOne such example is, \'OZH ENSH,\' which when SPOKEN as a prefix followed by other words in Ozkavosh, forms a spell that will translate them for you.\nOnce you know what that spell is, you can SAY that spell on its own to cast it.\nYou can EXAMINE your SPELLS if you ever forget what they are.\n\nTry to figure out what written on that tablet and see if you can open my lockbox.\"")
                                            inv.pie += LAKE_PIE_REWARD
                                            roomCurrent.isGive = False
                                            if not spell_learn:
                                                spell_learn = 2
                                    if roomBridge.eastBlocked and invTroll.gold < TROLL_GOAL and roomID == "roomBridge":
                                        print('"Ugg only needs %s gold left until Ugg has enough to buy all the funnel cakes Ugg wants."' % (TROLL_GOAL - invTroll.gold))
                                # If not enough items in inventory to give
                                else:
                                    if give_count > 1:
                                        print("You don't have enough to give %s %s to %s." % (give_count,itemsName,receiverName))
                                        notTurn = True
                                    else:
                                        print("You don't have any %s to give to %s." % (itemsName,receiverName))
                                        notTurn = True
                        else:
                            print("You cannot give any",itemsName,"here.")
                            notTurn = True
                        # Result after give
                        if roomBridge.eastBlocked and invTroll.gold >= TROLL_GOAL:
                            print('The troll celebrates as his funnel cake dreams are fulfilled. "Ugg is very happy!" he cheers. He grabs everything you\'ve given him and goes back under the bridge.')
                            roomBridge.eastBlocked = False
                        if roomGate.northBlocked and invGate.shrubbery:
                            print("The guard takes a quick look. \"Yes, it is a good shrubbery. I like the laurels particularly.\" He lets you through the gate.")
                            roomGate.northBlocked = False
                            roomGate.isGive = False

            else:
                print("You can't give anything here.")
                notTurn = True
        # Sell
        # Exchange items for gold
        elif option.startswith("sell"):
            option = option[5:]
            if roomCurrent.isSell:
                if option == "":
                    print("Sell what?")
                    notTurn = True
                else:
                    sellCoal = False
                    sellStone = False
                    # Checking what is being sold and how many
                    # Sell coal
                    if option in ("coal", "piece of coal", "pieces of coal"):
                        sell_count = 1
                        sellCoal = True
                    elif option.endswith("pieces of coal"):
                        sell_count = option[:len(option) - len("pieces of coal") - 1]
                        sellCoal = True
                    elif option.endswith("piece of coal"):
                        sell_count = option[:len(option) - len("piece of coal") - 1]
                        sellCoal = True
                    elif option.endswith("coal"):
                        sell_count = option[:len(option) - len("coal") - 1]
                        sellCoal = True
                    # Dragonstone
                    elif option in ("dragonstone", "stone"):
                        sell_count = 1
                        sellStone = True
                    elif option.endswith("dragonstone"):
                        sell_count = option[:len(option) - len("dragonstone") - 1]
                        sellStone = True
                    elif option.endswith("stone"):
                        sell_count = option[:len(option) - len("stone") - 1]
                        sellStone = True
                    else:
                        print("You cannot sell that.")
                    if (sellCoal or sellStone):
                       # Item information
                        if sellCoal:
                            itemName = "piece of coal"
                            itemsName = "pieces of coal"
                            invItem = inv.coal
                            price = PRICE_SELL_COAL
                        elif sellStone:
                            itemName = "dragonstone"
                            itemsName = "dragonstones"
                            invItem = inv.stone
                            price = PRICE_SELL_STONE
                        itemInStore = False
                        # Blacksmith
                        if roomID == "roomBlacksmith":
                            shopKeeperName = "the blacksmith"
                            if sellCoal:
                                itemInStore = True
                        # Alchemist
                        if roomID == "roomAlchemist":
                            shopKeeperName = "Tim the Enchanter"
                            if sellStone:
                                itemInStore = True
                        if itemInStore:
                            sellItemCountOkay = False
                            try:
                                sell_count = float(sell_count)
                                if float(sell_count) % 1 == 0 and sell_count > 0:
                                    sellItemCountOkay = True
                                    sell_count = int(sell_count)
                                else:
                                    print("You cannot sell ",sell_count," %s." % itemsName)
                                    notTurn = True
                            except ValueError:
                                print("You cannot sell \"" + sell_count + "\"  %s." % itemsName)
                                notTurn = True
                            # Selling coal
                            if sellItemCountOkay:
                                # Checks if inventory has enough item
                                if invItem >= sell_count:
                                    invItem -= sell_count
                                    if sellCoal:
                                        inv.coal -= sell_count
                                    elif sellStone:
                                        inv.stone -= sell_count
                                    if sell_count == 1:
                                        print("You give the %s" % shopKeeperName,"a %s. He gives you %s gold." % (itemName,sell_count * price))
                                    elif sell_count > 1:
                                        print("You give the %s" % shopKeeperName,"%s %s. He gives you %s gold." % (sell_count,itemsName,sell_count * price))
                                    inv.gold += sell_count * price
                                    print("You have",inv.gold,"gold.")
                                    # Sell is completed
                                    if roomID == "roomAlchemist":
                                        roomCurrent.isSell = False
                                        roomCurrent.isBuy = True
                                        roomCurrent.isFill = True
                                        print("\"Wow, a full-sized dragonstone! Once I get that ground up, that will make enough powder to last me a life time.\" He grabs a mortar and pestle and works away at the dragonstone. After tossing some odd-looking herbs and the powder into the cauldron, the water thickens and turns red almost immediately. \"My potions both heal your wounds and satisfy your hunger. If you have an empty flask, you can refill it for a reduced cost. Those flasks don't come cheap after all.\"")
                                        print("\nGoods available to buy:\n    Potion of rejuvination (%s gold)" % PRICE_BUY_POTION)
                                        print("\nGoods available to fill:\n    Flask (%s gold)" % PRICE_REFILL_POTION)
                                else:
                                    if sell_count > 1:
                                        print("You don't have %s %s." % (sell_count,itemsName))
                                        notTurn = True
                                    else:
                                        print("You don't have have %s %s." % (sell_count,itemName))
                                        notTurn = True
                        else:
                            print("You cannot sell any",itemsName,"here.")
                            notTure = True
            # If not inSell
            else:
                print("You cannot sell anything here.")
                notTurn = True
        # Universally invalid action
        else:
            print("You cannot do that.")
            notTurn = True

#_______Special Decisions__________________________________________________________
# Being inside certain rooms allows for special actions.
# Shops, locked rooms

#_______Finished user input and all return values_______________________________


#_______Current Room____________________________________________________________
# Current room information
        # Refers to room classes for current room information
        if changeRoom:
            # Jail
            if roomID == "roomJailCell":
                roomCurrent = roomJailCell
            elif roomID == "roomJailCorridor":
                roomCurrent = roomJailCorridor
                if roomCurrent.firstTime:
                    same = random.randint(1,4)
                    if same == 1:
                        roomCurrent.counter_1 = roomCurrent.counterAns_1
                        roomCurrent.counter_3 = roomCurrent.counterAns_3
                    elif same == 2:
                        roomCurrent.counter_2 = roomCurrent.counterAns_2
                        roomCurrent.counter_4 = roomCurrent.counterAns_4
                    elif same == 3:
                        roomCurrent.counter_1 = roomCurrent.counterAns_1
                        roomCurrent.counter_4 = roomCurrent.counterAns_4
                    elif same == 4:
                        roomCurrent.counter_2 = roomCurrent.counterAns_2
                        roomCurrent.counter_3 = roomCurrent.counterAns_3
            elif roomID == "roomJailFoyer":
                roomCurrent = roomJailFoyer
            elif roomID == "roomJailAntechamber":
                roomCurrent = roomJailAntechamber
            elif roomID == "roomJailHallway":
                roomCurrent = roomJailHallway
            elif roomID == "roomJailBreakRoom":
                roomCurrent = roomJailBreakRoom
            elif roomID == "roomJailEntrance":
                roomCurrent = roomJailEntrance
            # Town
            elif roomID == "roomCourtyardNorth":
                roomCurrent = roomCourtyardNorth
            elif roomID == "roomCourtyardSouth":
                roomCurrent = roomCourtyardSouth
            elif roomID == "roomBlacksmith":
                roomCurrent = roomBlacksmith
            elif roomID == "roomAlchemist":
                roomCurrent = roomAlchemist
            elif roomID == "roomCarnival":
                roomCurrent = roomCarnival
            elif roomID == "roomCarnivalShellGame":
                roomCurrent = roomCarnivalShellGame
            elif roomID == "roomCarnivalFood":
                roomCurrent = roomCarnivalFood
            elif roomID == "roomCarnivalWheelGame":
                roomCurrent = roomCarnivalWheelGame
            elif roomID == "roomGeneralStore":
                roomCurrent = roomGeneralStore
            elif roomID == "roomGate":
                roomCurrent = roomGate
            # Road
            elif roomID == "roomRoadSouth":
                roomCurrent = roomRoadSouth
            elif roomID == "roomRoadMid":
                roomCurrent = roomRoadMid
            elif roomID == "roomRoadNorth":
                roomCurrent = roomRoadNorth
            elif roomID == "roomRoadEast":
                roomCurrent = roomRoadEast
            elif roomID == "roomRoadWest":
                roomCurrent = roomRoadWest
            elif roomID == "roomRoadCorner":
                roomCurrent = roomRoadCorner
            elif roomID == "roomLake":
                roomCurrent = roomLake
            # Bridge
            elif roomID == "roomBridge":
                roomCurrent = roomBridge
            # Temple
            elif roomID == "roomTempleEntrance":
                roomCurrent = roomTempleEntrance
            elif roomID == "roomTempleInside":
                roomCurrent = roomTempleInside
            elif roomID == "roomTempleBasement":
                roomCurrent = roomTempleBasement
            # Cave
            elif roomID == "roomMountEntrance":
                roomCurrent = roomMountEntrance
            # 1
            elif roomID == "roomCave_1_m":
                roomCurrent = roomCave_1_m
            # 2
            elif roomID == "roomCave_2_m":
                roomCurrent = roomCave_2_m
            elif roomID == "roomCave_2_mr":
                roomCurrent = roomCave_2_mr
            elif roomID == "roomCave_2_lm":
                roomCurrent = roomCave_2_lm
            elif roomID == "roomCave_2_llm":
                roomCurrent = roomCave_2_llm
            # 3
            elif roomID == "roomCave_3_m_coalmine":
                roomCurrent = roomCave_3_m_coalmine
            elif roomID == "roomCave_3_mr":
                roomCurrent = roomCave_3_mr
            elif roomID == "roomCave_3_mrr_coalmine":
                roomCurrent = roomCave_3_mrr_coalmine
            elif roomID == "roomCave_3_lm_coalmine":
                roomCurrent = roomCave_3_lm_coalmine
            elif roomID == "roomCave_3_llm_crevasse":
                roomCurrent = roomCave_3_llm_crevasse
            elif roomID == "roomCave__3_lllm_treasure_crevasse":
                roomCurrent = roomCave__3_lllm_treasure_crevasse
            # 4
            elif roomID == "roomCave_4_m":
                roomCurrent = roomCave_4_m
            elif roomID == "roomCave_4_mr":
                roomCurrent = roomCave_4_mr
            elif roomID == "roomCave_4_lm":
                roomCurrent = roomCave_4_lm
            elif roomID == "roomCave_4_llm":
                roomCurrent = roomCave_4_llm
            # 5
            elif roomID == "roomCave_5_m":
                roomCurrent = roomCave_5_m
            elif roomID == "roomCave_5_mr_coalmine":
                roomCurrent = roomCave_5_mr_coalmine
            elif roomID == "roomCave_5_lm_coalmine":
                roomCurrent = roomCave_5_lm_coalmine
            elif roomID == "roomCave_5_llm":
                roomCurrent = roomCave_5_llm
            elif roomID == "roomCave_5_lllm":
                roomCurrent = roomCave_5_lllm
            # 6
            elif roomID == "roomCave_6_m":
                roomCurrent = roomCave_6_m
            elif roomID == "roomCave_6_mr":
                roomCurrent = roomCave_6_mr
            elif roomID == "roomCave_6_lm_coalmine":
                roomCurrent = roomCave_6_lm_coalmine
            elif roomID == "roomCave_6_llm_coalmine":
                roomCurrent = roomCave_6_llm_coalmine
            elif roomID == "roomCave_6_lllm":
                roomCurrent = roomCave_6_lllm
            # 7
            elif roomID == "roomCave_7_m":
                roomCurrent = roomCave_7_m
            elif roomID == "roomCave_7_mr":
                roomCurrent = roomCave_7_mr
            elif roomID == "roomCave_7_lm":
                roomCurrent = roomCave_7_lm
            elif roomID == "roomCave_7_llm":
                roomCurrent = roomCave_7_llm
            elif roomID == "roomCave_7_lllm":
                roomCurrent = roomCave_7_lllm
            # 8
            elif roomID == "roomCave_8_mr_crevasse":
                roomCurrent = roomCave_8_mr_crevasse
            elif roomID == "roomCave_8_llm_coalmine":
                roomCurrent = roomCave_8_llm_coalmine
            # 9
            elif roomID == "roomCave_9_mr_crevasse":
                roomCurrent = roomCave_9_mr_crevasse
            # 10
            elif roomID == "roomCave__10_m":
                roomCurrent = roomCave__10_m
            elif roomID == "roomCave__10_mr":
                roomCurrent = roomCave__10_mr
            elif roomID == "roomCave__10_mrr":
                roomCurrent = roomCave__10_mrr
            elif roomID == "roomCave__10_mrrr":
                roomCurrent = roomCave__10_mrrr
            elif roomID == "roomCave__10_lm":
                roomCurrent = roomCave__10_lm
            # 11
            elif roomID == "roomCave__11_mrrr":
                roomCurrent = roomCave__11_mrrr
            elif roomID == "roomCave__11_lm":
                roomCurrent = roomCave__11_lm
            # Darkness
            elif roomID == "roomDarkness":
                roomCurrent = roomDarkness
            # Lair
            elif roomID == "roomLairMid":
                roomCurrent = roomLairMid
            elif roomID == "roomLairEast":
                roomCurrent = roomLairEast
            elif roomID == "roomLairWest":
                roomCurrent = roomLairWest
            elif roomID == "roomLairHole":
                roomCurrent = roomLairHole
            # Field
            elif roomID == "roomRoad2South":
                roomCurrent = roomRoad2South
            elif roomID == "roomRoad2Mid":
                roomCurrent = roomRoad2Mid
            elif roomID == "roomFarm":
                roomCurrent = roomFarm
            elif roomID == "roomBarn":
                roomCurrent = roomBarn
            elif roomID == "roomBarnUp":
                roomCurrent = roomBarnUp
            elif roomID == "roomRoad2North":
                roomCurrent = roomRoad2North
            elif roomID == "roomRoad2East":
                roomCurrent = roomRoad2East
            elif roomID == "roomShrine":
                roomCurrent = roomShrine
                if not word_darkness:
                    word_darkness = 1
            # House
            elif roomID == "roomHouseEntrance":
                roomCurrent = roomHouseEntrance
            elif roomID == "roomHouseFoyer":
                roomCurrent = roomHouseFoyer
            elif roomID == "roomHouseKitchen":
                roomCurrent = roomHouseKitchen
            elif roomID == "roomHousePantry":
                roomCurrent = roomHousePantry
            elif roomID == "roomHouseHallway":
                roomCurrent = roomHouseHallway
            elif roomID == "roomHouseOffice":
                roomCurrent = roomHouseOffice
            # Mysterious book
            elif roomID == "roomBookAnimal":
                roomCurrent = roomBookAnimal
            elif roomID == "roomBookMirror":
                roomCurrent = roomBookMirror
            elif roomID == "roomBook_3_1":
                roomCurrent = roomBook_3_1
            elif roomID == "roomBook_3_2":
                roomCurrent = roomBook_3_2
            elif roomID == "roomBook_3_3":
                roomCurrent = roomBook_3_3
            elif roomID == "roomBook_3_4":
                roomCurrent = roomBook_3_4
            elif roomID == "roomBook_3_5":
                roomCurrent = roomBook_3_5
            elif roomID == "roomBook_3_6":
                roomCurrent = roomBook_3_6
            elif roomID == "roomBook_3_7":
                roomCurrent = roomBook_3_7
            elif roomID == "roomBook_3_8":
                roomCurrent = roomBook_3_8
            elif roomID == "roomBook_3_End":
                roomCurrent = roomBook_3_End
                roomCurrent.itemFound = True
                if not word_dominion:
                    word_dominion = 1

            else:
                print("Uh oh, you broke the game.",roomID,"does not exist. Room not changed.")
            if direction:
                print("You go %s." % direction)
                justEntered = True
            # Rubble falling
            if roomID.startswith("roomCave_") and not roomID.startswith("roomCave__") and random.randint(1,100) <= RUBBLE_FALL_CHANCE:
                stat.health -= 1
                print("The mountain shakes and some rubble falls and hits you.")

            changeRoom = False
        elif direction == "North" and roomCurrent.northBlocked:
            print(roomCurrent.northBlockedReason)
            notTurn = True
        elif direction == "East" and roomCurrent.eastBlocked:
            print(roomCurrent.eastBlockedReason)
            notTurn = True
        elif direction == "South" and roomCurrent.southBlocked:
            print(roomCurrent.southBlockedReason)
            notTurn = True
        elif direction == "West" and roomCurrent.westBlocked:
            print(roomCurrent.westBlockedReason)
            notTurn = True
#_______Events__________________________________________________________________
        # Before justEntered description
        if roomID.startswith("roomCave") and not roomID == "roomCave_1_m":
            # Creatures does not naturally roam if have staff in normal cave
            # However, lantern is still needed to prevent creature chase
            if inv.staff and not roomID.startswith("roomCave__"):
                creatureRoam = False
            else:
                creatureRoam = True
            # Chase begins immediately if no light
            if not ((oilCounter and (inv.lantern or roomCurrent.lantern)) or lightCounter):
                creatureRoam = False
                creatureChase = True
                if creatureChase and creatureChaseCounter > 5:
                    creatureChaseCounter = 6
        if roomID == "roomJailCell" and roomCurrent.counter_1 and not notTurn:
            if roomCurrent.counter_1 == 1:
                print("You hear a loud crack of thunder and a mystical portal opens in the nearby corridor. A woman veiled in white robes jumps through, entering the room. She swiftly runs through the corridor, scanning each cell she passes. Suddenly, she stops at your cell, pauses and draws nearer. \"Tell me your name, stranger.\" she exclaims. \"You may be who I'm looking for.\" She looks worried.")
                askName = True
                roomCurrent.counter_1 += 1
            elif turnCounter >= 2 and askName:
                if stat.name:
                    if stat.name.lower() == "ozh gluth izh sol":
                        print("\n\"Ha! You think you can kill me with the tongue of the Ozkavosh? You are a fool!\" The woman transforms in a scaly demon, sprouting wings and sharp claws. \"No mortal shall defeat Vesh'kathal the Deceiver. OZH GLUTH IZH SOL!\"\n\nYou are overwhelmed with pain and agony as you fall to your knees.\n")
                        endGame = True
                    elif stat.name.lower() in ("eden von roquefort","vesh'kathal","vesh'raheen","vesh'arkosh","garrotxa","rodney williams"):
                        print("\n\"" + stat.name + "? I do not appreciate being lied to.\" She zaps you with a bolt of electricity. \"I'll let you live your last few moments bleeding in your cell. Farewell.\" She leaves through the portal from whence she came. The portal snaps shut and disappears.")
                        stat.health -= 1
                        askName = False
                    else:
                        print("\n\"" + stat.name + "? Finally, my search is over. This letter is for you. Read it and you will understand the urgency of the situation. I unforunately have no time to explain as I have much yet to do.\" She nods, tosses a key and a letter through your cell window, and quickly leaves through the portal from whence she came. The portal snaps shut and disappears.")
                        roomCurrent.letter += 1
                        roomCurrent.key += 1
                        roomCurrent.itemsPresent()
                        inv.letterRead = "It reads:\n\nTo " + stat.name + ",\n\nWord has gotten out that a certain Eden Von Roquefort has set up residence north of Mount Magna. While he purports to be a lowly cheese mage, reliable sources claim him to be the demon lord, Vesh'kathal the Deceiver, a shapeshifter infamous of manipulating the minds and bending the wills of others. Legend tells of a saviour, deemed the Monterey Messiah, who will save all of Kashkaval from his wickedness. It has be brought to my attention that you are that saviour that the legends speak of. While I have very important matters to attend to, the best I can do is help instruct you in how to defeat this demon.\n\nFIRST, you must acquire the staff from the Garrotxian temple northeast of this town, for it is the only weapon capable of defeating such a powerful demon.\n\nNEXT, once you have the staff, go north, through the mines of Mount Magna and find him at his house on the other end.\n\nFINALLY, kill Roquefort and Kashkaval will be saved from his wrath.\n\nI know this is probably a lot to digest at once, but you are our only hope. I fear in your attempt to complete this task, Vesh'kathal will attempt to thwart you. He may attempt to contact and manipulate you, or have his minions work to stop you. Either way, you must persevere.\n\nMay you be blessed by the will of Garrotxa,\n\nA friend\n\nP.S. The code to the vault door is %i%i%i%i." % (roomJailCorridor.counterAns_1, roomJailCorridor.counterAns_2, roomJailCorridor.counterAns_3, roomJailCorridor.counterAns_4)
                        askName = False
                if not stat.name:
                    if roomCurrent.counter_1 == 2:
                        print('The stranger impatiently waits for your response. "I beg of you. Tell me your name. This is of utmost importance."')
                    elif roomCurrent.counter_1 == 3:
                        print("The stranger quickly grows angry as you continue to ignore her.")
                    else:
                        print("\"Why am I wasting my time with a mute rotting in a jail cell?\" She zaps you with a bolt of electricity and walks back through the portal, which snaps shut and disappears.")
                        askName = False
                        endGame = True
                    roomCurrent.counter_1 += 1
        elif roomID == "roomJailFoyer":
            if jailGuards == False:
                jailGuards = True
                jailGuardCounter = 3
        elif roomID == "roomCourtyardSouth":
            if jailGuards == True:
                print("You get lost in the crowd, confusing and disorienting the jail guards. They lose interest, probably because they don't get paid enough.")
                jailGuards = False
        # Creature stops chase at crevasse or outside
        elif roomID == "roomMountEntrance" or (roomID.endswith("crevasse") and roomID_Last.endswith("crevasse") and roomID != roomID_Last):
            creatureRoam = False
            creatureChase = False
            if creatureChaseCounter <= 2:
                if roomID == "roomMountEntrance":
                    print("The creature is blinded by the outside light and stops its chase, returning back to the cavern.")
                else:
                    print("The creature stops at the crevasse, unable to cross. It walks away.")
            creatureRoamCounter = random.randint(CREATURE_ROAM_MIN,CREATURE_ROAM_MAX)
            creatureChaseCounter = random.randint(CREATURE_CHASE_MIN,CREATURE_CHASE_MAX)
        # Creature stops chas at outside of lair
        elif roomID == "roomRoad2South":
            creatureLairChase = False
            if creatureLairChaseCounter <= 2 and roomID_Last == "roomLairEast":
                print("The creature is blinded by the outside light and stops its chase, returning back to the cavern.")
        # If player returns to lair after exiting it
        elif roomID == "roomLairEast" and roomID_Last == "roomRoad2South":
            print("The creature is just at the opening and notices you immediately. It claws your face off.")
            endGame = True
            # continue
            # see how actually works out, may be better to have as post-justEntered text

        # Lose lantern
        # Start of creature chase
        if roomID == "roomCave_9_mr_crevasse" and roomCurrent.firstTime:
            if inv.lantern > 1:
                lantern_count = "lanterns"
            elif inv.lantern == 1:
                lantern_count = "lantern"
            else:
                lantern_count = False
            inv.lantern = 0
            roomCave_8_mr_crevasse.hook = 0
            roomCave_8_mr_crevasse.northBlocked = True
            if lantern_count:
                lantern_loss = "Your " + lantern_count + " fall into the abyss below."
                aswell = " as well"
            else:
                lantern_loss = ""
                aswell = ""
            print("As you cross the crevasse, the entire mountain begins to shake and a loud rumble echoes throughout the cavern walls.",lantern_loss,"You finally make it to the other side just before the grappling hook shakes loose and falls%s." % aswell)
        # Rubble falls
        elif roomID == "roomCave__10_mr" and roomCurrent.firstTime:
            print("Rubble collapses from above as the mountain shakes, blocking the path behind you.")
        # Enter darkness
        elif roomID == "roomDarkness":
            creatureRoam = False
            creatureChase = False
            stat.hunger = HUNGER_DARKNESS
            stat.health = HEALTH_MAX - 1
            oilCounter = 0
            lightCounter = 0
            creatureRoamCounter = random.randint(CREATURE_ROAM_MIN,CREATURE_ROAM_MAX)
            creatureChaseCounter = random.randint(CREATURE_CHASE_MIN,CREATURE_CHASE_MAX)
            if inv.pickaxe:
                roomLairMid.counter_3 = 1
            if inv.staff:
                roomLairMid.counter_2 = 1
            inv = Inventory()
            print("The mountain shakes again, and more rubble collapses from above. A large rock hits you, knocking you unconcious.")

        # Crevasse block check
        if roomCurrent.isCrevasse:
            if not roomCurrent.hook and not roomCurrent.northBlocked and not roomCurrent.eastBlocked and not roomCurrent.westBlocked and not roomCurrent.southBlocked:
                if roomID == "roomCave_3_llm_crevasse":
                    roomCurrent.westBlocked = True
                elif roomID == "roomCave__3_lllm_treasure_crevasse":
                    roomCurrent.eastBlocked = True
                elif roomID == "roomCave_8_mr_crevasse":
                    roomCurrent.northBlocked = True
                elif roomID == "roomCave_9_mr_crevasse":
                    roomCurrent.southBlocked = True

        # Grappling hook spawn check
        if roomID in ("roomCave_3_llm_crevasse","roomCave__3_lllm_treasure_crevasse","roomCave_8_mr_crevasse","roomCave_9_mr_crevasse"):
            if roomID == "roomCave__3_lllm_treasure_crevasse" and roomCurrent.eastBlocked and not roomCave_3_llm_crevasse.westBlocked and roomCave_3_llm_crevasse.hook:
                roomCurrent.eastBlocked = False
                roomCurrent.hook += 1
                roomCave_3_llm_crevasse.hook -= 1
            elif roomID == "roomCave_3_llm_crevasse" and roomCurrent.westBlocked and not roomCave__3_lllm_treasure_crevasse.eastBlocked and roomCave__3_lllm_treasure_crevasse.hook:
                roomCurrent.westBlocked = False
                roomCurrent.hook += 1
                roomCave__3_lllm_treasure_crevasse.hook -= 1
            elif roomID == "roomCave_9_mr_crevasse" and roomCurrent.southBlocked and not roomCave_8_mr_crevasse.northBlocked and roomCave_8_mr_crevasse.hook:
                roomCurrent.southBlocked = False
                roomCurrent.hook += 1
                roomCave_8_mr_crevasse.hook -= 1
            elif roomID == "roomCave_8_mr_crevasse" and roomCurrent.northBlocked and not roomCave_9_mr_crevasse.southBlocked and roomCave_9_mr_crevasse.hook:
                roomCurrent.northBlocked = False
                roomCurrent.hook += 1
                roomCave_9_mr_crevasse.hook -= 1

#_______________________________________________________________________________
        # justEntered Description
        # Prints room description if just entered.
        if justEntered:
            roomCurrent.description()
            roomCurrent.itemsPresent()
            justEntered = False
#_______________________________________________________________________________
# COUNTERS
# Counts end of turn
        if not notTurn or endGame:
            # After changeRoom description
            direction = False
            turnCounter += 1
            turnCounter_total += 1
            # Darkness loop
            if roomID == "roomDarkness":
                darknessCounter = DARKNESS_DURATION
                while darknessCounter:
                    option = input("\n> ").lower()
                    darknessCounter -= 1
                    if darknessCounter:
                        print("You feel nothing.")
                roomID = "roomLairMid"
                roomCurrent = roomLairMid
                print("You wake up.")
                roomCurrent.description()
                roomCurrent.itemsPresent()

            # Jail guards
            if jailGuards and not endGame:
                jailGuardCounter -= 1
                if jailGuardCounter == 2:
                    print("\nJail guards notice you walking into the foyer and begin to run towards you from the eastern hallway, armed with bludgeons.")
                elif jailGuardCounter == 1:
                    print("\nThe jail guards draw nearer, closing in the distance.")
                elif jailGuardCounter == 0:
                    print("\nThe jail guards catch up and grab hold of you. They beat you to death for trying to escape.")
                    endGame = True
            # Lair Creature
            if creatureLairChase:
                if creatureLairChaseCounter > 5:
                    print("\nThe creature notices it is trapped and tries to escape.")
                elif creatureLairChaseCounter == 5:
                    print("\nThe creature screeches as it claws away at the rock around it.")
                elif creatureLairChaseCounter == 4:
                    print("\nThe creature's torso erupts free as it breaks the surrounding rubble.")
                elif creatureLairChaseCounter == 3:
                    print("\nThe creature is almost free, with only its foot caught in the rocks.")
                elif creatureLairChaseCounter == 2:
                    print("\nThe creature entirely breaks free from the rubble, which stops to release an ear-piercing scream. It erupts into full sprint towards you.")
                elif creatureLairChaseCounter == 1:
                    print("\nThe creature extends its arms towards you as it runs, continuing it's chaotic screeching in full force. It claws your arm.")
                    stat.health -= 1
                else:
                    print("\nThe creature grabs hold of you, tearing you to pieces.")
                    endGame = True
                creatureLairChaseCounter -= 1
            if creatureRoam:
                creatureRoamCounter -= 1
                if creatureRoamCounter == 0:
                    creatureRoam = False
                    creatureChase = True
            if creatureChase:
                creatureChaseCounter -= 1
                if creatureChaseCounter > CREATURE_CHASE_MIN:
                    print("\nSomething doesn't feel quite right...")
                elif creatureChaseCounter >= 9:
                    print("\nA faint noise echoes in the cavern.")
                elif creatureChaseCounter >= 5:
                    print("\nDistant breathing can be heard in rhythm of steady footsteps.")
                elif creatureChaseCounter == 4:
                    print("\nThe breathing draws nearer as the footsteps quicken in pace.")
                elif creatureChaseCounter == 3:
                    print("\nThe breathing turns into growling, which reverberates throughout the cavern walls.")
                elif creatureChaseCounter == 2:
                    print("\nA creature appears, which stops to release an ear-piercing scream. It erupts into full sprint towards you.")
                elif creatureChaseCounter == 1:
                    print("\nThe creature extends its arms towards you as it runs, continuing it's chaotic screeching in full force. It claws your arm.")
                    stat.health -= 1
                elif creatureChaseCounter == 0:
                    print("\nThe creature grabs hold of you, tearing you to pieces.")
                    endGame = True
            # Raffle reimbursement
            if roomID == "roomCarnival" and roomCurrent.counter_1 <= RAFFLE_TIMER:
                if roomCurrent.isGive:
                    roomCurrent.counter_1 += 1
                if roomCurrent.counter_1 > RAFFLE_TIMER:
                    print("\nThe spokesperson and the guards pack and up and leave the grounds.")
                    roomCurrent.isGive = False
            if not roomID.startswith("roomBook"):
                # Hunger
                stat.hunger -= 1
                if stat.hunger <= 0:
                    endGame = True
                elif stat.hunger <= 15 and option != "hunger":
                    print()
                    stat.examineHunger()
                # Health
                if stat.health in range(1,stat.healthmax):
                    stat.health -= 1
                    print()
                    stat.examineHealth()
                if stat.health <= 0:
                    endGame = True
                # Oil
                if oilCounter:
                    inv.lanternDescription = "Perfect for lighting dark areas. It is currently lit."
                    oilCounter -= 1
                else:
                    inv.lanternDescription = "Perfect for lighting dark areas. It is currently unlit."
                # Oil warning
                if inv.lantern > 0 or roomCurrent.lantern > 0:
                    if oilCounter in range(3,5):
                        print("\nYour lantern flickers.")
                    elif oilCounter == 2:
                        print("\nYour lantern has almost died out.")
                    elif oilCounter == 1:
                        print("\nYour lantern has ran out of oil.")

            # Ball of light
            if lightCounter:
                lightCounter -= 1
                if lightCounter > 2:
                    print("\nA ball of light illuminates your surroundings.")
                elif lightCounter == 2:
                    print("\nA ball of light dimly illuminates your surroundings.")
                elif lightCounter == 1:
                    print("\nA ball of light fades away.")


            # Roquefort messages
            # roomCourtyardNorth
            if roomID == "roomCourtyardNorth" and roomCurrent.firstTime:
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"I do not know who you are, but you if you seek to do me harm, you are making a grave mistake.\"")
                messageCounter = 1
            # roomRoadSouth
            elif roomID == "roomRoadSouth" and roomCurrent.firstTime:
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"You continue to work against me. You know nothing of who you are dealing with! Why do you persist?\"")
            # roomCave_5_m or roomCave_5_llm
            if roomID in ("roomCave_5_m","roomCave_5_llm") and (roomCave_5_m.firstTime and roomCave_5_llm.firstTime):
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"You are still determined to defeat me? If I cannot persuade you, then so be it. Be warned that that it will take more than cold steel to defeat a cheese mage such as myself.\"")
            # roomTempleEntrance after get staff
            if roomID == "roomTempleEntrance" and roomTempleBasement.characterDead and roomCurrent.firstTime:
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"The staff of Garrotxa? No mere mortal would obtain such a weapon to defeat me. You clearly have a plan. No matter. You will be stopped.\"")
            # roomRoad2South
            if roomID == "roomRoad2South" and roomCurrent.firstTime:
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"One does not simply obtain the staff of Garrotxa, kill my greatest warrior and travel trhough the mines of Mount Magna on their own accord. You must be an assassin or something of the likes. But sent by who?\"")
            # roomBarn
            if roomID == "roomBarn" and roomCurrent.firstTime:
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"Who ARE you? You seem driven to kill me, yet for reasons still unbeknownst to me. This seems personal, yet I do not recall ever wronging you. This must be something more at stake here.\"")
            # roomHouseEntrance
            if roomID == "roomHouseEntrance" and roomCurrent.firstTime:
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"Enough. You speak the tongue of demon-kind in order to trespasse into my property. Clearly you are a servant of the Ozhkavosh. I will do what I can in my power to stop you. Hold your tongue for you are silenced!\"")
                print("A strange force takes over you.")
                silenced = True
            # roomHouseFoyer
            if roomID == "roomHouseFoyer" and roomCurrent.firstTime:
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"It seems your power is beyond that of my own. I don't know how you got through that door as I had enchanted it with my strongest incantations. If what I fear is true, then I will simply make one request of you: See me at my office. There is one final thing to discuss.")

            # Unlock shrine to get mysterious book
            if roomID == "roomRoad2Mid" and silenced and not roomRoad2East.itemFound:
                roomRoad2East.itemFound = True
                roomRoad2East.firstTime = True
                roomRoad2East.eastBlocked = False

            # End game dialogue
            if roomID == "roomHouseOffice":
                # Eden asks to give staff at firstTime
                if not roomCurrent.characterDead:
                    # If player gives staff: counter_1 = 1 and isGive = False
                    if roomCurrent.counter_1 == 1 and roomCurrent.isGive == False:
                        # spawn Vesh ()
                        roomCurrent.counter_2 = 1
                    # If player does not give staff: counter_1 = 0 and isGive = True
                    elif roomCurrent.counter_1 == 0 and roomCurrent.isGive == True:
                        # If player does something else, or nothing, given a second chance
                        roomCurrent.counter_3 += 1
                        if roomCurrent.counter_3 == 2:
                            # Roquefort asks again (print)
                            print("Roquefort asks you again. \"I plead of you. Break free from the demon's control and give me the staff.\"")
                        elif roomCurrent.counter_3 > 2:
                            # Roquefort kills you if you reject 2nd opportunity to give
                            print("\"If you will not give me the staff, then I will take it by force\" Roquefort extends his arms forward and shoots a fireball at you.")
                            endGame = True
                # If player kills Roquefort
                # Vesh is in room, uninjured
                elif roomCurrent.counter_2 == 1: # staff has been used once or Eden is dead
                    # Spawn Vesh (print)
                    # Reveals self
                    # Tries to kill you
                    roomCurrent.counter_4 += 1
                    if roomCurrent.counter_4 == 1:

                        if roomCurrent.counter_1: # attemptted to give staff
                            response = "\"Fool!\" She transforms into a scaly demon, sprouting wings and sharp claws. \"IZH ICHA VO'FEK OZH DOMOSH! IZH VO'POZ!\" You lose all control of your body and immediately grab the staff back. You aim at Roquefort and fire a bolt of lighting, killing him. \"INSOLENT HUMAN. YOU DARE DEFY ME IN GIVING THE STAFF TO THE ENEMY? NO MATTER, FOR I AM VESH'KATHAL, LAST OF THE AHM'FOL, DECEIVER OF MANY, AND NOW, THANKS YOU TO, RULER OF KASHKAVAL. WITH THE FINAL PROPHET OF GARROTXA DEAD, NO ONE WILL BE ABLE TO STOP MY REIGN. NOW, YOU TOO MUST DIE, AND THE STAFF OF GARROTXA SHALL BE DESTROYED.\"\nYou feel the control over your own body returning."
                            if not word_stop:
                                word_stop = 1
                            if not word_servant:
                                word_servant = 1
                            inv.staff += 1
                        else:
                            response = "\"You did it! Finally, Roquefort has fallen. Peace can be restored to all of Kashkaval. After all this, I think I ought to introduce myself.\" She transforms into a scaly demon, sprouting wings and sharp claws. \"OZKAVOSH ICHA DOMOSH SA NITH. I AM VESH'KATHAL, LAST OF THE AHM'FOL, DECEIVER OF MANY, AND NOW, THANKS YOU TO, RULER OF KASHKAVAL. WITH THE FINAL PROPHET OF GARROTXA DEAD, NO ONE WILL BE ABLE TO STOP MY REIGN. NOW, YOU TOO MUST DIE, AND THE STAFF OF GARROTXA SHALL BE DESTROYED.\""
                            if not word_servant:
                                word_servant = 1
                        print("\nYou hear a loud crack of thunder and a mystical portal opens in the office. The woman veiled in white robes enters the room.",response)
                    # Player does not respond and dies
                    else:
                        print("Vesh'kathal lets out a bellowing roar. \"OZH GLUTH IZH SOL!\"\n\nYou are overwhelmed with pain and agony as you fall to your knees.")
                        if not spell_kill:
                            spell_kill = 1
                        endGame = True
                # Player injures vesh after reveal and loses staff
                elif roomCurrent.counter_2 == 2:
                    roomCurrent.counter_4 += 1
                    if roomCurrent.counter_4 == 3:
                        print("Vesh'kathal recovers from her burns, and gets back up on her feet.")
                    elif roomCurrent.counter_4 > 3:
                        print("Vesh'kathal lets out a bellowing roar. \"OZH GLUTH IZH SOL!\"\n\nYou are overwhelmed with pain and agony as you fall to your knees.")
                        if not spell_kill:
                            spell_kill = 1
                        endGame = True
                else:
                    # Vesh resists hit and tries to kill you again (differently?)
                    if inv.potato:
                        # somehow involve potato
                        print("He grabs hold of you and pummels you against the wall. The potato you've been carrying falls out and hits her square in the face. \"GGAAAAAAAHHHH\" she screams in agony, \"A POTATO OF GARROTXA. MY GREATEST WEAKNESS. I THOUGHT I WAS SAFE WHEN I BURNT DOWN EVERY LAST CROP ON THAT CURSED FARM.\" Streams of light begin to errupt from Vesh'kathal's body, paralyzing her. She turns to stone shortly before she explodes, sending shards of rock flying across the room.\n\nYou take a deep breath and walk over the Roquefort's corpse. You take a closer look at his ragged clothing and and notice it's made out of cheesecloth. As soon as you lay hands on it, you feel a surge of energy throughout your body and hear a booming voice from the heavens.\n\n\"I have returned.\"\n")
                        if not word_curse:
                             word_curse = 1
                        win = True
                    else:
                        # Kills you
                        print("Vesh'kathal lets out a bellowing roar. \"OZH GLUTH IZH SOL!\"\n\nYou are overwhelmed with pain and agony as you fall to your knees.")
                        if not spell_kill:
                            spell_kill = 1
                    endGame = True

        roomCurrent.firstTime = False

    # End Game Message
    if win:
        print("Congratulations! You win!\nYou took %s turns.\n\nTotal deaths: %s\nTotal turns: %s\n" % (turnCounter,deaths_total,turnCounter_total))
    else:
        deaths_total += 1
        if stat.health <= 0:
            cause = " from bleeding out"
        elif stat.hunger <= 0:
            cause = " from starvation"
        else:
            cause = ""
        print("Oh no! You died%s.\nYou took %s turns this life.\n\nTotal deaths: %s\nTotal turns: %s\n" % (cause,turnCounter,deaths_total,turnCounter_total))

    # Restart the program
    repeat()
# Start game
menu()
