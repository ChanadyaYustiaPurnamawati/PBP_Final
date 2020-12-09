package com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PdfMotorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PdfMotorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is pdf fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}