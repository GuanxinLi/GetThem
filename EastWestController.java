package lab4;

public class EastWestController extends CarController
{
    public EastWestController(CoordInfo oracle)
    {
        super(oracle);
        direction = NORTH;
        
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
        Coord diff = new Coord(current.diff(goal));
        //no obstacles in front of car
        if(oracle.coordFree(current.add(direction)))
        {
            //rider not in the same col
            if(diff.col!=0)
            {
                //car in west
                if(diff.col<0)
                {
                    return direction = EAST;
                }
                //car in east
                else
                {
                    return direction = WEST;
                }
            }
            //in the same col
            else
            {
                //car in north
                if(diff.row<0)
                {
                    return direction = SOUTH;
                }
                //car in south
                else
                {
                    return direction = NORTH;
                }
            }
        }
        // an obstacle in front 
        else
        {
            //when an  obstacle in east or west
            if (direction==EAST || direction==WEST)
            {
                //car in north
                if(diff.row<0)
                {
                    return direction = SOUTH;
                }
                //car in south
                else
                {
                    return direction = NORTH;
                }
            }
            // when an obstacle in south or north
            else
            {
              //car in west
                if(diff.col<0)
                {
                    return direction = EAST;
                }
                //car in east
                else
                {
                    return direction = WEST;
                }
            }
        }
    }
}


