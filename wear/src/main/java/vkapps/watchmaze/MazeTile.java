package vkapps.watchmaze;

/**
 * A single tile of the maze
 * Created by Vivek on 2014-07-11.
 */
public class MazeTile {
	public int row, col;
	public int resourceId;
	public MazeTile top, right, bottom, left;

	private static MazeTile[] list = {
		new MazeTile(R.drawable.maze_title),         //00
		new MazeTile(R.drawable.centre),            //01
		new MazeTile(R.drawable.top),           //02
		new MazeTile(R.drawable.right),         //03
		new MazeTile(R.drawable.bottom),        //04
		new MazeTile(R.drawable.left),          //05
		new MazeTile(R.drawable.top_right),    //06
		new MazeTile(R.drawable.top_bottom),         //07
		new MazeTile(R.drawable.top_left),     //08
		new MazeTile(R.drawable.right_bottom), //09
		new MazeTile(R.drawable.right_left),         //10
		new MazeTile(R.drawable.bottom_left),  //11
		new MazeTile(R.drawable.top_right_left),          //12
		new MazeTile(R.drawable.top_right_bottom),        //13
		new MazeTile(R.drawable.right_bottom_left),       //14
		new MazeTile(R.drawable.top_bottom_left),         //15
		new MazeTile(R.drawable.maze_prize),          //16
		new MazeTile(R.drawable.no_path)               //17
	};

	public static MazeTile all(int i) {
		return new MazeTile(list[i].resourceId);
	}

	public void setRowCol(int r, int c) {
		row = r;
		col = c;
	}

	private MazeTile(int rId) {
		resourceId = rId;
		top = right = bottom = left = null;
	}
}
