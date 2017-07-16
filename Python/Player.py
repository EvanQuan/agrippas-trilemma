# Contains all classes directly relevant to Player
# Stat
# Inventory
# Word
# DemonWords

from Constants import *

# Stats
class Stat(object):
    def __init__(self, hunger = HUNGER_START, health = HEALTH_MAX, shield = 0, objectiveMain = 0, objectiveSecondary = [0], objectiveSecondaryBlock = [0]):
        self.hunger = hunger
        self.health = health
        self.healthmax = HEALTH_MAX
        self.shield = shield
        self.objectiveMain = objectiveMain
        self.objectiveSecondary = objectiveSecondary
        self.objectiveSecondaryBlock = objectiveSecondaryBlock
    def examineHunger(self):
        if self.hunger <= 1:
            print("You are about to die from starvation.")
        elif self.hunger <= 3:
            print("You feel extremely hungry.")
        elif self.hunger <= 6:
            print("You feel very hungry.")
        elif self.hunger <= 9:
            print("You feel quite hungry.")
        elif self.hunger <= 20:
            print("You feel moderately hungry.")
        elif self.hunger <= 30:
            print("You feel a bit hungry.")
        elif self.hunger <= HUNGER_MAX:
            print("You feel satisfied.")
        else:
            print("\nYou feel full.")
    def examineHealth(self):
        if self.health == 7:
            print("You are healthy.")
        elif self.health in range(2,7):
            print("You're bleeding out.")
        elif self.health == 1:
            print("You are about to die from your wounds.")
    def lowerHunger(self):
        self.hunger -= 1
    def lowerHealth(self):
        if self.shield:
            print("The effects of the potion of rejuvination protect you from harm.")
        else:
            self.health -= 1
    def lowerShield(self):
        if self.shield:
            self.shield -= 1
            if self.shield == 1:
                print("\nYou feel the protective power of the potion of rejuvination dissipate.")
    def setObjectiveMain(self,option):
        if option > self.objectiveMain:
            self.objectiveMain = option
    def addObjectiveSecondary(self,option):
        if option not in self.objectiveSecondaryBlock:
            self.objectiveSecondary.append(option)
            self.objectiveSecondaryBlock.append(option)
    def removeObjectiveSecondary(self,option):
        self.objectiveSecondaryBlock.append(option)
        if option in self.objectiveSecondary:
            self.objectiveSecondary.remove(option)
    def printObjective(self):
        # Main goal
        print("Main Objective:",end = "\n    - ")
        if self.objectiveMain == 0:
            print("Escape the jail cell")
        elif self.objectiveMain == 1:
            print("Get the staff of Garrotxa from the Temple of Garrotxa")
        elif self.objectiveMain == 2:
            print("Go through Mount Magna Mine to reach Eden von Roquefort's house")
        elif self.objectiveMain == 3:
            print("Get inside of Eden von Roquefort's house")
        elif self.objectiveMain == 4:
            print("Kill Eden von Roquefort")
        elif self.objectiveMain == 5:
            print("Kill Vesh'kathal the Deceiver")
        # Immediate problems to solve
        print("\nSecondary Objective:")
        if 0 in self.objectiveSecondary:
            print("    - ",end="")
            print("Have enough food to stay alive")
        if 1 in self.objectiveSecondary:
            print("    - ",end="")
            print("Find enough money to stay alive")
        if 2 in self.objectiveSecondary:
            print("    - ",end="")
            print("Buy a raffle ticket from the Wonderful Wheel of Mystery")
        if 3 in self.objectiveSecondary:
            print("    - ",end="")
            print("Give the raffle ticket to the spokesperson to get reimbursed")
        if 4 in self.objectiveSecondary:
            print("    - ",end="")
            print("Give the shrubbery to the guard at the town gate")
        if 5 in self.objectiveSecondary:
            print("    - ",end="")
            print("Get enough gold/funnel cakes to bypass the troll at the bridge")
            print("        - ",end="")
            print("Mine coal in Mount Magna")
            print("        - ",end="")
            print("Explore Mount Magna")
            print("        - ",end="")
            print("Complete tasks for people around Airedale")
            print("        - ",end="")
            print("Play Sybil's Shell Game at the Carnival")
        if 6 in self.objectiveSecondary:
            print("    - ",end="")
            print("Give the dragonstone to Tim the Enchanter")
        if 7 in self.objectiveSecondary:
            print("    - ",end="")
            print("Give the wooden bird to the stranger at the lake")
        if 8 in self.objectiveSecondary:
            print("    - ",end="")
            print("Learn the spell from the stone tablet at the lake")
        if 9 in self.objectiveSecondary:
            print("    - ",end="")
            print("Open the lockbox at the lake")
        if 10 in self.objectiveSecondary:
            print("    - ",end="")
            print("Unlock the vault door at the Mount Magna Mine entrance")
        if 11 in self.objectiveSecondary:
            print("    - ",end="")
            print("Find a way to get across the crevasse to reach the treasure chest in Mount Magna Mine")
        if 12 in self.objectiveSecondary:
            print("    - ",end="")
            print("Open the treasure chest in Mount Magna Mine")
        if 13 in self.objectiveSecondary:
            print("    - ",end="")
            print("Find a way to get through the rubble blocking the Temple of Garrotxa entrance")
        if 14 in self.objectiveSecondary:
            print("    - ",end="")
            print("Solve the ghostly figure's riddle")
        if 15 in self.objectiveSecondary:
            print("    - ",end="")
            print("Get past the black knight")
        if 16 in self.objectiveSecondary:
            print("    - ",end="")
            print("Escape the creature's lair")
        if 17 in self.objectiveSecondary:
            print("    - ",end="")
            print("Find a way to get into Eden von Roquefort's house without using spells")
        if 18 in self.objectiveSecondary:
            print("    - ",end="")
            print("Go into the obsidian hemisphere")
        if 19 in self.objectiveSecondary:
            print("    - ",end="")
            print("Solve the puzzle of the gargoyle")
        if 20 in self.objectiveSecondary:
            print("    - ",end="")
            print("Solve the puzzle of the coloured rooms")
        if 21 in self.objectiveSecondary:
            print("    - ",end="")
            print("Solve the puzzle to get through the metal door")
        if 22 in self.objectiveSecondary:
            print("    - ",end="")
            print("Alternatively, give the staff of Garrotxa to Eden von Roquefort")


#### Continue - Finish turning items into subclasses and revamp take/drop/examine
# Inventory
class Inventory(object):
    def __init__(self, name = "room", gold = 0, letter = 0, key = 0, keySkeleton = 0, pickaxe = 0, shrubbery = 0, funnelCake = 0, halfFunnelCake = 0, foot = 0, porridge = 0, bowl = 0, lantern = 0, oil = 0, pie = 0, coal = 0, biscuit = 0, hook = 0, staff = 0, ticket = 0, potato = 0, bandage = 0, journal = 0, book = 0, brie = 0, munster = 0, stilton = 0, swiss = 0, wensleydale = 0, potion = 0, flask = 0, stone = 0, bird = 0, note = 0, memo = 0, note_1 = 0, note_2 = 0, note_3 = 0, note_4 = 0):
        self.name = name
        self.gold = gold
        self.goldDescription = "The edges are worn down from handling."
        self.letter = letter
        self.letterDescription = "Made out of old parchment, the message on it is written in ink."
        self.letterRead = "It reads:\n\nTo " + str(playerName) + ",\n\nA certain Eden Von Roquefort has set up residence NORTH of MOUNT MAGNA. While he purports to be a lowly cheese mage, reliable sources claim him to be the demon lord, Vesh'kathal the Deceiver, a shapeshifter infamous of manipulating the minds and bending the wills of others. Legend tells of a saviour, deemed the Monterey Messiah, who will save all of Kashkaval from his wickedness. It has be brought to my attention that you are that saviour that the legends speak of. While I have very important matters to attend to, the best I can do is help instruct you in how to defeat this demon lord:\n\nFIRST, you must acquire the staff from the Garrotxian temple NORTHEAST of this town, for it is the only weapon capable of defeating such a powerful demon.\n\nNEXT, once you have the staff, go NORTH through the MINES of MOUNT MAGNA and find him at his house on the other end.\n\nFINALLY, kill Roquefort and Kashkaval will be saved from his wrath.\n\nI know this is probably a lot to digest at once, but you are our only hope. I fear in your attempt to complete this task, Vesh'kathal will attempt to thwart you. He may attempt to contact and manipulate you, or have his minions work to stop you. Whatever he does, you must persevere.\n\nMay you be blessed,\n\nThe last prophet of Garrotxa"
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
        self.porridgeDescription = "Bland prison food. Makes you consider if eating this everyday was the real punishment."
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
        self.potatoDescription = "It looks rotten and covered in ash. You probably shouldn't eat it."
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
        self.bird = bird
        self.birdDescription = "It's crudely carved to look like a raven."
        self.note = note
        self.noteDescription = "Some numbers are written on the small slip of paper."
        self.note_1 = note_1
        self.note_2 = note_2
        self.note_3 = note_3
        self.note_4 = note_4
        self.noteRead = "It reads: \"If you must enter, bring a LIGHT SOURCE to keep the creature away. It won't work forever, but it will give you some time. The vault code is %s%s%s%s.\"" % (note_1,note_2,note_3,note_4)
        self.memo = memo
        self.memoDescription = "The edges are burnt and the parchment is covered in ash."
        self.memoRead = "It reads:\n\n\"To whoever is still alive,\n\nBy the time you read this, I will probably be dead. After I learned I could say \"EYIK VO'HOLLOM\" to enter the obisdian hemispheres, I came here from Airedale through one of them in order to help look for survivors up North. The demons went from farm to farm, burning all the crops down, and got me before I could escape. If you are to save this world from demon-kind, you must vanquish them with the staff of Garrotxa back in my home town. It is our only hope.\""
        # "It reads:\n\nTo whoever is still alive,\n\nBy the time you read this, I will probably be dead. It turns out POTATOES, of all things, are the Ozkavosh's greatest WEAKNESS. After I learned I could say \"EYIK VO'HOLLOM\" to enter the obisdian hemispheres, I came here from Airedale through one in order to get as many potatoes as I could find. The demons went from farm to farm, burning all the crops down, and got me before I could escape. If you are to save this world from demon-kind, you must vanquish them with a potato. Either that, or get hold of the staff of Garrotxa back in my town."
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
        if self.bird:
            type_count += 1
        if self.note:
            type_count += 1
        if self.memo:
            type_count += 1
        return type_count
    def examineInventory(self, option, roomCurrent):
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
                if self.memo:
                    print("    a memo")
                if self.book == 1:
                    print("    a mysterious book")
                elif self.book > 1:
                    print("   ",self.book,"mysterious books")
                if self.note:
                    print("    a note")
                if self.pickaxe == 1:
                    print("    a pickaxe")
                elif self.pickaxe > 1:
                    print("   ",self.pickaxe,"pickaxes")
                if self.coal == 1:
                    print("    a piece of coal")
                elif self.coal > 1:
                    print("   ",self.coal,"pieces of coal")
                if self.potato:
                    print("    a rotten potato")
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
                if self.bird:
                    print("    a wooden bird")
        # Individual items
        elif option == "gold" and (self.gold or roomCurrent.gold):
            print(self.goldDescription)
        elif option == "letter" and (self.letter or roomCurrent.letter):
                print(self.letterDescription)
        elif option == "key" and (self.key or roomCurrent.key):
            print(self.keyDescription)
        elif option == "key of Ahm'domosh" and (self.keySkeleton or roomCurrent.keySkeleton):
            print(self.keySkeletonDescription)
        elif option == "pickaxe" and (self.pickaxe or roomCurrent.pickaxe):
            print(self.pickaxeDescription)
        elif option == "shrubbery" and (self.shrubbery or roomCurrent.shrubbery):
            print(self.shrubberyDescription)
        elif option == "funnelCake" and (self.funnelCake or roomCurrent.funnelCake):
            print(self.funnelCakeDescription)
        elif option == "half-eaten funnel cake" and (self.halfFunnelCake or roomCurrent.halfFunnelCake):
            print(self.halfFunnelCakeDescription)
        elif option == "lucky rabbit foot" and (self.foot or roomCurrent.foot):
            print(self.footDescription)
        elif option == "bowl of porridge" and (self.porridge or roomCurrent.porridge):
            print(self.porridgeDescription)
        elif option == "bowl" and (self.bowl or roomCurrent.bowl):
            print(self.bowlDescription)
        elif option == "lantern" and (self.lantern or roomCurrent.lantern):
            print(self.lanternDescription)
        elif option == "vial of oil lantern" and (self.oil or roomCurrent.oil):
            print(self.oilDescription)
        elif option == "chicken pot pie" and (self.pie or roomCurrent.pie):
            print(self.pieDescription)
        elif option == "hardtack biscuit" and (self.biscuit or roomCurrent.biscuit):
            print(self.biscuitDescription)
        elif option == "grappling hook" and (self.hook or roomCurrent.hook):
            print(self.hookDescription)
        elif option == "staff of Garrotxa" and (self.staff or roomCurrent.staff):
            print(self.staffDescription)
        elif option == "raffle ticket" and (self.ticket or roomCurrent.ticket):
            print(self.ticketDescription)
        elif option == "piece of coal" and (self.coal or roomCurrent.coal):
            print(self.coalDescription)
        elif option == "potato" and (self.potato or roomCurrent.potato):
            print(self.potatoDescription)
        elif option == "bandage" and (self.bandage or roomCurrent.bandage):
            print(self.bandageDescription)
        elif option == "journal" and (self.journal or roomCurrent.journal):
            print(self.journalDescription)
        elif option == "book" and (self.book or roomCurrent.book):
            print(self.bookDescription)
        elif option == "brie" and (self.brie or roomCurrent.brie):
            print(self.brieDescription)
        elif option == "slice of munster cheese" and (self.munster or roomCurrent.munster):
            print(self.munsterDescription)
        elif option == "slice of stilton cheese" and (self.stilton or roomCurrent.stilton):
            print(self.stiltonDescription)
        elif option == "slice of swiss cheese" and (self.swiss or roomCurrent.swiss):
            print(self.swissDescription)
        elif option == "slice of wensleydale cheese" and (self.wensleydale or roomCurrent.wensleydale):
            print(self.wensleydaleDescription)
        elif option == "potion or rejuvination" and (self.potion or roomCurrent.potion):
            print(self.potionDescription)
        elif option == "flask" and (self.flask or roomCurrent.flask):
            print(self.flaskDescription)
        elif option == "dragonstone" and (self.stone or roomCurrent.stone):
            print(self.stoneDescription)
        elif option == "wooden bird" and (self.bird or roomCurrent.bird):
            print(self.birdDescription)
        elif option == "note" and (self.note or roomCurrent.note):
            print(self.noteDescription)
        elif option == "memo" and (self.memo or roomCurrent.memo):
            print(self.memoDescription)
        else:
            if option == "gold":
                print("There is no gold here to examine.")
            else:
                print("There is no",option,"here to examine.")

class Word(object):
    def __init__(self, name, translation, isKnown = False, isASpell = False):
        self.name = name
        self.translation = translation
        self.isKnown = isKnown
        self.isASpell = isASpell
    def learn(self):
        self.isKnown = True

class DemonWords(object):
    def __init__(self, wordList = []):
        self.wordList = wordList
    def add(self, inWord): # Player has come across word but does not know what it means
        if (type(inWord) is Word) and (inWord not in self.wordList):
            self.wordList.append(inWord)
    def learn(self, inWord): # Players knows the meaning of the word
        if inWord not in self.wordList:
            self.add(inWord)
        inWord.learn()
    def hasSpells(self):
        spellsKnown = False
        for word in self.wordList:
            if word.isKnown and word.isASpell:
                spellsKnown = True
        return(spellsKnown)
    def hasNonSpells(self):
        nonSpellsKnown = False
        for word in self.wordList:
            if word.isKnown and not word.isASpell:
                nonSpellsKnown = True
        return(nonSpellsKnown)
    def hasUnknownWords(self):
        UnknownWordsKnown = False
        for word in self.wordList:
            if not word.isKnown:
                UnknownWordsKnown = True
        return(UnknownWordsKnown)
    def hasWords(self):
        wordsKnown = False
        if (self.hasSpells() or self.hasNonSpells() or self.hasUnknownWords()):
            wordsKnown = True
        return(wordsKnown)
    def printWords(self):
        if (self.hasWords()):
            if self.hasSpells():
                print("Spells:")
                for word in self.wordList:
                    if word.isKnown and word.isASpell:
                        print("    " + word.name)
                        print("\t" + word.translation)
            if self.hasNonSpells():
                print("Non-Spells:")
                for word in self.wordList:
                    if word.isKnown and not word.isASpell:
                        print("    " + word.name + " - " + word.translation)
            if self.hasUnknownWords():
                print("Unknown:")
                for word in self.wordList:
                    if not word.isKnown:
                        print("    " + word.name)
        else:
            print("You do not know any Ozhkavosh words.")

#_______Stat Init
stat = Stat()

# Spells
spellLearn = Word(name = "Ozh ensh", translation = "I learn this - Translates the meaning and effects of words from Ozkavosh.", isASpell = True)
spellUnlock = Word(name = "Ozh vo'ses sa", translation = "I unlock this - Opens any lock.", isASpell = True)
spellPersuade = Word(name = "Izh vo'poz", translation = "You have no power - Perusades those blocking the way.", isASpell = True)
spellKillself = Word(name = "Ozh sol fek", translation = "My life ends - Kills self.", isASpell = True)
spellJump = Word(name = "Ozh thok alatho", translation = "I move forward - Crosses large gaps.", isASpell = True)
spellLight = Word(name = "Ozh groth sol", translation = "I open the light - Creates a glowing ball that illuminates your surroundings.")
spellOblivion = Word(name = "Eyik vo'hollom", translation = "Behold oblivion - Opens obsidian hemispheres.", isASpell = True)
spellHeal = Word(name = "Ozh vo'irush", translation = "I am without illness - Mends all wounds and illnesses.", isASpell = True)
spellFeed = Word(name = "Ozh gluth nith", translation = "I consume the Earth - Satisfies all hunger.", isASpell = True)
spellKill = Word(name = "Ozh gluth izh sol", translation = "I consume your soul - Kills your enemies.", isASpell = True)
# Non-spells
wordDarkness = Word(name = "Omoz gloth izh", translation = "Darkness welcomes you.")
wordMirror = Word(name = "Izh tal el ozh icha rek'tal", translation = "Talk and I will mirror you.")
wordDominion = Word(name = "Ahm'domosh", translation = "Highest dominion")
wordReign = Word(name = "Ozkavosh icha domosh sa nith", translation = "Demon-kind shall reign upon this land.")
wordStop = Word(name = "Izh icha vo'fek ozh domosh", translation = "You will not stop my reign.")
wordServant = Word(name = "Ahm'fol", translation = "Servant of Vesh'arkosh.")
wordCurse = Word(name = "Sof izh", translation = "Curse you.")


demonWords = DemonWords()
