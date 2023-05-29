package com.example.ngm.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.ngm.FanItem;
import com.example.ngm.PublicData;

import java.util.ArrayList;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<ArrayList<FanItem>> mItem = Transformations.map(mIndex, new Function<Integer, ArrayList<FanItem>>() {
        @Override
        public ArrayList<FanItem> apply(Integer input) {
            if(input == 1){
//                PublicData.fanItems.add(new FanItem(null,input+""));
                return PublicData.fanItems;
            }
            else {
//                PublicData.followingItems.add(new FanItem(null,input+""));a
                return PublicData.followingItems;
            }

        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);

    }

    public LiveData<ArrayList<FanItem>> getItem() {
        return mItem;
    }
}