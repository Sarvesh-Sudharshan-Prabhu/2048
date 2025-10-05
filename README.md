# 🎮 2048 (Java)

A console-based implementation of the classic **2048 puzzle game** in Java.  
Players combine tiles with matching numbers to reach the coveted **2048 tile**, using logic and strategy to maximize their score.

---

## 📂 Project Structure
```
2048-main/
└── 2048 (3).java
```

---

## 🧩 Overview
The `_2048` class encapsulates all game logic for the 2048 puzzle, including:
- Generating random tiles (2s and 4s)
- Handling tile movement and merging in four directions
- Tracking scores and previous states
- Detecting win/loss conditions

This project demonstrates **array manipulation**, **randomization**, and **game state logic** using fundamental Java programming techniques.

---

## 🧠 Core Class: `_2048`

### Constructors
```java
public _2048()
```
Creates a new 4×4 2048 game board and generates two random starting tiles.

```java
public _2048(int[][] board)
```
Creates a game instance from a predefined 4×4 board (useful for testing).

---

### Key Methods
| Method | Description |
|--------|--------------|
| `private void generateTile()` | Adds a new tile (2 with 80% chance, 4 with 20% chance) to a random empty cell. |
| `public int[][] getBoard()` | Returns the current game board. |
| `public int getScore()` | Returns the current score. |
| `public void move(String direction)` | Moves and merges tiles in the specified direction (`up`, `down`, `left`, `right`). |
| `public boolean canMove()` | Checks if there are valid moves remaining. |
| `public boolean hasWon()` | Checks if the player has reached a 2048 tile. |
| `public void undo()` | Restores the previous game state (score and board). |

---

## 🧪 Example Usage
```java
public class Main {
    public static void main(String[] args) {
        _2048 game = new _2048();

        // Display starting board
        printBoard(game.getBoard());

        // Example move
        game.move("left");
        printBoard(game.getBoard());

        System.out.println("Score: " + game.getScore());
    }

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
```

### Sample Output
```
0   2   0   0
0   0   0   2
0   0   0   0
0   0   0   0

After moving left:
2   2   0   0
0   0   0   0
0   0   0   0
0   0   0   0

Score: 4
```

---

## ⚙️ How to Run

1. Ensure **Java (JDK 8+)** is installed.  
2. Save both `_2048.java` (rename the file from `2048 (3).java`) and `Main.java` in the same folder.  
3. Compile and run:
   ```bash
   javac _2048.java Main.java
   java Main
   ```

---

## 🧩 Game Logic Concepts Demonstrated
- 2D Array traversal and manipulation  
- Random tile generation  
- State tracking (score, undo functionality)  
- Conditional merging of numeric tiles  

---
