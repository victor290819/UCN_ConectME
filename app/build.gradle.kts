plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.ucn_conectme"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.ucn_conectme"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true;
    }
}

dependencies {
    //noinspection GradleCompatible,GradleCompatible,GradleCompatible,GradleCompatible


    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.google.firebase:firebase-messaging:23.3.0")
    implementation ("com.google.firebase:firebase-firestore:24.0.0")
    implementation ("com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava")
    implementation ("com.firebaseui:firebase-ui-auth:7.2.0")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.databinding:compiler:3.2.0-alpha11")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}