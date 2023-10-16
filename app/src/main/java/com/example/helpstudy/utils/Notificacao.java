package com.example.helpstudy.utils;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.helpstudy.R;
import com.example.helpstudy.view.TelaLogin;

public class Notificacao {

    private Context context;

    public Notificacao(Context context) {
        this.context = context;
    }

    public void notificar(String titulo, String mensagem, int id){
        Intent it = new Intent(context, TelaLogin.class);

        PendingIntent pi = PendingIntent.getActivity(context, id, it, PendingIntent.FLAG_IMMUTABLE |PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(context).setContentTitle(titulo)
                .setContentText(mensagem).setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pi).setChannelId("Canal_1").build();
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(new NotificationChannel("Canal_1","HelpStudy", NotificationManager.IMPORTANCE_LOW));
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(id, notification);
    }
}
