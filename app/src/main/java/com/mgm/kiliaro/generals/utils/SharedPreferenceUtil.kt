package com.mgm.kiliaro.generals.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val SHARED_MEDIA = "shared_media"

class SharedPreferenceUtil @Inject constructor(@ApplicationContext context: Context) {

    private var sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
    private var globalSharedPreferences: SharedPreferences =
        context.getSharedPreferences("global", Context.MODE_PRIVATE)

    fun saveObject(tag: String?, item: Any?) {
        if (item == null) {
            return
        }
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(item)
        editor.putString(tag, json)
        editor.apply()
    }

    fun saveString(tag: String?, string: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(tag, string)
        editor.apply()
    }

    fun getString(tag: String?, defVal: String?): String? {
        return sharedPreferences.getString(tag, defVal)
    }

    fun saveStringSet(
        tag: String?,
        string: Set<String?>?
    ) {
        val editor = sharedPreferences.edit()
        editor.putStringSet(tag, string)
        editor.apply()
    }

    fun getStringSet(
        tag: String?,
        defVal: Set<String?>?
    ): Set<String?>? {
        return sharedPreferences.getStringSet(tag, defVal)
    }

    fun saveLong(tag: String?, `val`: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(tag, `val`)
        editor.apply()
    }

    fun getLong(tag: String?, defVal: Long): Long {
        return sharedPreferences.getLong(tag, defVal)
    }

    fun saveInteger(tag: String?, `val`: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(tag, `val`)
        editor.apply()
    }

    fun getInteger(tag: String?, defVal: Int): Int {
        return sharedPreferences.getInt(tag, defVal)
    }

    fun saveFloat(tag: String?, `val`: Float) {
        val editor = sharedPreferences.edit()
        editor.putFloat(tag, `val`)
        editor.apply()
    }

    fun getFloat(tag: String?, defVal: Float): Float {
        return sharedPreferences.getFloat(tag, defVal)
    }

    fun saveBoolean(tag: String?, `val`: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(tag, `val`)
        editor.apply()
    }

    fun getBoolean(tag: String?, defVal: Boolean): Boolean {
        return sharedPreferences.getBoolean(tag, defVal)
    }

    fun saveGlobalBoolean(tag: String?, `val`: Boolean) {
        val editor = globalSharedPreferences.edit()
        editor.putBoolean(tag, `val`)
        editor.apply()
    }

    fun getGlobalBoolean(tag: String?, defVal: Boolean): Boolean {
        return globalSharedPreferences.getBoolean(tag, defVal)
    }

    fun clear(tag: String?) {
        sharedPreferences.edit().remove(tag).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    fun clearAllGlobal() {
        globalSharedPreferences.edit().clear().apply()
    }
}