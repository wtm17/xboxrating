package nat_xboxrating

class Game {

    static hasMany = [votes: Vote]

    String title
    Boolean owned
    Date created

    static constraints = {
        owned defaultValue: false
        created defaultValue: new Date()
    }
    String toString() {
        return this.title;
    }

}
