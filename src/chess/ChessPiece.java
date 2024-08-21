package chess;

import boardgame.Board;//ctrl shitf o para importar o necessario para funcionar
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{//extende a classe Piece

	private Color color;

	public ChessPiece(Board board, Color color) {//começado a implementar a classe da peça de xadrez, com o super construtor da classe board
		super(board);
		this.color = color;
	}

	public Color getColor() {//apagado o set pq n se pode modificiar a cor da peça
		return color;
	}
	
	public ChessPosition getChessPosition() {//tendo acesso a posição pela herança, convertendo o metodo estatico do chess position para a posição de xadrez
		return ChessPosition.fromPosition(position);
	}

	protected boolean isThereOpponentPiece(Position position) {//feita uma operação generica para ser reaproveitada em todas as peças
		ChessPiece p = (ChessPiece)getBoard().piece(position);//criada a variavel para receber a peça que está nesse local, feito um Downcast pra ChessPiece
		return p != null && p.getColor() != color;//para saber se é uma peça adversaria, tem que ver se for diferente de nulo, ou diferente da cor da minha peça, sendo assim uma peça adversaria
	}
}
