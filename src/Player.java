import java.util.ArrayList;

/**
 * Defines the player class. This includes the hand, any books placed on the table, and a name.
 * @author jburge
 *
 */
public class Player {

	private String name;
	private Hand myHand;


	/**
	 * Creates a Player and gives them a name
	 * @param name
	 */
	public Player(String name)
	{
		this.name = name;
		myHand = new Hand();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Returns the number of cards held in the player's hand
	 * @return number of cards
	 */
	public int numCards()
	{
		return myHand.numCards();
	}
	

	
	/**
	 * Draws a card from the deck and places it in the player's hand
	 * @param deck the deck of cards
	 * @return the card dealt
	 */
	public PlayingCard draw(Deck deck)
	{
		PlayingCard cardDealt = deck.draw();
		myHand.addCard(cardDealt);
		return cardDealt;
	}
	
	/**
	 * Puts a card back in the player's hand
	 * @param card the card being added
	 */
	public void replaceCard(PlayingCard replace, PlayingCard drawn)
	{
		myHand.replaceCard(replace, drawn);
	}
	
	public ArrayList<PlayingCard> getHand() {
		return myHand.getCards();
	}


	/**
	 * Used to add a list of cards to the player's hand
	 * @param newCards - cards being added
	 */
	public void addCards(ArrayList<PlayingCard> newCards)
	{
		myHand.addCards(newCards);
	}
	
	
	public int calculateScore()
	{
		return myHand.scoreHand();
	}
	
	/**
	 * Gets a random card from the hand
	 * @return the value of the random card
	 */
	public PlayingCard randomChoice()
	{
		return myHand.randomChoice();
	}
	
	public PlayingCard bestChoice(PlayingCard possibleReplacement)
	{
		PlayingCard highest = myHand.highestVisible();
		if (highest != null)
		{
			if (possibleReplacement.value() < highest.value())
			{
				return highest; //will want to replace
			}
			else
			{
				return null; //will not want to replace
			}
		}
		else {
			if (possibleReplacement.value() < 8)
			{
				return myHand.randomChoice(); //replace a card at random
			}
			else
			{
				return null; //don't replace
			}
		}
	}

	
	/**
	 * Used to create a string representation of the player so it can be printed.
	 */
	public String toString()
	{
		String player = "\n" + name + ":\n";
		player += myHand.toString();
		return player;
	}
	
}
