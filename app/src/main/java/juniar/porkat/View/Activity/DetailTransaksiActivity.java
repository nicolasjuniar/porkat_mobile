package juniar.porkat.View.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import juniar.porkat.Manifest;
import juniar.porkat.Model.GetTransaksiModel;
import juniar.porkat.Presenter.DetailTransaksiPresenter;
import juniar.porkat.R;
import juniar.porkat.Utils.PreferenceHelper;
import juniar.porkat.View.Interface.UpdateNotaListener;

import static juniar.porkat.Main.base_url;

/**
 * Created by Nicolas Juniar on 10/12/2017.
 */

public class DetailTransaksiActivity extends AppCompatActivity implements UpdateNotaListener{

    @BindView(R.id.tv_namakatering)
    TextView tv_namakatering;
    @BindView(R.id.tv_alamatpemesanan)
    TextView tv_alamatpemesanan;
    @BindView(R.id.tv_tglpesan)
    TextView tv_tglpesan;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.tv_catatan)
    TextView tv_catatan;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.img_fotonota)
    ImageView img_fotonota;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_tambahnota)
    Button btn_tambahnota;

    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;

    Bundle bundle;
    private GetTransaksiModel detail_transaksi;
    boolean setting;

    public static int COUNT_CAMERA = 100;
    private Uri fileUri;
    private String timeStamp;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "MyQueue_CapturedPhoto";

    PreferenceHelper preferences;
    DetailTransaksiPresenter presenter;

    String encodedImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtransaksi);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitleActionBar("Detail Transaksi");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        presenter=new DetailTransaksiPresenter(this);

        bundle = getIntent().getExtras();
        detail_transaksi =new Gson().fromJson(bundle.getString("detail_transaksi"),GetTransaksiModel.class);

        if(!detail_transaksi.getNota().equalsIgnoreCase(""))
        {
            btn_tambahnota.setText("ganti nota");
            Picasso.with(DetailTransaksiActivity.this).load(base_url+"foto/nota/"+detail_transaksi.getNota()).fit().into(img_fotonota);
            img_fotonota.setVisibility(View.VISIBLE);
        }

        preferences=PreferenceHelper.getInstance(getApplicationContext());

        tv_namakatering.setText(detail_transaksi.getNama_katering());
        tv_alamatpemesanan.setText(detail_transaksi.getAlamat());
        tv_tglpesan.setText(changeDateFormat(detail_transaksi.getTgl_mulai())+" s/d "+changeDateFormat(detail_transaksi.getTgl_selesai()));
        tv_total.setText(convertToIDR(detail_transaksi.getTotal()));
        tv_catatan.setText(detail_transaksi.getCatatan());
        tv_status.setText(detail_transaksi.getStatus());

        btn_tambahnota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    public void setTitleActionBar(String title)
    {
        getSupportActionBar().setTitle(title);
    }

    private String changeDateFormat(String input){
        DateTimeFormatter oldFormat= DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime oldDateTime=oldFormat.parseDateTime(input);
        DateTimeFormatter newFormat=DateTimeFormat.forPattern("d MMM yyyy");
        DateTime newDateTime=DateTime.parse(newFormat.print(oldDateTime),newFormat);
        Locale locale=new Locale("in_ID");
        return newDateTime.toString("d MMM yyyy",locale);
    }

    public String convertToIDR(int harga)
    {
        Locale indonesia=new Locale("id","ID");
        NumberFormat indoFormat=NumberFormat.getCurrencyInstance(indonesia);
        String idr = indoFormat.format(new BigDecimal(String.valueOf(harga)));

        return  idr;
    }

    public void uploadNota()
    {
        generateTimeStamp();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ((BitmapDrawable) img_fotonota.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        presenter.updateNota(detail_transaksi.getId_pesan(),"nota_"+timeStamp+".jpg",encodedImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == COUNT_CAMERA) {

                Bitmap bitmap;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(fileUri.getPath(), options);
//                    BitmapFactory.decodeFile(util.getPath(DetailTransaksiActivity.this, data.getData()), options);
                final int REQUIRED_SIZE = 450;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bitmap =  BitmapFactory.decodeFile(fileUri.getPath(), options);
                img_fotonota.setVisibility(View.VISIBLE);
                img_fotonota.setImageBitmap(bitmap);
                img_fotonota.setScaleType(ImageView.ScaleType.FIT_XY);
                btn_tambahnota.setText("Ganti Nota");
                uploadNota();

            } else if (requestCode == 2) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
//                Cursor cursor = this.getContentResolver().query(selectedImageUri,  projection, null, null, null);
                CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();

                String selectedImagePath = cursor.getString(column_index);

                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 450;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;

                img_fotonota.setVisibility(View.VISIBLE);
                if(bm.getWidth()<width){
                    int heighttoAdd = bm.getHeight()+(width-bm.getWidth());
                    Bitmap newResizedBitmap = getResizedBitmap(bm,width,heighttoAdd);
                    img_fotonota.setImageBitmap(newResizedBitmap);
                }else{
                    img_fotonota.setImageBitmap(bm);
                    img_fotonota.setScaleType(ImageView.ScaleType.FIT_START);
                }
                btn_tambahnota.setText("Ganti Nota");
                uploadNota();
            }
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Ambil lewat kamera", "Pilih lewat penyimpanan", "Batal" };

        AlertDialog.Builder builder = new AlertDialog.Builder(DetailTransaksiActivity.this);
        builder.setTitle("Tambahkan Foto");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Ambil lewat kamera")) {
                    if(ActivityCompat.checkSelfPermission(DetailTransaksiActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        if(ActivityCompat.shouldShowRequestPermissionRationale(DetailTransaksiActivity.this,android.Manifest.permission.CAMERA)){
                            //Show Information about why you need the permission
                            AlertDialog.Builder builder = new AlertDialog.Builder(DetailTransaksiActivity.this);
                            builder.setTitle("Need Multiple Permissions");
                            builder.setMessage("This app needs Camera and Location permissions.");
                            builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    ActivityCompat.requestPermissions(DetailTransaksiActivity.this,new String[]{android.Manifest.permission.CAMERA},PERMISSION_CALLBACK_CONSTANT);
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                        } else if (preferences.getBoolean(android.Manifest.permission.CAMERA,false)) {
                            //Previously Permission Request was cancelled with 'Dont Ask Again',
                            // Redirect to Settings after showing Information about why you need the permission
                            AlertDialog.Builder builder = new AlertDialog.Builder(DetailTransaksiActivity.this);
                            builder.setTitle("Need Multiple Permissions");
                            builder.setMessage("This app needs Camera and Location permissions.");
                            builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    setting = true;
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                                    Toast.makeText(getBaseContext(), "Go to Permissions to Grant  Camera and Location", Toast.LENGTH_LONG).show();
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                        }  else {
                            //just request the permission
                            ActivityCompat.requestPermissions(DetailTransaksiActivity.this,new String[]{android.Manifest.permission.CAMERA},PERMISSION_CALLBACK_CONSTANT);
                        }

                        preferences.putBoolean(android.Manifest.permission.CAMERA,true);
                    } else {
                        //You already have the permission, just go ahead.
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                        startActivityForResult(intent, COUNT_CAMERA);
                    }
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, COUNT_CAMERA);
                } else if (items[item].equals("Pilih lewat penyimpanan")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            2);
                } else if (items[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private File getOutputMediaFile(int type) {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            generateTimeStamp();
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp +"_user3.jpg");
        } else {
            return null;
        }
        MediaScannerConnection.scanFile(this, new String[]{Environment.getExternalStorageDirectory().toString()},
                null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
        return mediaFile;
    }

    private void generateTimeStamp(){ // KALO MW GENERATE WAKTU
        timeStamp= new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    @Override
    public void onUpdateNota(boolean error, @org.jetbrains.annotations.Nullable String message, @org.jetbrains.annotations.Nullable Throwable t) {
        if(!error)
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            tv_status.setText("konfirmasi nota");
        }
    }
}
