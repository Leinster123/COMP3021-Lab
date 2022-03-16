package base;

import java.util.Date;
import java.io.*;

public class Note implements Comparable<Note>, Serializable{
    
    private Date date;
    private String title;
    
    public Note(String title){
        this.title = title;
        date = new Date(System.currentTimeMillis());
    }
    
    public String getTitle(){
        return title;
    }
    
    public Date getDate(){
        return date;
    }
    
    public boolean equals(Note note){
        return title.equals(note.getTitle());
    }
    
    public int compareTo(Note o){
        int result = date.compareTo(o.getDate());
        if (result == 0){return 0;}
        else if (result > 0){return -1;}
        else {return 1;}
    }
    
    public String toString(){
        return date.toString() + "\t" + title;
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