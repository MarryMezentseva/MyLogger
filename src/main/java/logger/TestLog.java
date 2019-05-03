package logger;

public class TestLog {
    public static void main(String[] args) {

        MyLog.getInstance().logInfo("some info");
        MyLog.getInstance().logWarning("this is a warning");
        MyLog.getInstance().logError(1547956);
    }
}

