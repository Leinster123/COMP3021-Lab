package base;

import java.util.ArrayList;

public class NoteBook {
    
    private ArrayList<Folder> folders;
    
    public NoteBook(){
        folders = new ArrayList<Folder>();
    }
    
    public boolean createTextNote(String folderName, String title){
        TextNote note = new TextNote(title);
        return insertNote(folderName, note);
    }
    
    public boolean createImageNote(String folderName, String title){
        ImageNote note = new ImageNote(title);
        return insertNote(folderName, note);   
    }
    
    public ArrayList<Folder> getFolders(){
        return folders;
    }
    
    public boolean insertNote(String folderName, Note note){
        for (Folder f : folders){
            if (f.getName().equals(folderName)){
                for (Note n : f.getNotes()){
                    if (note.equals(n)){
                        System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
                        return false;
                    }
                }
                f.getNotes().add(note);
                return true;
            }
        }
        Folder newFolder = new Folder(folderName);
        newFolder.getNotes().add(note);
        folders.add(newFolder);
        return true;
    }
}