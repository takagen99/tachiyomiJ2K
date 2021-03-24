package eu.kanade.tachiyomi.ui.reader.settings

import android.content.Context
import android.util.AttributeSet
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.ui.reader.ReaderActivity
import eu.kanade.tachiyomi.util.bindToIntPreference
import eu.kanade.tachiyomi.util.bindToPreference
import eu.kanade.tachiyomi.util.view.RecyclerWindowInsetsListener
import eu.kanade.tachiyomi.util.view.visibleIf
import kotlinx.android.synthetic.main.reader_paged_layout.view.*
import kotlinx.android.synthetic.main.reader_paged_layout.view.crop_borders_webtoon
import kotlinx.android.synthetic.main.reader_paged_layout.view.settings_scroll_view
import kotlinx.android.synthetic.main.reader_paged_layout.view.webtoon_enable_zoom_out
import kotlinx.android.synthetic.main.reader_paged_layout.view.webtoon_side_padding

class ReaderPagedView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    BaseReaderSettingsView(context, attrs) {

    override fun initGeneralPreferences() {
        scale_type.bindToPreference(preferences.imageScaleType(), 1)
        zoom_start.bindToPreference(preferences.zoomStart(), 1)
        crop_borders.bindToPreference(preferences.cropBorders())
        page_transitions.bindToPreference(preferences.pageTransitions())

        settings_scroll_view.setOnApplyWindowInsetsListener(RecyclerWindowInsetsListener)
        val mangaViewer = (context as ReaderActivity).presenter.getMangaViewer()
        val isWebtoonView = mangaViewer == ReaderActivity.WEBTOON || mangaViewer == ReaderActivity.VERTICAL_PLUS
        val hasMargins = mangaViewer == ReaderActivity.VERTICAL_PLUS
        crop_borders_webtoon.bindToPreference(if (hasMargins) preferences.cropBorders() else preferences.cropBordersWebtoon())
        webtoon_side_padding.bindToIntPreference(preferences.webtoonSidePadding(), R.array.webtoon_side_padding_values)
        webtoon_enable_zoom_out.bindToPreference(preferences.webtoonEnableZoomOut())

        updatePagedGroup(!isWebtoonView)
    }

    fun updatePrefs() {
        val mangaViewer = activity.presenter.getMangaViewer()
        val isWebtoonView = mangaViewer == ReaderActivity.WEBTOON || mangaViewer == ReaderActivity.VERTICAL_PLUS
        val hasMargins = mangaViewer == ReaderActivity.VERTICAL_PLUS
        crop_borders_webtoon.bindToPreference(if (hasMargins) preferences.cropBorders() else preferences.cropBordersWebtoon())
        updatePagedGroup(!isWebtoonView)
    }

    fun updatePagedGroup(show: Boolean) {
        listOf(scale_type, zoom_start, crop_borders, page_transitions).forEach { it.visibleIf(show) }
        listOf(crop_borders_webtoon, webtoon_side_padding, webtoon_enable_zoom_out).forEach { it.visibleIf(!show) }
    }
}