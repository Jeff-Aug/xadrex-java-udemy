package boardgame;

public class Board {
	
	
	private int rows;
	private int columns;
	
	private Piece[][] pieces;

	public Board(int linhas, int colunas) {
		if(linhas <1 || colunas <1 ) {
			throw new BoardException("Erro na cria��o do tabuleiro : Deve haver necessariamente pelos menos uma (1) linha e uma (1) coluna ");
			
		}
		this.rows = linhas;
		this.columns = colunas;
		
		pieces = new Piece[linhas][colunas];
	}

	
	public int getRows() {
		return rows;
	}

	
	public int getColumns() {
		return columns;
	}

	
	public Piece piece(int linha , int coluna) { //retorna da posi��o da pe�a
		
		
		if(!positionExists(linha, coluna)) {
			throw new BoardException("Posi��o nao encotrada no tabuleiro");
		}
		return pieces[linha][coluna];
		
	}
	
	
	
	public Piece piece(Position position) {//coloca uma pe�a em uma dada posi��o
		
		if(!positionExists(position)) {
			throw new BoardException("Esta posi��o � invalidade ou ja encontra uma pe�a");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	
	
	

	public void placePiece (Piece piece,Position position) {//recebe uma poe�a e uma posi��o
		
		if(thereIsPiece(position)) {
			throw new BoardException("j� encontra uma pe�a nessa posi��o");
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
		
		}
		
		
		public Piece removePiece(Position position){
			if (!positionExists(position)){
								
				throw new BoardException("Posi��o n�o existe");
			}
			if(piece(position)==null) {
				return null;
			}
			Piece aux = piece(position);
			aux.position = null;
			
			pieces[position.getRow()][position.getColumn()] = null;
			return aux;
			
			
			
		}
		
		
	
	
//////////////////////////////////////////////////
	public boolean positionExists(int row ,int column) {
		
		return row >= 0 && row < rows && column >= 0 && column < columns;
		
	}
	public boolean positionExists(Position position) {
		
		return positionExists(position.getRow(), position.getColumn());
		
		
	}
	
	
	//testando a existencia da pe�a
	
	public boolean thereIsPiece(Position position) {
		if(!positionExists(position)) {//testa primeiro a existencia da poosi��o
			throw new BoardException("Posi��o nao encotrada no tabuleiro");
		}
		return piece(position) != null;
		
		
	}

}
