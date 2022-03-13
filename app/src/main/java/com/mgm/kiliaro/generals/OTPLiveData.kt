package com.mgm.kiliaro.generals

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.LiveData

import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

//get otp from sms
class OTPLiveData @Inject constructor(@ActivityContext private val context: Context) :
    LiveData<String>() {


    private var smsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("SMS_RECEIVER", " ----------------> OnReceive")
            /*if (SmsRetriever.SMS_RETRIEVED_ACTION == intent?.action) {
                Log.d("SMS_RECEIVER", " ----------------> On Receive There's Action")
                val extras = intent.extras
                val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status?
                when (status?.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        var otp: String = extras?.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                        Log.d(
                            "SMS_RECEIVER", " ----------------> " +
                                    otp
                        )
                        otp = otp
                            .replace("Your verification code is: ", "")
                            .replace("Your verification reset password code is: ", "")
                            .split("\n".toRegex())
                            .dropLastWhile { it.isEmpty() }.toTypedArray()[0]

                        Log.d(
                            "SMS_RECEIVER", " ----------------> " +
                                    otp
                        )
                        value = otp

                    }
                    CommonStatusCodes.TIMEOUT -> {
                        Log.e("SMS_RECEIVER", " ----------------> TIME OUT")
                    }
                }
            }*/

        }
    }

    override fun onActive() {
        super.onActive()
//        context.registerReceiver(smsReceiver, IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION))
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(smsReceiver)
    }
}