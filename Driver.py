# Cheese Quest: The Plight of Kashkaval
# Evan Quan Oct. 17, 2016
# Single-player text based adventure game
# Save the world from the Cheese Mage!

# Developed in Python 3.5.2

# Inspired by: http://pages.cpsc.ucalgary.ca/~tamj/2016/231F/assignments/assignment2/index.htm
# Simple puzzles http://h2g2.com/approved_entry/A22196289

# Currently working on for 1.5.0
# Spaghetti code update
# Rework much of program to be Object Oriented
# Redistribute Main.py to other files
# Balancing numbers during playthrough
# POTENTIALLY: Change cave layout to be less confusing?
#   Remove south and inward dead ends

# Kidnapping


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


# add 1 more level to mysterious book
# find right action/verb for animal totems
# consider other interactions with mirror image
#   maybe make it do nothing but talk in reverse (how to make clear, add text?)

#____Less Important features_____


# First time descriptions
# Adds immersion, less repetitive

# Punch, kick
# If people in room

## continue##
# simplify cave, balance coal mine placement/coal quantity/price, monster spawn and chase
# have small opening with rewards
#   If not monster chase, inside small openings do not initiate lair chase
#   View adjacent room (outside hole) as the same light settings as hole


# change roquefort end talk
# more clear, just overall improve

# poison potion for alchemist
# ingredient in cave, near back
# use on funnel cake to make poisoned funnel cake
# Make moat around roquefort house instead of lock (allows use of ozh thok alatho, as well as opportunity to go back to town and buy grappling hook (difficult) instead)

# make skeleton key open lockbox, vault? first roquefort gate

# Fix more spacing for rooms, especially with dialogue
# Make going fullscreen unnecessary


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
#   class className
#   changeRoom (at start and at end loop)
#   Instantiate

from MainMenu import *
mainMenu = MainMenu()
mainMenu.printSplashArt()
