package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{//criado pagina do Rei

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {//feito argumento para voltar a letra K, que aparecerá no tabuleiro em vez de -
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}
}
