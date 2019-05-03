package logger;

public class TestLog {
    public static void main(String[] args) {

        MyLog myLog = MyLog.getInstance();

        myLog.logInfo("some info");
        myLog.logWarning("this is a warning");
        myLog.logError(1547956);
    }
}

