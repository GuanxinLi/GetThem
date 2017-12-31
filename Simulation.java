package lab4;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Simulation extends JFrame implements Runnable, ActionListener,ChangeListener 
{
    //set up 
    GridSetup setup = new GridSetup("gridtest.txt");
    int ticks = 100;
    Grid grid;
    GraphicsGrid graphGrid = new GraphicsGrid(setup.getDimension().row,
            setup.getDimension().col,30);
    TimeTick ticker;
    private JLabel header;
    private JButton buttonNewGame,buttonPause;
    private JPanel topPanel, bottomPanel;
    private JSlider speedSlider;
    private int playerLoadNum =0;
    private int robotsLoadNum = 0;
    
    // create the setup grid using setup file
    public Grid setup() 
    { 
        grid=  new Grid(setup.getDimension().row,
                setup.getDimension().col);
        ticker = new TimeTick(ticks, grid, this);
        EastWestController controller = new EastWestController(grid);
        SharedCar car = new SharedCar (controller,grid);
        grid.addCar(car,setup.getRobocars()[0].row,setup.getRobocars()[0].col);
        Rider rider = new Rider();
        grid.addRider(rider,setup.getRider().row,setup.getRider().col);
        Obstacle o = new Obstacle();
        grid.addObstacle(o,setup.getObstacles()[0].row,
                setup.getObstacles()[0].col);
        return this.grid;
    }
    
    // repaint the whole graph again
    public void update()
    {
        //System.out.println("calling update in sim");
        graphGrid.clearObjects();
        
        for(int i = 0; i<grid.getGrid().length;i++)
            for (int j = 0; j<grid.getGrid()[i].length;j++)
            {
                if(grid.getGrid()[i][j]!=null)
                {
                    graphGrid.addGridObject(grid.getGrid()[i][j]);
                }
            }
        graphGrid.repaint();
        
    }
    
    @Override
    public void run()
    {

        //record keeper
        header = new JLabel("Riders Loaded Player: "
                + playerLoadNum + " Robots: "+ robotsLoadNum);
        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.add(header);
        
        // new game button
        buttonNewGame = new JButton("New Game");
        buttonNewGame.setPreferredSize(new Dimension(120, 30));
        buttonNewGame.addActionListener(this);
        
        //pause button
        buttonPause = new JButton();
        buttonPause.setPreferredSize(new Dimension(110, 30));
        buttonPause.setText("Pause");
        buttonPause.addActionListener(this);
        
        //speed slider
        speedSlider = new JSlider();
        speedSlider.addChangeListener(this);
        
        
        // Adding to the container
        Container pane = getContentPane();
        pane.add(topPanel,BorderLayout.NORTH);
        pane.add(graphGrid,BorderLayout.CENTER);
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        
        // Add to the Panel
        bottomPanel.add(buttonNewGame);
        bottomPanel.add(buttonPause);
        bottomPanel.add(speedSlider);
        // Add the panel to JFrame
        pane.add(bottomPanel, BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton buttonEvent = (JButton) e.getSource();
        if (buttonEvent == buttonNewGame)
        {
           
            this.setup();
            Thread tickerThread = new Thread (ticker);
            tickerThread.start();
        }
        else if (buttonEvent == buttonPause)
        {
            ticker.changeState();
            if(ticker.paused())
                buttonPause.setText("Resume");
            if(!ticker.paused())
                buttonPause.setText("Pause");
        }
        
    }
    
    
    @Override
    public void stateChanged(ChangeEvent e) 
    {
        //TODO change of the slide -- change ticks
    }

    public static void main(String[] args)
    {
        Simulation graph = new Simulation();
        SwingUtilities.invokeLater(graph);
    }
    
    public Grid getGrid()
    {
        return grid;
    }

}
