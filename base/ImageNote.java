package base;

import java.io.*;

public class ImageNote extends Note implements Serializable{
    public File image;
    
    public ImageNote(String title){
        super(title);
    }

    public boolean save(String file){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
