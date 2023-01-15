package fr.ecole42.swingy.view.console;

public class Logger {
    private static boolean active = false;

    private Logger() {}

    public static void setActive(boolean active) {
        Logger.active = active;
    }

    public static void print(String msg) {
        if (active && msg != null && !msg.isEmpty() && !msg.isBlank())
            System.out.println("[GAME] " + msg);
    }

    public static void lnprint(String msg) {
        if (active && msg != null && !msg.isEmpty() && !msg.isBlank())
            System.out.println("\n[GAME] " + msg);
    }

    public static void printDelimiter() {
        if (active)
            System.out.println("-------------------------");
    }



}
