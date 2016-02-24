package Utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class DataHandler {

    private File file;
    private JSONObject object = new JSONObject();

    public DataHandler(File file){
        this.file = file;

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(file));
            object = (JSONObject) obj;

        }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
               // e.printStackTrace();
            }

        }

    public void setFile(){

        try {

        FileWriter fileWriter = new FileWriter(file);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(object);

        fileWriter.write(jsonOutput);
        fileWriter.flush();
        fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void setObject(String name, Object value){
        if(object.get(name) == null){
            object.put(name, value);
        }else{
            object.remove(name);
            object.put(name, value);
        }

        setFile();
    }




    public Integer getInteger(String name){
        if(object.get(name) == null)
            return 0;

        Object t = object.get(name);
        return Integer.parseInt(t.toString());
    }


    public String getString(String name){
        if(object.get(name) == null)
            return null;

        Object t = object.get(name);

        if(t instanceof String)
            return(String)t;

        return null;
    }

    public Long getLong(String name){
        if(object.get(name) == null)
            return 0L;

        Object t = object.get(name);

        if(t instanceof Long)
            return(Long)t;

        return 0L;
    }


    public Double getDouble(String name){
        if(object.get(name) == null)
            return 0D;

        Object t = object.get(name);

        if(t instanceof Double)
            return(Double)t;

        return 0D;
    }


    public Boolean getBoolean(String name){
        if(object.get(name) == null)
            return false;

        Object t = object.get(name);

        if(t instanceof Boolean)
            return(Boolean)t;

        return false;
    }


    public Object getObject(String name){
        return object.get(name);
    }





    public Integer getIntegerDefault(String name, Integer def){
        if(object.get(name) == null) {
            setObject(name,def);
            return def;
        }

        return getInteger(name);
    }


    public String getStringDefault(String name, String def){
        if(object.get(name) == null) {
            setObject(name,def);
            return def;
        }

        return getString(name);
    }


    public Long getLongDefault(String name, long def){
        if(object.get(name) == null) {
            setObject(name,def);
            return def;
        }

        return getLong(name);
    }


    public Double getDoubleDefault(String name, double def){
        if(object.get(name) == null) {
            setObject(name,def);
            return def;
        }

        return getDouble(name);
    }


    public Boolean getBooleanDefault(String name, boolean def){
        if(object.get(name) == null) {
            setObject(name,def);
            return def;
        }

        return getBoolean(name);
    }





    public void setObject(String name, Object value, JSONObject object){
        if(object.get(name) == null){
            object.put(name, value);
        }else{
            object.remove(name);
            object.put(name, value);
        }

        setFile();
    }




    public Integer getInteger(String name, JSONObject object){
        if(object.get(name) == null)
            return 0;

        Object t = object.get(name);

        if(t instanceof Integer)
            return(Integer)t;

        return 0;
    }


    public String getString(String name, JSONObject object){
        if(object.get(name) == null)
            return null;

        Object t = object.get(name);

        if(t instanceof String)
            return(String)t;

        return null;
    }


    public Float getFloat(String name, JSONObject object){
        if(object.get(name) == null)
            return 0F;

        Object t = object.get(name);

        if(t instanceof Float)
            return(Float)t;

        return 0F;
    }


    public Long getLong(String name, JSONObject object){
        if(object.get(name) == null)
            return 0L;

        Object t = object.get(name);

        if(t instanceof Long)
            return(Long)t;

        return 0L;
    }


    public Double getDouble(String name, JSONObject object){
        if(object.get(name) == null)
            return 0D;

        Object t = object.get(name);

        if(t instanceof Double)
            return(Double)t;

        return 0D;
    }


    public Boolean getBoolean(String name, JSONObject object){
        if(object.get(name) == null)
            return false;

        Object t = object.get(name);

        if(t instanceof Boolean)
            return(Boolean)t;

        return false;
    }


    public Object getObject(String name, JSONObject object){
        return object.get(name);

    }





    public Integer getIntegerDefault(String name, Integer def, JSONObject object){
        if(object.get(name) == null) {
            setObject(name,def);
            return def;
        }

        return getInteger(name, object);
    }


    public String getStringDefault(String name, String def, JSONObject object){
        if(object.get(name) == null) {
            setObject(name,def);
            return def;
        }

        return getString(name, object);
    }



    public Long getLongDefault(String name, long def, JSONObject object){
        if(object.get(name) == null) {
            setObject(name,def);
            return def;
        }

        return getLong(name, object);
    }


    public Double getDoubleDefault(String name, double def, JSONObject object){
        if(object.get(name) == null) {
            setObject(name,def);
            return def;
        }

        return getDouble(name, object);
    }


    public Boolean getBooleanDefault(String name, boolean def, JSONObject object){
        if(object.get(name) == null) {
            setObject(name,def);
            return def;
        }

        return getBoolean(name, object);
    }



}
