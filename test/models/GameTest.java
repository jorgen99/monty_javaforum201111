package models;

import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class GameTest extends UnitTest {

    @Test
    public void the_opened_goat_door_should_not_be_the_initial_player_door() {
        Game game = new Game("Joe");
        for (int i = 0; i < 500; i++) {
            int goatDoor = game.selectDoor(1);
            assertThat(goatDoor, is(not(equalTo(1))));
        }
    }

    @Test
    public void the_opened_goat_door_should_not_be_the_car_door() {
        Game game = new Game("Joe");
        for (int i = 0; i < 500; i++) {
            int goatDoor = game.selectDoor(1);
            assertThat(goatDoor, is(not(equalTo(game.carDoor))));
        }
    }

    @Test
    public void when_choosing_the_car_door_you_win() {
        Game game = new Game(1);
        game.selectDoor(1);
        game.stayOrSwitch(1);
        assertThat(game.won, is(true));
    }

    @Test
    public void when_switching_door_switch_is_true() {
        Game game = new Game(3);
        game.selectDoor(1);
        game.stayOrSwitch(3);
        assertThat(game.switched, is(true));
    }
}
