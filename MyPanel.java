import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Color;
public class MyPanel extends JPanel {
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    //Controls for Horizantal Movement
    private static final String FIRE = "player fire";
    private static final String FIRE2 = "player2 fire";
    private static final String RELEASED = "player3 fire";
    private static final String RELEASED2 = "player4 fire";
    //Cordinates
    int x = 100;
    int y = 100;
    double dx =0, dy =0;
    //Dimensions for the frame
    int width = 800;
    int height = 300;
    
    //Check to reset accelration
    boolean reverse = true;
    
    //Misc
    static JLabel obj1 = new JLabel("Welcome to MARIO");
    Color backG = Color.BLACK;
    private static ImageIcon ii = new ImageIcon("Mario.png");
    public MyPanel() {
        //sets background and dimensoins
        setPreferredSize(new Dimension(width,height));
        setBackground(backG);
        this.add(obj1);
       
        if(x!= 0 && x != width)
        dx -= .1;
        
        //Input Maps
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), FIRE);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), FIRE2);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released LEFT"), RELEASED);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released LEFT"), RELEASED2);
        this.getActionMap().put(FIRE, new FireAction(1));
        this.getActionMap().put(FIRE2, new FireAction2(1));
        this.getActionMap().put(RELEASED, new ReleaseAction(1));
        this.getActionMap().put(RELEASED2, new ReleaseAction2(1));
        

    }
    
    @Override
    //Paints the sprite
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ii.paintIcon(this,g,x,y); 
        // g.dispose();
    }

    public int getY()
    {
        return y;
    }

    public int getX()
    {
        return x;
    }

    //CONTOLS MOVEMENT------------------------------------------------------------------
    //**********************************************************************************
    //**********************************************************************************
    
    //Actions for button pressed
    private class FireAction extends AbstractAction {
        int player;
        FireAction(int player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            dx += .2;
            if(reverse == true)
            {
                dx = 0;
                reverse = false;
            }
             if(x <= 0)
            {
                dx =0;
                x = 0;
            }
            x -= (int)dx;

            repaint();
        }
    }
    private class FireAction2 extends AbstractAction {
        int player;
        FireAction2(int player) {
            this.player = player;
        }
        @Override
        public void actionPerformed(ActionEvent e) {       
            dx += .2;
            if(reverse == false)
            {
                dx = 0;
                reverse = true;
            }
            if(x >= width)
            {
                dx = 0;
                x = width;
            }
            x += (int)dx;
            repaint();
        }
    }
    
    //Accelration Reset
    private class ReleaseAction extends AbstractAction {
        int player;
        ReleaseAction(int player) {
            this.player = player;
        }
        @Override
        public void actionPerformed(ActionEvent e) {       
            dx = 0;
        }
    }
    private class ReleaseAction2 extends AbstractAction {
        int player;
        ReleaseAction2(int player) {
            this.player = player;
        }
        @Override
        public void actionPerformed(ActionEvent e) {       
            dx = 0;
        }
    }
}
