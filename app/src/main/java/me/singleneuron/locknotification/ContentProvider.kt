package me.singleneuron.locknotification

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.Keep
import androidx.annotation.RequiresApi
import androidx.annotation.StringDef
import me.singleneuron.locknotification.Utils.GeneralUtils
import me.singleneuron.locknotification.Utils.LogUtils
import org.json.JSONObject
import java.io.File

@Keep
class ContentProvider : ContentProvider() {

    companion object {
        const val CONTENT_PROVIDER_JSON: String = "content_provider_json"
        //const val CONTENT_PROVIDER_PREFERENCE: String = "content_provider_preference"
        //const val CONTENT_PROVIDER_DEVICE_PROTECTED_PREFERENCE: String = "content_provider_device_protected_preference"
        const val BUNDLE_KEY_JSON_STRING: String = "bundle_key_json_string"
        const val CONTENT_PROVIDER_COMMIT: String = "content_provider_commit"
    }

    override fun call(@ContentProviderParams method: String, arg: String?, extras: Bundle?): Bundle? {
        //Log.d("LockNotification", "ContentProvider is called")
        val bundle = Bundle()
        if (method == CONTENT_PROVIDER_COMMIT) {
            LogUtils.addLog(arg!!, context!!)
            return Bundle().apply {
                putBoolean("success", true)
            }
        }
        if (method == CONTENT_PROVIDER_JSON) {
            bundle.putString(BUNDLE_KEY_JSON_STRING, File(context!!.filesDir.absolutePath + File.separator + "config.json").readText())
        } /*else {
            val mSharedPreferences: SharedPreferences = when (method) {
                CONTENT_PROVIDER_PREFERENCE -> GeneralUtils.getSharedPreferenceOnUI(context)
                CONTENT_PROVIDER_DEVICE_PROTECTED_PREFERENCE -> context!!.createDeviceProtectedStorageContext().getSharedPreferences("deviceProtected", Context.MODE_PRIVATE);
                else -> throw IllegalArgumentException()
            }

            bundle.putString(BUNDLE_KEY_JSON_STRING, JSONObject(mSharedPreferences.all).toString())

        }*/
        return bundle
    }

    override fun onCreate(): Boolean {
        return true
    }

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(/*CONTENT_PROVIDER_DEVICE_PROTECTED_PREFERENCE, CONTENT_PROVIDER_PREFERENCE,*/ CONTENT_PROVIDER_JSON, CONTENT_PROVIDER_COMMIT)
    @Target(AnnotationTarget.VALUE_PARAMETER)
    annotation class ContentProviderParams

    @Deprecated("NoSuchMethod")
    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        throw NoSuchMethodException()
    }

    @Deprecated("NoSuchMethod")
    override fun getType(uri: Uri): String? {
        throw NoSuchMethodException()
    }

    @Deprecated("NoSuchMethod")
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw NoSuchMethodException()
    }

    @Deprecated("NoSuchMethod")
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        throw NoSuchMethodException()
    }

    @Deprecated("NoSuchMethod")
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw NoSuchMethodException()
    }

}