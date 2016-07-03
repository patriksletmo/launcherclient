# About
Sony was the first developer to introduce a native Google Now integration into their third party launcher (https://plus.google.com/110419935293316930818/posts/gTjBE8UPz2E). Since most Android code is easily decompilable I decided to see if there was some library in use for this feature, which there was: `com.google.android.libraries.launcherclient`. This project is a manual decompilation of the library to ensure that the code is executable in a new Android app project.

If you are a Google employee and want this project removed, please contact me on my personal e-mail.

# Limitations
It is currently only possible to use this library if the app is installed as a system application or if the Google Search app has debugging set to true. Neither of these requirements can be full-filled without read/write access to `/system`.

**Please be aware that this code was originally written by Google and distributed to Sony under an unknown license. The code is likely not licensed for use by anyone but Sony and it is therefore not recommended that you use this library other than for experimental purposes.**

# Demonstration
[![Demo](https://thumbs.gfycat.com/CrazyDishonestDarklingbeetle-size_restricted.gif)](https://gfycat.com/FarawayConsiderateChinchilla)

# Usage
Please see the included example for example usage. You must install the example application as a system application by moving the built apk to `/system/app/`.

# Decompiling
I prefer decompiling Android applications using apktool (http://ibotpeaches.github.io/Apktool/). The program is really simple to use and enables the user to see the code represented as [smali](https://github.com/JesusFreke/smali).

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
