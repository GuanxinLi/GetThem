package lab4;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GraphicsMain extends JFrame implements Runnable
{
    GraphicsGrid graphGrid = new GraphicsGrid(10,10,30);
    Grid grid = new Grid(10,10);
    

    //create a grid of my own
    public void ownGird()
    {
        EastWestController controller = new EastWestController(grid);
        SharedCar car = new SharedCar (controller,grid);
        if (grid.addCar(car, 0, 0))
        {
            graphGrid.addGridObject(car);
        }
        //create and add a rider
        Rider r = new Rider();
        r.location = new Coord(2,5);
        if(grid.addRider(r,r.location.row, r.location.col))
        {
            graphGrid.addGridObject(r);
        }
        // create and add a obstacle
        Obstacle o = new Obstacle();
        o.location = new Coord(7,8);
        if(grid.addObstacle(o, o.location.row, o.location.col))
        {
            graphGrid.addGridObject(o);
        }
        System.out.println("calling the method");
        System.out.println(this.grid.toString());
    }
    
    @Override
    public void run()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(graphGrid);
        pack();
        setVisible(true);
        
    }
    
    public static void main(String[] args)
    {
        GraphicsMain graph = new GraphicsMain();
        graph.ownGird();
        SwingUtilities.invokeLater(graph);
        System.out.println(graph.getGrid());
    }

    public GraphicsGrid getGraphGrid()
    {
        return graphGrid;
    }

    public void setGraphGrid(GraphicsGrid graphGrid)
    {
        this.graphGrid = graphGrid;
    }

    public Grid getGrid()
    {
        return grid;
    }

    public void setGrid(Grid grid)
    {
        this.grid = grid;
    }
}
