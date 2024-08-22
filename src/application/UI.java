package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
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
	
	//para limpar a tela
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
		public static void clearScreen() {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}	

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
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);//para imprimir as peças capturadas na tela
		System.out.println();
		System.out.println("turno; " + chessMatch.getTurn());
		if (!chessMatch.getCheckMate()) {//feito um if, para caso não esteja com xeque mate o jogo continua
			System.out.println("aguardando jogador da cor: " +  chessMatch.getCurrentPlayer());
			if (chessMatch.getCheck()) {
				System.out.println("Xeque!");
			}
		}
		else {
			System.out.println("Xeque-Mate!");
			System.out.println("Vencedor: " + chessMatch.getCurrentPlayer());
		}
	}
	
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {// criado um for para mostrar o tabuleiro na tela
			System.out.print((8 - i) + " ");// feito a 1 linha para começar a parte de cima do tabuleiro
			for (int j = 0; j < pieces.length; j++) {// criado a 2 parte do tabuleiro, para representar as colunas
				printPiece(pieces[i][j], false);//agora graças a implementação do possible moves, caso seja falso não ira colorir
			}
			System.out.println();// quebra de linha depois da ultima linha
		}
		System.out.println("  a b c d e f g h");// impressão das letras
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {//tabuleiro com as posições marcadas
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);//a diferença é que agora ele recebe os movimentos coloridos
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {//se a variavel background é verdadeira
			System.out.print(ANSI_BLUE_BACKGROUND);//a cor do fundo é alterada
		}
		if (piece == null) {// caso não tenha nenhuma peça naquele local
			System.out.print("-" + ANSI_RESET);// é retornado o lugar com o - representando vazio
		} else {// caso tenha
			if (piece.getColor() == Color.WHITE) {//se a cor da peça for branca
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);//ansi reset é para resetar a cor
			}
			else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	private static void printCapturedPieces(List<ChessPiece> captured) {//feito um comando para fazer uma lista com as peças capturadas de cada cor
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());//lista para peças brancas
		List<ChessPiece> black= captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());//lista para peças pretas
		System.out.println("Pecas capturadas");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);//para transformar o que vier em branco
		System.out.println(Arrays.toString(white.toArray()));//sairá as peças capturadas na cor da fonte branca
		System.out.print(ANSI_RESET);//irá resetar a cor inserida
		System.out.print("Pretas: ");
		System.out.print(ANSI_YELLOW);//para transformar o que vier em amarelo
		System.out.println(Arrays.toString(black.toArray()));//sairá as peças capturadas na cor da fonte amarela
		System.out.print(ANSI_RESET);//irá resetar a cor inserida
	}
}
