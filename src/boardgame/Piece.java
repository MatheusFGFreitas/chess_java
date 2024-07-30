package boardgame;

public abstract class Piece {

		protected Position position;//para não ser acessado indevidamente, utilizamos protected
		private Board board;
		
		public Piece(Board board) {
			this.board = board;
			position = null;
		}

		protected Board getBoard() {//deixado em protected para somente classes no mesmo pacote e subpacotes poderão acessar o tabuleiro
			return board;
		}

		public abstract boolean[][] possibleMoves();//criado argumento abstrato para receber verdadeiro ou falso 
		
		public boolean possibleMove(Position position) {//argumento para retornar se é possivel mover a peça ou não
			return possibleMoves()[position.getRow()][position.getColumn()];//vai retornar se é possivel mover graças ao metodo abstrato
		}//isso foi um metodo concreto com um metodo abstrato, um Hook method
		
		public boolean isThereAnyPossibleMove() {//argumento irá utilizar novamente possibleMove para ver se tem pelo menos um movimento disponivel
			boolean[][] mat = possibleMoves();//dai irá trazer um laço de repetição pra ver se tem um movimento verdadeiro
			for (int i=0; i<mat.length; i++) {
				for (int j=0; j<mat.length; j++) {
					if (mat[i][j]) {
						return true;
					}
				}
			}
			return false;
		}
}
