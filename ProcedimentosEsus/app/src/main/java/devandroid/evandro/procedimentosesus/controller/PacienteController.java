package devandroid.evandro.procedimentosesus.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import devandroid.evandro.procedimentosesus.dataModel.EnderecoDM;
import devandroid.evandro.procedimentosesus.dataModel.PacienteDM;
import devandroid.evandro.procedimentosesus.model.Endereco;
import devandroid.evandro.procedimentosesus.model.Paciente;

public class PacienteController {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;


    public PacienteController(Context context) {

        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarPaciente(Paciente paciente){

        ContentValues cv = new ContentValues();
        cv.put(PacienteDM.CPF,paciente.getCpf());
        cv.put(PacienteDM.NOME, paciente.getNome());
        cv.put(PacienteDM.DATA_NASCIMENTO,paciente.getData_nascimento());
        cv.put(PacienteDM.SEXO,paciente.getSexo());
        cv.put(PacienteDM.COR,paciente.getCor());

        try {

            write.insert(PacienteDM.TABELA,null,cv);
            //write.close();

        }catch (Exception e){
            Log.i("ERROR","Erro a salvar "+e.getMessage());
        }

    }
}
