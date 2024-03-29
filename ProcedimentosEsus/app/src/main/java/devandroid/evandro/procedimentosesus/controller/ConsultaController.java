package devandroid.evandro.procedimentosesus.controller;

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

    /**
     * Incluir dados no banco de dados
     *
     * @return
     */
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

    /**
     * buscar todos os cpf  no banco de dados que foram atendidos no dia atual
     *
     * @return
     */
    public List<Consulta> getTodoCpfDaDataAtual(String data, String turno) {

        List<Consulta> consultaList = new ArrayList<>();

        String buscarCpf = "SELECT DISTINCT " + ConsultaDM.FKCPF + " FROM " +
                ConsultaDM.TABELA + " WHERE " + ConsultaDM.DATA + " = " + "'" + data + "'" + " AND " + ConsultaDM.TURNO + " = " + "'" + turno + "'";
        Cursor cursor = read.rawQuery(buscarCpf, null);


        while (cursor.moveToNext()) {
            Consulta consulta = new Consulta();
            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.FKCPF));
            consulta.setCnsPaciente(cpf);
            consultaList.add(consulta);

        }
        return consultaList;

    }

    /**
     * retornar todos dados dos atendimentos relacionado a um cpf na data atual
     *
     * @return
     */
    public Consulta getTodosProcedimentoPorPaciente(String cpf1, String sTurno) {

        //"SELECT  DISTINCT fkcpfPaciente, datanascimento,sexo,turno, data, local FROM consulta INNER JOIN paciente ON fkcpfPaciente = cpf where fkcpfPaciente = '1' and  turno  ='noite'";

        String buscarCpf = "SELECT  " + ConsultaDM.FKCPF + ", " + ConsultaDM.TURNO + ", " + ConsultaDM.DATA + ", " + PacienteDM.DATA_NASCIMENTO + "," + PacienteDM.SEXO + ", " + ConsultaDM.LOCAL +
                " FROM " + ConsultaDM.TABELA + " INNER JOIN " + PacienteDM.TABELA + " ON " + ConsultaDM.FKCPF + " = " + PacienteDM.CPF + " WHERE  "
                + ConsultaDM.FKCPF + " = " + "'" + cpf1 + "'" + " AND " + ConsultaDM.TURNO + " = " + "'" + sTurno + "'";
        Cursor cursor = read.rawQuery(buscarCpf, null);

        Consulta consulta = new Consulta();
        ;
        Log.i("Procedimentos", "CPF " + buscarCpf);

        while (cursor.moveToNext()) {

            Paciente paciente = new Paciente();
            String data = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.DATA));
            String turno = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.TURNO));
            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.FKCPF));
            String dn = cursor.getString(cursor.getColumnIndexOrThrow(PacienteDM.DATA_NASCIMENTO));
            String sexo = cursor.getString(cursor.getColumnIndexOrThrow(PacienteDM.SEXO));
            String local = cursor.getString(cursor.getColumnIndexOrThrow(ConsultaDM.LOCAL));


            consulta.setData(data);

            consulta.setTurno(turno);
            consulta.setCnsPaciente(cpf);
            paciente.setData_nascimento(dn);
            consulta.setData_nascimento(paciente.getData_nascimento());
            paciente.setSexo(sexo);
            consulta.setSexo(paciente.getSexo());
            consulta.setLocal(local);
            consulta.setProcedimentos(listProcedimentos(cpf, data, turno));


        }

        return consulta;

    }

    /**
     * retornar todos os procedimentos relacionado ao um cpf
     *
     * @return
     */
    private String listProcedimentos(String cpfs, String data, String turno) {


        String buscarCpf = "SELECT " + ConsultaDM.PROCEDIMENTO + " FROM " + ConsultaDM.TABELA + " WHERE " + ConsultaDM.FKCPF + "=" + "'" + cpfs + "'" + " AND " + ConsultaDM.DATA + " = " + "'" + data + "'" + " AND " + ConsultaDM.TURNO + " = " + "'" + turno + "'";
        ;
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