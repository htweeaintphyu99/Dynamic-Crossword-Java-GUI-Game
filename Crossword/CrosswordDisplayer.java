package Crossword;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.*;
import java.util.*;
import java.text.AttributedString;

/**
 * Write a description of class Crossword_Displayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CrosswordDisplayer
{
    public static JFrame frame = new JFrame();
    JPanel container = new JPanel();
    private JPanel jp;
    private JPanel[][] cell = new JPanel[15][15];
    private ClueAnswer clueAns;
    private Cell[][] cellArray = new Cell[15][15]; // to store correct answer
    private static Cell[][] cellArray1 = new Cell[15][15];// to store user answer
    private JLabel jl2;
    
    
    Letter.TYPE_LETTER empty = Letter.TYPE_LETTER.EMPTY;
    
    JPanel clue_part = new JPanel(new FlowLayout(FlowLayout.CENTER));
   
    JTextField[] txtFieldAry = null;
    JButton[] buttonAry = null;
    int i, row, col; String tfValue, str; char ch; String emptyStr = new String();
    private static ArrayList<String> answerAL = new ArrayList<>();
    private static ArrayList<String> ansAL = new ArrayList<>(); // the rest answers
    private static ArrayList<String> currentAL = new ArrayList<>();// the answers for CW
    private static ArrayList<String> clueAL = new ArrayList<>();// clues for CW
    private static ArrayList<String> type = new ArrayList<>();
    private static ArrayList<String> cAL = new ArrayList<String>();
    private static int index = 1;
    
    ArrayList<Character> user_input = new ArrayList<>();
    
    Letter.TYPE_LETTER l; Font f;
    CrosswordDisplayer(int number, boolean eng, boolean cs, boolean gk){
        emptyGrid();
        getCrossword(number,eng,cs,gk);
        markCrossword();
        
        for(int row = 0; row < 15; row++){
            for(int col = 0; col < 15; col++){
                if(cellArray[row][col].getIndex1()!=0 || cellArray[row][col].getIndex2()!=0)
                    System.out.println(cellArray[row][col].getIndex1()+", "+cellArray[row][col].getIndex2());
             }    
            }
        
        displayClue();
        
    }
    
    
    
    
    
    public void insert_in_grid(ArrayList<Character> ch, int id){
        int x=0, y=0, j=0,len; 
        String ad; char c;
        boolean emptyFill = false;
        
        ad = type.get(id);
        System.out.println("index is "+id);
        Font f = new Font("TimesRoman",Font.BOLD,15); 
        //jl1 = new JLabel();
        jl2 = new JLabel(); 
        //jl1.setFont(f);
        jl2.setFont(f);
        //jl1.setBounds(18,9,30,30);
        jl2.setBounds(18,9,30,30);
        jl2.setBackground(Color.WHITE);
        
        
        
        point:
        for(int row = 0; row < 15; row++){
            for(int col = 0; col < 15; col++){
                if(cellArray[row][col].getIndex1() == id+1 || cellArray[row][col].getIndex2() == id+1){
                     x = row;
                     y = col;
                     break point;
                }
            }
        }
        
        
        //System.out.println("row and col is "+x+", "+y);
        System.out.println();
        
        len = ch.size();
        //System.out.println("char size is "+len);
        String prev = "";
            out:
            for(j = 0; j < (currentAL.get(id)).length(); j++){
                 if(j < len){
                     c = ch.get(j);
                     //System.out.println("char is "+c);
                     l = checkChar(c);
                     if( ad == "A"){
                         if(isAlphabet(c)==true){
                             if(cellArray1[x][y+j].getLetter() != empty){
                                System.out.println("correct");
                                cell[x][y+j].removeAll();
                                cell[x][y+j].revalidate();
                                cell[x][y+j].repaint();

                                if(cellArray1[x][y+j].getIndex1() != 0 && cellArray[x][y+j].getIndex2() == 0){
                                    JLabel jl2 = new JLabel(Integer.toString(cellArray[x][y+j].getIndex1()));
                                    jl2.setBounds(5,0,40,13);
                                    jl2.setFont(f);
                                    cell[x][y+j].add(jl2);
                                    
                                }
                                else if(cellArray1[x][y+j].getIndex1() != 0 && cellArray[x][y+j].getIndex2() != 0){
                                   JLabel jl2 = new JLabel(Integer.toString(cellArray[x][y+j].getIndex1())+", "+Integer.toString(cellArray[x][y+j].getIndex2()));
                                   jl2.setBounds(5,0,40,13); 
                                   jl2.setFont(f);
                                   cell[x][y+j].add(jl2);
                                   
                                }
                             }
                             JLabel jl1 = new JLabel();
                             jl1.setFont(f);
                             jl1.setBounds(18,9,30,30);
                             cellArray1[x][y+j].setLetter(l);
                             str = cellArray1[x][y+j].getLetter().toString();
                             jl1.setText(str);
                             cell[x][y+j].add(jl1);
                             
                         }
                         else{
                             JLabel jl1 = new JLabel();
                             jl1.setFont(f);
                             jl1.setBounds(18,9,30,30);
                             jl1.setText(Character.toString(c));
                             cell[x][y+j].add(jl1);
                         }
                     }
                     else if(ad == "D"){
                         if(isAlphabet(c)==true){
                             if(cellArray1[x+j][y].getLetter() != empty){
                                System.out.println("correct");
                                cell[x+j][y].removeAll();
                                cell[x+j][y].revalidate();
                                cell[x+j][y].repaint();
                                
                                if(cellArray1[x+j][y].getIndex1() != 0 && cellArray[x+j][y].getIndex2() == 0){
                                    JLabel jl2 = new JLabel(Integer.toString(cellArray[x+j][y].getIndex1()));
                                    jl2.setBounds(5,0,40,13);
                                    jl2.setFont(f);
                                    cell[x+j][y].add(jl2);
                                }
                                else if(cellArray1[x+j][y].getIndex1() != 0 && cellArray[x+j][y].getIndex2() != 0){
                                   JLabel jl2 = new JLabel(Integer.toString(cellArray[x+j][y].getIndex1())+", "+Integer.toString(cellArray[x+j][y].getIndex2()));
                                   jl2.setBounds(5,0,40,13); 
                                   jl2.setFont(f);
                                   cell[x+j][y].add(jl2);
                                }
                             }
                             JLabel jl1 = new JLabel();
                             jl1.setFont(f);
                             jl1.setBounds(18,9,30,30);
                             cellArray1[x+j][y].setLetter(l);
                             str = cellArray1[x+j][y].getLetter().toString();
                             jl1.setText(str);
                             cell[x+j][y].add(jl1);
                         }
                         else{
                             JLabel jl1 = new JLabel();
                             jl1.setFont(f);
                             jl1.setBounds(18,9,30,30);
                             jl1.setText(Character.toString(c));
                             cell[x+j][y].add(jl1);
                         }
                     }
                 }
                 else{
                    emptyFill = true;
                    break out;
                }
            }
            System.out.println("al size "+currentAL.get(id).length());
            
            if(emptyFill == true){
                
                while(j < (currentAL.get(id)).length()){
                   
                   if(ad == "A"){
                       cellArray1[x+j][y].setLetter(empty);
                       cell[x][y+j].removeAll();
                       cell[x][y+j].revalidate();
                       cell[x][y+j].repaint();

                        if(cellArray[x][y+j].getIndex1() != 0 && cellArray[x][y+j].getIndex2() == 0){
                            JLabel jl2 = new JLabel(Integer.toString(cellArray[x][y+j].getIndex1()));
                            jl2.setBounds(5,0,40,13);
                            jl2.setFont(f);
                            cell[x][y+j].add(jl2);
                        }
                        else if(cellArray[x][y+j].getIndex1() != 0 && cellArray[x][y+j].getIndex2() != 0){
                           JLabel jl2 = new JLabel(Integer.toString(cellArray[x][y+j].getIndex1())+", "+Integer.toString(cellArray[x][y+j].getIndex2()));
                           jl2.setBounds(5,0,40,13); 
                           jl2.setFont(f);
                           cell[x][y+j].add(jl2);
                        }
                   }
                   else if(ad == "D"){
                       cellArray1[x+j][y].setLetter(empty);
                       cell[x+j][y].removeAll();
                       cell[x+j][y].revalidate();
                       cell[x+j][y].repaint();
                        if(cellArray[x+j][y].getIndex1() != 0 && cellArray[x+j][y].getIndex2() == 0){
                            JLabel jl2 = new JLabel(Integer.toString(cellArray[x+j][y].getIndex1()));
                            jl2.setBounds(5,0,40,13);
                            jl2.setFont(f);
                            cell[x+j][y].add(jl2);
                        }
                        else if(cellArray[x+j][y].getIndex1() != 0 && cellArray[x+j][y].getIndex2() != 0){
                           JLabel jl2 = new JLabel(Integer.toString(cellArray[x+j][y].getIndex1())+", "+Integer.toString(cellArray[x+j][y].getIndex2()));
                           jl2.setBounds(5,0,40,13); 
                           jl2.setFont(f);
                           cell[x+j][y].add(jl2);
                        }
                   } 
                   j++;
                }
                
            }
            
         
    }

    
 
  
    
    public void displayClue(){
        
        JPanel clue_part = new JPanel();
        JPanel clue_data_display = new JPanel(new GridLayout(clueAL.size()+3, 2,0,7));
    
        
        txtFieldAry = new JTextField[clueAL.size()];
        buttonAry = new JButton[clueAL.size()];
        
        //displaying headings
        JLabel clue = new JLabel("CLUES");
        clue.setSize(200,200);
        Font f = new Font("Verdana",Font.BOLD,17);
        clue.setFont(f);
        JLabel answer = new JLabel("ANSWERS",JLabel.CENTER);
        answer.setFont(f);
        
        JPanel ans_panel = new JPanel();
        ans_panel.add(answer);
        clue_data_display.add(clue);
        clue_data_display.add(ans_panel);
        //clue_data_display.add(new JLabel(""));
      
        //add clue and answer
        for(int i = 0; i < clueAL.size();i++){
    JPanel txt_button_panel = new JPanel(new FlowLayout());
        	
            txtFieldAry[i] = new JTextField(10);
           // txtFieldAry[i].setPreferredSize(new Dimension(10,35));
            txtFieldAry[i].setBounds(0,10,300,25);
            txt_button_panel.add(txtFieldAry[i]);
            buttonAry[i] = new JButton("SUBMIT");
            buttonAry[i].setName(Integer.toString(i));
            buttonAry[i].setBounds(325,10,300,45);
            txt_button_panel.add(buttonAry[i]);
            clue_data_display.add(new JLabel(i+1 + "." +clueAL.get(i) + "(" + type.get(i)  + ")"));
            clue_data_display.add(txt_button_panel);
            buttonAry[i].addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JTextField input = new JTextField();
                JButton input_button = (JButton)e.getSource();
                
                int index = Integer.parseInt(input_button.getName());
                System.out.println(index);
                input = txtFieldAry[index];
                    String s = input.getText();
                        System.out.println("input accepted ");
                        System.out.println(s);
                        for(int j =0;j < s.length();j++){
                            user_input.add(Character.toUpperCase(s.charAt(j)));
                            System.out.println(Character.toUpperCase(s.charAt(j)));      
                            
                        }
                        insert_in_grid(user_input,index);
                        System.out.println(user_input);
                        user_input.clear(); 
            }
        });
            //txtFieldAry[i].addKeyListener(new CustomKeyListener());
        }
        //button
        JButton submit  = new JButton("SUBMIT");
         submit.setFont(f);
         JPanel submit_panel = new JPanel();
         submit.setBounds(5,10,450,55);
         submit_panel.add(submit);
       // submit.setPreferredSize(new Dimension(200,200));
      
        clue_data_display.add(submit_panel);    
        clue_part.add(clue_data_display);
        container.add(clue_part);
        submit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame last_page = new JFrame("last page");
                JPanel container = new JPanel(new FlowLayout());
                
                last_page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                last_page.setSize(1000, 1000);
                JScrollPane sp = new JScrollPane(container);   
                sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
                sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
                last_page.add(sp);  
                
                JPanel heading = new JPanel();
                heading.setBounds(0, 0, last_page.getWidth(), 200);
                heading.setPreferredSize(new Dimension(1000,250));
                heading.setBackground(Color.red);
                JLabel header = new JLabel("Let's Play Crossword Together!");
                
                
                frame.setVisible(false);
               
                last_page.setVisible(true);
                
                
                JPanel grid_part = new JPanel();
                grid_part.setLayout(new GridLayout(2, 2, 75, -275));
           
                JPanel answer_grid = new JPanel(new GridLayout(0,15));
                answer_grid.setPreferredSize(new Dimension(600,600));
                answer_grid.setSize(450,450);
                answer_grid.setBounds(100, 300, 650, 650);
                
                JPanel answer_cell[][] = new JPanel[15][15];
                JPanel user_cell[][] = new JPanel[15][15];
                JPanel user_grid = new JPanel(new GridLayout(0,15));
                user_grid.setBounds(100, 300, 650, 650);
                user_grid.setPreferredSize(new Dimension(600,600));
                user_grid.setSize(450,450);
               
                JLabel jans = new JLabel("Correct Answer");
                Font f1 = new Font("TimesRoman",Font.BOLD,25);
                jans.setFont(f1);
                
                JLabel juser = new JLabel("Your Answer");
                juser.setFont(f1);
                
                grid_part.add(user_grid);
               
                grid_part.add(answer_grid);
                grid_part.add(juser);
                grid_part.add(jans);
                
                //container.add(heading);
                container.add(grid_part);
                //last_page.add(grid_part);
                
                for(int row = 0; row < 15; row++){
                    for(int col = 0; col < 15; col++){
                        answer_cell[row][col] = new  JPanel(null);
                        Font f = new Font("TimesRoman",Font.BOLD,15);
                        if(cellArray[row][col].getLetter() != Letter.TYPE_LETTER.EMPTY){
                            str = cellArray[row][col].getLetter().toString();
                            JLabel jl1 = new JLabel(str);
                            jl1.setFont(f);
                            jl1.setBounds(15,6,30,30);
                            //answer_cell[row][col].setBackground(Color.WHITE);
                            answer_cell[row][col].add(jl1);
                        }
                        else{
                            answer_cell[row][col].setBackground(Color.BLACK);
                        }
                        answer_cell[row][col].setBorder(new LineBorder(Color.WHITE));
                        f = new Font("TimesRoman",Font.BOLD,11);
                         if(cellArray[row][col].getIndex1() != 0 && cellArray[row][col].getIndex2() == 0){
                            JLabel jl2 = new JLabel(Integer.toString(cellArray[row][col].getIndex1()));
                            jl2.setBounds(5,0,40,13);
                            jl2.setFont(f);
                            answer_cell[row][col].add(jl2);
                        }
                        else if(cellArray[row][col].getIndex1() != 0 && cellArray[row][col].getIndex2() != 0){                        
                            JLabel jl2 = new JLabel(Integer.toString(cellArray[row][col].getIndex1())+", "+Integer.toString(cellArray[row][col].getIndex2()));
                            jl2.setBounds(5,0,40,13);
                            jl2.setFont(f);
                            answer_cell[row][col].add(jl2);
                        }
                    }
                }                
                
                for(int row = 0; row < 15; row++){
                    for(int col = 0; col < 15; col++){
                        user_cell[row][col] = new  JPanel(null);
                        Font f = new Font("TimesRoman",Font.BOLD,15);
                        if(cellArray[row][col].getLetter() == Letter.TYPE_LETTER.EMPTY){
                                user_cell[row][col].setBackground(Color.BLACK);
                        }
                        if(cellArray1[row][col].getLetter() != Letter.TYPE_LETTER.EMPTY){
                            str = cellArray1[row][col].getLetter().toString();
                            JLabel jl1 = new JLabel(str);
                            jl1.setFont(f);
                            jl1.setBounds(15,6,30,30);
                            user_cell[row][col].add(jl1);
                        }
                        
                      
                        user_cell[row][col].setBorder(new LineBorder(Color.WHITE));
                        f = new Font("TimesRoman",Font.BOLD,11);
                         if(cellArray[row][col].getIndex1() != 0 && cellArray[row][col].getIndex2() == 0){
                            JLabel jl2 = new JLabel(Integer.toString(cellArray1[row][col].getIndex1()));
                            jl2.setBounds(5,0,40,13);
                            jl2.setFont(f);
                            user_cell[row][col].add(jl2);
                        }
                        else if(cellArray[row][col].getIndex1() != 0 && cellArray[row][col].getIndex2() != 0){                        
                            JLabel jl2 = new JLabel(Integer.toString(cellArray1[row][col].getIndex1())+", "+Integer.toString(cellArray1[row][col].getIndex2()));
                            jl2.setBounds(5,0,40,13);
                            jl2.setFont(f);
                            user_cell[row][col].add(jl2);
                        }
                    }
                }                
                
                for(int i = 0; i < 15; i++){
                    for(int j = 0; j < 15; j++){
                        
                        user_grid.add(user_cell[i][j]);
                        answer_grid.add(answer_cell[i][j]);
                        
                    }
                }
                
                
            }
        });
       
    }
    /*
    public void displayUserInputs(){
        
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(cellArray1[i][j].getLetter() != empty){
                    jl1 = new JLabel();
                    jl1.setFont(f);
                    jl1.setBounds(18,9,30,30);
                    System.out.println(cellArray1[i][j].getLetter().toString());
                    jl1.setText(cellArray1[i][j].getLetter().toString());
                    cell[i][j].add(jl1);
                }
            }
        }
    }
    */
    public void emptyGrid(){
        frame = new JFrame("Crossword");
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        container.setSize(frame.getWidth(),frame.getHeight());
        frame.add(container);
        container.setLayout(new GridLayout(2,1,0,0));
        
        //scrollPane
        JScrollPane sp = new JScrollPane(container);   
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        frame.add(sp);  
        
        //the upper part
    
        JPanel crossword_part = new JPanel(new FlowLayout(FlowLayout.CENTER));
     
        jp = new JPanel(new GridLayout(0,15));
        jp.setPreferredSize(new Dimension(700,700));
        jp.setSize(650,650);
       
        //jp.setBorder(new LineBorder(Color.BLACK));
        crossword_part.add(jp);
        container.add(crossword_part);
        

        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                cell[i][j] = new JPanel(null);
                cell[i][j].setBorder(new LineBorder(Color.BLACK));
                jp.add(cell[i][j]);
                
                cellArray[i][j] = new Cell(empty,i,j);
                cellArray1[i][j] = new Cell(empty,i,j);
            }
        }
        frame.setVisible(true);
    }
   
    
    public void markCrossword(){
         Font f;
        int count = 0; String str;
        for(int row = 0; row < 15; row++){
            for(int col = 0; col < 15; col++){
                f = new Font("TimesRoman",Font.BOLD,15);
                if(cellArray[row][col].getLetter() != Letter.TYPE_LETTER.EMPTY){
                    cell[row][col].setBorder(new LineBorder(Color.WHITE));
                    /*
                    str = cellArray[row][col].getLetter().toString();
                    jl1 = new JLabel(str);
                    jl1.setFont(f);
                    jl1.setBounds(18,9,30,30);
                    cell[row][col].add(jl1);
                    */
                }
                else{
                    cell[row][col].setBorder(new LineBorder(Color.WHITE));
                    cell[row][col].setBackground(Color.BLACK);
                }
                
                //print index
                f = new Font("TimesRoman",Font.BOLD,11);
                
                if(cellArray[row][col].getIndex1() != 0 && cellArray[row][col].getIndex2() == 0){
                    //System.out.println("Id is "+cellArray[row][col].getIndex1());
                    jl2 = new JLabel(Integer.toString(cellArray[row][col].getIndex1()));
                    jl2.setBounds(5,0,40,13);
                    jl2.setFont(f);
                    cell[row][col].add(jl2);
                }
                else if(cellArray[row][col].getIndex1() != 0 && cellArray[row][col].getIndex2() != 0){
                    //System.out.println("Id is "+cellArray[row][col].getIndex1()+" and "+cellArray[row][col].getIndex2());
                    jl2 = new JLabel(Integer.toString(cellArray[row][col].getIndex1())+", "+Integer.toString(cellArray[row][col].getIndex2()));
                    jl2.setBounds(5,0,40,13);
                    jl2.setFont(f);
                    cell[row][col].add(jl2);
                }
                
                
                //mark index in cellArray1
                if(cellArray[row][col].getIndex1() != 0)
                    cellArray1[row][col].setIndex1(cellArray[row][col].getIndex1());
                else if(cellArray[row][col].getIndex2() != 0)
                    cellArray1[row][col].setIndex2(cellArray[row][col].getIndex2());
            }
        }  
    }
    
    public void getCrossword(int number, boolean eng, boolean cs, boolean gk){
        String answer;
        Letter.TYPE_LETTER l;
        ClueAnswer clueAns = new ClueAnswer(number,eng,cs,gk);
        answerAL = clueAns.getAnsArrayList();
        cAL = clueAns.getClueArrayList();
        ansAL = answerAL;
        
        System.out.println(ansAL);
        answer = ansAL.get(0);
        
        char c; int row = 2, col = 0; boolean first, second, whole, done;
        int x, y;
        for(int i = 0; i < answer.length(); i++){
            c = answer.charAt(i);
            //System.out.println(c);
            l = checkChar(c);
            if(i == 0 )
                cellArray[row][col] = new Cell(l,row,col,index,0);
            else 
                cellArray[row][col] = new Cell(l,row,col);
            col++;
        }
        currentAL.add(ansAL.get(0));
        type.add("A");
        clueAL.add(cAL.get(0));
           
        next:
        for(int i = 1; i < ansAL.size(); i++){
            answer = ansAL.get(i);
            for(int j = 0; j < answer.length(); j++){
                c = answer.charAt(j);
                l = checkChar(c);
                y = answer.length()-j-1;
                x = answer.length()-y-1;
                
                for(row = 0; row < 15; row++){
                    colLoop:
                    for(col = 0; col < 15; col++){
                        whole = true; first = true; second = true;int k = 0;
                        if(l == cellArray[row][col].getLetter()){
                            if(i%2 == 1 && j == 0){
                                whole = noIntersection(answer,row,col,'r');
                                if( whole == false ){
                                    continue colLoop;
                                }
                                if( whole == true ){
                                    if(cellArray[row][col].getIndex1() == 0){
                                        cellArray[row][col].setIndex1(++index);
                                     }
                                    else{
                                        cellArray[row][col].setIndex2(++index);
                                    }   
                                        
                                    for(k = 1; k < answer.length(); k++){
                                        c = answer.charAt(k);
                                        l = checkChar(c);
                                        cellArray[row+k][col] = new Cell(l,row+k,col);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("D");
                                    continue next;
                                }
                            }
                            else if(i%2 == 0  && j == 0){
                                whole = noIntersection(answer,row,col,'c');
                                if( whole == false ){
                                    continue colLoop;
                                  }
                                if( whole == true ){
                                    if(cellArray[row][col].getIndex1() == 0)
                                        cellArray[row][col].setIndex1(++index);
                                    else
                                        cellArray[row][col].setIndex2(++index);
                                        
                                    for(k = 1; k < answer.length(); k++){
                                        c = answer.charAt(k);
                                        l = checkChar(c);
                                        cellArray[row][col+k] = new Cell(l,row,col+k);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("A");
                                    continue next;
                                }
                            }
                            else if (i%2 == 1 && j > 0 && j < answer.length()-1){
                                whole = checkIntersection(answer,row,col,j,'r');
                                if(whole == false)
                                    continue colLoop;
                                else{
                                    for(k = 1; k <= x; k++){
                                        c = answer.charAt(j-k);
                                        l = checkChar(c);
                                        if(k == x){
                                            if(cellArray[row-k][col].getIndex1() == 0)
                                                cellArray[row-k][col] = new Cell(l,row-k,col,++index,0);
                                            else
                                                cellArray[row-k][col].setIndex2(++index);
                                         }
                                        else
                                            cellArray[row-k][col] = new Cell(l,row-k,col);
                                    }
                                    
                                    for(k = 1; k <= y; k++){
                                        c = answer.charAt(j+k);
                                        l = checkChar(c);
                                        cellArray[row+k][col] = new Cell(l,row+k,col);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("D");
                                    continue next;
                                }
                            }
                            else if( i % 2 == 0 && j > 0 && j < answer.length()-1 ){
                                whole = checkIntersection(answer,row,col,j,'c');
                                
                                if( whole == false ){
                                    continue colLoop;
                                }
                                if( whole == true ){
                                    for(k = 1; k <= x; k++){
                                        c = answer.charAt(j-k);
                                        l = checkChar(c);
                                        if(k == x){
                                            if(cellArray[row][col-k].getIndex1() == 0)
                                                cellArray[row][col-k] = new Cell(l,row,col-k,++index,0);
                                            else
                                                cellArray[row][col-k].setIndex2(++index);                                         }
                                        else
                                            cellArray[row][col-k] = new Cell(l,row,col-k);
                                    }
                                    
                                    for(k = 1; k <= y; k++){
                                        c = answer.charAt(j+k);
                                        l = checkChar(c);
                                        cellArray[row][col+k] = new Cell(l,row,col+k);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("A");
                                    continue next;
                                }
                            }
                            else if( i % 2 == 1 && j == answer.length()-1){
                                whole = atLast(answer,row,col,'r');
                                if( whole == false ){
                                    continue colLoop;
                                }
                                else{
                                    for(k = 1;k < answer.length(); k++){
                                        c = answer.charAt(answer.length()-k-1);
                                        l = checkChar(c);
                                        if(k == answer.length()-1){
                                            if(cellArray[row-k][col].getIndex1() == 0)
                                                cellArray[row-k][col] = new Cell(l,row-k,col,++index,0);
                                              else
                                                cellArray[row-k][col].setIndex2(++index);
                                         }
                                        else
                                            cellArray[row-k][col] = new Cell(l,row-k,col);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("D");
                                    continue next;
                                }
                            }
                            else if( i % 2 == 0 && j == answer.length()-1){
                                whole = atLast(answer,row,col,'c');
                                if( whole == false ){
                                    continue colLoop;
                                }
                                else{
                                    for(k = 1;k < answer.length(); k++){
                                        c = answer.charAt(answer.length()-k-1);
                                        l = checkChar(c);
                                        if(k == answer.length()-1){
                                            if(cellArray[row][col-k].getIndex1() == 0)
                                                cellArray[row][col-k] = new Cell(l,row,col-k,++index,0);
                                              else
                                                cellArray[row][col-k].setIndex2(++index);
                                         }
                                        else
                                            cellArray[row][col-k] = new Cell(l,row,col-k);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("A");
                                    continue next;
                                }
                            }
                            
                    }//if
                }//end of col
                
              }//end of row
                
            }//end of j
        }//end of for i loop
        
        ansAL.removeAll(currentAL);
        System.out.println(currentAL);
        System.out.println(ansAL);
        if(!ansAL.isEmpty()){
            checkAllPossible();
        }
        
        
        ansAL.removeAll(currentAL);
        System.out.println(currentAL);
        System.out.println(ansAL);
        if(!ansAL.isEmpty()){
            checkAllPossible();
        }
        
        
        ansAL.removeAll(currentAL);
        System.out.println();
        System.out.println(currentAL);
        System.out.println(ansAL);
        if(!ansAL.isEmpty()){
            checkAllPossible();
        }
        
        System.out.println(index);
        System.out.println(clueAL);
    }
    
    public void checkAllPossible(){
        String answer;
        char c; int row, col, x, y;
        Letter.TYPE_LETTER l;
        boolean whole;
        int k = 0;
        
        next:
        for(int i = 0; i < ansAL.size(); i++){
            answer = ansAL.get(i);
            for(int j = 0; j < answer.length(); j++){
                c = answer.charAt(j);
                l = checkChar(c);
                for(row = 0; row < 15; row++){
                    colLoop:
                    for(col = 0; col < 15; col++){
                        if(l == cellArray[row][col].getLetter()){
                            if(j == 0)
                            {
                                whole = noIntersection(answer,row,col,'r');
                               
                                if( whole == true ){
                                    if(cellArray[row][col].getIndex1() == 0)
                                        cellArray[row][col].setIndex1(++index);
                                    else 
                                        cellArray[row][col].setIndex2(++index);
                                
                                    for(k = 1; k < answer.length(); k++){
                                        c = answer.charAt(k);
                                        l = checkChar(c);
                                        cellArray[row+k][col] = new Cell(l,row+k,col);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("D");
                                    System.out.println("true at first Down");
                                    continue next;
                                    
                                }
                                whole = noIntersection(answer,row,col,'c');
                                
                                if( whole == true ){
                                    if(cellArray[row][col].getIndex1() == 0)
                                        cellArray[row][col].setIndex1(++index);
                                    else 
                                        cellArray[row][col].setIndex2(++index);
                                
                                    for(k = 1; k < answer.length(); k++){
                                        c = answer.charAt(k);
                                        l = checkChar(c);
                                        cellArray[row][col+k] = new Cell(l,row,col+k);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("A");
                                    System.out.println("true at first Across");
                                    continue next;
                                }
                                break colLoop;
                            }
                            else if(j > 0 && j < answer.length()-1){
                                y = answer.length()-j-1;
                                x = answer.length()-y-1;
                                whole = checkIntersection(answer,row,col,j,'r');
                                if(whole == true){                                
                                    for(k = 1; k <= x; k++){
                                        c = answer.charAt(j-k);
                                        l = checkChar(c);
                                        if(k == x){
                                            if(cellArray[row-k][col].getIndex1() == 0)
                                                cellArray[row-k][col] = new Cell(l,row-k,col,++index,0);
                                            else
                                                cellArray[row-k][col].setIndex2(++index);
                                            }
                                        else
                                            cellArray[row-k][col] = new Cell(l,row-k,col);
                                    }
                                    
                                    for(k = 1; k <= y; k++){
                                        c = answer.charAt(j+k);
                                        l = checkChar(c);
                                        cellArray[row+k][col] = new Cell(l,row+k,col);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("D");
                                    System.out.println("true at middle Down ");
                                    continue next;
                                }
                                
                                whole = checkIntersection(answer,row,col,j,'c');             
                                if( whole == true ){
                                    for(k = 1; k <= x; k++){
                                        c = answer.charAt(j-k);
                                        l = checkChar(c);
                                        if(k == x){
                                            if(cellArray[row][col-k].getIndex1() == 0)
                                                cellArray[row][col-k] = new Cell(l,row,col-k,++index,0);
                                            else
                                                cellArray[row][col-k].setIndex2(++index);
                                            }
                                        else
                                            cellArray[row][col-k] = new Cell(l,row,col-k);
                                    }
                                    
                                    for(k = 1; k <= y; k++){
                                        c = answer.charAt(j+k);
                                        l = checkChar(c);
                                        cellArray[row][col+k] = new Cell(l,row,col+k);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("A");
                                    System.out.println("true at middle across");
                                    continue next;
                                }
                                break colLoop;
                            }
                            
                            else if(j == answer.length()-1){
                                whole = atLast(answer,row,col,'r');
                                if( whole == true ){  
                                    for(k = 1;k < answer.length(); k++){
                                        c = answer.charAt(answer.length()-k-1);
                                        l = checkChar(c);
                                        if(k == answer.length()-1){
                                            if(cellArray[row-k][col].getIndex1() == 0)
                                                cellArray[row-k][col] = new Cell(l,row-k,col,++index,0);
                                            else
                                                cellArray[row-k][col].setIndex2(++index); 
                                        }
                                        else
                                            cellArray[row-k][col] = new Cell(l,row-k,col);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("D");
                                    System.out.println("true at last down");
                                    continue next;
                                }
                                
                                whole = atLast(answer,row,col,'c');
                                if( whole == true ){
                                    for(k = 1;k < answer.length(); k++){
                                        c = answer.charAt(answer.length()-k-1);
                                        l = checkChar(c);
                                        if(k == answer.length()-1){
                                            if(cellArray[row][col-k].getIndex1() == 0)
                                                cellArray[row][col-k] = new Cell(l,row,col-k,++index,0);
                                            else
                                                cellArray[row][col-k].setIndex2(++index);
                                            }
                                        else
                                            cellArray[row][col-k] = new Cell(l,row,col-k);
                                    }
                                    currentAL.add(ansAL.get(i));
                                    clueAL.add(cAL.get(i));
                                    type.add("A");
                                    System.out.println("true at last Across");
                                    continue next;
                                }
                                break colLoop;
                            
                        }
                    }
                }
                }
                
            }
        }
    }
    
    
    
    public boolean noIntersection(String str, int row, int col, char c){
        char ch; Letter.TYPE_LETTER l; boolean check, check1, prev;
        check = true; check1 = true; prev = true;
        int k = 0;
        
            loop:
            for(k = 1; k < str.length(); k++){
                try{
                    if(c == 'r'){
                        if(cellArray[row+k][col].getLetter() == Letter.TYPE_LETTER.EMPTY){
                            check = true;
                        }
                        else{
                            check =false;
                            break loop;
                        }
                    }
                    else if(c == 'c'){
                        if(cellArray[row][col+k].getLetter() == Letter.TYPE_LETTER.EMPTY){
                            check = true;
                        }
                        else{
                            check =false;
                            break loop;
                        }
                    }
                 }
                    catch(IndexOutOfBoundsException e){
                        check = false;
                        break loop;
                    }
                }//for 
            
            
            
            if ( check == false ){
                return false;
            }
            
            if(c == 'r'){
                if(row+str.length()-1 < 14){
                    if(cellArray[row+str.length()][col].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check = true;
                    else 
                        check =false;
                }
                
                if(row > 0){
                    if(cellArray[row-1][col].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        prev = true;
                    else
                        prev = false;
                }
                
                 if(col == 0){
                       loop1:
                       for(k = 1; k < str.length(); k++){
                           if(cellArray[row+k][col+1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                                check1 = true;
                           else{
                                check1 = false;
                                break loop1;
                            }
                        }
                   
                }
                else if(col == 14){
                     loop2:
                     for(k = 1; k < str.length(); k++){
                         if(cellArray[row+k][col-1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                              check1 = true;
                         else{
                              check1 = false;
                              break loop2;
                         }                 
                     }
                }
                else{
                       loop3:
                       for(k = 1; k < str.length(); k++){
                           if(cellArray[row+k][col-1].getLetter() ==  Letter.TYPE_LETTER.EMPTY && cellArray[row+k][col+1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                               check1 = true;
                           else{
                               check1 = false;
                               break loop3;
                            }
                       }
                }
            
            }
            else if(c == 'c'){
                if(col+str.length()-1 < 14){
                    if(cellArray[row][col+str.length()].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check = true;
                    else 
                        check =false;
                }
                
                if(col > 0){
                    if(cellArray[row][col-1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        prev = true;
                    else
                        prev = false;
                }
                
                if(row == 0){
                    loop:
                    for(k = 1; k < str.length(); k++){
                        if(cellArray[row+1][col+k].getLetter() == Letter.TYPE_LETTER.EMPTY)
                            check1 = true;
                        else{
                            check1 = false;
                            break loop;
                        }
                    }
                }
                else if(row == 14){
                    loop1:
                    for(k = 1; k < str.length(); k++){
                       if(cellArray[row-1][col+k].getLetter() == Letter.TYPE_LETTER.EMPTY)
                           check1 = true;
                       else{
                           check1 = false;
                           break loop1;
                        }
                    }
                }
                else{
                     loop2:
                     for(k = 1; k < str.length(); k++){
                         if(cellArray[row-1][col+k].getLetter() ==  Letter.TYPE_LETTER.EMPTY && cellArray[row+1][col+k].getLetter() == Letter.TYPE_LETTER.EMPTY)
                             check1 = true;
                         else{
                             check1 = false;
                             break loop2;
                            }
                     }
                }
            }
            if(check == true && prev == true && check1 == true){
                return true;
            }
            return false;
    }
    
    
        public boolean atLast(String str, int row, int col, char c){
        char ch; Letter.TYPE_LETTER l; boolean check, check1, prev;
        check = true; check1 = true; prev = true;
        int k = 0;
        
            loop:
            for(k = 1; k < str.length(); k++){
                try{
                    if(c == 'r'){
                        if(cellArray[row-k][col].getLetter() == Letter.TYPE_LETTER.EMPTY){
                            check = true;
                        }
                        else{
                            check =false;
                            break loop;
                        }
                    }
                    else if(c == 'c'){
                        if(cellArray[row][col-k].getLetter() == Letter.TYPE_LETTER.EMPTY){
                            check = true;
                        }
                        else{
                            check =false;
                            break loop;
                        }
                    }
                 }
                    catch(IndexOutOfBoundsException e){
                        check = false;
                        break loop;
                    }
                }//for 
            
            if ( check == false ){
                return false;
            }
            
            if(c == 'r'){
                if(row-str.length()+1 > 0){
                    if(cellArray[row-str.length()][col].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check = true;
                    else 
                        check =false;
                }
                
                if(row < 14){
                    if(cellArray[row+1][col].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        prev = true;
                    else
                        prev = false;
                }
                
                 if(col == 0){
                       loop1:
                       for(k = 1; k < str.length(); k++){
                           if(cellArray[row-k][col+1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                                check1 = true;
                           else{
                                check1 = false;
                                break loop1;
                            }
                        }
                   
                }
                else if(col == 14){
                     loop2:
                     for(k = 1; k < str.length(); k++){
                         if(cellArray[row-k][col-1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                              check1 = true;
                         else{
                              check1 = false;
                              break loop2;
                         }                 
                     }
                }
                else{
                       loop3:
                       for(k = 1; k < str.length(); k++){
                           if(cellArray[row-k][col-1].getLetter() ==  Letter.TYPE_LETTER.EMPTY && cellArray[row-k][col+1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                               check1 = true;
                           else{
                               check1 = false;
                               break loop3;
                            }
                       }
                }
            
            }
            else if(c == 'c'){
                if(col-str.length()+1 > 0){
                    if(cellArray[row][col-str.length()].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check = true;
                    else 
                        check =false;
                }
                
                if(col < 14){
                    if(cellArray[row][col+1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        prev = true;
                    else
                        prev = false;
                }
                
                if(row == 0){
                    loop:
                    for(k = 1; k < str.length(); k++){
                        if(cellArray[row+1][col-k].getLetter() == Letter.TYPE_LETTER.EMPTY)
                            check1 = true;
                        else{
                            check1 = false;
                            break loop;
                        }
                    }
                }
                else if(row == 14){
                    loop1:
                    for(k = 1; k < str.length(); k++){
                       if(cellArray[row-1][col-k].getLetter() == Letter.TYPE_LETTER.EMPTY)
                           check1 = true;
                       else{
                           check1 = false;
                           break loop1;
                        }
                    }
                }
                else{
                     loop2:
                     for(k = 1; k < str.length(); k++){
                         if(cellArray[row-1][col-k].getLetter() ==  Letter.TYPE_LETTER.EMPTY && cellArray[row+1][col-k].getLetter() == Letter.TYPE_LETTER.EMPTY)
                             check1 = true;
                         else{
                             check1 = false;
                             break loop2;
                            }
                     }
                }
            }
            if(check == true && prev == true && check1 == true){
                return true;
            }
            return false;
    }
    
    
    public boolean checkIntersection(String str, int row, int col, int j, char c){
        int i, k, x, y;
        boolean check1, check2, check3, check4;
        y = str.length()-j-1;
        x = str.length()-y-1;
        check1 = true;
        check2 = true;
        check3 = true;
        check4 = true;
        if(c == 'r'){
         loop1:
         try{
            for(i = 1; i <= x; i++){
                if(cellArray[row-i][col].getLetter() == Letter.TYPE_LETTER.EMPTY)
                    check1 = true;
                else{
                    check1 = false;
                    break loop1;
                }
            }
          }
        
        catch(IndexOutOfBoundsException e){
            check1 = false;
            break loop1;
        }
          loop2:
          try{
            for(i = 1; i <= y; i++){
                if(cellArray[row+i][col].getLetter() == Letter.TYPE_LETTER.EMPTY)
                    check2 = true;
                else{
                    check2 = false;
                    break loop2;
                }
            }
          }
          catch(IndexOutOfBoundsException e){
            check2 = false;
            break loop2;
        }
       if(!check1 || !check2 ){
           return false;
       }
                
            if(col == 0){
                loop1:
                for(i = 1; i <= x; i++){
                    if(cellArray[row-i][col+1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check1 = true;
                    else{
                        check1 = false;
                        break loop1;
                    }
                }

                loop2:            
                for(i = 1; i <= y; i++){
                    if(cellArray[row+i][col+1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check2 = true;
                    else{
                        check2 = false;
                        break loop2;
                    }
                }

            }
            else if(col == 14){
                loop1:
                for(i = 1; i <= x; i++){
                    if(cellArray[row-i][col-1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check1 = true;
                    else{
                        check1 = false;
                        break loop1;
                    }
                }
              
                loop2:
                for(i = 1; i <= y; i++){
                    if(cellArray[row+i][col-1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check2 = true;
                    else{
                        check2 = false;
                        break loop2;
                    }
                }              
            }
            else{
                loop1:
                for(i = 1; i <= x; i++){
                    if(cellArray[row-i][col-1].getLetter() == Letter.TYPE_LETTER.EMPTY && cellArray[row-i][col+1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check1 = true;
                    else{
                        check1 = false;
                        break loop1;
                    }
                }
                
                loop2:
                for(i = 1; i <= y; i++){
                    if(cellArray[row+i][col-1].getLetter() == Letter.TYPE_LETTER.EMPTY &&  cellArray[row+i][col+1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check2 = true;
                    else{
                        check2 = false;
                        break loop2;
                    }
                }
            }
            
            if(row-x > 0){
                if(cellArray[row-x-1][col].getLetter() == Letter.TYPE_LETTER.EMPTY)
                    check3 = true;
                else
                    check3 = false;
            }
            
            if(row+y < 14){
                if(cellArray[row+y+1][col].getLetter() == Letter.TYPE_LETTER.EMPTY)
                    check4 = true;
                else
                    check4 = false;
            }
        }
        else if(c == 'c'){
         loop1:
         try{
            for(i = 1; i <= x; i++){
                if(cellArray[row][col-i].getLetter() == Letter.TYPE_LETTER.EMPTY)
                    check1 = true;
                else{
                    check1 = false;
                    break loop1;
                }
            }
          }
        
        catch(IndexOutOfBoundsException e){
            check1 = false;
            break loop1;
        }
          loop2:
          try{
            for(i = 1; i <= y; i++){
                if(cellArray[row][col+i].getLetter() == Letter.TYPE_LETTER.EMPTY)
                    check2 = true;
                else{
                    check2 = false;
                    break loop2;
                }
            }
          }
          catch(IndexOutOfBoundsException e){
            check2 = false;
            break loop2;
        }
       if(!check1 || !check2 ){
           return false;
       }
                
            if(row == 0){
                loop1:
                for(i = 1; i <= x; i++){
                    if(cellArray[row+1][col-i].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check1 = true;
                    else{
                        check1 = false;
                        break loop1;
                    }
                }

                loop2:            
                for(i = 1; i <= y; i++){
                    if(cellArray[row+1][col+i].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check2 = true;
                    else{
                        check2 = false;
                        break loop2;
                    }
                }

            }
            else if(row == 14){
                loop1:
                for(i = 1; i <= x; i++){
                    if(cellArray[row-1][col-i].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check1 = true;
                    else{
                        check1 = false;
                        break loop1;
                    }
                }
              
                loop2:
                for(i = 1; i <= y; i++){
                    if(cellArray[row-1][col+i].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check2 = true;
                    else{
                        check2 = false;
                        break loop2;
                    }
                }              
            }
            else{
                loop1:
                for(i = 1; i <= x; i++){
                    if(cellArray[row-1][col-i].getLetter() == Letter.TYPE_LETTER.EMPTY && cellArray[row+1][col-i].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check1 = true;
                    else{
                        check1 = false;
                        break loop1;
                    }
                }
                
                loop2:
                for(i = 1; i <= y; i++){
                    if(cellArray[row-1][col+i].getLetter() == Letter.TYPE_LETTER.EMPTY &&  cellArray[row+1][col+i].getLetter() == Letter.TYPE_LETTER.EMPTY)
                        check2 = true;
                    else{
                        check2 = false;
                        break loop2;
                    }
                }
            }
            
            if(col-x > 0){
                if(cellArray[row][col-x-1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                    check3 = true;
                else
                    check3 = false;
            }
            
            if(col+y < 14){
                if(cellArray[row][col+y+1].getLetter() == Letter.TYPE_LETTER.EMPTY)
                    check4 = true;
                else
                    check4 = false;
            }
        }
        
        if(check1 && check2 && check3 && check4)
            return true;
            
        return false;
        
    }
    
    public boolean isAlphabet(char c){
        if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
            return true;
        return false;
    }
    
    public Letter.TYPE_LETTER checkChar(char c){
        Letter.TYPE_LETTER l = Letter.TYPE_LETTER.EMPTY;
        if(c == 'a' || c == 'A'){
                l = Letter.TYPE_LETTER.A;
            }
        else if(c == 'b' || c == 'B'){
                l = Letter.TYPE_LETTER.B;
            }
        else if(c == 'c' || c == 'C'){
                l = Letter.TYPE_LETTER.C;
            } 
        else if(c == 'd' || c == 'D'){
                l = Letter.TYPE_LETTER.D;
            }
        else if(c == 'e' || c == 'E'){
                l = Letter.TYPE_LETTER.E;
            }
        else if(c == 'f' || c == 'F'){
                l = Letter.TYPE_LETTER.F;
            }
        else if(c == 'g' || c == 'G'){
                l = Letter.TYPE_LETTER.G;
            }
        else if(c == 'h' || c == 'H'){
                l = Letter.TYPE_LETTER.H;
            }
        else if(c == 'i' || c == 'I'){
                l = Letter.TYPE_LETTER.I;
            }
        else if(c == 'j' || c == 'J'){
                l = Letter.TYPE_LETTER.J;
            }
        else if(c == 'k' || c == 'K'){
                l = Letter.TYPE_LETTER.K;
            }
        else if(c == 'l' || c == 'L'){
                l = Letter.TYPE_LETTER.L;
            }
        else if(c == 'm' || c == 'M'){
                l = Letter.TYPE_LETTER.M;
            }
        else if(c == 'n' || c == 'N'){
                l = Letter.TYPE_LETTER.N;
            }
        else if(c == 'o' || c == 'O'){
                l = Letter.TYPE_LETTER.O;
            }
        else if(c == 'p' || c == 'P'){
                l = Letter.TYPE_LETTER.P;
            }
        else if(c == 'q' || c == 'Q'){
                l = Letter.TYPE_LETTER.Q;
            }
        else if(c == 'r' || c == 'R'){
                l = Letter.TYPE_LETTER.R;
            }
        else if(c == 's' || c == 'S'){
                l = Letter.TYPE_LETTER.S;
            }
        else if(c == 't' || c == 'T'){
                l = Letter.TYPE_LETTER.T;
            }
        else if(c == 'u' || c == 'U'){
                l = Letter.TYPE_LETTER.U;
            }
        else if(c == 'v' || c == 'V'){
                l = Letter.TYPE_LETTER.V;
            }
        else if(c == 'w' || c == 'W'){
                l = Letter.TYPE_LETTER.W;
            }
        else if(c == 'x' || c == 'X'){
                l = Letter.TYPE_LETTER.X;
            }
        else if(c == 'y' || c == 'Y'){
                l = Letter.TYPE_LETTER.Y;
            }
        else if(c == 'z' || c == 'Z'){
                l = Letter.TYPE_LETTER.Z;
            }
        return l;
    }
    
}
