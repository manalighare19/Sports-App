# Sports App

## Build tools & versions used
- Gradle: 7.4.1
- Compile/Target SDK: 33
- Min SDK: 24
- Android Studio Version: 2022.1.1 Patch 1

## Steps to run the app
1. git clone
2. Open project in Android Studio
3. Sync Gradle and build project
4. Create "secrets.properties" in the root folder of the project. Add  `SPORTS_DB_API_KEY`  in the following format:  `SPORTS_DB_API_KEY = "YOUR_SPORTS_DB_API_KEY"`
5. Run project on Android emulator or Android device

## Area of focus
I chose to prioritize the architecture and structure of my application by implementing the MVVM design pattern.   
Employing MVVM architecture in this instance results in an app that is more maintainable, extensible, and easier to test. It provides a clear separation of concerns, enables easier unit testing, and facilitates code reuse across multiple views and screens.

## Enhancements
1. If given more time, I would focus on improving the user interface of the app to enhance the overall user experience.
2. Analytics services like Firebase analytics could be used to track user behavior for better recommendations. For example, if we want to track when a user performs a search, views player details, or interacts with certain UI elements. We can implement event tracking code in the appropriate places to send relevant data to the analytics platform.
3. In addition to events, we may want to track screen views to understand how users navigate through the app. We can add Firebase analytics methods to track screen views, which can call when a new screen or fragment is displayed.

##  Dependencies used
These libraries are widely used in the developers community and being continuously maintained and improved.
Also, these libraries provide great Kotlin support which makes it easy to use for a Kotlin heavy app
- [Moshi](https://github.com/square/moshi) 1.14.0
- [Retrofit](https://square.github.io/retrofit/) 2.9.0
- [Glide](https://github.com/bumptech/glide) 4.13.2
- [Mockito](https://github.com/mockito/mockito) 4.1.0
- [Hilt](https://dagger.dev/hilt/) 2.44
- [Google Icons](https://fonts.google.com/icons)
- [Iconscout](https://iconscout.com/icons/)

## Other
I enjoyed working on this take-home case study.  Thanks for reviewing and I look forward to next steps.


## App In Action
| <img src = "https://github.com/manalighare19/Sports-App/assets/43833000/00a8b1d6-2699-4095-8811-fd39e3993a1f" width = "200" height = "400" /> | <img src = "https://github.com/manalighare19/Sports-App/assets/43833000/b61b9ff3-b8f8-496b-a58c-9dc557decf5d" width = "200" height = "400" /> | <img src = "https://github.com/manalighare19/Sports-App/assets/43833000/4397a66d-8fd3-4db0-b6bb-e7c20d64cfac" width = "200" height = "400" /> | <img src = "https://github.com/manalighare19/Sports-App/assets/43833000/06cbcf67-3f8c-446d-ba35-764c2aeae7f6" width = "200" height = "400" />
|--|--|--|--|
