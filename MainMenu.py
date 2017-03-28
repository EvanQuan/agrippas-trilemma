# Main Menu
# Splash Screen
# All credit goes to:
#   http://www.chris.com/ascii/index.php?art=art%20and%20design/borders
#   http://patorjk.com/software/taag/
#

from Constants import *
import Changelog
import Main
import getpass

class MainMenu(object):
    def __init__(self, option = False, debugOn = False):
        self.debugOn = debugOn
        self.STARTGAME = 1
        self.HOWTOPLAY = 2
        self.ABOUTTHISGAME = 3
        self.QUIT = 4
        self.DEBUG = 5
        self.startGame = ("1","1.","start","start game","1. start game")
        self.howToPlay = ("2","2.","how to play","2. how to play")
        self.aboutThisGame = ("3","3.","about","about game","about this game")
        self.quit = ("4","4.","quit","4. quit","q")
        self.debug = "debug"
    def printSplashArt(self):
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
        print(" \     /                          A Text Adventure Game")
        print("  `---'")
        self.askSelection()
    def printCommandShortcuts(self):
        print("l - Look")
        print("x - Examine")
        print("i - Inventory")
        print("h - Examine Hunger")
        print("o - Current Objective")
        print("z - Wait")
        print("g - Again (Repeat previous action)")
        print("\nMovement:")
        print("n - Go North")
        print("e - Go East")
        print("w - Go West")
        print("s - Go South")
        print("u - Go Up")
        print("d - Go Down")
    def printHowToPlay(self):
        print("Type in commands to do stuff. See what works and what doesn't.")
        print("\nExample:")
        print("\n> get a 4.0 GPA")
        print("You cannot get that.")
        print("\n> drink eggnog")
        print("You drink the eggnog. It's like Christmas morning!")
        print("\n> give catnip to kitten")
        print("You give the catnip to the kitten, making it go crazy.")
        print("\n\n\nBesides that, there are some basic shortcut commands.\n")
        self.printCommandShortcuts()
        print("\nYou can recall these shortcuts in-game at any time by typing in \"help\".")
    def printAboutThisGame(self):
        # print("Cheese Quest: The Plight of Kashkaval was developed by Evan Quan from on Oct. 17, 2016 to " + DATE + ". The original motivation behind the game's creation was to complete an assignment for CPSC 231 (found here: http://pages.cpsc.ucalgary.ca/~tamj/2016/231F/assignments/assignment2/index.htm). It draws inspiration from a variety of other works including:\n    - Terry Jones' and Terry Gilliam's movie, \"Monty Python and the Holy Grail.\" (1975)\n    - Douglas Adams' and Steve Meretzky's text adventure game, \"Hitchhicker's Guide to the Galaxy.\" (1984)\n    - Irrational Games' first-person shooter, \"Bioshock\" (2007)\n    - Frictional Game's survival horror game, \"Amnesia: The Dark Descent.\" (2010)\n    - Bethesda Game Studio's open world RPG, \"The Elder Scrolls V: Skyrim.\" (2011)\n    - Blizzard's online CCG \"Heartstone.\" (2014)")
        print("Version " + Changelog.VERSION + "\n")
        print("Cheese Quest: The Plight of Kashkaval was developed by Evan Quan starting on Oct. 17, 2016 because he was bored and had a lot of free time.")
        option = ""
        yes = ("yes","y","yup","sure")
        no = ("no","n","nope","nah")
        while (option not in yes) and (option not in no):
            option = input("\nWould you like to see the changelog?\n(WARNING: Changelog contains spoilers)\n> ")
        if option in yes:
            print(Changelog.CHANGELOG)
    def askSelection(self):
        option = ""
        outSelection = 0
        validOption = (self.STARTGAME,self.HOWTOPLAY,self.ABOUTTHISGAME,self.QUIT,self.DEBUG)
        while outSelection not in validOption:
            print("\n    1. Start Game        2. How to Play        3. About this Game        4. Quit")
            option = input("\n\n\n\n> ").lower()
            if option in self.startGame:
                outSelection = self.STARTGAME
            elif option in self.howToPlay:
                self.printHowToPlay()
            elif option in self.aboutThisGame:
                self.printAboutThisGame()
            elif option in self.quit:
                outSelection = self.QUIT
            elif option in self.debug:
                password = getpass.getpass("Enter debug mode password.\n> ").lower()
                if password == PASSWORD:
                    outSelection = self.DEBUG
                else:
                    print("Incorrect.")
            else:
                print("Choose selection 1, 2, 3, or 4.")
        self.processSelection(outSelection)
    def processSelection(self, inSelection):
            outTask = False
            if inSelection == self.STARTGAME:
                Main.inGame(False)
            elif inSelection == self.QUIT:
                print("Okay. Bye!")
            elif inSelection == self.DEBUG:
                Main.inGame(True)
            if inSelection != self.QUIT:
                self.repeat()
    def repeat(self):
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
                password = getpass.getpass("Debug mode password: ").lower()
                if password == PASSWORD:
                    choice_made = True
                    debug = True
                else:
                    print("Incorrect.")
            else:
                print("Select 1, 2, or 3.")
        if option in ("1","play","play again"):
            self.processSelection(self.STARTGAME)
        elif option in ("2","return","menu","main menu","return to main menu"):
            self.printSplashArt()
        elif option in ("3","quit","q"):
            print("Okay. Bye!")
        elif debug:
            self.processSelection(self.DEBUG)
