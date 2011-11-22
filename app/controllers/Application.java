package controllers;

import play.*;
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

    public static void name(String name) {
        session.put("name", name);
        game();
    }

    public static void game() {
        render();
    }
}