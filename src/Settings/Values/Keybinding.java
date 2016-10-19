package Settings.Values;

public class Keybinding {
	private String name, id, group;
	private int key, defaultKey;
	
	public Keybinding( String Name, String id, int key, String group ) {
		this.name = Name;
		this.id = id;
		this.key = key;
		this.defaultKey = key;
		this.group = group;
	}
	
	public boolean isEnabled() {
		return true;
	}
	
	public KeybindingAction getAction() {return null;}
	
	public int getKey() {
		return key;
	}
	
	public void setKey( int key ) {
		this.key = key;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getGroup() {
		return group;
	}
	
	public int getDefaultKey() {
		return defaultKey;
	}
	
	@Override
	public String toString() {
		return "Keybinding{" + "name='" + name + '\'' + ", id='" + id + '\'' + ", group='" + group + '\'' + ", key=" + key + ", defaultKey=" + defaultKey + '}';
	}
}
