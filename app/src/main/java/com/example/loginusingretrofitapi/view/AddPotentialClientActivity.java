package com.example.loginusingretrofitapi.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.loginusingretrofitapi.R;
import com.example.loginusingretrofitapi.databinding.ActivityAddPotentialClientBinding;
import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.model.PotentialClientPost;
import com.example.loginusingretrofitapi.model.district.District;
import com.example.loginusingretrofitapi.model.ResponseModel;
import com.example.loginusingretrofitapi.model.up_zila.Upozila;
import com.example.loginusingretrofitapi.repository.PotentialClientRepository;
import com.example.loginusingretrofitapi.util.AppUtils;
import com.example.loginusingretrofitapi.viewModel.PotentialClientViewModel;

import java.io.File;
import java.io.IOException;

public class AddPotentialClientActivity extends AppCompatActivity {

    private ActivityAddPotentialClientBinding binding;
    PotentialClientViewModel potentialClientViewModel;
    private static final int SELECT_REQUEST_CODE = 1;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public ArrayAdapter<String> districtAdapter, upozilaAdapter;
    private String[] districts = new String[0];
    private String[] districtsId = new String[0];
    private String[] upozila = new String[0];
    private String[] upozilaId = new String[0];

    private String districtIdPost = "";
    private String upozilaIdPost = "";
    private File imageFile = new File("");
    Uri ImageUri;
    private MaterialDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_potential_client);

        imageFile = null;
        potentialClientViewModel = new ViewModelProvider(this).get(PotentialClientViewModel.class);
        potentialClientViewModel.getDistrictData();

        getObserverData();
        initListener();
    }

    private void initListener() {

        binding.tvZila.setOnItemClickListener((adapterView, view, position, l) -> {
            upozila = new String[0];
            upozilaId = new String[0];
            upozilaAdapter = new ArrayAdapter<>(this, R.layout.menu_item_row, upozila);
            binding.tvUpZilla.setAdapter(upozilaAdapter);
            binding.tvUpZilla.setText("");
            potentialClientViewModel.getUpozila(districtsId[position]);
            districtIdPost = districtsId[position];
            upozilaIdPost = "";

        });
        binding.tvUpZilla.setOnItemClickListener((adapterView, view, position, l) -> {
            upozilaIdPost = upozilaId[position];

        });

        binding.submit.setOnClickListener(v -> {
            PotentialClientPost post = new PotentialClientPost();
            post.setName(binding.etName.getText().toString());
            post.setAddress(binding.etAddress.getText().toString());
            post.setNote(binding.etNote.getText().toString());
            post.setProjectName(binding.etProject.getText().toString());
            post.setPhoneNo(binding.etPhone.getText().toString());
            post.setDistrictId(districtIdPost);
            post.setUpazillaId(upozilaIdPost);
            potentialClientViewModel.addPotentialClient(post, imageFile);
            // Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();

        });
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_REQUEST_CODE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void getObserverData() {

        potentialClientViewModel.districtResponse.observe(this, new Observer<DataResource<District>>() {
            @Override
            public void onChanged(DataResource<District> dataResource) {
                switch (dataResource.getStatus()) {
                    case SUCCESS:
                        districts = new String[dataResource.getActualData().getData().size()];
                        districtsId = new String[dataResource.getActualData().getData().size()];
                        for (int i = 0; i < dataResource.getActualData().getData().size(); i++) {
                            districts[i] = dataResource.getActualData().getData().get(i).getDistrictNameBng();
                            districtsId[i] = String.valueOf(dataResource.getActualData().getData().get(i).getId());
                        }
                        districtAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_item_row, districts);
                        binding.tvZila.setAdapter(districtAdapter);
                        binding.tvZila.isPopupShowing();
                        binding.refresh.setRefreshing(false);
                        binding.tvZila.setOnClickListener(v -> {
                            // AppUtils.hideSoftInput(getParent());
                            binding.tvZila.showDropDown();
                        });
                        break;
                    case ERROR:
                        binding.refresh.setRefreshing(false);
                        Log.d("Error", dataResource.getMessage());
                        AppUtils.message(binding.getRoot(), dataResource.getMessage(), Color.WHITE, Color.RED);
                        break;
                    case LOADING:
                        binding.refresh.setRefreshing(true);
                        break;
                }
            }
        });

        potentialClientViewModel.upozilaResponse.observe(this, new Observer<DataResource<Upozila>>() {
            @Override
            public void onChanged(DataResource<Upozila> dataResource) {
                switch (dataResource.getStatus()) {
                    case SUCCESS:
                        upozila = new String[dataResource.getActualData().getData().size()];
                        upozilaId = new String[dataResource.getActualData().getData().size()];
                        for (int i = 0; i < dataResource.getActualData().getData().size(); i++) {
                            upozila[i] = dataResource.getActualData().getData().get(i).getUpazilaNameBng();
                            upozilaId[i] = String.valueOf(dataResource.getActualData().getData().get(i).getId());
                        }
                        upozilaAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.menu_item_row, upozila);
                        binding.tvUpZilla.setAdapter(upozilaAdapter);
                        binding.tvUpZilla.isPopupShowing();
                        binding.refresh.setRefreshing(false);
                        binding.tvUpZilla.setOnClickListener(v -> {
                            // AppUtils.hideSoftInput(getParent());
                            binding.tvUpZilla.showDropDown();
                        });
                        break;
                    case ERROR:
                        Log.d("Error", dataResource.getMessage());
                        binding.refresh.setRefreshing(false);
                        AppUtils.message(binding.getRoot(), dataResource.getMessage(), Color.WHITE, Color.RED);
                        break;
                    case LOADING:
                        binding.refresh.setRefreshing(true);
                        break;
                }
            }
        });

        potentialClientViewModel.submitResponse.observe(this, new Observer<DataResource<ResponseModel>>() {
            @Override
            public void onChanged(DataResource<ResponseModel> responseModelDataResource) {
                switch (responseModelDataResource.getStatus()) {
                    case SUCCESS:
                        //loadingDialog.dismiss();
//                    AppUtils.dialog(getApplicationContext(), getString(R.string.success), getString(R.string.created), false)
//                            .positiveText(R.string.back_bn)
//                            .positiveColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
//                            .onPositive((dialog, which) -> {
//                                dialog.dismiss();
//                                finish();
//                            })
//                            .show();
                        AppUtils.message(binding.getRoot(), "Data save successfully", Color.WHITE, R.color.colorPrimaryDark);
                        break;
                    case LOADING:
                        loadingDialog = AppUtils.showProgressDialog(getApplicationContext(), "Loading", "Please Wait!");
                        // loadingDialog.show();
                        break;
                    case ERROR:
                        Log.d("Error", responseModelDataResource.getMessage());
                        // loadingDialog.dismiss();
                        AppUtils.message(binding.getRoot(), responseModelDataResource.getMessage(), Color.WHITE, Color.RED);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            try {
                ImageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ImageUri);
                binding.imageView.setImageBitmap(bitmap);
                createDirectoryAndSaveFile(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave) {
        File file = AppUtils.imageToFile(imageToSave);

        if (file != null && !file.getAbsolutePath().equals("")) {
            imageFile = file;
        } else {
            AppUtils.message(binding.getRoot(), "Image resource not found!", Color.WHITE, Color.RED);
        }
    }

    public boolean CheckPermission() {
        if (ContextCompat.checkSelfPermission(AddPotentialClientActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(AddPotentialClientActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(AddPotentialClientActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(AddPotentialClientActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(AddPotentialClientActivity.this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(AddPotentialClientActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(AddPotentialClientActivity.this)
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(AddPotentialClientActivity.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_LOCATION);


                                startActivity(new Intent(AddPotentialClientActivity
                                        .this, MainActivity.class));
                                AddPotentialClientActivity.this.overridePendingTransition(0, 0);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(AddPotentialClientActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;

        }

    }
}