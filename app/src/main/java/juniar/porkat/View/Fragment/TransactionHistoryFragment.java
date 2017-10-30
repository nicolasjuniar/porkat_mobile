package juniar.porkat.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import juniar.porkat.R;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public class TransactionHistoryFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_transactionhistory, container, false);

        return view;
    }
}
