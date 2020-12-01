package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {//por padr�o todas as posi��o come�a com falso


		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		return mat;
	}

}
