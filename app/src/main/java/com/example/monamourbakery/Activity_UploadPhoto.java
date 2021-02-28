package com.example.monamourbakery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.google.android.material.button.MaterialButton;

public class Activity_UploadPhoto extends AppCompatActivity {
    private static final int IMAGE_REQUEST = 1;
    private MaterialButton photo_BTN_save;
    private MaterialButton photo_BTN_choose;
    private MaterialButton photo_BTN_back;
    private String currentProductString;
    private Product product;

    private boolean isSuccess= false;
    private Uri downloadUri;
    private DatabaseReference myRef;
    private StorageReference storageReference;
    private Uri imageUri;
    private StorageTask uploadTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__upload_photo);
        currentProductString = getIntent().getStringExtra("currentProduct");
        findViews();
        initViews();

    }

    private void findViews() {
        photo_BTN_save=(MaterialButton)findViewById(R.id.photo_BTN_save);
        photo_BTN_back=findViewById(R.id.photo_BTN_back);
        photo_BTN_choose = findViewById(R.id.photo_BTN_choose);
        //Profile Image ref in storage;
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        myRef = FirebaseDatabase.getInstance().getReference("Products").child(currentProductString);
    }

    private void initViews() {
        readProductFromDB();
        photo_BTN_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSuccess){
                    final ProgressDialog progressDialog = new ProgressDialog(Activity_UploadPhoto.this);
                    String mUri = downloadUri.toString();
                    Log.d("pttt", mUri);

                    myRef = FirebaseDatabase.getInstance().getReference("Products").child(currentProductString);
                    product.setPastry_img(mUri);
                    myRef.setValue(product);

                    Toast.makeText(Activity_UploadPhoto.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        photo_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_UploadPhoto.this, Activity_Main_Manager.class);
                startActivity(intent);
                finish();
            }
        });
        photo_BTN_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runTimePermission();
            }
        });
    }

    private void readProductFromDB(){
        myRef = FirebaseDatabase.getInstance().getReference("Products").child(currentProductString);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                product = dataSnapshot.getValue(Product.class); //load user from DB
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void selectImage() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        this.startActivityForResult(chooserIntent, IMAGE_REQUEST);
    }

    private String getFileExtention(Uri uri){
        ContentResolver contentResolver = this.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading");
        progressDialog.show();

        if(imageUri != null){
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtention(imageUri));
            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful())
                        throw task.getException();

                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    Log.d("DDMComplete",imageUri.getPath().toString().trim());
                    if(task.isSuccessful()){
                        isSuccess = true;
                        downloadUri = task.getResult();
                        String mUri = downloadUri.toString();
                        Toast.makeText(Activity_UploadPhoto.this, "Photo Chose!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }else {
                        Toast.makeText(Activity_UploadPhoto.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Activity_UploadPhoto.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }else{
            Toast.makeText(Activity_UploadPhoto.this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Log.d("DDM",imageUri.getPath().toString().trim());
            if (uploadTask != null && uploadTask.isInProgress())
                Toast.makeText(Activity_UploadPhoto.this, "Upload In Progress..", Toast.LENGTH_SHORT).show();
            else{
                Log.d("DDMElse",imageUri.getPath().toString().trim());
                uploadImage();

            }

        }
    }

    private void runTimePermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                selectImage();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }


}

