import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Defines the discarded cards
 * @author jburge
 *
 */
public class DiscardPile {
	private Stack<PlayingCard> discardedCards; 

	/**
	 * Create an empty pile
	 * @param book
	 */
	public DiscardPile() {
		discardedCards = new Stack<PlayingCard>();
	}

	/**
	 * Gets the active pile
	 * @return
	 */
	public ArrayList<PlayingCard>getDiscards()
	{
		ArrayList<PlayingCard> pile = new ArrayList(Arrays.asList(this.discardedCards.toArray()));
		Collections.reverse(pile);
		return pile;
	}

	/*
	 * Adds a card to the top of the pile
	 */
	public void addCard(PlayingCard card) {
		this.discardedCards.push(card);

	}

	/**
	 * Returns the top card without removing it from the discard pile
	 * @return
	 */
	public PlayingCard getTop()
	{
		return discardedCards.peek();
	}

	/**
	 * Gets the top card and removes it from the discard pile
	 * @return
	 */
	public PlayingCard draw()
	{
		return discardedCards.pop();
	}
	/**
	 * Used to print out the pile. Java iterates over stacks in the wrong order so we
	 * have to do it manually. Kind of negates the whole value of using Stack...
	 */
	public String toString()
	{
		String pileInfo = "";
		pileInfo += "Top Discard = " + discardedCards.peek().toString();
		pileInfo += "\n";
		return pileInfo;
	}
}
