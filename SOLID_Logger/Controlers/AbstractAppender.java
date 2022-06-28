package SOLID_Logger.Controlers;

import SOLID_Logger.Enums.ReportLevel;
import SOLID_Logger.Interfaces.Appender;
import SOLID_Logger.Interfaces.Layout;

public abstract class AbstractAppender implements Appender {
    private Layout layout;
    private  ReportLevel reportLevel;
    private int appendsCount;

    public AbstractAppender(ReportLevel reportLevel,Layout layout) {
        this.reportLevel = reportLevel;
        this.layout = layout;
    }
    public AbstractAppender(Layout layout){
        this(ReportLevel.INFO, layout);

    }
    protected Layout getLayout(){
        return layout;
    }

    @Override
    public ReportLevel getReportLevel() {
        return this.reportLevel;
    }

     protected abstract String getType();
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Appender type: ");
        builder.append(this.getType())
                .append(", Layout type: ")
                .append(this.layout.getType())
                .append(", ")
                .append(String.format("Report level: %s,",this.getReportLevel().toString()))
                .append("Messages appended: ")
                .append(this.appendsCount);
        return builder.toString();
    }

    protected void incrementAppendCount() {
        this.appendsCount++;
    }
}

