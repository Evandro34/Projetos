package devandroid.evandro.procedimentosesus.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
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



        while (cursor.moveToNext()) {

            String data = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.DATA));
            String turno = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.TURNO));
            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.FKCPF));
            //String dn = cursor.getString(cursor.getColumnIndexOrThrow(PacienteDM.DATA_NASCIMENTO));
            String local = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.LOCAL));
            // @SuppressLint("Range") String procedimento = cursor.getString(cursor.getColumnIndex(ConsultaDM.PROCEDIMENTO));

            Consulta consulta = new Consulta();
            consulta.setData(data);
            Log.i("Procedimentos", "CPF " + data);
            consulta.setTurno(turno);
            Log.i("Procedimentos", "CPF " + turno);
            consulta.setCnsPaciente(cpf);
            Log.i("Procedimentos", "CPF " + cpf);
            //consulta.setData_nascimento(dn);
            consulta.setLocal(local);
            Log.i("Procedimentos", "CPF " + local);
            // consulta.setProcedimentos(procedimento);

            consultaList.add(consulta);
        }
        return consultaList;

    }

    public Consulta getTODOS(String cpf1) {

        //"SELECT  DISTINCT fkcpfPaciente, datanascimento,sexo,turno, data, local FROM consulta INNER JOIN paciente ON fkcpfPaciente = cpf where fkcpfPaciente = '1' and  turno  ='noite'";

        String buscarCpf = "SELECT  " + ConsultaDM.FKCPF + ", " + ConsultaDM.TURNO + ", " + ConsultaDM.DATA + ", "  + PacienteDM.DATA_NASCIMENTO + ", "+ ConsultaDM.LOCAL +
                " FROM " + ConsultaDM.TABELA + " INNER JOIN " + PacienteDM.TABELA + " ON " + ConsultaDM.FKCPF + " = " + PacienteDM.CPF +" WHERE  "
                + ConsultaDM.FKCPF +" = "+"'" + cpf1 + "'"+" AND "+ ConsultaDM.TURNO +" = "+" 'noite' " ;
        Cursor cursor = read.rawQuery(buscarCpf, null);

        Consulta consulta = new Consulta();;
        Log.i("Procedimentos", "CPF " + buscarCpf);

        while (cursor.moveToNext()) {

            Paciente paciente = new Paciente();
            String data = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.DATA));
            String turno = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.TURNO));
            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.FKCPF));
            String dn = cursor.getString(cursor.getColumnIndexOrThrow(PacienteDM.DATA_NASCIMENTO));
            String local = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.LOCAL));


            consulta.setData(data);

            consulta.setTurno(turno);
            consulta.setCnsPaciente(cpf);
            paciente.setData_nascimento(dn);
            consulta.setData_nascimento(paciente.getData_nascimento());
            consulta.setLocal(local);
            consulta.setProcedimentos(listProcediemntos(cpf,data));


        }
        return  consulta;

    }

    public Consulta  getProcedimento(String cpf1) {
        String buscarCpf = "SELECT " + ConsultaDM.PROCEDIMENTO + " FROM " + ConsultaDM.TABELA + " WHERE " + ConsultaDM.FKCPF + " = " + "'" + cpf1 + "'" + " AND " + ConsultaDM.TURNO + " = 'noite' ";
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

    public String  listProcediemntos(String cpfs,String data) {


        String buscarCpf = "SELECT "+ConsultaDM.PROCEDIMENTO+ " FROM " + ConsultaDM.TABELA +" WHERE "+ConsultaDM.FKCPF +"=" +"'"+cpfs+"'" +" AND "+ConsultaDM.DATA +" = "+"'"+data+"'";
        Cursor cursor = read.rawQuery(buscarCpf, null);

        String procedimentos="";
        while (cursor.moveToNext()) {
            String procedimento = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.PROCEDIMENTO));

            procedimentos = procedimentos  +procedimento+"\n";



        }
        return procedimentos;

    }


}