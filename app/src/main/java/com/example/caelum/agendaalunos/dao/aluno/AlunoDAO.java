package com.example.caelum.agendaalunos.dao.aluno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.caelum.agendaalunos.domain.aluno.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android5843 on 08/12/15.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    private static final String TABLE = "alunos";
    private static final String DATABASE = "CAELUM";
    private static final int VERSION = 2;

    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE ").append(TABLE).append("(")
               .append("id INTEGER PRIMARY KEY, ")
               .append("nome TEXT NOT NULL, ")
               .append("telefone TEXT, ")
               .append("endereco TEXT, ")
               .append("site TEXT, ")
               .append("nota REAL)");

        db.execSQL(builder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public long insertOrUpdate(Aluno aluno) {
        return (aluno != null && aluno.getId() == null) ? insert(aluno) : update(aluno);
    }

    public long insert(Aluno aluno) {
        ContentValues data = toContentValue(aluno);
        if(data == null) {
            throw new NullPointerException("Instancia de aluno nao deve ser nula.");
        }

        return getWritableDatabase().insert(TABLE, null, data);
    }

    public long update(Aluno aluno) {
        ContentValues data = toContentValue(aluno);
        if(data == null || aluno.getId() == null) {
            throw new NullPointerException("Instancia de aluno nao deve ser nula.");
        }

        String[] ids = { String.valueOf(aluno.getId()) };
        getWritableDatabase().update(TABLE, data, "id = ?", ids);

        return aluno.getId();
    }

    private ContentValues toContentValue(Aluno aluno){
        if(aluno == null) {
            return null;
        }

        ContentValues data = new ContentValues();

        data.put("nome", aluno.getNome());
        data.put("telefone", aluno.getTelefone());
        data.put("endereco", aluno.getEndereco());
        data.put("site", aluno.getSite());
        data.put("nota", aluno.getNota());

        return data;
    }

    public List<Aluno> list() {
        List<Aluno> alunos = new ArrayList<>();
        Aluno auxiliar = null;

        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + TABLE, null);

        while(cursor.moveToNext()) {
            auxiliar = new Aluno();

            auxiliar.setId(cursor.getLong(cursor.getColumnIndex("id")));
            auxiliar.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            auxiliar.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            auxiliar.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            auxiliar.setSite(cursor.getString(cursor.getColumnIndex("site")));
            auxiliar.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));

            alunos.add(auxiliar);
        }

        return alunos;
    }

    public void delete(Aluno aluno) {
        if(aluno == null || aluno.getId() == null) {
            throw new NullPointerException("Id não deve ser nulo!");
        }

        String[] ids = { String.valueOf(aluno.getId()) };
        getWritableDatabase().delete(TABLE, "id = ?", ids);
    }
}
