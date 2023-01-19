package utils;

public abstract class TinyLogger {

    static boolean debugEnable = false;

    public static void debugSwitchOff() {
        debugEnable = false;
    }

    public static void debugSwitchOn() {
        debugEnable = true;
    }

    public static void debug(String msg, Object... arguments) {
        if (debugEnable)
            printStdOut("DEBUG " + msg, arguments);
    }

    public static void info(String msg, Object... arguments) {
        printStdOut("INFO " + msg, arguments);
    }

    public static void warn(String msg, Object... arguments) {
        printStdOut("WARN " + msg, arguments);
    }

    public static void error(String msg, Object... arguments) {
        System.err.println("ERROR " + String.format(msg, arguments));
    }

    public static void printStdOut(String msg, Object... arguments) {
        System.out.println(String.format(msg, arguments));
    }
}
