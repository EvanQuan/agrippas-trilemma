Agrippa's Trilemma
==================

Table of Contents
-----------------
1. [Introduction](#introduction)
2. [Goals](#goals)
    - [Improve Maintainability and Scalability](#improve-maintainability-and-scalability)
    - [Improve Game Mechanics and Balance](#improve-game-mechanics-and-balance)
    - [Have a Graphic User Interface](#have-a-graphic-user-interface)
    - [Being Able to Save and Load Games](#being-able-to-save-and-load-games)
3. [Development History](#development-history)
4. [Frequently Asked Questions](#frequently-asked-questions)
    - [Why Interactive Fiction?](#why-interactive-fiction)
    - [Why Not Make Something More Contemporary?](#why-not-make-something-more-contemporary)
    - [Why not use Inform?](#why-not-use-inform)

Introduction
------------
Agrippa's Trilemma is a text-based adventure game, or more commonly referred to as Interactive Fiction, inspired by many of the games published by Infocom.
It started development in August of 2017.

<!-- MOVE TO WIKI -->
Goals
-----
Here is the list of central goals I have for Cheese Quest 2, and some insight on my intentions in the game's development.

#### Improve Maintainability and Scalability

After "completing" Cheese Quest 1 in Python, there came a point where continuing development [become too much of a pain to be worth it](https://github.com/EvanQuan/CheeseQuest#completion-and-abandonment).
I thought rewriting the game from scratch in an object-oriented manner would help solve some of the problems I had in maintaining and improving the codebase.


#### Improve Game Mechanics and Balance

The original Cheese Quest wasn't really constrained mechanically from the programming end.
At least with respect to the short to medium term goals I had in adding stuff I already had idea on how I would go about solving them.
It would simply a manner of putting in the time to implement the features and playtesting to get the balance right.

#### Have a Graphic User Interface
I actually went back and forth on whether I wanted this.
There is something almost beautiful in the simplicity of classic IF having plain text being the only medium in which the game and player communicate.
I thought I would potentially spice it up when I figured out that I could print to standard output in different colors/bold/underline. It would make it more readable and visually appealing, yet still keep it old fashioned by sticking to text only.

However, I also wanted to add panels to display certain information at all times, such as the player stats, inventory and spells.
I found through playtesting many individuals had troubles keeping track of what items they had or making sure they've eaten enough food. It was annoying for some players to constantly check up on that information, especially when they have another task to focus on like solving a puzzle or gathering resources.
I briefly considered having panels composed of ASCII characters displaying these off to the side, but decided that would be too annoying to implement and it would result in potential problems not worth dealing with.

Conveniently I already had an elementary understanding of implementing GUIs using javax.swing and java.awt at the time, so I finally decided to give it a go in Java.

#### Being Able to Save and Load Games
This may actually one if not the central goals I had since the beginning of the game's development.
Figuring out how I could serialize and deserialize Java objects in text files was actually one of the very first things I started working on because I knew that this was central to how the rest of the game was to be designed.
[I already designed Cheese Quest 1 to work around the inability to save](https://github.com/EvanQuan/CheeseQuest1#altneratives-to-saving) and I didn't want to continue having to work with that limitation.

Development History
-------------------
Development began very slowly as I spent more time thinking and planning in order not to make a lot of the same mistakes I did for the first game.
On top of that, I've been very busy with school, so I have mainly set this project aside.
As it stands, not much progress has been made.

Frequently Asked Questions
--------------------------

#### Why Interactive Fiction?
At first glance, IF would seem like a rather odd choice for a personal project.
The genre has essentially died out commercially, and while it is still produced by mainly hobbyists today, it has crawled into obscurity.
My first experience playing IF was playing Infocom's [Hitchhiker's Guide to the Galaxy](http://www.bbc.co.uk/radio4/hitchhikers/game.shtml) game in my early childhood.
I was already a big Hitchhiker's fan at a young age, having listened to the entire hg2g radio show, so learning there was a game about it was exciting.
Since then, I've played portions of [Zork](https://en.wikipedia.org/wiki/Zork_I), a Zork parody within [Kingdom of Loathing](https://kingdomofloathing.com), and [Planetfall](https://en.wikipedia.org/wiki/Planetfall).

With my very first experience programming in 2016, the idea of creating a game in one form or another began to emerge.
I made [Cheese Quest 1](https://github.com/EvanQuan/CheeseQuest1), in which I faced a lot of problems and made a lot of mistakes.
Upon it's completion, I decided to start working on Cheese Quest 2.

#### Why Not Make Something More Contemporary?
In terms of game development in general, I did do a bit of stuff in Unity, and while it was fun, there were a few problems I had.

First, I felt like I was limited by what I could find on the Unity asset store.
Often it became less about what assets could I use to fit in my game and more about what game I could make from the assets I found from the store.
Unless I was willing to spend a fair amount of money on a wide variety of art assets to create the characters, items or places that I wanted, I often had to piece together whatever was freely available and make it make sense.
As a more minor point, this also resulted in an issue of having models with varying art styles that didn't mesh well together asthetically.

Second, I wanted to create a game that told a story.
Not necessarily a good one, but a story nonetheless.
My favourite games were always ones that had a narrative component, and so it's something I really wanted to include in my own.
This alone heavily favoured IF as a choice.

Third, I'm primarily making this game for myself and a select few individuals are who are interested.
I recognize that creating something like a sidescroller or top-down shooter may be far more flashy, appeal to a larger audience, and potentially even be marketable, but I'm not really doing this to impress.
My own IF is something I've genuinely wanted to make for a while.

#### Why not use Inform?
**Inform is already a language designed specifically for creating IF. It's popular within the IF community, and would seem the most practical to use.**

This is something I briefly considered.
In terms of development, using Inform would undoubtedly and overwhelmingly be more productive.
All the parsing, which for me is the central and most challenging problem to deal with, is all abstracted away;
So are the all the inner workings of how the player, items, NPCs, and locations interact.

That being said, I kind of like the challenge of figuring out how to solve these problems on my own.
Using Inform, especially Inform 7, almost feels like cheating, which takes some of the fun away from it, even if it produces a better game in the end result.
So in all, I decided against using Inform and will continue to develop the game in Java.
