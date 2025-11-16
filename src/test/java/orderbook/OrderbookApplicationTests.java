package orderbook;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderbookApplicationTests {

	private OrderBook market;

	@BeforeEach
	public void initMarket()
	{
		market = new OrderBook("Test");
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void addNewBidShouldCorrectlyAddNewBid2()
	{
		assertTrue(market.getBidMap().isEmpty());
		market.addBid(12.0, 1);
		assertTrue(market.getBidMap().containsKey(12.0));
	}

}
