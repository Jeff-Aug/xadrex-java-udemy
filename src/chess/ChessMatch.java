//onde ocorre as regrras de xadrez

package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	
	public ChessMatch() {
		
		board = new Board(8,8);
		initialSetup();
	}
	
	
	public ChessPiece[][] getPieces(){//retorna uma matriz correspondente a partida de xadrez
		ChessPiece[][] mat = new ChessPiece[board.getLinhas()][board.getColunas()];
		
		for (int i = 0; i < board.getLinhas(); i++) {
			for (int j = 0; j < board.getColunas(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j);//retorna uma peça de xadrez
			}
			
		}
		
		return mat;//retorna a matriz de peças na partida de xadrez
		
	}
	
	private void initialSetup() {
		
		board.placePiece(new Rook(board, Color.WHITE), new  Position(3,4));
		board.placePiece(new King(board, Color.BLACK), new  Position(7,3));
		board.placePiece(new King(board, Color.BLACK), new  Position(5,7));
		
	}
	
	
	
	
	
}
