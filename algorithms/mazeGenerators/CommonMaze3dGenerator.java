package algorithms.mazeGenerators;

public abstract class CommonMaze3dGenerator implements Maze3dGenerator {

	int x;
    int y;
    int z;
	
	@Override
	public abstract Maze3d generate();
	
	@Override
	public String measureAlgorithmTime(){
		long startTime = System.currentTimeMillis();
		generate();
		long endTime = System.currentTimeMillis();
		return "Algorithm Time: " + (endTime - startTime) / 1000.0 + " Seconds\n";
	}
}
