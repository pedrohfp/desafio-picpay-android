package dependencies

object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${PluginsVersions.KOTLIN}"
    const val KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val KTLINT = "com.pinterest:ktlint:${Versions.KTLINT}"
    const val KOIN = "org.koin:koin-android:${Versions.KOIN}"
    const val ARCH_COMPONENTS = "androidx.lifecycle:lifecycle-extensions:${Versions.ARCH_COMPONENTS}"
    const val PICASSO = "com.squareup.picasso:picasso:${Versions.PICASSO}"

    object Android {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW}"
    }

    object Koin {
        const val CORE = "org.koin:koin-android:${Versions.KOIN}"
        const val SCOPE = "org.koin:koin-androidx-scope:${Versions.KOIN}"
        const val VIEWMODEL = "org.koin:koin-androidx-viewmodel:${Versions.KOIN}"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
        const val LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    }
}