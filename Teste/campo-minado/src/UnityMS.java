
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
public class UnityMS {
    private int value;
    private JButton botao;
    private int status;
    private boolean isOpen;
    
    public UnityMS() {
        this.status = 0;
        this.isOpen = false;
        /*Status do botão no Campo minado: 
            0 = fechado
            1 = BombMarked
            2 = QuestMarked
        */
        botao = new JButton();
    }
    
    public UnityMS(int value) {
        this.status = 0;
        /*Status do botão no Campo minado: 
            0 = fechado
            1 = BombMarked
            2 = QuestMarked
        */
        botao = new JButton();
        this.isOpen = false;
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public JButton getBotao() {
        return botao;
    }

    public void setBotao(JButton botao) {
        this.botao = botao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    
    
}
