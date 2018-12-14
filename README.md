# TransformerProject
This Project is a Spring based Rest service  which involves Transformers Fight ( Autobots &amp; Decepticons ).Based on business rules, the winner and the survivors are defined..

Overview

The goal of this Project is to create a Java RESTful API.  API will allow the
following main functionality:

    ● Create a Transformer
    ● Update a Transformer
    ● Delete a Transformer
    ● List Transformers
    ● Given a list of Transformer IDs, determine the winning team
    Transformer definition:
    
Each Transformer has the following criteria (ranked from 1 to 10) on their tech spec:

    ● Strength
    ● Intelligence
    ● Speed
    ● Endurance
    ● Rank
    ● Courage
    ● Firepower
    ● Skill
    
The “overall rating” of a Transformer is the following formula: (Strength + Intelligence + Speed +
Endurance + Firepower).
Each Transformer must either be an Autobot or a Decepticon.
Determining winning team:
Your API should take as input a list of Transformer IDs and based on input returns:

    1. The number of battles
    2. The winning team
    3. The surviving members of the losing team
    
The basic rules of the battle are:

    ● The transformers are split into two teams based on if they are Autobots or Decepticons
    
    ● The teams should be sorted by rank and faced off one on one against each other in
    order to determine a victor, the loser is eliminated.
    
    A battle between opponents uses the following rules:
    
    ●  If any fighter is down 4 or more points of courage and 3 or more points of
    strength compared to their opponent, the opponent automatically wins the
    face-off regardless of overall rating (opponent has ran away)
    ●  Otherwise, if one of the fighters is 3 or more points of skill above their opponent,
    they win the fight regardless of overall rating
    ● The winner is the Transformer with the highest overall rating
    ● In the event of a tie, both Transformers are considered destroyed
    ● Any Transformers who don’t have a fight are skipped (i.e. if it’s a team of 2 vs. a team of
    1, there’s only going to be one battle) 
    ● The team who eliminated the largest number of the opposing team is the winner
    Special rules:
    ● Any Transformer named Optimus Prime or Predaking wins his fight automatically
    regardless of any other criteria
    ● In the event either of the above face each other (or a duplicate of each other), the game
    immediately ends with all competitors destroyed


Example:

For example, given the following input:

Soundwave, D, 8,9,2,6,7,5,6,10
Bluestreak, A, 6,6,7,9,5,2,9,7
Hubcap: A, 4,4,4,4,4,4,4,4

The output should be:

1 battle
Winning team (Decepticons): Soundwave
Survivors from the losing team (Autobots): Hubcap
