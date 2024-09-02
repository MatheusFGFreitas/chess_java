package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece{

	public Queen(Board board, Color color) {
		super(board, color);//criado automaticamente pelo sistema
	}

	@Override
	public String toString() {//está fazendo a peça retornar uma letra, então no local de - que seria o local vazio, aparecerá Q de torre
		return "Q";
	}
	
	@Override
	public boolean[][] possibleMoves() {//isso mostrara se a peca pode andar em determinadas casas a cima, lados e baixo, caso haja uma peca aliada, não podera se mover até ela, porém se for inimiga, podera capturar
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0 ,0);//criada posição auxiliar para ter um valor inicial
		
		//verificando acima
		p.setValues(position.getRow() - 1, position.getColumn());//para verificar acima, tem que ver a posição na mesma coluna, porem para ver acima, tem que ir decrementando a linha
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//enquanto a posição estiver vaga vai marcar como verdadeira
			mat[p.getRow()][p.getColumn()] = true;//voltando o valor verdadeiro
			p.setRow(p.getRow() - 1);//feito uma repetição para subir mais um enquanto a casa estiver vazia
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//se existir uma peça adversaria no local
			mat[p.getRow()][p.getColumn()] = true;//é marcada como verdadeiro, dizendo que pode mover para o local
		}
		//para baixo
		p.setValues(position.getRow() + 1, position.getColumn());//dessa vez verificando abaixo, então tem que somar a linha
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//se existir uma peça adversaria no local
			mat[p.getRow()][p.getColumn()] = true;//é marcada como verdadeiro, dizendo que pode mover para o local
		}
		//verificando a esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);//dessa vez para a esquerda, tendo que diminuir a coluna
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);;
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//se existir uma peça adversaria no local
			mat[p.getRow()][p.getColumn()] = true;//é marcada como verdadeiro, dizendo que pode mover para o local
		}
		//para direita
		p.setValues(position.getRow(), position.getColumn() + 1);//dessa vez a direita, adicionando um para a coluna
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() +1 );;
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {//se existir uma peça adversaria no local
			mat[p.getRow()][p.getColumn()] = true;//é marcada como verdadeiro, dizendo que pode mover para o local
		}
		
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
