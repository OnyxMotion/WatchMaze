package vkapps.watchmaze;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.ImageReference;
import android.util.Log;

/**
 * Created by Vivek on 2014-07-11.
 */
public class MazeAdapter extends FragmentGridPagerAdapter
	implements GridViewPager.OnPageChangeListener {

	public interface onPageChangeCallable {
		public void setCurrent(int row, int column);
	}

	public static String TAG = "MazeAdapter";
	private MazeTile[][] maze;
	public MazeTile currTile;
	public int currRow, currColumn;
	private onPageChangeCallable onPage;




	public MazeAdapter(FragmentManager fm, Difficulty d, onPageChangeCallable toCall) {
		super(fm);

		onPage = toCall;

		// Select a maze to do
		switch(d) {
			case TEST: case VERY_EASY: case EASY:
			case MEDIUM: case HARD: case VERY_HARD: break;
		}

		MazeTile[][] m = {
			{MazeTile.all(15), MazeTile.all(7), MazeTile.all(7), MazeTile.all(6), MazeTile.all(16)},
			{MazeTile.all(8), MazeTile.all(7), MazeTile.all(7), MazeTile.all(3), MazeTile.all(10)},
			{MazeTile.all(10), MazeTile.all(8), MazeTile.all(6), MazeTile.all(5), MazeTile.all(9)},
			{MazeTile.all(14), MazeTile.all(14), MazeTile.all(11), MazeTile.all(4), MazeTile.all(13)}

		};

		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 5; c++) {
				boolean top = false, right = false, left = false, bottom = false;
				MazeTile tile = m[r][c];
				tile.row = r;
				tile.col = c;
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
				if (top) tile.top = m[r - 1][c];
				else tile.top = null;
				if (right) tile.right = m[r][c + 1];
				else tile.right = null;
				if (bottom) tile.bottom = m[r + 1][c];
				else tile.bottom = null;
				if (left) tile.left = m[r][c - 1];
				else tile.left = null;
			}
		}

		currTile = m[0][0];
		currRow = currTile.top != null ? 1 : 0;
		currColumn = currTile.left != null ? 1 : 0;

		maze = m;
		onPage.setCurrent(currRow, currColumn);


	}

	@Override
	public int getRowCount() {
		int i = 1;
		if (currTile.top != null) ++i;
		if (currTile.bottom != null) ++i;
		return i;
	}

	@Override
	public int getColumnCount(int row) {
		int i = 1;
		if (currTile.left != null) ++i;
		if (currTile.right != null) ++i;
		return i;
	}

	@Override
	public int getCurrentColumnForRow(int row, int currentColumn) {
		return currentColumn;
	}

	@Override
	public Fragment getFragment(int row, int column) {
		MazeTile tile = getTile(row, column);
		CardFragment fragment
			= CardFragment.create("" + tile.row, "" + tile.col);
		return fragment;
	}

	@Override
	public ImageReference getBackground(int row, int column) {
		return ImageReference.forDrawable(getTile(row, column).resourceId);
	}

	@Override
	public void onPageSelected(int row, int column) {
		currTile = getTile(row, column);
		currRow = currTile.top != null ? 1 : 0;
		currColumn = currTile.left != null ? 1 : 0;
		Log.d(TAG,"[RowCount,ColCount] [currRow,currCol]:"
			+ "[" + getRowCount() + "," + getColumnCount(0) + "] ["
			+ currRow + "," + currColumn + "]");
		notifyDataSetChanged();
		onPage.setCurrent(currRow, currColumn);
	}

	@Override
	public void onPageScrolled(int row, int column, float rowOffset,
		float columnOffset, int rowOffsetPixels, int columnOffsetPixels) {

	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	public MazeTile getTile(int row, int col) {
		int r = currTile.row + row - currRow, c = currTile.col + col - currColumn;
		return maze[currTile.row + row - currRow][currTile.col + col - currColumn];
	}
}
