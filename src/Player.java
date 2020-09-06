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
	
	/*
	 * Get the player's name
	 * @return the player's name
	 */
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
	 * @param drawn the card being added
	 * @param replace the card being replaced
	 */
	public void replaceCard(PlayingCard replace, PlayingCard drawn)
	{
		myHand.replaceCard(replace, drawn);
	}
	
	/*
	 * Get the full hand
	 * @return the cards in the hand as an ArrayList<PlayCard>
	 */
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
	
	/*
	 * Calculate the score of the player's hand
	 * @return the score of the player's hand
	 */
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
	
	/*
	 * Make a best choice about whether to replace a card, and if so which card
	 * to replace. 
	 * @param possibleReplacement - the PlayingCard which is being considered as 
	 * a replacement for a card in the hand.
	 * @return either the card in the hand which should be replaced, or return null
	 * to indicate that no card should be replaced.
	 */
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
