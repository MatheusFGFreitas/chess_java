package boardgame;

public class Piece {

		protected Position position;//para não ser acessado indevidamente, utilizamos protected
		private Board board;
		
		public Piece(Board board) {
			this.board = board;
			position = null;
		}

		protected Board getBoard() {//deixado em protected para somente classes no mesmo pacote e subpacotes poderão acessar o tabuleiro
			return board;
		}

		
		
}
