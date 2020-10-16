
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suporte
 */
public class TimeCount implements Runnable{
    private Double timeCount;
    
    public TimeCount(){
        timeCount = 0.0;
    }
    
    public void run(){
        //while(true){
            GraphicScreen.timeCount+= 0.5;
            timeCount+= 0.5;
            try {
                Thread.sleep(500);
                //System.out.print(GraphicScreen.timeCount + " - Graphic Screen");
                //System.out.print("  "+timeCount+" - timeCount \n");

            } catch (InterruptedException ex) {
                Logger.getLogger(TimeCount.class.getName()).log(Level.SEVERE, null, ex);
            }
        //}
    }

    public double getTimeCount() {
        return 0.0;
    }
}
