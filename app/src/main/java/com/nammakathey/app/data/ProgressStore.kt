package com.nammakathey.app.data

import android.content.Context

class ProgressStore(context: Context) {
    private val preferences = context.getSharedPreferences("namma_kathey_progress", Context.MODE_PRIVATE)

    fun completedBadges(): Set<String> =
        preferences.getStringSet(KEY_BADGES, emptySet()).orEmpty()

    fun saveBadge(badgeId: String) {
        val updated = completedBadges().toMutableSet().apply { add(badgeId) }
        preferences.edit().putStringSet(KEY_BADGES, updated).apply()
    }

    private companion object {
        const val KEY_BADGES = "badges"
    }
}
