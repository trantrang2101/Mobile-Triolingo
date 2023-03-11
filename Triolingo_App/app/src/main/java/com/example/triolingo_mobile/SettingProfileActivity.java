package com.example.triolingo_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingProfileActivity extends AppCompatActivity{
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);
        findViewById(R.id.btnClose).setOnClickListener(this::onQuit);
        findViewById(R.id.btnSave).setOnClickListener(this::onSave);
        imageView=findViewById(R.id.imageview_account_profile);
        findViewById(R.id.floatingActionButton).setOnClickListener(this::onEditImage);
    }
    public void onEditImage(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingProfileActivity.this);
        builder.setTitle("Chọn ảnh từ");
        builder.setItems(new CharSequence[]{"Chụp ảnh", "Chọn ảnh"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                        break;
                    case 1:
                        Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent2, REQUEST_IMAGE_PICK);
                        break;
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(bitmap);
                    break;
                case REQUEST_IMAGE_PICK:
                    Uri imageUri = data.getData();
                    imageView.setImageURI(imageUri);
                    break;
            }
        }
    }
    public void onQuit(View view) {
        Intent account = new  Intent(SettingProfileActivity.this, AccountActivity.class);
        startActivity(account);
    }
    public void onSave(View view) {
        onQuit(view);
    }
}