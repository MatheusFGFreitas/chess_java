package chess;

import boardgame.Position;

public class ChessPosition {
	
	private char column;
	private int row;
	public ChessPosition(char column, int row) {
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {//feito programação defensiva para caso as colunas ou linhas forem maiores ou menores do que as que são aceitas
			throw new ChessException("Erro instanciando a posicao no xadrez, os valores validos sao de a1 ate h8");
		}
		this.column = column;
		this.row = row;
	}
	public char getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}

	protected Position toPosition() {//metodo que retornará uma nova posição
		return new Position(8 - row, column - 'a');//retorno sendo a linha - coluna e coluna - 'a' que seria 1
	}
	
	protected static ChessPosition fromPosition(Position position) {//metodo estatico recebendo position como argumento
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());//trazendo a formula inversa a position to position, convertendo pra char pois a conversão não é automatica
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
}
