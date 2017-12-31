package lab4;

public class SharedCarMain {
	private DummyGrid dGrid = new DummyGrid();
	private DummyController dController = new DummyController(dGrid);
	private SharedCar car = new SharedCar (dController,dGrid);

	public static void main(String[] args)
	{
		
	}
}
