package Utilities;

import GameFiles.BaseGame;
import sun.reflect.CallerSensitive;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LoggerUtil {
	public static final DateFormat df = new SimpleDateFormat("[dd/MM/yyyy][HH:mm:ss]");
	public static Logger out;
	public static File log;
	public static FileWriter logWriter;
	
	public static void exception( Exception e ) {
		out.log(Level.SEVERE, e.toString());
		
		for (StackTraceElement t : e.getStackTrace()) {
			out.log(Level.SEVERE, " at " + t.toString());
		}
	}
	
	public static void activate( String name ) {
		out = Logger.getLogger(name);
		out.setUseParentHandlers(false);
		out.setLevel(Level.ALL);
		
		CustomFormatter formatter = new CustomFormatter();
		CustomConsoleHandler handler = new CustomConsoleHandler();
		
		handler.setFormatter(formatter);
		out.addHandler(handler);
	}
	
	public static void activateLogFile( String file, BaseGame game ) {
		try {
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss");
			String time = df.format(new Date(System.currentTimeMillis()));
			
			log = FileUtils.getFile(game.getFilesSaveLocation() + "/" + file + "/log-" + time + ".log");
			logWriter = new FileWriter(log);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class CustomFormatter extends Formatter {
	@CallerSensitive
	public String format( LogRecord record ) {
		
		StringBuilder builder = new StringBuilder(1000);
		builder.append(LoggerUtil.df.format(new Date(record.getMillis()))).append(" - ");
	
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		for (StackTraceElement stack : stacks) {
			String className = stack.getClassName();
			//TODO Try to optimize this and make sure it doesnt affect performance and startup time too much?
			if (!className.startsWith("java") && !className.contains(CustomFormatter.class.getName()) && !className.contains(CustomConsoleHandler.class.getName()) && !className.contains(CustomPrintStream.class.getName()) && !className.contains(LoggerUtil.class.getName())) {
				builder.append("[" + stack.getFileName() + "][" + stack.getMethodName() + ":" + stack.getLineNumber() + "] - "); //TODO Use .getFileName() or .getClassName() ?
				break;
			}
		}
		
		builder.append("[").append(record.getLevel()).append("] - ");
		builder.append(formatMessage(record));
		
		if (!record.getMessage().endsWith("\n")) {
			builder.append("\n");
		}
		
		if (LoggerUtil.logWriter != null) {
			try {
				LoggerUtil.logWriter.write(builder.toString());
				LoggerUtil.logWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return builder.toString();
	}
	
	
}

class CustomConsoleHandler extends ConsoleHandler {
	@Override
	public void publish( LogRecord record ) {
		
		try {
			String message = getFormatter().format(record);
			
			if (record.getLevel() == Level.SEVERE || record.getLevel() == Level.WARNING) {
				System.err.write(message.getBytes());
			} else {
				System.out.write(message.getBytes());
			}
			
		} catch (Exception exception) {
			reportError(null, exception, ErrorManager.FORMAT_FAILURE);
		}
	}
}

class CustomPrintStream extends PrintStream {
	Logger log;
	public CustomPrintStream( OutputStream out, Logger log ) {
		super(out, true);
		this.log = log;
	}
	
	@Override
	public void print( String s ) {
		log.log(Level.INFO, s);
	}
	
	@Override
	public void println( String s ) {
		print(s);
	}
}


