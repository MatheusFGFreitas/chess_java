package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

	//copiado tudo da peça torre, já que são peças parecidas, apenas mudado movimento e nomes de classes
	public Bishop(Board board, Color color) {
		super(board, color);//criado automaticamente pelo sistema
	}

	@Override
	public String toString() {//está fazendo a peça retornar uma letra, então no local de - que seria o local vazio, aparecerá R de torre
		return "B";
	}
	
	@Override
	public boolean[][] possibleMoves() {//isso mostrara se a peca pode andar em determinadas casas a cima, lados e baixo, caso haja uma peca aliada, não podera se mover até ela, porém se for inimiga, podera
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0 ,0);//criada posição auxiliar para ter um valor inicial
		
		//Noroeste
		p.setValues(position.getRow() - 1, position.getColumn() - 1);//verificando acima e a esquerda
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//se existir uma peça adversaria no local
			mat[p.getRow()][p.getColumn()] = true;//é marcada como verdadeiro, dizendo que pode mexer para lá
			p.setValues(p.getRow() - 1, p.getColumn() - 1);//colocado o local apos o movimento
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//se tiver peça no local
			mat[p.getRow()][p.getColumn()] = true;//a peça é capturada
		}
		//Nordeste
		p.setValues(position.getRow() -1 , position.getColumn() + 1);//nordeste, sendo -1 linha e + 1 coluna
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() -1, p.getColumn() +1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//se existir uma peça adversaria no local
			mat[p.getRow()][p.getColumn()] = true;//é marcada como verdadeiro, dizendo que pode mover para o local
		}
		//Sudeste
		p.setValues(position.getRow() +1, position.getColumn() + 1);//Sudeste, sendo +1 linha e +1 coluna
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//se existir uma peça adversaria no local
			mat[p.getRow()][p.getColumn()] = true;//é marcada como verdadeiro, dizendo que pode mover para o local
		}
		//Sudoeste
		p.setValues(position.getRow() +1, position.getColumn() - 1);//Sudoeste, linha +1 coluna -1
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//se existir uma peça adversaria no local
			mat[p.getRow()][p.getColumn()] = true;//é marcada como verdadeiro, dizendo que pode mover para o local
		}
		
		return mat;
	}
}
