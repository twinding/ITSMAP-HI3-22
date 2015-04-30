package dk.itsmap.group22.itsmap_hi3_22;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

import java.util.Calendar;

public class AlarmService extends IntentService {

    public static final String ALARM_MESSAGE = "dk.itsmap.group22.itsmap_hi3_22.alarm_message";

    private Runnable updateTask;
    private Handler handler = new Handler();

    public AlarmService() { super("AlarmService"); }

    @Override
    protected void onHandleIntent(Intent intent) {
        final int delay = intent.getIntExtra("data", 1);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.SECOND, delay);

        Intent newIntent = new Intent(this, AlarmReceiver.class);
        newIntent.putExtra("message", intent.getExtras().getString("message"));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, newIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

        updateTask = new Runnable() {
            int repetitions = delay;

            @Override
            public void run() {
                if (repetitions == 0) return;
                Intent messageIntent = new Intent(ALARM_MESSAGE);
                messageIntent.putExtra("message", getResources().getString(R.string.alarmMessage) + Integer.toString(repetitions));
                LocalBroadcastManager.getInstance(AlarmService.this).sendBroadcast(messageIntent);
                repetitions--;
                handler.postDelayed(updateTask, 1000);
            }
        };
        handler.post(updateTask);
    }
}
