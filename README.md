# TransformerProject
This Project is a Spring based Rest service  which involves Transformers Fight ( Autobots &amp; Decepticons ).Based on business rules, the winner and the survivors are defined..
All required REST Services can be viewed in Integrated swagger UI .

Swagger UI: http://localhost:8080/swagger-ui.html

Sample Request:

{
"teamName": "Soundwave",
"type": "D",
"strength": 8,
"intelligence": 9,
"speed": 2,
"endurance": 6,
"rank": 7,
"courage": 5,
"firepower": 6,
"skill": 10
}

Assumptions:

1. Request and response are in JSON format.
2. If both the teams have same number of winners, fighter with highest overall rating across teams will be the winner and his team will win the fight.
3. If winning team has more than one winner, fighter with the highest overall rating will be the winner.
