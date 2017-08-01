# Abstract classes

class Item(object):
    PRIMARY = 1
    def __init__(self,name,names,description):
        self.name = name
        self.names = names
        self.description = description

class Readable(Item):
    def __init__(self,name,names,description,text):
        Item.__init__(self,name,names,description)
        self.text = text

class Food(Item):
    def __init__(self,name,names,description,count = 1,health):
        Item.__init__(self,name,names,description,count)
        self.health = health
    def getDescription():
        Item.getDescription()
        self.printHealth()
    def printHealth():
        print("It will keep you full for " + self.health + "turns.")

# Cannot be taken by player
class Background(Item):
    def __init__(self,name,names,description):
        Item.__init__(self,name,names,description)


# Sub classes

class Gold(Item):
    def __init__(self):
        Item.__init__(self, name = ["gold","coin","gold coin"], names = ["gold","coins","gold coins"], description = "The edges are worn down from handling.")

class Letter(Readable):
    def __init__(self):
        Readable.__init__(self, name = ["letter"], names = ["letters"], description = "Made out of old parchment, the message on it is written in ink.")
	def getReadDescription(playerName = "the hero of Kashkaval"):
		return("It reads:\n\nTo " + playerName + ",\n\nA certain Eden Von Roquefort has set up residence NORTH of MOUNT MAGNA. While he purports to be a lowly cheese mage, reliable sources claim him to be the demon lord, Vesh'kathal the Deceiver, a shapeshifter infamous of manipulating the minds and bending the wills of others. Legend tells of a saviour, deemed the Monterey Messiah, who will save all of Kashkaval from his wickedness. It has be brought to my attention that you are that saviour that the legends speak of. While I have very important matters to attend to, the best I can do is help instruct you in how to defeat this demon lord:\n\nFIRST, you must acquire the staff from the Garrotxian temple NORTHEAST of this town, for it is the only weapon capable of defeating such a powerful demon.\n\nNEXT, once you have the staff, go NORTH through the MINES of MOUNT MAGNA and find him at his house on the other end.\n\nFINALLY, kill Roquefort and Kashkaval will be saved from his wrath.\n\nI know this is probably a lot to digest at once, but you are our only hope. I fear in your attempt to complete this task, Vesh'kathal will attempt to thwart you. He may attempt to contact and manipulate you, or have his minions work to stop you. Whatever he does, you must persevere.\n\nMay you be blessed,\n\nThe last prophet of Garrotxa")

class JailKey(Item):
    def __init__(self):
        Item.__init__(self, name = ["jail cell key", "jail key", "cell key", "key"], names = ["jail cell keys", "jail keys", "cell keys", "keys"], description = "It is made out of brass.")

class  SkeletonKey(Item):
    def __init__(self):
        Item.__init__(self, name = ["key of Ahm'domosh", "skeleton key", "key"], names = ["keys of Ahm'domosh", "skeleton keys", "key"], description = "Made from bone, the head looks like a skull with glowing purple eyes.")

class Pickaxe(Item):
    def __init__(self):
        Item.__init__(self, name = ["pickaxe", "pick axe", "pick", "axe"], names = ["pickaxes", "pick axes", "picks", "axes"], description = "A sturdy tool useful for mining.")

class Shrubbery(Item):
    def __init__(self):
        Item.__init__(self, name = ["shrubbery", "shrub"], names = ["shrubberies", "shrubs"], description = "It's a very nice shrubbery, and not too expensive.")

class LuckyFoot(Item):
    def __init__(self):
        Item.__init__(self, name = ["lucky rabbit foot", "lucky foot", "rabbit foot", "foot"], names = ["lucky rabbit feet", "lucky feet", "rabbit feet", "feet"], description = "A prominent symbol of the RNGesus, the ancient god of gambling, luck, and salt.")

class Porridge(Food):
    def __init__(self):
        Food.__init__(self, name = ["bowl of porridge", "porridge", "bowl"], names = ["bowls of porridge", "porridge", "bowls"], description = "Cold and bland.")

class Bowl(Item):
    def __init__(self):
        Item.__init__(self, name = ["bowl"], names = ["bowls"], description = "Round and dented. Made out of tin.")

class Lantern(Item):
    def __init__(self, oilCount = 0, onStatus = False):
        Item.__init__(self, name = ["lantern"], names = ["lanterns"], description = "Perfect for lighting dark areas.")
		self.__EMPTY__ = 0
		self.oilCount = oilCount
	def getDescription(self):
		if self.isOn():
			onStatus1 = "lit and has enough oil to last " + self.oilCount + " turns."
		else:
			onStatus1 = "unlit."
		onStatus2 = " It is currently " + onStatus1
		return(self.description + onStatus2)

	def getOilCount(self):
		return(oilCount)
	def incrementOilCount(self, incrementCount = 1):
		oilCount += incrementCount
	def decrementOilCount(self, decrementCount = 1):
		if ((self.oilCount - decrementCount) < self.__EMPTY__):
			self.oilCount = 0
		else:
			self.oilCount -= decrementCount
	def isOn(self):
		if (self.oilCount > self.__EMPTY__):
			onStatus = True
		else:
			onStatus = False
		return(onStatus)
class LanternOil(Item):
    def __init__(self):
        Item.__init__(self, name = ["vial of lantern oil", "vial", "lantern oil","oil"], names = ["vials of lantern oil", "vials", "lantern oil", "oil"], description = "Perfect for things that are perfect for lighting dark areas."):


class ChickenPotPie(Food):
    def __init__(self):
        Food.__init__(self)

class Biscuit(Food):
    def __init__(self):
        Food.__init__(self)

class GrapplingHook(Item):
    def __init__(self):
        Item.__init__(self)

class Staff(Item):
    def __init__(self):
        Item.__init__(self)

class Ticket(Item):
    def __init__(self):
        Item.__init__(self)

class Coal(Item):
    def __init__(self):
        Item.__init__(self)

class Potato(Food):
    def __init__(self):
        Food.__init__(self)

class Bandage(Item):
    def __init__(self):
        Item.__init__(self)





class Flask(Item):
    def __init__(self):
        Item.__init__(self)

class Dragonstone(Item):
    def __init__(self):
        Item.__init__(self)

class WoodenBird(Item):
    def __init__(self):
        Item.__init__(self)


# Food

class Brie(Food):
    def __init__(self):
        Food.__init__(self)

class FunnelCake(Food):
    def __init__(self):
        Food.__init__(self)

class HalfEatenFunnelCake(Food):
    def __init__(self):
        Food.__init__(self)

class Munster(Food):
    def __init__(self):
        Food.__init__(self)

class Potion(Food):
    def __init__(self):
        Food.__init__(self)

class Stilton(Food):
    def __init__(self):
        Food.__init__(self)

class Swiss(Food):
    def __init__(self):
        Food.__init__(self)

class Wensleydale(Food):
    def __init__(self):
        Food.__init__(self)

# Readable

class Book(Readable):
    def __init__(self):
        Readable.__init__(self)

class Journal(Readable):
    def __init__(self):
        Readable.__init__(self)

class Note(Readable):
    def __init__(self):
        Readable.__init__(self)
        self.note_1 = note_1
        self.note_2 = note_2
        self.note_3 = note_3
        self.note_4 = note_4

class Memo(Readable):
    def __init__(self):
        Readable.__init__(self)
