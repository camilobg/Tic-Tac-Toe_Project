package TicTacToe_Project;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

class Cell extends JButton{
    int turno;
    static int[][] tablero=new int[3][3];

    public Cell(){

    }
    public Cell(String name){
        setText(" ");
        setFocusPainted(false);
        setBackground(Color.white);
        setBorder(new LineBorder(Color.black,3));
        super.setName("Button"+name);
        //super.setText(name);
        setPreferredSize(new Dimension(150,150));
    }
    void marcar(String name,int player,int[][] tablero){
        if(name.substring(0,1).equals("A")){
            if(name.substring(1,2).equals("3"))tablero[0][0]=player;
            else if(name.substring(1,2).equals("2"))tablero[1][0]=player;
            else if(name.substring(1,2).equals("1"))tablero[2][0]=player;
        }
        else if(name.substring(0,1).equals("B")){
            if(name.substring(1,2).equals("3"))tablero[0][1]=player;
            else if(name.substring(1,2).equals("2"))tablero[1][1]=player;
            else if(name.substring(1,2).equals("1"))tablero[2][1]=player;
        }
        else if(name.substring(0,1).equals("C")){
            if(name.substring(1,2).equals("3"))tablero[0][2]=player;
            else if(name.substring(1,2).equals("2"))tablero[1][2]=player;
            else if(name.substring(1,2).equals("1"))tablero[2][2]=player;
        }

        /*
        for(int i=0;i< tablero.length;i++){
            System.out.println(Arrays.toString(tablero[i]));
        }

         */
    }

    int verificar(int play){
        //System.out.println("Verifico, jugador #"+play);
        int[][] tab=tablero;
        //ROWS
        if(play==tab[0][0] && tab[0][0]==tab[0][1] && tab[0][1]==tab[0][2] ||
                play==tab[1][0] && tab[1][0]==tab[1][1] && tab[1][1]==tab[1][2] ||
                play==tab[2][0] && tab[2][0]==tab[2][1] && tab[2][1]==tab[2][2])return play;
            //COLUMNS
        else if(play==tab[0][0] && tab[0][0]==tab[1][0] && tab[1][0]==tab[2][0] ||
                play==tab[0][1] && tab[0][1]==tab[1][1] && tab[1][1]==tab[2][1] ||
                play==tab[0][2] && tab[0][2]==tab[1][2] && tab[1][2]==tab[2][2])return play;
            //DIAGONALS
        else if(play==tab[0][0] && tab[0][0]==tab[1][1] && tab[1][1]==tab[2][2] ||
                play==tab[0][2] && tab[0][2]==tab[1][1] && tab[1][1]==tab[2][0])return play;
        else if(tab[0][0]!=0 && tab[0][1]!=0 && tab[0][2]!=0 &&
                tab[1][0]!=0 && tab[1][1]!=0 && tab[1][2]!=0 &&
                tab[2][0]!=0 && tab[2][1]!=0 && tab[2][2]!=0)return 3;
        else return 4;
    }
    static ArrayList<Integer> freeCells(){
        ArrayList<Integer> myList = new ArrayList<>();
        for(int i=0; i<tablero.length; i++){
            for(int j=0; j<tablero[i].length; j++){
                if(tablero[i][j]==0)myList.add((3*i)+(j));
                System.out.println("En los indices "+i+" "+j+" está el valor "+tablero[i][j]);
            }
        }
        return myList;
    }
}
class Board extends JPanel{
    Cell[] botones;
    public Board(){
        setLayout(new GridLayout(3,3,0,0));
        setSize(450,450);
        String[] nombres= {"A3","B3","C3","A2","B2","C2","A1","B1","C1"};
        botones= new Cell[9];

        for(int i=0;i<botones.length;i++){
            botones[i]=new Cell(nombres[i]);
            add(botones[i]);
        }
    }
    Cell[] getBotones(){
        return botones;
    }
}
class LabelStatus extends JLabel{
    static int turnos;
    public LabelStatus(int turnos){
        this.turnos=turnos;
        setName("LabelStatus");
        if(turnos==0)setText("Game is not started");
        else setText("Game in progress. Turn "+turnos);

    }
    static int getTurnos(){
        return turnos;
    }

    void setTurnos(int turn) {
        turnos = turn;
        if(turn==0)setText("Game is not started");
            //else setText("Game in progress. Turn "+turnos);
        else setText("Game in progress");
        //System.out.println("hola, pongo el turno en el label: "+turnos);
    }
    boolean setWinner(int winner){
        if(winner==1)setText("X wins");
        else if(winner==2) setText("O wins");
        else if(winner==3) setText("Draw");
        if(winner==1 || winner==2 || winner==3)return true;
        else return false;
    }
    static boolean isGameStarted(){
        if(turnos>0)return true;
        else return false;
    }
}
class ButtonReset extends JButton{
    int gameCase=0;
    public ButtonReset(){
        setName("ButtonStartReset");
        setText("Start");
        setPreferredSize(new Dimension(120,30));

    }
    public ButtonReset(String buttonName){
        setName("Button"+buttonName);
        setText("Human");
        setPreferredSize(new Dimension(120,30));
        addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(getText().equals("Human") && !getName().equals("ButtonStartReset")){
                    setText("Robot");
                    System.out.println("if1");
                }
                else if(getText().equals("Robot") && !getName().equals("ButtonStartReset")){
                    setText("Human");
                }
            }
        });

    }
}
public class TicTacToe extends JFrame{
    public TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,480);
        setResizable(false);
        //setPreferredSize(new Dimension(450, 480));

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel buttonsPlayers=new JPanel();
        //buttonsPlayers.setBackground(Color.BLUE);
        //buttonsPlayers.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        buttonsPlayers.setLayout(new FlowLayout(FlowLayout.CENTER,22,5));
        ButtonReset buttonStartReset=new ButtonReset();
        ButtonReset buttonPlayer1=new ButtonReset("Player1");
        ButtonReset buttonPlayer2=new ButtonReset("Player2");
        buttonsPlayers.add(buttonPlayer1);
        buttonsPlayers.add(buttonStartReset);
        buttonsPlayers.add(buttonPlayer2);
        add(buttonsPlayers);


        Board board=new Board();
        add(board);
        for(int i=0;i<9;i++)board.getBotones()[i].setEnabled(false);

        JPanel description=new JPanel();
        description.setLayout(new FlowLayout());

        int turnsBoard=0;
        LabelStatus labelStatus=new LabelStatus(turnsBoard);






        buttonStartReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(buttonStartReset.getText().equals("Reset")){
                    for(int i=0;i<9;i++){
                        board.getBotones()[i].setText(" ");
                        board.getBotones()[i].setEnabled(false);
                    }
                    for(int i=0;i<3;i++) Arrays.fill(Cell.tablero[i],0);
                    labelStatus.setTurnos(0);
                    buttonStartReset.setText("Start");
                }
                else{
                    for(int i=0;i<9;i++)board.getBotones()[i].setEnabled(true);
                    buttonStartReset.setText("Reset");
                    labelStatus.setText("Game in progress");

                    if(buttonPlayer1.getText().equals("Human") && buttonPlayer2.getText().equals("Human"))buttonStartReset.gameCase=1;
                    else if(buttonPlayer1.getText().equals("Human") && buttonPlayer2.getText().equals("Robot"))buttonStartReset.gameCase=2;
                    else if(buttonPlayer1.getText().equals("Robot") && buttonPlayer2.getText().equals("Human"))buttonStartReset.gameCase=3;
                    else if(buttonPlayer1.getText().equals("Robot") && buttonPlayer2.getText().equals("Robot"))buttonStartReset.gameCase=4;
                    System.out.println("El caso de juego es "+buttonStartReset.gameCase);
                }

            }
        });


        ActionListener botonAction=new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                /**if(buttonStartReset.gameCase==1){
                    Cell boton=(Cell)e.getSource();
                    boton.setEnabled(false);
                    String name=boton.getName().substring(6,8);
                    int i=labelStatus.turnos;
                    int player;
                    i++;
                    labelStatus.setTurnos(i);
                    //System.out.println(labelStatus.turnos+"# Turno, Función Listener");
                    if(labelStatus.turnos%2==0){
                        player=2;
                        boton.setText("O");
                    }
                    else {
                        player=1;
                        boton.setText("X");
                    }

                    if(LabelStatus.isGameStarted())buttonStartReset.setText("Reset");
                    boton.marcar(name,player,Cell.tablero);
                    boolean winCondition=labelStatus.setWinner(boton.verificar(player));
                    if(winCondition){
                        for(int j=0;j<9;j++){
                            board.getBotones()[j].setEnabled(false);
                        }
                    }
                    
                }**/

                gameCases(buttonStartReset.gameCase,(Cell)e.getSource(),labelStatus,board,buttonStartReset);



            }
        };

        for(Cell boton:board.getBotones()){
            boton.addActionListener(botonAction);
        }
        description.add(labelStatus);
        //description.add(buttonStartReset);


        add(description);
        setVisible(true);
    }

    public static void gameCases(int gameCase, Cell boton, LabelStatus labelStatus, Board board, ButtonReset buttonStartReset){

        Random random=new Random();
        String name;
        int player;
        int i;
        boolean winCondition;

        int randomCell;
        String randomName;
        Cell randomButton;

        switch(gameCase){
            case 1:
                boton.setEnabled(false);
                name=boton.getName().substring(6,8);
                i=labelStatus.turnos;

                i++;
                labelStatus.setTurnos(i);
                if(labelStatus.turnos%2==0){
                    player=2;
                    boton.setText("O");
                }
                else {
                    player=1;
                    boton.setText("X");
                }

                //if(LabelStatus.isGameStarted())buttonStartReset.setText("Reset");
                boton.marcar(name,player,Cell.tablero);
                winCondition=labelStatus.setWinner(boton.verificar(player));
                if(winCondition){
                    for(int j=0;j<9;j++){
                        board.getBotones()[j].setEnabled(false);
                    }
                }
                break;
            case 2:
                //#####
                boton.setEnabled(false);
                name=boton.getName().substring(6,8);
                i=labelStatus.turnos;

                i++;
                labelStatus.setTurnos(i);
                if(labelStatus.turnos%2!=0){
                    player=1;
                    boton.setText("X");
                }
                else {
                    player=2;
                    boton.setText("O");
                }

                //if(LabelStatus.isGameStarted())buttonStartReset.setText("Reset");
                boton.marcar(name,player,Cell.tablero);
                winCondition=labelStatus.setWinner(boton.verificar(player));
                if(winCondition){
                    for(int j=0;j<9;j++){
                        board.getBotones()[j].setEnabled(false);
                    }
                    break;
                }
                System.out.println("El turno es: "+i);
                //número random entre 0 y 8
                randomCell=Cell.freeCells().get(random.nextInt(Cell.freeCells().size()+0));
                System.out.println("Casilla libre de 0 a 8 son: "+Cell.freeCells());
                //randomCell=random.nextInt(9);
                randomButton=board.getBotones()[randomCell];
                randomButton.setEnabled(false);
                randomName=randomButton.getName().substring(6,8);
                randomButton.setText("O");
                i++;
                labelStatus.setTurnos(i);

                randomButton.marcar(randomName,2,Cell.tablero);
                winCondition=labelStatus.setWinner(randomButton.verificar(2));
                if(winCondition){
                    for(int j=0;j<9;j++){
                        board.getBotones()[j].setEnabled(false);
                    }
                    break;
                }
                for(int k=0;k<Cell.tablero.length;k++){
                    System.out.println(Arrays.toString(Cell.tablero[k]));
                }


                break;
        }



    }
}
