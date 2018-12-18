package com.beans.coder.recyclerpdfcreater;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private File pdfFile;
    private List<Item> items;
    private int[] myImageList = new int[]{R.drawable.benz, R.drawable.bike,
            R.drawable.car,R.drawable.carrera
            ,R.drawable.ferrari,R.drawable.harly,
            R.drawable.lamborghini,R.drawable.silver};
    private String[] myImageNameList = new String[]{"Benz", "Bike",
            "Car","Carrera"
            ,"Ferrari","Harly",
            "Lamborghini","Silver"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        adapter = new RecyclerViewAdapter(this,items);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        for(int i=0; i<8; i++){
            Item item = new Item();
            item.setName(myImageNameList[i]);
            item.setImageId(myImageList[i]);
            items.add(item);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onCreatePDFClick(int position) {
        Intent intent = new Intent(this,PDFCreatorActivity.class);
        startActivity(intent);
        finish();
    }
}
