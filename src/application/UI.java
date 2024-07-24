package application;

import chess.ChessPiece;

public class UI {//o tabuleiro em si, nessa pagina será impressa como está o tabuleiro

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i=0; i<pieces.length; i++) {//criado um for para mostrar o tabuleiro na tela
			System.out.print((8 - i) + " ");//feito a 1 linha para começar a parte de cima do tabuleiro
			for (int j=0; j<pieces.length; j++) {//criado a 2 parte do tabuleiro, para representar as colunas
				printPiece(pieces[i][j]);
			}
			System.out.println();//quebra de linha depois da ultima linha
		}
		System.out.println("  a b c d e f g h");//impressão das letras
	}
	
	private static void printPiece(ChessPiece piece) {
		if (piece == null) {//caso não tenha nenhuma peça naquele local
			System.out.print("-");//é retornado o lugar com o - representando vazio
		}
		else {//caso tenha
			System.out.print(piece);//retorna qual é a peça ali
		}
		System.out.print(" ");
	}
}
