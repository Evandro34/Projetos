package devandroid.evandro.esusprocedimetnos.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import devandroid.evandro.esusprocedimetnos.model.Endereco;

public class EnderecoDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public EnderecoDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarEndereco(Endereco endereco){

        ContentValues cv = new ContentValues();
        cv.put("fkcnsendereco",endereco.getFkCpfEndereco());
        cv.put("logradouro", endereco.getLogradouro());
        cv.put("endereco",endereco.getEndereco());
        cv.put("numero",endereco.getNumero());
        cv.put("bairro",endereco.getBairro());
        cv.put("cidade",endereco.getCidade());
        cv.put("estado",endereco.getEstado());
        cv.put("cep",endereco.getCep());

        try {

            write.insert(DBHelper.TB_ENDERECO,null,cv);
            //write.close();

        }catch (Exception e){
            Log.i("ERROR","Erro a salvar produto"+e.getMessage());
        }

    }
}
