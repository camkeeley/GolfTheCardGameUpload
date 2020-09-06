import java.util.ArrayList;

public class NoCardsVisibleStrategy implements Strategy
{
	/*
	 * Makes all cards invisible
	 */
	public void setVisibility(ArrayList<PlayingCard> hand) 
	{
		for(PlayingCard card: hand)
		{
			card.setVisible(false);
		}
	}

		
}
