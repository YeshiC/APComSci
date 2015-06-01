import java.util.ArrayList;
/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform
{
    // instance variables - replace the example below with your own
   
    int platX1,platY1,h,w;
    
    public Platform(int x1, int y1,int h1, int w1)
    {
        platX1 = x1;
        platY1 = y1;
        h = h1;
        w = w1;
    }
//     static ArrayList<Platform> cords = new ArrayList<>();
//     public void makePlatforms()
//     {
//         Platform first = new Platform(0,200,20,400);
//         cords.add(first);
//         Platform second = new Platform(700,0,300,300);
//         cords.add(second);
//     }
    
    public int returnX()
    {
    return platX1;
    }
    public int returnY()
    {
    return platY1;
    }
    public int returnH()
    {
    return h;
    }
    public int returnW()
    {
    return w;
    }
    
//     public static ArrayList<Platform> returnCordinates()
//     {
//         
//         return cords;
//     }
}
