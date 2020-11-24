package boardgame;

public class Board {
	
	
	private int linhas;
	private int colunas;
	
	private Piece[][] pieces;

	public Board(int linhas, int colunas) {
		if(linhas <1 || colunas <1 ) {
			throw new BoardException("Erro na criação do tabuleiro : Deve haver necessariamente pelos menos uma (1) linha e uma (1) coluna ");
			
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

	
	public Piece piece(int linha , int coluna) { //retorna da posição da peça
		
		
		if(!positionExists(linha, coluna)) {
			throw new BoardException("Posição nao encotrada no tabuleiro");
		}
		return pieces[linha][coluna];
		
	}
	
	
	
	public Piece piece(Position position) {//coloca uma peça em uma dada posição
		
		if(!positionExists(position)) {
			throw new BoardException("Esta posição é invalidade ou ja encontra uma peça");
		}
		return pieces[position.getLinha()][position.getColuna()];
	}
	
	
	
	

	public void placePiece (Piece piece,Position position) {//recebe uma poeça e uma posição
		
		if(thereIsPiece(position)) {
			throw new BoardException("já encontra uma peça nessa posição");
		}
		pieces[position.getLinha()][position.getColuna()] = piece;
		piece.position = position;
		
		}
		
		
		public Piece removePiece(Position position){
			if (!positionExists(position)){
								
				throw new BoardException("Posição não existe");
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
	
	
	//testando a existencia da peça
	
	public boolean thereIsPiece(Position position) {
		if(!positionExists(position)) {//testa primeiro a existencia da poosição
			throw new BoardException("Posição nao encotrada no tabuleiro");
		}
		return piece(position) != null;
		
		
	}

}
