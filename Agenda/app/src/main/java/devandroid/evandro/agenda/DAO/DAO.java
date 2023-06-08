package devandroid.evandro.agenda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.agenda.objetos.Pessoa;

public class DAO extends SQLiteOpenHelper {


    public DAO(@Nullable Context context) {
        super(context, "banco", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE pessoa(nome TEXT,sexo TEXT,idade Integer)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antigaVersao, int novaVersao) {

        String sql = "DROP TABLE IF EXISTS pessoa";
        db.execSQL(sql);
        onCreate(db);


    }

    public void inserePessoa(Pessoa pessoa) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", pessoa.getNome());
        dados.put("sexo", pessoa.getSexo());
        dados.put("idade", pessoa.getIdade());

        db.insert("pessoa", null, dados);

    }

    public List<Pessoa> buscarPessoa() {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT  * FROM  pessoa";
        Cursor busca = db.rawQuery(sql, null);


        List<Pessoa> pessoas = new ArrayList<>();

        while (busca.moveToNext()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(busca.getString(busca.getColumnIndexOrThrow("nome")));
            pessoa.setSexo(busca.getString(busca.getColumnIndexOrThrow("sexo")));
            pessoa.setIdade(Integer.valueOf(busca.getString(busca.getColumnIndexOrThrow("idade"))));
            pessoas.add(pessoa);
        }
        return pessoas;


    }
    public void apagaPessoa(String nome){

        SQLiteDatabase db = getReadableDatabase();

        String sql = "DELETE FROM pessoa WHERE nome = "+ "'"+ nome +"'";
        db.execSQL(sql);

    }
}
