Cheese Quest: The Plague of Kashkaval
=====================================

Table of Contents
-----------------
1. [Introduction](#introduction)
2. [Goals](#goals)
    - [Improve Maintainability and Scalability](#improve-maintainability-and-scalability)
    - [Improve Game Mechanics and Balance](#improve-game-mechanics-and-balance)
    - [Have a Graphic User Interface](#have-a-graphic-user-interface)
    - [Being Able to Save and Load Games](#being-able-to-save-and-load-games)
3. [Frequently Asked Questions](#frequently-asked-questions)
    - [Why Interactive Fiction?](#why-interactive-fiction)
    - [Why not use Inform?](#why-interactive-fiction)

Introduction
------------
Cheese Quest 2 is a text-based adventure game, or more commonly referred to as Interactive Fiction, inspired by many of the games published by Infocom in the 1980s.
It started development in 2017 after completing my first year of Computer Science at the University of Calgary.

Goals
-----
Here is the list of central goals I have for Cheese Quest 2, and some insight on my intentions in the game's development.

#### Improve Maintainability and Scalability

After "completing" Cheese Quest 1 in Python, there came a point where continuing development [become too much of a pain to be worth it](https://github.com/EvanQuan/CheeseQuest1#abandonment).
I thought rewriting the game from scratch in an object-oriented manner would help solve some of the problems I had in maintaining and improving the codebase.


#### Improve Game Mechanics and Balance

The original Cheese Quest wasn't really constrained mechanically from the programming end.
At least with respect to the short to medium term goals I had in adding stuff I already had idea on how I would go about solving them.
It would simply a manner of putting in the time to implement the features and playtesting to get the balance right.

#### Have a Graphic User Interface
I actually went back and forth on whether I wanted this.
There was something almost beautiful in the simplicity of classic IF in there being plain text as the only medium in which the game and player communicate.
I thought I would potentially spice it up with different colors when I figured out that I could print to standard output in different colors/bold/underline etc. to make it more readable and visually appealing, yet still keep it old fashioned.

However, I also wanted to add panels to display certain information at all times, such as the player stats, inventory and spells.
I found through playtesting many individuals had troubles keeping track of what items they had or making sure they've eaten enough food. It was annoying for some players to constantly need to check those.
I briefly considered having panels composed of ASCII characters displaying these off to the side, but decided that would be too annoying to implement and it would result in potential problems not worth dealing with.

Conveniently I was learning how to implement GUIs using javax.swing and java.awt at the time, so I finally decided to give it a go in Java.

#### Being Able to Save and Load Games
This may actually one if not the central goals I had since the beginning of the game's development.
Figuring out how I could serialize and deserialize Java objects in text files was actually one of the very first things I started working on because I knew that this was central to how the rest of the game was to be designed.
[I already designed Cheese Quest 1 to work around the inability to save](https://github.com/EvanQuan/CheeseQuest1#altneratives-to-saving) and I didn't want to continue having to work with that limitation.

Frequently Asked Questions
--------------------------

#### Why Interactive Fiction? Didn't IF as a genre die in the 1980s?
I've always wanted to create a game in one form or another.
I did do a bit of stuff in Unity, and while it was fun, there were a few problems I had.

First, I felt like my creativity was limited by what I could find on the Unit asset store.
Often it became less about what assets could I use to fit in my game and more about what game I could make from the assets I found from the store.
Unless I was willing to spend hundreds of dollars on a wide variety of art assets to create the characters, items or places that I wanted, I often had to piece together whatever was available and make it make sense.

##### Why not use Inform? Inform is already a language designed specifically for creating IF. It's popular within the IF community, and would seem the most practical to use.
I actually only recently found out about Inform.
After thinking about it for some time, I actually think that creating a game in Inform probably would result in the best game in the end.
Learning about Inform 7 actually impressed me quite bit considering how it reads as natural English.
It also abstracts away all the input parsing.
The biggest reason for this is that Inform deals with parsing player input phenomenally well.

... That being said, I kind of like the challenge of figuring how to parse player input and getting all the game interact manually.
Will I make a lot of mistakes? Yup.
Will I do it sub-optimally? Most definitely.
Will I it be more limited than Inform? Absolutely?