Lottery System in Java
This project implements a simple lottery game application in Java using Swing.

**Features:**

-Generates random lottery tickets (5-digit numbers)
-Displays pre-defined winning tickets (also 5-digit numbers) with visual differentiation for winners (colors)
-Allows users to check their tickets against the winning numbers
-Provides feedback on whether the user's ticket matches any winning numbers

##How to Run:

**Prerequisites:** 

-Ensure you have Java Development Kit (JDK) installed on your system.
-Compile: Use a Java IDE or command line compiler to compile the Lottery.java file.
-Run: Execute the compiled class file (e.g., java Lottery).

**Gameplay:**

-Click the "Generate Ticket" button to generate a random 5-digit lottery ticket for you.
-Enter your ticket number in the provided text field (ensure it's a 5-digit number).
-Click the "Check Results" button to compare your ticket with the winning numbers.
-The result label will display a message indicating if you have any matches and, if so, which winning number(s) you matched.

**Note:**

-This is a basic implementation for demonstration purposes.
-Error handling for invalid user input (non-numeric characters) is not implemented in the current version.
-Improvements and Extensions:

-Allow for a customizable number of winning tickets.
-Implement different prize tiers based on the number of matches.
-Add features like ticket history or sound effects to enhance the user experience.

**Code Structure:**

##The code utilizes the following key components:

**Lottery class:**
-The main class that manages the UI components and game logic.
-generateRandomTicket method: Generates a random 5-digit lottery ticket number.

**Swing components:**

-JLabels for displaying text and instructions.
-JTextFields for user input (ticket number).
-JButtons for user interaction (generating tickets and checking results).
-GridBagLayout for arranging UI elements.

**Improvements:**

-New registrations not working can be fixed.
-More appealing ui can be implemented.
