package devandroid.evandro.procedimentosesus.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import devandroid.evandro.procedimentosesus.dataModel.EnderecoDM;
import devandroid.evandro.procedimentosesus.model.Endereco;

public class EnderecoController {


    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public EnderecoController(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarEndereco(Endereco endereco){

        ContentValues cv = new ContentValues();
        cv.put(EnderecoDM.FKCPF,endereco.getFkCpfEndereco());
        cv.put(EnderecoDM.LOGRADOURO, endereco.getLogradouro());
        cv.put(EnderecoDM.ENDERECO,endereco.getEndereco());
        cv.put(EnderecoDM.NUMERO,endereco.getNumero());
        cv.put(EnderecoDM.BAIRRO,endereco.getBairro());
        cv.put(EnderecoDM.CIDADE,endereco.getCidade());
        cv.put(EnderecoDM.ESTADO,endereco.getEstado());
        cv.put(EnderecoDM.CEP,endereco.getCep());

        try {

            write.insert(EnderecoDM.TABELA,null,cv);
            //write.close();

        }catch (Exception e){
            Log.i("ERROR","Erro a salvar produto"+e.getMessage());
        }

    }
}
