package com.misikirmehari.simple_game;

import java.util.Scanner;

/**
* A Simple Game Class
*
* @version cs4050 - Fall2017 - Project 4
* @author misikir mehari
*/
public class SimpleGame {

  /**
  * Global Variables
  */
  static int PlayerOneTotal = 0;
  static int PlayerTwoTotal = 0;
  static Scanner input = new Scanner(System.in);
  static GameBoard simplegame;
  static int PlayerMove = 0;
  static int[] ListOfNums;
  public static final String RESET = "\033[0m";
  public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";

  /**
  * Starts the Simple Game
  *
  * @param args
  */
  public static void main(String[] args) {

    // Gets input from user and initializes a List and dynamic table
    initialize();

    // Player selects either first or last element in the list
    int first = 0;
    int last = ListOfNums.length - 1;
    printTable(first , last);
    // Play
    PlayGame(first, last, ListOfNums, PlayerMove, simplegame);

  }


  /**
  * Initializes an array with values from user , Displays the values entered
  * and creates and Displays dynamic table using these values
  */

  public static void initialize() {

    System.out.println("|~~~~~~~~~~~~~~ Simple Sum Game ~~~~~~~~~~~~~~~~~~~~~~~~~|");
    System.out.print("Enter the number of elements in the List: ");
    int userInput = input.nextInt();
    ListOfNums = new int[userInput];

    for (int i = 0; i < ListOfNums.length; i++) {

      System.out.print("Element[" + (i + 1) + "] = ");
      ListOfNums[i] = input.nextInt();

    }

    System.out.print("\nThe Values Entered are: ");
    for (int i : ListOfNums) {
      System.out.print(i + " ");
    }
    System.out.print("\n");
    System.out.println("In the following chart: L means move left and D means move Down");

    simplegame = new GameBoard(ListOfNums, userInput);
   // simplegame.PrintTable(simplegame.DynamicTable());
  }


  /**
   * Prints the dynamic table 
   * @param first
   * @param last
   */
  private static void printTable(int first , int last){
    
	  int Table[][] = simplegame.DynamicTable();

	    System.out.println("==========================================================");
		System.out.println("=============== DYNAMIC TABLE (2D CHART) =================");
		System.out.println("==========================================================");
		for (int i : ListOfNums) {
			System.out.print(i + "\t");
		}
		System.out.print("\n");
		System.out.println("==========================================================");

		    
      for(int i=0; i< Table.length;i++){
          System.out.println();
          for(int j = 0; j < Table.length; j++){
      
              if(simplegame.getMove(i, j) != 0)
                  if(i == first && j == last)
                	 System.out.print(RED_BACKGROUND_BRIGHT + Table[i][j] + "("+ Character.toString(simplegame.getMove(i, j)) + ")"+ RESET +"\t");
                  else
                     
                	  System.out.print(Table[i][j] + "("+ Character.toString(simplegame.getMove(i, j)) +")"+ "\t");
             
              else if (simplegame.DynamicTable()[i][j] != 0) {
                  
            	  if(i == first && j == last)
                      System.out.print(RED_BACKGROUND_BRIGHT+ Table[i][j]+RESET+"\t");
                  else
                      System.out.print(Table[i][j] + "\t");
              }
              else{
                  System.out.print("0\t");
              }
          }
      }
      System.out.println();
  	  System.out.println("==========================================================");
  }
  
   /**
  *
  * @param first
  *            the first item in the list
  * @param last
  *            the last item in the list
  * @param ListOfNums
  *            A List containing positive integers
  * @param PlayerMove
  *            A Player choice to pick the first or last item in the List
  * @param simplegame
  *            An instance of the Current Game
  */
  public static void PlayGame(int first, int last, int[] ListOfNums, int PlayerMove, GameBoard simplegame) {
    int count = 1;

    while (first <= last) {

      if (simplegame.getMove(first, last) == 'D') {
        PlayerMove = ListOfNums[first];
      }

      else if (simplegame.getMove(first, last) == ('L')) {
        PlayerMove = ListOfNums[last];

      }
      System.out.println("\n       Round " + count);
      // Get player choice
      System.out.println("You can choose either " + ListOfNums[first] + " or " + ListOfNums[last]);
      System.out.println("The Optimal Choice is : " + PlayerMove);
      System.out.print("Your Choice is : ");
      int userinput = input.nextInt();

      // Make sure only the first or last item is selected
      if (userinput != ListOfNums[first] && userinput != ListOfNums[last]) {
        System.out
        .println("Wrong Move! - Please choose either " + ListOfNums[first] + " or " + ListOfNums[last]);
        continue;
      }

      if (userinput == ListOfNums[first]) {
        PlayerOneTotal += ListOfNums[first++];
      }

      else {
        PlayerOneTotal += ListOfNums[last--];
      }

      if (simplegame.getMove(first, last) == 0) {
        PlayerTwoTotal += simplegame.DynamicTable()[first][last];
        break;
      }
      if (simplegame.getMove(first, last) == 'L') {
        System.out.println("Player Two has chosen: " + ListOfNums[last]);
        PlayerTwoTotal += ListOfNums[last--];

      }

      else if (simplegame.getMove(first, last) == 'D') {
        System.out.println("Player Two has chosen: " + ListOfNums[first]);
        PlayerTwoTotal += ListOfNums[first++];
      }

      else {
        System.out.println("Player Two has chosen: " + ListOfNums[first]);
        PlayerTwoTotal += ListOfNums[first];
      }

      // Current Scores after each round
      System.out.println("\n");
      System.out.print("==========================================\n");
      System.out.println("           After Round " + count);
      System.out.print("==========================================\n");
      System.out.println("Player One: " + PlayerOneTotal + " || " + "Player Two:" + PlayerTwoTotal + "\n");
      System.out.print("==========================================\n");
      count++;
      
      
      printTable(first , last);
    }

    //Player one
    if (PlayerOneTotal > PlayerTwoTotal) {
      System.out.println("\n");
      System.out.print("================ Game Over ==============\n");
      System.out.println("Final Score ");
      System.out.println("Player One: " + PlayerOneTotal + " || " + "Player Two: " + PlayerTwoTotal);
      System.out.println("Winner : Player One ");
      System.out.print("=========================================\n");
    }

    // Quit or play again if its a tie
    else if (PlayerOneTotal == PlayerTwoTotal) {
      System.out.println("\n");
      System.out.println("========== Game ended as a Tie! ==========");
      System.out.println("Player One: " + PlayerOneTotal + " || " + "Player Two: " + PlayerTwoTotal);
      System.out.print("=========================================\n");
      System.out.print("Do you want to play another Game ? Press Y/N: ");
      String choice = input.next();

      if (choice.equalsIgnoreCase("Y")){
        System.out.println("\n\n");
        main(null);
      }
      else if (choice.equalsIgnoreCase("N")){
        System.out.println("Good Bye !!!");
        System.exit(0);
      }
      else{
        System.out.print("Invalid Option! ... System Quiting ... Good Bye !");
        System.exit(0);
      }


    }

    // Player Two Wins
    else {
      System.out.println("\n");
      System.out.print("================ Game Over ==============\n");
      System.out.println("Final Score ");
      System.out.println("Player One: " + PlayerOneTotal + " || " + "Player Two: " + PlayerTwoTotal);
      System.out.println("Winner : Player Two ");
      System.out.print("=========================================\n");
    }


  }

}
