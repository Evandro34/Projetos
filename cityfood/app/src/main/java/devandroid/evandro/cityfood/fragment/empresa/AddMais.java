package devandroid.evandro.cityfood.fragment.empresa;


import com.google.firebase.database.DatabaseReference;

import java.util.List;

import devandroid.evandro.cityfood.helper.FirebaseHelper;

public class AddMais {
    public static void salvar(List<String> addMaisList){
        DatabaseReference addMaisRef = FirebaseHelper.getDatabaseReference()
                .child("addMais")
                .child(FirebaseHelper.getIdFirebase());
        addMaisRef.setValue(addMaisList);
    }
}
