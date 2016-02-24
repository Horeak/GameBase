package Utils;

import GameFiles.BaseGame;
import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.io.*;
import java.util.HashMap;

public class SaveUtils {

	public BaseGame game;

	public SaveUtils(BaseGame game){
		this.game = game;
	}

	public void saveObjectFile( Object ob, String location){
		try {
			File ff = new File(game.getFilesSaveLocation());

			if(!ff.exists()) {
				ff.mkdirs();
			}


			FileOutputStream stream = new FileOutputStream(new File(game.getFilesSaveLocation() + "/" + location));
			ObjectOutputStream outputStream = new ObjectOutputStream(stream);

			outputStream.writeObject(ob);


		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public Object loadObjectFile( String location){
		try {
			File ff = new File(game.getFilesSaveLocation());

			if(!ff.exists()) {
				ff.mkdirs();
			}

			if(!new File(game.getFilesSaveLocation() + "/" + location).exists()){
				return null;
			}

			FileInputStream fis = new FileInputStream(game.getFilesSaveLocation() + "/" + location);
			ObjectInputStream ois = new ObjectInputStream(fis);

			return ois.readObject();

		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

	public HashMap<String, DataHandler> handlerHashMap = new HashMap<>();

	public DataHandler getDataHandler(String file){
		if(!handlerHashMap.containsKey(file)){
			DataHandler handler = new DataHandler(FileUtils.getFile(game.getFilesSaveLocation() + "/" + file));
			handlerHashMap.put(file, handler);

			return handler;
		}else{
			return handlerHashMap.get(file);
		}
	}

}
