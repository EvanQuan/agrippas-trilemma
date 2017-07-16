from Constants import *
import random

#______Rooms____________________________________________________
# Room information
#	 Description
#	 Adjacent Rooms
#	 Check Adjacent Rooms
#	 Items Present

# Super Rooms
class Room(object):
    def __init__(self, name = "name", inv = Inventory(), coordinates, gold = 0, letter = 0, key = 0, keySkeleton  = 0, pickaxe = 0, shrubbery = 0, funnelCake = 0, halfFunnelCake = 0, foot = 0, porridge = 0, bowl = 0, lantern = 0, oil = 0, pie = 0, biscuit = 0, hook = 0, staff = 0, ticket = 0, coal = 0, potato = 0, bandage = 0, journal = 0, book = 0, brie = 0, munster = 0, stilton = 0, swiss = 0, wensleydale = 0, potion = 0, flask = 0, stone = 0, bird = 0, note = 0, memo = 0, north = False, northBlocked = False, northBlockedReason = False, east = False, eastBlocked = False, eastBlockedReason = False, south = False, southBlocked = False, southBlockedReason = False, west = False, westBlocked = False, westBlockedReason = False, up = False, upBlocked = False, upBlockedReason = False, down = False, downBlocked = False, downBlockedReason = False,  betMade = False, counterAns_1 = 0, counterAns_2 = 0, counterAns_3 = 0, counterAns_4 = 0, counter_1 = 0, counter_2 = 0, counter_3 = 0, counter_4 = 0, characterDead = True, itemFound = False, firstTime = True, isBet = False, isBuy = False, isCrevasse = False, isGive = False, isMine = False, isFill = False, isSell = False):
        self.name = name
        self.inv = inv
        # Coordinates
        self.coordinates = coordinates
        # Check Adjacent Rooms
        self.northBlocked = northBlocked
        self.northBlockedReason = northBlockedReason
        self.eastBlocked = eastBlocked
        self.eastBlockedReason = eastBlockedReason
        self.southBlocked = southBlocked
        self.southBlockedReason = southBlockedReason
        self.westBlocked = westBlocked
        self.westBlockedReason = westBlockedReason
        self.upBlocked = upBlocked
        self.upBlockedReason = upBlockedReason
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
        print("\nRoom Name\n\nDescription placeholder.\n")
        # Adjacent Rooms
        print("North is north room.")
        print("East is east room.")
        print("South is south room.")
        print("West is west room.")
    def add(self,inCount,inItem):
        if inItem in self.inv:
            ## continue
        else:
            inv.append(inItem)
    # def getInventoryTypes(): # This should just be a method of inventory
    #     return(len(inv))
    def printItemsPresent(self):# Should be a method of inventory
        # Items Present
        if self.itemTypes():
            if self.itemTypes() > 1:
                print("\nThere are some items here:")
            else:
                print("\nThere is an item here:")
        print(inv):
        # Delete below
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
        elif self.bird:
            print("    a wooden bird")

class Store(Room):
    pass

class Cave(Room):
    pass

class SpecialCave(Cave):
    pass

class Lair(Room):
    pass

class BookRoom(Room):
    pass

class Road(Room):

#_______Jail____________________________________________________________________
# Cell
class classJailCell(Room):
    # Description
    def description(self):
        print("\nJail Cell\n\nThere are stone walls enclosed around you.\nThere is a solid metal door with a small slit at eye-level peering into the corridor.\nThere is a HAYSTACK here.\n")
        # Adjacent Rooms
        print("    East is the corridor.")
# Corridor
class classJailCorridor(Room):
    # Description
    def description(self):
        print("\nCorridor\n\nJail cells line the the hallway on both sides.\n")
        # Adjacent Rooms
        print("    North is the antechamber.")
        print("    West is is your jail cell.")
# Antechamber
class classJailAntechamber(Room):
    def description(self):
        print("\nAntechamber\n\nThe stone walls are close together, leaving just enough room for you to stand in.\n")
        print("    Down is the foyer.")
        print("    South is the corridor.")
# Foyer
class classJailFoyer(Room):
    # Description
    def description(self):
        print("\nFoyer\n\nThe place is rather cozy and well-lit. Definitely a contrast from all those years in your cell.\n")
        # Adjacent Rooms
        print("    Up is the antechamber.")
        print("    East is the hallway.")
        print("    West is outside.")
# Hallway
class classJailHallway(Room):
    # Description
    def description(self):
        print("\nHallway\n\nLight from outside pears in from the Northern windows. This is the first time you've felt sunlight in ages.\n")
        # Adjacent Rooms
        print("    East to the break room.")
        print("    West is inside the jail.")
# Break Room
class classJailBreakRoom(Room):
    # Description
    def description(self):
        print("\nBreak Room\n\nThere's some comfortable chairs and tables scattered around the room.\n")
        # Adjacent Rooms
        print("    West is the hallway.")
        print("    South is the washroom.")
# Entrance
class classJailEntrance(Room):
    # Description
    def description(self):
        if self.firstTime:
            firsttime = "Finally, outside! This is the first time you've felt sunlight in ages. "
        else:
            firsttime = ""
        print("\nJail Entrance\n\n%sA wall surrounds the prison from all sides, with a gate leading north.\n" % (firsttime))
        # Adjacent Rooms
        print("    East is the foyer.")
        print("    West is the courtyard.")
#_______Town____________________________________________________________________
# Courtyard
class classCourtyardNorth(Room):
    # Description
    def description(self):
        if self.firstTime:
            firsttime = "A young urchin boy rings a bell, yelling \"Come one, come all, to the WONDERFUL WHEEL OF MYSTERY! Short on cash? Want to get rich quick? Spin the wheel and test your luck! Only at the AIREDALE CARNIVAL!\""
        elif roomCarnivalWheelGame.characterDead:
            firsttime = ""
        else:
            firsttime = "A young urchin boy rings a bell, yelling \"The WONDERFUL WHEEL OF MYSTERY will be announcing the winner soon! Get your TICKET while you can!\""
        print("\nNorth Courtyard\n\nA crowd of people fill the streets.\nVarious buildings surround the courtyard from every angle.\n" + firsttime + "\n")
        # Adjacent Rooms
        print("    North is the town gate.")
        print("    East is the general store.")
        print("    West is the carnival grounds.")
        print("    South is the south courtyard.")
class classCourtyardSouth(Room):
    # Description
    def description(self):
        print("\nSouth Courtyard\n\nA large elegant fountain rests in the centre, surrounded by busy people minding their own business.\n")
        # Adjacent Rooms
        print("    North is the north courtyard.")
        print("    East is the jail entrance.")
        print("    West is the blacksmith.")
        print("    South is the alchemist's hut.")
# Blacksmith
class classBlacksmith(Room):
    # Description
    def description(self):
        print("\nBlacksmith\n")
        if self.characterDead:
            print("There is a charred body here.\nAn open furnace lights up the place and several tools lay on an adjacent table.")
        else:
            print("A man in an apron rests at stand near the entrance.\nBehind him, an open furnace lights up the place and several of his tools lay on an adjacent table.")
            if self.firstTime:
                print("\"You new here mate? Don't recognize the face. Buy anything here you'd like. Ever since the incident at the MOUNT MAGNA MINE, I've been running low on COAL to keep my furnace going. However, if you've got the guts to go up there, I'd be willing to buy any off from you.\"")
            else:
                print("\"Y'here to buy anything? If not, I'd be willin' to buy any COAL off you. In times like these, I could always use some more fuel for my fire.\"")
            print("\nGoods available to buy:\n    Pickaxe (%s gold)\n    Grappling Hook (%s gold)" % (PRICE_BUY_PICKAXE,PRICE_BUY_HOOK))
            print("\nGoods available to sell:\n    Coal (%s gold)" % PRICE_SELL_COAL)
        if self.isBuy:
            print("\nYou have",inv.gold,"gold.")
            if inv.coal > 1:
                print("You have",inv.coal,"pieces of coal.")
            elif inv.coal:
                print("You have",inv.coal,"piece of coal.")
        # Adjacent Rooms
        print("\n    East is the courtyard.")
# Alchemist
class classAlchemist(Room):
    def description(self):
        print("\nAlchemist's Hut\n")
        if self.characterDead:
            dead = "The dead body of Tim the Enchanter is here."
        else:
            dead = "An old man, wearing black robes and a leather cap with goat horns wanders around from shelf to shelf, examining various strange ingredients."
        if self.firstTime:
            firsttime = "\"Sorry, the shop is closed. I ran out of crushed dragonstone powder, a very rare and expensive ingredient necessary for all my potions. If you could find me a DRAGONSTONE, I would be willing to buy it off from you, and I'll be able to start up my business again. How does %s gold sound? Oh, I almost forgot to introduce myself. I'm Tim. Tim the Enchanter.\"" % PRICE_SELL_STONE
        else:
            if self.isBuy:
                firsttime = "\"Come on in. The fire's roaring. Business has been going great since you got me that dragonstone. What can I get for you?\""
            else:
                firsttime = "\"Hello again. I'm still empty on crushed dragonstone powder so I won't be able to make any potions. If you have a dragonstone, the offer is still on.\""
        if self.isBuy:
            buy = "filled with a bubbling, red liquid, rests above hot coals."
        else:
            buy = "rests above an fire, filled only with water."
        print("A large, cast-iron cauldron " + buy + "\n" + dead + " \n" + firsttime)
        if self.isBuy: # and self.isFill:
            outofstock = ""
        else:
            outofstock = " [OUT OF STOCK]"
        if not self.characterDead:
            print("\nGoods available to buy:\n    Potion of rejuvination (%s gold)%s" % (PRICE_BUY_POTION,outofstock))
            print("\nGoods available to fill:\n    Flask (%s gold)%s" % (PRICE_REFILL_POTION,outofstock))
        if self.isSell:
            print("\nGoods available to sell:\n    Dragonstone (%s gold)" % PRICE_SELL_STONE)
        if self.isBuy:
            print("\nYou have",inv.gold,"gold.")
        print("\n    North is the courtyard.")
# Town Gate
class classGate(Room):
    # Description
    def description(self):
        if self.northBlocked:
            if self.firstTime:
                firsttime = "\"Hey you there!\" says one of the guards as he approachs you. He looks around cautiously before lowering his voice.\n\"I recognize you! You were locked up years ago, you rat! I'd call for more guards to take you back to your cell, but I need something. You're a good thief and I'm sure you have a good eye.\nA while back, I lost a SHRUBBERY somewhere in this town.\nRetrieve it for me: one that looks nice and not too expensive, and I'll let you through the gate.\""
            else:
                firsttime = "\"Don't think you can get pass the gate until I get that SHRUBBERY. Try any funny business and I'll send you back to your cell.\""
        else:
            firsttime = "He recognizes you and opens it."
        print("\nTown Gate\n\nA guard stands at the gate.", firsttime)
        # Adjacent Rooms
        print("\n    North is a pathway.")
        print("    South is the courtyard.")
# General Store
class classGeneralStore(Room):
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
        print("\n    West is the courtyard.")
# Carnival
class classCarnival(Room):
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
        print("\nCarnival Grounds\n\nFestival lights are strung above, with colourful banners swaying in the wind.\nA large party of adults and children alike fill the carnival grounds, bustling about from tent to tent.")
        if self.isGive:
            print("The spokesperson is sitting outside at a table with two guards.\nA crowd of people surround her, GIVING her their raffle tickets.")
        print('\n    North is a tent labelled, "Funnel Cakes Galore."')
        print("    East is the courtyard.")
        print('    West is a tent labelled, "The Wonderful Wheel of %s."' % wClosed)
        print('    South is a tent labelled, "%s."' % sClosed)
# Carnival Shell Game
class classCarnivalShellGame(Room):
    def description(self):
        print("\nSybil\'s Shell Game")
        if self.isBet:
            print("\nInside the tent is an old woman hunched at a small, fragile wooden table.")
            if self.betMade:
                print("\nShe is waiting for you to chose a shell.")
            else:
                print('With only one eye, she turns to you and begins to chant.\n"Hear ye, hear ye! BET if you wish some GOLD out of glee! Test your LUCK with a guess from these shells three! Double your money if you win, you shall see!"')
        elif not self.characterDead:
            print("\nSybil is packing her stuff to leave the carnival.")
        else:
            print("\n    Inside the tent is a charred body on the ground, behind a fragile wooden table.")
        if self.isBet:
            print("\nYou have",inv.gold,"gold.")
        print("\n    North is the carnival grounds.")
# Carnival Food
class classCarnivalFood(Room):
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
        print("\n    South is the carnival grounds.")
class classCarnivalWheelGame(Room):
    # Description
    def description(self):
        print("\nWonderful Wheel of Mystery\n\nInside the tent is a large elevated platform.\nDisplayed in the centre is a large wheel with various numbers on it.")
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
        print("\n    East is the carnival grounds.")

#_______Outside____________________________________________________________________
# Road
class classRoadSouth(Room):
    def description(self):
        print("\nRoad\n\nThe gravel path extends out into the forest. Pine trees hug the path closely on both ends.")
        print("\n    North is a crossroads.")
        print("    South is the town gate.")
class classRoadMid(Room):
    def description(self):
        if self.itemFound:
            northD = "Mount Magna."
            eastD = "Temple of Garrotxa."
            westD = "Lake Laguiole."
        else:
            northD = "a road."
            eastD = "a road."
            westD = "a road."
        print("\nCrossroads\n\nFour paths meet here. There is a SIGN you can READ here.")
        print("\n    North is",northD)
        print("    East is",eastD)
        print("    West is",westD)
        print("    South is the town of Airedale.")
class classRoadNorth(Room):
    def description(self):
        print("\nRoad\n\nThe gravel path extends out into the forest. Pine trees hug the path closely on both ends.")
        print("\n    North is Mount Magna.")
        print("    South is a crossroads.")
class classRoadEast(Room):
    def description(self):
        print("\nRoad\n\nThe gravel path extends out into the forest. Pine trees hug the path closely on both ends.")
        print("\n    East is is a bridge.")
        print("    West is a crossroads.")
class classRoadWest(Room):
    def description(self):
        print("\nRoad\n\nThe gravel path extends out into the forest. Pine trees hug the path closely on both ends.")
        print("\n    East is a crossroads.")
        print("    West is a lake.")
class classRoadCorner(Room):
    def description(self):
        if not self.characterDead and self.counter_1:
            blackknight = " A knight, outfitted in black platemetal armour and equipped with a large broadsword stands in the way. He stands quietly."
        else:
            blackknight = ""
        print("\nRoad\n\nThe gravel path extends out into the forest. Pine trees hug the path closely on both ends.%s" % blackknight)
        print("\n    North is a temple.")
        print("    East is an opening in the forest.")
        print("    West is a bridge.")
class classForest(Room):
    def description(self):
        if self.eastBlocked:
            blocked = " The surface has no distinguishing features."
        else:
            blocked = " An opening leads inside."
        print("\nForest\n\nIn the middle of the foliage is a large obsidian hemisphere.%s\n" % blocked)
        print("    West is a road.")
        if not self.eastBlocked:
            print("    East is inside the obsidian hemisphere.")
# Lake
class classLake(Room):
    def description(self):
        if self.firstTime:
            firsttime = "A stranger lies by the shoreline.\n\"Hey you there! I lost my precious WOODEN BIRD in the MOUNT MAGNA MINE before it was locked down. If you could somehow get in there and GIVE me my bird back, I will surely make it worth your while. Please, I beg of you!\""
        elif self.characterDead:
            firsttime = "A dead body lies by the shoreline."
        elif not self.isGive:
            firsttime = "A stranger lies by the shoreline, happy to have his wooden bird back."
        else:
            firsttime = "A stranger lies by the shoreline. \"Are you back with my WOODEN BIRD from MOUNT MAGNA? I'll make it worth your while.\""
        print("\nLake Laguiole\n\nThe sun shimmers off the lake's surface.\nThere is a LOCKBOX here.\nThere is a STONE TABLET you can READ here.\n" + firsttime)
        print("\n    East is the crossroads.")
# Woods
class classShrineSouth(Room):
    def description(self):
        if self.firstTime and roomShrineNorth.firstTime:
            firsttime = " A voice echoes within the chamber, \"Omoz... gloth... izh...\""
        else:
            firsttime = ""
        print("\nSouth Shrine\n\nSteps lead down to a circular pit, shallowly filled with water.\nAround the edges of the room, floating candles dimly light the area.\nAlong the North wall is a MYSTICAL PORTAL." + firsttime + "\n")
        print("    North is through the portal.")
        print("    West is outside.")
# Bridge
class classBridge(Room):
    def description(self):
        print("\nBridge\n\nThe wooden bridge stretches across a river.",end = " ")
        if self.firstTime:
            firsttime = "He notices you, and says \"Bridge is home of Ugg. Ugg want funnel cakes. Ugg need gold to buy funnel cakes. Ugg no let you pass unless you GIVE Ugg GOLD or FUNNEL CAKES.\nUgg need %s gold to be happy.\nIf you give Ugg funnel cakes, that will be like giving Ugg %s gold each because it take a lot of work for Ugg to go buy funnel cakes.\"" % (TROLL_GOAL - invTroll.gold,TROLL_FUNNELCAKE_MULTIPLIER)
        else:
            firsttime = "He reminds you, \"Ugg need %s GOLD before you can pass. You can GIVE Ugg FUNNEL CAKES too, which count as %s gold each.\"" % (TROLL_GOAL - invTroll.gold,TROLL_FUNNELCAKE_MULTIPLIER)
        if self.eastBlocked:
            print("There is a troll standing on it, blocking the way. %s" % firsttime)
        elif not self.characterDead:
            print("The troll is happily sitting under the bridge, excited to see how many funnel cakes he can buy.")
        else:
            print("The body of the troll lays on the bridge, leaving some room to step over and across the other side.")
        if self.eastBlocked:
            print("\nYou have",inv.gold,"gold.")
            if inv.funnelCake > 1:
                print("You have",inv.funnelCake,"funnel cakes.")
            elif inv.funnelCake:
                print("You have",inv.funnelCake,"funnel cake.")
        print("\n    East is the crossroads.")
        print("    West is a road.")

# Temple
class classTempleEntrance(Room):
    def description(self):
        if self.northBlocked:
            rubble = ", blocked by RUBBLE"
        else:
            rubble = ""
        print("\nTemple Entrance\n\nWhite marble pillars support the temple roof, which is embellished with gold.\nA large stairway leads up towards the doorway entering the temple.\nA hedge surrounds the temple from the back and sides.\n")
        print("    North is inside the temple%s." % rubble)
        print("    South is a road.")
class classTempleInside(Room):
    def description(self):
        print("\nTemple\n\nA velvet carpet extends from the entrance to a pedestal in the center of the room.\nAt the back is a large statue of the goddess, Garrotxa, overlooking the room.\nThree MURALS you can READ with embroided text are laid out, spanning across the West, North, and East walls.\n")
        print("    Down is the antechamber.")
        print("    South is outside.")
class classTempleBasement(Room):
    def description(self):
        if self.staff:
            staffpresent = "A gem-encrusted staff rests on a stand atop it."
        else:
            staffpresent = "An empty stand sits on top of it."
        if not self.characterDead:
            if self.firstTime:
                firsttime = "A ghostly figure stands in the way.\nIt says, \"The staff of Garrotxa stands before you. Only those who can answer my riddle are worthy to take it.\"\nIt pauses to speak again.\"%s\"" % self.counter_1
            else:
                firsttime = "A ghostly figure stands in the way.\nIt repeats the riddle, \"%s\"" % self.counter_1
        else:
            firsttime = ""
        print("\nInner Sanctum\n\nTorches line the walls of a stone hallway.\nAt the end sits a pedestal. " + staffpresent + "\n" + firsttime)
        print("\n    Up is the ground floor.")

#_______The Cavern______________________________________________________________
# Entrance
# debug cave names
class classMountEntrance(Room):
    def description(self):
        print("\nCoal Mine Entrance\n\nMount Magna towers above, casting a shadow onto the land below.\nThere is a DEAD BODY here.\nThere is a SIGN you can READ here.\nThere is a vault door North, with a combination lock.\nFour large dials, each set to a number from 0 to 9 are displayed on the vault.\n\nThe first dial reads %i.\nThe second dial reads %i.\nThe third dial reads %i.\nThe fourth dial reads %i.\n" % (self.counter_1, self.counter_2, self.counter_3, self.counter_4))
        print("    North is inside the mine.")
        print("    South is the crossroads.")
# 1
class classCave_1_m(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    South is outside.")
# 2
class classCave_2_m(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    East is an opening.")
        print("    West is an opening.")
        print("    South is an opening.")
class classCave_2_mr(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    West is an opening.")
class classCave_2_lm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.\n")
        print("    North is an opening.")
        print("    East is an opening.")
        print("    West is an opening.")
class classCave_2_llm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.\nThere is a DEAD BODY here.")
        print("\n    North is an opening.")
        print("    East is an opening.")
# 3
class classCave_3_m_coalmine(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        if self.counter_1:
            print("There are COAL veinss lined along the cavern wall.")
        print("\n    North is an opening.")
class classCave_3_mr(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    East is an opening.")
        print("    South is an opening.")
class classCave_3_mrr_coalmine(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        if self.counter_1:
            print("There are COAL veins lined along the cavern wall.")
        print("\n    West is an opening.")
class classCave_3_lm_coalmine(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        if self.counter_1:
            print("There are COAL veins lined along the cavern wall.")
        print("\n    South is an opening.")
class classCave_3_llm_crevasse(Room):
    def description(self):
        if self.westBlocked:
            hook = ""
        else:
            hook = " with a grappling hook spanning across it"
        print("\nCavern\n\nCold stone surrounds you in every direction.\nYou can see a TREASURE CHEST to the West.")
        print("\n    North is an opening.")
        print("    West is a crevasse%s." % hook)
        print("    South is an opening.")
class classCave__3_lllm_treasure_crevasse(Room):
    def description(self):
        if self.eastBlocked:
            hook = ""
        else:
            hook = " with a grappling hook spanning across it"
        if self.itemFound:
            chest = "n open"
        else:
            if self.counter_1:
                chest = " closed"
            else:
                chest = " locked"
        print("\nCavern\n\nCold stone surrounds you in every direction.\nThere is a%s TREASURE CHEST here." % chest)
        print("\n    East is a crevasse%s." % hook)
# 4
class classCave_4_m(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    East is an opening.")
        print("    West is an opening.")
        print("    South is an opening.")
class classCave_4_mr(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.\nThere is some strange TEXT on written on one of the walls.\n")
        print("    West is an opening.")
        print("    South is an opening.")
class classCave_4_lm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.\n")
        print("    North is an opening.")
        print("    East is an opening.")
        print("    West is an opening.")
class classCave_4_llm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    East is an opening.")
        print("    South is an opening.")
# 5
class classCave_5_m(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    East is an opening.")
        print("    South is an opening.")
class classCave_5_mr_coalmine(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        if self.counter_1:
            print("There are COAL veins lined along the cavern wall.")
        print("\n    West is an opening.")
class classCave_5_lm_coalmine(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        if self.counter_1:
            print("There are COAL veins lined along the cavern wall.")
        print("\n    South is an opening.")
class classCave_5_llm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    East is an opening.")
        print("    West is an opening.")
        print("    South is an opening.")
class classCave_5_lllm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    East is an opening.")
# 6
class classCave_6_m(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.\nThere is a DEAD BODY here.")
        print("\n    South is an opening.")
        print("    East is an opening.")
class classCave_6_mr(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    West is an opening.")
class classCave_6_lm_coalmine(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        if self.counter_1:
            print("There are COAL veins lined along the cavern wall.")
        print("\n    North is an opening.")
class classCave_6_llm_coalmine(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        if self.counter_1:
            print("There are COAL veins lined along the cavern wall.")
        print("\n    West is an opening.")
class classCave_6_lllm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.\nThere is a DEAD BODY here.")
        print("\n    North is an opening.")
        print("    East is an opening.")
        print("    South is an opening.")
# 7
class classCave_7_m(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    East is an opening.")
        print("    West is an opening.")
class classCave_7_mr(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    West is an opening.")
        print("    South is an opening.")
class classCave_7_lm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    East is an opening.")
        print("    West is an opening.")
        print("    South is an opening.")
class classCave_7_llm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    East is an opening.")
        print("    West is an opening.")
class classCave_7_lllm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    East is an opening.")
        print("    South is an opening.")
# 8
class classCave_8_mr_crevasse(Room):
    def description(self):
        if self.northBlocked:
            hook = ""
        else:
            hook = " with a grappling hook spanning across it"
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is a crevasse%s." % hook)
        print("    South is an opening.")
class classCave_8_llm_coalmine(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        if self.counter_1:
            print("There are COAL veins lined along the cavern wall.")
        print("\n    South is an opening.")
# 9
# Creature cannot naturally spawn here.
# If creature chase, then creature retreats (cannot pass crevasse)
class classCave_9_mr_crevasse(Room):
    def description(self):
        if self.northBlocked:
            rubble = "The tunnel has collapsed North.\n"
        else:
            rubble = "\n"
        if self.southBlocked:
            hook = ""
        else:
            hook = " with a grappling hook spanning across it"
        print("\nCavern\n\nCold stone surrounds you in every direction.",rubble)
        if not self.northBlocked:
            print("    North is an opening.")
        print("    South is a crevasse%s." % hook)
# 10
# Chase rooms after crossing crevasse
class classCave__10_mr(Room):
    def description(self):
        if self.southBlocked:
            rubble = ", blocked by RUBBLE"
        else:
            rubble = ""
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    East is an opening.")
        print("    West is an opening.")
        print("    South is an opening%s." % rubble)
class classCave__10_mrr(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    East is an opening.")
        print("    West is an opening.")
class classCave__10_m(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    East is an opening.")
        print("    West is an opening.")
class classCave__10_mrrr(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    West is an opening.")
class classCave__10_lm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    East is an opening.")
# 11
class classCave__11_lm(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    South is an opening.")
class classCave__11_mrrr(Room):
    def description(self):
        print("\nCavern\n\nCold stone surrounds you in every direction.")
        print("\n    North is an opening.")
        print("    South is an opening.")
# Darkness
class classDarkness(Room):
    def description(self):
        print("\nDarkness\n\nYou feel nothing.")
# Lair
# Have lots of stuff to examine
#   Shrine to Vesh'kathal
#   markings on wall?
class classLairMid(Room): # body here with lantern oil, biscuits
    def description(self):
        print("\nLair\n\nAn obsidian fountain sits in the centre, spewing a mysterious purple fluid.\nFour gargoyle statues from the corners of the room look inward towards the fountain, perched upon stone pedestals.\nYour BACKPACK and the CREATURE are visible within the rubble.\nThere is a DEAD BODY here.") # contains biscuits to prevent starve (hunger set low after waking up)
        print("\n    East is a doorway.") # leads to exit
        print("    West is a doorway.") # leads to drop puzzle and supplies in more bodies (include bandage for escape hit, pick for mining rock, more food for after escape)
        print("South is an opening, blocked by RUBBLE.")
class classLairEast(Room):
    def description(self):
        if self.northBlocked:
            rubble = ", blocked by RUBBLE. Light from the outside peers through the cracks"
        else:
            rubble = ""
        if self.firstTime:
            eyes = "closed"
        else:
            eyes = "open"
        print("\nLair\n\nStrange TEXT is etched across the walls. A single gargoyle statue is perched on a pedestal. Its eyes are %s." % eyes)
        print("\n    North is an opening%s." % rubble)
        print("    West is a doorway.")
class classLairWest(Room): # body here with bandages, lantern
    def description(self):
        print("\nLair\n\nA skeleton lies on an elongated table, stained with old blood.")
        print("\n    East is a doorway.")
        print("    South is a small opening.")
class classLairHole(Room): # get pickaxe here, only if carrying 1 thing in travel both ways
    def description(self):
        print("\nLair\n\nCold stone surrounds you in every direction. The space is extremely cramped.\nThere is a DEAD BODY here.\n")
        print("    North is a small opening.")
# Fermiere
class classRoad2South(Room):
    def description(self):
        print("\nRoad\n\nThe dirt path extends out on the field. The shadow of Mount Magna towers from the south.\n")
        print("    North is a crossroads.")
        print("    South is inside the mine.")
class classRoad2Mid(Room):
    def description(self):
        if self.itemFound:
            northD = "the House of Roquefort."
            westD = "Fermiere Farm."
            southD = "Mount Magna."
        else:
            northD = "a road."
            westD = "a road."
            southD = "a road."
        print("\nCrossroads\n\nThe paths meet here. There is a SIGN you can READ here.\n")
        print("    North is",northD)
        print("    East is a field.")
        print("    West is",westD)
        print("    South is",southD)
class classFarm(Room): # Get potato
    def description(self):
        print("\nFarm\n\nThe entire field is barren and the soil is mixed with ash and dead crops.\n")
        print("    East is a crossroads.")
        print("    West is a barn.")
class classBarn(Room):
    def description(self):
        print("\nBarn\n\nThe entire interior is empty as as the walls are all burnt.\nThe roof is entirely removed, and part of the second floor, allowing the sun to light up the area.\nThere is some WRITING on a wall you can READ.\n")
        print("    Up is the second floor.")
        print("    East is a farm.")
class classBarnUp(Room):
    def description(self):
        print("\nBarn\n\nSeveral stacks of hay are lined across the wall.\nA table and chair stand at the far end.\nThere is a DEAD BODY here.\n")
        print("    Down is the ground floor.")
class classHouseGate(Room):
    def description(self):
        if self.northBlocked:
            lock = " locked"
        else:
            lock = "n unlocked"
        print("\nGate\n\nThe path leads to a%s gate with a large stone wall surrounding the house inside.\nThere is a METAL PLAQUE you can READ just above the gate.\n" % lock)
        print("    North is the entrance.")
        print("    South is the crossroads.")
class classField(Room):
    def description(self):
        if self.eastBlocked:
            blocked = "The surface has no distinguishing features."
        else:
            blocked = "An opening leads inside."
        print("\nRoad\n\nIn the middle of the field is large obsidian hemisphere. %s\n" % blocked)
        if not self.eastBlocked:
            print("    East is inside the obsidian hemisphere.")
        print("    West is the crossroads.")
class classShrineNorth(Room):
    def description(self):
        if self.firstTime and roomShrineSouth.firstTime:
            firsttime = " A pedestal emerges from the water as a voice echoes within the chamber, \"Omoz... gloth... izh...\""
        else:
            if self.book:
                bookpresent = " with a mysterious book"
            else:
                bookpresent = ""
            firsttime = " A pedestal is here%s." % bookpresent
        print("\nNorth Shrine\n\nSteps lead down to a circular pit, shallowly filled with water.\nAround the edges of the room, floating candles dimly light the area.\nAlong the South wall is a MYSTICAL PORTAL." + firsttime + "\n")
        print("    West is outside.")
        print("    South is through the portal.")
# House
class classHouseEntrance(Room):
    def description(self):
        print("\nEntrance\n\nStairs lead up to a tall door bordered by cobblestones.\n")
        print("    North is inside the house.")
        print("    South is the gate.")
class classHouseFoyer(Room):
    def description(self):
        if self.northBlocked:
            lock = " locked"
        else:
            lock = "n unlocked"
        print("\nFoyer\n\nVelet carpet covers the floor as a large, glass chandilier crowned with lights hangs above.\nSeveral busts and paintings line the walls.\nThere is a%s rectangular metal door leading north.\n" % lock)
        print("    North is the hallway.")
        print("    East is the pantry.")
        print("    West is the kitchen.")
class classHouseKitchen(Room):
    def description(self):
        print("\nKitchen\n\nSeveral complicated gadgets sit on the countertops.\nThere is a LEVER here that can be set in a forward or backwards position.\nIt is currently set to the %s position.\n" % self.counter_1)
        print("    East is the foyer.")
class classHousePantry(Room):
    def description(self):
        print("\nPantry\n\nThere are several cabinets containing various cheeses.\nOn the wall is a DIAL with 3 settings: blue, red, and green.\nIt is currently set to %s.\n" % self.counter_1)
        print("    West is the foyer.")
class classHouseHallway(Room): # East and West blocked
    def description(self):
        print("\nHallway\n\nThe velvet carpet extends out in the hallway.\n")
        print("    North is the office.")
        print("    East is the library.")
        print("    West is the bedroom.")
        print("    South is the foyer.")
class classHouseOffice(Room): # End game
    def description(self):
        if self.firstTime: ##continue revamp
            firsttime = "The door locks behind you. \"The assassin has overcome my final defense, and now he's come to murder me. Tell me, what would a lowly rat from the prisons of Airedale want in killing me? Are you an agent of the Ozkavosh?\"\nAn old bearded man wearing tattered rags, sits at the desk at the far end of the room, facing the North wall. He gets up from his chair and turns around to see you.\n\"What did they offer you for your nefarious task? Untold riches? Rule over Airedale? They will not hold their end of the deal! GIVE me the staff and together we can rid the world of demon-kind.\"\nEden Von Roquefort walks towards you with open hands. \"It is not too late top stop what you are doing.\""
            # firsttime = "The door locks behind you. \"The assassin has overcome my final defense, and now he's come to murder me. Tell me, what would a lowly rat from the prisons of Airedale want in killing me?\" An old bearded man wearing tattered rags, sits at the desk at the far end of the room, facing the North wall. He gets up from his chair and turns around to see you. \"So you are the one sent to kill me? Not what I expected. Then again, appearances don't mean much in times like these. It took me a while to figure out what was going on. You are an agent of the Ozkavosh, here to kill me like they've done the rest of this land. A vesh'raheen would not do, so they would need someone with a history of breaking and entering to reach me. What better choice than a criminal from the Airedale prisons? It is not too late to stop what you are doing.\" Eden Von Roquefort walks towards you with open hands. \"Give me the staff, and together we can rid the world of demon-kind.\""
        elif self.characterDead:
            firsttime = "There is a desk at the far back of the room. The body of Eden Von Roquefort lays on the floor."
        else:
            firsttime = "There is a desk at the far back of the room. Eden Von Roquefort is here."
        print("\nOffice\n\n" + firsttime + "\n")
        print("    South is the hallway.")
# Mysterious book
class classBookAnimal(Room):
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
class classBookMirror(Room):
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
        print("\nBlack room\n\nA %s.\nOn the wall behind it is some TEXT.\n%s%s" % (gargoyle,itemfound,light))
class classBook_3_1(Room):
    def description (self):
        print("\nRed room\n\nThere is a statue of a tortoise here.\n")
        print("    North is a doorway.")
        print("    East is a doorway.")
        print("    West is a doorway.")
        print("    South is a doorway.")
class classBook_3_2(Room):
    def description (self):
        print("\nGreen room\n\nThere is a statue of a vulture here.\n")
        print("    North is a doorway.")
        print("    East is a doorway.")
        print("    West is a doorway.")
        print("    South is a doorway.")
class classBook_3_3(Room):
    def description (self):
        print("\nPurple room\n\nThere is a statue of a goat here.\n")
        print("    North is a doorway.")
        print("    East is a doorway.")
        print("    West is a doorway.")
        print("    South is a doorway.")
class classBook_3_4(Room):
    def description (self):
        print("\nOrange room\n\nThere is a statue of a cow here.\n")
        print("    North is a doorway.")
        print("    East is a doorway.")
        print("    West is a doorway.")
        print("    South is a doorway.")
class classBook_3_5(Room):
    def description (self):
        print("\nYellow room\n\nThere is a statue of a wolf here.\n")
        print("    North is a doorway.")
        print("    East is a doorway.")
        print("    West is a doorway.")
        print("    South is a doorway.")
class classBook_3_6(Room):
    def description (self):
        print("\nBlue room\n\nThere is a statue of a boar here.\n")
        print("    North is a doorway.")
        print("    East is a doorway.")
        print("    West is a doorway.")
        print("    South is a doorway.")
class classBook_3_7(Room):
    def description (self):
        print("\nWhite room\n\nThere is a statue of a bear here.\n")
        print("    North is a doorway.")
        print("    East is a doorway.")
        print("    West is a doorway.")
        print("    South is a doorway.")
class classBook_3_8(Room):
    def description(self):
        print("\nGrey room\n\nThere is a statue of a snake here.\n")
        print("    North is a doorway.")
        print("    East is a doorway.")
        print("    West is a doorway.")
        print("    South is a doorway.")
class classBook_3_End(Room):
    def description (self):
        if self.book:
            bookpresent = " with a mysterious book on it"
        else:
            bookpresent = ""
        print("\nBlack room\n\nThere is a pedestal here%s.\n" % bookpresent)
        print("    East is a doorway.")
        print("    West is a doorway.")

#____________ Room Init

# Jail
roomJailCell = classJailCell(name = "jail cell", porridge = 1, east = "roomJailCorridor", eastBlocked = True, eastBlockedReason = "The cell door is locked.")
roomJailCorridor = classJailCorridor(name = "corridor", north = "roomJailAntechamber", west = "roomJailCell", westBlocked = False, westBlockedReason = "The cell door is locked.")
roomJailAntechamber = classJailAntechamber(name = "antechamber", south = "roomJailCorridor", down = "roomJailFoyer")
roomJailFoyer = classJailFoyer(name = "foyer", east = "roomJailHallway", west = "roomJailEntrance", up = "roomJailAntechamber")
roomJailHallway = classJailHallway(name = "hallway", east = "roomJailBreakRoom", west = "roomJailFoyer")
roomJailBreakRoom = classJailBreakRoom()
roomJailEntrance = classJailEntrance(name = "jail entrance", east = "roomJailFoyer", west = "roomCourtyardSouth", shrubbery = 1)
# Town
roomCourtyardNorth = classCourtyardNorth(name = "north courtyard", north = "roomGate", east = "roomGeneralStore", south = "roomCourtyardSouth", west = "roomCarnival", characterDead = False)
roomCourtyardSouth = classCourtyardSouth(name = "south courtyard", gold = LOOT_S_GOLD, north = "roomCourtyardNorth", east = "roomJailEntrance", west = "roomBlacksmith", south = "roomAlchemist", characterDead = False)
roomBlacksmith = classBlacksmith(name = "blacksmith", east = "roomCourtyardSouth", characterDead = False, isBuy = True, isSell = True)
roomAlchemist = classAlchemist(name = "alchemist's hut", north = "roomCourtyardSouth", characterDead = False, isSell = True)
# Carnival
roomCarnival = classCarnival(name = "carnival", halfFunnelCake = 1, north = "roomCarnivalFood", east = "roomCourtyardNorth", south = "roomCarnivalShellGame", southBlockedReason = "The tent is closed.", west = "roomCarnivalWheelGame", westBlockedReason = "The tent is closed.", characterDead = False)
roomCarnivalFood = classCarnivalFood(name = "funnel cakes galore", south = "roomCarnival", characterDead = False, isBuy = True)
roomCarnivalShellGame = classCarnivalShellGame(name = "sybil\'s shell game", north = "roomCarnival", characterDead = False, isBet = True)
roomCarnivalWheelGame = classCarnivalWheelGame(east = "roomCarnival", characterDead = False, isBuy = True)
roomGate = classGate(name = "town gate", gold = LOOT_GATE_GOLD, north = "roomRoadMid", northBlocked = True, northBlockedReason = "The guard prevents you from leaving the town.", south = "roomCourtyardNorth", characterDead = False, isGive = True)
roomGeneralStore = classGeneralStore(name = "general store", west = "roomCourtyardNorth", characterDead = False, isBuy = True)
# Outside
# Road
roomRoadSouth = classRoadSouth(name = "road", north = "roomRoadMid", eastBlocked = True, eastBlockedReason = "The foliage is too thick to traverse.", westBlocked = True, westBlockedReason = "The foliage is too thick to traverse.", south = "roomGate")
roomRoadMid = classRoadMid(name = "crossroads", north = "roomMountEntrance", east = "roomBridge", south = "roomGate", west = "roomLake")
roomRoadNorth = classRoadNorth(name = "road", north = "roomMountEntrance", eastBlocked = True, eastBlockedReason = "The foliage is too thick to traverse.", south = "roomRoadMid", westBlocked = True, westBlockedReason = "The foliage is too thick to traverse.")
roomRoadEast = classRoadEast(name = "road", northBlocked = True, northBlockedReason = "The foliage is too thick to traverse.", east = "roomBridge", west = "roomRoadMid", southBlocked = True, southBlockedReason = "The foliage is too thick to traverse.")
roomRoadWest = classRoadWest(name = "road", northBlocked = True, northBlockedReason = "The foliage is too thick to traverse.", east = "roomRoadMid", west = "roomLake", southBlocked = True, southBlockedReason = "The foliage is too thick to traverse.")
roomRoadCorner = classRoadCorner(name = "road", north = "roomTempleEntrance", east = "roomForest", west = "roomBridge", westBlockedReason = "The black knight stands in the way. He declares, \"None shall pass.\"", southBlocked = True, southBlockedReason = "The foliage is too thick to traverse.")
roomForest = classForest(name = "forest", northBlocked = True, northBlockedReason = "The foliage is too thick to traverse.", east = "roomShrineSouth", eastBlocked = True, eastBlockedReason = "The foliage is too thick to traverse.", west = "roomRoadCorner", southBlocked = True, southBlockedReason = "The foliage is too thick to traverse.")
# Lake
roomLake = classLake(name = "lake laguiole", northBlocked = True, northBlockedReason = "The foliage is too thick to traverse.", east = "roomRoadMid", southBlocked = True, southBlockedReason = "The foliage is too thick to traverse.", westBlocked = True, westBlockedReason = "The foliage is too thick to traverse.", characterDead = False, isGive = True)
# Bridge
roomBridge = classBridge(name = "bridge", east = "roomRoadCorner", eastBlocked = True, eastBlockedReason = "The troll is stopping you from crossing.", west = "roomRoadMid", characterDead = False, isGive = True)
# Temple
roomTempleEntrance = classTempleEntrance(name = "temple entrance", north = "roomTempleInside", northBlocked = True, northBlockedReason = "Rubble is blocking the way.", south = "roomRoadCorner", counter_1 = RUBBLE_DURABILITY, isMine = True)
roomTempleInside = classTempleInside(name = "temple", south = "roomTempleEntrance", down = "roomTempleBasement")
roomTempleBasement = classTempleBasement(name = "inner sanctum", up = "roomTempleInside", characterDead = False, staff = 1)
# Cave
roomMountEntrance = classMountEntrance(name = "coal mine entrance", north = "roomCave_1_m", northBlocked = True, northBlockedReason = "The vault door is locked.", eastBlocked = True, eastBlockedReason = "The foliage is too thick to traverse.", south = "roomRoadMid", westBlocked = True, westBlockedReason = "The foliage is too thick to traverse.")
# 1
roomCave_1_m = classCave_1_m(name = "cavern", north = "roomCave_2_m", south = "roomMountEntrance")
# 2
roomCave_2_m = classCave_2_m(name = "cavern", east = "roomCave_2_mr", south = "roomCave_1_m", west = "roomCave_2_lm")
roomCave_2_mr = classCave_2_mr(name = "cavern", north = "roomCave_3_mr", west = "roomCave_2_m", bird = 1)
roomCave_2_lm = classCave_2_lm(name = "cavern", north = "roomCave_3_lm_coalmine", east = "roomCave_2_m", west = "roomCave_2_llm")
roomCave_2_llm = classCave_2_llm(name = "cavern", north = "roomCave_3_llm_crevasse", east = "roomCave_2_lm")
# 3
roomCave_3_m_coalmine = classCave_3_m_coalmine()
roomCave_3_mr = classCave_3_mr()
roomCave_3_mrr_coalmine = classCave_3_mrr_coalmine(name = "cavern", west = "roomCave_3_mr", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
roomCave_3_lm_coalmine = classCave_3_lm_coalmine(name = "cavern", south = "roomCave_2_lm", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
roomCave_3_llm_crevasse = classCave_3_llm_crevasse(name = "cavern", north = "roomCave_4_llm", south = "roomCave_2_llm", west = "roomCave__3_lllm_treasure_crevasse", westBlocked = True, westBlockedReason = "The crevasse is too large to walk across.", isCrevasse = True)
roomCave__3_lllm_treasure_crevasse = classCave__3_lllm_treasure_crevasse(name = "cavern", east = "roomCave_3_llm_crevasse", eastBlocked = True, eastBlockedReason = "The crevasse is too large to walk across.", isCrevasse = True)
# 4
roomCave_4_m = classCave_4_m(name = "cavern", north = "roomCave_5_m", east = "roomCave_4_mr", south = "roomCave_3_m_coalmine", west = "roomCave_4_lm")
roomCave_4_mr = classCave_4_mr(name = "cavern", south = "roomCave_3_mr", west = "roomCave_4_m")
roomCave_4_lm = classCave_4_lm(name = "cavern", north = "roomCave_5_lm_coalmine", east = "roomCave_4_m", west = "roomCave_4_llm")
roomCave_4_llm = classCave_4_llm(name = "cavern", north = "roomCave_5_llm", east = "roomCave_4_lm", south = "roomCave_3_llm_crevasse")
# 5
roomCave_5_m = classCave_5_m(name = "cavern", north = "roomCave_6_m", east = "roomCave_5_mr_coalmine", south = "roomCave_4_m")
roomCave_5_mr_coalmine = classCave_5_mr_coalmine(name = "cavern", west = "roomCave_5_m", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
roomCave_5_lm_coalmine = classCave_5_lm_coalmine(name = "cavern", south = "roomCave_4_lm", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
roomCave_5_llm = classCave_5_llm(name = "cavern", east = "roomCave_5_lm_coalmine", south = "roomCave_4_llm", west = "roomCave_5_lllm")
roomCave_5_lllm = classCave_5_lllm(name = "cavern", north = "roomCave_6_lllm", east = "roomCave_5_llm")
# 6
roomCave_6_m = classCave_6_m(name = "cavern", east = "roomCave_6_mr", south = "roomCave_5_m")
roomCave_6_mr = classCave_6_mr(name = "cavern", north = "roomCave_7_mr", west = "roomCave_6_m")
roomCave_6_lm_coalmine = classCave_6_lm_coalmine(name = "cavern", north = "roomCave_7_lm", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
roomCave_6_llm_coalmine = classCave_6_llm_coalmine(name = "cavern", west = "roomCave_6_lllm", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
roomCave_6_lllm = classCave_6_lllm(name = "cavern", north = "roomCave_7_lllm", east = "roomCave_6_llm_coalmine", south = "roomCave_5_lllm")
# 7
roomCave_7_m = classCave_7_m(name = "cavern", east = "roomCave_7_mr", west = "roomCave_7_lm")
roomCave_7_mr = classCave_7_mr(name = "cavern", north = "roomCave_8_mr_crevasse", south = "roomCave_6_mr", west = "roomCave_7_m")
roomCave_7_lm = classCave_7_lm(name = "cavern", east = "roomCave_7_m", south = "roomCave_6_lm_coalmine", west = "roomCave_7_llm")
roomCave_7_llm = classCave_7_llm(name = "cavern", north = "roomCave_8_llm_coalmine", east = "roomCave_7_lm", west = "roomCave_7_lllm")
roomCave_7_lllm = classCave_7_lllm(name = "cavern", east = "roomCave_7_llm", south = "roomCave_6_lllm")
# 8
roomCave_8_mr_crevasse = classCave_8_mr_crevasse(name = "cavern", north = "roomCave_9_mr_crevasse", northBlocked = True, northBlockedReason = "The crevasse is too large to walk across.", south = "roomCave_7_mr", isCrevasse = True)
roomCave_8_llm_coalmine = classCave_8_llm_coalmine(name = "cavern", south = "roomCave_7_llm", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
# 9
roomCave_9_mr_crevasse = classCave_9_mr_crevasse(name = "cavern", north = "roomCave__10_mr", south = "roomCave_8_mr_crevasse", southBlocked = True, southBlockedReason = "The crevasse is too large to walk across.",  isCrevasse = True)
# 10
roomCave__10_m = classCave__10_m(name = "cavern", east = "roomCave__10_mr", west = "roomCave__10_lm")
roomCave__10_mr = classCave__10_mr(name = "cavern", east = "roomCave__10_mrr", south = "roomCave_9_mr_crevasse", southBlocked = True, southBlockedReason = "Rubble is blocking the way.", west = "roomCave__10_m", counter_1 = RUBBLE_DURABILITY, isMine = True)
roomCave__10_mrr = classCave__10_mrr(name = "cavern", east = "roomCave__10_mrrr", west = "roomCave__10_mr")
roomCave__10_mrrr = classCave__10_mrrr(name = "cavern", north = "roomCave__11_mrrr", west = "roomCave__10_mrr")
roomCave__10_lm = classCave__10_lm(name = "cavern", north = "roomCave__11_lm", east = "roomCave__10_m")
# 11
roomCave__11_mrrr = classCave__11_mrrr(name = "cavern", north = "roomDarkness", south = "roomCave__10_mrrr")
roomCave__11_lm = classCave__11_lm(name = "cavern", north = "roomDarkness", south = "roomCave__10_lm")
# Darkness
roomDarkness = classDarkness(name = "darkness")
# Lair
roomLairMid = classLairMid()
roomLairEast = classLairEast()
roomLairWest = classLairWest()
roomLairHole = classLairHole(name = "lair", north = "roomLairWest", northBlockedReason = "You are carrying too much to fit through the opening.", lantern = 1)
# Field
roomRoad2South = classRoad2South(name = "road", north = "roomRoad2Mid", south = "roomLairEast")
roomRoad2Mid = classRoad2Mid(name = "crossroad", north = "roomHouseGate", east = "roomField", south = "roomRoad2South", west = "roomFarm")
roomHouseGate = classHouseGate(name = "road", south = "roomRoad2Mid", north = "roomHouseEntrance", northBlocked = True, northBlockedReason = "The gate is locked.")
roomFarm = classFarm(name = "farm", east = "roomRoad2Mid", west = "roomBarn", potato = 1)
roomBarn = classBarn(name = "barn", east = "roomFarm", up = "roomBarnUp")
roomBarnUp = classBarnUp(name = "barn", down = "roomBarn")
roomField = classField()
# Shrines
roomShrineNorth = classShrineNorth(name = "north shrine", west = "roomField", south = "roomShrineSouth", book = 1)
roomShrineSouth = classShrineSouth(name = "south shrine", north = "roomShrineNorth", west = "roomForest")
# House
roomHouseEntrance = classHouseEntrance(name = "entrance", north = "roomHouseFoyer", northBlocked = True, northBlockedReason = "The door is locked.", south = "roomHouseGate")
roomHouseFoyer = classHouseFoyer(name = "foyer", north = "roomHouseHallway", northBlocked = True, northBlockedReason = "The door is locked.", east = "roomHousePantry", south = "roomHouseEntrance", west = "roomHouseKitchen")
roomHouseKitchen = classHouseKitchen(name = "kitchen", east = "roomHouseFoyer", counter_1 = LEVER_START)
roomHousePantry = classHousePantry(name = "pantry", west = "roomHouseFoyer", brie = 1, munster = 1, stilton = 1, swiss = 1, wensleydale = 1, counter_1 = DIAL_START)
roomHouseHallway = classHouseHallway(name = "hallway", north = "roomHouseOffice", eastBlocked = True, eastBlockedReason = "The door is locked.", south = "roomHouseFoyer", westBlocked = True, westBlockedReason = "The door is locked.")
roomHouseOffice = classHouseOffice(name = "office", south = "roomHouseHallway", southBlocked = True, southBlockedReason = "The door is locked.", characterDead = False, isGive = True)
# Mysterious book
# Puzzle 1 - Animal Totems
roomBookAnimal = classBookAnimal(name = "black room")
# Puzzle 2 - Mirror player
roomBookMirror = classBookMirror(name = "black room", characterDead = False)
# Puzzle 3 - Movement
roomBook_3_1 = classBook_3_1(name = "red room", north = "roomBook_3_5", east = "roomBook_3_3", south = "roomBook_3_4", west = "roomBook_3_2")
roomBook_3_2 = classBook_3_2(name = "green room", north = "roomBook_3_3", east = "roomBook_3_1", south = "roomBook_3_5", west = "roomBook_3_3")
roomBook_3_3 = classBook_3_3(name = "purple room", north = "roomBook_3_8", east = "roomBook_3_2", south = "roomBook_3_4", west = "roomBook_3_1")
roomBook_3_4 = classBook_3_4(name = "orange room", north = "roomBook_3_1", east = "roomBook_3_5", south = "roomBook_3_7", west = "roomBook_3_6")
roomBook_3_5 = classBook_3_5(name = "yellow room", north = "roomBook_3_2",  east = "roomBook_3_6", south = "roomBook_3_1", west = "roomBook_3_4")
roomBook_3_6 = classBook_3_6(name = "blue room", north = "roomBook_3_7", east = "roomBook_3_4", south = "roomBook_3_8", west = "roomBook_3_5")
roomBook_3_7 = classBook_3_7(name = "white room", north = "roomBook_3_4", east = "roomBook_3_8", south = "roomBook_3_6", west = "roomBook_3_End")
roomBook_3_8 = classBook_3_8(name = "grey room", north = "roomBook_3_6", east = "roomBook_3_End", south = "roomBook_3_3", west = "roomBook_3_7")
roomBook_3_End = classBook_3_End(name = "black room", east = "roomBook_3_7", west = "roomBook_3_8", keySkeleton = 1, book = 1)
