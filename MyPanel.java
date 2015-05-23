import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String FIRE = "player fire";
    private static final String FIRE2 = "player2 fire";
    int x = 100;
    int y = 100;
    double dx =0, dy =0;
    static JLabel obj1 = new JLabel("Welcome to MARIO");
    
    private static ImageIcon ii = new ImageIcon("Mario.png");
    public MyPanel() {
       // ii = new ImageIcon("Mario.png");
        setPreferredSize(new Dimension(800,300));
        
        this.add(obj1);
        //this.setBounds(20,20,200,200);
        dx -= .1;
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), FIRE);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), FIRE2);
        this.getActionMap().put(FIRE, new FireAction(1));
        this.getActionMap().put(FIRE2, new FireAction2(1));

    }

    @Override
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
    /*static void rebindKey(KeyEvent ke, String oldKey) {
    //      Depends on your GUI implementation.
    //      Detecting the new key by a KeyListener is the way to go this time.
    obj1.getInputMap(IFW).remove(KeyStroke.getKeyStroke(oldKey));
    //      Removing can also be done by assigning the action name "none".
    obj1.getInputMap(IFW).put(KeyStroke.getKeyStrokeForEvent(ke),
    obj1.getInputMap(IFW).get(KeyStroke.getKeyStroke(oldKey)));
    //      You can drop the remove action if you want a secondary key for the action.
    }*/

    

    private class FireAction extends AbstractAction {
        int player;
        FireAction(int player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            dx += .2;
            x -= (int)dx;
            //System.out.println(x);   
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
            x += (int)dx;
            //System.out.println(x);  
            repaint();
        }
    }
}
