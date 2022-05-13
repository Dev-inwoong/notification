package kr.ac.tuk.notificationstudty

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.*

class MyService : Service() {
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent == null){
            return START_STICKY
        }

        CoroutineScope(Dispatchers.Default).launch {
            for(i in 0 .. 100) { //0 ~ 100 count
                delay(1000)
                Log.e("test", "count : $i")
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ // 빌드 버전이 오레오 8.0 이상이면
            var serviceChannel = NotificationChannel("id", "알림 설정 모드 타이틀", NotificationManager.IMPORTANCE_DEFAULT) //id,타이틀, 우선순위
            var manager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager //notification manager 선언
            manager.createNotificationChannel(serviceChannel) // notification 채널 생성
        }
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val builder1 = NotificationCompat.Builder(this, "id")
            .setSmallIcon(android.R.drawable.ic_dialog_info) //small Icon
            .setContentTitle("Notification") //Title
            .setContentText("Notification example1!") //내용
            .setAutoCancel(false) //알림 클릭시 알림 제거 여부
            .setContentIntent(pendingIntent) //클릭시 pendingIntent의 Activity로 이동
            .setGroup("group_key_notify")
            .addAction(android.R.drawable.sym_action_chat,"OPEN", pendingIntent) //액션

        val builder2 = NotificationCompat.Builder(this, "id")
            .setSmallIcon(android.R.drawable.ic_dialog_info) //small Icon
            .setContentTitle("Notification") //Title
            .setContentText("Notification example2!") //내용
            .setAutoCancel(true) //알림 클릭시 알림 제거 여부
            .setContentIntent(pendingIntent) //클릭시 pendingIntent의 Activity로 이동
            .setGroup("group_key_notify")

        val builder3 = NotificationCompat.Builder(this, "id")
            .setSmallIcon(android.R.drawable.ic_dialog_info) //small Icon
            .setContentTitle("Notification") //Title
            .setContentText("Notification example3!") //내용
            .setAutoCancel(true) //알림 클릭시 알림 제거 여부
            .setContentIntent(pendingIntent) //클릭시 pendingIntent의 Activity로 이동
            .setGroup("group_key_notify")

        val buildersummary = NotificationCompat.Builder(this, "id")
            .setSmallIcon(android.R.drawable.ic_dialog_info) //small Icon
            .setContentTitle("Notification") //Title
            .setContentText("Notification summary") //내용
            .setAutoCancel(true) //알림 클릭시 알림 제거 여부
            .setContentIntent(pendingIntent) //클릭시 pendingIntent의 Activity로 이동
            .setGroup("group_key_notify") //빌더 그룹으로 묶기
            .setGroupSummary(true) // 그룹 요약

        val notificationId1 = 100
        val notificationId2 = 101
        val notificationId3 = 102
        val notificationId4 = 103

        NotificationManagerCompat.from(this).apply{
            notify(notificationId1, builder1.build())
            notify(notificationId2, builder2.build())
            notify(notificationId3, builder3.build())
            notify(notificationId4, buildersummary.build())
        }
        //https://huiung.tistory.com/169
        //https://qwerty-ojjj.tistory.com/37


        return super.onStartCommand(intent, flags, startId)
    }
}