import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Image;
public class MyPanel extends JPanel {
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    //Controls for Horizantal Movement
    private static final String FIRE = "player fire";
    private static final String FIRE2 = "player2 fire";
    private static final String FIRE3 = "player3 fire";
    private static final String RELEASED = "player4 fire";
    private static final String RELEASED2 = "player4 fire";
    //Cordinates
    int x = 100;
    int y = 100;
    double dx =0, dy =0;
    //Dimensions for the frame
    int width = 1000;
    int height = 300;

    //Check to reset accelration
    boolean reverse = true;

    //Misc
    static JLabel obj1 = new JLabel("Welcome to MARIO");
    Color backG = Color.BLACK;
    private static ImageIcon ii = new ImageIcon("Mario.png");
    int imageH = ii.getIconHeight();
    int imageW = ii.getIconWidth();
    boolean hasJumped = false;
    Platform stuff;
    ArrayList<Platform> temp ;
    public MyPanel() {
        //sets background and dimensoins
        temp = stuff.returnCordinates();
        setPreferredSize(new Dimension(width,height));
        setBackground(backG);

    
        if(x!= 0 && x != width && dx < 7)
            dx -= .1;        
        //Input Maps
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), FIRE);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), FIRE2);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), FIRE3);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released LEFT"), RELEASED);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released RIGHT"), RELEASED2);
        this.getActionMap().put(FIRE, new FireAction(1));
        this.getActionMap().put(FIRE2, new FireAction2(1));
        this.getActionMap().put(FIRE3, new FireAction3(1));
        this.getActionMap().put(RELEASED, new ReleaseAction(1));
        this.getActionMap().put(RELEASED2, new ReleaseAction2(1));
    }

    @Override
    //Paints the sprite
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int ctr = 0; ctr < temp.size(); ctr ++)
        {
            g.setColor(Color.WHITE);
            g.drawRect(temp.get(ctr).returnX(),
                temp.get(ctr).returnY(),
                temp.get(ctr).returnH(),
                temp.get(ctr).returnW());
        }
        ii.paintIcon(this,g,x,y); 
        // g.dispose();
    }

    public int getCordsY()
    {
        return y;
    }

    public int getCordsX()
    {
        return x;
    }

    //CONTOLS MOVEMENT------------------------------------------------------------------
    //**********************************************************************************
    //**********************************************************************************

    //Changes accelration to 0, if a collsion is detected
    public void collisionDetectionF()
    {
        for(int ctr = 0; ctr< temp.size(); ctr ++)
        {
            if((temp.get(ctr).returnX() + temp.get(ctr).returnW()) == x 
            && ( y > temp.get(ctr).returnY() && y < (temp.get(ctr).returnY() + temp.get(ctr).returnH())))
                dx = 0;
        }
    }

    public void collisionDetectionB()
    {
        for(int ctr = 0; ctr< temp.size(); ctr ++)
        {
            if((temp.get(ctr).returnX()) == x 
            && ( y > temp.get(ctr).returnY() && y < (temp.get(ctr).returnY() + temp.get(ctr).returnH())))
                dx = 0;
        }
    }

    public void collisionDetectionU()
    {
        for(int ctr = 0; ctr< temp.size(); ctr ++)
        {
            if((
                (temp.get(ctr).returnY() == y + imageH)) 
            && ( (x <= (temp.get(ctr).returnX() + temp.get(ctr).returnW()) && x >= temp.get(ctr).returnX() )
                || (x + imageW <= (temp.get(ctr).returnX() + temp.get(ctr).returnW()) && x + imageW >= temp.get(ctr).returnX() )))
            {   
                dy = 0;
                hasJumped = false;
            }   
            if(((temp.get(ctr).returnY() + temp.get(ctr).returnH()) == y 
            ) 
            && ( (x <= (temp.get(ctr).returnX() + temp.get(ctr).returnW()) && x >= temp.get(ctr).returnX() )
                || (x + imageW <= (temp.get(ctr).returnX() + temp.get(ctr).returnW()) && x + imageW >= temp.get(ctr).returnX() )))
                dy = 0;
        }
    }

    //Actions for button pressed
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
                dx = 3;
            }
            if(x >= width)
            {
                dx = 0;
                x = width;
            }
            collisionDetectionF();
            x += (int)dx;

            repaint();
        }
    }
    private class FireAction extends AbstractAction {
        int player;
        FireAction(int player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            dx -= .2;
            if(reverse == true)
            {
                dx = 0;
                reverse = false;
                dx = -3;

            }
            if(x <= 0)
            {
                dx =0;
                x = 0;
            }
            collisionDetectionB();
            x += (int)dx;
            repaint();
        }
    }
    private class FireAction3 extends AbstractAction {
        int player;
        FireAction3(int player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            y -= 1;
            if(hasJumped == false)
            {
                hasJumped = true;
                dy = 10;
            }
          
            collisionDetectionU();
            y -= (int)dy;
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
