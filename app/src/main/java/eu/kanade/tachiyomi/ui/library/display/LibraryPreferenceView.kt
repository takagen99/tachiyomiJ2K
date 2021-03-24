package eu.kanade.tachiyomi.ui.library.display

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.f2prateek.rx.preferences.Preference
import eu.kanade.tachiyomi.data.preference.PreferencesHelper
import eu.kanade.tachiyomi.data.preference.getOrDefault
import eu.kanade.tachiyomi.ui.library.LibraryController
import uy.kohesive.injekt.injectLazy

abstract class LibraryPreferenceView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    internal val preferences by injectLazy<PreferencesHelper>()
    lateinit var controller: LibraryController

    abstract fun initGeneralPreferences()

    override fun onFinishInflate() {
        super.onFinishInflate()
        initGeneralPreferences()
    }
}