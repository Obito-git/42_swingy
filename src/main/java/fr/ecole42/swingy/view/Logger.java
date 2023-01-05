package fr.ecole42.swingy.view;

public class Logger {
    private static boolean active = false;

    private Logger() {}

    public static void setActive(boolean active) {
        Logger.active = active;
    }

    public static void print(String msg) {
        if (active)
            System.out.println("[GAME] " + msg);
    }



}
