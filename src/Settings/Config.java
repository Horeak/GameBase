package Settings;

import GameFiles.BaseGame;
import Settings.Values.ConfigOption;
import Settings.Values.Keybinding;
import Utils.DataHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.newdawn.slick.Input;

public abstract class Config {
	public abstract Keybinding[] getKeybindings();
	public abstract ConfigOption[] getConfigOptions();

	public Keybinding getKeybindFromID( String id ) {
		for (Keybinding keybinding : getKeybindings()) {
			if (keybinding.getId().equals(id)) {
				return keybinding;
			}
		}

		return null;
	}

	public void saveConfig( BaseGame game, String fileLoc){
		if(game.getFilesSaveLocation() == null)return;

		//Keybindings
		DataHandler handlerKeys = game.saveUtil.getDataHandler(fileLoc + "keybindings.cfg");
		handlerKeys.setObject("keybindings", getKeybindings());

		//options
		DataHandler handlerOptions = game.saveUtil.getDataHandler(fileLoc + "settings.cfg");

		JSONArray array = new JSONArray();
		for(ConfigOption op : getConfigOptions()){
			JSONObject ob = new JSONObject();

			handlerOptions.setObject("name", op.getName(), ob);
			handlerOptions.setObject("id", op.getCur(), ob);
			handlerOptions.setObject("possibleObjects", op.getObs(), ob);
			handlerOptions.setObject("currentObject", op.getOb(), ob);

			array.add(ob);
		}

		handlerOptions.setObject("settings", array);
	}

	public void loadConfig( BaseGame game, String fileLoc){
		if(game.getFilesSaveLocation() == null) return;

		//Keybindings
		DataHandler handlerKeys = game.saveUtil.getDataHandler(fileLoc + "keybindings.cfg");
		JSONArray obKey = (JSONArray) handlerKeys.getObject("keybindings");

		if(obKey != null)
		for(int i = 0; i < obKey.size(); i++){
			JSONObject js = (JSONObject) obKey.get(i);
			if(i >= getKeybindings().length)
				continue;

			if(getKeybindings()[i] != null && getKeybindings()[i].getId().equals(js.get("id"))) {
				getKeybindings()[ i ].setKey(Integer.parseInt(Long.toString((Long) js.get("key"))));
			}
		}


		//Options
		DataHandler handlerOptions = game.saveUtil.getDataHandler(fileLoc + "settings.cfg");
		JSONArray obOption = (JSONArray) handlerOptions.getObject("settings");

		if(obOption != null)
		for(int i = 0; i < obOption.size(); i++){
			JSONObject js = (JSONObject) obOption.get(i);

			if(js != null)
			if(getConfigOptions()[i] != null && getConfigOptions()[i].getName().equals(handlerOptions.getString("name", js))) {
				getConfigOptions()[ i ].setCur(Integer.parseInt(Long.toString((Long) js.get("id"))));
				getConfigOptions()[ i ].setValue(getConfigOptions()[ i ].getObs()[Integer.parseInt(Long.toString((Long) js.get("id")))]);
				getConfigOptions()[ i ].setOb(getConfigOptions()[ i ].getObs()[Integer.parseInt(Long.toString((Long) js.get("id")))]);
			}
		}
	}

}
