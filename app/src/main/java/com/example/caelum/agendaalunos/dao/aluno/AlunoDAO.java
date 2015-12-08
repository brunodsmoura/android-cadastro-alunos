package com.example.caelum.agendaalunos.dao.aluno;

import android.content.ContentValues;
import android.content.Context;
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
    private static final int VERSION = 1;

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

    public long insert(Aluno aluno) {
        ContentValues data = new ContentValues();

        data.put("nome", aluno.getNome());
        data.put("telefone", aluno.getTelefone());
        data.put("endereco", aluno.getEndereco());
        data.put("site", aluno.getSite());
        data.put("nota", aluno.getNota());

        getReadableDatabase().rawQuery(null, null);

        return getWritableDatabase().insert(TABLE, null, data);
    }

    public List<Aluno> list() {
        return new ArrayList<>();
    }
}