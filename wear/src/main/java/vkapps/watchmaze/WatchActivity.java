package vkapps.watchmaze;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;

public class WatchActivity extends Activity
	implements GridViewPager.OnPageChangeListener {

	private GridViewPager pager;
	private MazeAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_watch);
		pager = (GridViewPager) findViewById(R.id.pager);

		MazeHolder.initialize();
		adapter = new MazeAdapter(getFragmentManager());
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(this);
	//	pager.setCurrentItem(adapter.currRow, adapter.currCol);
	}

	@Override
	public void onPageSelected(int row, int column) {
		MazeTile tile = MazeHolder.getTile(adapter.currRow, adapter.currCol);
		if ((row < adapter.currRow && tile.top == null)
			|| (column > adapter.currCol && tile.right == null)
			|| (row > adapter.currRow && tile.bottom == null)
			|| (column < adapter.currCol && tile.left == null))
			pager.setCurrentItem(adapter.currRow, adapter.currCol);
		else {
			adapter.currRow = row;
			adapter.currCol = column;
		}
	}

	@Override
	public void onPageScrollStateChanged(int i) {

	}

	@Override
	public void onPageScrolled(int i, int i2, float v, float v2, int i3, int i4) {

	}
}
