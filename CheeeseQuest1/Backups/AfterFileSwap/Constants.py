#_______Constants_______________________________________________________________

WINDOW_WIDTH = 200
# Debug mode password
PASSWORD = "q"
# Stats
HUNGER_MAX = 80
HUNGER_START = 5
HUNGER_DEBUG = HUNGER_MAX
HEALTH_MAX = 7
HUNGER_DARKNESS = 30
# How many turns until death that warning messages appear
HUNGER_WARNING = 15

DEFAULT_NAME = "the hero of Kashkaval"


# Food Hunger Values
HUNGER_PORRIDGE = HUNGER_MAX
HUNGER_FUNNELCAKE = 20 # 2 hunger/gold
HUNGER_HALFFUNNELCAKE = HUNGER_FUNNELCAKE / 2
HUNGER_PIE = 70 # 3.5 hunger/gold
HUNGER_BISCUIT = 30
HUNGER_POTION = 125 # 5 hunger/gold, or 12.5 hunger/gold if refill
HUNGER_CHEESE = 10

# Lantern oil turn duration
OIL_DURATION = 30
# Ball of light duration
LIGHT_DURATION = OIL_DURATION * 2
# Prices
# Buy
PRICE_BUY_PICKAXE = 20
PRICE_BUY_FUNNELCAKE = 5
PRICE_BUY_FOOT = 30
PRICE_BUY_LANTERN = 30
PRICE_BUY_OIL = 5
PRICE_BUY_PIE = 20
PRICE_BUY_HOOK = 400
PRICE_BUY_BANDAGE = 10
PRICE_BUY_TICKET = 20
PRICE_BUY_POTION = 25
PRICE_REFILL_POTION = 10
# Sell
PRICE_SELL_COAL = 25
PRICE_SELL_STONE = 300
# LAKE
LAKE_GOLD_REWARD = 241
LAKE_PIE_REWARD = 1
# MOUNT ENTRANCE
LOOT_ENTRANCE_COAL = 3
LOOT_ENTRANCE_NOTE = 1
# CAVERN
# Coal Mine
COAL_MIN = 6
COAL_MAX = 9
PICKAXE_BREAK_CHANCE = 1
RUBBLE_FALL_CHANCE = 1
# Creature
CREATURE_ROAM_MIN = 22
CREATURE_ROAM_MAX = 32
CREATURE_CHASE_MIN = 16
CREATURE_CHASE_MAX = 22
# Cave Treasure chest gold reward
# Should offset most of equipment prices
CHEST_REWARD = 324
# Dead body loot
LOOT_A_GOLD = 187
LOOT_A_OIL = 2
LOOT_B_GOLD = 96
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
LOOT_GATE_GOLD = 16
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
# Raffle ticket reimbursement
#   How many turns raffle ticket can be exchanged at roomCarnival
RAFFLE_TIMER = 5
# Raffle ticket reward
RAFFLE_COMPENSATION = 200

# HOUSE
DIAL_START = "GREEN"
DIAL_ANSWER = "RED"
LEVER_START = "FORWARD"
LEVER_ANSWER = "BACKWARDS"


# Non-resetable varaibles
#   Caries on between lives
# Total game counters
# Continue between playthroughs of a single sitting
turnCounter_total = 0
deaths_total = 0

# Player name
playerName = False
