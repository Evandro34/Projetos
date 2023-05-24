package devandroid.evandro.procedimentosesftecnico.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import devandroid.evandro.procedimentosesftecnico.activity.DBHelper;
import devandroid.evandro.procedimentosesftecnico.model.Paciente;

public class PacienteDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;


    public PacienteDAO(Context context) {

        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarPaciente(Paciente paciente){

        ContentValues cv = new ContentValues();
        cv.put("cpfCns",paciente.getCpf());
        cv.put("nome", paciente.getNome());
        cv.put("data_nascimento",paciente.getData_nascimento());
        cv.put("sexo",paciente.getSexo());
        cv.put("cor",paciente.getCor());

        try {

            write.insert(DBHelper.TB_PACIENTE,null,cv);
            //write.close();

        }catch (Exception e){
            Log.i("ERROR","Erro a salvar produto"+e.getMessage());
        }

    }
}
