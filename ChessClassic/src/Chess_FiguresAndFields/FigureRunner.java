/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess_FiguresAndFields;
import Chess_Board.Board;
/**
 *
 * @author Lucas
 */
public class FigureRunner extends Chessfigure
{

    public FigureRunner(Field field, String color, Board board)
    {
        super(field, color, board);
    }

int maxMoveCount=8;
public boolean canFigureMakeThisMove(MoveInformations moveinformation)
{
    boolean returnvalue=false;
    if (moveinformation.TYPE!=TYPE_MOVES.STRAIGHT_MOVE && moveinformation.TYPE!=TYPE_MOVES.JUMPER_MOVE && moveinformation.TYPE!=TYPE_MOVES.IMPOSSIBLE_MOVE && moveinformation.COUNT<=maxMoveCount)
    {    
        returnvalue= true;
    }
    return returnvalue;
}

public void actionsAfterMove(Field oldField)
{
    
}

    
}