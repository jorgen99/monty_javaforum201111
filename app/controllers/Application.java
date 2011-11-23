package controllers;

import play.data.validation.Validation;
import play.mvc.*;

import java.util.*;

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
        session.put("gameId", game.id);
        render(game);
    }

    public static void selectDoor(Integer doorNo) {
        Game game = currentGame();
        int goatDoorNo = game.selectDoor(doorNo);
        renderJSON(replyMap("goatDoor", goatDoorNo));
    }

    public static void stayOrSwitch(Integer doorNo) {
        Game game = currentGame();
        int carDoor = game.stayOrSwitch(doorNo);
        renderJSON(replyMap("carDoor", carDoor));
    }

    private static Map<String, String> replyMap(String key, Object value) {
        Map<String, String> reply = new HashMap<String, String>(1);
        reply.put(key, value.toString());
        return reply;
    }

    private static Game currentGame() {
        Long gameId = Long.parseLong(session.get("gameId"));
        Game currentGame = Game.findById(gameId);
        if(currentGame == null) {
            start();
        }
        return currentGame;
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