package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	public Board(int rows, int columns) {
		if (rows < 1 || columns <1) {//programação defensiva, para caso for criado um tabuleiro com menos de 1 linha ou coluna, o erro será tratado
			throw new BoardException("Erro criando tabuleiro: é necessario que tenha no minimo 1 linha e 1 coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}
	public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) { //criado esse metodo para retornar a peça na linha e coluna
		if(!positionExists(row, column)) {
			throw new BoardException("Posição não está no tabuleiro");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {//criado uma sobrecarga para retornar a posição que a peça está
		if(!positionExists(position)) {
			throw new BoardException("Posição não está no tabuleiro");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {//criado um metodo para colocar a peça na posição do tabuleiro
		if(thereIsAPiece(position)) {
			throw new BoardException("Já tem uma peça na posição " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;//a peça será atribuida a peça que veio como argumento na linha e coluna designada
		piece.position = position;//ou seja, o local não é mais nulo, está na posição que foi informada no metodo
	}
	
	private boolean positionExists(int row, int column) {//metodo auxiliar criado, porque dentro da classe, terá um momento que será mais facil testar pela linha e coluna
		return row >= 0 && row < rows && column >= 0 && column < columns;//condilçao para se retornar a posição, onde não pode ser menor que 0 e nem maior que o tamanho de linhas e colunas do tabuleiro
	}
	
	public boolean positionExists(Position position) {//função position exists criado recebendo position como argumento
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {//criado argumento para saber se existe peça ali ou não
		if(!positionExists(position)) {
			throw new BoardException("Posição não está no tabuleiro");
		}
		return piece(position) != null;//caso seja diferente de nulo, voltará a peça
	}
}
