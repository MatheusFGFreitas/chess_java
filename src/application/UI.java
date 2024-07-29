package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {// o tabuleiro em si, nessa pagina será impressa como está o tabuleiro
	
	//codigos especiais para printar as cores, retiradas no link abaixo segundo a aula
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

	public static ChessPosition readChessPosition(Scanner sc) {//recebendo o Scanner do programa principal, para receber ele como argumento
		try {//feito bloco catch para caso aconteça algum erro, será tratado
		String s = sc.nextLine();//mandei ler o String com um sc.next line
		char column = s.charAt(0);//a coluna é o 1 caractere do string, então foi retornado no charAt(0)
		int row = Integer.parseInt(s.substring(1));//int row recebe interger.parseInt a partir da 1 linha e traz o resultado inteiro
		return new ChessPosition(column, row);//retorna a coluna e linha
		}
		catch (RuntimeException e) {//caso aconteça o erro
			throw new InputMismatchException("Erro lendo ChessPosition, valores validos são de a1 até h8");//ele é tratado e traz a explicação
		}
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {// criado um for para mostrar o tabuleiro na tela
			System.out.print((8 - i) + " ");// feito a 1 linha para começar a parte de cima do tabuleiro
			for (int j = 0; j < pieces.length; j++) {// criado a 2 parte do tabuleiro, para representar as colunas
				printPiece(pieces[i][j]);
			}
			System.out.println();// quebra de linha depois da ultima linha
		}
		System.out.println("  a b c d e f g h");// impressão das letras
	}

	private static void printPiece(ChessPiece piece) {
		if (piece == null) {// caso não tenha nenhuma peça naquele local
			System.out.print("-");// é retornado o lugar com o - representando vazio
		} else {// caso tenha
			if (piece.getColor() == Color.WHITE) {//se a cor da peça for branca
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			}
			else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
}
