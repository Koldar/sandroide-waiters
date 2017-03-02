SandroidE Waiters
=================

A working code example for sandroide framework

Introduction
============

A SAndroidE official setup procedure can be found at
https://github.com/SAndroidEOfficial/framework/wiki/Preparing-your-app-for-SAndroidE

How to setup the emulator
=========================

In order to develop this project via android studio you can do the following:

 -  You may download SAndroidE latest release and copy the *.xml inside the "sdcard" and install the apk
    (https://github.com/SAndroidEOfficial/framework/releases/download/v0.4-beta-gio/sandroide-bin-v0.4-beta.zip);
 -  A copy of the files are available in "SandroidE" folder;
 -  During the writing of this guide I couldn't open Android Studio DDMS (a graphical view allowing you to trasnfer data to external device);
 -  as stated in http://alvinalexander.com/android/how-copy-files-android-device-emulator-adb-push-read-only-error,
    this **might** be caused by a read-only mounted sdcard; You can solve that using adb:

        adb.exe shell
        su
        mount -o rw,remount rootfs /
        <exit from su Ctrl+D>
        <exit from adb Ctrl+D>

 -  After this operation you might upload the *.xml and *.apk via the following command:

        adb.exe push "E:\Users\massi\Documents\git\SAndroidE-Waiters\SAndroidE\bledataclustermodels.apk" "/sdcard/"
        adb.exe push "E:\Users\massi\Documents\git\SAndroidE-Waiters\SAndroidE\bledeviceparsers.apk" "/sdcard/"
        adb.exe push "E:\Users\massi\Documents\git\SAndroidE-Waiters\SAndroidE\devices.xml" "/sdcard/"

        adb.exe push "E:\Users\massi\Documents\git\SAndroidE-Waiters\SAndroidE\flasher-debug.apk" "/sdcard/"

    The terminal will alert you if the operations are successful or not (the path may be relative);
    on Windows systems the 'adb.exe' can be found on path like 'E:\Users\massi\AppData\Local\Android\sdk1\platform-tools'

 -  Now you should install the SAndroidE apk:

        adb.exe install "E:\Users\massi\Documents\git\SAndroidE-Waiters\SAndroidE\flasher-debug.apk"

Ok, now you **should** ready to develop with SAndroidE.

Your first application
======================

The project has already been setup. However you can also follow the useful guide shown in
https://github.com/SAndroidEOfficial/framework/wiki/Preparing-your-app-for-SAndroidE.

As shown in http://stackoverflow.com/a/22604347/1887602, you can't emulate the bluetooth device.
In order to successfully test the application, you need to use an actual phone. Use it only when you need to actually
test the bluetooth connection.

Knowledgable people
===================

Further information about SAndroidE framework can be fetched from:
 -  Francesco Bonfadelli (gdg brescia);
 -  Giovanni;
