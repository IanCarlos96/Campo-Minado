
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suporte
 */
public class GraphicScreen {
    
    private JFrame frame;
    
    private JPanel painelCampo; 
    private JPanel painelMenu;
    private JPanel gameModeMenu;
    
    //private JButton[][] campo;
    private UnityMS[][] campo;
    private JButton reset;
    
    //botoes de dificuldade
    private JButton gameModeHard, gameModeMedium, gameModeEasy;
    
    private JTextField bombCountField, time;
    
    private int bombCount;
    
    private int largura;
    private int altura;
    
    public static int gameModeStatic;
    private int gameModeCurrent;
    public static int count;
    public static int lastMoveLarg, lastMoveAlt;
    public static int statusChange;
    public static double timeCount;
    
    //Número de casas com número que ainda não foram abertas (o jogo termina ao abrir todas)
    public static int hiddenTotal;
    
    private boolean resetMode;
    
    private TimeCount clock = new TimeCount();
    
    private OpenButtonListener listener;
    
    

    
    
    public GraphicScreen(int gameMode, MineSweep campoVet) {
        count = 0;
        timeCount = 0;
        hiddenTotal = 0;
        statusChange = 0;
        gameModeStatic = gameMode;
        gameModeCurrent = gameMode;
        resetMode = false;
        
        listener = new OpenButtonListener();
        
        int sizeLarg, sizeAlt;  //tamanho em pixels
        
        Dimension buttonSize = new Dimension(0,0);
        
        //propriedades dos botoes de dificuldade
        gameModeHard = new JButton();//"HARD");
        gameModeHard.setBackground(new Color(192,192,192));
        gameModeHard.setName("gameModeHard");
        gameModeHard.setIcon(new ImageIcon("Icons/gameModeHard.PNG"));
        gameModeHard.setSize(80,22);
        //gameModeHard.setPreferredSize(80,26);
        gameModeHard.addActionListener(listener);
        gameModeHard.setBorder(null);

        
        gameModeMedium = new JButton();//"MEDIUM");
        gameModeMedium.setBackground(new Color(192,192,192));
        gameModeMedium.setName("gameModeMedium");
        gameModeMedium.setIcon(new ImageIcon("Icons/gameModeMedium.PNG"));
        gameModeMedium.setSize(80,22);
        gameModeMedium.addActionListener(listener);
        gameModeMedium.setBorder(null);
        
        gameModeEasy = new JButton();//"EASY");
        gameModeEasy.setBackground(new Color(192,192,192));
        gameModeEasy.setName("gameModeEasy");
        gameModeEasy.setIcon(new ImageIcon("Icons/gameModeEasy.PNG"));
        gameModeEasy.setSize(80,22);
        gameModeEasy.addActionListener(listener);
        gameModeEasy.setBorder(null);

        
        switch(gameMode){
            default:
            case 1:   //Hard mode, 16 altura x 30 largura, 99 bombas
                this.setAltura(30);
                this.setLargura(16);
                sizeLarg = 670;     //670 x 525 = com gameMode
                sizeAlt = 525;      //670x470 = sem gameMode
                buttonSize = new Dimension(22,22);
                gameModeHard.setEnabled(false);
                break;
            case 2:  //Medium mode, 16 largura x 16 largura, 40 bombas
                this.setAltura(16);
                this.setLargura(16);
                sizeLarg = 365;        //365 x 455 = sem gameMode
                sizeAlt = 497;         //365 x 497 = com gameMode  
                buttonSize = new Dimension(22,22);
                gameModeMedium.setEnabled(false);
                break;
            case 3:  //Easy mode, 9 largura x 9 largura, 10 bombas
                this.setAltura(9);
                this.setLargura(9);
                sizeLarg = 225;         //210 x 290 = sem gameMode
                sizeAlt = 330;          //210 x 330 = com gameMode
                buttonSize = new Dimension(22,22);
                gameModeEasy.setEnabled(false);
                break;
        }
        
        frame = new JFrame("Campo minado do Ian");
        frame.setLayout(new BorderLayout(this.getLargura(), this.getAltura()+1));
        frame.setBackground(new Color(0,0,0));//192,192,192));
        frame.getContentPane().setBackground(new Color(192,192,192));
       
        
//painelCampo com FlowLayout
        FlowLayout layoutCampo = new FlowLayout(FlowLayout.CENTER,0,0);
        layoutCampo.setAlignment(FlowLayout.CENTER);
        painelCampo = new JPanel(layoutCampo);
        painelCampo.setBackground(new Color(192,192,192));
        painelCampo.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        
        painelMenu = new JPanel(new BorderLayout());
        
        //propriedades do gameModeMenu
        gameModeMenu = new JPanel( );
        gameModeMenu.setLayout(new GridLayout());
        gameModeMenu.setBackground(new Color(192,192,192));
        
        gameModeMenu.add(gameModeEasy);
        gameModeMenu.add(gameModeMedium);
        gameModeMenu.add(gameModeHard);
        
        //propriedades do contador de bomba
        bombCount = campoVet.getBombs();
        bombCountField = new JTextField(Integer.toString(bombCount), 4);
        bombCountField.setFont(new Font("Arial", Font.BOLD, 14));
        bombCountField.setHorizontalAlignment(JTextField.CENTER);
        bombCountField.setBackground(Color.BLACK);
        bombCountField.setForeground(Color.red);
        bombCountField.setEditable(false);
        
        //propriedades do contador de tempo
        time = new JTextField("0000");
        time.setEditable(false);
        time.setFont(new Font("Arial", Font.BOLD, 14));
        time.setHorizontalAlignment(JTextField.CENTER);
        time.setBackground(Color.BLACK);
        time.setForeground(Color.red);
        
        
        
        reset = new JButton("");
        reset.setBorder(null);
        reset.setBackground(new Color(192,192,192));
        reset.setIcon(new ImageIcon("Icons/R.png"));
        reset.setSize(44,44);
        reset.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent ae) {
                resetGame(GraphicScreen.gameModeStatic);
            }
        });
        
        
        
        
        //adiciona os campos bombCount, time e botão reset no painel de menu
        painelMenu.add(reset, BorderLayout.CENTER);
        painelMenu.add(time, BorderLayout.EAST);
        painelMenu.add(bombCountField, BorderLayout.WEST);
        //propriedades Gráficas PainelMenu
        painelMenu.setBackground(new Color(192,192,192));
        
        
        campo = new UnityMS[largura][altura];
        //campoVet.printVet();
        for(int larg = 0; larg<largura; larg++) {
            for(int alt = 0; alt<altura; alt++) {
                campo[larg][alt] = new UnityMS(campoVet.getUnitValue(larg, alt));
                
                
                // Propriedades gráficas dos botões
                campo[larg][alt].getBotao().setBackground(new Color(192,192,192));
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/closed.png"));
                campo[larg][alt].getBotao().setPreferredSize(buttonSize);
                campo[larg][alt].getBotao().setSize(buttonSize);
                //campo[larg][alt].getBotao().setBounds(0, 0, 30, 30);
                
                //tentar fazer funcionar com botão direito too
                campo[larg][alt].getBotao().addMouseListener(new Mouse(larg, alt));
                
                //é o que funciona só com botão esquerdo
                //campo[larg][alt].getBotao().addActionListener(new OpenButtonListener(campo[larg][alt].getValue(), count, larg, alt));
                
                painelCampo.add(campo[larg][alt].getBotao());
                if(campo[larg][alt].getValue()!= 10) {
                    hiddenTotal += campo[larg][alt].getValue();
                }
            }
            //painelCampo.
        } 
        
        
        frame.add(painelMenu, BorderLayout.NORTH);
        //painelMenu.setVisible(true);
        frame.add(gameModeMenu, BorderLayout.SOUTH);
        frame.add(painelCampo, BorderLayout.CENTER);
        
    //mudar para false depois
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeLarg, sizeAlt);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - (frame.getSize().width/2), dim.height/2-(frame.getSize().height/2));
        
        frame.setVisible(true);
        
    }
    
    public void run() {
        if(count!=0){   
            if(count == 1){
                if(clock != null){
                    clock.run();
                }
            }
            //System.out.println(hiddenTotal);
            if(statusChange == 0 && !campo[lastMoveLarg][lastMoveAlt].isIsOpen()) {
                if(campo[lastMoveLarg][lastMoveAlt].getValue() == 10 && campo[lastMoveLarg][lastMoveAlt].getStatus()!= 1) {
                    endGame();
                    clock = null;
                }
                if(campo[lastMoveLarg][lastMoveAlt].getValue() == 0 && campo[lastMoveLarg][lastMoveAlt].getStatus()!= 1){
                    openZero(lastMoveLarg, lastMoveAlt);
                }
                if(campo[lastMoveLarg][lastMoveAlt].getValue()>0 && campo[lastMoveLarg][lastMoveAlt].getValue() < 9 && campo[lastMoveLarg][lastMoveAlt].getStatus()!= 1) {
                    openNumber(lastMoveLarg, lastMoveAlt);
                    if(hiddenTotal == 0) {
                        winGame();
                        clock=null;
                        hiddenTotal=-1;
                    }
                }
                //printButtonDetails(lastMoveLarg, lastMoveAlt);
            } else {
                if(!campo[lastMoveLarg][lastMoveAlt].isIsOpen()) {
                    changeStatus(lastMoveLarg, lastMoveAlt);
                    statusChange = 0;
                }
            }
            if(clock!=null){
                Integer.toString((int)timeCount);
                if(timeCount < 10.0)
                    time.setText("000"+Integer.toString((int)timeCount));
                if(timeCount < 100.0 && timeCount > 10.0)
                    time.setText("00"+Integer.toString((int)timeCount));
                if(timeCount < 1000.0 && timeCount > 100.0)
                    time.setText("0"+Integer.toString((int)timeCount));
                if(timeCount > 9999.0)
                    time.setText("9999");
            } 
        }
        if(gameModeStatic != gameModeCurrent){
            resetGame(gameModeStatic);
            
        }
    }
    
    public void printButtonDetails(int larg, int alt){
        System.out.println("Button["+larg+"]["+alt+"] => value -> "+campo[lastMoveLarg][lastMoveAlt].getValue());
        System.out.println("Status -> "+campo[larg][alt].getStatus());
        System.out.println("IsOpen = "+campo[larg][alt].isIsOpen());
    }
    
    public void changeStatus(int larg, int alt){
        /*Status do botão no Campo minado: 
            0 = fechado
            1 = BombMarked
            2 = QuestMarked
        */
        switch(campo[larg][alt].getStatus()){
            case 0:  //botão não marcado -> bombMarked
                campo[larg][alt].setStatus(1);
                
                //Com ícone
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/flag.png"));
                
                /* //Sem ícone
                campo[larg][alt].getBotao().setForeground(Color.red);
                campo[larg][alt].getBotao().setText("!");
                */
                //Atualizando o contator de bombas
                bombCount--;
                bombCountField.setText(Integer.toString(bombCount));
                
                
                break;
            case 1:  //botão bombMarked -> QuestMarked
                campo[larg][alt].setStatus(2);
                
                //Com ícone
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/quest.png"));
                
                /* //Sem ícone
                campo[larg][alt].getBotao().setForeground(Color.CYAN);
                campo[larg][alt].getBotao().setText("?");
                */
                //Atualizando o Contador de bombas
                bombCount++;
                bombCountField.setText(Integer.toString(bombCount));
                break;
            case 2:  //botão QuestMarked -> não marcado
                campo[larg][alt].setStatus(0);
                
                //Medida provisória, colocar ícone mais tarde
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/closed.png"));
                campo[larg][alt].getBotao().setText("");
                
                break;
        }
        count=0;
    }
    
    public void endGame() {
        for (int larg = 0; larg < largura; larg++) {
            for (int alt =0; alt < altura; alt++) {
                campo[larg][alt].getBotao().setSelected(true);
                if(campo[larg][alt].getValue() == 10) {
                    /* No graphic
                    campo[larg][alt].getBotao().setBackground(Color.red);
                    campo[larg][alt].getBotao().setText("X"); */
                    // *With icon
                    campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/bomb.png"));
                    //*/
                }
                if(!campo[larg][alt].isIsOpen()) {
                    campo[larg][alt].setIsOpen(true);
                }
                //campo[larg][alt].setIsOpen(false);
                if((campo[larg][alt].getValue()>0 && campo[larg][alt].getValue()<9) && campo[larg][alt].getStatus() == 1) {
                    campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/falseFlag.png"));
                }
            }
        }
    }
    
    public void openZero(int larg, int alt){
        int relative=1;
        if(campo[larg][alt].getValue() == 0){
            campo[larg][alt].getBotao().setIcon(null);
            campo[larg][alt].getBotao().setEnabled(false);
            campo[larg][alt].getBotao().setBackground(Color.GRAY);
            campo[larg][alt].getBotao().setText("");
            campo[larg][alt].setIsOpen(true);
            while(relative<10) {
                verify(relative, larg, alt);
                relative++;
            }
        }
        
        if(hiddenTotal == 0){
            winGame();
            hiddenTotal = -1;

        }
    }
    
    public void openNumber(int larg, int alt){
        colorNumber(larg, alt); //atribui o ícone ao número
        
        if(!campo[larg][alt].isIsOpen()){  // getBotao().isEnabled()) {
            hiddenTotal = hiddenTotal - campo[larg][alt].getValue();
            
            campo[larg][alt].setIsOpen(true); //setEnabled(false);
        }
        /*
        if(hiddenTotal == 0){
            winGame();

        }*/
        
    }
    
    public void colorNumber(int larg, int alt) {
        switch(campo[larg][alt].getValue()){
            case 1:
                campo[larg][alt].getBotao().setForeground(Color.BLUE);
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/1.png"));
                break;
            case 2:
                campo[larg][alt].getBotao().setForeground(Color.GREEN);
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/2.png"));
                break;
            case 3:
                campo[larg][alt].getBotao().setForeground(Color.RED);
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/3.png"));
                break;
            case 4:
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/4.png"));
                //campo[larg][alt].getBotao().setForeground(new Color(0,0,102));
                break;
            case 5:
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/5.png"));
                //campo[larg][alt].getBotao().setForeground(new Color(0,51,0));
                break;
            case 6:
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/6.png"));
                //campo[larg][alt].getBotao().setForeground(new Color(204,204,0));
                break;
            case 7:
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/7.png"));
                //campo[larg][alt].getBotao().setForeground(new Color(153,204,155));
                break;
            case 8:
                campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/8.png"));
                //campo[larg][alt].getBotao().setForeground(new Color(102,0,102));
                break;
        }
    }
    
    public int verify(int relative, int larg, int alt){
        switch(relative) {
            case 1:
                if(((larg-1)< 0) || (alt-1)<0) {
                    return 0;
                }else {
                    if(campo[larg-1][alt-1].getValue() == 0 && campo[larg-1][alt-1].getBotao().isEnabled()){
                        openZero(larg-1, alt-1);
                    }
                    if(campo[larg-1][alt-1].getValue()>0 && campo[larg-1][alt-1].getValue()<9) {
                        openNumber(larg-1, alt-1);
                    }
                }
                return 0;
            case 2:
                if(alt-1 < 0){
                    return 0;
                }else{
                    if(campo[larg][alt-1].getValue() == 0 && campo[larg][alt-1].getBotao().isEnabled()){
                        openZero(larg, alt-1);
                    }
                    if(campo[larg][alt-1].getValue()>0 && campo[larg][alt-1].getValue()<9) {
                        openNumber(larg, alt-1);
                    }
                    
                }
                return 0;
            case 3:
                if(((larg+1)>= this.getLargura()) || (alt-1) <0) {
                    return 0;
                }else {
                    if(campo[larg+1][alt-1].getValue() == 0 && campo[larg+1][alt-1].getBotao().isEnabled()){
                        openZero(larg+1, alt-1);
                    }
                    if(campo[larg+1][alt-1].getValue()>0 && campo[larg+1][alt-1].getValue()<9) {
                        openNumber(larg+1, alt-1);
                    }
                }
                return 0;
            case 4:
                if(larg-1 < 0){
                    return 0;
                }else{
                    if(campo[larg-1][alt].getValue() == 0 && campo[larg-1][alt].getBotao().isEnabled()){
                        openZero(larg-1, alt);
                    }
                    if(campo[larg-1][alt].getValue()>0 && campo[larg-1][alt].getValue()<9) {
                        openNumber(larg-1, alt);
                    }
                    
                }
                return 0;
            case 5:
                return 0;
            case 6:
                if(larg+1 >=this.getLargura()){
                    return 0;
                }else{
                    if(campo[larg+1][alt].getValue() == 0 && campo[larg+1][alt].getBotao().isEnabled()){
                        openZero(larg+1, alt);
                    }
                    if(campo[larg+1][alt].getValue()>0 && campo[larg+1][alt].getValue()<9) {
                        openNumber(larg+1, alt);
                    }
                }
                return 0;
            case 7:
                if(((larg-1)< 0) || (alt+1)>=this.getAltura()) {
                    return 0;
                }else {
                    if(campo[larg-1][alt+1].getValue() == 0 && campo[larg-1][alt+1].getBotao().isEnabled()){
                        openZero(larg-1, alt+1);
                    }
                    if(campo[larg-1][alt+1].getValue()>0 && campo[larg-1][alt+1].getValue()<9) {
                        openNumber(larg-1, alt+1);
                    }
                }
                return 0;
            case 8:
                if((alt+1) >= this.getAltura()){
                    return 0;
                }else{
                    if(campo[larg][alt+1].getValue() == 0  && campo[larg][alt+1].getBotao().isEnabled()){
                        openZero(larg, alt+1);
                    }
                    if(campo[larg][alt+1].getValue()>0 && campo[larg][alt+1].getValue()<9) {
                        openNumber(larg, alt+1);
                    }
                }
                return 0;
            case 9:   
                if(((larg+1)>= this.getLargura()) || (alt+1)>= this.getAltura()) {
                    return 0;
                }else {
                    if(campo[larg+1][alt+1].getValue() == 0 && campo[larg+1][alt+1].getBotao().isEnabled()){
                        openZero(larg+1, alt+1);
                    }
                    if(campo[larg+1][alt+1].getValue()>0 && campo[larg+1][alt+1].getValue()<9) {
                        openNumber(larg+1, alt+1);
                    }
                }
                return 0;
        }
        
        return 0;
    }
    
    public void winGame(){
        for(int larg=0; larg<largura; larg++) {
            for(int alt=0; alt<altura; alt++) {
                if(campo[larg][alt].getValue()==10) {
                    campo[larg][alt].getBotao().setIcon(new ImageIcon("icons/flag.png"));
                    campo[larg][alt].setIsOpen(true);
                    //campo[larg][alt].setStatus(alt);
                }
            }
        }
        bombCountField.setText("0");
        JOptionPane.showMessageDialog(frame, "Parabéns! \nO Deus do campo minado te dá felicitações pela vitória.\nTempo gasto: "+(int)timeCount+" segundos.");
    }
    
    public void resetGame(int gameMode){
        frame.setVisible(false);
        //frame = null;
        resetMode = true;
        frame.dispose();
    }
    
    //setters e getters
    
    public JPanel painelCampo() {
        return painelCampo;
    }

    public void setPainelCampo(JPanel tela) {
        this.painelCampo = tela;
    }

    public JButton getReset() {
        return reset;
    }

    public void setReset(JButton reset) {
        this.reset = reset;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public boolean isResetMode() {
        return resetMode;
    }

    public void setResetMode(boolean resetMode) {
        this.resetMode = resetMode;
    }
    
    
}
