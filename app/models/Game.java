package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Game extends Model {
    public String playerName;

    public Game(String playerName) {
        this.playerName = playerName;
    }
}
