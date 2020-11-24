package boardgame;

public class Board {
	
	
	private int linhas;
	private int colunas;
	
	private Piece[][] pieces;

	public Board(int linhas, int colunas) {
		if(linhas <1 || colunas <1 ) {
			throw new BoardException("Erro na cria��o do tabuleiro : Deve haver necessariamente pelos menos uma (1) linha e uma (1) coluna ");
			
		}
		this.linhas = linhas;
		this.colunas = colunas;
		
		pieces = new Piece[linhas][colunas];
	}

	
	public int getLinhas() {
		return linhas;
	}

	
	public int getColunas() {
		return colunas;
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
		return pieces[position.getLinha()][position.getColuna()];
	}
	
	
	
	

	public void placePiece (Piece piece,Position position) {//recebe uma poe�a e uma posi��o
		
		if(thereIsPiece(position)) {
			throw new BoardException("j� encontra uma pe�a nessa posi��o");
		}
		pieces[position.getLinha()][position.getColuna()] = piece;
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
			
			pieces[position.getLinha()][position.getColuna()] = null;
			return aux;
			
			
			
		}
		
		
	
	
//////////////////////////////////////////////////
	public boolean positionExists(int row ,int column) {
		
		return row >= 0 && row < linhas && column >= 0 && column < colunas;
		
	}
	public boolean positionExists(Position position) {
		
		return positionExists(position.getLinha(), position.getColuna());
		
		
	}
	
	
	//testando a existencia da pe�a
	
	public boolean thereIsPiece(Position position) {
		if(!positionExists(position)) {//testa primeiro a existencia da poosi��o
			throw new BoardException("Posi��o nao encotrada no tabuleiro");
		}
		return piece(position) != null;
		
		
	}

}
