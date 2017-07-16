class Item(object):
    def __init__(self,count = 1):
        self.name = name
        self.names = names
        self.description = description
        self.count = count
    def printCount():
        if count == 1:
            nameType = name
        else:
            nameType = names
        print("You have " + count + nameType)

class ReadableItem(Item):
    def __init__(self):
        Item.__init__(self)
        self.readDescription = readDescription

class Food(Item):
    def __init__(self):
        Item.__init__(self)
        self.health = health

class Gold(Item):
    def __init__(self):
        Item.__init__(self)
        self.name = "gold"
        self.names = "gold"
        self.description = "The edges are worn down from handling."

class Letter(
