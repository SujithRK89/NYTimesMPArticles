# NY Times Most Popular #

This README would normally document whatever steps are necessary to get NY Times Most Popular application up and running.

### About the application ###

This application is only for showcasing the coding standard and best practices of android development.
App will have two screens, landing screen will be listing of most popular feeds from api and a detail screen.


![Screenshot_1630621594](https://user-images.githubusercontent.com/64698918/131924185-9ca35d31-a6cd-44f9-95c4-120d59560534.png)
![Screenshot_1630621597](https://user-images.githubusercontent.com/64698918/131924193-9179eeaf-22a0-48d4-bc20-7eeed0025b0b.png)


### Tools and Technology used ###

* Android Studio (IDE)
* Kotlin (Language)
* GIT (Version control)
* MVVM (Architecture)
* DataBinding (Binding data to view)
* Navigation Graph (Navigation)
* Retrofit, Okhttp, Gson, Gson convertor (Network api call)
* Hilt (Dependency Injection)
* Glide (Image loading)
* Coroutine
* LiveData etc

### Imp Project Structure ###

* app (will have the application class)
* data (To hold the data and all the classes and files depends to data like model, repository etc)
* di (dependency module)
* handler (interfaces)
* ui (all ui related files)
* utils (all supporting files)
* res (all the ui related resource files like layout, drawable, theme etc)


### What is this repository for? ###

* Configure the project and run the application
* Run the unit test cases manually
* Run the test coverage

### Installation ###
Clone this repository and import into Android Studio

```bash
git clone https://github.com/SujithRK89/NYTimesMPArticles.git
```

* Directly clone from Android Studio. File -> New -> project from version control -> copy and past the repository from github -> clone

### How to build the project ###

* clone or import the project into android studio and run the project in a device or emulator.
* clone or import the project into android studio and build the apk from BUILD -> Build bundle or apk -> build apk, result apk will be in app/build/outputs/apk/app-debug.apk.
* Build the project from the command-line

  To build a debug version of NY Times Most Popular Android application, you can run ./gradlew assembleDebug from the root of repository. In a default project setup, the resulting apk can then be found in app/build/outputs/apk/app-debug.apk.

### How to run Unit test ###

* Clone the project in android studio
* open the project in android studio
* navigate /Users/sujithrk/Projects/Android/NYTimesMPArticles/app/src/androidTest/java/com/srk/nytimes/ui/mostpopular/MostPopularViewModelTest.kt, right click and press Run->(Android icon).
* From the terminal, cd into the Android project and run ./gradlew test


### Code coverage ###

* We can now get coverage (on Android Studio) by: right clicking in our project → Run Tests with Coverage. This will output our code coverage metrics.
* To run Jacoco and sonarqube code coverage run the below commands in terminal

```bash
$ sudo ./gradlew jacocoTestReport
```
