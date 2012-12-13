package models;

import classes.Word;


public class ModelOld {
    
    private static final int spaceFactor = 70;
    private double left, right, top, bottom;
    private double width, height;
    private java.util.List<Notes> pages;
    private int currentIndex;
    
    public ModelOld(){
        pages = new java.util.ArrayList<>();
        currentIndex = -1;
        reset();
    }
    
    public void addPage(){
        pages.add(new Notes());
        currentIndex = pages.size()-1;
    }
    
    public void setCurrentIndex(int i){
        currentIndex = i;
    }
    
    public int getCurrentIndex(){
        return currentIndex;
    }
    
    public boolean isNewWord(double x, double y){
        if(right + spaceFactor < x || left - spaceFactor > x){
            System.out.println("New Word");
            return true;
        }
        else{
            System.out.println("Still in Word");
            checkBounds(x,y);
            return false;
        }
    }
    
    public void addPoint(double x, double y){
        checkBounds(x, y);
    }
    
    public void addWord(Word w){
        pages.get(currentIndex).addWord(w);
        reset();
    }
    
    public java.util.List<Word> getWords(){
        return pages.get(currentIndex).getWords();
    }
    
    public int left(){
        return (int)left;
    }
    
    public int top(){
        return (int)top;
    }
    
    public int getWidth(){
        return (int)width;
    }
    
    public int getHeight(){
        return (int)height;
    }
    
    private void reset(){
        right = bottom = 0;
        left = top = 1000;
        width = height = 0;
    }
    
    private void checkBounds(double x, double y){
        left = left > x ? x : left;
        right = right < x ? x : right;
        top = top > y ? y : top;
        bottom = bottom < y ? y : bottom;
        width = right - left;
        height = bottom - top;
    }
}