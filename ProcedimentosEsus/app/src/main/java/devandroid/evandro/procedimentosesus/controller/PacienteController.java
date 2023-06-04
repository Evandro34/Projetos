package devandroid.evandro.procedimentosesus.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.procedimentosesus.api.AppUtil;
import devandroid.evandro.procedimentosesus.dataModel.ConsultaDM;
import devandroid.evandro.procedimentosesus.dataModel.EnderecoDM;
import devandroid.evandro.procedimentosesus.dataModel.PacienteDM;
import devandroid.evandro.procedimentosesus.model.Consulta;
import devandroid.evandro.procedimentosesus.model.Endereco;
import devandroid.evandro.procedimentosesus.model.Paciente;

public class PacienteController {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;


    public PacienteController(Context context) {

        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarPaciente(Paciente paciente) {

        ContentValues cv = new ContentValues();
        cv.put(PacienteDM.CPF, paciente.getCpf());
        cv.put(PacienteDM.NOME, paciente.getNome());
        cv.put(PacienteDM.DATA_NASCIMENTO, paciente.getData_nascimento());
        cv.put(PacienteDM.SEXO, paciente.getSexo());
        cv.put(PacienteDM.COR, paciente.getCor());

        try {

            write.insert(PacienteDM.TABELA, null, cv);
            //write.close();

        } catch (Exception e) {
            Log.i("ERROR", "Erro a salvar " + e.getMessage());
        }

    }

    public List<Consulta> getCpfCurativo(String dataInicio, String dataFinal) {

        List<Consulta> consultaList = new ArrayList<>();
//SELECT  distinct fkcpfPaciente FROM consulta WHERE data BETWEEN '2023-06-01'  And '2023-06-04' And procedimentos = 'Curativo'


        String buscarCpf2 = "SELECT DISTINCT " + ConsultaDM.FKCPF + " FROM " + ConsultaDM.TABELA + " WHERE " + ConsultaDM.DATA + " BETWEEN " + "'" + dataInicio + "'" + " AND " + "'" + dataFinal + "'" + " AND " + ConsultaDM.PROCEDIMENTO + " = " + "'Curativo'";


        // String buscarCpf = "SELECT DISTINCT " + ConsultaDM.FKCPF + " FROM " +
        //        ConsultaDM.TABELA + " WHERE " + ConsultaDM.DATA + " = " + "'" + data + "'"+" AND " + ConsultaDM.TURNO + " = " + "'"+turno+"'";
        Cursor cursor = read.rawQuery(buscarCpf2, null);


        while (cursor.moveToNext()) {
            Consulta consulta = new Consulta();
            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.FKCPF));
            consulta.setCnsPaciente(cpf);
            consultaList.add(consulta);

        }
        return consultaList;

    }

    public Paciente getTodasDataCurativoPorCpf(String cpf1) {

        /* fkcpfendereco,
        nome,datanascimento,
        sexo,cor,
        logradouro,endereco,numero,bairro,cidade,cep
        FROM endereco INNER JOIN paciente ON fkcpfendereco = cpf where fkcpfendereco = '1'";
         */

        String buscarCpf = "SELECT " + PacienteDM.CPF + ", " + PacienteDM.NOME + ", " + PacienteDM.DATA_NASCIMENTO + ", "
                + PacienteDM.SEXO + ", " + PacienteDM.COR + ", " + EnderecoDM.LOGRADOURO + ", " + EnderecoDM.ENDERECO + ", "
                + EnderecoDM.NUMERO + ", " + EnderecoDM.BAIRRO + ", " + EnderecoDM.CIDADE + ", " + EnderecoDM.CEP + " FROM "
                + EnderecoDM.TABELA + " INNER JOIN " + PacienteDM.TABELA + " ON "
                + EnderecoDM.FKCPF + " = " + PacienteDM.CPF + " WHERE  " + EnderecoDM.FKCPF + " = " + "'" + cpf1 + "'";

        Cursor cursor = read.rawQuery(buscarCpf, null);


        Log.i("Procedimentos", "CPF " + buscarCpf);
        Paciente paciente = new Paciente();
        Endereco endereco = new Endereco();
        Consulta consulta = new Consulta();
        while (cursor.moveToNext()) {


            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(PacienteDM.CPF));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(PacienteDM.NOME));
            String DN = cursor.getString(cursor.getColumnIndexOrThrow(PacienteDM.DATA_NASCIMENTO));
            String sexo = cursor.getString(cursor.getColumnIndexOrThrow(PacienteDM.SEXO));
            String cor = cursor.getString(cursor.getColumnIndexOrThrow(PacienteDM.COR));
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
            consulta.setData(listData(cpf1));
            paciente.setConsultaPaciente(consulta);

        }
        return paciente;

    }

    private String listData(String cpf) {

        String buscarCpf = "SELECT  distinct " +ConsultaDM.DATA
                +" FROM " + ConsultaDM.TABELA + " WHERE "
                + ConsultaDM.FKCPF + " = " + "'" + cpf + "'" +
                " and " +ConsultaDM.DATA + " BETWEEN '2023-06-01'  And '2023-06-04' And " + ConsultaDM.PROCEDIMENTO+ " = 'Curativo'";

        Cursor cursor = read.rawQuery(buscarCpf, null);

        String procedimentos = "";
        while (cursor.moveToNext()) {
            String data = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.DATA));

            try {
                procedimentos = procedimentos + AppUtil.getDataAtualFormatoBrasileiro(data) + "\n";
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


        }
        return procedimentos;

    }

}

