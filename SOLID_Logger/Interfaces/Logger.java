package SOLID_Logger.Interfaces;

public interface Logger {
    void logCritical(String date, String message);
    void logFatal(String date, String message);
    void logInfo(String date, String message);
    void logError(String date, String message);
    void logWarning(String date, String message);

    void addAppender(Appender appender);
}
