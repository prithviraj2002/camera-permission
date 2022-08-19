# camera-permission

The PermissionsApp demonstrates how to handle runtime permissions in android compose. 
Camera permission is used here, hence the name.
Google accompanist library is used here.

The app works like this:
https://user-images.githubusercontent.com/82358330/184865383-2ef35f4a-2868-4c75-b7db-67b3fa5332db.mp4

The screen that appears first, has a place holder at its focus, where currently lies an icon, for the clicked image to be displayed later.
Down below is a button to open the camera, followed by the text which shows the state of the camera permission.
  1) The user is asked to enable camera permission, since it is not allowed initially.
  2) If the user denies the first time, and tries to reopen, the permission dialog is prompted again.
  3) If the permission is denied the second time, then the user will have to change the permissions in settings manually.
