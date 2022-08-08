package com.acharyamukti.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TransViewModel extends ViewModel {


    private final MutableLiveData<String> mText;

    public TransViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is fragments.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
