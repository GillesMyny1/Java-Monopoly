# Monopoly

## Project Description
This is Group 16's entry for SYSC3110's Monopoly Project. Also, please note that although each deliverable has an author, all parts of the project were collaborated on by multiple group members. These collaborations can be outlined through the various commits made on GitHub.

Exclaimer: This README file is best read on GitHub and was authored by Jeremy Trendoff.

## Authors
Gilles Myny - 101145477

Jeremy Trendoff - 101160306

Frank Dow - 101140402

Joshua Gatto - 101150890

## Deliverables
### Milestone 1
The Description for Milestone is as follows: 

A text-based playable version of the game, i.e.,players should be able to play the game via the console using the keyboard. There should be a command to print the state of each player (where they are on the board, how much money they have, which properties they own), a command to buy the property they landed on (if available), and a command to pass your turn to the next player. Events such as landing on a property owned by another player (and therefore paying the indicated rent), the bankruptcy of a player, etc. should be printed to the console when applicable. Also required: the UML modeling of the problem domain (class diagramswith complete variable and method signatures, and sequence diagrams for important scenarios), detailed description of the choice of data structures and relevant operations: you are providing an initial design and implementation for the Model part of the MVC.

In this milestone, Group 16 has prepared a Jar file containing all source files. We have also prepared documentation to futher understand the development and design of this project. We have created a UML file, Sequence diagrams for buying property, quitting the game, paying rent, and going bankrupt. We have also outlined our design decisions and provided a user manual for the project. The source code has also been documented using javadoc if you would like to look at the source code on out GitHub repository. 

### Milestone 2
The Description for the Milestone is as follows:

GUI-based version (now you’re adding the View and the Controller!) of the game. Display must be in a JFrame, anduserinput is via the mouse. You have freedom for other GUI decisions. Also required: Unit tests for the Model.

In this milestone, Group 16 has prepared a Jar file containing all source files. We have also prepared documentation to futher understand the development and design of this project. We have created a UML file and sequence diagrams for buying property, quitting the game, paying rent, and going bankrupt. We have also outlined our design decisions and provided a user manual for the project. The source code has also been documented using javadoc if you would like to look at the source code on out GitHub repository. There have also been some changes made to the project from the first milestone. These changes are outlined in the change log later in this README. 

### Milestone 3
The Description for the Milestone is as follows:

Additional features: houses, hotels, and special properties and squares such as: jail, “Go”, railroad, utility. Also, the ability to use any number of “AI” players. The way the “AI” player plays is up to you, and it could simply involve hard-codeddecisions and that’s good enough for our purpose. But if you’re really intrigued by investigating true AI, I’d recommend Reinforcement Learning as a technique: the AI player would keep playing against itself and update the relative weight of decisions over time based on whether it won or lost (or shorter term, whether its overall wealth increasedor not).

In this milestone, Group 16 has prepared a Jar file containing all source files. We have also prepared documentation to futher understand the development and design of this project. We have created a UML file and sequence diagrams for buying property, quitting the game, paying rent, and going bankrupt. We have also outlined our design decisions and provided a user manual for the project. The source code has also been documented using javadoc if you would like to look at the source code on out GitHub repository. There have also been some changes made to the project from the second milestone. These changes are outlined in the change log later in this README. 

### Milestone 4
The Description for the Milestone is as follows:

Two more things: 1- Save/load features. You may use Java Serialization to 
achieve this. 2- International versions with custom street names, values and currencies. 
The customization may be defined in XML or JSON format.

In this milestone, Group 16 has prepared a Jar file containing all source files. We have also prepared documentation to futher understand the development and design of this project. We have created a UML file and sequence diagrams for various behaviour in our monopoly game. We have also outlined our design decisions and provided a user manual for the project. The source code has also been documented using javadoc if you would like to look at the source code on out GitHub repository. There have also been some changes made to the project from the third milestone. These changes are outlined in the change log later in this README. 

## The ChangeLog
### Milestone 1
Milestone 1 was the first iteration of this project and, therefore, we have not made any changes to the project yet. In future milestones, our design changes will be documented in this section. If you like to learn more about our design choices, please read our design decisions document and the rest of our provided documentation.

### Milestone 2
In milestone 2, team were required to create a GUI for there Monopoly game. Previously in milestone 1, the game was ran in the console. The change to a GUI has prompted some changes to our code. Firstly, we have restructured our Game class into the model responsible for the business logic of the game. Previously, the Game class also implemented the console outputs. A test class has also been created to validate the logic of out Game model. Inside of that game class, we have implemented an enum to the states of the game. On the recommendation of our TA, we have made changes to the Board class to use an ArrayList for the board and an initProperties() method to initialize the board. Also on the recommendation of the TA, we have created the class Ownable to represent ownable tiles. This will now be the parent class of Property, Railroad, and Ultility. This provides an extra layer of abstraction for tiles. We now follow the MVC design for our application. We have added a View and a Controller to the project. The view is broken down into a GameFrame class that implements the GameView interface which provides the boilerplate for handling game states. Our controller, GameController, controls what happens when the player presses a button. To read more about our design desicions, please read our Design_Desicions document. This can be found in the Documentation folder under Milestone 2.       

### Milestone 3
In milestone 3, the team was required to add additional useful features to enhance the Monopoly game. Previously, the game only included the property tiles and a GUI. The game was update with houses, hotels, and special properties. Houses allow for a player that owns all properties of one colour set to charge extra rent fee when another player lands on their property, hotels work the same way. Special properties such as Railroad and Utility tiles were also added so that players could purchase them. More importantly, the basic game tiles such as Go, Jail(Visiting Jail), Free Parking, and Go To Jail tiles were added with their respective attributes and consequences. The game now gives users the option to add "AI" players which follow the same rules a normal player does. The team has also updated the appropriate model test cases and implemented the Java Event Model for communication from the model to the view. Specific aspects of the game GUI have also been refined. To read more about our decisions made for this milestone, please read our documentation. This can be found in the Documentation folder under Milestone 3.

### Milestone 4
In milestone 4, the team was required to add the save/load feature into the game, and provide multiple international versions to choose from when starting a new game. We also improved upon our controller. Previously, we used one controller to control all actionable objects and distingushed between them by passing a string to controller. This resulted in "magic strings" being used to control the game. Now, each actionable object defines its own controller with a state from our state enum. Based on what that state is, the controller initiates an action. The team has also updated the appropriate model test cases to include tests from importing each international version, and the save and load feature. To read more about our decisions made for this milestone, please read our documentation. This can be found in the Documentation folder under Milestone 4.

## Known Issues 
### Milestone 1
- When the game is won, the winning player may have to go through one extra turn before they are declared the winner.
- There is some code written that should be useful later, but will not be used now. This results in a bit less code cleanliness. 
- It may make sense for the Game class methods, pay() and purchaseProperty(), to move to the piece class.  

### Milestone 2
- In order to run the tests on the model, you must input player names via a JOPtionPane for each test.
- In the game controller, the switch statement in actionPerformed() does not work with our State enum.  
- There is some code written that should be useful later, but will not be used now. This results in a bit less code cleanliness.

### Milestone 3
- In terms of the houses, you can not build on more than one set of properties.
- In order to run the tests on the model, you must input player names via a JOPtionPane for each test.
- There is some code written that should be useful later, but will not be used now. This results in a bit less code cleanliness.

### Milestone 4
- In terms of the houses, you can not build on more than one set of properties.
- In order to run the tests on the model, you must input player names via a JOPtionPane for each test.
- In order to add more international versions of the game, the source code must be changed to accomodate each new version.
- This is not exactly an issue, but each time the player saves the game, they will automatically exit the game as well. Our save feature is more of a save and exit by design.

## The Roadmap Ahead
### Milestone 1
Our next steps for this project are to finalize the testing of each of the model classes using JUnit testing to provide more insight into some bugs that we might have not caught yet. The team also plans to look at how we can improve our models. One way to do this may be to implement the Java Event Model into our code and use interfaces to decrease coupling between our classes. Of course, any feedback from milestone 1 will be addressed and fixed as well. These changes will all be outlined in the change log for milestone 2.

### Milestone 2
Our next steps for this project are to implement the Java Event Model into our design. Instead of passing specific arguments to the view from the model, we should be passing different events that the view can process. This, plus adding in any recommendations from our TA should put Team 16 on track for Milestone 3.

### Milestone 3
Our next steps for this project are to implement save and load features through Java Serialization, and to add international versions of the game which have different street names, values, currencies, and more which will be defined in XML or JSON format. On top of this, making any changes recommended by our TA will put Team 16 on track for Milestone 4.

### Milestone 4
Milestone 4 is the final milestone for this project and, therefore, there should not be any new features implemented to this monopoly game. However, modifications will be made with the recommendation of our TA.

## Contact Us
If you have any questions, comments, or concerns please feel free to contact the team using the information below or open up an issue or discussion on our GitHub page.

Jeremy Trendoff: jeremytrendoff@cmail.carleton.ca

Gilles Myny: gillesmyny@cmail.carleton.ca

Frank Dow: frankdow@cmail.carleton.ca

Joshua Gatto: joshuagatto@cmail.carleton.ca
