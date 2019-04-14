package birth.h3.app.curl_kusegeapp.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule() {
    @Provides
    fun provideChildFragmentManager(fragment: Fragment) = fragment.childFragmentManager
}
