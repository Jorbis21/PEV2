package src;

public class TableroGlobal {
	private int dimension ;
	private int tableroGlobal[][];
	
	public TableroGlobal() {
		dimension = 8;
		tableroGlobal = new int[8][dimension];
	}
	public TableroGlobal(int dimension, int tabGlob[][]) {
		this.dimension = dimension;
		tableroGlobal = new int[8][dimension];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < dimension; j++) {
				tableroGlobal[i][j] = tabGlob[i][j];
			}
		}
	}
	public int[][] getTab() {
		return tableroGlobal;
	}
	public int getDim() {
		return dimension;
	}
}

