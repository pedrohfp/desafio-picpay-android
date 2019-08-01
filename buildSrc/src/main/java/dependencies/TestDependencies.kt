package dependencies

object TestDependencies {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"
    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP}"
    const val ARCH_TESTING = "androidx.arch.core:core-testing:${Versions.ARCH_COMPONENTS}"
    const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.MOCKITO_KOTLIN}"
    const val ESPRESSO_RUNNER = "androidx.test:runner:${Versions.ESPRESSO_RUNNER}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}