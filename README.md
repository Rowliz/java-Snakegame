Snake Game - Java Swing Implementation

A classic Snake game built with Java Swing featuring smooth controls, pause/resume functionality, and score tracking.
🎮 Features

    Classic Snake Gameplay: Navigate the snake to eat food and grow longer

    Pause/Resume Functionality: Press SPACE to pause and resume the game

    Score Tracking: Earn 5 points for each food item eaten

    Collision Detection: Game ends if snake hits walls or itself

    Visual Grid: Game board with grid lines for better visibility

    Smooth Controls: Arrow key controls with direction restriction logic

🕹️ How to Play

    Objective: Control the snake to eat red food pellets

    Controls:

        Arrow Keys: Move the snake (Up, Down, Left, Right)

        SPACE: Pause/Resume the game

    Rules:

        Each food pellet increases score by 5 points

        Snake grows longer with each food eaten

        Game ends if snake hits walls or itself

🖥️ Game Components
Classes

    SnakeGame (Main Class)

        Extends JFrame

        Initializes the game window

        Contains the main method to launch the game

    GamePanel (Game Logic)

        Extends JPanel and implements ActionListener, KeyListener

        Handles all game mechanics:

            Snake movement and growth

            Food placement

            Collision detection

            Score tracking

            Rendering graphics

Game Elements

    Snake: Green rectangles that move as a linked chain

    Food: Red ovals placed randomly on the grid

    Grid: 20×20 tile playing field

    Score Display: Top-left corner of the screen

    Status Messages: "PAUSED" and "GAME OVER" notifications

🛠️ Technical Details
Game Settings
java

private static final int TILE_SIZE = 25;    // Size of each grid square
private static final int WIDTH = 20;        // Grid width in tiles
private static final int HEIGHT = 20;       // Grid height in tiles
private static final int DELAY = 550;       // Game speed (milliseconds)

Key Technologies

    Java Swing: For GUI components and rendering

    LinkedList<Point>: To store snake segments

    Timer: For game loop and updates

    Graphics2D: For drawing game elements

📁 Project Structure
text

SnakeGame/
├── SnakeGame.java          # Main class with JFrame
├── GamePanel.java          # Game logic and rendering
└── README.md              # This documentation

🚀 How to Run
Compilation
bash

javac SnakeGame.java

Execution
bash

java SnakeGame

🎯 Game Logic Explained
Movement System

    Snake moves one tile per game tick

    Direction changes are queued and processed

    Prevents 180-degree turns (can't reverse into itself)

Food Generation

    Randomly placed on empty tiles

    Regenerates immediately after being eaten

    Ensures food doesn't spawn on snake

Collision Detection
java

// Wall collision
head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT

// Self-collision
snake.contains(head)

🎨 Customization Options

You can easily modify the game by changing these constants:

    Game Speed: Adjust DELAY value (lower = faster)

    Grid Size: Change WIDTH and HEIGHT

    Tile Size: Modify TILE_SIZE for different resolutions

    Score System: Change the score += 5 value

    Colors: Modify the setColor() calls in paintComponent()

🐛 Known Issues & Improvements
Current Limitations

    No high score tracking

    No difficulty levels

    No sound effects

    No start screen or restart option

Possible Enhancements
java

// Add to implement later:
- High score persistence
- Multiple difficulty levels
- Sound effects
- Power-ups (speed boost, shrink, etc.)
- Obstacles on the game board
- Mobile device compatibility

📊 Score System

    +5 points: For each food pellet eaten

    Score Display: Continuously updated in top-left corner

    Game Over: Final score displayed at end of game

🎮 Game States

    Running: Normal gameplay

    Paused: Game temporarily stopped (press SPACE)

    Game Over: Snake collided with wall or itself

🤝 Contributing

Contributions are welcome! Here are some areas for improvement:

    Code Refactoring: Separate game logic from UI rendering

    New Features: Add levels, obstacles, or power-ups

    UI Improvements: Better graphics, animations, or themes

    Bug Fixes: Improve collision detection or movement smoothness

📄 License

This project is open source and available for educational and personal use.

Created with: Java Swing
Difficulty: Beginner to Intermediate
Lines of Code: ~150
Purpose: Educational game development example

Enjoy the game! 🐍
