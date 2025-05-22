package com.example.petcareapp.data.local

import android.content.Context

class UserPrefs(context: Context) {
    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveAccessToken(token: String) {
        prefs.edit().putString("access_token", token).apply()
    }

    fun getAccessToken(): String? {
        return prefs.getString("access_token", null)
    }

    fun saveCurrentUserId(userId: String) {
        prefs.edit().putString("current_user_id", userId).apply()
    }

    fun getCurrentUserId(): String? {
        return prefs.getString("current_user_id", null)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}
