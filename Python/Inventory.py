class Inventory(object):
    def __init__(self, items):
        self.items = items
    def __str__(self):
        s = ""
        for item in items:
            s += "    " + str(item) + "\n"
        return(s)
    def getTypes(self):
        return(len(items))
    def add(self,count,inItem):
        pass
    def set(self,inItem,count):
        pass
    def empty(self):
        self.items = {}
