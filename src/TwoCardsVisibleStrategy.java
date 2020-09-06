import java.util.ArrayList;

public class TwoCardsVisibleStrategy implements Strategy
{

	/*
	 * sets the first two cards in the hand to visible,
	 * the rest invisible
	 */
	public void setVisibility(ArrayList<PlayingCard> hand)
	{
		for(int i = 0; i <= 1; i++)
		{
			hand.get(i).setVisible(true);
		}
		
		for(int i = 2; i < hand.size(); i++)
		{
			hand.get(i).setVisible(false);
		}
	}
}
