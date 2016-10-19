package Settings.Values;

import Utilities.LoggerUtil;

import java.util.Arrays;
import java.util.logging.Level;

public abstract class ConfigOption {
	private String name;
	private Object ob;
	private Object[] obs;
	private int cur = 0;
	
	public ConfigOption( String name, Object[] values, Object displayValue ) {
		this.name = name;
		this.obs = values;
		this.ob = displayValue;
	}
	
	public void change() {
		cur += 1;
		if (cur >= obs.length) {
			cur = 0;
		}
		ob = obs[ cur ];
		
		setValue(ob);
		
		LoggerUtil.out.log(Level.CONFIG, "[" + name + "] Value was changed to: " + ob);
	}
	
	public boolean showOption() {return true;}
	
	public String getName() {
		return name;
	}
	
	public Object getOb() {
		return ob;
	}
	
	public void setOb( Object ob ) {
		this.ob = ob;
	}
	
	public Object[] getObs() {
		return obs;
	}
	
	public abstract void setValue( Object ob );
	
	public int getCur() {
		return cur;
	}
	
	public void setCur( int cur ) {
		this.cur = cur;
	}
	
	//TODO Should i save the data that is not required for loading? (The ob and obs are not required)
	@Override
	public String toString() {
		return "ConfigOption{" + "name='" + name + '\'' + ", object=" + ob + ", possibleObjects=" + Arrays.toString(obs) + ", id=" + cur + '}';
	}
}
