package com.poly.assm.fragment.nguoidung;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

import com.poly.assm.R;
import com.poly.assm.loginfrm;

public class dangxuatFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dangxuat, container, false);
        startActivity(new Intent(getActivity(), loginfrm.class));
        return root;
    }
}
