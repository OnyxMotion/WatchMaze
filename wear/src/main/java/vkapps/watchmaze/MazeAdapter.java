package vkapps.watchmaze;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.wearable.view.FragmentGridPagerAdapter;

/**
 * Adapts the maze to the GridViewPager UI
 * Created by Vivek on 2014-07-11.
 */
public class MazeAdapter extends FragmentGridPagerAdapter {

	public int currRow = 0, currCol = 0;

	public MazeAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getRowCount() {
		return MazeHolder.rowCount();
	}

	@Override
	public int getColumnCount(int row) {
		return MazeHolder.colCount();
	}

	@Override
	public int getCurrentColumnForRow(int row, int currentColumn) {
		return currentColumn;
	}

	@Override
	public Fragment getFragment(int row, int col) {
		MazeFragment fragment = new MazeFragment();
		fragment.setTile(MazeHolder.getTile(row, col));
		return fragment;
	}
}
