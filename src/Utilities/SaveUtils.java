package Utilities;

import GameFiles.BaseGame;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class SaveUtils {
	public BaseGame game;
	public HashMap<String, DataHandler> handlerHashMap = new HashMap<>();
	
	public SaveUtils( BaseGame game ) {
		this.game = game;
	}
	
	public synchronized void saveObjectFile( Object ob, String location ) {
		try {
			SwingUtilities.invokeAndWait(() -> {
				try {
					File ff = new File(game.getFilesSaveLocation());
					
					if (!ff.exists()) {
						ff.mkdirs();
					}
					
					FileOutputStream stream = new FileOutputStream(FileUtils.getFile(game.getFilesSaveLocation() + "/" + location));
					ObjectOutputStream outputStream = new ObjectOutputStream(stream);
					
					outputStream.writeObject(ob);
				} catch (Exception e) {
					LoggerUtil.exception(e);
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized Object loadObjectFile( String location ) {
		try {
			File ff = new File(game.getFilesSaveLocation());
			
			if (!ff.exists()) {
				ff.mkdirs();
			}
			
			if (!new File(game.getFilesSaveLocation() + "/" + location).exists()) {
				return null;
			}
			
			FileInputStream fis = new FileInputStream(game.getFilesSaveLocation() + "/" + location);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			return ois.readObject();
			
		} catch (Exception e) {
			LoggerUtil.exception(e);
		}
		
		return null;
	}
	
	public DataHandler getDataHandler( String file ) {
		if (!handlerHashMap.containsKey(file)) {
			DataHandler handler = new DataHandler(FileUtils.getFile(game.getFilesSaveLocation() + "/" + file));
			handlerHashMap.put(file, handler);
			
			return handler;
		} else {
			return handlerHashMap.get(file);
		}
	}
	
}
