package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{

	private ChessMatch chessMatch;//criado dependencia para a partida
	
	public Pawn(Board board, Color color, ChessMatch chessMatch) {//puxado automaticamente, pois o proprio compilador reclama da falta de construtor e implementar o metodo abstrato
		super(board, color);
		this.chessMatch = chessMatch;
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
			
			//en passant branco
			
			if (position.getRow() == 3) {//caso o peão branco esteja na linha 3 da matrix( linha 5 no xadrez), ira fazer o teste do en passant
				Position left = new Position(position.getRow(), position.getColumn() -1);
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){//testando se a posição da esquerda existe, se existe uma peça a esquerda e se está vulneravel a en passant
					mat[left.getRow() - 1][left.getColumn()] = true;//possibilita o movimento en passant
				}
			}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){//testando se a posição da direita existe, se existe uma peça a esquerda e se está vulneravel a en passant
					mat[right.getRow() - 1][right.getColumn()] = true;//possibilita o movimento en passant
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
			
			//en passant preto
			
			if(position.getRow() == 4) {//caso o peão branco esteja na linha 4 da matrix (linha 4 no xadrez), ira fazer o teste do en passant
				Position left = new Position(position.getRow(), position.getColumn() -1);
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable());{//testando se a posição da esquerda existe, se existe uma peça a esquerda e se está vulneravel a en passant
					mat[left.getRow() + 1][left.getColumn()] = true;//possibilita o movimento en passant
				}
			}
			Position right = new Position(position.getRow(), position.getColumn() + 1);
				if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){//testando se a posição da direita existe, se existe uma peça a esquerda e se está vulneravel a en passant
					mat[right.getRow() + 1][right.getColumn()] = true;//possibilita o movimento en passant
				}
			}
		
		return mat;
		}


	@Override
	public String toString() {
		return "P";
	}
	
}
