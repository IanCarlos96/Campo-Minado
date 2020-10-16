
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suporte
 */
class MineSweep {
    
    private int largura;
    private int altura;
    private int bombs;
    private int vetor[][];

    Random rand = new Random();
    
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

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public int[][] getVetor() {
        return vetor;
    }

    public void setVetor(int[][] vetor) {
        this.vetor = vetor;
    }
    
    
    
    public MineSweep(int gameMode) {
        
        switch(gameMode) {
            default:
            case 1:   //Hard mode, 16 altura x 30 largura, 99 bombas
                this.setAltura(30);
                this.setLargura(16);
                this.setBombs(99);
                break;
            case 2:  //Medium mode, 16 largura x 16 largura, 40 bombas
                this.setAltura(16);
                this.setLargura(16);
                this.setBombs(40);
                break;
            case 3:  //Easy mode, 9 largura x 9 largura, 10 bombas
                this.setAltura(9);
                this.setLargura(9);
                this.setBombs(10);
                break;
        }
        vetor = new int[largura][altura];
                
        zera();                     //Preenche o vetor inteiro com 0
        putBomb();                  //Randomiza a posição das bombas
        putNumber();                //Preenche os campos adjacentes das bombas
    }
    
    
    private void putBomb(){
        int currentBombs = this.getBombs();
        int alt, larg;
        while(currentBombs!=0) {
            alt = rand.nextInt(this.getAltura());
            larg = rand.nextInt(this.getLargura());
            if(vetor[larg][alt] == 0){
                vetor[larg][alt] = 10;
                currentBombs--;
            }
        }
    }
    
    private void putNumber(){
        int count;
        
        for(int larg=0; larg<this.getLargura(); larg++){
            for(int alt=0; alt<this.getAltura(); alt++){
                count =0;
                if(vetor[larg][alt] == 10){
                //System.out.print("[ X ] ");
                }
                else{
                    for(int relative=1; relative<=9; relative++){
                        count += verify(larg, alt, relative);
                    }
                    vetor[larg][alt] = count;
                    //System.out.print("[ "+vetor[larg][alt]+ " ] ");
                }
            }
        }
        
    }
    
    private void zera(){
        for(int i=0; i<largura; i++) {
            for(int j=0; j<altura; j++) {
                vetor[i][j] = 0;
            }
        }
    }
    
    private int verify(int larg, int alt, int relative){
        switch(relative) {
            case 1:
                if(((larg-1)< 0) || (alt-1)<0) {
                    return 0;
                }else {
                    if(vetor[larg-1][alt-1] == 10){
                        return 1;
                    }
                }
                return 0;
            case 2:
                if(alt-1 < 0){
                    return 0;
                }else{
                    if(vetor[larg][alt-1] == 10){
                        return 1;
                    }
                }
                return 0;
            case 3:
                if(((larg+1)>= this.getLargura()) || (alt-1) <0) {
                    return 0;
                }else {
                    if(vetor[larg+1][alt-1] == 10){
                        return 1;
                    }
                }
                return 0;
            case 4:
                if(larg-1 < 0){
                    return 0;
                }else{
                    if(vetor[larg-1][alt] == 10){
                        return 1;
                    }
                }
                return 0;
            case 5:
                return 0;
            case 6:
                if(larg+1 >=this.getLargura()){
                    return 0;
                }else{
                    if(vetor[larg+1][alt] == 10){
                        return 1;
                    }
                }
                return 0;
            case 7:
                
                if(((larg-1)< 0) || (alt+1)>=this.getAltura()) {
                    return 0;
                }else {
                    if(vetor[larg-1][alt+1] == 10){
                        return 1;
                    }
                }
                return 0;
            case 8:
                if((alt+1) >= this.getAltura()){
                    return 0;
                }else{
                    if(vetor[larg][alt+1] == 10){
                        return 1;
                    }
                }
                return 0;
            case 9:   
                if(((larg+1)>= this.getLargura()) || (alt+1)>= this.getAltura()) {
                    return 0;
                }else {
                    if(vetor[larg+1][alt+1] == 10){
                        return 1;
                    }
                }
                return 0;
        }
        
        return 0;
    }
    
    public void printVet(){
        for(int larg=0; larg<this.getLargura(); larg++) {
            System.out.println();
            for(int alt=0; alt<this.getAltura(); alt++) {
                System.out.print("[");
                if(vetor[larg][alt] == 10) {
                    System.out.print("X] ");
                }else{
                    System.out.print(vetor[larg][alt]+"] ");
                }
            }
        }
        System.out.println();
    }
    
    public int getUnitValue(int larg, int alt){
        return vetor[larg][alt];
    }
    
}
