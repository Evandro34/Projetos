package devandroid.evandro.esusprocedimentosesf.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.dataModel.PessoaDM;
import devandroid.evandro.esusprocedimentosesf.model.Pessoa;

public class PessoaController {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public PessoaController(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarPessoa(Pessoa pessoa) {

        ContentValues cv = new ContentValues();
        cv.put(PessoaDM.CPF, pessoa.getCpf());
        cv.put(PessoaDM.NOME, pessoa.getNome());
        cv.put(PessoaDM.DATA_NASCIMENTO, pessoa.getData_nascimento());
        cv.put(PessoaDM.SEXO, pessoa.getSexo());
        cv.put(PessoaDM.COR, pessoa.getCor());

        try {

            write.insert(PessoaDM.TABELA, null, cv);
            //write.close();

        } catch (Exception e) {
            Log.i("ERROR", "Erro a salvar " + e.getMessage());
        }

    }

    public int getLastPK() {

        // SELECT seq FROM sqlite_sequence WHERE name = "tabela"

        String sql = "SELECT seq FROM sqlite_sequence WHERE name = '" + PessoaDM.TABELA + "'";
        int id = 0;

        try {


            Cursor cursor = read.rawQuery(sql, null);

            if (cursor.moveToFirst()) {

                do {

                    id = cursor.getInt(cursor.getColumnIndexOrThrow("seq"));

                } while (cursor.moveToNext());

            }

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, "Erro recuperando último PK: " + PessoaDM.TABELA);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }

        return id;
    }

    public Pessoa getBuscaPessoaCpf(String cpf) {

        // SELECT seq FROM sqlite_sequence WHERE name = "tabela"

        String sql = "SELECT * FROM " + PessoaDM.TABELA + " WHERE " + PessoaDM.CPF + " = " + "'" + cpf + "'";
        Pessoa pessoa = new Pessoa();
        try {


            Cursor cursor = read.rawQuery(sql, null);


            while (cursor.moveToNext()) {

                if (cursor.getInt(cursor.getColumnIndexOrThrow(PessoaDM.IDPESSOA)) > 0) {
                    pessoa.setIdPessoa(cursor.getInt(cursor.getColumnIndexOrThrow(PessoaDM.IDPESSOA)));
                    pessoa.setCpf(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.CPF)));
                    pessoa.setNome(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.NOME)));
                    pessoa.setData_nascimento(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.DATA_NASCIMENTO)));
                    pessoa.setCor(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.COR)));
                    pessoa.setSexo(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.SEXO)));
                }

            }

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, "Erro recuperando último PK: " + PessoaDM.TABELA);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }


        return pessoa;
    }

    public Pessoa getBuscaPessoaId(int id) {

        // SELECT seq FROM sqlite_sequence WHERE name = "tabela"

        String sql = "SELECT * FROM " + PessoaDM.TABELA + " WHERE " + PessoaDM.IDPESSOA + " = " + "'" + id + "'";
        Pessoa pessoa = new Pessoa();
        try {


            Cursor cursor = read.rawQuery(sql, null);


            while (cursor.moveToNext()) {


                pessoa.setIdPessoa(cursor.getInt(cursor.getColumnIndexOrThrow(PessoaDM.IDPESSOA)));
                pessoa.setCpf(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.CPF)));
                pessoa.setNome(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.NOME)));
                pessoa.setData_nascimento(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.DATA_NASCIMENTO)));
                pessoa.setCor(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.COR)));
                pessoa.setSexo(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.SEXO)));


            }

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, "Erro recuperando último PK: " + PessoaDM.TABELA);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }


        return pessoa;
    }

    public Pessoa getBuscaPessoaNomeData(String nome, String data) {

        // SELECT seq FROM sqlite_sequence WHERE name = "tabela"

        String sql = "SELECT * FROM " + PessoaDM.TABELA + " WHERE " + PessoaDM.NOME + " = " + "'"
                + nome + "'" + " AND " + PessoaDM.DATA_NASCIMENTO + " = " + "'" + data + "'";
        ;
        Pessoa pessoa = new Pessoa();
        try {


            Cursor cursor = read.rawQuery(sql, null);


            while (cursor.moveToNext()) {

                pessoa.setIdPessoa(cursor.getInt(cursor.getColumnIndexOrThrow(PessoaDM.IDPESSOA)));
                pessoa.setCpf(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.CPF)));
                pessoa.setNome(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.NOME)));
                pessoa.setData_nascimento(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.DATA_NASCIMENTO)));
                pessoa.setCor(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.COR)));
                pessoa.setSexo(cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.SEXO)));

            }

        } catch (SQLException e) {

            Log.e(AppUtil.LOG_APP, "Erro recuperando último PK: " + PessoaDM.TABELA);
            Log.e(AppUtil.LOG_APP, "Erro: " + e.getMessage());
        }

        return pessoa;
    }

    public int verificaTotalDeRegistroLocalizado(String cpf) {
        int total = 0;

        String buscarCpf = " SELECT Count (" + PessoaDM.IDPESSOA + ") FROM " +
                PessoaDM.TABELA + " WHERE " + PessoaDM.CPF + " = " + "'" + cpf + "'";
        Cursor cursor = read.rawQuery(buscarCpf, null);

        while (cursor.moveToNext()) {

            total = Integer.parseInt(String.valueOf(cursor.getInt(Integer.parseInt("0"))));

        }
        return total;
    }

    public int verificaTotalDeRegistroLocalizado(String nome, String data) {
        int total = 0;

        String buscarCpf = " SELECT Count (" + PessoaDM.IDPESSOA + ") FROM " +
                PessoaDM.TABELA + " WHERE " + PessoaDM.NOME + " = " + "'" + nome + "'" + " AND " + PessoaDM.DATA_NASCIMENTO + " = " + "'" + data + "'";
        Cursor cursor = read.rawQuery(buscarCpf, null);

        while (cursor.moveToNext()) {

            total = Integer.parseInt(String.valueOf(cursor.getInt(Integer.parseInt("0"))));

        }
        return total;
    }

}
