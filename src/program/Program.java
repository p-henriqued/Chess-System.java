
package program;


import chessGame.ChessMatch;
import chessGame.ChessPiece;
import chessGame.ChessPosition;
import chessGame.chessGameException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        ChessMatch chess = new ChessMatch();
        
        
        //Testing
        while(true){
            try{
            UI.cleanScreen();
            UI.printMatch(chess); 
            System.out.print("\nSource: ");
            ChessPosition source = UI.readChessPosition(sc);
            
            boolean[][] possibleMoves = chess.possibleMoves(source);
            UI.cleanScreen();
            UI.printBoard(chess.getPieces(), possibleMoves);
            
            System.out.print("\nTarget: ");
            ChessPosition target = UI.readChessPosition(sc);
            ChessPiece capturedPiece = chess.performChessMove(source, target);
            }
            catch(chessGameException | InputMismatchException e){
                System.out.println(e.getMessage());
                System.out.print("Press Enter to be continue. ");
                sc.nextLine();
            }
            
        }
        
        
    }
    
}
