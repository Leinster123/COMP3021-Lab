package base;

import java.util.Date;

public class Note{
    
    private Date date;
    private String title;
    
    public Note(String title){
        this.title = title;
        date = new Date(System.currentTimeMillis());
    }
    
    public String getTitle(){
        return title;
    }
    
    public boolean equals(Note note){
        return title.equals(note.getTitle());
    }
}