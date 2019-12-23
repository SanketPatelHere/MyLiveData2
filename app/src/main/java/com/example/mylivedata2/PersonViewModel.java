package com.example.mylivedata2;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
public class PersonViewModel extends ViewModel {
    public MutableLiveData<Person> userMutableLiveData = new MutableLiveData<>();
    private Person p;
    int i=1;
    public PersonViewModel() {
        p = new Person("User", "patel", "playing");
        userMutableLiveData.setValue(p);
    }

    public void changeUserName() {
        p.setName("Name is Changed");
    }

    public void changeUser() {
        if(i==1)
        {
            p = new Person("New User1", "sanket patel", "playing, sleeping");
            // Without setting new value UI is not updated and observe is not called
            userMutableLiveData.setValue(p);
            i=0;
        }
        else if(i==0)
        {
            p = new Person("New User2", "sanket ramani", "playing, chess");
            // Without setting new value UI is not updated and observe is not called
            userMutableLiveData.setValue(p);
            i=1;
        }

    }
}
