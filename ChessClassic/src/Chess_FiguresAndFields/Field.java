/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess_FiguresAndFields;
import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author Lucas
 */

public final class Field extends JButton implements Cloneable{

    
public Field clone() throws CloneNotSupportedException 
    {  
        return (Field)super.clone();  
    }  
private final int myX;
private final int myY;
private Chessfigure myFigure;
private final String myColor;
   public Field(Chessfigure figure, int x, int y, String color)
   {
       myColor=color;
       setColor(myColor);
       myX=x;
       myY=y;
       setFigure(figure);
   }
   
   
   public void setColor(String color)
   {
       
       color = (color.equals(TYPE_COLORS.WHITE_COLOR) || color.equals(TYPE_COLORS.BLACK_COLOR) || color.equals(TYPE_COLORS.BLUE_COLOR))? color:myColor;
       //System.out.println(color);
       if (color.equals(TYPE_COLORS.WHITE_COLOR))
        {
            this.setBackground(Color.white);
        }
       else if (color.equals(TYPE_COLORS.BLACK_COLOR))
        {
           this.setBackground(Color.black);
        }
       else if (color.equals(TYPE_COLORS.BLUE_COLOR))
        {
           
           this.setBackground(Color.blue);
        }
       else
        {
           this.setBackground(Color.gray);
        }
       repaint();
    }
   
   
   void setFigure(Chessfigure figure) 
   {
       ImageIcon II=new ImageIcon();
       myFigure=figure;
       if (myFigure!=null)
       {
            II=new ImageIcon(myFigure.getIcon());
            //ystem.out.println(myFigure.getIcon());
       }
       setIcon(II);
       setColor(myColor);//and repaint
       //repaint();    
   }
   
   public Chessfigure getFigure()
   {
       return myFigure;
   }
   
   
   final int getChessX()
   {
       return myX;
   }
   
   final int getChessY()
   {
       return myY;
   }
   
   /*
   public void makeMove(Field oldField)
   {
       Figure figure;
       if (oldField=null)
       {
           
       }
               
       this.myFigure=oldField.getFigure();
   
   public void isMovePossible(Field oldField)
   {
       
   }
   }
   /*{
       if (oldfield==null)
       {
           setNewFigure(null, myX, myY);
       }
       else
       {
           setNewFigure(oldfield.getFigure(), myX, myY);
       }
   }
   
  private Chessfigure setNewFigure(Chessfigure figure, int x, int y)
   {
       
     myFigure=figure;
       if oldfield!=null
               {
                   oldfigure=oldfield.getFigure();
               }
               
       switch figure.isMovePossible(x, y)
       {
               case 1: //possible - do the move and set oldfield to null
                   myFigure=figure;
                   figure.moveFigure(x,y);
                   
               case 2:
                   
       }
     
       if (figure.isMovePossible(figure, x, y))
       {
           oldfield.
                   doTheMove=true;
       }
       else 
       {
           
       }
   }
   
   public Chessfigure getFigure()
   {
       return myFigure;
   }

   this.

    @Override
    public void setIcon(Icon defaultIcon) {
        super.setIcon(defaultIcon);
    }
    

    @Override
    public void repaint() {
        super.repaint();
    }
   JButton Fieldbutton= new JButton("ee","ee")
   */
   
    
}
