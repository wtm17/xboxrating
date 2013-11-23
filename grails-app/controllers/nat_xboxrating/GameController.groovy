package nat_xboxrating

import grails.converters.JSON

class GameController {

    static allowedMethods = [query: "GET", addGame: "POST", addVote: "POST", setOwned: "PUT"]

    /**
     * Query for all Games and return them as a JSON array
     */
    def query() {
        render Game.findAll() as JSON
    }

    /**
     * Create new game based on attributes in JSON
     * Also create one vote for that game
     */
    def addGame() {

        def game = new Game(
                title : request.JSON.title,
                owned: request.JSON.owned ?: false,
                created : request.JSON.created ?: new Date()
        ).save( failOnError : true )

        def vote = new Vote(
                game: game,
                created : request.JSON.created ?: new Date()
        ).save( failOnError : true )

        render ""
    }

    /**
     * Vote for a particular game
     */
    def addVote() {
        def game = Game.get( request.JSON.id )

        if ( game ) {
            def vote = new Vote(
                    game: game,
                    created : new Date()
            ).save( failOnError : true )
        }

        render ""
    }

    /**
     * Mark Game as owned
     */
    def setOwned() {
        def game = Game.get( request.JSON.id )

        if ( game ) {
            game.owned = true
            game.save()
        }

        render ""
    }

}
