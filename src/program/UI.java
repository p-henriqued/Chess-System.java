package program;

import chessGame.ChessMatch;
import chessGame.ChessPiece;
import chessGame.ChessPosition;
import chessGame.Color;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    
    // Work´s just on  terminals that support ANSI escape codes.
    public static void cleanScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    //Responsible for reading the movement of the píece.
    public static ChessPosition readChessPosition(Scanner sc){
        try{
        String s = sc.nextLine();
        char columm = s.charAt(0);
        int row = Integer.parseInt(s.substring(1));
        return new ChessPosition(columm,row);
        }
        catch(RuntimeException e){
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
        }
    }
    
    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured){
        printBoard(chessMatch.getPieces());
        System.out.println("");
        printCapturedPieces(captured);
        System.out.println("\nTurn: "+ chessMatch.getTurn());
        System.out.println("Waiting player: "+chessMatch.getCurrentPlayer());
    }
    
    public static void printBoard(ChessPiece[][] pieces) {
        for (int r = 0; r < pieces.length; r++) {
            System.out.print("\n" + (8 - r) + " ");
            for (int c = 0; c < pieces.length; c++) {
                printPiece(pieces[r][c],false);
            }
        }
        System.out.println("\n   a   b   c   d   e   f   g   h ");
    }
    
    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves){
        for (int r = 0; r < pieces.length; r++) {
            System.out.print("\n" + (8 - r) + " ");
            for (int c = 0; c < pieces.length; c++) {
                printPiece(pieces[r][c],possibleMoves[r][c]);
            }
        }
        System.out.println("\n   a   b   c   d   e   f   g   h ");
    }

    private static void printPiece(ChessPiece piece, boolean background) {
        if(background){
            System.out.print(ANSI_GREEN);
        }
        if (piece == null) {
            System.out.print(" - "+ ANSI_RESET);
        } else {
            if(piece.getColor() == Color.WHITE){
                System.out.print(ANSI_WHITE + " " +piece+ " " + ANSI_RESET);
            }else{
                System.out.print(ANSI_CYAN + " " +piece+ " " + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
    
    //method that has a list parameter to store the captured pieces;
    private static void printCapturedPieces(List<ChessPiece> captured){
        List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList()); 
        
        System.out.println("Captured Pieces: ");
        
        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        
        System.out.print(ANSI_CYAN);
        System.out.print("Black: ");
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }

}
