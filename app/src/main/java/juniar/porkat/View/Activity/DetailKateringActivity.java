package juniar.porkat.View.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Model.KateringModel;
import juniar.porkat.R;

import static juniar.porkat.Main.base_url;

public class DetailKateringActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbar_layout;
    private Bundle bundle;
    private KateringModel katering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailkatering);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar_layout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        toolbar_layout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        bundle = getIntent().getExtras();
        katering=new Gson().fromJson(bundle.getString("detail_katering"),KateringModel.class);
        setTitleActionBar(katering.getNama_katering());
        String url=base_url+"foto/katering/"+katering.getFoto();


        Picasso.with(this).load(url).into(new Target(){

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                toolbar_layout.setBackground(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
            }
        });
    }

    public void setTitleActionBar(String title)
    {
        getSupportActionBar().setTitle(title);
    }
}
