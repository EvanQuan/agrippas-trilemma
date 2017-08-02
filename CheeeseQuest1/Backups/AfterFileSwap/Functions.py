from Constants import *

import textwrap

# Non-Game-Specific function

# Prints lines wrapped within a certain number of characters in width
def printWrap(inText = ""):
    outText = textwrap.wrap(inText, WINDOW_WIDTH)
    for line in outText:
        print(line)

def printWrapList(inText = ""):
    outText = textwrap.wrap(inText, WINDOW_WIDTH)
    print(outText)


# printWrap("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
#
# printWrapList("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")

# printWrap("Dank\n\nMemes\nAyy lmao\n\nDank")
#
# printWrapList("Dank\n\nMemes\nAyy lmao\n\nDank")
