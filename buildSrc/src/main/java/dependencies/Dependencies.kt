package dependencies

object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${PluginsVersions.KOTLIN}"
    const val KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"

    object Android {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    }
}