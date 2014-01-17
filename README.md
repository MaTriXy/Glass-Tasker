Glass-Tasker
============
Glass Tasker allows you to run tasks in [Tasker]( https://play.google.com/store/apps/details?id=net.dinglisch.android.taskerm) on your phone just by speaking to Glass.  Say "ok glass...run task" to open the Glass Tasker voice recognition window.  This is where you say the name of the task to run.  Once recognition is done, the task name your spoke will be sent to your phone where it will ask Tasker to run it.

IMPORTANT NOTES: 
* You will need to enable the “Allow External Access” setting enabled for Glass-Tasker to trigger tasks in Tasker.
* Tasker’s tasks are case sensitive.  Glass-Tasker will try to launch tasks as you see then recognize in Glass AND will also try to run the task where the first letter of each word is capitalized.  The reason for the latter is the Tasker by default will capitalize the first letter of the task as you enter the name in Tasker.

Install Instructions
===================
1. Download and install [IntentTunnel](https://github.com/TheMasterBaron/Glass-IntentTunnel/).  Check the readme on how to install IntentTunnel on both your phone and Glass.

2. Download the Glass-Tasker APK [here](https://github.com/TheMasterBaron/Glass-Tasker/blob/master/apk/GlassTasker-debug-unaligned-0.0.1.apk?raw=true).  The same APK is for the phone and Glass

3. Intall the APK on Glass using this command:
<code>adb install -r GlassTasker-debug-unaligned.apk</code>

4. Install the APK on your phone.

To use GlassTasker, say "ok glass" and then "run task".  Next speak the name of the task your wish to run.
