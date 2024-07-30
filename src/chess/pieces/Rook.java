package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{//criado pagina para representar a torre do xadrez

	public Rook(Board board, Color color) {
		super(board, color);//criado automaticamente pelo sistema
	}

	@Override
	public String toString() {//está fazendo a peça retornar uma letra, então no local de - que seria o local vazio, aparecerá R de torre
		return "R";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}
}
