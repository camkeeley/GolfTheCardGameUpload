import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;

/**
 * Represent the hand of cards held by a player
 * @author jburge
 *
 */
public class Hand {
	//re-named "cards" to hand to indicate more clearly that the variable represents a hand of cards
	private ArrayList<PlayingCard> hand;
	
	
	private Random randomGenerator = new Random();

	/**
	 * Creates an empty hand of cards
	 */
	public Hand() {
		super();
		hand = new ArrayList<PlayingCard>();
	}
	
	/**
	 * Returns how many cards are in the hand
	 * @return number of cards held
	 */
	public int numCards() 
	{
		return hand.size();
	}
	
	/**
	 * Adds a new card to the hand
	 * @param newcard - card being added
	 */
	public void addCard(PlayingCard newcard)
	{
		hand.add(newcard);
//		Collections.sort(cards);
	}
	
	/*
	 * Adds all passed-in cards to the hand
	 * @param newCards - new cards being passed in to the hand
	 */
	public void addCards(ArrayList<PlayingCard> newCards)
	{
		hand.addAll(newCards);

	}
	

	/*
	 * Remove a card from the hand and then add a card to the hand. Updated to keep track
	 * of the index of the card being removed, and to add the new card to that index in the
	 * hand ArrayList. Also gives the new card the visibility setting of the old card
	 * @param replace - the card to be replaced
	 * @param drawn - the card to add to the hand
	 */
	public void replaceCard(PlayingCard replace, PlayingCard drawn)
	{
		int indexReplaced;
		int indexFinder = 0;
		
		for(PlayingCard card: hand)
		{
			if(card == replace)
			{
				indexReplaced = indexFinder;
				break;
			}
			indexFinder++;

		}
		
		if(!replace.getVisible())
		{
			drawn.setVisible(false);
			
		}
		else
		{
			drawn.setVisible(true);
		}
		
		replace.setVisible(true);

		
		hand.remove(replace);
		hand.add(indexFinder, drawn);

	}
	
	/*
	 * Gets the highest visible card in the hand
	 * @return the highest visible card
	 */
	public PlayingCard highestVisible()
	{
		/*
		PlayingCard maxCard = null;
		for(PlayingCard card: hand)
		{
			if(card.value() >= maxCard.value() && card.getVisible())
			{
				maxCard = card;
			}
		}
		return maxCard;
		*/
		ArrayList<PlayingCard> allCards = new ArrayList<PlayingCard>();
		allCards.addAll(hand);
		Collections.sort(allCards);
		return allCards.get(allCards.size()-1);
		
	}

	/**
	 * Gets the cards in the hand that haven't been pulled into a meld
	 * @return the list of cards
	 */
	public ArrayList<PlayingCard> getCards() {
		return hand;
	}
	
	/*
	 * Adds up the values of the cards in the hands to find the hand's score
	 * @return the score of the hand
	 */
	public int scoreHand()
	{
		int score = 0;
		for (PlayingCard card: hand)
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
		int choice = randomGenerator.nextInt(hand.size());
		return hand.get(choice);
	}
	
	/**
	 * Returns a string representation of the hand so we can print it out.
	 */
	public String toString()
	{
		String allCards = "";
		for (PlayingCard card : hand)
		{
			allCards += card.toString() + "\n";
		}
//		allCards += "Current score = " + this.scoreHand();
		return allCards;
	}
	

}
