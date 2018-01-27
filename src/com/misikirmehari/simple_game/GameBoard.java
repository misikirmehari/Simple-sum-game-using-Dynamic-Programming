package com.misikirmehari.simple_game;

/**
 * <p>
 * A simple game implemented using dynamic programming where a list of positive
 * integers Vj through Vk is given and two players take turns picking either the
 * first or the last value in the list and removing that value from the list.The
 * goal for each player is to pick items whose values add up to the maximum
 * possible value.
 * </p>
 *
 * @version cs4050 - Fall2017 - Project 4
 * @author misikir mehari
 *
 */

public class GameBoard {

	/**
	 * Move options a player can make
	 */
	private int move_1, move_2, move_3;

	/**
	 * Contain an optimal move a player makes by going down or left
	 */
	private char[][] Move;

	/**
	 * 2D-Array to create dynamic table chart
	 */
	private int Table[][];

	/**
	 * Array containing positive integers
	 */
	private int[] List;

	/**
	 * Constructs a specified GameBoard
	 *
	 * @param ListOfNums
	 *            Array containing positive integers
	 * @param n
	 *            Size of the Array that contains the positive Integers
	 */

	public GameBoard(int[] ListOfNums, int n) {

		this.List = ListOfNums;
		this.Table = new int[n][n];
		this.Move = new char[n][n];

	}

	/**
	 *
	 * @param j
	 * @param k
	 * @return the next optimal move
	 */
	public char getMove(int j, int k) {
		return Move[j][k];
	}

	/**
	 *
	 * @return a dynamic table with values populated using list of positive
	 *         integers
	 */
	public int[][] DynamicTable() {

		for (int k = 0; k < List.length; k++) {

			for (int i = 0, j = k; j < List.length; i++, j++) {

				// Player 1 chooses i and Player 2 chooses i+1
				if ((i + 2) <= j)
					move_1 = Table[i + 2][j];
				else
					move_1 = 0;

				// Player 1 chooses i and Player 2 chooses j or
				// Player 1 chooses j and Player 2 chooses i

				if ((i + 1) <= (j - 1))
					move_2 = Table[i + 1][j - 1];
				else
					move_2 = 0;

				// Player 1 chooses j and Player 2 chooses j-1
				if (i <= (j - 2))
					move_3 = Table[i][j - 2];
				else
					move_3 = 0;

				if (List[i] + Math.min(move_1, move_2) > List[j] + Math.min(move_2, move_3)) {
					// Optimal move:Down
					Table[i][j] = List[i] + Math.min(move_1, move_2);
					Move[i][j] = 'D';

				} else {
					// Optimal move: Left
					Table[i][j] = List[j] + Math.min(move_2, move_3);
					Move[i][j] = 'L';
				}
				if (i == j){
					Move[i][j] = 0;
				}
			}
		}

		return Table;
	}

}
