# Customer Statement Processor
Spring Boot Web project met embedded server. Getest in Eclipse en m.b.v. Postman

De logic wordt getest in de *Analyzer* class, die de *Record*'s checkt op unieke transactie-references, checken end-balance, correct gebruik van + en - bij mutaties. 
*RestAPI* class gebruikt logic in Analyzer om alle JSON POST input te verwerken op basis van de conditions, en passende http responses/messages terug te sturen eveneens als JSON.  Input path is "/in", succesvolle verwerkte transacties worden opgehaald met "/get".

*AnalyzerTest* test de logic op functionaliteit en *RestAPITest* test de endpoints en het produceren van JSON en response objects.

*JPA database support toegevoegd met basic CRUD features. Sql scriptje in folder src/main/java, wijzig username en password in application.properties en de eventueel de jdbc url naar de gebruikte database. 
