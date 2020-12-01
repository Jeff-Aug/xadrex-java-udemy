package boardgame;

public abstract class Piece {
	
	protected Position position;
	private Board board;
	
	
	public Piece(Board board) {
		
		this.board = board;
		position = null; 
	}


	protected Board getBoard() {
		return board;
	}
	
	
	public abstract boolean[][] possibleMoves();
		
		////possibilidade de movimento
	public boolean possibleMove(Position position){
		
		return possibleMoves()[position.getRow()] [position.getColumn()];
		
	}
	
	
	//verificando a possibilidade de movimento
	public boolean isThereAnyPossibleMove() {//erifica se a peça esta presa ou nao
		
		boolean [][] mat = possibleMoves();
		
		for(int i=0 ; i < mat.length ; i++) {//verifica a possibilidade de movimento
			for(int j=0 ; j < mat.length ; j++) {
		
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
















}
