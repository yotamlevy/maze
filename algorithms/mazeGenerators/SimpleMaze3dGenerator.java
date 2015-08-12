package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {

	private static final int HOLE = 0;
	private static final int WALL = 1;
	private static final int WALL_PRECENTAGE_FOR_OUTER_FLOORS = 95;
	private static final int WALL_PRECENTAGE_FOR_INNER_FLOORS = 70;
	private static final int WIDTH = 8;
	private static final int DEPTH = 8;
	private static final int HEIGHT = 4;

	private Maze3d maze;
	private Random rand;

	public SimpleMaze3dGenerator() {
		this.rand = new Random();
		this.maze = new Maze3d(HEIGHT, DEPTH, WIDTH);
	}

	@Override //actually creating the maze with with the method below
	public Maze3d generate() {
		Cell lastVisitedCell = new Cell();
		handleFirstAndLastFloor(lastVisitedCell);
		handleInnerFloors(lastVisitedCell);
		return maze;
	}

	//this method will take care of the the inner floors of the 3d maze that i'm creating
	private void handleInnerFloors(Cell lastVisitedCellInPreviousFloor) {
		for (int floor = 1; floor < maze.getHeight() - 1; floor++) {
			createFloorWalls(maze, floor, WALL_PRECENTAGE_FOR_INNER_FLOORS);
			int numOfSteps = rand.nextInt(Math.min(maze.getDepth(), maze.getWidth()));
			int stepsCounter = 0;
			Cell cell = new Cell(floor, lastVisitedCellInPreviousFloor.y, lastVisitedCellInPreviousFloor.z);
			if (cell != null) {
				maze.setHoleAt(cell.x, cell.y, cell.z);
				while (stepsCounter <= numOfSteps) {
					Direction direction = Direction.values()[rand.nextInt(Direction.values().length)];
					try {
						switch (direction) {
						case LEFT:
							maze.setHoleAt(floor, cell.y - 1, cell.z);
							cell.updateCell(cell.y - 1, cell.z);
							break;
						case RIGHT:
							maze.setHoleAt(floor, cell.y + 1, cell.z);
							cell.updateCell(cell.y + 1, cell.z);
							break;
						case BACKWARD:
							maze.setHoleAt(floor, cell.y, cell.z - 1);
							cell.updateCell(cell.y, cell.z - 1);
							break;
						case FORWARD:
							maze.setHoleAt(floor, cell.y, cell.z + 1);
							cell.updateCell(cell.y, cell.z + 1);
							break;
						}
						stepsCounter++;
					} catch (ArrayIndexOutOfBoundsException e) {
						// if all directions were tried then jump to another available cell
						// do nothing... just try pick up another direction again
					}
				}
			} 

			// go through to the next floor
			lastVisitedCellInPreviousFloor = cell;
		}
		
		maze.setHoleAt(maze.getHeight() - 1, lastVisitedCellInPreviousFloor.y, lastVisitedCellInPreviousFloor.z);

	}

	private Cell findAvailableSpotInFloor(Maze3d maze, int floor) {
		for (int i = 0; i < maze.getDepth(); i++) {
			for (int j = 0; j < maze.getWidth(); j++) {
				if (maze.getValueAt(floor, i, j) == HOLE) {
					return new Cell(floor, i, j);
				}
			}
		}
		return null;
	}

	private void handleFirstAndLastFloor(Cell lastVisitedCell) {
		createFloorWalls(maze, 0, WALL_PRECENTAGE_FOR_OUTER_FLOORS);
		Cell cell = findAvailableSpotInFloor(maze, 0);
		lastVisitedCell.updateCell(cell.x, cell.y, cell.z);
		createFloorWalls(maze, HEIGHT - 1, WALL_PRECENTAGE_FOR_OUTER_FLOORS);
	}

	/**
	 * 
	 * @param maze
	 * @param floor
	 * @param precent
	 *            - values between 0-100
	 */
	private void createFloorWalls(Maze3d maze, int floor, int precent) {
		int numberOfWallsToMake = maze.getDepth() * maze.getWidth() * precent / 100;
		int wallCounter = 0;
		while (wallCounter <= numberOfWallsToMake) {
			int dRand = rand.nextInt(maze.getDepth());
			int wRand = rand.nextInt(maze.getWidth());
			if (maze.getValueAt(floor, dRand, wRand) == HOLE) {// if not already
																// a hole
				wallCounter++;
				maze.setWallAt(floor, dRand, wRand);
			}
		}
	}

	public Maze3d getMaze() {
		return maze;
	}

}