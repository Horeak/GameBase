package Utils;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LoggerUtil
{
    public static Logger out;

    public static void activate(String name){
        out = Logger.getLogger(name);

        out.setUseParentHandlers(false);

        CustomFormatter formatter = new CustomFormatter();
        CustomConsoleHandler handler = new CustomConsoleHandler();

        handler.setFormatter(formatter);
        out.addHandler(handler);

        addCustomPrinting(out);
    }


    public static void deActivate(){
        removeCustomPrinting(out);
    }
    public static void addCustomPrinting(Logger log){
        CustomPrintStream out = new CustomPrintStream(System.out, log);
        out.attachOut();
    }
    public static void removeCustomPrinting(Logger log){
        CustomPrintStream out = new CustomPrintStream(System.out, log);
        out.detachOut();
    }

}




class CustomFormatter extends Formatter {

    private static final DateFormat df = new SimpleDateFormat("[dd/MM/yyyy][HH:mm:ss]");

    public String format(LogRecord record) {

        StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date(record.getMillis()))).append(" - ");

	    builder.append("[").append(record.getSourceClassName() + ":" + record.getSourceMethodName()).append("] - ");
        builder.append("[").append(record.getLevel()).append("] - ");
        builder.append(formatMessage(record));
        builder.append("\n");


        return builder.toString();
    }


}

class CustomConsoleHandler extends ConsoleHandler{

    public CustomConsoleHandler(){
        super();
            setOutputStream(System.out);
    }
}

class CustomPrintStream extends PrintStream{

    Logger log;
    PrintStream orig, origErr;

    public CustomPrintStream(OutputStream out, Logger log) {
        super(out,true);
        this.log = log;
    }


    @Override
    public void print( String s )
    {
        log.log(Level.INFO, s );
    }

    @Override
    public void println( String s )
    {
        print( s );
    }


    public void attachOut()
    {
        orig  = System.out;
        System.setOut( this );

        origErr  = System.err;
        System.setErr(this);
    }


    public void detachOut()
    {
        if( orig != null )
        {
            System.setOut( orig );
        }

        if( origErr != null )
        {
            System.setErr( origErr );
        }
    }

}


