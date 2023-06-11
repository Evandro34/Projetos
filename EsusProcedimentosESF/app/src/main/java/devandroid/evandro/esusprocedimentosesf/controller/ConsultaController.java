package devandroid.evandro.esusprocedimentosesf.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.esusprocedimentosesf.api.AppUtil;
import devandroid.evandro.esusprocedimentosesf.dataModel.ConsultaDM;
import devandroid.evandro.esusprocedimentosesf.dataModel.PessoaDM;
import devandroid.evandro.esusprocedimentosesf.model.Consulta;
import devandroid.evandro.esusprocedimentosesf.model.Pessoa;

public class ConsultaController {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public ConsultaController(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarConsulta(Consulta consulta) {

        ContentValues cv = new ContentValues();

        cv.put(ConsultaDM.DATA, consulta.getData());
        cv.put(ConsultaDM.TURNO, consulta.getTurno());
        cv.put(ConsultaDM.FKIDPESSOACONSULTA, consulta.getFkidPessoaConsulta());
        cv.put(ConsultaDM.LOCAL, consulta.getLocal());
        cv.put(ConsultaDM.PROCEDIMENTO, consulta.getProcedimentos());
        try {

            write.insert(ConsultaDM.TABELA, null, cv);
            //write.close();

        } catch (Exception e) {
            Log.i("ERROR", "Erro a salvar " + e.getMessage());
        }

    }

    public List<Consulta> getTodoCpfDaDataAtual(String data, String turno) {

        List<Consulta> consultaList = new ArrayList<>();

        String buscarCpf = "SELECT DISTINCT " + ConsultaDM.FKIDPESSOACONSULTA + " FROM " +
                ConsultaDM.TABELA + " WHERE " + ConsultaDM.DATA + " = " + "'" + data + "'" + " AND " + ConsultaDM.TURNO + " = " + "'" + turno + "'";
        Cursor cursor = read.rawQuery(buscarCpf, null);


        Log.i(AppUtil.LOG_APP, "" + buscarCpf);

        while (cursor.moveToNext()) {

            Consulta consulta = new Consulta();
            int id = (cursor.getInt(cursor.getColumnIndexOrThrow(ConsultaDM.FKIDPESSOACONSULTA)));
            consulta.setFkidPessoaConsulta(id);
            consultaList.add(consulta);

        }
        return consultaList;

    }

    public Consulta getTodosProcedimentoPorPaciente(int idPessoa, String sTurno) {

        //"SELECT   fkcpfPaciente, datanascimento,sexo,turno, data, local FROM consulta INNER JOIN paciente ON fkcpfPaciente = cpf where fkcpfPaciente = '1' and  turno  ='noite'";


        String buscarCpf = "SELECT " + ConsultaDM.DATA + ", " + ConsultaDM.TURNO + ", " + PessoaDM.CPF + ", " + PessoaDM.DATA_NASCIMENTO + ", " + PessoaDM.SEXO +
                ", " + ConsultaDM.LOCAL +
                " FROM " + ConsultaDM.TABELA + " INNER JOIN " + PessoaDM.TABELA + " ON " + ConsultaDM.FKIDPESSOACONSULTA + " = " + PessoaDM.IDPESSOA + " WHERE "
                + ConsultaDM.FKIDPESSOACONSULTA + " = " + "'" + idPessoa + "'" + " AND " + ConsultaDM.TURNO + " = " + "'" + sTurno + "'";
        Log.i(AppUtil.LOG_APP, "" + buscarCpf);
        Cursor cursor = read.rawQuery(buscarCpf, null);

        Consulta consulta = new Consulta();

        Log.i("Procedimentos", "CPF " + buscarCpf);

        while (cursor.moveToNext()) {

            Pessoa paciente = new Pessoa();
            String data = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.DATA));
            String turno = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.TURNO));
            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.CPF));
            String dn = cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.DATA_NASCIMENTO));
            String sexo = cursor.getString(cursor.getColumnIndexOrThrow(PessoaDM.SEXO));
            String local = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.LOCAL));


            consulta.setData(data);
            consulta.setTurno(turno);
            paciente.setCpf(cpf);
            consulta.setCpf(paciente.getCpf());
            paciente.setData_nascimento(dn);
            consulta.setData_nascimento(paciente.getData_nascimento());
            paciente.setSexo(sexo);
            consulta.setSexo(paciente.getSexo());
            consulta.setLocal(local);
            consulta.setProcedimentos(listProcedimentos(idPessoa, data, turno));


        }

        return consulta;

    }

    private String listProcedimentos(int idPessoa, String data, String turno) {


        String buscarCpf = "SELECT " + ConsultaDM.PROCEDIMENTO + " FROM " + ConsultaDM.TABELA + " WHERE " + ConsultaDM.FKIDPESSOACONSULTA + "=" + "'" + idPessoa + "'" + " AND " + ConsultaDM.DATA + " = " + "'" + data + "'" + " AND " + ConsultaDM.TURNO + " = " + "'" + turno + "'";
        Cursor cursor = read.rawQuery(buscarCpf, null);

        String procedimentos = "";
        while (cursor.moveToNext()) {
            String procedimento = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.PROCEDIMENTO));

            procedimentos = procedimentos + procedimento + "\n";


        }

        return procedimentos;

    }
    public int totalProcedimentosEsusData(String data, String procedimentos) {
        int pa = 0;

        String buscarCpf = "SELECT Count (" + ConsultaDM.PROCEDIMENTO + " )FROM " +
                ConsultaDM.TABELA + " WHERE " + ConsultaDM.PROCEDIMENTO + " = " + "'" + procedimentos + "'" + " AND " + ConsultaDM.DATA + " = " + "'" + data + "'";
        Cursor cursor = read.rawQuery(buscarCpf, null);


        while (cursor.moveToNext()) {

            pa = Integer.parseInt(String.valueOf(cursor.getInt(Integer.parseInt("0"))));

        }
        return pa;
    }
}
