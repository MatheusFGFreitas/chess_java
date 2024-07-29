package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {// o coração do projeto, com todas as regras do jogo

	public Board board;

	public ChessMatch() {// criado um construtor padrão, criando um tabuleiro 8 por 8
		board = new Board(8, 8);
		initialSetup();
	}

	public ChessPiece[][] getPieces() {// criado para retornar a matriz das peças de xadrez correspondente a essa
										// partida
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {// criado um for para as linhas
			for (int j = 0; j < board.getColumns(); j++) {// e um for interior para as colunas
				mat[i][j] = (ChessPiece) board.piece(i, j);// vai receber a peça via downcast do ChessPiece, para ser
															// interpretado como peça de xadrez, n apenas uma peça
															// generica
			}
		}
		return mat;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {// metodo irá recer as coordenadas do xadrez
		board.placePiece(piece, new ChessPosition(column, row).toPosition());// passando as coordenadas para um novo
																				// chess position com toPosition
	}

	private void initialSetup() {// metodo responsavel para colocar as peças no local inicial
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));//instanciados cada peça no tabuleiro
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
