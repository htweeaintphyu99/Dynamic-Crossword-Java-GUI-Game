package Crossword;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.*;
import java.util.*;

/**
 * Write a description of class GUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GUI
{
    private JFrame frame = new  JFrame();
    //private JLabel label;
    private JLabel jl1, jl2, jl3;
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7;
    private JButton jb1;
    private JCheckBox cb1, cb2, cb3;
    private JTextField tf;
    private boolean checkCb1, checkCb2, checkCb3, checkTf;
    private String numOfClues;
    private int number;
    private CrosswordDisplayer c;
    
    GUI(){
       cwGUI();
    } 
    
    
    public void cwGUI(){
       checkCb1 = false;
       checkCb2 = false;
       checkCb3 = false;
       checkTf = false;
       
       frame = new JFrame("Crossword game");
       frame.setLayout(new FlowLayout());
       frame.setSize(900,900);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       
       jp1 = new JPanel();
       jp1.setPreferredSize(new Dimension(450,70));
       jp1.setBorder(new LineBorder(Color.BLACK));
       
       jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
       jp2.setPreferredSize(new Dimension(800,50));
       //jp2.setBorder(new LineBorder(Color.BLACK));
       
       jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
       jp3.setPreferredSize(new Dimension(650,50));
       //jp3.setBorder(new LineBorder(Color.BLACK));
       
       jp4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
       jp4.setPreferredSize(new Dimension(650,50));
       //jp4.setBorder(new LineBorder(Color.BLACK));
       
       jp5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
       jp5.setPreferredSize(new Dimension(650,100));
       //jp5.setBorder(new LineBorder(Color.BLACK));
       
       jp6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
       jp6.setPreferredSize(new Dimension(800,50));
       //jp6.setBorder(new LineBorder(Color.BLACK));
       
       jp7 = new JPanel(new GridBagLayout());
       jp7.setPreferredSize(new Dimension(600,300));
       //jp7.setBorder(new LineBorder(Color.BLACK));
       
       frame.add(jp1);
       frame.add(jp2);
       frame.add(jp3);
       frame.add(jp4);
       frame.add(jp5);
       frame.add(jp6);
       frame.add(jp7);
       
       
       jl1 = new JLabel("Let's play crossword together");
       Font f = new Font("TimesRoman",Font.BOLD,25);
       jl1.setFont(f);
       jl1.setForeground(Color.blue);
       jl1.setBounds(300,80,350,30);
       
       jl2 = new JLabel("Subjects:");
       f = new Font("TimesRoman",Font.PLAIN,20);
       jl2.setFont(f);
       
       cb1 = new JCheckBox("English");
       cb2 = new JCheckBox("Computer Science");
       cb3 = new JCheckBox("General Knowledge");
       cb1.setFont(f);
       cb2.setFont(f);
       cb3.setFont(f);
       
       /*
       jl3 = new JLabel("Number of clues:");
       jl3.setFont(f);
       
       tf = new JTextField("");
       tf.setPreferredSize(new Dimension(70,40));
       tf.setFont(f);
       */
       jb1 = new JButton("PLAY");
       jb1.setPreferredSize(new Dimension(150,40));
       f = new Font("TimesRoman",Font.BOLD,20);
       jb1.setFont(f);
       jb1.setBackground(Color.gray);
       jb1.setForeground(Color.white);
       
       
       jp1.add(jl1);
       jp1.add(jl2);
       jp2.add(jl2);
       jp3.add(cb1);
       jp4.add(cb2);
       jp5.add(cb3);
       //jp6.add(jl3);
       //jp6.add(tf);
       jp7.add(jb1);
       frame.setVisible(true);
       cb1.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               if(cb1.isSelected() == true){
                   checkCb1 = true;
                }
            }
            });
            
       cb2.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               if(cb2.isSelected() == true){
                   checkCb2 = true;
                }
            }
            });
            
       cb3.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               if(cb3.isSelected() == true){
                   checkCb2 = true;
                }
            }
            });
       
        
       jb1.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               if((checkCb1 || checkCb2 || checkCb3)){
                    frame.setVisible(false);
                    c = new CrosswordDisplayer(25,checkCb1,checkCb2,checkCb3);
                        
                }            
           }
        });
    }
    
    public static void main(String args[])
    {
        GUI sample = new GUI();
    }
    
}
