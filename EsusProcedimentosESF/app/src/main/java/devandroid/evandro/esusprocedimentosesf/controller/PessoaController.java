package devandroid.evandro.esusprocedimentosesf.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.dataModel.ConsultaDM;
import devandroid.evandro.esusprocedimentosesf.dataModel.EnderecoDM;
import devandroid.evandro.esusprocedimentosesf.dataModel.PessoaDM;
import devandroid.evandro.esusprocedimentosesf.model.Consulta;
import devandroid.evandro.esusprocedimentosesf.model.Endereco;
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

    public int listTotalProcedimentosPab(String dataInicial, String dataFinal, String procedimento) {

        String buscarCpf = "SELECT  COUNT(" + ConsultaDM.PROCEDIMENTO + ") FROM " + ConsultaDM.TABELA + " WHERE " + ConsultaDM.DATA + " BETWEEN " + "'" + dataInicial + "'" + "And '" + dataFinal + "' And " + ConsultaDM.PROCEDIMENTO + " = '" + procedimento + "'";

        Log.i("Teste", "" + buscarCpf);
        Cursor cursor = read.rawQuery(buscarCpf, null);
        int total = 0;
        String procedimentos = "";
        while (cursor.moveToNext()) {
            total = Integer.parseInt(String.valueOf(cursor.getInt(Integer.parseInt("0"))));


        }
        return total;

    }

    public List<Consulta> getCpfCurativo(String dataInicio, String dataFinal) {

        List<Consulta> consultaList = new ArrayList<>();
//SELECT  distinct fkcpfPaciente FROM consulta WHERE data BETWEEN '2023-06-01'  And '2023-06-04' And procedimentos = 'Curativo'


        String buscarCpf2 = "SELECT DISTINCT " + ConsultaDM.FKIDPESSOACONSULTA + " FROM " + ConsultaDM.TABELA + " WHERE " + ConsultaDM.DATA + " BETWEEN " + "'" + dataInicio + "'" + " AND " + "'" + dataFinal + "'" + " AND " + ConsultaDM.PROCEDIMENTO + " = " + "'Curativo'";

        Log.i("TESTE2", "" + buscarCpf2);

        // String buscarCpf = "SELECT DISTINCT " + ConsultaDM.FKCPF + " FROM " +
        //        ConsultaDM.TABELA + " WHERE " + ConsultaDM.DATA + " = " + "'" + data + "'"+" AND " + ConsultaDM.TURNO + " = " + "'"+turno+"'";
        Cursor cursor = read.rawQuery(buscarCpf2, null);


        while (cursor.moveToNext()) {
            Consulta consulta = new Consulta();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ConsultaDM.FKIDPESSOACONSULTA));
            consulta.setFkidPessoaConsulta(id);
            consultaList.add(consulta);

        }
        return consultaList;

    }

    public Pessoa getTodasDataCurativoPorCpf(int id, String dataInicial, String dataFinal) {

        /* fkcpfendereco,
        nome,datanascimento,
        sexo,cor,
        logradouro,endereco,numero,bairro,cidade,cep
        FROM endereco INNER JOIN paciente ON fkcpfendereco = cpf where fkcpfendereco = '1'";
         */

        String buscarCpf = "SELECT " + PessoaDM.CPF + ", " + PessoaDM.NOME + ", " + PessoaDM.DATA_NASCIMENTO + ", "
                + PessoaDM.SEXO + ", " + PessoaDM.COR + ", " + EnderecoDM.LOGRADOURO + ", " + EnderecoDM.ENDERECO + ", "
                + EnderecoDM.NUMERO + ", " + EnderecoDM.BAIRRO + ", " + EnderecoDM.CIDADE + ", " + EnderecoDM.CEP + " FROM "
                + EnderecoDM.TABELA + " INNER JOIN " + PessoaDM.TABELA + " ON "
                + EnderecoDM.FKIDPESSOAENDERECO + " = " + PessoaDM.IDPESSOA + " WHERE  " + EnderecoDM.FKIDPESSOAENDERECO + " = " + "'" + id + "'";

        Cursor cursor = read.rawQuery(buscarCpf, null);


        Log.i("TESTE2", "" + buscarCpf);
        Pessoa paciente = new Pessoa();
        Endereco endereco = new Endereco();
        Consulta consulta = new Consulta();
        while (cursor.moveToNext()) {


            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.CPF));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.NOME));
            String DN = cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.DATA_NASCIMENTO));
            String sexo = cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.SEXO));
            String cor = cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.COR));
            String logradouro = cursor.getString(cursor.getColumnIndexOrThrow(EnderecoDM.LOGRADOURO));
            String endereco1 = cursor.getString(cursor.getColumnIndexOrThrow(EnderecoDM.ENDERECO));
            String numero = cursor.getString(cursor.getColumnIndexOrThrow(EnderecoDM.NUMERO));
            String bairro = cursor.getString(cursor.getColumnIndexOrThrow(EnderecoDM.BAIRRO));
            String cidade = cursor.getString(cursor.getColumnIndexOrThrow(EnderecoDM.CIDADE));
            String cep = cursor.getString(cursor.getColumnIndexOrThrow(EnderecoDM.CEP));


            paciente.setCpf(cpf);
            paciente.setNome(nome);
            paciente.setData_nascimento(DN);
            paciente.setSexo(sexo);
            paciente.setCor(cor);
            endereco.setLogradouro(logradouro);
            endereco.setEndereco(endereco1);
            endereco.setNumero(numero);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setCep(cep);
            paciente.setEnderecoPaciente(endereco);
            consulta.setData(listData(id, dataInicial, dataFinal));
            paciente.setConsultaPaciente(consulta);

        }
        return paciente;

    }
    private String listData(int  id, String dataInicial, String dataFinal) {

        String buscarCpf = "SELECT  distinct " + ConsultaDM.DATA
                + " FROM " + ConsultaDM.TABELA + " WHERE "
                + ConsultaDM.FKIDPESSOACONSULTA + " = " + "'" + id + "'"+" and "+ ConsultaDM.DATA + " BETWEEN " + "'" + dataInicial + "'" + " AND " + "'" + dataFinal + "'" + " And " + ConsultaDM.PROCEDIMENTO + " = 'Curativo'";

        Cursor cursor = read.rawQuery(buscarCpf, null);
        Log.i("TESTE2", "" + buscarCpf);
        String procedimentos = "";
        while (cursor.moveToNext()) {
            String data = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.DATA));


            procedimentos = procedimentos + AppUtil.getDataAtualFormatoBrasileiro(data) + "\n";


        }
        return procedimentos;

    }

}

