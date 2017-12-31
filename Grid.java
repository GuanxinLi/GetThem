package lab4;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Grid implements GridInfo, CoordInfo{

	private final int row;
	private final int col;
	private GridObject[][]grid;
	private ArrayList<SharedCar> sharedCar = new ArrayList<>();
	private ArrayList<Obstacle> obstacle = new ArrayList<>();
	private Rider rider;
	private Random randGen = new Random();
    
    // construct a grid with its dimensions of row * col
	public Grid(int row, int col)
	{
		this.row = row;
		this.col = col;
		this.grid = new GridObject[row][col];
	}
	
	public GridObject[][] getGrid()
	{
		return grid;
	}
	
	// add car to the Grid
	public boolean addCar(SharedCar car, int row, int col)
	{
	    if(car==null)
        {
            return false;
        }
	    Coord carCoord = new Coord(row,col);
		if(row<this.row && col<this.col && coordFree(carCoord))
		{
			car.location = carCoord;
			sharedCar.add(car);
			grid[row][col] = car;
			System.out.println("in addCar in Grid "+sharedCar);
			return true;
		}
		else
			return false;
	}
	
	//add rider to the Grid
	public boolean addRider(Rider rider, int row, int col)
	{
	    if(rider==null)
	    {
	        return false;
	    }
	    Coord riderCoord = new Coord(row,col);
		if(row<this.row && col<this.col 
		        && coordFree(riderCoord))
		{
			this.rider = rider;
			this.rider.location = riderCoord;
			grid[row][col] = rider;
			for(int i = 0; i<sharedCar.size();i++)
			{
			    sharedCar.get(i).newRider(riderCoord);
			}
			return true;
		}
		else
			return false;
	}
	
	//add obstacles to the Grid
	public boolean addObstacle(Obstacle o, int row, int col)
	{
	    if(o==null)
        {
            return false;
        }
	    Coord obstacleCoord = new Coord(row,col);
	    if(rider!=null && (obstacleCoord).equals(rider.location))
	    {
	        return false;
	    }
	    else if(row<this.row && col<this.col
		        && coordFree(obstacleCoord))
		{
			o.location = obstacleCoord;
			obstacle.add(o);
			grid[row][col] = o;
			return true;
		}
		else 
			return false;
	}
	
	//tell all the cars to drive together
	public void driveAll()
	{
	    //System.out.println("The car loaded in grid: "+sharedCar.toString());
	    for(int i = 0; i<sharedCar.size();i++)
	    {
	        sharedCar.get(i).drive();
	    }
	}
	
	private boolean outOfBond(Coord c)
	{
	    //System.out.println("my coord C: " +c);
	    if(c.row<0 || c.col <0 || c.row>(this.row-1) || c.col>(this.col-1))
	        return true;
	    else 
	        return false;
	}
	
	public void update()
	{
	    //System.out.println("calling update in Grid");
	    driveAll();
	}
	
@Override
	public boolean claim(SharedCar car, Coord loc) 
	{
		//check if coord is taken if free, claim the coord
        if(coordFree(loc) && car!=null && loc!= null 
				&& !outOfBond(loc))
		{
		    grid [car.location.row][car.location.col] = null;
		    car.location = loc;
		    grid[loc.row][loc.col]=car;
			return true;
		}
		else 
		{
			return false;
		}
	}
@Override
	public boolean riderLoaded(SharedCar car) 
	{
		rider.pickUp(car);
        car.location = rider.location;
        for(int i = 0; i<sharedCar.size();i++)
        {
            sharedCar.get(i).roam();
        }
		rider.location = null;
		return true;
	}

@Override
	public boolean coordFree(Coord loc) 
	{
		if (outOfBond(loc))
        {
		    return false;
        }
            //check if coord taken by a car
		for (int i =0; i<sharedCar.size();i++)
		{
		    if(loc.equals(sharedCar.get(i).location))
		    {
		        return false;
		    }
		}
		//check if coord taken by an obstacle
		for (int j = 0; j<obstacle.size();j++)
		{
		    if(loc.equals(obstacle.get(j).location))
		    {
		        return false;
		    }
		}
		return true;
        
	}
@Override 
	public String toString()
	{
		
		String c = "";
		for(int i = 0;i<this.col+2;i++)
		{
			c+="=";
			if(i==this.col+1)
			{
				c+="\n";
			}
		}
		for(int i = 0; i<this.row; i++)
		{
			c+="|";
			for (int j = 0; j < this.col; j++ )
			{
				if (grid[i][j]==null)
				{
					c+=" ";
					continue;
				}
				if (grid[i][j] instanceof Rider)
				{
					c+=grid[i][j].getSymbol();
					continue;
				}
				if (grid[i][j] instanceof Obstacle)
				{
					c+=grid[i][j].getSymbol();
					continue;
				}
				if (grid[i][j] instanceof SharedCar)
				{
					c+=grid[i][j].getSymbol();
					continue;
				}
			}
			c+="|\n";
		}
		for(int i = 0;i<this.col+2;i++)
		{
			c+="=";
		}
		return c;
	}

public ArrayList<SharedCar> getSharedCar()
{
    return sharedCar;
}

public void setSharedCar(ArrayList<SharedCar> sharedCar)
{
    this.sharedCar = sharedCar;
}

public ArrayList<Obstacle> getObstacle()
{
    return obstacle;
}

public void setObstacle(ArrayList<Obstacle> obstacle)
{
    this.obstacle = obstacle;
}

public Rider getRider()
{
    return rider;
}

public void setRider(Rider rider)
{
    this.rider = rider;
}

public Random getRandGen()
{
    return randGen;
}

public void setRandGen(Random randGen)
{
    this.randGen = randGen;
}

public int getRow()
{
    return row;
}

public int getCol()
{
    return col;
}

public void setGrid(GridObject[][] grid)
{
    this.grid = grid;
}

	

}
