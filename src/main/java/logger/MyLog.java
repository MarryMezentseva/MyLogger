package logger;

import java.sql.Timestamp;

public class MyLog {
    private MyLog() {
    }

    private static final MyLog INSTANCE = new MyLog();

    public static MyLog getInstance() {
        return INSTANCE;
    }

    public void logInfo(Object msg) {
        System.out.println(new Timestamp(System.currentTimeMillis()) + " INFO " + msg);

    }

    public void logWarning(Object msg) {
        System.out.println(new Timestamp(System.currentTimeMillis()) + " WARNING " + msg);
    }

    public void logError(Object msg) {
        System.out.println(new Timestamp(System.currentTimeMillis()) + " ERROR " + msg);
    }
}
