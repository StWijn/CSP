# Customer Statement Processor
Spring Boot Web project met embedded server. 

De logic wordt getest in de *Analyzer* class, die de *Record*'s checkt op unieke transactie-references, checken end-balance, correct gebruik van + en - bij mutaties. 
*RestAPI* class gebruikt logic in Analyzer en produceert JSON op basis van de conditions. Input path is "/in".

*AnalyzerTest* test de logic op functionaliteit en *RestAPITest* test de endpoints en het produceren van JSON en response objects.
