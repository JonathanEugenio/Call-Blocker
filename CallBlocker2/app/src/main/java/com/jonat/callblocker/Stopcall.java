package com.jonat.callblocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;
import com.jonat.callblocker.database.dao.TelefoneDAO;
import com.jonat.callblocker.database.entities.Telefone;

import java.lang.reflect.Method;
import java.util.List;

public class Stopcall extends BroadcastReceiver {

    private String number;


    @Override
    public void onReceive(Context context, Intent intent) {



        if(!intent.getAction().equals("android.intent.action.PHONE_STATE")){
            return;
        }else{
            number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(TelefoneDAO.getInstance(context).hasTelephone(number)){
                disconnectPhoneItelephony(context);
            }
        }
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void disconnectPhoneItelephony(Context context) {
        ITelephony telephonyService;
        TelephonyManager telephony = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Class c = Class.forName(telephony.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            telephonyService = (ITelephony) m.invoke(telephony);
            telephonyService.endCall();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
