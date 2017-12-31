package lab4;
import java.util.*;
public class DummyGrid implements GridInfo, CoordInfo 
{

	@Override
	public boolean claim(SharedCar car, Coord loc) {
	    System.out.println("calling claim in DummyGrid");
		return true;
	}

	@Override
	public boolean riderLoaded(SharedCar car) {
	    System.out.println("calling riderLoaded in DummyGrid");
		return false;
	}

	@Override
	public boolean coordFree(Coord loc) {
	    System.out.println("call on the coordFree in DummyGrid");
		return true;
	}
	
	public static void main(String[] args)
    {
        Scanner scnr = new Scanner(System.in);
        //input file
        GridSetup setup = new GridSetup(args[0]);
	    //build up the basic grid
        Grid testGrid = new Grid(setup.getDimension().row,setup.getDimension().col);
        DummyController dController = new DummyController(testGrid);
        EastWestController c1 = new EastWestController(testGrid);
        EastWestController c2 = new EastWestController(testGrid);
        EastWestController c3 = new EastWestController(testGrid);
        // create a car
        SharedCar car1 = new SharedCar (c1,testGrid);
        SharedCar car2 = new SharedCar (c2,testGrid);
        SharedCar car3 = new SharedCar (c3,testGrid);
        // create a rider
        Rider rider = new Rider(); 
        // create a obstacle 
        Obstacle o = new Obstacle(); 
         
        
        
        //testGrid.addCar(car,setup.getRobocars()[0].row,setup.getRobocars()[0].col);
//        testGrid.addCar(car1, 6, 2);
        testGrid.addCar(car2, 0, 1);
//        testGrid.addCar(car3, 3, 7);
        testGrid.addRider(rider,5,1);
        // Print out grid
        System.out.println(testGrid);
        
       
        
        do
        {
        System.out.println("car what");
        String command = scnr.next();
        if (command.equals("d"))
        {
            testGrid.driveAll();
            //System.out.println("car 1 location"+(car1.location));
            //System.out.println((car1.location).add(dController.direction));
            //System.out.println("car 2 location"+(car2.location));
            //System.out.println((car2.location).add(dController.direction));
            System.out.println(testGrid);
        }
        }while(true);
        
    }
   
}
