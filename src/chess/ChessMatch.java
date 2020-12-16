//onde ocorre as regrras de xadrez

package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Color currentPlayer;
	private Board board;
	private boolean check;
	
	
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
		//check = false;
		initialSetup();
	}
	
	

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	
	
	
	
	public ChessPiece[][] getPieces(){//retorna uma matriz correspondente a partida de xadrez
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j);//retorna uma peça de xadrez
			}
			
		}
		
		return mat;//retorna a matriz de peças na partida de xadrez
		
	}
	
	
	public boolean getCheck() {
		
		return check;
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
	
	
	//fica respossavel por mover as peças no tabuleiro
	public ChessPiece performChessMove(ChessPosition sourcePosition,ChessPosition targetPosition) {
		
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);//validado posição de origem
		
		
		validateTargetPosition(source, target);
		
		Piece capturedPiece = makeMove(source,target);
		
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Voce nao pode se colocaar em check");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false ;
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	//desfazer movimento ,ou seja, o jogado em questao nao pode se colocar em cheque
	
	private void undoMove(Position source,Position target,Piece capturedPiece) {
		
		Piece p = board.removePiece(target);
		board.placePiece(p, source);//devolvendo para a posiçao de origem
		
		
		if(capturedPiece != null) {//caaso tenha capturadop uma peca
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);//tirando da lista de peças capturadas
			piecesOnTheBoard.add(capturedPiece);//adcionando no  tabuleiro
			
		
		}
		
		
		
	}
	
	
	
	
	private Piece makeMove(Position source,Position target) {
		
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);//tira a peça 
			capturedPieces.add(capturedPiece);//adiciona na lista
			
		}
		
		return capturedPiece;
		
		
		
	}
	
	private void validateSourcePosition(Position position) {
		
		if(!board.thereIsPiece(position)) {
			
			throw new ChessException("Não existe peça na posição de origem");
		}
		
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			
			throw new ChessException("O movimento em questao nao eh seu");
			
		}
		
		//movimentos possiveis
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Nao existe movimentos possivveis para a peça escolhida");
		}
		
		
	}
	
	
	
	private void validateTargetPosition(Position source,Position target) {//validação da posição de destino com relação a posiçao origem
		
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("A peça escolhida não pode ser movida para a posiçao de destino");
		}
		
		
		
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	
	private ChessPiece king(Color color) {//localização do rei de uma determinada cor
		
		//filtrando as peças do jogo
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p : list) {
			if(p instanceof King) {
				return(ChessPiece)p;
			}
		}
		//confirmação de nao haver nenhum rei na list
		throw new IllegalStateException("Nao ha essa peca" + color + "king on the board");
	}
	
	
	private boolean testCheck(Color color) {
		
		Position kingPosition =  king(color).getChessPosition().toPosition();
		//verifica se o rei esta sob ameaça,verificando,peça por peca do oponente se vai de encontro ao rei(ou seja, toma a posião do rei) 
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent (color)).collect(Collectors.toList());
		for(Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				
				return true;
			}
			
		}
		return false;
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
        placeNewPiece('d', 8, new King(board, Color.BLACK));
		
	}
	
	
	
	
	
}
