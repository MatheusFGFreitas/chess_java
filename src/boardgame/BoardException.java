package boardgame;

public class BoardException extends RuntimeException{//criado e dado extends em RuntimeException para tratar erros
	private static final long serialVersionUID = 1L;//escolhido o numero universal pelo proprio sistema

	public BoardException(String msg) {//esse construtor ira repassar a mensagem para o RuntimeException
		super(msg);
	}
}
