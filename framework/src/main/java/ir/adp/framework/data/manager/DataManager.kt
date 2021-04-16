package ir.adp.framework.data.manager


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import ir.adp.framework.utils.parseClassToStringGSON
import ir.adp.framework.utils.parseToClassGSON


class DataManager(application: Application) {

//    val key = KeyStoreKeyGenerator.get(application, application.packageName).loadOrGenerateKeys()

    //    val sp: SharedPreferences = ObscuredPreferencesBuilder()
//    .setApplication(application)
//    .obfuscateValue(true)
//    .obfuscateKey(true)
//    .setSharePrefFileName(application.packageName)
//    .setSecret(key)
//    .createSharedPrefs()
    var sp: SharedPreferences =
        application.getSharedPreferences(application.packageName, Context.MODE_PRIVATE)

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> get(key: String, def: T): T {
        return when (def) {
            is Boolean -> sp.getBoolean(key, def) as T
            is Int -> sp.getInt(key, def) as T
            is Long -> sp.getLong(key, def) as T
            is Float -> sp.getFloat(key, def) as T
            is Double -> sp.getFloat(key, def.toFloat()).toDouble() as T
            is String -> sp.getString(key, def) as T
            else -> sp.getString(key, "").parseToClassGSON() ?: def
        }
    }

    fun put(key: String, value: Any?) {
        when (value) {
            is Boolean -> sp.edit().putBoolean(key, value).apply()
            is Int -> sp.edit().putInt(key, value).apply()
            is Long -> sp.edit().putLong(key, value).apply()
            is Float -> sp.edit().putFloat(key, value).apply()
            is Double -> sp.edit().putFloat(key, value.toFloat()).apply()
            is String -> sp.edit().putString(key, value).apply()
            else -> sp.edit().putString(key, value.parseClassToStringGSON()).apply()
        }
    }

    fun clear() {
        sp.edit().clear().apply()
    }
}