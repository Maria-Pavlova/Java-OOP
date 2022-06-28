package SOLID_Logger;

import SOLID_Logger.Controlers.*;
import SOLID_Logger.Enums.ReportLevel;
import SOLID_Logger.Interfaces.*;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        AppenderFactory appenderFactory = new AppenderWorkshop();
        LayoutFactory layoutFactory = new LayoutWorkshop();
        Logger logger = new MessageLogger();


        while (n-- > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");

            ReportLevel reportLevel = tokens.length == 3
                    ? ReportLevel.valueOf(tokens[2].toUpperCase())
                    : ReportLevel.INFO;

            Appender appender = appenderFactory.produce(tokens[0],reportLevel, layoutFactory.produce(tokens[1]));

            logger.addAppender(appender);

        }
        String input;
        while (!"END".equals(input=scanner.nextLine())) {
            String[] tokens = input.split("\\|");

            ReportLevel reportLevel = ReportLevel.valueOf(tokens[0]);
            String date = tokens[1];
            String message = tokens[2];

            switch (reportLevel) {
                case INFO -> logger.logInfo(date, message);
                case WARNING -> logger.logWarning(date, message);
                case ERROR -> logger.logError(date, message);
                case CRITICAL -> logger.logCritical(date, message);
                case FATAL -> logger.logFatal(date, message);
                default -> throw new IllegalStateException("Unknown enum value.");
            }

        }
        System.out.println(logger.toString());
    }
}
