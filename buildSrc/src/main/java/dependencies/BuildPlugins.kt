package dependencies

object BuildPlugins {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}"
    const val GRADLE = "com.android.tools.build:gradle:${PluginsVersions.GRADLE}"
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.DETEKT}"
}
