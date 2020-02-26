# Bikes _Rapides_

An App to locate Bike Services Stations

## Getting Started

This project is built for both iOS and Android using [Flutter](https://flutter.dev)

It uses the following main components:
- [Firebase](https://pub.dev/packages/firebase_auth) for authentication
- [Realm Cloud](https://realm.io) for local and cloud database + syncing. 
  - Realm doesn't support Flutter. As such, I used Platform Channels to communicate with the native installation of Realm on both iOS and Android
- [Google Maps for Flutter](https://pub.dev/packages/google_maps_flutter)

You will also need a Google Map API Key to get this running :)
-------------------------
A few resources to get you started if this is your first Flutter project:

- [Lab: Write your first Flutter app](https://flutter.dev/docs/get-started/codelab)
- [Cookbook: Useful Flutter samples](https://flutter.dev/docs/cookbook)

For help getting started with Flutter, view our
[online documentation](https://flutter.dev/docs), which offers tutorials,
samples, guidance on mobile development, and a full API reference.
