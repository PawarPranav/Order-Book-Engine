package orderbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class TestMarket
{
    private OrderBook market;

    @BeforeEach
    public void initMarket()
    {
        market = new OrderBook("Test");
    }

    /*******************************************
     *
     *   addBid Tests
     *
     *******************************************/

    @Test
    @DisplayName("Add new bid should correctly add new bid")
    public void addNewBidShouldCorrectlyAddNewBid()
    {
        assertTrue(market.getBidMap().isEmpty());
        market.addBid(12.0, 1);
        assertTrue(market.getBidMap().containsKey(12.0));
    }

    @Test
    @DisplayName("Add duplicate bid price should correctly add new bid")
    public void addDuplicateBidPriceShouldCorrectlyAddNewBid()
    {
        assertTrue(market.getBidMap().isEmpty());
        market.addBid(12.0, 1);
        market.addBid(12.0, 2);

        // Checks to see if corresponding elements have same quantities.
        assertEquals(1, market.getBidMap().get(12.0).get(0).getQuantity());
        assertEquals(2, market.getBidMap().get(12.0).get(1).getQuantity());
    }

    /*******************************************
     *
     *   addOffer Tests
     *
     *******************************************/

    @Test
    @DisplayName("Add new offer should correctly add new offer")
    public void addNewOfferShouldCorrectlyAddNewOffer()
    {
        assertTrue(market.getOfferMap().isEmpty());
        market.addOffer(12.0, 1);
        assertTrue(market.getOfferMap().containsKey(12.0));
    }

    @Test
    @DisplayName("Add duplicate offer price should correctly add new offer")
    public void addDuplicateOfferPriceShouldCorrectlyAddNewOffer()
    {
        assertTrue(market.getOfferMap().isEmpty());
        market.addOffer(12.0, 1);
        market.addOffer(12.0, 2);

        // Checks to see if corresponding elements have same quantities.
        assertEquals(1, market.getOfferMap().get(12.0).get(0).getQuantity());
        assertEquals(2, market.getOfferMap().get(12.0).get(1).getQuantity());
    }

    /*******************************************
     *
     *   getBucket Test
     *
     *******************************************/

    @Test
    @DisplayName("Get bucket should return correct list")
    public void getBucketShouldReturnCorrectList()
    {
        assertTrue(market.getOfferMap().isEmpty());
        market.addOffer(12.0, 1);
        market.addOffer(12.0, 2);

        // Checks to see if corresponding bucket elements have same quantities.
        assertEquals(1, market.getBucket(market.getOfferMap(), 12.0).get(0).getQuantity());
        assertEquals(2, market.getBucket(market.getOfferMap(), 12.0).get(1).getQuantity());
    }

    /*******************************************
     *
     *   matchOrders Tests
     *
     *******************************************/

    @Test
    @DisplayName("Bid quantity should correctly decrement when greater than offer quantity")
    public void BidQuantityShouldCorrectlyDecrementWhenGreaterThanOfferQuantity()
    {
        market.addOffer(12.0, 6);
        market.addBid(12.0, 9);
        market.matchOrders();
        assertEquals(3, market.getBidMap().get(12.0).get(0).getQuantity());  // Bid correctly decremented
        assertTrue(market.getOfferMap().get(12.0).isEmpty());  // Offer correctly closed
    }

    @Test
    @DisplayName("Offer quantity should correctly decrement when greater than bid quantity")
    public void OfferQuantityShouldCorrectlyDecrementWhenGreaterThanBidQuantity()
    {
        market.addBid(12.0, 5);
        market.addOffer(12.0, 10);
        market.matchOrders();
        assertEquals(5, market.getOfferMap().get(12.0).get(0).getQuantity());  // Offer correctly decremented
        assertTrue(market.getBidMap().get(12.0).isEmpty());  // Bid correctly closed
    }

    @Test
    @DisplayName("Both quantities equal should correctly remove both")
    public void BothQuantitiesEqualShouldCorrectlyRemoveBoth()
    {
        market.addBid(12.0, 5);
        market.addOffer(12.0, 5);
        market.matchOrders();
        assertTrue(market.getBidMap().get(12.0).isEmpty());   // Bid correctly closed
        assertTrue(market.getOfferMap().get(12.0).isEmpty()); // Offer correctly closed
    }

    @Test
    @DisplayName("Bid with value and no offers should stay the same")
    public void BidWithValueAndNoOffersShouldStayTheSame()
    {
        market.addBid(12.0, 5);
        market.matchOrders();
        assertEquals(5, market.getBidMap().get(12.0).get(0).getQuantity());   // Bid still has same value
        assertNull(market.getOfferMap().get(12.0)); // Offer still null
    }

    @Test
    @DisplayName("Offer with value and no bids should stay the same")
    public void OfferWithValueAndNoBidsShouldStayTheSame()
    {
        market.addOffer(12.0, 5);
        market.matchOrders();
        assertEquals(5, market.getOfferMap().get(12.0).get(0).getQuantity());   // Offer still has same value
        assertNull(market.getBidMap().get(12.0)); // Bid still null
    }

    @Test
    @DisplayName("Offer price higher than bid price should stay the same")
    public void OfferPriceHigherThanBidPriceShouldStayTheSame()
    {
        market.addOffer(12.0, 7);
        market.addBid(6.0, 5);
        market.matchOrders();
        assertEquals(7, market.getOfferMap().get(12.0).get(0).getQuantity());   // Offer still has same value
        assertEquals(5, market.getBidMap().get(6.0).get(0).getQuantity());   // Bid still has same value
    }
}
