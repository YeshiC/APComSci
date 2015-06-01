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
    int width = 2000;
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
    boolean dead = false;
    Platform stuff;
    ArrayList<Platform> temp ;
    public MyPanel() {
        //sets background and dimensoins
        //temp = stuff.returnCordinates();
        setPreferredSize(new Dimension(width,height));
        setBackground(backG);
        makePlatforms();
        makeSpikes();
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

    ArrayList<Platform> cords = new ArrayList<>();
    public void makePlatforms()
    {
        Platform first = new Platform(0,220,2000,25);
        cords.add(first);
       
        Platform thrid = new Platform(10,10,10,10);
        cords.add(thrid);
       temp = cords;
    }
    
    ArrayList<Platform> spikes = new ArrayList<>();
    public void makeSpikes()
    {
        Platform one = new Platform(500,220,200,25);
        spikes.add(one);
       
        Platform two = new Platform(200,220,100,25);
        spikes.add(two);
        
        Platform three = new Platform(700,220,150,25);
        spikes.add(three);
       
    }
    
    

    @Override
    //Paints the sprite
    public void paintComponent(Graphics g) {
        
        
        
        super.paintComponent(g);
        for(int ctr = 0; ctr < temp.size(); ctr ++)
        {
            g.setColor(Color.WHITE);
            g.fillRect(temp.get(ctr).returnX(),
                temp.get(ctr).returnY(),
                temp.get(ctr).returnH(),
                temp.get(ctr).returnW());
        }
        for(int ctr = 0; ctr < spikes.size(); ctr ++)
        {
            g.setColor(Color.RED);
            g.fillRect(spikes.get(ctr).returnX(),
                spikes.get(ctr).returnY(),
                spikes.get(ctr).returnH(),
                spikes.get(ctr).returnW());
        }
        ii.paintIcon(this,g,x,y); 
        if(x == 1900 && (y == 205 || y == 201))
        {
            
            g.setColor(Color.GREEN);
            g.fillRect(0,0,2000,300);
            g.setColor(Color.WHITE);
            g.drawString("You WIN!", 2000, 300);
        }
        if(dead == true)
        {
            g.setColor(Color.RED);
            g.fillRect(0,0,2000,300);
            g.setColor(Color.WHITE);
            g.drawString("You Ded", 2000, 300);
        }
        System.out.print(y);
    }

    public int getCordsY()
    {
        return y;
    }

    public int getCordsX()
    {
        return x;
    }

  
    
    public void printIt()
    {

        if(y<200){
            dy += 1;

        }
        if(y>200 && dy > 1){
            dy = 0;

        }

        if(dx >0)
            dx-=.15;
        if(dx<0)
            dx+=.15;

        x+=dx;
        y+=dy;
        //collisionDetectionF();
        //collisionDetectionB();
        //collisionDetectionU();
        System.out.println(y);
        repaint();
        
        for(int ctr = 0; ctr < spikes.size(); ctr ++)
        {
            if(((spikes.get(ctr).returnX() < x && spikes.get(ctr).returnX() + spikes.get(ctr).returnW() > x) && (y == 205||y == 201)))
            {
                dead = true;
            }
            if((spikes.get(ctr).returnX() < x + imageW && spikes.get(ctr).returnX() + spikes.get(ctr).returnW() > x + imageW) && (y == 205 || y == 201))
                 dead = true;
        }

    }

    //CONTOLS MOVEMENT------------------------------------------------------------------
    //**********************************************************************************
    //**********************************************************************************

    //Changes accelration to 0, if a collsion is detected
    public void collisionDetectionF()
    {
        for(int ctr = 0; ctr< temp.size(); ctr ++)
        {
            if((temp.get(ctr).returnY()< y 
                && x > temp.get(ctr).returnH()+ temp.get(ctr).returnY())
            ||(temp.get(ctr).returnY()< y + imageH 
                && x + imageH  > temp.get(ctr).returnH()+ temp.get(ctr).returnY())){

                if(dx< temp.get(ctr).returnX() + temp.get(ctr).returnW() - x)
                    dx = temp.get(ctr).returnX() + temp.get(ctr).returnW() - x;

            }}
    }

    public void collisionDetectionB()
    {
        for(int ctr = 0; ctr< temp.size(); ctr ++)
        {
            if((temp.get(ctr).returnY()< y 
                && x > temp.get(ctr).returnH()+ temp.get(ctr).returnY())
            ||(temp.get(ctr).returnY()< y + imageH 
                && x + imageH  > temp.get(ctr).returnH()+ temp.get(ctr).returnY()))
                if(dx > temp.get(ctr).returnX() - x)
                    dx = temp.get(ctr).returnX()  - x;
        }
    }

    //     public void collisionDetectionU()
    //     {
    //         for(int ctr = 0; ctr< temp.size(); ctr ++)
    //         {
    //             if((
    //                 (temp.get(ctr).returnY() == y + imageH)) 
    //             && ( (x <= (temp.get(ctr).returnX() + temp.get(ctr).returnW()) && x >= temp.get(ctr).returnX() )
    //                 || (x + imageW <= (temp.get(ctr).returnX() + temp.get(ctr).returnW()) && x + imageW >= temp.get(ctr).returnX() )))
    //             {   
    //                 dy = 0;
    //                 hasJumped = false;
    //             }   
    // //             if(((temp.get(ctr).returnY() + temp.get(ctr).returnH()) == y 
    // //             ) 
    // //             && ( (x <= (temp.get(ctr).returnX() + temp.get(ctr).returnW()) && x >= temp.get(ctr).returnX() )
    // //                 || (x + imageW <= (temp.get(ctr).returnX() + temp.get(ctr).returnW()) && x + imageW >= temp.get(ctr).returnX() )))
    // //                 dy = 0;
    //         }
    //     }

    public void collisionDetectionU()
    {
        for(int ctr = 0; ctr< temp.size(); ctr ++)
        {
            if((temp.get(ctr).returnX()< x 
                && x > temp.get(ctr).returnH()+ temp.get(ctr).returnY())
            ||(temp.get(ctr).returnX()< x + imageH 
                && x + imageH  > temp.get(ctr).returnH()+ temp.get(ctr).returnY()))
                if(dy > temp.get(ctr).returnY() - y)
                    dy = temp.get(ctr).returnY() - y;
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
            if(dx<10)
                dx += 1;
            printIt();
        }
    }
    private class FireAction extends AbstractAction {
        int player;
        FireAction(int player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(dx>-10)
                dx -= 1;
            printIt();
        }
    }
    private class FireAction3 extends AbstractAction {
        int player;
        FireAction3(int player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(hasJumped == false)
            {
                hasJumped = true;
                dy = -10;
            }
            if(y > 200)
                hasJumped = false;

            printIt();
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
