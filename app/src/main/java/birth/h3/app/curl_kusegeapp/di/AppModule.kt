package birth.h3.app.curl_kusegeapp.di

import android.content.Context
import androidx.fragment.app.FragmentManager
import birth.h3.app.curl_kusegeapp.ui.util.UtilIcon
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context{
        return context
    }

    @Singleton
    @Provides
    fun provideUtilIcon(): UtilIcon {
        return UtilIcon(context)
    }
}
