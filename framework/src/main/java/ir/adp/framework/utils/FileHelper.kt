package ir.adp.framework.utils

import android.content.Context
import java.io.File

/**
 * Created by Ali on 10/8/2019.
 */

object FileHelper

fun clearCache(context: Context) {
    try {
        val dir = context.cacheDir
        deleteDir(dir)
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun deleteDir(dir: File?): Boolean {
    if (dir != null && dir.isDirectory) {
        val children = dir.list()
        for (i in children.indices) {
            val success = deleteDir(File(dir, children[i]))
            if (!success) {
                return false
            }
        }
        return dir.delete()
    } else return if (dir != null && dir.isFile) {
        dir.delete()
    } else {
        false
    }
}