package chess;

import boardgame.BoardException;

public class ChessException extends BoardException{//alterado para Board Exception para tratar tambem erros do tabuleiro

	private static final long serialVersionUID = 1L;

	public ChessException(String msg) {//para passar a mensagem
		super(msg);//recebendo a mensagem para repassar
	}
}
