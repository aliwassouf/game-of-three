# Game of Three 
This is the solution to the coding challenge of Leiferando 

This solution is using Java, SpringBoot and RabbitMQ. 

## How to run 
* go to the parent directory and do `docker-compose up`, this will spin up docker images for PostgreSQL and RabbitMQ.
* go to [PlayerOne](PlayerOne/src/main/java/com/lieferando/servicea/PlayerOne.java) and run the service, this will start a spring boot service on port `8080`
* go to [PlayerTwo](PlayerTwo/src/main/java/com/lieferando/serviceb/PlayerTwo.java) and run the service, this will start a spring boot service on port `8082`

PlayerOne can change the response mode of their game between [manual](http://localhost:8080/manual) and [automatic](http://localhost:8080/automatic)
PlayerTwo has the game response mode always set to automatic. 

## Playing the game
### Manual 
PlayerOne can start a game by sending any random value to `/start/{number}`; everytime this end point is invoked we create a new game session between PlayerOne and PlayerTwo.
The reply to this request from PlayerTwo is printed on the console of PlayerOne, something similar to "`Received Message From PlayerTwo {messageValue} on game session with id {sessionID} nearest is {NearestNumber}`".
PlayerOne can then choose to invoke one of the threes endpoint  `/{gameSessionId}/addOne` , `/{gameSessionId}/subtractOne`, `/{gameSessionId}/addZero` as a `POST` request.

If any player invokes any of the three mentioned endpoints, and it's not their turn, we respond with a message `It's not your turn`.

If any player reaches (1 / -1) we print `You won!` on their console, and we set the game session status to finished.

Trying to add/subtract to a session that doesn't exist, or a session that has ended, creates a new session with a new ID.

### Automatic
Invoke [automatic](http://localhost:8080/automatic) for PlayerOne. 
Then invoke `POST http://localhost:8080/start/{number}`, and keep an eye on the console of PlayerOne to see the flow of the game. 




