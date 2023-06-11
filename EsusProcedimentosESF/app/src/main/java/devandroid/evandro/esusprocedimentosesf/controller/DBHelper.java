package devandroid.evandro.esusprocedimentosesf.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import devandroid.evandro.esusprocedimentosesf.dataModel.ConsultaDM;
import devandroid.evandro.esusprocedimentosesf.dataModel.EnderecoDM;
import devandroid.evandro.esusprocedimentosesf.dataModel.PessoaDM;


public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME_DB = "ESUS_APP";


    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        try {

            sqLiteDatabase.execSQL(PessoaDM.gerarTabela());

        } catch (Exception e) {
            Log.i("ERROR", "Erro a criar Tabela" + e.getMessage());
        }

        try {

            sqLiteDatabase.execSQL(EnderecoDM.gerarTabela());

        } catch (Exception e) {
            Log.i("ERROR", "Erro a criar Tabela" + e.getMessage());
        }

        try {

            sqLiteDatabase.execSQL(ConsultaDM.gerarTabela());

        } catch (Exception e) {
            Log.i("ERROR", "Erro a criar Tabela" + e.getMessage());
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
        if (!sqLiteDatabase.isReadOnly()) {
            sqLiteDatabase.execSQL("PRAGMA foreing_key = ON;");
        }
    }

}
