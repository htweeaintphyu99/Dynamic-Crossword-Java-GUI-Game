package Crossword;


/**
 * Write a description of class Cell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cell
{
    private int x, y;
    private int index1, index2;
    private boolean free = true;
    private Letter.TYPE_LETTER letter;
    Cell(Letter.TYPE_LETTER l, int x, int y){
       this.letter = l;
       this.x = x;
       this.y = y;
       this.index1 = 0;
       this.index2 = 0;
    }
    
    Cell(Letter.TYPE_LETTER l, int x, int y, int index1, int index2){
       this.letter = l;
       this.x = x;
       this.y = y;
       this.index1 = index1;
       this.index2 = index2;
    }
    
    public Letter.TYPE_LETTER getLetter(){
        return this.letter;
    }
    
    public void setIndex1(int index1){
        this.index1 = index1;
    }
    
    public void setIndex2(int index2){
        this.index2 = index2;
    }
    
    public void setLetter(Letter.TYPE_LETTER l){
        this.letter = l;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getIndex1(){
        return this.index1;
    }
    
    public int getIndex2(){
        return this.index2;
    }
}
 