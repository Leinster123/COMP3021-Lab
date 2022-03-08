package base;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Folder implements Comparable<Folder>{
    
    private ArrayList<Note> notes;
    private String name;
    
    public Folder(String name){
        this.name = name;
        notes = new ArrayList<Note>();
    }
    
    public void addNote(Note note){
        notes.add(note);
    }
    
    public String getName(){
        return name;
    }
    
    public ArrayList<Note> getNotes(){
        return notes;
    }
    
    public String toString(){
        int nText = 0;
        int nImage = 0;
        
        for (Note note : notes){
            if (note instanceof TextNote){ nText += 1;}
            else if (note instanceof ImageNote){ nImage += 1;}
        }
        
        return name + ":" + nText + ":" + nImage;
    }
    
    public boolean equals(Folder folder){
        return name.equals(folder.getName());
    }
    
    public int compareTo(Folder f){
        return name.compareTo(f.getName());
    }
    
    public void sortNotes(){
        Collections.sort(notes);
        return;
    }
    
    public List<Note> searchNotes(String keywords){
        String key = keywords.toLowerCase();
        String[] arrOfStr = key.split("or");
        ArrayList<Note> result = new ArrayList<Note>();
        for (Note note: notes){
            for (String a : arrOfStr){
                if (note instanceof ImageNote){
                    String title = note.getTitle().toLowerCase();
                    if (title.contains(a)){
                        result.add(note);
                    }
                }
                else{
                    String title = note.getTitle().toLowerCase();
                    String content = ((TextNote)note).getContent().toLowerCase();
                    if (title.contains(a) || content.contains(a)){
                        result.add(note);
                    }
                }
            }
        }
        return result;
    }
        
}