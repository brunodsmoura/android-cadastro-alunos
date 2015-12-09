package com.example.caelum.agendaalunos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.caelum.agendaalunos.R;
import com.example.caelum.agendaalunos.dao.aluno.AlunoDAO;

/**
 * Created by android5843 on 09/12/15.
 */
public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        if(pdus == null) {
            return;
        }

        Toast.makeText(context, "Chegou um SMS!!!", Toast.LENGTH_LONG).show();

        byte[] pdu = (byte[]) pdus[0];
        SmsMessage sms = SmsMessage.createFromPdu(pdu);

        String phoneNumber = sms.getOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);

        if(dao.isAluno(phoneNumber)) {
            MediaPlayer player = MediaPlayer.create(context, R.raw.msg);
            player.start();

            Toast.makeText(context, "Chegou um SMS de aluno!!!", Toast.LENGTH_LONG).show();
        }

        dao.close();
    }
}
