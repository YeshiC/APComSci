import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Write a description of class sdf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyGameRunner
{
    // instance variables - replace the example below with your own
    public static void main(String[]args)
    {
        

        JFrame frame = new JFrame("Mario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = frame.getContentPane();
        c.setBackground(Color.pink);
        //c.setLayout(null);
        c.add(new MyPanel());
        
        frame.pack();
        frame.setVisible(true);
    }
}
