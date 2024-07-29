package chess;

public class ChessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ChessException(String msg) {//para passar a mensagem
		super(msg);//recebendo a mensagem para repassar
	}
}
