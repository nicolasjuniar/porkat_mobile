package juniar.porkat.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import juniar.porkat.Model.KateringModel;
import juniar.porkat.R;
import juniar.porkat.View.Interface.ListKateringView;

/**
 * Created by Nicolas Juniar on 30/10/2017.
 */

public class KateringByDistanceFragment extends Fragment implements ListKateringView {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_kateringbydistance, container, false);

        return view;
    }

    @Override
    public void onGetListKateringResponse(boolean error, ArrayList<KateringModel> ListKatering, Throwable t) {

    }
}