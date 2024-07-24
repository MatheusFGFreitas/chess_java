package chess;

import boardgame.Board;//ctrl shitf o para importar o necessario para funcionar
import boardgame.Piece;

public class ChessPiece extends Piece{//extende a classe Piece

	private Color color;

	public ChessPiece(Board board, Color color) {//começado a implementar a classe da peça de xadrez, com o super construtor da classe board
		super(board);
		this.color = color;
	}

	public Color getColor() {//apagado o set pq n se pode modificiar a cor da peça
		return color;
	}

	
}
