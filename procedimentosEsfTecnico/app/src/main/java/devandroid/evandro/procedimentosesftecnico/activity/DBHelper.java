package devandroid.evandro.procedimentosesftecnico.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME_DB = "ESF_APP";
    public static final String TB_CONSULTA = "consulta";
    public static final String TB_PACIENTE = "paciente";
    public static final String TB_ENDERECO = "endereco";


    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String sqlPaciente = "CREATE TABLE IF NOT EXISTS " + TB_PACIENTE +
                "(cpfCns          TEXT PRIMARY KEY, " +
                "nome            TEXT NOT NULL, " +
                "data_nascimento TEXT NOT NULL, " +
                "sexo            TEXT NOT NULL, " +
                "cor             TEXT NOT NULL );";


        String sqlEndereco = "CREATE TABLE IF NOT EXISTS " + TB_ENDERECO +
                "(fkcnsendereco TEXT PRIMARY KEY," +
                "logradouro    TEXT, " +
                "endereco      TEXT, " +
                "numero        TEXT, " +
                "bairro        TEXT, " +
                " cidade       TEXT, " +
                "estado        TEXT, " +
                "cep           TEXT, " +
                "FOREIGN KEY (fkcnsendereco)REFERENCES " +
                TB_PACIENTE +
                "(cpfCns));";

        String sqlConsulta = "CREATE TABLE IF NOT EXISTS " + TB_CONSULTA+
        "(idConsulta    INTEGER PRIMARY KEY AUTOINCREMENT," +
                " cnsPaciente   TEXT," +
                "data          TEXT, " +
                "turno         TEXT, " +
                "local         TEXT, " +
                "procedimentos TEXT, " +
                " FOREIGN KEY (cnsPaciente) REFERENCES "
                + TB_PACIENTE +
                " (cpfCns));";


        try {

            sqLiteDatabase.execSQL(sqlPaciente);
            sqLiteDatabase.execSQL(sqlEndereco);
            sqLiteDatabase.execSQL(sqlConsulta);

        } catch (Exception e) {
            Log.i("ERROR", "Erro a criar Tabela" + e.getMessage());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
