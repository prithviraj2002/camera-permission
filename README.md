# camera-permission

The PermissionsApp demonstrates how to handle runtime permissions in android compose. 
Camera permission is used here, hence the name.
Google accompanist library is used here.

The app works like this:
https://user-images.githubusercontent.com/82358330/185547615-7e75a848-03a7-4eb8-ba77-5e5c27a0327d.mp4

Permission dialog is prompted as:
![WhatsApp Image 2022-08-19 at 10 05 47 AM](https://user-images.githubusercontent.com/82358330/185547728-4b308cdd-7eb3-4e72-9b34-c2a48f3140df.jpeg)

The screen that appears first, has a place holder at its focus, where currently lies an icon, for the clicked image to be displayed later.
Down below is a button to open the camera, followed by the text which shows the state of the camera permission.
  1) The user is asked to enable camera permission, since it is not allowed initially.
  2) If the user denies the first time, and tries to reopen, the permission dialog is prompted again.
  3) If the permission is denied the second time, then the user will have to change the permissions in settings manually.
