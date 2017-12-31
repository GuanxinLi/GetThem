package lab4;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManualController extends CarController implements KeyListener
{

    public ManualController(CoordInfo oracle)
    {
        super(oracle);
        direction = null;
    }

    @Override
    public void setDefaultDirection()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Coord roam(Coord current)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Coord drive(Coord current, Coord goal)
    {
        return direction;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // TODO Auto-generated method stub
        
    }
    
}
