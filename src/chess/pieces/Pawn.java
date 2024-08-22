package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{

	public Pawn(Board board, Color color) {//puxado automaticamente, pois o proprio compilador reclama da falta de construtor e implementar o metodo abstrato
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0 ,0);//criada posição auxiliar para ter um valor inicial
		
		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1,  position.getColumn());//olhando uma posição acima
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//comando dizendo que se existir uma linha acima dele e estiver vazia, ele pode mover para lá
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 2,  position.getColumn());//olhando duas posições acima
			Position p2 = new Position(position.getRow() - 1,  position.getColumn());//para ver uma posição acima
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {//comando dizendo que se existir duas linha acima dele e estiver vazia, ele pode mover para lá, porém só no primeiro movimento
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1,  position.getColumn() -1 );//olhando diagonal pra esquerda
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//comando dizendo que se existir uma peça na diagonal, ele pode mover para lá
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1,  position.getColumn() +1);//olhando diagonal a direita
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//comando dizendo que se existir uma peça na diagonal, ele pode mover para lá
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		else {
			p.setValues(position.getRow() + 1,  position.getColumn());//olhando uma posição abaixo
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//comando dizendo que se existir uma linha abaixo dele e estiver vazia, ele pode mover para lá
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 2,  position.getColumn());//olhando duas posições abaixo
			Position p2 = new Position(position.getRow() + 1,  position.getColumn());//para ver uma posição abaixo
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {//comando dizendo que se existir duas linha abaixo dele e estiver vazia, ele pode mover para lá, porém só no primeiro movimento
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1,  position.getColumn() -1 );//olhando diagonal pra esquerda
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//comando dizendo que se existir uma peça na diagonal, ele pode mover para lá
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1,  position.getColumn() +1);//olhando diagonal a direita
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//comando dizendo que se existir uma peça na diagonal, ele pode mover para lá
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
	
}
