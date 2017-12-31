package lab4;
import java.util.*;
public class GridMain
{
    
    // check to see if a the next enter is a Int
    private boolean isInt(String s)
    {
        Scanner scnr = new Scanner(s);
        return scnr.hasNextInt();
    }
    
    //check the input length of command
    private boolean checkBadCarUsage(String[] parts)
    {
        return!((parts.length==3) && isInt(parts[1]) && isInt(parts[2]));
    }
    
    private boolean checkBadRiderUsage(String[] parts)
    {
        return!((parts.length==3) && isInt(parts[1]) && isInt(parts[2]));
    }
    
    private boolean checkBadObstacleUsage(String[] parts)
    {
        return!((parts.length==3) && isInt(parts[1]) && isInt(parts[2]));
    }
    
    //give examples of command line
    private void printBadCarUsage()
    {
        System.out.println("Usage:car row col\n"
                + "Add a car to the grid.");
    }
    private void printBadRiderUsage()
    {
        System.out.println("Usage:rider row col\n"
                + "Add a rider to the grid.");
    }
    private void printBadObstacleUsage()
    {
        System.out.println("Usage:obstacle row col\n"
                + "Add an obstacle to the grid.");
    }
    private void printUnkownCommand(String[] parts)
    {
        System.out.println("Unknown command: "+ parts);
    }
    
    
    // invoke method
    private void addCar(Grid grid, SharedCar car, int row, int col)
    {
        System.out.println("prepare to add car");
        if(grid.addCar(car, row, col))
        {
            System.out.println("Successfully added"); 
        }
        else
        {
            System.out.println("Cannot be added.\n"
                    + "Index out of bond or place taken.");
        }
    }
    
    private void addRider(Grid grid, Rider r, int row, int col)
    {
        if(grid.addRider(r, row, col))
        {
            System.out.println("Successfully added"); 
        }
        else
        {
            System.out.println("Cannot be added.\n"
                    + "Index out of bond or place taken.");
        }
    }
    
    private void addObstacle(Grid grid, Obstacle o, int row, int col)
    {
        if(grid.addObstacle(o, row, col))
        {
            System.out.println("Successfully added"); 
        }
        else
        {
            System.out.println("Cannot be added.\n"
                    + "Index out of bond or place taken.");
        }
    }
    
    private void runInterpreter()
    {
        int row =10;
        int col =10;
        Grid grid = new Grid(row, col);
        String command = "";
        Scanner scan = new Scanner(System.in);
        
        while(true)
        {
            System.out.println(grid);
            System.out.println("What would you like to do to your grid?\n>");
            if(!scan.hasNext())
                return;
            command = scan.nextLine();
            String[] parts = command.split("\\s+");
            if(parts.length == 0)
                continue;
            //Interpret command line
            switch (parts[0])
            {
                case"d":
                    {
                        grid.driveAll();
                        continue;
                    }
                    
                case "car":
                    if(checkBadCarUsage(parts))
                    {
                        printBadCarUsage();
                        continue;
                    }
                    EastWestController controller = new EastWestController(grid);
                    SharedCar car = new SharedCar (controller,grid);
                    addCar(grid, car, Integer.parseInt(parts[1]), 
                            Integer.parseInt(parts[2]));
                    continue;
                    
                case "obstacle":
                    if(checkBadObstacleUsage(parts))
                    {
                        printBadObstacleUsage();
                        continue;
                    }
                    Obstacle o = new Obstacle();
                    addObstacle(grid,o,Integer.parseInt(parts[1]), 
                            Integer.parseInt(parts[2]));
                    continue;
                    
                case "rider":
                    if(checkBadRiderUsage(parts))
                    {
                        printBadRiderUsage();
                        continue;
                    }
                    Rider r = new Rider();
                    addRider(grid, r,Integer.parseInt(parts[1]), 
                            Integer.parseInt(parts[2]));
                    continue;
                
                case "exit":
                    break;
                    
                default:
                    printUnkownCommand(parts);
                    continue;
            }
            break;
        }
    }
    
    public static void main(String[] args)
    {
        GridMain main = new GridMain();
        main.runInterpreter();
    }
    
}
