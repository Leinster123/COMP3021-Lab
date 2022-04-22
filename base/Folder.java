package base;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Folder implements Comparable<Folder>, Serializable{
	private static final long serialVersionUID = 1L;
    private ArrayList<Note> notes;
    private String name;
    
    public Folder(String name){
        this.name = name;
        notes = new ArrayList<Note>();
    }
    
    public void addNote(Note note){
        notes.add(note);
    }
    
    public boolean deleteNote(Note note) {
    	try {
    		notes.remove(note);
    		return true;
    	}
    	catch(Exception ex) {
    		return false;
    	}
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
        List<Note> res = new ArrayList<Note>();

		ArrayList<ArrayList<String>> patterns = new ArrayList<ArrayList<String>>();

		// process keywords
		String[] keyList = keywords.split(" ");
		int i=0;
		while(i<keyList.length) {
			if(keyList[i].toLowerCase().equals("or")) {
				i++;
				patterns.get(patterns.size() - 1).add(keyList[i].toLowerCase());
			} else {
				ArrayList<String> newArr = new ArrayList<String>();
				newArr.add(keyList[i].toLowerCase());
				patterns.add(newArr);
			}
			i++;
		}

		for(Note n : notes) {
			String toBeSearched = n.getTitle();
			if(n instanceof TextNote) {
				toBeSearched +=  ((TextNote)n).getContent();
			}
			toBeSearched = toBeSearched.toLowerCase();

			boolean flag = true;
			for(ArrayList<String> pattern : patterns) {
				boolean flag2 = false;
				for(String oneKey : pattern) {
					if(toBeSearched.contains(oneKey)) {
						flag2 = true;
						break;
					}
				}
				if(!flag2) {
					flag = false;
					break;
				}
			}
			if(flag) res.add(n);
		}
        return res;
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