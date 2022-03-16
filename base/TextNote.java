package base;

import java.io.*;

public class TextNote extends Note implements Serializable{
    public String content;
    
    public TextNote(String title){
        super(title);
    }
    
    public TextNote(String title, String content){
        super(title);
        this.content = content;
    }

    public TextNote(File f) {
        super(f.getName());
        this.content = getTextFromFile(f.getAbsolutePath());
    }
    
    public String getContent(){
        return content;
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

    private String getTextFromFile(String absolutePath) {
        String result = "";
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(absolutePath);
            in = new ObjectInputStream(fis);
            result = (String) in.readObject();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void exportTextToFile(String pathFolder) {
        String name = this.getTitle().replaceAll(" ", "_");
        File file = new File(pathFolder + File.separator + name + ".txt");
        try(PrintWriter output = new PrintWriter(file)){
            output.println(this.getTitle());
            output.println(this.getDate());
            output.println(this.getContent());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}