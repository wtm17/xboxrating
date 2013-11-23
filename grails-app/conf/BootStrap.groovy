import nat_xboxrating.Game
import nat_xboxrating.Vote

class BootStrap {

    def init = { servletContext ->
        def g1 = new Game(title: "Halo 3", owned: false, created: new Date())
        g1.save()
        def g2 = new Game(title: "Gears of War", owned: false, created: new Date())
        g2.save()
        def g3 = new Game(title: "Madden NFL 25", owned: false, created: new Date())
        g3.save()
        def g4 = new Game(title: "Dead or Alive 3", owned: true, created: new Date())
        g4.save()
        def g5 = new Game(title: "Grand Theft Auto V", owned: false, created: new Date())
        g5.save()
        def g6 = new Game(title: "Call of Duty: Black Ops", owned: false, created: new Date())
        g6.save()
        def g7 = new Game(title: "Assassin's Creed", owned: true, created: new Date())
        g7.save()
        def g8 = new Game(title: "Guitar Hero", owned: true, created: new Date())
        g8.save()


        10.times {new Vote(game: g1, created: new Date()).save()}
        15.times {new Vote(game: g2, created: new Date()).save()}
        5.times {new Vote(game: g3, created: new Date()).save()}
        21.times {new Vote(game: g5, created: new Date()).save()}
        31.times {new Vote(game: g6, created: new Date()).save()}
    }
    def destroy = {
    }
}
