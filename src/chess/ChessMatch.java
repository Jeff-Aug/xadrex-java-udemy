//onde ocorre as regrras de xadrez

package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Color currentPlayer;
	private Board board;
	
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces= new ArrayList<>();
	
	private int turn;
	
	
	
	public int getTurn() {
		return turn;
	}
	
	

	public ChessMatch() {
		
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	
	
	
	
	public ChessPiece[][] getPieces(){//retorna uma matriz correspondente a partida de xadrez
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j);//retorna uma pe�a de xadrez
			}
			
		}
		
		return mat;//retorna a matriz de pe�as na partida de xadrez
		
	}
	
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	
	
	private void nextTurn() {
		
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; 
		
	}
	
	
	//fica respossavel por mover as pe�as no tabuleiro
	public ChessPiece performChessMove(ChessPosition sourcePosition,ChessPosition targetPosition) {
		
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);//validado posi��o de origem
		
		
		validateTargetPosition(source, target);
		
		Piece capturedPiece = makeMove(source,target);
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	
	private Piece makeMove(Position source,Position target) {
		
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);//tira a pe�a 
			capturedPieces.add(capturedPiece);//adiciona na lista
			
		}
		
		return capturedPiece;
		
		
		
	}
	
	private void validateSourcePosition(Position position) {
		
		if(!board.thereIsPiece(position)) {
			
			throw new ChessException("N�o existe pe�a na posi��o de origem");
		}
		
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			
			throw new ChessException("O movimento em questao nao eh seu");
			
		}
		
		//movimentos possiveis
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao existe movimentos possivveis para a pe�a escolhida");
		}
		
		
	}
	
	
	
	private void validateTargetPosition(Position source,Position target) {//valida��o da posi��o de destino com rela��o a posi�ao origem
		
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("A pe�a escolhida n�o pode ser movida para a posi�ao de destino");
		}
		
		
		
	}
	
	private void placeNewPiece(char column , int row, ChessPiece piece) {
		
		board.placePiece(piece, (new ChessPosition(column, row).toPosition()));
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
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
        placeNewPiece('h', 8, new King(board, Color.BLACK));
		
	}
	
	
	
	
	
}
