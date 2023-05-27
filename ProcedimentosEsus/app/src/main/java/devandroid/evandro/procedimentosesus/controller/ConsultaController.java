package devandroid.evandro.procedimentosesus.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import devandroid.evandro.procedimentosesus.dataModel.ConsultaDM;
import devandroid.evandro.procedimentosesus.dataModel.EnderecoDM;
import devandroid.evandro.procedimentosesus.model.Consulta;
import devandroid.evandro.procedimentosesus.model.Endereco;

public class ConsultaController {


    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public ConsultaController(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarConsulta(Consulta consulta){

        ContentValues cv = new ContentValues();

        cv.put(ConsultaDM.DATA, consulta.getData());
        cv.put(ConsultaDM.TURNO, consulta.getTurno());
        cv.put(ConsultaDM.FKCPF,consulta.getCnsPaciente());
        cv.put(ConsultaDM.LOCAL,consulta.getLocal());
        cv.put(ConsultaDM.PROCEDIMENTO,consulta.getProcedimentos());
        try {

            write.insert(ConsultaDM.TABELA,null,cv);
            //write.close();

        }catch (Exception e){
            Log.i("ERROR","Erro a salvar "+e.getMessage());
        }

    }
}
