# SpaceX-App (work-in-progress)

SpaceX-App is a sample Android app which uses Kotlin Coroutines & the Kotlin Flow API to implement an MVI architecture.

It uses the [SpaceX API](https://github.com/r-spacex/SpaceX-API) to fetch data.

## Tech-stack

<img src="misc/image/application_anim.gif" width="156" align="right" hspace="20">

* Entirely written in [Kotlin](https://kotlinlang.org/)
* [Kotlin Coroutines & Flow](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html) throughout
* Many of the [Architecture Components](https://developer.android.com/topic/libraries/architecture/): Room, LiveData, Navigation, ViewModel
* [Dagger Hilt](https://dagger.dev/hilt/) for dependency injection
* Architecture
  * MVVM + MVI based on Kotlin Coroutines Flow
  * Modularization by feature
  * Repository pattern
* [Retrofit2](https://square.github.io/retrofit/), [Moshi](https://github.com/square/moshi), [Coil](https://github.com/coil-kt/coil) and much more...

SpaceX-App is a **work-in-progress**. The interface of the app utilizes some of the modern material design components, however, is deliberately kept simple to focus on application architecture.
