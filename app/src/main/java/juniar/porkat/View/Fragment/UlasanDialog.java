package juniar.porkat.View.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.DeleteUlasanResponse;
import juniar.porkat.Model.InsertUlasanRequest;
import juniar.porkat.Model.InsertUlasanResponse;
import juniar.porkat.Model.UlasanModel;
import juniar.porkat.Model.UpdateUlasanRequest;
import juniar.porkat.Model.UpdateUlasanResponse;
import juniar.porkat.Presenter.UlasanPresenter;
import juniar.porkat.R;
import juniar.porkat.View.Interface.RefreshUlasanListener;
import juniar.porkat.View.Interface.UlasanListener;

/**
 * Created by Nicolas Juniar on 15/11/2017.
 */

public class UlasanDialog extends DialogFragment implements UlasanListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rb_ulasan)
    RatingBar rb_ulasan;
    @BindView(R.id.et_ulasan)
    EditText et_ulasan;
    @BindView(R.id.img_btn_done)
    ImageButton img_btn_done;
    @BindView(R.id.img_btn_cancel)
    ImageButton img_btn_cancel;
    @BindView(R.id.img_btn_delete)
    ImageButton img_btn_delete;

    View view;
    Bundle bundle;
    UlasanPresenter presenter;
    String dialogtipe;
    int id_pelanggan, id_katering;
    UlasanModel ulasan;
    ProgressDialog progressDialog;
    RefreshUlasanListener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_ulasan, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        ButterKnife.bind(this, view);

        presenter = new UlasanPresenter(this);
        listener = (RefreshUlasanListener) getTargetFragment();

        bundle = getArguments();
        dialogtipe = bundle.getString("tipe");
        id_katering = bundle.getInt("id_katering");
        id_pelanggan = bundle.getInt("id_pelanggan");
        ulasan = new Gson().fromJson(bundle.getString("ulasan"), UlasanModel.class);

        rb_ulasan.setRating(1.0f);
        rb_ulasan.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (v < 1.0f)
                    ratingBar.setRating(1.0f);
            }
        });

        if (dialogtipe.equalsIgnoreCase("add")) {
            tv_title.setText("Tambahkan Ulasan");
            img_btn_delete.setVisibility(View.GONE);
        }
        if (dialogtipe.equalsIgnoreCase("edit")) {
            tv_title.setText("Ubah Ulasan");
            rb_ulasan.setRating(ulasan.getRating());
            et_ulasan.setText(ulasan.getUlasan());
            img_btn_delete.setVisibility(View.VISIBLE);
        }

        img_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        img_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Menghapus Ulasan")
                        .setMessage("Apa anda yakin ingin menghapus ulasan?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (progressDialog == null) {
                                    progressDialog = new ProgressDialog(getActivity());
                                    progressDialog.setCancelable(false);
                                    progressDialog.setMessage("Menghapus ulasan...");
                                }
                                progressDialog.show();
                                presenter.deleteUlasan(ulasan.getId_ulasan());
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        img_btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearError();
                if (checkInput()) {
                    if (progressDialog == null) {
                        progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setCancelable(false);
                    }
                    if (dialogtipe.equalsIgnoreCase("add")) {
                        progressDialog.setMessage("Menambahkan ulasan...");
                        progressDialog.show();
                        InsertUlasanRequest requestBody = new InsertUlasanRequest(et_ulasan.getText().toString(), rb_ulasan.getRating(), id_pelanggan, id_katering);
                        presenter.insertUlasan(requestBody);
                    }
                    if (dialogtipe.equalsIgnoreCase("edit")) {
                        progressDialog.setMessage("Mengubah ulasan...");
                        progressDialog.show();
                        UpdateUlasanRequest requestBody = new UpdateUlasanRequest(ulasan.getId_ulasan(), et_ulasan.getText().toString(), rb_ulasan.getRating());
                        presenter.updateUlasan(requestBody);
                    }
                }
            }
        });

        return view;
    }

    public boolean checkInput() {
        boolean cek = true;
        if (et_ulasan.getText().toString().isEmpty()) {
            et_ulasan.setError("Ulasan tidak boleh kosong");
            cek = false;
        }

        return cek;
    }

    public void clearError() {
        et_ulasan.setError(null);
    }

    @Override
    public void onInsertUlasanResponse(boolean error, InsertUlasanResponse response, Throwable t) {
        if (!error) {
            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
            listener.onInsertedUlasan(response);
            if ((progressDialog != null) && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            dismiss();
        } else {
            if ((progressDialog != null) && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdateUlasanResponse(boolean error, UpdateUlasanResponse response, Throwable t) {
        if (!error) {
            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
            listener.onUpdatedUlasan(response);
            if ((progressDialog != null) && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            dismiss();
        } else {
            if ((progressDialog != null) && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void clearDialog() {
        rb_ulasan.setRating(1.0f);
        et_ulasan.setText("");
    }

    @Override
    public void onDeleteUlasanResponse(boolean error, DeleteUlasanResponse response, Throwable t) {
        if (!error) {
            clearDialog();
            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
            listener.onDeletedUlasan();
            if ((progressDialog != null) && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            dismiss();
        } else {
            if ((progressDialog != null) && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
