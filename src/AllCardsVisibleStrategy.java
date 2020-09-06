import java.util.ArrayList;

public class AllCardsVisibleStrategy implements Strategy
{

	/*
	 * makes all cards visible
	 */
	public void setVisibility(ArrayList<PlayingCard> hand)
	{
		for(PlayingCard card: hand)
		{
			card.setVisible(true);
		}
	}
}
