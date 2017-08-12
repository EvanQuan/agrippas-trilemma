Notes on design decisions for self use in considering/revising decisions made

Making a new game instead of continuing original Cheese Quest
	* Pros
		* Still inspired by Hitchhiker's Guide to Galaxy text adventure game
		* Wanted to continue working original Cheese Quest but code became hugely impractical to add onto as it scaled up.
			* Not object oriented
			* Huge variable scoping issues
			* Large amounts of repeated code/lack of functions, which could not have been implemented due to variable scoping issues
		* Java is better for object oriented programming than Python.
		* More familiar with Java, especially for file IO, and GUI.
	* Cons
		* Huge amount of work, and everything needs to be redone

Rooms are connected together through pointers (similar to a linked list) instead of using a XYZ coordinate system.
	* Pros
		* Not restricted by the space a room takes up to allow more rooms to be connected.
			* Ex. Carnival tents in carnival and courtyard stores not conflicting in Cheese Quest 1.
		* Allows rooms to not be mutually connected if desired.
			* Ex. Black book room puzzle in Cheese Quest 1.
	* Cons
		* More work needs to be done to connect them, as neighbours need to be added manually, and in both directions.
		* More prone to bugs if rooms are not mutually connected when they should be.

All Room, Collectable classes follow singleton design pattern.
	Pros
		* There will never be multiple instances of the same room since rooms are linked together.
		* Storing multiple instances of the same collectable take up more space than necessary since the properties of each instance of Collectable does not change.
		* equals() method does not need to be created for each class (overriding and using parent equals() method)
	Cons
		* private static instantiation and getInstance() method need to be created for each class

Classes are "aggressively" divided into packages
	Pros
		* Makes organizing classes much cleaner and easier to manage when writing them.
		* Makes inheritance better to visualize and prevents some forgotten inheritance.
	Cons
		* Initial importing is a pain as there are technically no subpackages.
		* Forgetting to import some packages may result in bugs (easily fixable though).

Menus directly output to OutputPanel
	Originally, menus appended to a StringBuffer, throughout the input process, and finally outputted the StringBuffer.toString() out to the output panel. This was fast, and worked well when the OutputPanel originally used a JTextArea, as it handled only plain text. However, since the OutputPanel now uses a JTextPane to allow for better text coloring and styling, outputting everything together would style everything together for each output.
