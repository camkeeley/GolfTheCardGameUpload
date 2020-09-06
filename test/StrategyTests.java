import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;



public class StrategyTests 
{
	private Player player;
	
	@Before
	public void setUp() throws Exception
	{
		player = new Player(null);
	}
	
	public void testNoCardsVisible()
	{
		Strategy testNoCardsVisibleStrategy = new NoCardsVisibleStrategy();
	}
	
	public void testTwoCardsVisible()
	{
		Strategy testNoCardsVisibleStrategy = new TwoCardsVisibleStrategy();

	}
	public void testAllCardsVisible()
	{
		Strategy testNoCardsVisibleStrategy = new AllCardsVisibleStrategy();

	}
}
