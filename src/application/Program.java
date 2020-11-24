package application;


import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessmatch = new ChessMatch();
		UI.printBoard(chessmatch.getPieces());//recebe a matriz de peça da partida

	}

}
