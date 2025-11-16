# Order Book Engine

A simple Order Book system built using **Java**, **Spring Boot**, and
**Maven**.\
This project demonstrates how a basic trade engine works by handling
bids, offers, and market items.

## How to Build

``` bash
mvn package
```

Run the application:

``` bash
java -jar target/orderbook-0.0.1-SNAPSHOT.jar
```

## API Usage (via Curl)

### Add Market Item

``` bash
curl http://localhost:8090/market/add/item/diner0
```

### Get All Market Items

``` bash
curl http://localhost:8090/market/list/get
```

### Add Bid

``` bash
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"100", "qty":"20"}' -X POST http://localhost:8090/market/bid/add
```

### Add Offer

``` bash
curl -H "Content-Type: application/json" -d '{"name":"diner0", "price":"100", "qty":"10"}' -X POST http://localhost:8090/market/offer/add
```

### Get Bid List

``` bash
curl -H "Content-Type: application/json" -d '{"name":"diner0"}' -X POST http://localhost:8090/market/bid/get
```

### Get Offer List

``` bash
curl -H "Content-Type: application/json" -d '{"name":"diner0"}' -X POST http://localhost:8090/market/offer/get
```

## Requirements

-   Java 17+
-   Maven
-   Spring Boot
-   Curl (for testing)

## References

-   https://en.wikipedia.org/wiki/Order_book\_(trading)
-   https://www.investopedia.com/terms/o/order-book.asp
