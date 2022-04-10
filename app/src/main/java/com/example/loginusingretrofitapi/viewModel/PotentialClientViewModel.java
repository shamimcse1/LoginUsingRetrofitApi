package com.example.loginusingretrofitapi.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.model.PotentialClientPost;
import com.example.loginusingretrofitapi.model.district.District;
import com.example.loginusingretrofitapi.model.ResponseModel;
import com.example.loginusingretrofitapi.model.up_zila.Upozila;
import com.example.loginusingretrofitapi.repository.PotentialClientRepository;

import java.io.File;

public class PotentialClientViewModel extends ViewModel {

    PotentialClientRepository potentialClientRepository = new PotentialClientRepository();

    public LiveData<DataResource<ResponseModel>>  submitResponse = potentialClientRepository.submitResponse;
    public  LiveData<DataResource<District>> districtResponse = potentialClientRepository.districtLiveData;
    public  LiveData<DataResource<Upozila>> upozilaResponse =potentialClientRepository.upozilaLiveData;

    public void  getDistrictData(){
        potentialClientRepository.getDistrict();
    }

    public void getUpozila(String id){
        potentialClientRepository.getUpozila(id);
    }

    public void addPotentialClient(PotentialClientPost potentialClientPost , File imageFile){
        potentialClientRepository.addPotentialClient(potentialClientPost,imageFile);
    }
}
