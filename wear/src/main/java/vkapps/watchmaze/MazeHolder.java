package vkapps.watchmaze;

/**
 * Creates and holds the maze
 * Created by Vivek on 2014-07-11.
 */
public class MazeHolder {

	public static MazeTile[][] maze;

	public static void initialize() {
		maze = new MazeTile[][]{
			{MazeTile.all(15), MazeTile.all(7), MazeTile.all(7), MazeTile.all(6), MazeTile.all(16)},
			{MazeTile.all(8), MazeTile.all(7), MazeTile.all(7), MazeTile.all(3), MazeTile.all(10)},
			{MazeTile.all(10), MazeTile.all(8), MazeTile.all(6), MazeTile.all(5), MazeTile.all(9)},
			{MazeTile.all(14), MazeTile.all(14), MazeTile.all(11), MazeTile.all(4), MazeTile.all(13)}

		};

		for (int r = 0; r < rowCount(); r++) {
			for (int c = 0; c < colCount(); c++) {
				boolean top = false, right = false, left = false, bottom = false;
				maze[r][c].setRowCol(r, c);
				MazeTile tile = maze[r][c];
				switch(tile.resourceId) {
					case R.drawable.maze_title:
						break; // I am not sure yet
					case R.drawable.centre:
						top = right = bottom = left = true; break;
					case R.drawable.top:
						right = bottom = left = true; break;
					case R.drawable.right:
						top = bottom = left = true; break;
					case R.drawable.bottom:
						top = right = left = true; break;
					case R.drawable.left:
						top = right = bottom = true; break;
					case R.drawable.top_right:
						bottom = left = true; break;
					case R.drawable.top_bottom:
						right = left = true; break;
					case R.drawable.top_left:
						right = bottom = true; break;
					case R.drawable.right_bottom:
						top = left = true; break;
					case R.drawable.right_left:
						top = bottom = true; break;
					case R.drawable.bottom_left:
						top = right = true; break;
					case R.drawable.top_right_left:
						bottom = true; break;
					case R.drawable.top_right_bottom:
						left = true; break;
					case R.drawable.right_bottom_left:
						top = true; break;
					case R.drawable.top_bottom_left:
						right = true; break;
					case R.drawable.maze_prize:
						break;
					default:
						break;
				}
				if (top) tile.top = maze[r - 1][c];
				if (right) tile.right = maze[r][c + 1];
				if (bottom) tile.bottom = maze[r + 1][c];
				if (left) tile.left = maze[r][c - 1];
			}
		}
	}

	public static MazeTile getTile(int row, int col) {
		return maze[row][col];
	}

	public static int rowCount() {
		return maze.length;
	}

	public static int colCount() {
		return maze[0].length;
	}
}
