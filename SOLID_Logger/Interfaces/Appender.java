package SOLID_Logger.Interfaces;

import SOLID_Logger.Enums.ReportLevel;

public interface Appender {

 ReportLevel getReportLevel();
 void append(String date, ReportLevel reportLevel, String message);
}
