

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suporte
 */
public class Mouse extends MouseAdapter{
    
    public int larg, alt;
    
    public Mouse(int larg, int alt){
        this.larg = larg;
        this.alt = alt;
    }
    
    public Mouse(){}
    
    public void mouseClicked(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1) {
            GraphicScreen.lastMoveLarg = larg;
            GraphicScreen.lastMoveAlt = alt;
            GraphicScreen.count++;
            //System.out.println(GraphicScreen.lastMoveLarg +" "+ GraphicScreen.lastMoveAlt);
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            GraphicScreen.lastMoveLarg = larg;
            GraphicScreen.lastMoveAlt = alt;
            GraphicScreen.statusChange = 1;
            GraphicScreen.count++;
            
            
        }

    }

    
}
