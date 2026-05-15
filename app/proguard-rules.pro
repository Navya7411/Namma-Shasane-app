# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep data model classes
-keep class com.namma.shasane.model.** { *; }

# Keep ViewBinding
-keep class com.namma.shasane.databinding.** { *; }

# Material components
-keep class com.google.android.material.** { *; }

# Navigation component
-keep class androidx.navigation.** { *; }
