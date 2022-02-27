# :waxing_gibbous_moon: Oze Senior Android Engineer Assignment

[Download APK](https://github.com/chydee/Oze-Senior-Android-Engineer-Assignment/releases/download/debug-update/app-debug.apk)

# :scroll: Assignment instructions

Implement “Github” user’s api for Lagos developers
https://api.github.com/search/users?q=lagos&page=1 (GET)
➔ See list of users with profile pictures ➔ See default avatar if there is no profile picture ➔ Click on a profile and see more details about the profile ➔ See more pages/profiles as the user scrolls towards the end ➔ Mark profile as favorite and view from local db (Room)
➔ Remove single profile from favorite or clear all

To learn more about Github API and Implementations
https://docs.github.com/en/rest/guides/getting-started-with-the-rest-api

# :question: Tech Requirements

❖ Strictly MVVM ❖ Strictly Kotlin ❖ Dagger or Hilt ❖ Strictly RxJava ❖ Retrofit ❖ Jetpack Paging ❖ Modularization and unit test is a plus

# :scroll: Description

This assignment solution is implemented with MVVM

It is written 100% in Kotlin with both unit and integrated tests.🙂

<p align="center">
  <img src="photos/home.jpeg" alt="MAD Summary" width="20%" hspace="15"/>
  <img src="photos/fav_def.jpeg" alt="MAD Summary" width="20%" hspace="15"/>
  <img src="photos/fav_def_empty.jpeg" alt="MAD Summary" width="20%" hspace="15"/>
</p>

<p align="center">
  <img src="photos/fav_clear_all.jpeg" alt="MAD Summary" width="20%" hspace="15"/>
  <img src="photos/user_jav.jpeg" alt="MAD Summary" width="20%" hspace="15"/>
  <img src="photos/user_lie.jpeg" alt="MAD Summary" width="20%" hspace="15"/>
  <img src="photos/user_ola.jpeg" alt="MAD Summary" width="20%" hspace="15"/>
</p>

## Installation

    Follow these steps if you want to get a local copy of the project.

    Prerequisites
    Android Studio IDE 4.0+
    Android SDK v30
    Android Build Tools 30.0.0+
    1. Clone or fork the repository (Main Branch) by running the command below
    on your git terminal
    
    `git clone https://github.com/chydee/Oze-Senior-Android-Engineer-Assignment.git`
    
    2. Import the project in AndroidStudio
    In Android Studio, go to File -> New -> Import project
    Follow the dialog for set up instructions

    To run this application, please use an Android device or emulator (OS 5.0 or newer).

## Architecture

The architecture of the project follows the principles of DRY and MVVM.

<p align="center">
  <img src="photos/summary.png" alt="MAD Summary" width="60%" hspace="15"/>
</p>

## :thought_balloon: What I could have done better in attempting this test

- Handle error messages properly with more contexts
- Test end to end.
- Persist room data so I can reduce the number of network calls made.

...but there's more from where this came.