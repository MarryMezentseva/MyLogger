package logger;

import java.util.Date;

public class MyLog {
    private MyLog() {
    }

    public static final MyLog INSTANCE = new MyLog();


    public void logInfo(Object msg) {
        System.out.println(new Date().toString() + " INFO [" + INSTANCE + "] - " + msg);

    }

    public void logWarning(Object msg) {
        System.out.println(new Date().toString() + " WARNING [" + INSTANCE + "] - " + msg);
    }

    public void logError(Object msg) {
        System.out.println(new Date().toString() + " ERROR [" + INSTANCE + "] - " + msg);
    }
}
