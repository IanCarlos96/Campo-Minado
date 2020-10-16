
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suporte
 */
public class OpenButtonListener implements ActionListener {
    
    
    
    public OpenButtonListener() {
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JButton button = (JButton) e.getSource();
        //System.out.println(button.toString());
        switch (button.getName()){
            case "gameModeHard":
                main.gameMode = 1;
                GraphicScreen.gameModeStatic = 1;   //resetGame(1);
                //System.out.println("var_dump");
                break;
            case "gameModeMedium":
                main.gameMode = 2;
                GraphicScreen.gameModeStatic = 2;   //resetGame(1);
                //System.out.println("var_dump");
                break;
            case "gameModeEasy":
                main.gameMode = 3;
                GraphicScreen.gameModeStatic = 3;   //resetGame(1);
                //System.out.println("var_dump");
                break;
        }
    
        
    }
}
