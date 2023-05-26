package devandroid.evandro.esusprocedimetnos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import devandroid.evandro.esusprocedimetnos.model.Consulta;
import devandroid.evandro.esusprocedimetnos.model.Endereco;

public class ConsultaDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public ConsultaDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();

    }
    public void salvarConsulta(Consulta consulta){

        ContentValues cv = new ContentValues();
        cv.put("fkcnsPaciente",consulta.getCnsPaciente());
        cv.put("data", consulta.getData());
        cv.put("turno",consulta.getTurno());
        cv.put("local",consulta.getLocal());
        cv.put("procedimentos",consulta.getProcedimentos());


        try {

            write.insert(DBHelper.TB_CONSULTA,null,cv);
            //write.close();

        }catch (Exception e){
            Log.i("ERROR","Erro a salvar Consulta"+e.getMessage());
        }

    }
}
