package orderbook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class TestOrder
{
    private Order order;

    @Test
    @DisplayName("Get price should return correct price")
    public void getPriceShouldReturnCorrectPrice()
    {
        order = new Order(12.0, 1);
        assertEquals(12.0, order.getPrice(), .00000000000001);
    }

    @Test
    @DisplayName("Set price should correctly set price")
    public void setPriceShouldCorrectlySetPrice()
    {
        order = new Order(12.0, 1);
        order.setPrice(15.0);
        assertEquals(15.0, order.getPrice(), .00000000000001);
    }

    @Test
    @DisplayName("Get quantity should return correct quantity")
    public void getQuantityShouldReturnCorrectQuantity()
    {
        order = new Order(12.0, 1);
        assertEquals(1, order.getQuantity());
    }

    @Test
    @DisplayName("Set quantity should correctly set quantity")
    public void setQuantityShouldCorrectlySetQuantity()
    {
        order = new Order(12.0, 1);
        order.setQuantity(5);
        assertEquals(5, order.getQuantity());
    }

    @Test
    @DisplayName("toString should return correct string format")
    public void toStringShouldReturnCorrectStringFormat()
    {
        order = new Order(12.0, 1);
        assertEquals("12.0 1", order.toString());
    }
}
