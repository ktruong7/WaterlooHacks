# KACH Deals
A [Waterloo Hacks](https://waterloohacks.io) project repository.

*Project:* Bluetooth based store advertising.

Screenshots are available on the [DevPost project page](http://devpost.com/software/kach-deals).

## Sub-Projects

1. **WaterlooHacks-Android** - Android mobile consumer application
2. **BLEBeacon** - iOS mobile beacon application

## Abstract

The current state of brick-and-mortar retail advertising has not changed in any significant way since its inception. We have developed a solution that allows vendors to push real-time targeted offers to their consumers based on their in-store location. A mechanism such as this will help vendors deal with a surplus of products in a more cost-effective manner, incentivise consumers to visit in-store locations for exclusive offers, and present the potential for the collection of consumer movement patterns in relation to their purchasing patterns. Overall, such an infrastructure allows for the targeting of consumers in a platform agnostic and non-intrusive manner.

## Technical Overview

- Bluetooth 4.0 based beacons via one-way peripheral advertising
- Android mobile consumer application 
- iOS mobile beacon application (mocking hardware that would ideally be dedicated)
- A flexible infrastructure that allows for potential growth into other domains (e.x. venue visitor messaging, museum exhibit details, etc.)

## Challenges

- Researching and developing a Bluetooth advertising packet structure that is extendable and can be successfully transmitted by the beacon and received asynchronously by the consuming receiver
- Adapting to the fact that our available Android devices did not support Android API 21 (lack of BLE advertising functionality)

## Accomplishments
- Implementing a new way of targeted-marketing that has never been done before
- Cross-platform/Platform agnostic communication via Bluetooth

## What was learned
- It is possible, to an extent, to transfer data via Bluetooth without pairing (i.e. broadcasting a constant signal)
- How to send and receive Bluetooth signals using multiple devices across Android and iOS

## Future Work
- Preferences and filters for users
- Expand to have a better vendor beacon user-interface
- Analytics for vendors
- Extendible web platform
- Extend to other industry applications (e.x. entertainment)
