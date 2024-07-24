package chess;

import boardgame.Board;

public class ChessMatch {//o coração do projeto, com todas as regras do jogo
	
	public Board board;
	
	public ChessMatch() {//criado um construtor padrão, criando um tabuleiro 8 por 8
		board = new Board(8, 8);
	}
	
	public ChessPiece[][] getPieces(){//criado para retornar a matriz das peças de xadrez correspondente a essa partida
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows(); i++) {//criado um for para as linhas
			for (int j=0; j<board.getColumns(); j++) {//e um for interior para as colunas
				mat[i][j] = (ChessPiece) board.piece(i, j);//vai receber a peça via downcast do ChessPiece, para ser interpretado como peça de xadrez, n apenas uma peça generica
			}
		}
		return mat;
	}
}
