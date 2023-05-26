package com.example.androidpermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidpermissions.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;
     static final int CAMERA_PERMISSION_CODE = 100;
     static final int LOCATION_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
            }
        });

        binding.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION_PERMISSION_CODE);
            }
        });

    }
    public void checkPermission(String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(MainActivity.this,permission)== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission},requestCode);
        }else {
            Toast.makeText(MainActivity.this,"İzin zaten verilmiş", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Kameraya izin verildi", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Kameraya izin verilemedi", Toast.LENGTH_SHORT).show();
            }
        }
            else if (requestCode == LOCATION_PERMISSION_CODE) {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Konum izni verildi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Konum izni verilmedi", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
