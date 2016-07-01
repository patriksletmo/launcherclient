# About
Sony was the first developer to introduce a native Google Now integration into their third party launcher (https://plus.google.com/110419935293316930818/posts/gTjBE8UPz2E). Since most Android code is easily decompilable I decided to see if there was some library in use for this feature, which there was: `com.google.android.libraries.launcherclient`. This project is a manual decompilation of the library (mainly because of it's small minor size) to ensure that the code is executable in a new Android app project.

If you are a Google employee and want this project removed, please contact me on my personal e-mail.

# Decompiling
I prefer decompiling Android applications using apktool (http://ibotpeaches.github.io/Apktool/). The program is really simple to use and enables us to see the code represented as [smali](https://github.com/JesusFreke/smali).

## Steps
### 1) Find and download the apk
Download the apk file which contains the Google Now integration. If you download it through Google Play you must extract the apk from your device to your PC.

### 2) Run apktool
Install apktool and run the following command to decompile the app:

```
apktool d home.apk
```

### 3) Find the source
The source files are located in `smali/com/google/android/libraries/launcherclient/`.
