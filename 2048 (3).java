import java.util.ArrayList;

public class _2048
{
	private final int rows = 4;
	private final int cols = 4;
	private int[][] board;
	private int[][] previousBoard;
	private int score;
	private int previousScore;

	/**
	 * Initializes board and previousBoard using rows and cols.
	 * Uses the generateTile method to add two random tiles to board.
	 */
	public _2048()
	{
		this.board = new int[rows][cols];
		this.previousBoard = new int[rows][cols];
		this.previousScore = 0;
		this.score = 0;
		generateTile();
		generateTile();
	}

	/**
	 * Initializes the board of this object using the specified board.
	 * Initializes previousBoard using rows and cols.
	 *
	 * Precondition: the specified board is a 4x4 2D Array.
	 *
	 * @param board
	 */
	public _2048(int[][] board)
	{
		this.board = board;
		this.previousBoard = new int[rows][cols];
		this.score = 0;
		this.previousScore = 0;
	}

	/**
	 * Generates a tile and add it to an empty spot on the board.
	 * 80% chance to generate a 2
	 * 20% chance to generate a 4
	 *
	 * Does nothing if the board is full.
	 */
	private void generateTile()
	{
		boolean empty = false;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (board[i][j] == 0)
				{
					empty = true;
					break;
				}
			}
		}
		if (empty)
		{
			int tile;
			if (Math.random() < 0.8)
			{
				tile = 2;
			}
			else
			{
				tile = 4;
			}
			int r;
			int c;
			while (true)
			{
				r = (int)(Math.random() * rows);
				c= (int)(Math.random() * cols);
				if (board[r][c] == 0)
				{
					break;
				}
			}
			board[r][c] = tile;
		}
	}

	/**
	 * Returns the board.
	 * @return
	 */
	public int[][] getBoard()
	{
		return board;
	}

	/**
	 * Returns the score.
	 * @return
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * Saves board into previousBoard and score into previousScore
	 * then performs a move based on the specified direction:
	 *
	 * Valid directions (not case sensitive):
	 *  up
	 *  down
	 *  left
	 *  right
	 *
	 * Adds a new tile to the board using the generateTile method.
	 *
	 * @param direction
	 */
	public void move(String direction)
	{
		previousScore = score;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				previousBoard[i][j] = board[i][j];
			}
		}

		direction = direction.toUpperCase();
		if (direction.equals("UP"))
		{
			moveUp();
		}
		if (direction.equals("DOWN"))
		{
			moveDown();
		}
		if (direction.equals("LEFT"))
		{
			moveLeft();
		}
		if(direction.equals("RIGHT"))
		{
			moveRight();
		}
		generateTile();
	}

	/**
	 * Shifts all the tiles up, combines like tiles that collide.
	 */
	private void moveUp()
	{
		for (int j = 0; j < cols; j++)
		{
			ArrayList<Integer> rowcol = new ArrayList<>();
			for (int i = 0; i < rows; i++)
			{
				if (board[i][j] != 0)
				{
					rowcol.add(board[i][j]);
				}
			}

			for (int i = 0; i < rowcol.size() - 1; i++)
			{
				int combine;
				if (rowcol.get(i).equals(rowcol.get(i + 1)))
				{
					combine = rowcol.get(i) + rowcol.get(i);
					rowcol.set(i, combine);
					rowcol.remove(i + 1);
					score = score + combine;
					i--;
				}
			}

			while (rowcol.size() < rows)
			{
				rowcol.add(0);
			}

			for (int i = 0; i < rows; i++)
			{
				board[i][j] = rowcol.get(i);
			}
		}
	}

	/**
	 * Shifts all the tiles down, combines like tiles that collide.
	 */
	private void moveDown()
	{
		for (int j = 0; j < cols; j++)
		{
			ArrayList<Integer> rowcol = new ArrayList<>();
			for (int i = rows - 1; i >= 0; i--)
			{
				if (board[i][j] != 0)
				{
					rowcol.add(board[i][j]);
				}
			}

			for (int i = 0; i < rowcol.size() - 1; i++)
			{
				int combine;
				if (rowcol.get(i).equals(rowcol.get(i + 1)))
				{
					combine = rowcol.get(i) + rowcol.get(i);
					rowcol.set(i, combine);
					rowcol.remove(i + 1);
					score = score + combine;
					i--;
				}
			}

			while (rowcol.size() < rows)
			{
				rowcol.add(0);
			}

			for (int i = 0; i < rows; i++)
			{
				board[i][j] = rowcol.get(rows - 1 - i);
			}
		}
	}

	/**
	 * Shifts all the tiles left, combines like tiles that collide.
	 */
	/**
	 * Shifts all the tiles left, combines like tiles that collide.
	 */
	private void moveLeft()
	{
		for (int i = 0; i < rows ; i++)
		{
			ArrayList<Integer> rowcol = new ArrayList<>();

			// This logic uses an array list to basically put every non 0 tile in the current row into the array list.
			for (int j = 0; j < cols ; j++)
			{
				if(board[i][j] != 0)
				{
					rowcol.add(board[i][j]);
				}
			}

			// This basically combines 2 values that are equal and removes the 2nd tile after they are combined. This goes from left to right
			for (int j = 0; j < rowcol.size() -1 ; j++)
			{
				int combine;
				if (rowcol.get(j).equals(rowcol.get(j + 1)))
				{
					combine = rowcol.get(j) + rowcol.get(j);
					rowcol.set(j, combine);
					rowcol.remove(j + 1);
					score = score + combine;
					j--;
				}
			}

			// Adds a 0 to every row
			while(rowcol.size() < cols)
			{
				rowcol.add(0);
			}

			// Updates combined tiles into the board
			for (int j = 0; j < cols ; j++)
			{
				board[i][j] = rowcol.get(j);
			}
		}
	}

	/**
	 * Shifts all the tiles right, combines like tiles that collide.
	 */
	private void moveRight()
	{
		for (int i = 0; i < rows ; i++)
		{
			ArrayList<Integer> rowcol = new ArrayList<>();
			for (int j = 0; j < cols ; j++)
			{
				if(board[i][j] != 0)
				{
					rowcol.add(board[i][j]);
				}
			}
			for (int j = rowcol.size()-1; j > 0 ; j--)
			{
				int combine;
				if (rowcol.get(j).equals(rowcol.get(j - 1)))
				{
					combine = rowcol.get(j) + rowcol.get(j);
					rowcol.set(j, combine);
					rowcol.remove(j - 1);
					score = score + combine;
				}
			}
			while(rowcol.size() < cols)
			{
				rowcol.add(0,0);
			}
			for (int j = 0; j < cols ; j++)
			{
				board[i][j] = rowcol.get(j);
			}
		}
	}

	/**
	 * Sets board to previousBoard and score to previousScore
	 */
	public void undo()
	{
		score = previousScore;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				board[i][j] = previousBoard[i][j] ;
			}
		}
	}

	/**
	 * Returns true if the game is over, false otherwise.
	 * @return
	 */
	public boolean gameOver()
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols - 1; j++)
			{
				if (board[i][j] == 0 || board[i][j] == board[i][j + 1])
				{
					return false;
				}
			}
		}

		for (int i = 0; i < rows; i++)
		{
			for (int j = 1; j < cols; j++)
			{
				if (board[i][j] == 0 || board[i][j] == board[i][j - 1])
				{
					return false;
				}
			}
		}

		for (int j = 0; j < cols; j++)
		{
			for (int i = 0; i < rows - 1; i++)
			{
				if (board[i][j] == 0 || board[i][j] == board[i + 1][j])
				{
					return false;
				}
			}
		}

		for (int j = 0; j < cols; j++)
		{
			for (int i = 1; i < rows; i++)
			{
				if (board[i][j] == 0 || board[i][j] == board[i - 1][j])
				{
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Returns a String representation of this object.
	 */
	public String toString()
	{
		String rtn = "";

		for(int[] row : board)
		{
			rtn += "|";
			for(int num : row)
				if(num != 0)
				{
					String str = num + "";
					while(str.length() < 4)
						str = " " + str;
					rtn += str;
				}
				else
					rtn += "    ";
			rtn += "|\n";
		}

		rtn += "Score: " + score + "\n";

		return rtn;
	}
}