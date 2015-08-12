package algorithms.mazeGenerators;

public class Maze3d {

	private int height;
	private int depth;
	private int width;
	private int[][][] theMaze;

	public Maze3d(int height, int depth, int width) {
		this.height = height;
		this.depth = depth;
		this.width = width;
		theMaze = new int[height][depth][width];
	}

	public int[][][] getTheMaze() {
		return theMaze;
	}

	public int getValueAt(int x, int y, int z) {
		return theMaze[x][y][z];
	}

	public void setWallAt(int x, int y, int z) {
		theMaze[x][y][z] = 1;
	}

	public void setHoleAt(int x, int y, int z) {
		theMaze[x][y][z] = 0;
	}

	public int getHeight() {
		return height;
	}

	public int getDepth() {
		return depth;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < theMaze.length; i++) {
			str.append("floor " + i + ":\n");
			for (int j = 0; j < theMaze[i].length; j++) {
				str.append("[");
				for (int k = 0; k < theMaze[i][j].length; k++) {
					str.append(" " + theMaze[i][j][k] +" ");
				}
				str.append("]\n");
			}
			str.append("\n");
		}
		return str.toString();
	}

}
