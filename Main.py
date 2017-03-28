#_______Libraries_______________________________________________________________
import random,os

# Local libraries
from Player import *
from Constants import *
from Rooms import *
from MainMenu import *
from Functions import *

#_______Library stuff___________________________________________________________
# Resize command line window to appropriate size
# col is width
# lines is buffer height
os.system('mode con: cols=200')

#______Classes__________________________________________________________________




#_______Class Initialization - Objects - Before game
# debug inventory
DEBUG_INV = Inventory()
inv = Inventory()
invGate = Inventory()
invShell = Inventory(gold = SHELL_GOLD)
invTroll = Inventory()
invSpokesperson = Inventory()
invFood = Inventory(funnelCake = FUNNELCAKE_LIMIT)




#_______Functions_______________________________________________________________
def shortcuts():
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
# In Game
def inGame(debug):
    global deaths_total,turnCounter_total,spellLearn,spellUnlock,spellPersuade,spellJump,spellLight,spellHeal,spellFeed,spellKill,spellKillself,spellOblivion,wordDarkness,wordReign,wordStop,wordServant,wordMirror,wordDominion,wordCurse,playerName
    # Resetable constant/variables

    # Vault Answers
    vaultAnswer_1 = random.randint(0,9)
    vaultAnswer_2 = random.randint(0,9)
    vaultAnswer_3 = random.randint(0,9)
    vaultAnswer_4 = random.randint(0,9)

    # Vault Initial
    vaultInitial_1 = random.randint(0,9)
    vaultInitial_2 = random.randint(0,9)
    vaultInitial_3 = random.randint(0,9)
    vaultInitial_4 = random.randint(0,9)
    same = random.randint(1,4)
    # Two vault values are randomly set to the correct answer already
    if same == 1:
        vaultInitial_1 = vaultAnswer_1
        vaultInitial_3 = vaultAnswer_3
        while (vaultInitial_2 == vaultAnswer_2 or vaultInitial_4 == vaultAnswer_4):
            vaultInitial_2 = random.randint(0,9)
            vaultInitial_4 = random.randint(0,9)
    elif same == 2:
        vaultInitial_2 = vaultAnswer_2
        vaultInitial_4 = vaultAnswer_4
        while (vaultInitial_1 == vaultAnswer_1 or vaultInitial_3 == vaultAnswer_3):
            vaultInitial_1 = random.randint(0,9)
            vaultInitial_3 = random.randint(0,9)
    elif same == 3:
        vaultInitial_1 = vaultAnswer_1
        vaultInitial_4 = vaultAnswer_4
        while (vaultInitial_2 == vaultAnswer_2 or vaultInitial_3 == vaultAnswer_3):
            vaultInitial_2 = random.randint(0,9)
            vaultInitial_3 = random.randint(0,9)
    elif same == 4:
        vaultInitial_2 = vaultAnswer_2
        vaultInitial_3 = vaultAnswer_3
        while (vaultInitial_1 == vaultAnswer_1 or vaultInitial_4 == vaultAnswer_4):
            vaultInitial_1 = random.randint(0,9)
            vaultInitial_4 = random.randint(0,9)
#_______Player Information______________________________________________________
# Information about Player
    #_______Class Initialization - Objects - Reset
    # debug inventory
    DEBUG_INV = Inventory(gold = 100000, pickaxe = 1, lantern = 1, oil = 10000, pie = 30000, coal = 100000, foot = 100, funnelCake = 50, halfFunnelCake = 50, hook = 10, staff = 1, ticket = 1, bandage = 100, journal = 1, book = 1, potato = 1, stone = 1, note = 1, bird = 1, memo = 1, potion = 100, flask = 100, keySkeleton = 1, note_1 = vaultAnswer_1, note_2 = vaultAnswer_2, note_3 = vaultAnswer_3, note_4 = vaultAnswer_4)
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
    roomMountEntrance = classMountEntrance(name = "coal mine entrance", north = "roomCave_1_m", northBlocked = True, northBlockedReason = "The vault door is locked.", eastBlocked = True, eastBlockedReason = "The foliage is too thick to traverse.", south = "roomRoadMid", westBlocked = True, westBlockedReason = "The foliage is too thick to traverse.", counterAns_1 = vaultAnswer_1, counterAns_2 = vaultAnswer_2, counterAns_3 = vaultAnswer_3, counterAns_4 = vaultAnswer_4, counter_1 = vaultInitial_1, counter_2 = vaultInitial_2, counter_3 = vaultInitial_3, counter_4 = vaultInitial_4)
    # 1
    roomCave_1_m = classCave_1_m(name = "cavern", north = "roomCave_2_m", south = "roomMountEntrance")
    # 2
    roomCave_2_m = classCave_2_m(name = "cavern", east = "roomCave_2_mr", south = "roomCave_1_m", west = "roomCave_2_lm")
    roomCave_2_mr = classCave_2_mr(name = "cavern", north = "roomCave_3_mr", west = "roomCave_2_m", bird = 1)
    roomCave_2_lm = classCave_2_lm(name = "cavern", north = "roomCave_3_lm_coalmine", east = "roomCave_2_m", west = "roomCave_2_llm")
    roomCave_2_llm = classCave_2_llm(name = "cavern", north = "roomCave_3_llm_crevasse", east = "roomCave_2_lm")
    # 3
    roomCave_3_m_coalmine = classCave_3_m_coalmine(name = "cavern", north = "roomCave_4_m", counter_1 = random.randint(COAL_MIN,COAL_MAX), isMine = True)
    roomCave_3_mr = classCave_3_mr(name = "cavern", north = "roomCave_4_mr", east = "roomCave_3_mrr_coalmine", south = "roomCave_2_mr", counter_1 = random.randint(COAL_MIN,COAL_MAX))
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
    roomLairMid = classLairMid(name = "lair", east = "roomLairEast", west = "roomLairWest", southBlocked = True, southBlockedReason = "Rubble is blocking the way.", counter_1 = 5, isMine = True)
    roomLairEast = classLairEast(name = "lair", north = "roomRoad2South", northBlocked = True, northBlockedReason = "Rubble is blocking the way.", west = "roomLairMid", counter_1 = RUBBLE_DURABILITY, isMine = True)
    roomLairWest = classLairWest(name = "lair", east = "roomLairMid", south = "roomLairHole", southBlockedReason = "You are carrying too much to fit through the opening.", gold = LOOT_WEST_GOLD, biscuit = LOOT_WEST_BISCUIT)
    roomLairHole = classLairHole(name = "lair", north = "roomLairWest", northBlockedReason = "You are carrying too much to fit through the opening.", lantern = 1)
    # Field
    roomRoad2South = classRoad2South(name = "road", north = "roomRoad2Mid", south = "roomLairEast")
    roomRoad2Mid = classRoad2Mid(name = "crossroad", north = "roomHouseGate", east = "roomField", south = "roomRoad2South", west = "roomFarm")
    roomHouseGate = classHouseGate(name = "road", south = "roomRoad2Mid", north = "roomHouseEntrance", northBlocked = True, northBlockedReason = "The gate is locked.")
    roomFarm = classFarm(name = "farm", east = "roomRoad2Mid", west = "roomBarn", potato = 1)
    roomBarn = classBarn(name = "barn", east = "roomFarm", up = "roomBarnUp")
    roomBarnUp = classBarnUp(name = "barn", down = "roomBarn")
    roomField = classField(name = "road", east = "roomShrineNorth", eastBlocked = True, eastBlockedReason = "You cannot go East.", west = "roomRoad2Mid")
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

    #_______Stat Init
    stat = Stat()

    #_______Inventory init
    if playerName:
        inv = Inventory(key = 1,porridge = 1,letter = 1)
        roomJailCell.porridge = 0
    else:
        inv = Inventory(note_1 = vaultAnswer_1, note_2 = vaultAnswer_2, note_3 = vaultAnswer_3, note_4 = vaultAnswer_4)
    # roomBridge troll
    invTroll = Inventory()
    # roomGate guard
    invGate = Inventory()
    # roomCarnivalShellGame
    invShell = Inventory(gold = SHELL_GOLD)
    # roomCarnival
    invSpokesperson = Inventory()
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
    roomTempleBasement.counterAns_1 = riddle_answer
    roomTempleBasement.counter_1 = riddle

    endGame = False
    changeRoom = False
    # Starting room
    # Default: roomJailCell
    if debug:
        roomID = "roomJailCell"
        roomCurrent = roomJailCell
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
    # Default first option to prevent "again" crash on first turn
    option_last = "invalid"
#_______Start of Game text____________________________________________________________
# Current room information
    roomCurrent.description()
    roomCurrent.itemsPresent()
    if inv.itemTypes():
        print()
        inv.examineInventory("inventory",roomCurrent)
# Hunger
    stat.lowerShield()
    if stat.hunger <= 0:
        endGame = True
    elif stat.hunger <= 15:
        print()
        stat.examineHunger()
# Health
    if stat.health in range(1,stat.healthmax):
        stat.lowerHealth()
        print()
        stat.examineHealth()
    if stat.health <= 0:
        endGame = True
# Oil warning
    if inv.lantern > 0 or roomCurrent.lantern > 0:
        if oilCounter in range(3,5):
            print("\nYour lantern flickers.")
        elif oilCounter == 2:
            print("\nYour lantern is about to run out of oil.")
        elif oilCounter == 1:
            print("\nYour lantern has ran out of oil.")

#_______End Game_________________________________________________________________
# Loops until the game ends
# Game loop start
    while not endGame:
#_______User input______________________________________________________________
        option = input("\n\n\n\n> ").lower()
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
# Commands

        # Repeat last option
        if option in ("again","do it again","do again","g"):
            option = option_last
        #Quit game
        if option in ("quit","quit game","end game","die","kill self","suicide", "commit suicide","end my life","end my suffering","throw in the towel","give up","give up on life","exit game"):
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
            print("Name:",playerName)
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
            print("Name:",playerName)
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
            print("spellLearn is:",spellLearn)
            print("spellUnlock is:",spellUnlock)
            print("spellPersuade is:",spellPersuade)
            print("spellJump is:",spellJump)
            print("spellLight is:",spellLight)
            print("spellHeal is:",spellHeal)
            print("spellFeed is:",spellFeed)
            print("spellKill is:",spellKill)
            print("spellKillself is:",spellKillself)
            print("wordDarkness is:",wordDarkness)
            print("wordReign is:",wordReign)
            print("wordStop is:",wordStop)
            print("wordServant is:",wordServant)
            print("wordMirror is:",wordMirror)
            print("wordDominion is:",wordDominion)
            print("wordCurse is:",wordCurse)
            notTurn = True
        elif option == "!learn" and debug:
            print("All Ozkavosh spells and words learned.")

            spellLearn.learn()
            demonWords.learn(spellUnlock)
            demonWords.learn(spellPersuade)
            demonWords.learn(spellJump)
            demonWords.learn(spellLight)
            demonWords.learn(spellHeal)
            demonWords.learn(spellFeed)
            demonWords.learn(spellKill)
            demonWords.learn(spellKillself)
            demonWords.learn(wordDarkness)
            demonWords.learn(wordReign)
            wordStop = 2
            demonWords.learn(wordServant)
            demonWords.learn(wordMirror)
            demonWords.learn(wordDominion)
            demonWords.learn(wordCurse)
            demonWords.learn(spellOblivion)
            notTurn = True
        # Examine
        # Inventory
        # Objects in inventory
        # Room
        elif option.startswith("examine") or option.startswith("x") or option in ("inventory","inv","i","l","spells","spellbook","words","demon words","ozkavosh","ozkavosh words","hunger","health","hp","stat","stats","self","help","shortcut","shortcuts","h","current objective","objective","o") or option.startswith("look"):
            if option.startswith("examine") or option.startswith("look") or option.startswith("x"):
                if option.startswith("x"):
                    option = option[2:]
                elif option.startswith("examine the"):
                    option = option[12:]
                elif option.startswith("examine a"):
                    option = option[10:]
                elif option.startswith("examine"):
                    option = option[8:]
                elif option.startswith("look at"):
                    option = option[8:]
                elif option.startswith("look"):
                    option = option[5:]
            # Nothing
            if option == "":
                print("Examine what?")
                option = False
                notTurn = True
            # Shortcut help
            if option in ("commands","shortcut","shortcuts","help"):
                print("\nShortcut commands:")
                shortcuts()
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
                demonWords.printWords()
                option = False
                notTurn = True
            if option:
                # Stats
                if option in ("hunger","h"):
                    stat.examineHunger()
                    print("You need to eat something in",stat.hunger,"turns.")
                    notTurn = True
                elif option in ("health","hp"):
                    stat.examineHealth()
                    if stat.health != HEALTH_MAX:
                        print("You need to heal your wounds in",stat.health,"turns.")
                    notTurn = True
                elif option in ("stat","stats","self"):
                    stat.examineHunger()
                    stat.examineHealth()
                    print("You need to eat something in",stat.hunger,"turns.")
                    if stat.health != HEALTH_MAX:
                        print("You need to heal your wounds in",stat.health,"turns.")
                elif option in ("o","objective","current objective"):
                    stat.printObjective()
                    notTurn = True
                # Spells
                elif option in ("spells","spell","spellbook","words","word","demon words","ozkavosh","ozkavosh words"):
                    demonWords.printWords()
                # Inventory
                elif option in ("inventory","inv","i"):
                    option = "inventory"
                    inv.examineInventory(option, roomCurrent)
                    notTurn = True
                elif option .endswith("gold"):
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith("letter"):
                    option = "letter"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("key","key of ahm'domosh")):
                    if inv.keySkeleton or roomCurrent.keySkeleton:
                        option = "key of Ahm'domosh"
                    else:
                        option = "key"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("pickaxe","pick","pickaxes","picks")):
                    option = "pickaxe"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("shrubbery","shrub")):
                    option = "shrubbery"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("half-eaten cake","half cake","half-eaten funnel cake","half eaten funnel cake","half funnel cake","half-eaten cakes","half cakes","half-eaten funnel cakes","half eaten funnel cakes","half funnel cakes")):
                    option = "half-eaten funnel cake"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("cake","cakes")):
                    if (inv.halfFunnelCake or roomCurrent.halfFunnelCake) and not inv.funnelCake and not roomCurrent.funnelCake:
                        option = "half-eaten funnel cake"
                    else:
                        option = "funnel cake"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("foot","rabbit","feet","foots")):
                    option = "lucky rabbit foot"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("bowl","porridge")):
                    if option.endswith("bowl") and (inv.bowl or roomCurrent.bowl) and not inv.porridge and not roomCurrent.porridge:
                        option = "bowl"
                    else:
                        option = "bowl of porridge"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("lantern","lanterns")):
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("oil","vial","vials")):
                    option = "vial of lantern oil"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("pies","pie")):
                    option = "chicken pot pie"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("biscuit","biscuits","hardtack")):
                    option = "hardtack biscuit"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("hook","hooks")):
                    option = "grappling hook"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("staff of garrotxa","staff")):
                    option = "staff of Garrotxa"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("ticket","raffle")):
                    option = "raffle ticket"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith("coal"):
                    option = "piece of coal"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith("potato"):
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("bandages","bandage")):
                    option = "bandage"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("journals","journal")):
                    option = "journal"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith("book"):
                    option = "book"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("brie","brie cheese")):
                    option = "slice of brie cheese"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("munster","munster cheese")):
                    option = "slice of munster cheese"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("stilton""stilton cheese")):
                    option = "slice of stilton cheese"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("swiss""swiss cheese")):
                    option = "slice of swiss cheese"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("wensleydale","wensleydale cheese")):
                    option = "slice of wensleydale cheese"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("potion","rejuvination")):
                    option = "potion of rejuvination"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith(("flask","flasks")):
                    option = "flask"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith("stone"):
                    option = "dragonstone"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith("bird"):
                    option = "wooden bird"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith("note"):
                    option = "note"
                    inv.examineInventory(option, roomCurrent)
                elif option.endswith("memo"):
                    option = "memo"
                    inv.examineInventory(option, roomCurrent)
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
                            roomCurrent.gold += 8
                            print("You find some supplies.")
                            roomCurrent.itemsPresent()
                    elif option in ("solid metal door","solid door","metal door","door"):
                        print("It's made of cold steel. There is a small slit in it.")
                    elif option in ("slit","small slit","through slit","through small slit"):
                        print("You can see out into the corridor. Other jail cells are visible, but you can't see inside of them.")
                        if not roomCurrent.counter_1 and not playerName:
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
                # Road Mid
                elif roomID == "roomRoadMid":
                    if option in ("sign","the sign"):
                        print("The sign reads: \"North: Mount Magna, East: Temple of Garrotxa, West: Lake Laguiole, South: Town of Airedale\"")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Mount Entrance
                elif roomID == "roomMountEntrance":
                    if option in ("sign","the sign"):
                        print("It reads: \"Mount Magna coal mine is CLOSED. Dangerous CREATURE inside.\"")
                    elif option in ("a body","body","the body","dead body","a dead body","the dead body"):
                        print("It looks like a miner covered in claw marks.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            roomCurrent.coal += LOOT_ENTRANCE_COAL
                            roomCurrent.note += LOOT_ENTRANCE_NOTE
                            print("You search the body and find some supplies.")
                            roomCurrent.itemsPresent()
                    elif option in ("vault","vault door"):
                        if roomCurrent.northBlocked:
                            print("It is locked.")
                        else:
                            print("It is unlocked.")
                        print("The first dial reads %s." % roomCurrent.counter_1)
                        print("The second dial reads %s." % roomCurrent.counter_2)
                        print("The third dial reads %s." % roomCurrent.counter_3)
                        print("The fourth dial reads %s." % roomCurrent.counter_4)
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
                    if option in ("writing","tablet","stone tablet"):
                        print("The writing on the tablet neatly reads, \"OZH VO'SES SA.\"")
                        demonWords.add(spellUnlock)
                    elif option in ("stranger"):
                        if roomCurrent.characterDead:
                            print("He's dead.")
                        elif roomCurrent.isGive():
                            print("He looks sad.")
                        else:
                            print("He looks happy to have his bird statue back.")
                    elif option in ("lockbox","box"):
                        if not roomCurrent.counter_1:
                            print("It's locked.")
                        elif roomCurrent.counter_2:
                            print("It's open.")
                        else:
                            print("It's unlocked, but closed.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                # Forest, Field
                elif roomID in ("roomForest","roomField"):
                    if option in ("hemisphere","obsidian","obsidian hemisphere"):
                        print("It looks strangely out of place.")
                    else:
                        print("You cannot examine that.")
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
                    if option in ("a body","body","the body","dead body","a dead body","the dead body"):
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
                elif roomID == "roomCave_4_mr":
                    if option in ("text","strange text","wall","walls"):
                        print("Written in blood, you can see the words, \"OZH SOL FEK.\"")
                        demonWords.add(spellKillself)
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomCave_6_lllm":
                    if option in ("a body","body","the body","dead body","a dead body","the dead body","corpse"):
                        print("The man is missing an arm and has claw marks on his chest.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            roomCurrent.gold += LOOT_A_GOLD
                            roomCurrent.oil += LOOT_A_OIL
                            print("You search the body and find some supplies.")
                            roomCurrent.itemsPresent()
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomCave_6_m":
                    if option in ("a body","body","the body","dead body","a dead body","the dead body","corpse"):
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
                        print("Just big enough for you to squeeze through, although probably not much else. You can see a DEAD BODY on the other side.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomLairHole":
                    if option in ("a body","body","the body","dead body","a dead body","the dead body","corpse"):
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
                            inside = "The staff and your pickaxe are the only items within armsreach, although trying to take them may WAKE UP the creature, which lays unconcious in the way."
                        elif roomCurrent.counter_3:
                            roomCurrent.pickaxe += 1
                            inside = "Your pickaxe is the only item within armsreach, although trying to take it may WAKE UP the creature, which lays unconcious in the way."
                        elif roomCurrent.counter_2:
                            roomCurrent.staff += 1
                            inside = "The staff is the only item within armsreach, although trying to take it may WAKE UP the creature, which lays unconcious in the way."
                        else:
                            inside = "Nothing in the backpack is within armsreach. The creature lays unconcious in the way."
                        print("Your backpack is somewhat visible within a gap in the rubble.",inside)
                        roomCurrent.itemsPresent()
                    elif option in ("a body","body","the body","dead body","a dead body","the dead body","corpse"):
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
                        demonWords.add(spellJump)
                    elif option in ("statue","gargoyle","gargoyle statue","pedestal","stone pedestal"):
                        print("It's made out of stone.")
                    elif option in ("crack","cracks","light","outside"):
                        print("Through the cracks, you can see a grassy field on the other end.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomShrineNorth":
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
                        print("Burnt into the barn wall reads, \"OZH GROTH SOL.\"")
                        demonWords.add(spellLight)
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomBarnUp":
                    if option in ("a body","body","the body","dead body","a dead body","the dead body"):
                        print("Most of it is burnt to a crisp.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            print("You search the body and find a memo.")
                            roomCurrent.memo += 1
                            roomCurrent.itemsPresent()
                            # inv.letterRead = "It reads:\n\nTo whoever is still alive,\n\nBy the time you read this, I will probably be dead. Within the last two weeks, the Ozhkavosh have invaded most of Kashkaval. My greatest fears have come true. Vesh'kathal is alive and has returned to the Overworld. I thought keeping away from the cities of Finn and Fermiere would be a good idea, but the demon lord himself, disguised as my very own son, found me here, tricked me into letting him into the barn, and burnt the whole damn farmland to the ground. I would have travelled down to Airedale, but there's no way I'm crossing that cursed mountain. If you are still alive and well, Airedale may be your last safe haven from the Ozhkavosh. Don't make the same mistake I did."
                    elif option in ("haystacks","hay","stacks of hay","haystack","stack of hay"):
                        print("Most of them are burnt.")
                    elif option in ("table"):
                        print("It is covered in ash.")
                        if not roomCurrent.itemFound:
                            roomCurrent.itemFound = True
                            print("You search the body and find a memo.")
                            roomCurrent.memo += 1
                            roomCurrent.itemsPresent()
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
                        demonWords.add(wordMirror)
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
                elif roomID == "roomHouseGate":
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
                        print("You do not recognize any of the faces.")
                    elif option in ("paintings","painting"):
                        print("They all show images of cheese.")
                    elif option in ("door","rectangular door","metal door","rectangular metal door"):
                        print("Reminds you of your jail cell.")
                    else:
                        print("You cannot examine that.")
                        notTurn = True
                elif roomID == "roomHouseKitchen":
                    if option in ("gadget","complicated gadget","gadgets","complicated gadgets"):
                        print("Made out of metal, you do not recognize any of them or understand what they're for.")
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
            if option.endswith("letter"):
                if inv.letter:
                    print(inv.letterRead)
                else:
                    print("You do not have a letter to read.")
                    notTurn = True
            elif option in ("journal","the journal","1","2","3","4","5","6","journal 1","journal 2","journal 3","journal 4","journal 5","journal 6","journal entry 1","journal entry 2","journal entry 3","journal entry 4","journal entry 5","journal entry 6", ):
                if inv.journal:
                    # Journal Reading interface
                    print("There are six dated entries.\n	1. 31 Friesla 577\n	2. 39 Friesla 577\n	3. 42 Friesla 577\n	4. 43 Friesla 577\n	5. 3 Chaumes 577\n	6. 6 Chaumes 577\n\nWhat would you like to read?")
                    choice_made = False
                    while not choice_made:
                        if not option.endswith(("1","2","3","4","5","6")):
                            option = input("\n> ").lower()
                        if option.endswith("1"):
                            choice_made = True
                            print("31 Friesla 577\n\nThis book is property of Rodney Williams of Pecorino Romano. I have been appointed to investigate reports of strange activity at the Mount Magna mine. While I have no current information about the mine itself, the locals of the nearby town named Airedale may provide some insight.")
                        elif option.endswith("2"):
                            choice_made = True
                            print("39 Friesla 577\n\nI have come across something very strange at the entrance of the mine. Someone, or something, has set up what seems to be an altar or shrine to the Ozkavosh. Various gargoyle statues sat around the room and there was a fountain oozing a mysterious purple fluid. I dared not find out what it was. Most shockingly, I found a partially-eaten human body at a table. We've either got cannibals here, or a vesh'raheen.")
                        elif option.endswith("3"):
                            choice_made = True
                            print("42 Friesla 577\n\nSpent the last few days here in the cavern. Airedale workers were all over the place mining for coal. I told them about the altar at the North entrance, but none of them believed me. I'll need to investigate further.")
                        elif option.endswith("4"):
                            choice_made = True
                            print("43 Friesla 577\n\nThe miners and I have been hearing strange noises in the cave. A few of us have reportedly gone missing. After a heated discussion concerning our safety, we've decided to abandon the mine and return to Airedale. On our way back, I found some writing along one of the cavern walls. \"OZH SOL FEK\" it read. I have no idea what it means.")
                            demonWords.add(spellKillself)
                        elif option.endswith("5"):
                            choice_made = True
                            print("3 Chaumes 577\n\nHaven't written an entry for a while now. The residents of the town have been very fairly nice and hospitable. Crime, however, have become more prevalent since the mine shut down, although the guards seem to keep it in check. Just learned that the mine was the town's central source of income, so a large portion of the population here are now without jobs. Maybe they should starting working in lumber with all the trees around. I tried to get more information about the mine from whoever I could although most don't like to talk about it. I've decided to return back to the cave to get to the bottom of this.")
                        elif option.endswith("6"):
                            choice_made = True
                            print("6 Chaumes 577\n\nA strange creature has been roaming the mines, killing the consuming those it comes it in contact with. While I haven't been able to obtain descriptions of its appearance, I have come across corpses covered in claw marks. Having a source of light seems to keep it away, if only for a while, which I've notified the locals of. That, along with all the growling and screeching I've been hearing, makes me fear this creature may be a vesh'raheen, servant of the Ozkavosh, Vesh'kathal. The problem is that there does not seem to be any reason for a vesh'raheen to be here as they do not live in caves, let alone, anywhere here in Kashkaval. That is, of course, unless Vesh'kathal wants to keep someone from entering or crossing the mines. I will return to Romano Pecorino to report my findings and scale up this investigation. Something very suspicious is happening here.")
                        else:
                            print("Choose 1-6.")
                else:
                    print("You do not have a journal to read.")
                    notTurn = True
            elif option.endswith("book"):
                if inv.book:
                    print("As you stare into the open book, the symbols on the pages consume your mind.")

                    if roomID == "roomBookMirror":
                        # From Mirror puzzle to Movement Puzzle
                        roomID = "roomBook_3_1"
                        roomCurrent = roomBook_3_1
                        inv = Inventory(note_1 = vaultAnswer_1, note_2 = vaultAnswer_2, note_3 = vaultAnswer_3, note_4 = vaultAnswer_4)
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
                        if roomID_Outside.startswith("roomHouse"):
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
                        inv = Inventory(note_1 = vaultAnswer_1, note_2 = vaultAnswer_2, note_3 = vaultAnswer_3, note_4 = vaultAnswer_4)
                        if inv_Outside.keySkeleton:
                            inv.keySkeleton = 1
                        if roomBook_3_End.itemFound:
                            inv.book = 1
                        silenced = False
                        print("You feel the strange force leave your body.")
                    roomCurrent.description()
                    roomCurrent.itemsPresent()
            elif option.endswith("note"):
                if inv.note:
                    print(inv.noteRead)
            elif option.endswith("memo"):
                if inv.memo:
                    print(inv.memoRead)
                    demonWords.learn(spellOblivion)
            elif option in ("sign", "the sign"):
                if roomID == "roomMountEntrance":
                    print("It reads: \"Mount Magna coal mine is CLOSED. Dangerous CREATURE inside.\"")
                elif roomID == "roomRoadMid":
                    print("The sign reads: \"North: Mount Magna, East: Temple of Garrotxa, West: Lake Laguiole, South: Town of Airedale\"")
                    roomCurrent.itemFound = True
                elif roomID == "roomCourtyardNorth":
                    print("The sign reads: \"Come one, come all, to the Wonderful Wheel of Mystery! Want to get rich quick? Spin the wheel test your luck! Only at the Airedale Carnival!\"")
                elif roomID == "roomRoad2Mid":
                    print("It reads: \"North: House of Roquefort, West: Fermiere Farm, South: Mount Magna\"")
                    roomCurrent.itemFound = True
                else:
                    print("There is no sign here.")
            elif roomID == "roomLake":
                if option in ("writing","tablet","stone tablet"):
                    print("The writing on the tablet neatly reads, \"OZH VO'SES SA.\"")
                    demonWords.add(spellUnlock)
                else:
                    print("You cannot read that.")
                    notTurn = True
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
            elif roomID == "roomCave_4_mr":
                if option in ("text","strange text","wall","walls"):
                    print("Written in blood, you can see the words, \"OZH SOL FEK.\"")
                    demonWords.add(spellKillself)
                else:
                    print("You cannot read that.")
                    notTurn = True
            elif roomID == "roomLairEast":
                if option in ("strange text","text","wall"):
                    print("While it is difficult to make to make the text out, it reads, \"OZH THOK ALATHO.\"")
                    demonWords.add(spellJump)
                else:
                    print("You cannot read that.")
                    notTurn = True
            elif roomID == "roomBarn":
                    if option in ("wall","writing"):
                        print("Burnt into the barn wall reads, \"Ozh groth sol.\"")
                        demonWords.add(spellLight)
                    else:
                        print("You cannot read that.")
                        notTurn = True
            elif roomID == "roomBookMirror":
                if option in ("text","wall","writing"):
                        print("Very faintly, you can see, \"Izh tal et ozh icha rek'tal.\"")
                        demonWords.add(wordMirror)
                else:
                    print("You cannot read that.")
                    notTurn = True
            elif roomID == "roomHouseGate":
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
        elif option.startswith(("use","unlock","mine","throw","light","fire","shoot","heal")):
            use = False
            unlock = False
            mine = False
            throw = False
            light = False
            fire = False
            shoot = False
            heal = False
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
            elif option.startswith("fire"):
                option = option[5:]
                if option == "":
                    print("Fire what?")
                    notTurn = True
                else:
                    fire = True
            elif option.startswith("shoot"):
                option = option[6:]
                if option == "":
                    print("Shoot what?")
                    notTurn = True
                else:
                    shoot = True
            elif option.startswith("heal"):
                option = option[5:]
                heal = True
            # Use/unlock key to unlock roomJailCell
            if (use and option.startswith("key")) or unlock:
                if roomID == "roomJailCell" and roomJailCell.eastBlocked:
                    if inv.key or inv.keySkeleton:
                        roomJailCell.eastBlocked = False
                        print("You unlock the cell door.")
                        print("You can now go East.")
                        if not roomCurrent.counter_1 and not playerName:
                            roomJailCorridor.letter += 1
                            roomCurrent.counterAns_1 = 1
                    else:
                        print("You cannot unlock the cell door without a key.")
                        notTurn = True
                elif roomID == "roomHouseEntrance" and roomHouseEntrance.northBlocked:
                    if inv.keySkeleton:
                        roomHouseEntrance.northBlocked = False
                        print("You unlock the house door. The key of Ahm'domosh disintegrates, returing to the Underworld.")
                        inv.keySkeleton -= 1
                    elif inv.key:
                        print("You cannot unlock the house door with your jail cell key.")
                        notTurn = True
                    else:
                        print("You cannot unlock the house door with a key.")
                elif roomID == "roomHouseGate" and roomHouseGate.northBlocked:
                    if inv.keySkeleton:
                        roomHouseGate.northBlocked = False
                        print("You unlock the gate with the key of Ahm'domosh.")
                    elif inv.key:
                        print("You cannot unlock the gate with your jail cell key.")
                        notTurn = True
                    else:
                        print("You cannot unlock the gate without a key.")
                        notTurn = True
                elif roomID == "roomLake" and not roomCurrent.counter_1:
                    if inv.keySkeleton:
                        roomCurrent.counter_1 = 1
                        print("You unlock the lockbox with the key of Ahm'domosh.")
                    elif inv.key:
                        print("You cannot unlock the lockbox with your jail cell key.")
                        notTurn = True
                    else:
                        print("You cannot unlock the lockbox without a key.")
                        notTurn = True
                elif roomID == "roomHouseHallway":
                    print("You do not have a key.")
                else:
                    print("There is nothing here to unlock.")
            # Use bandage to heal wounds
            elif (heal and option in ("","wounds","wound","bleeding","injury","self")) or (use and option in ("bandage","bandages")):
                if inv.bandage:
                    if stat.health < stat.healthmax:
                        stat.health = stat.healthmax
                        inv.bandage -= 1
                        print("You apply a bandage to your wound to stop the bleeding.")
                    else:
                        print("You are not injured.")
                        notTurn = True
                else:
                    print("You do not have any bandages.")
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
                        print("You do not have a lantern.")
                        notTurn = True
                else:
                    print("You do not have any vials of lantern oil.")
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
                    print("You do not have a grappling hook.")
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
                                if inv.coal > 1:
                                    print("You have",inv.coal,"pieces of coal.")
                                else:
                                    print("You have 1 piece of coal.")
                                if not roomCurrent.counter_1:
                                    roomCurrent.isMine = False
                                    print("There is no more coal left.")
                            else:
                                inv.pickaxe -= 1
                                print("You mine away at the coal vein and break your pickaxe.")
                                if inv.pickaxe > 1:
                                    print("You have",inv.pickaxe,"spare pickaxes.")
                                elif inv.pickaxe:
                                    print("You have a spare pickaxe.")
                                else:
                                    print("You have no spare pickaxe.")
                        elif (use and option in ("pickaxe","pick")) or (mine and option in ("rubble","rocks","rock")): # rubble
                            roomCurrent.counter_1 -= 1
                            print("You mine away at the rubble, breaking apart some of the rock.")
                            if not roomCurrent.counter_1:
                                roomCurrent.isMine = False
                                roomCurrent.northBlocked = False
                                print("The opening is now large enough to go through.")
                                print("You can now go North.")
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
            elif (use or fire or shoot) and option.startswith("the staff") or option.startswith("staff"):
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
                            print("the black knight. He shouts, \"IZH VO'POZ!\" before his body vaporizes into nothing.")
                            demonWords.add(spellPersuade)
                            roomRoadCorner.westBlocked = False
                        elif roomID == "roomHouseOffice":
                            roomCurrent.counter_2 += 1
                            if roomCurrent.counter_2 == 1:
                                print("Eden Von Roquefort. His dead body flies back and crashes on the desk.")
                            elif roomCurrent.counter_2 == 2:
                                print("Vesh'kathal. She resists the shock and charges towards you, knocking the staff out of your hand and onto the floor. The holy staff merely touching her arm causes her scales to burn, making her to topple over in pain.")
                                inv.staff -= 1
                                roomCurrent.staff += 1
                                roomCurrent.itemsPresent()
                            else:
                                print("Vesh'kathal. She is severly weaked, but manages to stay alive.")
                        elif roomID.startswith("roomCave"):
                            print("the creature, which resists the shock. It only gets angrier.")
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
                    print("You do not have the staff of Garrotxa.")
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
                elif heal:
                    print("You cannot heal that.")
                notTurn = True
        # Turn, set
        # Unlock vault in roomCorridor
        elif option.startswith("turn") or option.startswith("rotate") or option.startswith("set") or option.startswith("change") or option.startswith("push") or option.startswith("pull"):
            if option.startswith("turn"):
                option = option[5:]
                if option == "":
                    print("Turn what?")
                    notTurn = True
                elif roomID not in ("roomMountEntrance","roomHousePantry"):
                    print("You cannot turn that.")
                    notTurn = True
            elif option.startswith("rotate"):
                option = option[7:]
                if option == "":
                    print("Rotate what?")
                    notTurn = True
                elif roomID not in ("roomMountEntrance","roomHousePantry"):
                    print("You cannot rotate that.")
                    notTurn = True
            elif option.startswith("set"):
                option = option[4:]
                if option == "":
                    print("Set what?")
                    notTurn = True
                elif roomID not in ("roomMountEntrance","roomHousePantry","roomHouseKitchen"):
                    print("You cannot set that.")
                    notTurn = True
            elif option.startswith("change"):
                option = option[7:]
                if option == "":
                    print("Change what?")
                    notTurn = True
                elif roomID not in ("roomMountEntrance","roomHousePantry","roomHouseKitchen"):
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
                if roomID == "roomMountEntrance":
                    # Set numbers in vault in roomMountEntrance
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
                                if roomCurrent.counter_1 == option:
                                    print("The first dial is already set to %s." % option)
                                else:
                                    roomCurrent.counter_1 = option
                                    print("You set first dial to %i." % option)
                            elif change_vault_2:
                                if roomCurrent.counter_2 == option:
                                    print("The second dial is already set to %s." % option)
                                else:
                                    roomCurrent.counter_2 = option
                                    print("You set the second dial to %i." % option)
                            elif change_vault_3:
                                if roomCurrent.counter_3 == option:
                                    print("The third dial is already set to %s." % option)
                                else:
                                    roomCurrent.counter_3 = option
                                    print("You set the third dial to %i." % option)
                            elif change_vault_4:
                                if roomCurrent.counter_4 == option:
                                    print("The fourth dial is already set to %s." % option)
                                else:
                                    roomCurrent.counter_4 = option
                                    print("You set the fourth dial to %i." % option)
                            print("\nThe first dial reads %s." % roomCurrent.counter_1)
                            print("The second dial reads %s." % roomCurrent.counter_2)
                            print("The third dial reads %s." % roomCurrent.counter_3)
                            print("The fourth dial reads %s." % roomCurrent.counter_4)
                        else:
                            print("You cannot do that.")
                            notTurn = True
                    else:
                        print("You cannot do that.")
                        notTurn = True
                    if (roomCurrent.counter_1, roomCurrent.counter_2, roomCurrent.counter_3, roomCurrent.counter_4) == (roomCurrent.counterAns_1, roomCurrent.counterAns_2, roomCurrent.counterAns_3, roomCurrent.counterAns_4) and roomCurrent.northBlocked:
                        print("\nYou hear a loud clunk, as the vault door creaks open.")
                        print("You can now to North.")
                        roomCurrent.northBlocked = False
                elif roomID == "roomHousePantry":
                    change_dial = False
                    if option.startswith("dial to"):
                        option = option[8:]
                    elif option.startswith("dial"):
                        option = option[5:]
                    if option == "":
                        print("Change dial to what?")
                        notTurn = True
                    elif option == "green":
                        if not roomCurrent.counter_1 == "GREEN":
                            roomCurrent.counter_1 = "GREEN"
                            change_dial = True
                    elif option == "red":
                        if not roomCurrent.counter_1 == "RED":
                            roomCurrent.counter_1 = "RED"
                            change_dial = True
                    elif option == "blue":
                        if not roomCurrent.counter_1 == "BLUE":
                            roomCurrent.counter_1 = "BLUE"
                            change_dial = True
                    else:
                        print("You cannot change the dial to" + option + ".")
                    if option in ("green","red","blue"):
                        if change_dial:
                            print("You set the dial to " + option + ".")
                        else:
                            print("The dial is already set to " + option + ".")
                if roomHousePantry.counter_1 == DIAL_ANSWER and roomHouseKitchen.counter_1 == LEVER_ANSWER and roomHouseFoyer.northBlocked:
                    print("You hear a loud metalic clunk echo from the foyer.")
                    roomHouseFoyer.northBlocked = False
                elif roomID == "roomHouseKitchen":
                    if option.startswith("lever to"):
                        option = option[9:]
                    elif option.startswith("lever"):
                        option = option[6:]

                    if option == "":
                        print("Change lever to what?")
                        notTurn = True
                    elif option in ("forward","forwards"):
                        if roomCurrent.counter_1 == "FORWARD":
                            print("The lever is already set to the forward position.")
                        else:
                            roomCurrent.counter_1 = "FORWARD"
                            print("You set the lever to the forward position.")
                    elif option in ("backward","back","backwards"):
                        if roomCurrent.counter_1 == "BACKWARDS":
                            print("The lever is already set to the backwards position.")
                        else:
                            roomCurrent.counter_1 = "BACKWARDS"
                            print("You set the lever to the backwards position.")

                    else:
                        print("You cannot change the lever to " + option + ".")
                    if roomHousePantry.counter_1 == DIAL_ANSWER and roomHouseKitchen.counter_1 == LEVER_ANSWER and roomHouseFoyer.northBlocked:
                        print("You hear a loud metalic clunk echo from the foyer.")
                        roomHouseFoyer.northBlocked = False


        # Open
        # Treasure chest in roomCave__3_lllm_treasure_crevasse
        elif option.startswith("open"):
            option = option[5:]
            if roomID == "roomCave__3_lllm_treasure_crevasse":
                if option in ("chest","treasure chest","the chest","the treasure chest"):
                    if roomCurrent.counter_1:##continue ##
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
                        print("The chest is locked.")
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
                        print("The rusted hinges of the chest are broken, making it unable to close.")
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
                    elif inv.halfFunnelCake:
                        inv.halfFunnelCake -= 1
                        stat.hunger += HUNGER_HALFFUNNELCAKE
                    else:
                        foodEmpty = True
                elif option in ("half-eaten cake","half cake","half-eaten funnel cake","half eaten funnel cake","half funnel cake","half-eaten cakes","half cakes","half-eaten funnel cakes","half eaten funnel cakes","half funnel cakes"):
                    foodName = "half-eaten funnel cake"
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
                elif option in ("potato","rotten potato"):
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
                elif foodName in ("potato","rotten potato"):
                    print("You eat the rotten potato and quickly feel sick to your stomach. You thought the five second rule was a thing? What about the 5 month rule?")
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
                        stat.shield += HUNGER_POTION
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
                    if drinkName == "potion of rejuvination":
                        shieldMessage = "You feel an aura of energy surround you, PROTECTING you from HARM."
                    else:
                        shieldMessage = ""
                    print("You drink a " + drinkName + ".",shieldMessage)

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
                                        print("You do not have enough gold to fill",fill_count,"%s." % itemsName)
                                    else:
                                        print("You do not have enough gold to fill a %s." % itemName)
                            else:
                                if fill_count > 1:
                                    print("You do not have",fill_count,"flasks to fill.")
                                    notTurn = True
                                else:
                                    print("You do not have any flasks to fill.")
                                    notTurn = True

            else:
                # If not isFill
                print("You cannot fill anything here.")
                notTurn = True
        # Take, Get
        # Move items from room to inventory
        elif option.startswith(("take","get","pick up","grab","obtain","acquire")):
            if option.startswith("take"):
                option = option[5:]
            elif option.startswith("get"):
                option = option[4:]
            elif option.startswith("pick up"):
                option = option[8:]
            elif option.startswith("grab"):
                option = option[5:]
            elif option.startswith("obtain"):
                option = option[7:]
            elif option.startswith("acquire"):
                option = option[8:]
            itemName = False
            itemsName = False
            invItem = False
            roomItem = False
            take_count = 0
            # All/Everything
            if option in ("all","everything") and roomCurrent.itemTypes():
                if roomID == "roomTempleBasement" and not roomCurrent.characterDead and roomCurrent.staff:
                    print("\"You are not worthy to take the staff!\" The figure zaps you with a bolt of electricity.")
                    stat.lowerHealth()
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
                    inv.bird += roomCurrent.bird
                    inv.note += roomCurrent.note
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
                    roomCurrent.bird = 0
                    roomCurrent.note = 0
                    roomCurrent.memo = 0
                    print("You take everything.")
                itemName = "everything"
                itemsName = "everything"
                take_count = "all"
            # Gold # continue add quantities like in buy and sell
            elif "gold" in option and roomCurrent.gold:
                itemName = "gold"
                itemsName = "gold"
                invItem = inv.gold
                roomItem = roomCurrent.gold
                if option == itemName:
                    take_count = roomCurrent.gold
                elif option.endswith("gold"):
                    take_count = option[:len(option) - len("gold") - 1]
            # Letter
            elif "letter" in option and roomCurrent.letter:
                itemName = "letter"
                itemsName = "letters"
                invItem = inv.letter
                roomItem = roomCurrent.letter
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith("letter"):
                    take_count = option[:len(option) - len("letter") - 1]
                elif option.endswith("letters"):
                    take_count = option[:len(option) - len("letters") - 1]
            # Skeleton key
            elif "key of ahm'domosh" in option and roomCurrent.keySkeleton:
                itemName = "key of Ahm'domosh"
                itemsName = "keys of Ahm'domosh"
                invItem = inv.keySkeleton
                roomItem = roomCurrent.keySkeleton
                if option == itemName.lower():
                    take_count = 1
                elif option == itemsName.lower():
                    take_count = roomItem
                elif option.endswith("key of ahm'domosh"):
                    take_count = option[:len(option) - len("key of ahm'domosh") - 1]
                elif option.endswith("keys of ahm'domosh"):
                    take_count = option[:len(option) - len("keys of ahm'domosh") - 1]
            # Key
            elif "key" in option and (roomCurrent.key or roomCurrent.keySkeleton):
                itemName = "key"
                itemsName = "keys"
                if roomCurrent.key:
                    invItem = inv.key
                    roomItem = roomCurrent.key
                elif roomCurrent.keySkeleton:
                    invItem = inv.keySkeleton
                    roomItem = roomCurrent.keySkeleton
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith("key"):
                    take_count = option[:len(option) - len("key") - 1]
                elif option.endswith("keys"):
                    take_count = option[:len(option) - len("keys") - 1]
            # Pickaxe
            elif "pick" in option and roomCurrent.pickaxe:
                itemName = "pickaxe"
                itemsName = "pickaxes"
                invItem = inv.pickaxe
                roomItem = roomCurrent.pickaxe
                if option in (itemName,"pick"):
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith("pick"):
                    take_count = option[:len(option) - len("pick") - 1]
                elif option.endswith("picks"):
                    take_count = option[:len(option) - len("picks") - 1]
                elif option.endswith("pickaxe"):
                    take_count = option[:len(option) - len("pickaxe") - 1]
                elif option.endswith("pickaxes"):
                    take_count = option[:len(option) - len("pickaxes") - 1]
            # shrubbery
            elif ("shrubbery" in option) or ("shrubberies" in option) and roomCurrent.shrubbery:
                itemName = "shrubbery"
                itemsName = "shrubberies"
                invItem = inv.shrubbery
                roomItem = roomCurrent.shrubbery
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith("shrubbery"):
                    take_count = option[:len(option) - len("shrubbery") - 1]
                elif option.endswith("shrubberies"):
                    take_count = option[:len(option) - len("shrubberies") - 1]
            # Half-eaten funnel cake
            elif ("cake" in option) and ("half" in option) and roomCurrent.halfFunnelCake:
                itemName = "half-eaten funnel cake"
                itemsName = "half-eaten funnel cakes"
                invItem = inv.halfFunnelCake
                roomItem = roomCurrent.halfFunnelCake
                if option.endswith("half-eaten funnel cake"):
                    take_count = option[:len(option) - len("half-eaten funnel cake") - 1]
                if option.endswith("half eaten funnel cake"):
                    take_count = option[:len(option) - len("half eaten funnel cake") - 1]
                elif option.endswith("half funnel cake"):
                    take_count = option[:len(option) - len("half funnel cake") - 1]
                elif option.endswith("half-eaten cake"):
                    take_count = option[:len(option) - len("half-eaten cake") - 1]
                elif option.endswith("half eaten cake"):
                    take_count = option[:len(option) - len("half eaten cake") - 1]
                elif option.endswith("half cake"):
                    take_count = option[:len(option) - len("half cake") - 1]
                elif option.endswith("half-eaten funnel cakes"):
                    take_count = option[:len(option) - len("half-eaten funnel cakes") - 1]
                elif option.endswith("half eaten funnel cakes"):
                    take_count = option[:len(option) - len("half eaten funnel cakes") - 1]
                elif option.endswith("half funnel cakes"):
                    take_count = option[:len(option) - len("half funnel cakes") - 1]
                elif option.endswith("half-eaten cakes"):
                    take_count = option[:len(option) - len("half-eaten cakes") - 1]
                elif option.endswith("half eaten cakes"):
                    take_count = option[:len(option) - len("half eaten cakes") - 1]
                elif option.endswith("half cakes"):
                    take_count = option[:len(option) - len("half cakes") - 1]
            # Funnel cake and Half-eaten funnel cake
            # If half-eaten cake is in room and cake not in room, treat half-eaten cake as cake
            elif "cake" in option and (roomCurrent.funnelCake or roomCurrent.halfFunnelCake):
                if roomCurrent.halfFunnelCake and not roomCurrent.funnelCake:
                    itemName = "half-eaten funnel cake"
                    itemsName = "half-eaten funnel cakes"
                    invItem = inv.halfFunnelCake
                    roomItem = roomCurrent.halfFunnelCake
                else:
                    itemName = "funnel cake"
                    itemsName = "funnel cakes"
                    invItem = inv.funnelCake
                    roomItem = roomCurrent.funnelCake
                if option in (itemName,"cake","funnel cake"):
                    take_count = 1
                elif option in (itemsName,"cakes","funnel cakes"):
                    take_count = roomItem
                elif option.endswith("funnel cake"):
                    take_count = option[:len(option) - len("funnel cake") - 1]
                elif option.endswith("funnel cakes"):
                    take_count = option[:len(option) - len("funnel cakes") - 1]
                elif option.endswith("cake"):
                    take_count = option[:len(option) - len("cake") - 1]
                elif option.endswith("cakes"):
                    take_count = option[:len(option) - len("cakes") - 1]
            # Lucky rabbit foot
            elif ("foot" in option) or ("feet" in option) and roomCurrent.foot:
                itemName = "lucky rabbit foot"
                itemsName = "lucky rabbit feet"
                invItem = inv.foot
                roomItem = roomCurrent.foot
                if option in (itemName,"foot","rabbit foot","lucky foot"):
                    take_count = 1
                elif option in (itemsName,"feet","rabbit feet","lucky feet"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith(("rabbit foot","rabbit feet")):
                    take_count = option[:len(option) - len("rabbit foot") - 1]
                elif option.endswith(("lucky foot","lucky feet")):
                    take_count = option[:len(option) - len("lucky foot") - 1]
                elif option.endswith(("foot","feet")):
                    take_count = option[:len(option) - len("foot") - 1]
            # Porridge (bowl of porridge)
            elif "porridge" in option and roomCurrent.porridge:
                itemName = "bowl of porridge"
                itemsName = "bowls of porridge"
                invItem = inv.porridge
                roomItem = roomCurrent.porridge
                if option == itemName:
                    take_count = 1
                elif option in (itemsName,"porridge"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("porridge"):
                    take_count = option[:len(option) - len("porridge") - 1]
            # Bowl
            elif "bowl" in option and (roomCurrent.bowl or roomCurrent.porridge):
                if roomCurrent.porridge and not roomCurrent.bowl:
                    itemName = "bowl of porridge"
                    itemsName = "bowls of porridge"
                    invItem = inv.porridge
                    roomItem = roomCurrent.porridge
                else:
                    itemName = "bowl"
                    itemsName = "bowls"
                    invItem = inv.bowl
                    roomItem = roomCurrent.bowl
                if option in (itemName,"bowl"):
                    take_count = 1
                elif option in (itemsName,"bowls"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]

            # Vial of lantern oil
            elif ("oil" in option) or ("vial" in option) and roomCurrent.oil:
                itemName = "vial of lantern oil"
                itemsName = "vials of lantern oil"
                invItem = inv.oil
                roomItem = roomCurrent.oil
                if option in (itemName,"vial"):
                    take_count = 1
                elif option in (itemsName,"oil","vials","lantern oil"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("vial"):
                    take_count = option[:len(option) - len("vial") - 1]
                elif option.endswith("vials"):
                    take_count = option[:len(option) - len("vials") - 1]
                elif option.endswith("lantern oil"):
                    take_count = option[:len(option) - len("lantern oil") - 1]
                elif option.endswith("oil"):
                    take_count = option[:len(option) - len("oil") - 1]
            # Lantern
            elif "lantern" in option and roomCurrent.lantern:
                itemName = "lantern"
                itemsName = "lanterns"
                invItem = inv.lantern
                roomItem = roomCurrent.lantern
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]

            # Hardtack biscuit
            elif ("biscuit" in option) or ("hardtack" in option) and roomCurrent.biscuit:
                itemName = "hardtack biscuit"
                itemsName = "hardtack biscuit"
                invItem = inv.biscuit
                roomItem = roomCurrent.biscuit
                if option in (itemName,"biscuit"):
                    take_count = 1
                elif option in (itemsName,"biscuits"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("biscuit"):
                    take_count = option[:len(option) - len("biscuit") - 1]
                elif option.endswith("biscuits"):
                    take_count = option[:len(option) - len("biscuits") - 1]
                elif option.endswith("hardtack"):
                    take_count = option[:len(option) - len("hardtack") - 1]
            # Coil of hook
            elif "hook" in option and roomCurrent.hook:
                itemName = "grappling hook"
                itemsName = "grappling hooks"
                invItem = inv.hook
                roomItem = roomCurrent.hook
                if option in (itemName,"hook"):
                    take_count = 1
                elif option in (itemsName,"hooks"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("hook"):
                    take_count = option[:len(option) - len("hook") - 1]
                elif option.endswith("hooks"):
                    take_count = option[:len(option) - len("hooks") - 1]
            # The staff of Garrotxa
            elif ("staff" in option) or ("staves" in option) and roomCurrent.staff:
                itemName = "staff of Garrotxa"
                itemsName = "staves of Garrotxa"
                invItem = inv.staff
                roomItem = roomCurrent.staff
                if option in (itemName.lower(),"staff"):
                    take_count = 1
                elif option in (itemsName.lower(),"staves"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName.lower()) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName.lower()) - 1]
                elif option.endswith("staff"):
                    take_count = option[:len(option) - len("staff") - 1]
                elif option.endswith("staves"):
                    take_count = option[:len(option) - len("staves") - 1]
            # Raffle ticket
            elif "ticket" in option and roomCurrent.ticket:
                itemName = "raffle ticket"
                itemsName = "raffle tickets"
                invItem = inv.ticket
                roomItem = roomCurrent.ticket
                if option in (itemName,"ticket"):
                    take_count = 1
                elif option in (itemsName,"tickets"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("ticket"):
                    take_count = option[:len(option) - len("ticket") - 1]
                elif option.endswith("tickets"):
                    take_count = option[:len(option) - len("tickets") - 1]
            # Piece of coal
            elif "coal" in option and roomCurrent.coal:
                itemName = "piece of coal"
                itemsName = "pieces of coal"
                invItem = inv.coal
                roomItem = roomCurrent.coal
                if option == itemName:
                    take_count = 1
                elif option in (itemsName,"coal"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("coal"):
                    take_count = option[:len(option) - len("coal") - 1]
            # Potato
            elif "potato" in option and roomCurrent.potato:
                itemName = "rotten potato"
                itemsName = "rotten potatoes"
                invItem = inv.potato
                roomItem = roomCurrent.potato
                if option in (itemName,"potato"):
                    take_count = 1
                elif option in (itemsName,"potatoes"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("potato"):
                    take_count = option[:len(option) - len("potato") - 1]
                elif option.endswith("potatoes"):
                    take_count = option[:len(option) - len("potatoes") - 1]

            # Bandage
            elif "bandage" in option and roomCurrent.bandage:
                itemName = "bandage"
                itemsName = "bandages"
                invItem = inv.bandage
                roomItem = roomCurrent.bandage
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
            # Journal
            elif "journal" in option and roomCurrent.journal:
                itemName = "journal"
                itemsName = "journals"
                invItem = inv.journal
                roomItem = roomCurrent.journal
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
            # Mysterious book
            elif "book" in option and roomCurrent.book:
                itemName = "mysterious book"
                itemsName = "mysterious books"
                invItem = inv.book
                roomItem = roomCurrent.book
                if option in (itemName,"book"):
                    take_count = 1
                elif option in (itemsName,"books"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len("book") - len("book") - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len("books") - len("books") - 1]
             # Cheeses
            elif "brie" in option and roomCurrent.brie:
                itemName = "slice of brie cheese"
                itemsName = "slices of brie cheese"
                invItem = inv.brie
                roomItem = roomCurrent.brie
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("brie"):
                    take_count = option[:len(option) - len("brie") - 1]
                elif option.endswith("brie cheese"):
                    take_count = option[:len(option) - len("brie cheese") - 1]
            elif "munster" in option and roomCurrent.munster:
                itemName = "slice of munster cheese"
                itemsName = "slices of munster cheese"
                invItem = inv.munster
                roomItem = roomCurrent.munster
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("munster"):
                    take_count = option[:len(option) - len("munster") - 1]
                elif option.endswith("munster cheese"):
                    take_count = option[:len(option) - len("munster cheese") - 1]
            elif "stilton" in option and roomCurrent.brie:
                itemName = "slice of stilton cheese"
                itemsName = "slices of stilton cheese"
                invItem = inv.stilton
                roomItem = roomCurrent.stilton
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("stilton"):
                    take_count = option[:len(option) - len("stilton") - 1]
                elif option.endswith("stilton cheese"):
                    take_count = option[:len(option) - len("stilton cheese") - 1]
            elif "swiss" in option and roomCurrent.brie:
                itemName = "slice of swiss cheese"
                itemsName = "slices of swiss cheese"
                invItem = inv.swiss
                roomItem = roomCurrent.swiss
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("swiss"):
                    take_count = option[:len(option) - len("swiss") - 1]
                elif option.endswith("swiss cheese"):
                    take_count = option[:len(option) - len("swiss cheese") - 1]
            elif "wensleydale" in option and roomCurrent.brie:
                itemName = "slice of wensleydale cheese"
                itemsName = "slices of wensleydale cheese"
                invItem = inv.wensleydale
                roomItem = roomCurrent.wensleydale
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("wensleydale"):
                    take_count = option[:len(option) - len("wensleydale") - 1]
                elif option.endswith("wensleydale cheese"):
                    take_count = option[:len(option) - len("wensleydale cheese") - 1]
            # Potion
            elif "potion" in option and roomCurrent.potion:
                itemName = "potion of rejuvination"
                itemsName = "potions of rejuvination"
                invItem = inv.potion
                roomItem = roomCurrent.potion
                if option in (itemName,"potion"):
                    take_count = 1
                elif option in (itemsName,"potions"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("potion"):
                    take_count = option[:len(option) - len("potion") - 1]
                elif option.endswith("potions"):
                    take_count = option[:len(option) - len("potions") - 1]
            # Flask
            elif "flask" in option and roomCurrent.flask:
                itemName = "flask"
                itemsName = "flasks"
                invItem = inv.flask
                roomItem = roomCurrent.flask
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
            # Dragonstone
            elif "stone" in option and roomCurrent.stone:
                itemName = "dragonstone"
                itemsName = "dragonstones"
                invItem = inv.stone
                roomItem = roomCurrent.stone
                if option in (itemName,"stone"):
                    take_count = 1
                elif option in (itemsName,"stones"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("stone"):
                    take_count = option[:len(option) - len("stone") - 1]
                elif option.endswith("stones"):
                    take_count = option[:len(option) - len("stones") - 1]
            # Wooden bird
            elif "bird" in option and roomCurrent.bird:
                itemName = "wooden bird"
                itemsName = "wooden birds"
                invItem = inv.bird
                roomItem = roomCurrent.bird
                if option in (itemName,"bird"):
                    take_count = 1
                elif option in (itemsName,"birds"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("bird"):
                    take_count = option[:len(option) - len("bird") - 1]
                elif option.endswith("birds"):
                    take_count = option[:len(option) - len("birds") - 1]
            # Note
            elif "note" in option and roomCurrent.note:
                itemName = "note"
                itemsName = "notes"
                invItem = inv.note
                roomItem = roomCurrent.note
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
            # Memo
            elif "memo" in option and roomCurrent.memo:
                itemName = "memo"
                itemsName = "memos"
                invItem = inv.memo
                roomItem = roomCurrent.memo
                if option == itemName:
                    take_count = 1
                elif option == itemsName:
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
            # Chicken pot pie
            elif "pie" in option and roomCurrent.pie:
                itemName = "chicken pot pie"
                itemsName = "chicken pot pies"
                invItem = inv.pie
                roomItem = roomCurrent.pie
                if option in (itemName,"chicken pie","pot pie","pie"):
                    take_count = 1
                elif option in (itemsName,"chicken pies","pot pies","pies"):
                    take_count = roomItem
                elif option.endswith(itemName):
                    take_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    take_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("pot pie"):
                    take_count = option[:len(option) - len("pot pie") - 1]
                elif option.endswith("chicken pie"):
                    take_count = option[:len(option) - len("chicken pie") - 1]
                elif option.endswith("pie"):
                    take_count = option[:len(option) - len("pie") - 1]
                elif option.endswith("pot pies"):
                    take_count = option[:len(option) - len("pot pies") - 1]
                elif option.endswith("chicken pies"):
                    take_count = option[:len(option) - len("chicken pies") - 1]
                elif option.endswith("pies"):
                    take_count = option[:len(option) - len("pies") - 1]
            elif option == "":
                print("Take what?")
                notTurn = True
            else:
                if option in ("all","everything"):
                    print("You cannot take anything here.")
                else:
                    print("There is no %s here to take." % option)
                notTurn = True
            if itemName:
                takeCountOkay = False
                try:
                    take_count = float(take_count)
                    if take_count % 1 == 0 and take_count > 0 and take_count <= roomItem:
                         take_count = int(take_count)
                         takeCountOkay = True
                    else:
                        print("You cannot take",take_count,"%s." % itemsName)
                        notTurn = True
                except ValueError:
                    if take_count in ("all","every"):
                        take_count = roomItem
                        takeCountOkay = True
                    elif take_count in ("a","the","only 1","only one"):
                        take_count = 1
                        takeCountOkay = True
                    else:
                        print("You cannot take \"" + take_count + "\" %s." % itemsName)
                        notTurn = True
                if takeCountOkay and not itemName == "everything":
                    # Once takeCountOkay, take events go here
                    if roomID == "roomTempleBasement" and not roomCurrent.characterDead and itemName == "staff of Garrotxa":
                        print("\"You are not worthy to take the staff!\" The figure zaps you with a bolt of electricity.")
                        stat.lowerHealth()
                        itemName = ""
                    elif roomID == "roomLairMid" and roomCurrent.counter_2 and not creatureLairChase and itemName == "staff of Garrotxa":
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
                    elif roomID == "roomLairMid" and roomCurrent.counter_1 and not creatureLairChase and itemName == "pickaxe":
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
                            print("Unfortunately, the lack of light allows the creature to quickly recover.")
                            roomCurrent.counter_1 += 1
                    elif take_count > 1:
                        print("You take %s %s." % (take_count,itemsName))
                    elif roomItem > 1:
                        print("You take a %s." % itemName)
                    else:
                        print("You take the %s." % itemName)
                    invItem += take_count
                    roomItem -= take_count
                    if itemName == "gold":
                        inv.gold = invItem
                        roomCurrent.gold = roomItem
                    elif itemName == "letter":
                        inv.letter = invItem
                        roomCurrent.letter = roomItem
                    elif itemName == "key":
                        inv.key = invItem
                        roomCurrent.key = roomItem
                    elif itemName == "key of Ahm'domosh":
                        inv.keySkeleton = invItem
                        roomCurrent.keySkeleton = roomItem
                    elif itemName == "pickaxe":
                        inv.pickaxe = invItem
                        roomCurrent.pickaxe = roomItem
                    elif itemName == "shrubbery":
                        inv.shrubbery = invItem
                        roomCurrent.shrubbery = roomItem
                    elif itemName == "funnel cake":
                        inv.funnelCake = invItem
                        roomCurrent.funnelCake = roomItem
                    elif itemName == "half-eaten funnel cake":
                        inv.halfFunnelCake = invItem
                        roomCurrent.halfFunnelCake = roomItem
                    elif itemName == "lucky rabbit foot":
                        inv.foot = invItem
                        roomCurrent.foot = roomItem
                    elif itemName == "bowl of porridge":
                        inv.porridge = invItem
                        roomCurrent.porridge = roomItem
                    elif itemName == "bowl":
                        inv.bowl = invItem
                        roomCurrent.bowl = roomItem
                    elif itemName == "lantern":
                        inv.lantern = invItem
                        roomCurrent.lantern = roomItem
                    elif itemName == "vial of lantern oil":
                        inv.oil = invItem
                        roomCurrent.oil = roomItem
                    elif itemName == "chicken pot pie":
                        inv.pie = invItem
                        roomCurrent.pie = roomItem
                    elif itemName == "hardtack biscuit":
                        inv.biscuit = invItem
                        roomCurrent.biscuit = roomItem
                    elif itemName == "grappling hook":
                        inv.hook = invItem
                        roomCurrent.hook = roomItem
                    elif itemName == "staff of Garrotxa":
                        inv.staff = invItem
                        roomCurrent.staff = roomItem
                    elif itemName == "raffle ticket":
                        inv.ticket = invItem
                        roomCurrent.ticket = roomItem
                    elif itemName == "piece of coal":
                        inv.coal = invItem
                        roomCurrent.coal = roomItem
                    elif itemName == "rotten potato":
                        inv.potato = invItem
                        roomCurrent.potato = roomItem
                    elif itemName == "bandage":
                        inv.bandage = invItem
                        roomCurrent.bandage = roomItem
                    elif itemName == "journal":
                        inv.journal = invItem
                        roomCurrent.journal = roomItem
                    elif itemName == "mysterious book":
                        inv.book = invItem
                        roomCurrent.book = roomItem
                    elif itemName == "slice of brie cheese":
                        inv.brie = invItem
                        roomCurrent.brie = roomItem
                    elif itemName == "slice of munster cheese":
                        inv.munster = invItem
                        roomCurrent.munster = roomItem
                    elif itemName == "slice of stilton cheese":
                        inv.stilton = invItem
                        roomCurrent.stilton = roomItem
                    elif itemName == "slice of swiss cheese":
                        inv.swiss = invItem
                        roomCurrent.swiss = roomItem
                    elif itemName == "slice of wensleydale cheese":
                        inv.wensleydale = invItem
                        roomCurrent.wensleydale = roomItem
                    elif itemName == "potion of rejuvination":
                        inv.potion = invItem
                        roomCurrent.potion = roomItem
                    elif itemName == "flask":
                        inv.flask = invItem
                        roomCurrent.flask = roomItem
                    elif itemName == "dragonstone":
                        inv.stone = invItem
                        roomCurrent.stone = roomItem
                    elif itemName == "wooden bird":
                        inv.bird = invItem
                        roomCurrent.bird = roomItem
                    elif itemName == "note":
                        inv.note = invItem
                        roomCurrent.note = roomItem
                    elif itemName == "memo":
                        inv.memo = invItem
                        roomCurrent.memo = roomItem
                if invItem > 1 and not invItem == take_count:
                    print("You have",invItem,"%s." % itemsName)
        # Drop
        elif option.startswith(("drop","discard","remove")):
            if option.startswith("drop"):
                option = option[5:]
            elif option.startswith("discard"):
                option = option[8:]
            elif option.startswith("remove"):
                option = option[7:]
            itemName = False
            itemsName = False
            roomItem = False
            invItem = False
            drop_count = 0
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
                roomCurrent.flask += inv.flask
                roomCurrent.stone += inv.stone
                roomCurrent.bird += inv.bird
                roomCurrent.memo += inv.memo
                inv = Inventory(note_1 = vaultAnswer_1, note_2 = vaultAnswer_2, note_3 = vaultAnswer_3, note_4 = vaultAnswer_4)
                print("You drop everything.")
                itemName = "everything"
                itemsName = "everything"
                drop_count = "all"
            # Gold # continue add quantities like in buy and sell
            elif "gold" in option and inv.gold:
                itemName = "gold"
                itemsName = "gold"
                roomItem = roomCurrent.gold
                invItem = inv.gold
                if option == itemName:
                    drop_count = inv.gold
                elif option.endswith("gold"):
                    drop_count = option[:len(option) - len("gold") - 1]
            # Letter
            elif "letter" in option and inv.letter:
                itemName = "letter"
                itemsName = "letters"
                roomItem = roomCurrent.letter
                invItem = inv.letter
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith("letter"):
                    drop_count = option[:len(option) - len("letter") - 1]
                elif option.endswith("letters"):
                    drop_count = option[:len(option) - len("letters") - 1]
            # Skeleton key
            elif "key of ahm'domosh" in option and inv.keySkeleton:
                itemName = "key of Ahm'domosh"
                itemsName = "keys of Ahm'domosh"
                roomItem = roomCurrent.keySkeleton
                invItem = inv.keySkeleton
                if option == itemName.lower():
                    drop_count = 1
                elif option == itemsName.lower():
                    drop_count = invItem
                elif option.endswith("key of ahm'domosh"):
                    drop_count = option[:len(option) - len("key of ahm'domosh") - 1]
                elif option.endswith("keys of ahm'domosh"):
                    drop_count = option[:len(option) - len("keys of ahm'domosh") - 1]
            # Key
            elif "key" in option and (inv.key or inv.keySkeleton):
                itemName = "key"
                itemsName = "keys"
                if inv.key:
                    roomItem = roomCurrent.key
                    invItem = inv.key
                elif inv.keySkeleton:
                    roomItem = roomCurrent.keySkeleton
                    invItem = inv.keySkeleton
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith("key"):
                    drop_count = option[:len(option) - len("key") - 1]
                elif option.endswith("keys"):
                    drop_count = option[:len(option) - len("keys") - 1]
            # Pickaxe
            elif "pick" in option and inv.pickaxe:
                itemName = "pickaxe"
                itemsName = "pickaxes"
                roomItem = roomCurrent.pickaxe
                invItem = inv.pickaxe
                if option in (itemName,"pick"):
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith("pick"):
                    drop_count = option[:len(option) - len("pick") - 1]
                elif option.endswith("picks"):
                    drop_count = option[:len(option) - len("picks") - 1]
                elif option.endswith("pickaxe"):
                    drop_count = option[:len(option) - len("pickaxe") - 1]
                elif option.endswith("pickaxes"):
                    drop_count = option[:len(option) - len("pickaxes") - 1]
            # shrubbery
            elif ("shrubbery" in option) or ("shrubberies" in option) and inv.shrubbery:
                itemName = "shrubbery"
                itemsName = "shrubberies"
                roomItem = roomCurrent.shrubbery
                invItem = inv.shrubbery
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith("shrubbery"):
                    drop_count = option[:len(option) - len("shrubbery") - 1]
                elif option.endswith("shrubberies"):
                    drop_count = option[:len(option) - len("shrubberies") - 1]
            # Half-eaten funnel cake
            elif ("cake" in option) and ("half" in option) and inv.halfFunnelCake:
                itemName = "half-eaten funnel cake"
                itemsName = "half-eaten funnel cakes"
                roomItem = roomCurrent.halfFunnelCake
                invItem = inv.halfFunnelCake
                if option.endswith("half-eaten funnel cake"):
                    drop_count = option[:len(option) - len("half-eaten funnel cake") - 1]
                if option.endswith("half eaten funnel cake"):
                    drop_count = option[:len(option) - len("half eaten funnel cake") - 1]
                elif option.endswith("half funnel cake"):
                    drop_count = option[:len(option) - len("half funnel cake") - 1]
                elif option.endswith("half-eaten cake"):
                    drop_count = option[:len(option) - len("half-eaten cake") - 1]
                elif option.endswith("half eaten cake"):
                    drop_count = option[:len(option) - len("half eaten cake") - 1]
                elif option.endswith("half cake"):
                    drop_count = option[:len(option) - len("half cake") - 1]
                elif option.endswith("half-eaten funnel cakes"):
                    drop_count = option[:len(option) - len("half-eaten funnel cakes") - 1]
                elif option.endswith("half eaten funnel cakes"):
                    drop_count = option[:len(option) - len("half eaten funnel cakes") - 1]
                elif option.endswith("half funnel cakes"):
                    drop_count = option[:len(option) - len("half funnel cakes") - 1]
                elif option.endswith("half-eaten cakes"):
                    drop_count = option[:len(option) - len("half-eaten cakes") - 1]
                elif option.endswith("half eaten cakes"):
                    drop_count = option[:len(option) - len("half eaten cakes") - 1]
                elif option.endswith("half cakes"):
                    drop_count = option[:len(option) - len("half cakes") - 1]
            # Funnel cake and Half-eaten funnel cake
            # If half-eaten cake is in room and cake not in inv, treat half-eaten cake as cake
            elif "cake" in option and (inv.funnelCake or inv.halfFunnelCake):
                if inv.halfFunnelCake and not inv.funnelCake:
                    itemName = "half-eaten funnel cake"
                    itemsName = "half-eaten funnel cakes"
                    roomItem = roomCurrent.halfFunnelCake
                    invItem = inv.halfFunnelCake
                else:
                    itemName = "funnel cake"
                    itemsName = "funnel cakes"
                    roomItem = roomCurrent.funnelCake
                    invItem = inv.funnelCake
                if option in (itemName,"cake","funnel cake"):
                    drop_count = 1
                elif option in (itemsName,"cakes","funnel cakes"):
                    drop_count = invItem
                elif option.endswith("funnel cake"):
                    drop_count = option[:len(option) - len("funnel cake") - 1]
                elif option.endswith("funnel cakes"):
                    drop_count = option[:len(option) - len("funnel cakes") - 1]
                elif option.endswith("cake"):
                    drop_count = option[:len(option) - len("cake") - 1]
                elif option.endswith("cakes"):
                    drop_count = option[:len(option) - len("cakes") - 1]
            # Lucky rabbit foot
            elif ("foot" in option) or ("feet" in option) and inv.foot:
                itemName = "lucky rabbit foot"
                itemsName = "lucky rabbit feet"
                roomItem = roomCurrent.foot
                invItem = inv.foot
                if option in (itemName,"foot","rabbit foot","lucky foot"):
                    drop_count = 1
                elif option in (itemsName,"feet","rabbit feet","lucky feet"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith(("rabbit foot","rabbit feet")):
                    drop_count = option[:len(option) - len("rabbit foot") - 1]
                elif option.endswith(("lucky foot","lucky feet")):
                    drop_count = option[:len(option) - len("lucky foot") - 1]
                elif option.endswith(("foot","feet")):
                    drop_count = option[:len(option) - len("foot") - 1]
            # Porridge (bowl of porridge)
            elif "porridge" in option and inv.porridge:
                itemName = "bowl of porridge"
                itemsName = "bowls of porridge"
                roomItem = roomCurrent.porridge
                invItem = inv.porridge
                if option == itemName:
                    drop_count = 1
                elif option in (itemsName,"porridge"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("porridge"):
                    drop_count = option[:len(option) - len("porridge") - 1]
            # Bowl
            elif "bowl" in option and (inv.bowl or inv.porridge):
                if inv.porridge and not inv.bowl:
                    itemName = "bowl of porridge"
                    itemsName = "bowls of porridge"
                    roomItem = roomCurrent.porridge
                    invItem = inv.porridge
                else:
                    itemName = "bowl"
                    itemsName = "bowls"
                    roomItem = roomCurrent.bowl
                    invItem = inv.bowl
                if option in (itemName,"bowl"):
                    drop_count = 1
                elif option in (itemsName,"bowls"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
            # Lantern
            elif "lantern" in option and inv.lantern:
                itemName = "lantern"
                itemsName = "lanterns"
                roomItem = roomCurrent.lantern
                invItem = inv.lantern
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
            # Vial of lantern oil
            elif ("oil" in option) or ("vial" in option) and inv.oil:
                itemName = "vial of lantern oil"
                itemsName = "vials of lantern oil"
                roomItem = roomCurrent.oil
                invItem = inv.oil
                if option in (itemName,"vial"):
                    drop_count = 1
                elif option in (itemsName,"oil","vials","lantern oil"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("vial"):
                    drop_count = option[:len(option) - len("vial") - 1]
                elif option.endswith("vials"):
                    drop_count = option[:len(option) - len("vials") - 1]
                elif option.endswith("lantern oil"):
                    drop_count = option[:len(option) - len("lantern oil") - 1]
                elif option.endswith("oil"):
                    drop_count = option[:len(option) - len("oil") - 1]
            # Hardtack biscuit
            elif ("biscuit" in option) or ("hardtack" in option) and inv.biscuit:
                itemName = "hardtack biscuit"
                itemsName = "hardtack biscuit"
                roomItem = roomCurrent.biscuit
                invItem = inv.biscuit
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("biscuit"):
                    drop_count = option[:len(option) - len("biscuit") - 1]
                elif option.endswith("biscuits"):
                    drop_count = option[:len(option) - len("biscuits") - 1]
                elif option.endswith("hardtack"):
                    drop_count = option[:len(option) - len("hardtack") - 1]
            # Coil of hook
            elif "hook" in option and inv.hook:
                itemName = "grappling hook"
                itemsName = "grappling hooks"
                roomItem = roomCurrent.hook
                invItem = inv.hook
                if option in (itemName,"hook"):
                    drop_count = 1
                elif option in (itemsName,"hooks"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("hook"):
                    drop_count = option[:len(option) - len("hook") - 1]
                elif option.endswith("hooks"):
                    drop_count = option[:len(option) - len("hooks") - 1]
            # The staff of Garrotxa
            elif ("staff" in option) or ("staves" in option) and inv.staff:
                itemName = "staff of Garrotxa"
                itemsName = "staves of Garrotxa"
                roomItem = roomCurrent.staff
                invItem = inv.staff
                if option in (itemName.lower(),"staff"):
                    drop_count = 1
                elif option in (itemsName.lower(),"staves"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName.lower()) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName.lower()) - 1]
                elif option.endswith("staff"):
                    drop_count = option[:len(option) - len("staff") - 1]
                elif option.endswith("staves"):
                    drop_count = option[:len(option) - len("staves") - 1]
            # Raffle ticket
            elif "ticket" in option and inv.ticket:
                itemName = "raffle ticket"
                itemsName = "raffle tickets"
                roomItem = roomCurrent.ticket
                invItem = inv.ticket
                if option in (itemName,"ticket"):
                    drop_count = 1
                elif option in (itemsName,"tickets"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("ticket"):
                    drop_count = option[:len(option) - len("ticket") - 1]
                elif option.endswith("tickets"):
                    drop_count = option[:len(option) - len("tickets") - 1]
            # Piece of coal
            elif "coal" in option and inv.coal:
                itemName = "piece of coal"
                itemsName = "pieces of coal"
                roomItem = roomCurrent.coal
                invItem = inv.coal
                if option == itemName:
                    drop_count = 1
                elif option in (itemsName,"coal"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("coal"):
                    drop_count = option[:len(option) - len("coal") - 1]
            # Potato
            elif "potato" in option and inv.potato:
                itemName = "rotten potato"
                itemsName = "rotten potatoes"
                roomItem = roomCurrent.potato
                invItem = inv.potato
                if option in (itemName,"potato"):
                    drop_count = 1
                elif option in (itemsName,"potatoes"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("potato"):
                    drop_count = option[:len(option) - len("potato") - 1]
                elif option.endswith("potatoes"):
                    drop_count = option[:len(option) - len("potatoes") - 1]
            # Bandage
            elif "bandage" in option and inv.bandage:
                itemName = "bandage"
                itemsName = "bandages"
                roomItem = roomCurrent.bandage
                invItem = inv.bandage
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
            # Journal
            elif "journal" in option and inv.journal:
                itemName = "journal"
                itemsName = "journals"
                roomItem = roomCurrent.journal
                invItem = inv.journal
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
            # Mysterious book
            elif "book" in option and inv.book:
                itemName = "mysterious book"
                itemsName = "mysterious books"
                roomItem = roomCurrent.book
                invItem = roomCurrent.book
                if option in (itemName,"book"):
                    drop_count = 1
                elif option in (itemsName,"books"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("book"):
                    drop_count = option[:len(option) - len("book") - 1]
                elif option.endswith("books"):
                    drop_count = option[:len(option) - len("books") - 1]
             # Cheeses
            elif "brie" in option and inv.brie:
                itemName = "slice of brie cheese"
                itemsName = "slices of brie cheese"
                roomItem = roomCurrent.brie
                invItem = inv.brie
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("brie"):
                    drop_count = option[:len(option) - len("brie") - 1]
                elif option.endswith("brie cheese"):
                    drop_count = option[:len(option) - len("brie cheese") - 1]
            elif "munster" in option and inv.munster:
                itemName = "slice of munster cheese"
                itemsName = "slices of munster cheese"
                roomItem = roomCurrent.munster
                invItem = inv.munster
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("munster"):
                    drop_count = option[:len(option) - len("munster") - 1]
                elif option.endswith("munster cheese"):
                    drop_count = option[:len(option) - len("munster cheese") - 1]
            elif "stilton" in option and inv.brie:
                itemName = "slice of stilton cheese"
                itemsName = "slices of stilton cheese"
                roomItem = roomCurrent.stilton
                invItem = inv.stilton
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("stilton"):
                    drop_count = option[:len(option) - len("stilton") - 1]
                elif option.endswith("stilton cheese"):
                    drop_count = option[:len(option) - len("stilton cheese") - 1]
            elif "swiss" in option and inv.brie:
                itemName = "slice of swiss cheese"
                itemsName = "slices of swiss cheese"
                roomItem = roomCurrent.swiss
                invItem = inv.swiss
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("swiss"):
                    drop_count = option[:len(option) - len("swiss") - 1]
                elif option.endswith("swiss cheese"):
                    drop_count = option[:len(option) - len("swiss cheese") - 1]
            elif "wensleydale" in option and inv.brie:
                itemName = "slice of wensleydale cheese"
                itemsName = "slices of wensleydale cheese"
                roomItem = roomCurrent.wensleydale
                invItem = inv.wensleydale
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("wensleydale"):
                    drop_count = option[:len(option) - len("wensleydale") - 1]
                elif option.endswith("wensleydale cheese"):
                    drop_count = option[:len(option) - len("wensleydale cheese") - 1]
            # Potion
            elif "potion" in option and inv.potion:
                itemName = "potion of rejuvination"
                itemsName = "potions of rejuvination"
                roomItem = roomCurrent.potion
                invItem = inv.potion
                if option in (itemName,"potion"):
                    drop_count = 1
                elif option in (itemsName,"potions"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("potion"):
                    drop_count = option[:len(option) - len("potion") - 1]
                elif option.endswith("potions"):
                    drop_count = option[:len(option) - len("potions") - 1]
            # Flask
            elif "flask" in option and inv.flask:
                itemName = "flask"
                itemsName = "flasks"
                roomItem = roomCurrent.flask
                invItem = inv.flask
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
            # Dragonstone
            elif "stone" in option and inv.stone:
                itemName = "dragonstone"
                itemsName = "dragonstones"
                roomItem = roomCurrent.stone
                invItem = inv.stone
                if option in (itemName,"stone"):
                    drop_count = 1
                elif option in (itemsName,"stones"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("stone"):
                    drop_count = option[:len(option) - len("stone") - 1]
                elif option.endswith("stones"):
                    drop_count = option[:len(option) - len("stones") - 1]
            # Wooden bird
            elif "bird" in option and inv.bird:
                itemName = "wooden bird"
                itemsName = "wooden birds"
                roomItem = roomCurrent.bird
                invItem = inv.bird
                if option in (itemName,"bird"):
                    drop_count = 1
                elif option in (itemsName,"birds"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("bird"):
                    drop_count = option[:len(option) - len("bird") - 1]
                elif option.endswith("birds"):
                    drop_count = option[:len(option) - len("birds") - 1]
            # Note
            elif "note" in option and inv.note:
                itemName = "note"
                itemsName = "notes"
                roomItem = roomCurrent.note
                invItem = inv.note
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
            # Memo
            elif "memo" in option and inv.memo:
                itemName = "memo"
                itemsName = "memos"
                roomItem = roomCurrent.memo
                invItem = inv.note
                if option == itemName:
                    drop_count = 1
                elif option == itemsName:
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
            # Chicken pot pie
            elif "pie" in option and inv.pie:
                itemName = "chicken pot pie"
                itemsName = "chicken pot pies"
                roomItem = roomCurrent.pie
                invItem = inv.pie
                if option in (itemName,"chicken pie","pot pie","pie"):
                    drop_count = 1
                elif option in (itemsName,"chicken pies","pot pies","pies"):
                    drop_count = invItem
                elif option.endswith(itemName):
                    drop_count = option[:len(option) - len(itemName) - 1]
                elif option.endswith(itemsName):
                    drop_count = option[:len(option) - len(itemsName) - 1]
                elif option.endswith("pot pie"):
                    drop_count = option[:len(option) - len("pot pie") - 1]
                elif option.endswith("chicken pie"):
                    drop_count = option[:len(option) - len("chicken pie") - 1]
                elif option.endswith("pie"):
                    drop_count = option[:len(option) - len("pie") - 1]
                elif option.endswith("pot pies"):
                    drop_count = option[:len(option) - len("pot pies") - 1]
                elif option.endswith("chicken pies"):
                    drop_count = option[:len(option) - len("chicken pies") - 1]
                elif option.endswith("pies"):
                    drop_count = option[:len(option) - len("pies") - 1]
            elif option == "":
                print("Drop what?")
                notTurn = True
            else:
                if option in ("all","everything"):
                    print("You have nothing to drop.")
                else:
                    print("You have no %s to drop." % option)
                notTurn = True
            if itemName:
                dropCountOkay = False
                try:
                    drop_count = float(drop_count)
                    if drop_count % 1 == 0 and drop_count > 0 and drop_count <= invItem:
                         drop_count = int(drop_count)
                         dropCountOkay = True
                    else:
                        print("You cannot drop",drop_count,"%s." % itemsName)
                        notTurn = True
                except ValueError:
                    if drop_count in ("all","every"):
                        drop_count = invItem
                        dropCountOkay = True
                    elif drop_count in ("a","the","only 1","only one"):
                        drop_count = 1
                        dropCountOkay = True
                    else:
                        print("You cannot drop \"" + drop_count + "\" %s." % itemsName)
                        notTurn = True
                if dropCountOkay and not itemName == "everything":
                    if drop_count > 1:
                        print("You drop %s %s." % (drop_count,itemsName))
                    elif invItem > 1:
                        print("You drop a %s." % itemName)
                    else:
                        print("You drop the %s." % itemName)
                    roomItem += drop_count
                    invItem -= drop_count
                    if itemName == "gold":
                        roomCurrent.gold = roomItem
                        inv.gold = invItem
                    elif itemName == "letter":
                        roomCurrent.letter = roomItem
                        inv.letter = invItem
                    elif itemName == "key":
                        roomCurrent.key = roomItem
                        inv.key = invItem
                    elif itemName == "key of Ahm'domosh":
                        roomCurrent.keySkeleton = roomItem
                        inv.keySkeleton = invItem
                    elif itemName == "pickaxe":
                        roomCurrent.pickaxe = roomItem
                        inv.pickaxe = invItem
                    elif itemName == "shrubbery":
                        roomCurrent.shrubbery = roomItem
                        inv.shrubbery = invItem
                    elif itemName == "funnel cake":
                        roomCurrent.funnelCake = roomItem
                        inv.funnelCake = invItem
                    elif itemName == "half-eaten funnel cake":
                        roomCurrent.halfFunnelCake = roomItem
                        inv.halfFunnelCake = invItem
                    elif itemName == "lucky rabbit foot":
                        roomCurrent.foot = roomItem
                        inv.foot = invItem
                    elif itemName == "bowl of porridge":
                        roomCurrent.porridge = roomItem
                        inv.porridge = invItem
                    elif itemName == "bowl":
                        roomCurrent.bowl = roomItem
                        inv.bowl = invItem
                    elif itemName == "lantern":
                        roomCurrent.lantern = roomItem
                        inv.lantern = invItem
                    elif itemName == "vial of lantern oil":
                        roomCurrent.oil = roomItem
                        inv.oil = invItem
                    elif itemName == "chicken pot pie":
                        roomCurrent.pie = roomItem
                        inv.pie = invItem
                    elif itemName == "hardtack biscuit":
                        roomCurrent.biscuit = roomItem
                        inv.biscuit = invItem
                    elif itemName == "grappling hook":
                        roomCurrent.hook = roomItem
                        inv.hook = invItem
                    elif itemName == "staff of Garrotxa":
                        roomCurrent.staff = roomItem
                        inv.staff = invItem
                    elif itemName == "raffle ticket":
                        roomCurrent.ticket = roomItem
                        inv.ticket = invItem
                    elif itemName == "piece of coal":
                        roomCurrent.coal = roomItem
                        inv.coal = invItem
                    elif itemName == "rotten potato":
                        roomCurrent.potato = roomItem
                        inv.potato = invItem
                    elif itemName == "bandage":
                        roomCurrent.bandage = roomItem
                        inv.bandage = invItem
                    elif itemName == "journal":
                        roomCurrent.journal = roomItem
                        inv.journal = invItem
                    elif itemName == "mysterious book":
                        roomCurrent.book = roomItem
                        inv.book = invItem
                    elif itemName == "slice of brie cheese":
                        roomCurrent.brie = roomItem
                        inv.brie = invItem
                    elif itemName == "slice of munster cheese":
                        roomCurrent.munster = roomItem
                        inv.munster = invItem
                    elif itemName == "slice of stilton cheese":
                        roomCurrent.stilton = roomItem
                        inv.stilton = invItem
                    elif itemName == "slice of swiss cheese":
                        roomCurrent.swiss = roomItem
                        inv.swiss = invItem
                    elif itemName == "slice of wensleydale cheese":
                        roomCurrent.wensleydale = roomItem
                        inv.wensleydale = invItem
                    elif itemName == "potion of rejuvination":
                        roomCurrent.potion = roomItem
                        inv.potion = invItem
                    elif itemName == "flask":
                        roomCurrent.flask = roomItem
                        inv.flask = invItem
                    elif itemName == "dragonstone":
                        roomCurrent.stone = roomItem
                        inv.stone = invItem
                    elif itemName == "wooden bird":
                        roomCurrent.bird = roomItem
                        inv.bird = invItem
                    elif itemName == "note":
                        roomCurrent.note = roomItem
                        inv.note = invItem
                    elif itemName == "memo":
                        roomCurrent.memo = roomItem
                        inv.memo = invItem
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
                        stat.lowerHealth()
                    else:
                        print("You try to kick yourself, however that would work, and quickly get frustrated. You end up kicking the back of one of your calves with your other foot, causing you to fall over and hit your head.")
                        stat.lowerHealth()
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
                            print("You %s the troll. He gets angry and punches you back." % hurt)
                            stat.lowerHealth()
                    else:
                        print("You can't %s that." % hurt)
                elif roomID == "roomTempleInside":
                    if option in ("ghostly figure","ghost","figure"):
                        if roomCurrent.characterDead:
                            print("There's no %s here to %s." % (option,hurt))
                        else:
                            print("You %s the ghostly figure, going right through it. \"Do not think you can defeat me with mere physical attacks.\" It zaps you, cutting open a wound in your chest." % hurt)
                            stat.lowerHealth()
                else:
                    print("You cannot",hurt,"that.")
                    notTurn = True
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
                    playerName = option2
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
                        if option in roomCurrent.counterAns_1:
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
                                stat.lowerHealth()
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
                        demonWords.learn(spellLearn)
                    elif option == "ozh vo'ses sa":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I unlock this.\n    Spell - Opens any lock.")
                        valid = True
                        demonWords.learn(spellUnlock)
                    elif option == "izh vo'poz":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    You have no power.\n    Spell - Persuades those blocking the way.")
                        valid = True
                        demonWords.learn(spellPersuade)
                    elif option == "ozh thok alatho":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I move forward\n    Spell - Crosses large gaps.")
                        valid = True
                        demonWords.learn(spellJump)
                    elif option == "ozh groth sol":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I open the light.\n    Spell - Creates a glowing ball that illuminates your surroundings.")
                        valid = True
                        demonWords.learn(spellLight)
                    elif option == "ozh vo'irush":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I am without illness.\n    Spell - Mends all wounds and illnesses.")
                        valid = True
                        demonWords.learn(spellHeal)
                    elif option == "ozh gluth nith":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I consume the Earth.\n    Spell - Satifsies all hunger.")
                        valid = True
                        demonWords.learn(spellFeed)
                    elif option == "ozh gluth izh sol":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    I consume your soul.\n    Spell - Kills your enemies.")
                        valid = True
                        demonWords.learn(spellKill)
                    elif option == "ozh sol fek":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    My life ends.\n    Spell - Kills self.")
                        valid = True
                        demonWords.learn(spellKillself)
                    # roomBarn letter
                    elif option == "eyik vo'hollom":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Behold oblivion.\n    Spell - Opens obsidian hemispheres.")
                        valid = True
                        demonWords.learn(spellOblivion)
                    # Not spells
                    # roomShrineNorth.firstTime
                    elif option == "omoz gloth izh":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Darkness welcomes you.")
                        valid = True
                        demonWords.learn(wordDarkness)
                    # roomHouseOffice
                    # Vesh'kathal says after Eden dies in dialogue (either you kill or she makes you kill him)
                    elif option == "ozkavosh icha domosh sa nith":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Demon-kind shall reign upon this land.")
                        valid = True
                        demonWords.learn(wordReign)
                    elif option == "izh icha vo'fek ozh domosh":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    You will not stop my reign.")
                        valid = True
                        wordStop = 2
                    elif option == "ahm'fol":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Servant of Vesh'arkosh")
                        valid = True
                        demonWords.learn(wordServant)
                    elif option == "sof izh":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Curse you.")
                        valid = True
                        demonWords.learn(wordCurse)
                    # roomBookMirror
                    # Hints the puzzle solution
                    elif option == "izh tal et ozh icha rek'tal":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Talk and I will mirror.")
                        valid = True
                        demonWords.learn(wordMirror)
                    elif option == "ahm'domosh":
                        print("Information enters your thoughts as the meaning of the words become clear.")
                        print("    Highest dominion.")
                        valid = True
                        demonWords.learn(wordDominion)
                    if valid:
                        demonWords.learn(spellLearn)
                # Unlock (I unhide this)
                # roomLake after saving stranger
                elif option == "ozh vo'ses sa":
                    if roomID in ("roomJailCell","roomMountEntrance","roomHouseGate","roomHouseEntrance") and (roomCurrent.northBlocked or roomCurrent.eastBlocked or roomCurrent.southBlocked or roomCurrent.westBlocked) or (roomID in ("roomLake","roomCave__3_lllm_treasure_crevasse") and not roomCurrent.counter_1):
                        print("You feel a surge of energy emit from the utterance of your words into the lock, unlocking it.")
                        if roomID in ("roomLake","roomCave__3_lllm_treasure_crevasse"):
                            roomCurrent.counter_1 = 1
                            valid = True
                            demonWords.learn(spellUnlock)
                        else:
                            valid = True
                            if roomCurrent.northBlocked and roomCurrent.north:
                                print("You can now go North.")
                                roomCurrent.northBlocked = False
                            if roomCurrent.eastBlocked and roomCurrent.east:
                                print("You can now go East.")
                                roomCurrent.eastBlocked = False
                            if roomCurrent.westBlocked and roomCurrent.west:
                                print("You can now go West.")
                                roomCurrent.southBlocked = False
                            if roomCurrent.southBlocked and roomCurrent.south:
                                print("You can now go South.")
                                roomCurrent.westBlocked = False
                            if roomID == "roomJailCell":
                                if not roomCurrent.counter_1 and not playerName:
                                    roomJailCorridor.letter += 1
                                    roomCurrent.counterAns_1 = 1
                                if not playerName:
                                    playerName = "the hero of Kashkaval"
                                inv.letterRead = "It reads:\n\nTo " + playerName + ",\n\nA certain Eden Von Roquefort has set up residence NORTH of MOUNT MAGNA. While he purports to be a lowly cheese mage, reliable sources claim him to be the demon lord, Vesh'kathal the Deceiver, a shapeshifter infamous of manipulating the minds and bending the wills of others. Legend tells of a saviour, deemed the Monterey Messiah, who will save all of Kashkaval from his wickedness. It has be brought to my attention that you are that saviour that the legends speak of. While I have very important matters to attend to, the best I can do is help instruct you in how to defeat this demon lord:\n\nFIRST, you must acquire the staff from the Garrotxian temple NORTHEAST of this town, for it is the only weapon capable of defeating such a powerful demon.\n\nNEXT, once you have the staff, go NORTH through the MINES of MOUNT MAGNA and find him at his house on the other end.\n\nFINALLY, kill Roquefort and Kashkaval will be saved from his wrath.\n\nI know this is probably a lot to digest at once, but you are our only hope. I fear in your attempt to complete this task, Vesh'kathal will attempt to thwart you. He may attempt to contact and manipulate you, or have his minions work to stop you. Whatever he does, you must persevere.\n\nMay you be blessed,\n\nThe last prophet of Garrotxa"
                # Oblivion - Open hemispheres
                # roomBarn letter
                elif option == "eyik vo'hollom" and roomID in ("roomForest","roomField") and roomCurrent.eastBlocked:
                    roomForest.eastBlocked = False
                    roomField.eastBlocked = False
                    print("You hear strange whispers in every direction around you. Suddenly, the hemisphere begins to hum and an opening appears, leading inside.")
                    print("You can no go East.")
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
                            demonWords.learn(spellPersuade)
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
                            demonWords.learn(spellPersuade)
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
                    demonWords.learn(spellLight)
                # Heal (I without illness)
                # Obtained at House/Never
                elif option == "ozh vo'irush":
                    if not stat.health == HEALTH_MAX:
                        stat.health = HEALTH_MAX
                        print("You feel a surge of energy emit from the utterance of your words into your wounds.")
                        valid = True
                        demonWords.learn(spellHeal)
                # Feed (I eat earth)
                # Obtained at House/Never
                elif option == "ozh gluth nith":
                    stat.hunger = HUNGER_MAX
                    print("You feel a surge of energy emit from the utterance of your words into your stomach.")
                    valid = True
                    demonWords.learn(spellFeed)
                # Kill (I eat your soul)
                # From losing the game at the end (implicit)
                elif option == "ozh gluth izh sol":
                    if not roomCurrent.characterDead or roomID.startswith("roomLair") or (roomID.startswith("roomCave") and creatureChaseCounter in [1,2]) or (roomID.startswith("roomJail") and jailGuardCounter in [1,2]):
                        roomCurrent.characterDead = True
                        print("You feel a surge of energy emit from the utterance of your words, consuming the soul of ", end = "")
                        valid = True
                        demonWords.learn(spellKill)
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
                            print("the black knight. He shouts, \"IZH VO'POZ!\" before his body vaporizes into nothing.")
                            demonWords.add(spellPersuade)
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
                    demonWords.learn(spellKillself)
                if spell and not valid:
                    print("You feel a strange sense of energy flow throughout your body although nothing else happens.")

                # Responses
                if roomID == "roomRoadCorner" and not roomCurrent.characterDead and roomCurrent.counter_1 and option not in ("ozh gluth izh sol","izh vo'poz"):
                    print("The black knight stands silently, ignoring your words.")
                elif roomID == "roomBookMirror" and not roomCurrent.characterDead:
                    print("\nThe gargoyle says, \"%s\" in return." % reverse_cap(option))
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
        # Filler commands
        # Do nothing important
        # Lie down
        elif option in ("save","save game","save the game"):
            print("HAHAHAHA. YOU THOUGHT THIS GAME WAS ACTUALLY GOOD ENOUGH TO HAVE A SAVE FEATURE?")
            notTurn = True
        elif option in ("load","load game","load saved game"):
            print("No saved game to load.")
            notTurn = True
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
            if option.startswith("buy"):
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
                                    if invItem:
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
                                            print("\nJust as you buy the ticket, someone walks around behind the stage and notices magnets lined across the back of the wheel. \"HEY! This raffle's rigged! Throw this FRAUD in jail!\" Guards enter the tent and arrest the raffle host.\n\nA spokesperson walks in and announces to the crowd, \"I apologize on behalf of the carnival for what has happened here. If you would like to be compensated for your tickets, it will be arranged just outside in the fair grounds. Now, everyone out. This man will be taken to the town jail for his crime.\" The crowd of people storm out, eager to give their tickets back.") ## continue make more concise and format well
                                            # print("\"Settle down everyone. We've sold our last ticket. It's time to spin the wonderful wheel of mystery!\" The man takes hold of the large wheel at the centre of the stage and with all his weight, pushes one end down, causing it to turn. The crowd cheers as the numbers along the edge pass the ticker at the top of the wheel. It finally stops at 42. \"I have the ticket! I have it!\" shouts a bearded man in the crowd. Just as he approaches the stage, someone grabs hold of him. \"Wait a minute, I've seen you before.\" His beard is torn off, revealing him in disguise. \"You won yesterday's raffle. In fact, you've been winning the raffle ever since this bloody carnival began. This raffle is rigged! Get 'em!\" Guards outside the tent notice the commotion and enter, grabbing hold of the raffle host and his accomplice to arrest them. \n\nA spokesperson enters the tent. She announces to the crowd, \"I apologize on behalf of the carnival for what has happened here. If you would like to be compensated for your tickets, it will be arranged outside in the fair grounds. Now, everyone out. These two will be taken to the town jail for their crimes.\" The crowd begrungenly walks out.")
                                    else:
                                        print("You have no %s." % itemsName)
                                else:
                                    if buy_count > 1:
                                        print("You do not have enough gold to buy",buy_count,"%s." % itemsName)
                                        notTurn = True
                                    else:
                                        print("You do not have enough gold to buy a %s." % itemName)
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
                        pass
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
                                        if s_bet in ("all","every"):
                                            betGoldOkay = True
                                            s_bet = inv.gold
                                        elif s_bet in ("none","no","zero"):
                                            betGoldOkay = True
                                            s_bet = 0
                                        else:
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
                                    print("\nYou have %s gold." % inv.gold)
                                    roomCarnivalShellGame.betMade = True
                                else:
                                    print("You do not have enough gold to bet",s_bet,"gold.")
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
                                        print("\nYou have",inv.gold,"gold.")
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
                giveBird = False
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
                    # Wooden Bird
                    elif option in ("bird","wooden bird","birds","wooden birds"):
                        give_count = 1
                        giveBird = True
                    elif option.endswith("wooden bird"):
                        give_count = option[:len(option) - len("wooden bird") - 1]
                        giveBird = True
                    elif option.endswith("wooden birds"):
                        give_count = option[:len(option) - len("wooden birds") - 1]
                        giveBird = True
                    elif option.endswith("bird"):
                        give_count = option[:len(option) - len("bird") - 1]
                        giveBird = True
                    elif option.endswith("birds"):
                        give_count = option[:len(option) - len("birds") - 1]
                        giveBird = True
                    else:
                        print("You cannot give that.")
                        notTurn = True
                    if (giveGold or giveFunnelCake or giveHalfFunnelCake or giveshrubbery or giveTicket or giveStaff or giveBird):
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
                        elif giveBird:
                            itemName = "wooden bird"
                            itemsName = "wooden birds"
                            invItem = inv.bird
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
                            if giveBird:
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
                                    elif giveBird:
                                        inv.bird -= give_count
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
                                            invSpokesperson.ticket += 1
                                            print("\nYou have %s gold." % inv.gold)
                                        elif roomID == "roomHouseOffice":
                                            roomCurrent.counter_1 = 1
                                            roomCurrent.isGive = False
                                            roomCurrent.characterDead = True # spawns vesh, which kills Eden
                                            roomCurrent.counter_2 += 1
                                        elif roomID == "roomLake":
                                            print("\"Thank you!\" He gives a chicken pot pie.\n\n\"One more thing. You see that stone tablet? Whatever is written there is in language of demons, or the TONGUE OF THE OZKAVOSH. Some phrases are merely words. However, some are SPELLS and can grant you great powers when SPOKEN.\n\nOne such example is, \'OZH ENSH,\' which when spoken as a prefix followed by other words in Ozkavosh, forms a spell that will translate them for you.\nOnce you know what that spell is, you can SAY that spell on its own to cast it.\nYou can EXAMINE your SPELLS if you ever forget what they are.\n\nTry to figure out what written on that TABLET and see if you can open my LOCKBOX. You can take whatever is inside.\"")
                                            inv.pie += LAKE_PIE_REWARD
                                            roomCurrent.isGive = False
                                            demonWords.learn(spellLearn)
                                    if roomBridge.eastBlocked and invTroll.gold < TROLL_GOAL and roomID == "roomBridge":
                                        print('"Ugg only needs %s gold left until Ugg has enough to buy all the funnel cakes Ugg wants."' % (TROLL_GOAL - invTroll.gold))
                                # If not enough items in inventory to give
                                else:
                                    if give_count > 1:
                                        print("You do not have enough to give %s %s to %s." % (give_count,itemsName,receiverName))
                                        notTurn = True
                                    else:
                                        print("You do not have any %s to give to %s." % (itemsName,receiverName))
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
                            print("You can now go North.")
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
                                if sell_count in ("all","every"):
                                    sell_count = invItem
                                    sellItemCountOkay = True
                                elif sell_count in ("a","the","only one","only 1"):
                                    sell_count = 1
                                    sellItemCountOkay = True
                                else:
                                    print("You cannot sell \"" + sell_count + "\" %s." % itemsName)
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
                                        print("You do not have %s %s." % (sell_count,itemsName))
                                        notTurn = True
                                    else:
                                        print("You do not have have %s %s." % (sell_count,itemName))
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
            elif roomID == "roomForest":
                roomCurrent = roomForest
            elif roomID == "roomShrineSouth":
                roomCurrent = roomShrineSouth
                demonWords.add(wordDarkness)
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
            elif roomID == "roomHouseGate":
                roomCurrent = roomHouseGate
            elif roomID == "roomField":
                roomCurrent = roomField
            elif roomID == "roomShrineNorth":
                roomCurrent = roomShrineNorth
                demonWords.add(wordDarkness)
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
                demonWords.add(wordDominion)

            else:
                print("Uh oh, you broke the game.",roomID,"does not exist. Room not changed.")
            if direction:
                print("You go %s." % direction)
                justEntered = True
            # Rubble falling
            if roomID.startswith("roomCave_") and not roomID.startswith("roomCave__") and random.randint(1,100) <= RUBBLE_FALL_CHANCE:
                print("The mountain shakes and some rubble falls and hits you.")
                stat.lowerHealth()


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
                print("\nYou hear a loud crack of thunder and a mystical portal opens in the nearby corridor. A woman veiled in white robes jumps through, entering the room. She swiftly runs through the corridor, scanning each cell she passes. Suddenly, she stops at your cell, pauses and draws nearer. \"Tell me your name, stranger.\" she exclaims. \"You may be who I'm looking for.\" She looks worried.")
                askName = True
                roomCurrent.counter_1 += 1
            elif turnCounter >= 2 and askName:
                if playerName:
                    if playerName.lower() == "ozh gluth izh sol":
                        print("\n\"Ha! You think you can kill me with the tongue of the Ozkavosh? You are a fool!\" The woman transforms in a scaly demon, sprouting wings and sharp claws. \"No mortal shall defeat Vesh'kathal the Deceiver. OZH GLUTH IZH SOL!\"\n\nYou are overwhelmed with pain and agony as you fall to your knees.\n")
                        endGame = True
                        playerName = False
                        askName = False
                    elif playerName.lower() in ("ozh ensh","ozh vo'ses sa","izh vo'poz","ozh sol fek","ozh thok alatho","ozh groth sol","eyik vo'hollom","ozh vo'irush","ozh gluth nith","omoz gloth nith","izh tal el ozh icha rek'tal","ahm'domosh","ozhkavosh icha domosh sa nith","izh icha vo'fek ozh domosh","ahm'fol","sof izh"):
                        print("\n\"" + playerName + "? You dare speak the tongue of the Ozhkavosh to me? She zaps you with a bolt of electricity. \"I'll let you live your last few moments bleeding in your cell. Farewell.\" She leaves through the portal from whence she came. The portal snaps shut and disappears.")
                        endGame = True
                        playerName = False
                        askName = False
                    elif playerName.lower() in ("eden von roquefort","vesh'kathal","vesh'raheen","vesh'arkosh","garrotxa","rodney williams"):
                        print("\n\"" + playerName + "? I do not appreciate being lied to.\" She zaps you with a bolt of electricity. \"I'll let you live your last few moments bleeding in your cell. Farewell.\" She leaves through the portal from whence she came. The portal snaps shut and disappears.")
                        endGame = True
                        playerName = False
                        askName = False
                    else:
                        print("\n\"" + playerName + "? Finally, my search is over. This letter is for you. READ it and you will understand the urgency of the situation. I unforunately have no time to explain as I have much yet to do.\" She nods, tosses a key and a letter through your cell window, and quickly leaves through the portal from whence she came. The portal snaps shut and disappears.")
                        roomCurrent.letter += 1
                        roomCurrent.key += 1
                        roomCurrent.itemsPresent()
                        inv.letterRead = "It reads:\n\nTo " + playerName + ",\n\nA certain Eden Von Roquefort has set up residence NORTH of MOUNT MAGNA. While he purports to be a lowly cheese mage, reliable sources claim him to be the demon lord, Vesh'kathal the Deceiver, a shapeshifter infamous of manipulating the minds and bending the wills of others. Legend tells of a saviour, deemed the Monterey Messiah, who will save all of Kashkaval from his wickedness. It has be brought to my attention that you are that saviour that the legends speak of. While I have very important matters to attend to, the best I can do is help instruct you in how to defeat this demon lord:\n\nFIRST, you must acquire the staff from the Garrotxian temple NORTHEAST of this town, for it is the only weapon capable of defeating such a powerful demon.\n\nNEXT, once you have the staff, go NORTH through the MINES of MOUNT MAGNA and find him at his house on the other end.\n\nFINALLY, kill Roquefort and Kashkaval will be saved from his wrath.\n\nI know this is probably a lot to digest at once, but you are our only hope. I fear in your attempt to complete this task, Vesh'kathal will attempt to thwart you. He may attempt to contact and manipulate you, or have his minions work to stop you. Whatever he does, you must persevere.\n\nMay you be blessed,\n\nThe last prophet of Garrotxa"
                        askName = False
                if not playerName and askName:
                    if roomCurrent.counter_1 == 2:
                        print('\nThe stranger impatiently waits for your response. "I beg of you. Tell me your name. This is of utmost importance."')
                    elif roomCurrent.counter_1 == 3:
                        print("\nThe stranger quickly grows angry as you continue to ignore her.")
                    else:
                        print("\n\"Why am I wasting my time with a mute rotting in a jail cell?\" She zaps you with a bolt of electricity and walks back through the portal, which snaps shut and disappears.")
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
        # Creature stops chase at crevasse if visible or outside
        elif roomID == "roomMountEntrance" or (roomID.endswith("crevasse") and roomID_Last.endswith("crevasse") and roomID != roomID_Last):
            if roomID == "roomMountEntrance":
                creatureRoam = False
                creatureChase = False
                if creatureChaseCounter <= 2:
                    print("The creature is blinded by the outside light and stops its chase, returning back to the cavern.")
                creatureRoamCounter = random.randint(CREATURE_ROAM_MIN,CREATURE_ROAM_MAX)
                creatureChaseCounter = random.randint(CREATURE_CHASE_MIN,CREATURE_CHASE_MAX)
            else:
                if creatureChaseCounter <= 2:
                    creatureRoam = False
                    creatureChase = False
                    print("The creature stops at the crevasse, unable to cross. Frustrated, it crawls away.")
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
            roomCave_9_mr_crevasse.northBlocked = True
        # Enter darkness
        elif roomID == "roomDarkness":
            creatureRoam = False
            creatureChase = False
            stat.hunger = HUNGER_DARKNESS
            stat.health = HEALTH_MAX - 1
            stat.shield = 0
            oilCounter = 0
            lightCounter = 0
            creatureRoamCounter = random.randint(CREATURE_ROAM_MIN,CREATURE_ROAM_MAX)
            creatureChaseCounter = random.randint(CREATURE_CHASE_MIN,CREATURE_CHASE_MAX)
            if inv.pickaxe:
                roomLairMid.counter_3 = 1
            if inv.staff:
                roomLairMid.counter_2 = 1
            inv = Inventory(note_1 = vaultAnswer_1, note_2 = vaultAnswer_2, note_3 = vaultAnswer_3, note_4 = vaultAnswer_4)
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

        if roomID == "roomHouseEntrance" and roomID_Last == "roomHouseGate" and not roomCurrent.firstTime:
            silenced = True
            print("You feel the strange force take over you again.")
        elif roomID == "roomHouseGate" and roomID_Last == "roomHouseEntrance":
            silenced = False
            print("You feel the strange force leave your body.")
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
        direction = False
        if not notTurn or endGame:
            # After changeRoom description
            turnCounter += 1
            turnCounter_total += 1
            # Darkness loop
            if roomID == "roomDarkness":
                darknessCounter = DARKNESS_DURATION
                while darknessCounter:
                    option = input("\n\n\n\n> ").lower()
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
                    print("\nThe creature entirely breaks free from the rocks, which stops to release an ear-piercing scream. It erupts into full sprint towards you.")
                elif creatureLairChaseCounter == 1:
                    print("\nThe creature extends its arms towards you as it runs, continuing it's chaotic screeching in full force. It claws your arm.")
                    stat.lowerHealth()
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
                    stat.lowerHealth()
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
                stat.lowerHunger()
                stat.lowerShield()
                if stat.hunger <= 0:
                    endGame = True
                elif stat.hunger <= HUNGER_WARNING:
                    print()
                    stat.examineHunger()
                # Health
                if stat.health in range(1,stat.healthmax):
                    stat.lowerHealth()
                    print()
                    stat.examineHealth()
                if stat.health <= 0:
                    endGame = True
                # Oil
                if oilCounter:
                    inv.lanternDescription = "Perfect for lighting dark areas. It is currently LIT."
                    oilCounter -= 1
                else:
                    inv.lanternDescription = "Perfect for lighting dark areas. It is currently UNLIT."
                # Oil warning
                if inv.lantern > 0 or roomCurrent.lantern > 0:
                    if oilCounter in range(3,5):
                        print("\nYour lantern flickers.")
                    elif oilCounter == 2:
                        print("\nYour lantern is about to run out of oil.")
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
            elif roomID == "roomRoadMid" and roomCurrent.firstTime:
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
                print("\"One does not simply obtain the staff of Garrotxa, kill my knight and travel through the mines of Mount Magna on their own accord. You must be an assassin or something of the likes. But sent by who?\"")
            # roomBarn
            if roomID == "roomBarn" and roomCurrent.firstTime:
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"Who ARE you? You seem driven to kill me, yet for reasons still unbeknownst to me. This seems personal, yet I do not recall ever wronging you. This must be something more at stake here.\"")
            # roomHouseEntrance
            if roomID == "roomHouseEntrance" and roomCurrent.firstTime:
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"Enough. You speak the tongue of demon-kind in order to trespass into my property. Clearly you are a servant of the Ozhkavosh. I will do what I can in my power to stop you. Hold your tongue for you are silenced!\"")
                print("A strange force takes over you.")
                silenced = True
            # roomHouseFoyer
            if roomID == "roomHouseFoyer" and roomCurrent.firstTime:
                print("\nYou feel like someone is trying to enter your mind. A voice echoes in your head.")
                print("\"It seems your power is beyond that of my own. I don't know how you got through that door as I had enchanted it with my strongest incantations. If what I fear is true, then I will simply make one request of you: See me at my office. There is one final thing to discuss.")

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
                            print("\"If you will not give me the staff, then I will take it by force.\" Roquefort extends his arms forward and shoots a fireball at you.")
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
                            demonWords.add(wordStop)
                            demonWords.add(wordServant)
                            inv.staff += 1
                        else:
                            response = "\"You did it! Finally, Roquefort has fallen. Peace can be restored to all of Kashkaval. After all this, I think I ought to introduce myself.\" She transforms into a scaly demon, sprouting wings and sharp claws. \"OZKAVOSH ICHA DOMOSH SA NITH. I AM VESH'KATHAL, LAST OF THE AHM'FOL, DECEIVER OF MANY, AND NOW, THANKS YOU TO, RULER OF KASHKAVAL. WITH THE FINAL PROPHET OF GARROTXA DEAD, NO ONE WILL BE ABLE TO STOP MY REIGN. NOW, YOU TOO MUST DIE, AND THE STAFF OF GARROTXA SHALL BE DESTROYED.\""
                            demonWords.add(wordServant)
                        print("\nYou hear a loud crack of thunder and a mystical portal opens in the office. The woman veiled in white robes enters the room.",response)
                    # Player does not respond and dies
                    else:
                        print("Vesh'kathal lets out a bellowing roar. \"OZH GLUTH IZH SOL!\"\n\nYou are overwhelmed with pain and agony as you fall to your knees.")
                        demonWords.add(spellKill)
                        endGame = True
                # Player injures vesh after reveal and loses staff
                elif roomCurrent.counter_2 == 2:
                    roomCurrent.counter_4 += 1
                    if roomCurrent.counter_4 == 3:
                        print("Vesh'kathal recovers from her burns, and gets back up on her feet.")
                    elif roomCurrent.counter_4 > 3:
                        print("Vesh'kathal lets out a bellowing roar. \"OZH GLUTH IZH SOL!\"\n\nYou are overwhelmed with pain and agony as you fall to your knees.")
                        demonWords.add(spellKill)
                        endGame = True
                else:
                    # Vesh resists hit and tries to kill you again (differently?)
                    if inv.potato:
                        # somehow involve potato
                        print("\nShe grabs hold of you and pummels you against the wall. The potato you've been carrying falls out and hits her square in the face. \"GGAAAAAAAHHHH\" she screams in agony, \"A POTATO OF GARROTXA. MY GREATEST WEAKNESS. I THOUGHT I WAS SAFE WHEN I BURNT DOWN EVERY LAST CROP ON THAT CURSED FARM.\" Streams of light begin to errupt from Vesh'kathal's body, paralyzing her. She turns to stone shortly before she explodes, sending shards of rock flying across the room.\n\nYou take a deep breath and walk over to Roquefort's corpse. You take a closer look at his ragged clothing and and notice it's made out of cheesecloth. As soon as you lay hands on it, you feel a surge of energy throughout your body and hear a booming voice from the heavens.\n\n\"I have returned.\"\n")
                        demonWords.add(wordCurse)
                        win = True
                    else:
                        # Kills you
                        print("Vesh'kathal lets out a bellowing roar. \"OZH GLUTH IZH SOL!\"\n\nYou are overwhelmed with pain and agony as you fall to your knees.")
                        demonWords.add(spellKill)
                    endGame = True


        # Objective check
        # Main
        if roomID == "roomCourtyardSouth":
            stat.setObjectiveMain(1)
        if inv.staff:
            stat.setObjectiveMain(2)
        if roomID == "roomRoad2South":
            stat.setObjectiveMain(3)
        if roomID == "roomHouseFoyer":
            stat.setObjectiveMain(4)
        if roomHouseOffice.characterDead:
            stat.setObjectiveMain(5)
        # Secondary add
        if roomID == "roomCourtyardSouth":
            stat.addObjectiveSecondary(1)
        if roomID == "roomCourtyardNorth":
            stat.addObjectiveSecondary(2)
        if roomCarnivalWheelGame.characterDead:
            stat.addObjectiveSecondary(3)
        if roomID == "roomGate":
            stat.addObjectiveSecondary(4)
        if roomID == "roomBridge":
            stat.addObjectiveSecondary(5)
        if roomID == "roomAlchemist":
            stat.addObjectiveSecondary(6)
        if roomID == "roomLake":
            stat.addObjectiveSecondary(7)
        if roomID == "roomLake" and not spellUnlock.isKnown and not roomCurrent.isGive:
            stat.addObjectiveSecondary(8)
        if not roomLake.isGive:
            stat.setObjectiveSecondary(9)
        if roomID == "roomMountEntrance":
            stat.addObjectiveSecondary(10)
        if roomID == "roomCave_3_llm_crevasse":
            stat.addObjectiveSecondary(11)
        if roomID == "roomCave__3_lllm_treasure_crevasse":
            stat.addObjectiveSecondary(12)
        if roomID == "roomTempleEntrance":
            stat.addObjectiveSecondary(13)
        if roomID == "roomTempleBasement":
            stat.addObjectiveSecondary(14)
        if roomID == "roomRoadCorner" and roomCurrent.westBlocked:
            stat.addObjectiveSecondary(15)
        if roomID == "roomLairMid":
            stat.addObjectiveSecondary(16)
        if roomID == "roomHouseEntrance":
            stat.addObjectiveSecondary(17)
        if spellOblivion.isKnown:
            stat.addObjectiveSecondary(18)
        if roomID == "roomBookMirror":
            stat.addObjectiveSecondary(19)
        if roomID == "roomBook_3_1":
            stat.addObjectiveSecondary(20)
        if roomID == "roomHouseFoyer":
            stat.addObjectiveSecondary(21)
        if roomID == "roomHouseOffice":
            stat.addObjectiveSecondary(22)
        # Secondary remove
        if inv.gold >= RAFFLE_COMPENSATION:
            stat.removeObjectiveSecondary(1)

        if inv.ticket:
            stat.removeObjectiveSecondary(2)
        if invSpokesperson.ticket or (roomCarnivalWheelGame.characterDead and not roomCarnival.isGive):
            stat.removeObjectiveSecondary(3)
        if not roomGate.northBlocked:
            stat.removeObjectiveSecondary(4)
        if not roomBridge.eastBlocked:
            stat.removeObjectiveSecondary(5)
        if roomAlchemist.isBuy:
            stat.removeObjectiveSecondary(6)
        if not roomLake.isGive:
            stat.removeObjectiveSecondary(7)
        if spellUnlock.isKnown:
            stat.removeObjectiveSecondary(8)
        if roomLake.itemFound:
            stat.removeObjectiveSecondary(9)
        if not roomMountEntrance.northBlocked:
            stat.removeObjectiveSecondary(10)
        if roomID == "roomCave__3_lllm_treasure_crevasse":
            stat.removeObjectiveSecondary(11)
        if roomCave__3_lllm_treasure_crevasse.itemFound:
            stat.removeObjectiveSecondary(12)
        if not roomTempleEntrance.northBlocked:
            stat.removeObjectiveSecondary(13)
        if roomTempleBasement.characterDead:
            stat.removeObjectiveSecondary(14)
        if (not roomRoadCorner.westBlocked) and (roomTempleBasement.characterDead):
            stat.removeObjectiveSecondary(15)
        if roomID == "roomRoad2South":
            stat.removeObjectiveSecondary(16)
        if not roomHouseEntrance.northBlocked:
            stat.removeObjectiveSecondary(17)
        if not roomID == "roomShrineNorth":
            stat.removeObjectiveSecondary(18)
        if roomBookMirror.characterDead:
            stat.removeObjectiveSecondary(19)
        if (not roomID.startswith("roomBook_3")) and roomBook_3_End.itemFound:
            stat.removeObjectiveSecondary(20)
        if not roomHouseFoyer.northBlocked:
            stat.removeObjectiveSecondary(21)
        if roomHouseOffice.characterDead:
            stat.removeObjectiveSecondary(22)

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
        if turnCounter == 1:
            turn = "turn"
        else:
            turn = "turns"
        print("\nOh no! You died%s.\nYou took %s %s this life.\n\nTotal deaths: %s\nTotal turns: %s\n" % (cause,turnCounter,turn, deaths_total,turnCounter_total))
