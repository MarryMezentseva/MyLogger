package logger;

public class TestLog {
    public static void main(String[] args) {

        MyLog.INSTANCE.logInfo("some info");
        MyLog.INSTANCE.logWarning("this is a warning");
        MyLog.INSTANCE.logError("error!");
    }
}

