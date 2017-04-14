package com.example.a34camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import id.zelory.compressor.Compressor;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CAPTURE_PIC = 1234;
    private static final int REQ_CAPTURE_VID = 5678;
    private File file_Pic, file_CompressedPic, file_Vid, file_CompressedVid;
    private Uri fileUri_Pic, fileUri_Vid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.BtnCapture).setOnClickListener(this::captureImage);
        findViewById(R.id.BtnVideo).setOnClickListener(this::captureVideo);
    }


    private void captureImage(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file_Pic = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MyTest.jpg");
        fileUri_Pic = Uri.fromFile(file_Pic);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri_Pic);
        startActivityForResult(intent, REQ_CAPTURE_PIC);

    }

    private void captureVideo(View view) {

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        file_Vid = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MyTest.mp4");
        fileUri_Vid = Uri.fromFile(file_Vid);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri_Vid);
        startActivityForResult(intent, REQ_CAPTURE_VID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      /*  if (requestCode == REQ_CAPTURE_PIC) {

            ((ImageView) findViewById(R.id.ImageView)).setImageURI(fileUri_Pic);
        } else if (requestCode == REQ_CAPTURE_VID) {

            ((VideoView) findViewById(R.id.VideoView)).setVideoURI(fileUri_Vid);
        } */

        if (requestCode == REQ_CAPTURE_PIC) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Image Captured Successfully", Toast.LENGTH_SHORT).show();

                ((ImageView) findViewById(R.id.ImageView)).setImageURI(fileUri_Pic);

                Compressor.getDefault(this)
                        .compressToFileAsObservable(file_Pic)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(this::compressImage)
                        .subscribe();
            }
        }

        if (requestCode == REQ_CAPTURE_VID) {
            if (resultCode == RESULT_OK) {
                findViewById(R.id.ImageView).setVisibility(View.GONE);
                findViewById(R.id.VideoView).setVisibility(View.VISIBLE);
                ((VideoView) findViewById(R.id.VideoView)).setVideoURI(Uri.fromFile(file_Vid));
                ((VideoView) findViewById(R.id.VideoView)).start();
            }
        }
    }

    private void compressImage(File file) {
    }

    private void compressImage() {

        file_CompressedPic = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                , "MyTest-comp.jpg");

        Compressor.getDefault(this)
                .compressToFileAsObservable(file_Pic)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File fileComp) {
                        //compressedImage = file;

                        try {
                            FileInputStream fis = new FileInputStream(fileComp);
                            byte[] bytes = new byte[(int) fileComp.length()];
                            fis.read(bytes);
                            fis.close();


                            File fileOut = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "comp.jpg");
                            FileOutputStream fos = new FileOutputStream(fileOut);
                            fos.write(bytes);
                            fos.close();
                        }
                     /*   catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }  */ catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //showError(throwable.getMessage());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
