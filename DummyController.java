package lab4;

public class DummyController extends CarController
{

	public DummyController(CoordInfo oracle) {
		super(oracle);
		direction = NORTH;
	}

	@Override
	public void setDefaultDirection() 
	{
	    
	}

	@Override
	public Coord roam(Coord current) {
		
		return NORTH;
	}

	@Override
	public Coord drive(Coord current, Coord goal) {
		
		return NORTH;
	}

}
