# crypto-recommendation-service-app

### Description

This is crypto recommendation service for those who want to invest their salaries on crypto.
This service contains few endpoints that may help you to make a decision.

Application read .csv files with the following name format CRYPTO_NAME_values.csv (e.g. BTC_values.csv) from the project
resource folder(src/main/resources/file).   
You may use next endpoints to get needed information:  

-*/cryptos/CRYPTO_NAME/min* to get the minimum price for requested crypto name    
-*/cryptos/CRYPTO_NAME/max* to get the maximum price for requested crypto name   
-*/cryptos/CRYPTO_NAME/oldest* to get the oldest price for requested crypto name   
-*/cryptos/CRYPTO_NAME/newest* to get the newest price for requested crypto name   
-*/cryptos/sorted* to get the crypto with normalised range(i.e. (max-min)/min) in descending order    
-*/cryptos/DATE/highest* to get the crypto with highest normalized range for a
specific day  

### Running the application locally

To run this application locally you need to have IDE installed. 
Then execute the `main` method from `com.epam.cryptorecommendationservice.CryptoRecommendationServiceApp` and
go to http://localhost:8080/cryptos

### Technologies

Java 11   
Spring Boot 2.7.10    
Spring Framework 5.3.26     
Mockito 3.12.4   