package models;

import play.Logger;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Game extends Model {
    public String playerName;
    public Integer initialPlayerDoor;
    public Integer carDoor;
    public Boolean switched = false;
    public Boolean won = false;

    public Game(String playerName) {
        this.playerName = playerName;
        this.carDoor = randomDoor();
    }

    Game(int carDoor) {
        this.playerName = "Joe Tester";
        this.carDoor = carDoor;
    }

    public int selectDoor(int doorNo) {
        initialPlayerDoor = doorNo;
        return goatDoor();
    }

    public int stayOrSwitch(int doorNo) {
        if(doorNo != initialPlayerDoor) {
            switched = true;
        }
        if(doorNo == carDoor) {
            won = true;
        }
        return carDoor;
    }

    private int goatDoor() {
        while(true) {
            int possibleGoat = randomDoor();
            if(possibleGoat != carDoor && possibleGoat != initialPlayerDoor) {
                return possibleGoat;
            }
        }
    }

    private int randomDoor() {
        return new Random().nextInt(3) + 1;
    }
}
