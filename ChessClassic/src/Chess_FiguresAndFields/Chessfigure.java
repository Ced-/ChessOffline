/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess_FiguresAndFields;

import java.net.URL;

import Chess_Board.Board;

import javax.swing.*;

/**
 *
 * @author Lucas
 */
public abstract class Chessfigure implements Cloneable{
    public Chessfigure clone() throws CloneNotSupportedException 
    {  
        return (Chessfigure)super.clone();  
    }  
    
    
    
    private final Board myChessboard;
    private Field myField;
    private final String myColor;
    private int FigureMoveCount=-1;
    private static int AllMoveCount=-1;
    private int FigureLastMove=-1;
    protected final int maxMoveCount=1;
    
   //KONSTRUKTOR        
    public Chessfigure(Field field, String color, Board chessboard)
    {
        if ((!color.equals(TYPE_COLORS.WHITE_COLOR))&&(!color.equals(TYPE_COLORS.BLACK_COLOR)))
        {
        color=TYPE_COLORS.WHITE_COLOR;
        }
        myColor=color;
        myChessboard=chessboard;
        myField=field;
        moveToAField(field);
    }
    
    
    
    
    protected MoveInformations getMoveInformation(Field newField)
    {
        int movetype;
        int numberOfMoves;
        int[] movedirection=new int[2];
        
        if (((newField.getChessX()-myField.getChessX()==0))&& ((newField.getChessY()-myField.getChessY()==0)))
                {
                    movetype = TYPE_MOVES.STRAIGHT_MOVE;
                    movedirection[0]=0;
                    movedirection[1]=0;
                    numberOfMoves=1;
                }
        else if (((newField.getChessX()-myField.getChessX()==0))|| ((newField.getChessY()-myField.getChessY()==0)))
                {
                    
                    movetype = TYPE_MOVES.STRAIGHT_MOVE;
                    movedirection[0]=(newField.getChessX()-myField.getChessX());
                    movedirection[1]=(newField.getChessY()-myField.getChessY());
                    numberOfMoves=(int)Math.sqrt((movedirection[0]+movedirection[1])*(movedirection[0]+movedirection[1])); //eins von beiden ist 0!
                    int x= (movedirection[0]<0) ? -1:1;
                    int y= (movedirection[1]<0) ? -1:1;
                    x= (movedirection[0]==0) ? 0:x;
                    y= (movedirection[1]==0) ? 0:y;
                    movedirection[0]=x;
                    movedirection[1]=y;
                    /*System.out.println(x);
                    System.out.println(y);
                    System.out.println("x");*/
                   
                }
        
        else if (((newField.getChessX()-myField.getChessX())*(newField.getChessX()-myField.getChessX()))==((newField.getChessY()-myField.getChessY())*(newField.getChessY()-myField.getChessY())))
                {
                    movetype =  TYPE_MOVES.DIAGONAL_MOVE;
                    movedirection[0]=(newField.getChessX()-myField.getChessX());
                    movedirection[1]=(newField.getChessY()-myField.getChessY());
                    numberOfMoves=(int)Math.sqrt(movedirection[0]*movedirection[0]);
                    movedirection[0] = (movedirection[0]<0) ? -1:1;
                    movedirection[1] = (movedirection[1]<0) ? -1:1;
                    ;// ist gleich dem movedirection[1] Betrag
                    
                }
        else if ((((newField.getChessX()-myField.getChessX())*(newField.getChessX()-myField.getChessX()))==1)&&(((newField.getChessY()-myField.getChessY())*(newField.getChessY()-myField.getChessY()))==4))
                {
                    movetype =  TYPE_MOVES.JUMPER_MOVE;
                    movedirection[0]=(newField.getChessX()-myField.getChessX());
                    movedirection[1]=(newField.getChessY()-myField.getChessY());
                    numberOfMoves=1;
                }
        else if (((newField.getChessY()-myField.getChessY())*(newField.getChessY()-myField.getChessY())==1)&&((newField.getChessX()-myField.getChessX())*(newField.getChessX()-myField.getChessX())==4))
                {
                    movetype =  TYPE_MOVES.JUMPER_MOVE;
                    movedirection[0]=(newField.getChessX()-myField.getChessX());
                    movedirection[1]=(newField.getChessY()-myField.getChessY());
                    numberOfMoves=1;
                }
        else
                {
                    movetype = TYPE_MOVES.IMPOSSIBLE_MOVE;
                    numberOfMoves=0;
                }
        return (new MoveInformations(movetype,movedirection[0],movedirection[1], numberOfMoves));
    }
    
    
    
    protected Chessfigure getChessFigureByRelativeCoordinates(MoveInformations moveInformation, int multiplier)
    {
        if (getChessFieldByRelativeCoordinates(moveInformation, multiplier)==null)
        {
            return null;
        }
        else
        {
            if (getChessFieldByRelativeCoordinates(moveInformation, multiplier).getFigure()==this)
            {
                return null;
            }
       
            else
            {
            return getChessFieldByRelativeCoordinates(moveInformation, multiplier).getFigure();
            }
        }
    }
            
    
    
     protected Field getChessFieldByRelativeCoordinates(MoveInformations moveInformation, int multiplier)
    {
     int x=0;
     int y=0;
        y= moveInformation.Y;
        x= moveInformation.X;
        x*=multiplier;
        y*=multiplier;
        x+=myField.getChessX();
        y+=myField.getChessY();
      
            return myChessboard.getField(x, y);
    }
    
    
    
   
            
    protected boolean isChessFigureHitableByMe(Chessfigure figure)
    {
       boolean returnvalue=true;
       if (figure!=null)
       {
            if (figure.getColor().equalsIgnoreCase(myColor))
            {
                returnvalue=false;
            }
       }
       return returnvalue;
    }
    
    
    protected Chessfigure moveToAField(Field newField)
    {
            Field oldField=myField;
            Chessfigure oldFigure=newField.getFigure();
            myField.setFigure(null);
            newField.setFigure(this);
            myField=newField;
            actionsAfterMove(oldField);
            FigureMoveCount++;
            AllMoveCount++;
            FigureLastMove=AllMoveCount;
            return oldFigure;
            
    }
    
    
    
    public boolean manualMove(Field field)
    {
   
        boolean isMovePossible=false; 
        MoveInformations MoveInformation=getMoveInformation(field);
     
        
     moveblock:     
     {       
        if (canFigureMakeThisMove(MoveInformation))
        {
              
                for (int i =1; i<=MoveInformation.COUNT; i++)
                {
                    Chessfigure tempfigure=getChessFigureByRelativeCoordinates(MoveInformation, i);

                    if (tempfigure!=null && i!=MoveInformation.COUNT)
                    {
                        break moveblock;
                    }
                    else if (i==MoveInformation.COUNT && !isChessFigureHitableByMe(tempfigure))
                    {
                        break moveblock;
                    }                    
                }
                moveToAField(field);
                isMovePossible=true;
                
           }
        } 
        return isMovePossible;
    }
    
   
    public int getFigureMoveCount()
    {
        return FigureMoveCount;
    }
    
    public static int getAllMoveCount()
    {
        return AllMoveCount;
    }
    
    public int getFigureLastMove()
    {
        return FigureLastMove;
    }
    
    public int getMaxMoveCount()
    {
        return maxMoveCount;
    }
    public String getFigureType()
    {
        return getClass().getSimpleName();
    }
    public Field getChessField()
    {
        return myField;
    }
    
    
    
    public String getColor()
    {
        return myColor;
    }
    
    URL getIcon()
    {
    	
    	URL f=Chessfigure.class.getResource("res/" + myColor + getClass().getSimpleName() +".png");
    	//System.out.println(f.getPath());
        return f;
    }
    
        

   protected abstract boolean canFigureMakeThisMove(MoveInformations information);
   
   protected abstract void actionsAfterMove(Field oldField);
    
    
    /*{
        return isMovePossible(newField.getX(), newField.getY());
    }*/
    
    //public abstract boolean isMovePossible(int x, int y);
    
    
    

    
}
