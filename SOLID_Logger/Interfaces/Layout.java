package SOLID_Logger.Interfaces;

import SOLID_Logger.Enums.ReportLevel;

public interface Layout {
    String format(String date, ReportLevel reportLevel, String message);

    String getType();

}
