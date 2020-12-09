package com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PdfUserViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PdfUserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is pdf fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}