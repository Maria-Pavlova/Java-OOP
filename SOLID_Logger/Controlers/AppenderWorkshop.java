package SOLID_Logger.Controlers;

import SOLID_Logger.Enums.ReportLevel;
import SOLID_Logger.Interfaces.Appender;
import SOLID_Logger.Interfaces.AppenderFactory;
import SOLID_Logger.Interfaces.Layout;

public class AppenderWorkshop implements AppenderFactory {
    @Override
    public Appender produce(String type, ReportLevel reportLevel, Layout layout) {
        return switch (type) {
            case "ConsoleAppender" -> new ConsoleAppender(reportLevel,layout);
            case "FileAppender" -> new FileAppender(reportLevel,layout);
            default -> throw new IllegalStateException(
                    "Invalid type of appender");
        };

    }
}
