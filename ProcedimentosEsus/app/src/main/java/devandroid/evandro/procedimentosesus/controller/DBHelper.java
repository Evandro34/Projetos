package devandroid.evandro.procedimentosesus.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import devandroid.evandro.procedimentosesus.dataModel.ConsultaDM;
import devandroid.evandro.procedimentosesus.dataModel.EnderecoDM;
import devandroid.evandro.procedimentosesus.dataModel.PacienteDM;
import devandroid.evandro.procedimentosesus.model.Consulta;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO =1;
    private static final String NOME_DB="DB_APP";
   static final String TB_PRODUTO="TB_PRODUTO";
    public DBHelper( Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        try {

            sqLiteDatabase.execSQL(PacienteDM.gerarTabela());

        }catch (Exception e){
            Log.i("ERROR","Erro a criar Tabela"+e.getMessage());
        }

        try {

            sqLiteDatabase.execSQL(EnderecoDM.gerarTabela());

        }catch (Exception e){
            Log.i("ERROR","Erro a criar Tabela"+e.getMessage());
        }

        try {

            sqLiteDatabase.execSQL(ConsultaDM.gerarTabela());

        }catch (Exception e){
            Log.i("ERROR","Erro a criar Tabela"+e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
