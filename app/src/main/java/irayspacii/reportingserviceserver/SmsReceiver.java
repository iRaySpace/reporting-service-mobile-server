package irayspacii.reportingserviceserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by ivanray on 12/14/17.
 */

public class SmsReceiver extends BroadcastReceiver {

    public static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle data = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for(int i = 0; i < pdus.length; i++) {

            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            mListener.messageReceived(smsMessage.getDisplayMessageBody());

        }

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }

}
