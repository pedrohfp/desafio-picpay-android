package dependencies

object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${PluginsVersions.KOTLIN}"
    const val KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val KTLINT = "com.pinterest:ktlint:${Versions.KTLINT}"
    const val KOIN = "org.koin:koin-android:${Versions.KOIN}"

    object Android {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    }

    object Koin {
        const val CORE = "org.koin:koin-android:${Versions.KOIN}"
        const val SCOPE = "org.koin:koin-androidx-scope:${Versions.KOIN}"
        const val VIEWMODEL = "org.koin:koin-androidx-viewmodel:${Versions.KOIN}"
    }
}