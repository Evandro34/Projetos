package devandroid.evandro.procedimentosesus.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import devandroid.evandro.procedimentosesus.dataModel.ConsultaDM;
import devandroid.evandro.procedimentosesus.dataModel.PacienteDM;
import devandroid.evandro.procedimentosesus.model.Consulta;
import devandroid.evandro.procedimentosesus.model.Paciente;

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
        cv.put(ConsultaDM.FKCPF, consulta.getCnsPaciente());
        cv.put(ConsultaDM.LOCAL, consulta.getLocal());
        cv.put(ConsultaDM.PROCEDIMENTO, consulta.getProcedimentos());
        try {

            write.insert(ConsultaDM.TABELA, null, cv);
            //write.close();

        } catch (Exception e) {
            Log.i("ERROR", "Erro a salvar " + e.getMessage());
        }

    }

    public List<Consulta> getCpf(String data) {

        List<Consulta> consultaList = new ArrayList<>();

        String buscarCpf = "SELECT DISTINCT " + ConsultaDM.FKCPF + " FROM " + ConsultaDM.TABELA + " WHERE " + ConsultaDM.DATA + " = " + "'" + data + "'";
        Cursor cursor = read.rawQuery(buscarCpf, null);


        while (cursor.moveToNext()) {
            Consulta consulta = new Consulta();
            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.FKCPF));
            consulta.setCnsPaciente(cpf);
            consultaList.add(consulta);

        }
        return consultaList;

    }

    public String getCpf1(String data) {


        String buscarCpf = "SELECT DISTINCT " + ConsultaDM.FKCPF + " FROM " + ConsultaDM.TABELA + " WHERE " + ConsultaDM.DATA + " = " + "'" + data + "'";
        Cursor cursor = read.rawQuery(buscarCpf, null);

        Consulta consulta = new Consulta();
        while (cursor.moveToNext()) {

            @SuppressLint("Range") String dataC = cursor.getString(cursor.getColumnIndex(ConsultaDM.DATA));
            @SuppressLint("Range") String turno = cursor.getString(cursor.getColumnIndex(ConsultaDM.TURNO));
            @SuppressLint("Range") String cpf = cursor.getString(cursor.getColumnIndex(ConsultaDM.FKCPF));
            @SuppressLint("Range") String dn = cursor.getString(cursor.getColumnIndex(PacienteDM.DATA_NASCIMENTO));
            @SuppressLint("Range") String local = cursor.getString(cursor.getColumnIndex(ConsultaDM.LOCAL));

            consulta.setCnsPaciente(cpf);


        }
        return consulta.getCnsPaciente();

    }

    public List<Consulta> getProcedimentos(String cpf1) {
        List<Consulta> consultaList = new ArrayList<>();

        String buscarCpf = "SELECT  DISTINCT " + ConsultaDM.FKCPF + ", " + ConsultaDM.TURNO + ", " + ConsultaDM.DATA + ", " + ConsultaDM.LOCAL +
                " FROM " + ConsultaDM.TABELA + " INNER JOIN " + PacienteDM.TABELA + " ON " + ConsultaDM.FKCPF + " = " + "'" + cpf1 + "'";
        Cursor cursor = read.rawQuery(buscarCpf, null);


        Log.i("Procedimentos", "CPF " + buscarCpf);

        while (cursor.moveToNext()) {

            String data = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.DATA));
            String turno = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.TURNO));
            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.FKCPF));
            //String dn = cursor.getString(cursor.getColumnIndexOrThrow(PacienteDM.DATA_NASCIMENTO));
            String local = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.LOCAL));
            // @SuppressLint("Range") String procedimento = cursor.getString(cursor.getColumnIndex(ConsultaDM.PROCEDIMENTO));

            Consulta consulta = new Consulta();
            consulta.setData(data);
            consulta.setTurno(turno);
            consulta.setCnsPaciente(cpf);
            //consulta.setData_nascimento(dn);
            consulta.setLocal(local);
            // consulta.setProcedimentos(procedimento);

            consultaList.add(consulta);
        }
        return consultaList;

    }

    public List<Consulta> getTODOS(String cpf1) {
        List<Consulta> consultaList = new ArrayList<>();

        String buscarCpf = "SELECT  DISTINCT " + ConsultaDM.FKCPF + ", " + ConsultaDM.TURNO + ", " + ConsultaDM.DATA + ", "
                + PacienteDM.DATA_NASCIMENTO + ", " + ConsultaDM.LOCAL +
                " FROM " + ConsultaDM.TABELA + " INNER JOIN " + PacienteDM.TABELA + " ON " + ConsultaDM.FKCPF + " = " + "'" + cpf1 + "'" + " AND " + ConsultaDM.TURNO + " = 'noite' ";
        Cursor cursor = read.rawQuery(buscarCpf, null);


        while (cursor.moveToNext()) {
            @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(ConsultaDM.DATA));
            @SuppressLint("Range") String turno = cursor.getString(cursor.getColumnIndex(ConsultaDM.TURNO));
            @SuppressLint("Range") String cpf = cursor.getString(cursor.getColumnIndex(ConsultaDM.FKCPF));
            @SuppressLint("Range") String dn = cursor.getString(cursor.getColumnIndex(PacienteDM.DATA_NASCIMENTO));
            @SuppressLint("Range") String local = cursor.getString(cursor.getColumnIndex(ConsultaDM.LOCAL));
            // @SuppressLint("Range") String procedimento = cursor.getString(cursor.getColumnIndex(ConsultaDM.PROCEDIMENTO));

            Consulta consulta = new Consulta();
            consulta.setData(data);
            consulta.setTurno(turno);
            consulta.setCnsPaciente(cpf);
            consulta.setData_nascimento(dn);
            consulta.setLocal(local);
            // consulta.setProcedimentos(procedimento);

            consultaList.add(consulta);
        }
        return consultaList;

    }

    public Consulta getProcedimento(String cpf1) {
        String buscarCpf = "SELECT " + ConsultaDM.DATA + ", " + ConsultaDM.TURNO + ", " + ConsultaDM.FKCPF + ", "
                + PacienteDM.DATA_NASCIMENTO + ", " + ConsultaDM.LOCAL +
                " FROM " + ConsultaDM.TABELA + " INNER JOIN " + PacienteDM.TABELA + " ON " + ConsultaDM.FKCPF + " = " + "'" + cpf1 + "'" + " AND " + ConsultaDM.TURNO + " = 'noite' ";
        Cursor cursor = read.rawQuery(buscarCpf, null);

        Consulta consulta = new Consulta();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex(ConsultaDM.DATA));
            @SuppressLint("Range") String turno = cursor.getString(cursor.getColumnIndex(ConsultaDM.TURNO));
            @SuppressLint("Range") String cpf = cursor.getString(cursor.getColumnIndex(ConsultaDM.FKCPF));
            @SuppressLint("Range") String dn = cursor.getString(cursor.getColumnIndex(PacienteDM.DATA_NASCIMENTO));
            @SuppressLint("Range") String local = cursor.getString(cursor.getColumnIndex(ConsultaDM.LOCAL));
            // @SuppressLint("Range") String procedimento = cursor.getString(cursor.getColumnIndex(ConsultaDM.PROCEDIMENTO));


            consulta.setData(data);
            consulta.setTurno(turno);
            consulta.setCnsPaciente(cpf);
            consulta.setData_nascimento(dn);
            consulta.setLocal(local);


        }
        return consulta;

    }

    public List<Paciente> listPaciente() {
        List<Paciente> consultaList = new ArrayList<>();

        String buscarCpf = "SELECT * FROM " + PacienteDM.TABELA;
        Cursor cursor = read.rawQuery(buscarCpf, null);


        while (cursor.moveToNext()) {
            @SuppressLint("Range") String cpf = cursor.getString(cursor.getColumnIndex(PacienteDM.CPF));
            @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex(PacienteDM.NOME));
            @SuppressLint("Range") String dn = cursor.getString(cursor.getColumnIndex(PacienteDM.DATA_NASCIMENTO));
            @SuppressLint("Range") String sexo = cursor.getString(cursor.getColumnIndex(PacienteDM.SEXO));
            @SuppressLint("Range") String cor = cursor.getString(cursor.getColumnIndex(PacienteDM.COR));
            Paciente paciente = new Paciente();


            paciente.setCpf(cpf);
            paciente.setNome(nome);
            paciente.setData_nascimento(dn);
            paciente.setSexo(sexo);
            paciente.setCor(cor);
            consultaList.add(paciente);
        }
        return consultaList;

    }


}