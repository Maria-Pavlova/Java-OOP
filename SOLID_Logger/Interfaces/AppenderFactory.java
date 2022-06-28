package SOLID_Logger.Interfaces;

import SOLID_Logger.Enums.ReportLevel;

public interface AppenderFactory {
    Appender produce(String type, ReportLevel reportLevel, Layout layout);
}
