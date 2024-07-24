package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {//o coração do projeto, com todas as regras do jogo
	
	public Board board;
	
	public ChessMatch() {//criado um construtor padrão, criando um tabuleiro 8 por 8
		board = new Board(8, 8);
		initialSetup();
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
	
	private void initialSetup() {//metodo responsavel para colocar as peças no local inicial
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));//instanciado a peça Torre, com o tabuleiro, e a cor Branca, no local 2, 1
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));//instanciado a peça rei no tabuleiro
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));//instanciado a peça rei branca no tabuleiro
	}
}
