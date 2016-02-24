package Utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static File getFile(String path){

        File file = new File(path);
        File folder = new File(file.getPath().replace(file.getName(), ""));

        try{
            if(!folder.exists())
            folder.mkdirs();

            if(!file.exists())
            file.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }

        return file;
    }
}
