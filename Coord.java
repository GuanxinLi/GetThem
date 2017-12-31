/**
 * Name: Guanxin Li
 */
package lab4;

public class Coord implements Comparable<Coord>{
	
	public final int col;
	public final int row;
	public static final Coord origin = new Coord(0,0);

	public Coord()
	{
		this(0,0);
	}
	
	public Coord (Coord other) 
	{
		this.col = other.col;
		this.row = other.row;
	}
	
	public Coord (int row1, int col1)
	{
		this.col = col1;
		this.row = row1;
	}
	
	public Coord dist(Coord b)
	{
		if (b!= null)
			return new Coord(Math.abs(this.row - b.row),Math.abs(this.col - b.col));
		else 
			return null;
	}
	
	public Coord diff(Coord b)
	{
		if (b!= null)
			return new Coord((this.row - b.row),(this.col - b.col));
		else 
			return null;
	}
	
	public int dist2(Coord b)
	{
		int dis = 0;
		if (b!=null)
		{
			dis = (int) (Math.pow(dist(b).row, 2)+Math.pow(dist(b).col,2));
			return dis;
		}
		else 
			return Integer.MAX_VALUE;
		
	}
	
	public Coord unit()
	{
		int signRow;
		int signCol;
		if (row<0)
			signRow = -1;
		else 
			signRow = 1;
		if (col<0)
			signCol = -1;
		else 
			signCol = 1;
		return new Coord(signRow,signCol);
	}
	
	public Coord add(Coord b)
	{
		if (b!= null)
			return new Coord(row + b.row,col + b.col);
		else 
			return null;
	}
	
	public int compareTo(Coord other)
	{
		Coord origin = new Coord(0,0);
	    int distOne = this.dist2(origin);
		int distTwo = other.dist2(origin);
		if (other == null || distOne<distTwo)
			return -1;
		else if (distOne>distTwo)
			return 1;
		else 
			return 0;
		}
	
	@Override
	public boolean equals(java.lang.Object other)
	{
		if(!(other instanceof Coord))
		{
			return false;
		}
		if(this.col==((Coord)other).col&&this.row==((Coord)other).row)
		{
			return true;
		}
		return false;
	}
	
	@Override 
	public String toString()
	{
		return "".format("Coord:(%d,%d)",row, col); 
	}
}
