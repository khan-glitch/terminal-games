import java.util.*;
public class battleship
 {

    public static Scanner input=new Scanner (System.in);
    public static Random randomly = new Random();

    public static String[][] water= new String[10][10];//water 2d array
    public static String[][] watertwo= new String[10][10];//watertwo
    
    public static int userships=0;
    public static int pcship=0;
///////////////////////////////////////////////////////////////////////////////////////////////
    public static void main ( String[] args){
        intro();
        locateUser(water); //user locate ship
        locatecomputer(water); //computer locate ship
        attack();
    }

    public static void intro(){
        System.out.println("\nWelcome to the game");
        place(water);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void place(String[][] water){
       System.out.println("\n  0123456789  ");

        for(int row =0; row<water.length;row++){ //grid
            System.out.print(row+"|");
            for (int col=0; col<water[row].length;col++){
                if(water[row][col]==null){
                    System.out.print(" ");
                } else {
                    System.out.print(water[row][col]);
                }
            }
            System.out.println("|"+row);
        }
        System.out.println("  0123456789  ");
    }
//////////////////////////////////////////////////////////////////////////////////////////////////
    public static void place2(String[][] watertwo){
   
        System.out.println("watertwo");
        System.out.println("  0123456789  ");
        //create number on grid
        for (int row =0; row<watertwo.length;row++){
            System.out.print(row+"|");
            for (int col=0; col<watertwo[row].length;col++){
                if(watertwo[row][col]==null){
                    System.out.print(" ");
                } else {
                    System.out.print(watertwo[row][col]);
                }
            }
            System.out.println("|"+row);
        }
        System.out.println("  0123456789  ");
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void locateUser(String[][] water){
        System.out.println("\nLocate the ships");

        while (userships<5){
            System.out.print("Enter x coord for your ship ");
            int col=input.nextInt();
            System.out.print("Enter y coord for your ship ");
            int row= input.nextInt();


            if (row>9||col>9){//10/10 grid only
                System.out.println("Cannot enter coord greater than 9");
            } else if(water [row][col]!=null) { 
                System.out.println("Coord is already used");
            } else {
                water [row][col]="@";
                userships++;
            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void locatecomputer(String[][] water){
        System.out.println("\n\ncomputer locating ship");

        while (pcship<5){
            int row=randomly.nextInt(10), col=randomly.nextInt(10) ; 

            if (water [row][col]==null&&watertwo [row][col]==null){//already taken by other
                System.out.println("ship is located ");
                watertwo[row][col] = "@";//placing ships in watertwo
                pcship++;
            }
        }

        System.out.println("\n*****************************\n");
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void userTurn(){

        System.out.println("\nEnter coord for your turn");
        int turn=0;

        while (turn==0) {

            System.out.print("locate x coord ");
            int col = input.nextInt();
            System.out.print("locate y coord ");
            int row = input.nextInt();


            if (row > 9 || col > 9) {//range
                System.out.println("Cannot enter the coord that are not within range\n");
            }else if(water[row][col] =="!"||water[row][col] == "x"||water[row][col]=="-"){
                System.out.println("Already Entered\n");
            } else if (watertwo[row][col] == "@") {//The player successfully predicted the ship's coordinates (computer loses ship).
                System.out.println("The computer's ship was sunk by you!");
                water[row][col] = "!";
                watertwo[row][col] = "!";
                pcship--;
                turn++;
            } else if (water[row][col] == "@") { //Player entered the ship's coordinates (player loses ship).
                System.out.println("you capsized your own vessel.");
                water[row][col] = "x";
                userships--;
                turn++;
            }else {//Player missed. No ship on the entered coordinates
                System.out.println("Sorry you have failed to guess");
                water[row][col]="-";
                turn++;
            }
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void computerTurn(){
        System.out.println("\n\nComputer locating ships");
        int turn=0;

        while (turn==0){

            int x=randomly.nextInt(10);;
            int y=randomly.nextInt(10);;

            if(watertwo[x][y]=="@") {
                System.out.println("One of the Computer's own ships was sunk!");
                water[x][y]="!";
                watertwo[x][y]="!";
                pcship--;
                turn++;
            } else if (water[x][y]=="@") { 
                System.out.println("One of your ships was sunk by the computer!");
                water[x][y]="x";
                watertwo[x][y]="x";
                userships--;
                turn++;
            }else if(water[x][y] =="!"||water[x][y] == "x"||watertwo[x][y]=="-"){

            } else {
                System.out.println("Computer failed to guess");
                watertwo[x][y]="-";
                turn++;
            }
        }

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void attack(){
        place(water); 
        System.out.println("\nYour  ships: "+userships+" | Own ships: "+pcship+"\n*******************************");

        while (userships>0&&pcship>0) {
            userTurn();
            computerTurn();
            place(water);
            System.out.println("\nOwn ships: "+userships+" |Own ships: "+pcship+"\n********************************");
        }

        if (userships==0){
            System.out.println("\nFame is over");
            System.out.println("\nOwn ships: "+userships+" | Own ships: "+pcship+"\n********************************");
        } else if (pcship==0){
            System.out.println("You won");
            System.out.println("\nOwnships: "+userships+" | Own ships: "+pcship+"\n*********************************");
        }
        
    }

}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////