package dk.itsmap.group22.itsmap_hi3_22;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class AlarmService extends IntentService {

    private static final String ALARM_MESSAGE = "alarm_message";
    private static final String TAG = "AlarmServiceTest";

    public AlarmService() { super("AlarmService"); }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "AlarmService onHandleIntent");
        int delay = intent.getIntExtra("data", 1);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.SECOND, delay);

        Intent newIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, newIntent, 0);

        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

    }
}
