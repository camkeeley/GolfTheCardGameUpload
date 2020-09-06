import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represent the hand of cards held by a player
 * @author jburge
 *
 */
public class Hand {
	
	private ArrayList<PlayingCard> cards;
	private Random randomGenerator = new Random();

	/**
	 * Creates an empty hand of cards
	 */
	public Hand() {
		super();
		cards = new ArrayList<PlayingCard>();
	}
	
	/**
	 * Returns how many cards are in the hand
	 * @return number of cards held
	 */
	public int numCards() 
	{
		return cards.size();
	}
	
	/**
	 * Adds a new card to the hand and sorts it
	 * @param newcard - card being added
	 */
	public void addCard(PlayingCard newcard)
	{
		cards.add(newcard);
//		Collections.sort(cards);
	}
	

	public void addCards(ArrayList<PlayingCard> newCards)
	{
		cards.addAll(newCards);

	}
	

	
	public void replaceCard(PlayingCard replace, PlayingCard drawn)
	{
		cards.remove(replace);
		cards.add(drawn);

	}
	
	public PlayingCard highestVisible()
	{
		ArrayList<PlayingCard> allCards = new ArrayList<PlayingCard>();
		allCards.addAll(cards);
		Collections.sort(allCards);
		return allCards.get(allCards.size()-1);
	}

	/**
	 * Gets the cards in the hand that haven't been pulled into a meld
	 * @return the list of cards
	 */
	public ArrayList<PlayingCard> getCards() {
		return cards;
	}
	
	public int scoreHand()
	{
		int score = 0;
		for (PlayingCard card: cards)
		{
			score += card.value();
		}
		return score;
	}
	/**
	 * Choose a random card
	 * @return the value of the card found
	 */
	public PlayingCard randomChoice()
	{
		int choice = randomGenerator.nextInt(cards.size());
		return cards.get(choice);
	}
	
	/**
	 * Returns a string representation of the hand so we can print it out.
	 */
	public String toString()
	{
		String allCards = "";
		for (PlayingCard card : cards)
		{
			allCards += card.toString() + "\n";
		}
//		allCards += "Current score = " + this.scoreHand();
		return allCards;
	}
	

}
