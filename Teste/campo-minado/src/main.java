
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suporte
 */
public class main {

    static int gameMode;
    
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        gameMode = 3;
        try{
            MineSweep campo = new MineSweep(gameMode);
            GraphicScreen screen = new GraphicScreen(gameMode, campo);

            while(!screen.isResetMode()){
                screen.run();

                //FAÇA O QUE FIZER, NÃO TIRE ESSE SLEEP DAQUI
                Thread.sleep(10);

                if(screen.isResetMode()) {
                    campo = new MineSweep(gameMode);
                    //campo.printVet();
                    screen = new GraphicScreen(gameMode, campo);

                }
            }
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        //campo.printVet();
        
        
        
        
    }
    
}
