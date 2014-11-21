/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jaypoddar
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 


import java.io.*;
import java.util.*;
/**
 *
 * @author jayp
 */
public class MazeSolver
{
    Vertex [][] _maze;
    
    int _noOfRows;
    int _noOfc;
    
    Vertex _source;
    
    Vertex _destination;
    
    PriorityQueue<Vertex> queue=new PriorityQueue<Vertex>();
    
    Set<Vertex> set=new HashSet<Vertex>();
    
    public MazeSolver() throws Exception
    {
        getInput();
        solveMaze();
        printSolution();
    }

    private void getInput() throws Exception
    {  
        Scanner k = new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(k.nextLine());
        _noOfRows=Integer.parseInt(st.nextToken());
        _noOfc =Integer.parseInt(st.nextToken());
        _maze=new Vertex[_noOfRows][_noOfc];
        
        String s;
        char ch;
        for (int i = 0; i < _noOfRows; i++)
        {
            s=k.nextLine();
            for(int j=0;j<_noOfc;j++)
            {
                ch=s.charAt(j);
                _maze[i][j]=new Vertex(ch,j,i);
                
                if(ch=='x'||ch=='X')
                {
                    _maze[i][j]._isWall=true;                    
                }
                else if(ch=='*')
                {
                    if(_source == null)
                    {
                        _source=_maze[i][j];
                    }
                    else
                    {
                        _destination=_maze[i][j];
                    }
                }
            }            
        }
    }

    private ArrayList<Vertex> getNeighbour(Vertex u)
    {
        ArrayList<Vertex> neighbours=new ArrayList<Vertex>();

        if(!((u._x - 1)<0)&&!_maze[u._y][u._x-1]._isWall)
        {
            neighbours.add(_maze[u._y][u._x-1]);
        }

        if(((u._x + 1)<_noOfc)&&!_maze[u._y][u._x + 1]._isWall)
        {
            neighbours.add(_maze[u._y][u._x + 1]);
        }

        if(!((u._y-1)<0)&&!_maze[u._y-1][u._x]._isWall)
        {
            neighbours.add(_maze[u._y-1][u._x]);
        }

       if(((u._y + 1)<_noOfRows)&&!_maze[u._y+1][u._x ]._isWall)
        {
            neighbours.add(_maze[u._y+1][u._x]);
        }
        return neighbours;
    }
    private void printSolution()
    {
        if(_destination._parent==null)
        {
            System.out.println("NO PATH");
        }
        else
        {
            for (int i = 0; i < _noOfRows; i++)
            {
                for (int j = 0; j < _noOfc; j++)
                {
                    System.out.print(_maze[i][j]);
                }
                System.out.println("");
            }
        }
    }               
    
    public static void main(String[] args) throws Exception
    {
        MazeSolver mazeSolver=new MazeSolver();
        
    }

    private void relaxNeighbours(Vertex u)
    {
        ArrayList<Vertex> neighbours= getNeighbour(u);
        for(Vertex v:neighbours)
        {
            if(!set.contains(v))
            {
                if(v._bestEstimatedDistance >(u._bestEstimatedDistance+1))
                {
                   v._bestEstimatedDistance=(u._bestEstimatedDistance+1);
                   v._parent=u;
                   queue.add(v);
                }
            }            
        }
    }

    private void solveMaze()
    {        
        queue.add(_source);
        _source._bestEstimatedDistance=0;
        Vertex u;

        while(!queue.isEmpty())
        {
            u=queue.remove();
            set.add(u);
            if(u==_destination)
            {
                break;
            }
            relaxNeighbours(u);
        }
        setPath();
    }
    private void setPath()
    {
        Vertex v=_destination;
        if(v._parent==null)
        {
            return;
        }
        while(v!=_source)
        {
            v._value='@';
            v=v._parent;
        }
        _source._value='@';
    }

}


