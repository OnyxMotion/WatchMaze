package vkapps.watchmaze;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;

public class WatchActivity extends Activity {

	private GridViewPager pager;
	private MazeAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_watch);
		pager = (GridViewPager) findViewById(R.id.pager);
		pager.setCurrentItem(1,1);
		adapter = new MazeAdapter(getFragmentManager(), Difficulty.TEST,
			new MazeAdapter.onPageChangeCallable() {
				@Override
				public void setCurrent(int row, int column) {
					pager.setCurrentItem(row, column);
				}
			});
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(adapter);
	}

}
