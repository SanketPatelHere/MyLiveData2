package com.example.mylivedata2;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylivedata2.databinding.ActivityDataBindingBinding;

public class DataBinding extends AppCompatActivity {
    ActivityDataBindingBinding binding;
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        //PlainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        binding.setName("Sanket");
        binding.setLastName("Patel");

        //binding.plainName = "Sanket";  //not right
        //binding.plainName.setText("New name");  //not working
        //binding.plainName.setText(new MutableLiveData());  //not working
        MyPresenter myPresenter = new MyPresenter();
        Person p = new Person("abc","patel","playing");
        binding.setPerson(p);

        //for click event
        binding.setHandlers(myPresenter);




        //using livedata
        PersonViewModel pvModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        binding.setViewModel(pvModel);  //User, patel, playing

        binding.setLifecycleOwner(this);  //for binding livedata scope = for this activity
        
    }

    public class MyPresenter
    {
        public void onSaveClick(Person p)
        {
            Toast.makeText(DataBinding.this, "clicked onSaveClick()", Toast.LENGTH_SHORT).show();
        }
        public void onButtonClicked(View view)
        {

            Toast.makeText(DataBinding.this, "clicked onButtonClicked()", Toast.LENGTH_SHORT).show();
            if(i==1)
            {
                binding.setLastName("New Lastname");
                i=0;
            }
            else if(i==0)
            {
                binding.setLastName("Patel");
                i=1;
            }
        }
    }

}
