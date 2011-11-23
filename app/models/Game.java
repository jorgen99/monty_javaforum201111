package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Game extends Model {
    public String playerName;
    public Integer selectedDoor;

    public Game(String playerName) {
        this.playerName = playerName;
    }

    public int selectDoor(int doorNo) {
        selectedDoor = doorNo;
        save();
        return 3;
    }
}
