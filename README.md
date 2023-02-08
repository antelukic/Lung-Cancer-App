# LungCancerApp
LungCancerApp is a student project to showcase a client application for [Machine Learning](https://azure.microsoft.com/en-us/products/machine-learning/) algorithm from [Microsoft Azure](https://azure.microsoft.com/en-us). Also, it is used to showcase simple instrumented testing using [Espresso](https://developer.android.com/training/testing/espresso). 

#### What is used:
 - [Ktor](https://ktor.io/) for HTTP Requests 
 - Clean architecture
 - Kotlin Flow
 - [Jetpack Compose](https://developer.android.com/jetpack/compose) for the UI
 - [Material 3](https://m3.material.io/)
 - [Espresso](https://developer.android.com/training/testing/espresso) for testing 
 
#### Static Analysis
 - [Detekt](https://detekt.dev/)
 - [Ktlint](https://pinterest.github.io/ktlint/)
 - [Android Lint](https://developer.android.com/studio/write/lint)
 
 #### Opening a sample in Android Studio
 Unfortunately, the `BASE_URL` and `API_KEY` are unavailable. However, you can always clone the repository using:
 
  ```
  git clone git@github.com:antelukic/Lung-Cancer-App.git
  ```
  
 and add these 2 lines to your local.properties file
 
  ``` 
  BASE_URL=" "
  API_KEY=" "
  ```
  
 #### Run Instrumented Tests
 To run tests you can simply write in terminal
  ``` 
  ./gradlew connectedAndroidTest
  ```
 #### App Preview

https://user-images.githubusercontent.com/72734166/217606647-f8ff7de1-5e24-4568-8779-83a47df500ab.mp4

