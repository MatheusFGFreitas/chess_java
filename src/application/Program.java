package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while (true) {
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				System.out.print("Local: ");//le o local que voce ira mover a peça
				ChessPosition source = UI.readChessPosition(sc);

				System.out.println();
				System.out.print("Destino: ");//le o destino que você ira mover a peça
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);//caso capture a peça, vai pra essa variavel
			}
			catch (ChessException e) {//caso aconteça o erro ChessException
				System.out.println(e.getMessage());//trata o erro
				sc.nextLine();
			}
			catch (InputMismatchException e) {//caso aconteça o erro InputMismatch
				System.out.println(e.getMessage());//trata o erro
				sc.nextLine();
			}
		}
	}

}
