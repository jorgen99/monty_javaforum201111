package controllers;

import play.data.validation.Validation;
import play.mvc.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void start() {
        render();
    }

    public static void name(String playerName) {
        validatePlayerName(playerName);
        game(playerName);
    }

    public static void game(String playerName) {
        validatePlayerName(playerName);
        Game game = new Game(playerName).save();
        render(game);
    }

    private static void validatePlayerName(String playerName) {
        Validation.required("playerName", playerName).message("Please enter a player name.");
        Validation.minSize("playerName", playerName, 2).message("At least two letters please.");
        if(Validation.hasErrors()) {
            Validation.keep();
            params.flash();
            start();
        }
    }
}