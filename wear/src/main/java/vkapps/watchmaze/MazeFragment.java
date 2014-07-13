package vkapps.watchmaze;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The UI of a single maze tile
 * Created by Vivek on 2014-07-11.
 */
public class MazeFragment extends Fragment {

	private MazeTile tile;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_view, container, false);
		((ImageView) view.findViewById(R.id.image))
			.setImageResource(tile.resourceId);
		((TextView) view.findViewById(R.id.text))
			.setText("" + tile.row + " " + tile.col);
		return view;
	}

	public void setTile(MazeTile t) {
		tile = t;
	}
}
