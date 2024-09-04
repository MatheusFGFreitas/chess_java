package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();//feito o arraylist
		
		while (!chessMatch.getCheckMate()) {//alterado para caso a partida não esteja em xeque mate, o programa continua rodando
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Local: ");//le o local que voce ira mover a peça
				ChessPosition source = UI.readChessPosition(sc);//depois que o usuario digitou a posição de origem	
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);//declarada matriz booleana recebendo os movimentos possiveis
				UI.clearScreen();//limpada a tela
				UI.printBoard(chessMatch.getPieces(), possibleMoves);//printado o tabuleiro com os movimentos possiveis
				System.out.println();
				System.out.print("Destino: ");//le o destino que você ira mover a peça
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);//caso capture a peça, vai pra essa variavel
				
				if (capturedPiece != null) {//comando para capturar a peça, e a inserir na lista
					captured.add(capturedPiece);
				}
				
				if(chessMatch.getPromoted() != null) {
					System.out.println("Entre a peca da promocao (B/N/R/Q): ");
					String type = sc.nextLine();
					chessMatch.replacePromotedPiece(type);
				}
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
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}
}
