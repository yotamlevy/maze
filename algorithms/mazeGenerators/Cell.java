package algorithms.mazeGenerators;

public class Cell {

	public int x;
	public int y;
	public int z;
	
	public Cell() {
	}

	public Cell(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	
	public void updateCell(int y, int z){
		this.y = y;
		this.z = z;
	}
	
	public void updateCell(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
}
