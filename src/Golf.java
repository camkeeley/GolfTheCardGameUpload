import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * This is the main class for a Golf game. See https://www.pagat.com/draw/golf.html
 * @author jburge
 *
 */
public class Golf {

	public static final int START_CARDS = 4;
	public static final int DECK_SIZE = PlayingCard.MAX;   //set to PlayingCard.MAX for a full deck
	public static final int DRAW = 1;
	public static final int PICK_DISCARD = 2;
	public static final int KNOCK = 3;
	public static final int UNDO_MOVE = 4;
	public static final int CHANGE_STRATEGY = 5;



	private boolean humanKnock = false;
	private boolean computerKnock = false;


	/**
	 * Asks for a yes/no response and repeats the question until valid input is received
	 * @param prompt - the question
	 * @return - true if yes
	 */
	
	private  boolean requestYesNo(String prompt)
	{
		boolean isYes = false;
		Scanner in = new Scanner(System.in);
		boolean invalid = true;

		while (invalid)
		{

			System.out.print(prompt);
			String answer = in.nextLine();
			if (answer.equalsIgnoreCase("Y"))
			{
				isYes = true;
				invalid = false;
			}
			else if (answer.equalsIgnoreCase("N"))
			{
				isYes = false;
				invalid = false;
			}
			else
			{
				System.out.println("\nPlease enter Y or N");
			}
		}
		return isYes;

	}


	/**
	 * Our main menu for the player options. 
	 * @return the valid menu choice
	 */
	private  int playerMenu()
	{
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		int selection = 1;
		while (!valid)
		{
			System.out.println("Choose the player option: ");
			System.out.println("1: Draw from Deck");
			System.out.println("2: Take Discard");
			System.out.println("3: Knock");
			System.out.println("4: Undo the last move");
			System.out.println("5: Change the opponent's strategy");
			System.out.print("> ");
			try {
				selection = in.nextInt();

				in.nextLine();
				if ((selection > 0) && (selection <= 5))
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter a number between 1 and 3");
			}
		}
		return selection;
	}


	/**
	 * Our main menu for the player options. 
	 * @return the valid menu choice
	 */
	private  int strategyMenu()
	{
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		int selection = 1;
		while (!valid)
		{
			System.out.println("Choose the strategy option: ");
			System.out.println("1: Show None");
			System.out.println("2: Show Two");
			System.out.println("3: Show All");
			System.out.print("> ");
			try {
				selection = in.nextInt();

				in.nextLine();
				if ((selection > 0) && (selection <= 3))
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter a number between 1 and 3");
			}
		}
		return selection;
	}

	/**
	 * Draw a new card from the deck
	 * @param message - the message to print explaining why we are drawing
	 * @param player - the player drawing a card
	 * @param deck - the deck
	 */
	public  PlayingCard drawCards(String message, Deck deck)
	{
		if (deck.size() == 0)
		{
			System.out.println("No more cards to draw.");
			return null;
		}
		System.out.println(message);
		PlayingCard card = deck.draw();
		return card;
	}

	/*
	 * Allow player to decide which card they want to discard from the cards they have, 
	 * and if they are allowed to pass, they can discard the drawn card as well.
	 * @param prompt- Ask the player to select a card
	 * @param cardOptions - the different cards the player can discard
	 * @param canPass - whether the player is allowed to keep all their original cards
	 * @return the PlayingCard which was discarded.
	 */
	private PlayingCard makeCardChoice(String prompt, ArrayList<PlayingCard> cardOptions, boolean canPass)
	{
		if (cardOptions.size() == 0)
		{
			return null;
		}
		int cardWanted = 1;
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		int selection = 1;
		int max = canPass ? cardOptions.size()+1 : cardOptions.size();
		PlayingCard choice = null;
		System.out.println(prompt);
		while (!valid)
		{
			for (PlayingCard card : cardOptions)
			{

				System.out.println("" + cardWanted + ": " + card.toString() );
				cardWanted++;
			}
			if (canPass)
			{
				System.out.println("" + cardWanted + ": Discard");
			}
			System.out.print("> ");
			try {
				selection = in.nextInt();

				in.nextLine();
				if ((selection > 0) && (selection <= cardOptions.size()))
				{
					valid = true;
					choice = cardOptions.get(selection-1);
					return choice;
				}
				else if (canPass && (selection == cardOptions.size()+1))
				{
					valid = true;
					return null; //return null if we aren't replacing a card
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter a number between 1 and " + max);
				cardWanted = 1;
			}
		}
		return null;
	}

	/**
	 * Checks to see who has won. Prints out the winner and returns true if the game is over.
	 * @param human - human player
	 * @param computer - computer player
	 * @return true if we have a winner
	 */
	public  void checkWinner(Player human, Player computer)
	{
		int humanScore = human.calculateScore();
		int computerScore = computer.calculateScore();
		System.out.println("\nHuman score = " + humanScore);
		System.out.println("Computer score = " + computerScore);
		if (humanScore < computerScore)
		{
			System.out.println("Human wins!");
		}
		else if (computerScore < humanScore)
		{
			System.out.println("Computer wins!");
		}
		else
		{
			System.out.println("It's a tie!");
		}

	}

	public void playGame() {
		Deck deck;

		Player human = new Player("Human");
		Player computer = new Player("Computer");
		DiscardPile discards = new DiscardPile();


		Scanner in = new Scanner(System.in);
		boolean isWinner = false;
		System.out.println("Welcome to Golf!");

		deck = new Deck(DECK_SIZE);

		//Deal initial cards to each player
		for (int i=0; i < Golf.START_CARDS; i++)
		{
			human.draw(deck);
			computer.draw(deck);
		}


		boolean playerTurn = true;
		System.out.println(human);

		discards.addCard(drawCards("", deck ));
		System.out.println("Discarded: " + discards.getTop());

		//Main loop
		while(!isWinner)
		{
			while (playerTurn)
			{
				System.out.println("\nHuman's Turn!");
				int choice = playerMenu();
				switch(choice) {
				case DRAW:
					PlayingCard drawn = drawCards("Drawing card from deck", deck);
					if (drawn != null)
					{
						System.out.println("Drew " + drawn.toString());
						PlayingCard replaced = makeCardChoice("Select a card to replace: ", human.getHand(), true);
						if (replaced == null)
						{
							discards.addCard(drawn);
							System.out.println("Discarded: " + discards.getTop());
						}
						else
						{
							human.replaceCard(replaced, drawn);
							discards.addCard(replaced);
							System.out.println("Discarded: " + discards.getTop());
						}
						playerTurn = false;
					}
					break;
				case PICK_DISCARD:
					drawn = discards.draw();
					System.out.println("\nDrew " + drawn.toString());
					PlayingCard replaced = makeCardChoice("\nSelect a card to replace: ", human.getHand(), false);

					discards.addCard(replaced);
					System.out.println("Discarded: " + discards.getTop());

					human.replaceCard(replaced, drawn);

					playerTurn = false;
					break;
				case KNOCK:
					System.out.println("Player knocking");
					humanKnock = true;
					playerTurn = false;
					break;
				case UNDO_MOVE:
					System.out.println("Undo is not implemented!");
					break;
				case CHANGE_STRATEGY:
					System.out.println("Strategy change is not implemented!");
					break;	
				}
				System.out.println(human);
			}

			//If we've run out of cards to draw or if the human has knocked
			if ((deck.size() == 0) || computerKnock)
			{
				checkWinner(human, computer);
				isWinner = true;
			}
			else {
				//Computer's turn!

				System.out.println("\nComputer's Turn\n");
				if (computer.calculateScore() <= 20)
				{
					computerKnock = true;
					System.out.println("Computer is knocking");
				}
				else
				{
					PlayingCard drawn = drawCards("Drawing card from deck", deck);
					System.out.println("Drew " + drawn.toString());
					PlayingCard replaced = computer.bestChoice(drawn);
					if (replaced != null)
					{
						computer.replaceCard(replaced, drawn);
					}
					else
					{
						replaced = drawn;
					}
					//Draw a card from the deck
					//Randomly choose a card to replace with it
					//Add card to discard

					discards.addCard(replaced);
					System.out.println("Discarded: " + discards.getTop());

					System.out.println(computer);
					playerTurn = true;

					//If we've run out of cards to draw or if the human has knocked
					if ((deck.size() == 0) || humanKnock)
					{
						checkWinner(human, computer);
						isWinner = true;
					}
				}
			}
		}
	}

	/**
	 * Main Program
	 * @param args
	 */
	public static void main(String[] args) {
		Golf game = new Golf();
		game.playGame();

	}

}
