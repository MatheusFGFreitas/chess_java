package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {// o coração do projeto, com todas as regras do jogo

	public Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;//como o argumento é boolean, ele já vem como falso, então não precosa especificar no construtor
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();//feito a lista das peças no jogo e ja instanciado antes do construtor
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {// criado um construtor padrão, criando um tabuleiro 8 por 8
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}
	
	public ChessPiece[][] getPieces() {// criado para retornar a matriz das peças de xadrez correspondente a essa
										// partida
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {// criado um for para as linhas
			for (int j = 0; j < board.getColumns(); j++) {// e um for interior para as colunas
				mat[i][j] = (ChessPiece) board.piece(i, j);// vai receber a peça via downcast do ChessPiece, para ser
															// interpretado como peça de xadrez, n apenas uma peça
															// generica
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){//feita operação para retornar os movimentos possiveis
		Position position = sourcePosition.toPosition();//convertendo a posição de xadrez para a posição normal
		validateSourcePosition(position);//validando a posição
		return board.piece(position).possibleMoves();//retornando os possiveis movimentos
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {//operação que recebe a posição de origem da peça até a posição de destido
		Position source = sourcePosition.toPosition();//traz o local da peça
		Position target = targetPosition.toPosition();//traz o destino da peça
		validateSourcePosition(source);//feita a validação que falará se tem peça ou não
		validateTargetPosition(source, target);//para validar o destino da peça
		Piece capturedPiece = makeMove(source, target);//declarada a variavel de peça capturada como resultado do movimento da peça
		
		if (testCheck(currentPlayer)) {//if para testar se o jogadr vai se colocar em xeque, caso for
			undoMove(source, target, capturedPiece);//o movimento retorna
			throw new ChessException("voce nao pode se colocar em xeque");//e o erro é tratado
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;//caso o oponente fique em xeque apos seu movimento, o check vira verdadeiro
		
		if(testCheckMate(opponent(currentPlayer))) {//caso o movimento que eu fiz deixou em xeque mate
			checkMate = true;//retorna como verdadeiro
		}
		else {
			nextTurn();//chamado a troca de turno
		}
		return (ChessPiece)capturedPiece;//retorna a peça capturada fazendo um downcast pois a peça é um ChessPiece
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {//metodo criado para desfazer o movimento recebendo posição de origem, destino e uma possivel peca capturada
		Piece p = board.removePiece(target);//peça retirada do destino
		board.placePiece(p, source);//retorna a peça ao local de inicio
		
		if(capturedPiece != null) {//if para retornar a peça capturada para o lugar
			board.placePiece(capturedPiece, target);//caso a peça tenha sido capturada
			capturedPieces.remove(capturedPiece);//é removido da variavel de peças capturadas
			piecesOnTheBoard.add(capturedPiece);//e é adicionada novamente ao tabuleiro
		}
	}
	
	private void validateSourcePosition(Position position) {//operação para validar caso tenha peça na posição
		if(!board.thereIsAPiece(position)) {//se não houver uma peça no local
			throw new ChessException("nao tem uma peca na posicao designada");//o erro é tratado
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {//feito operação em downcast onde caso o jogador for diferente da cor da peça
			throw new ChessException("a peca escolhida nao e sua");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {//feito operação para saber se é possivel o movimento
		if (!board.piece(source).possibleMove(target)) {//se a peça de origem, a posição de destino não é possivel, é lançado o erro
			throw new ChessException("a peca escolhida nao pode mover para a posicao escolhida");//o erro é tratado
		}
	}
	
	private void nextTurn() {//feito a operação para troca de turno
		turn ++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;//feito condicional onde
		//caso o jogador for branco, então troca pra preto, se não, troca pra branco
	}
	
	private Color opponent(Color color) {//confirmando a cor
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;//se essa cor do argumento for igual a white, retorna black, caso contrario retorna white
	}
	
	private ChessPiece king(Color color) {//confirmando se a peça é de determinada cor
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("nao a nenhum rei no tabuleiro com a cor" + color);
	}
	
	private boolean testCheck(Color color) {//criando um teste para saber que está em cheque
		Position kingPosition = king(color).getChessPosition().toPosition();//linha trazendo a peça rei do usuario, para saber a posição que está no tabuleiro
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());//pegado a lista de peças do oponente
		for(Piece p : opponentPieces) {//fazendo um for para saber se as peças inimigas podem matar o rei
			boolean[][] mat = p.possibleMoves();//checando o local das peças e os movimentos das peças
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {//verificando se alguma peça pode capturar o rei
				return true;//o teste de cheque deu verdadeiro
			}
		}
		return false;//nenhuma peça está em posição de tomar o rei, logo ele não está em cheque
	}
	private boolean testCheckMate(Color color) {//criando um teste para saber se está em xeque mate
		if(!testCheck(color)) {//feito um if já de cara para retirar a possibilidade de dar erro, pois se a peça não está em xeque, logo ela tambem não está em xeque mate
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());//filtrar todas as peças da lista
		for (Piece p : list) {//feito for para cada peça da lista, porque caso a peça tenha movimento que tire do xeque, então não está em xeque mate
			boolean[][] mat = p.possibleMoves();//feito uma matriz de booleano para percorrer o possible moves de cada peça
			for (int i=0; i<board.getRows(); i++) {//for para percorrer as linhas
				for (int j=0; j<board.getColumns(); j++) {//e um para percorrer as colunas
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();//downcast da variavel p para ChessPiece, chamando o chessposition para a posicao no formato de xadrez, convertendo ela para toPosition
						Position target = new Position(i, j);//instanciando new position com a linha i e j
						Piece capturedPiece = makeMove(source, target);//fazendo o movimento da peça para a linha e coluna de destino
						boolean testCheck =  testCheck(color); //feito o teste para ver se ainda está em xeque
						undoMove(source, target, capturedPiece);//voltando as peças aos lugares após o teste
						if(!testCheck) {//caso algum movimento de peça tire do xeque
							return false;//retorna que não está em xeque mate
						}
					}
				}
			}
			
		}
		return true;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {// metodo irá recer as coordenadas do xadrez
		board.placePiece(piece, new ChessPosition(column, row).toPosition());// passando as coordenadas para um novo chess position com toPosition
		piecesOnTheBoard.add(piece);
		
	}

	private void initialSetup() {// metodo responsavel para colocar as peças no local inicial
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));//instanciados cada peça no tabuleiro
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
