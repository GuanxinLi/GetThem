package lab4;

public class NorthSouthController extends CarController
{

    public NorthSouthController(CoordInfo oracle)
    {
        super(oracle);
        direction = SOUTH;
    }
    
    @Override
    public void setDefaultDirection()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Coord roam(Coord current)
    {
            if(oracle.coordFree(current.add(direction)))
            {
                return direction;
            }
            else
            {
                if(direction.equals(EAST))
                {
                    return direction = WEST;
                }
                else if(direction.equals(WEST))
                {    
                    return direction = EAST;
                }
                else if(direction.equals(NORTH))
                {
                   return direction = SOUTH;
                }
                else
                {
                    return direction = NORTH;
                }
            }
      }

    @Override
    public Coord drive(Coord current, Coord goal)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
}
