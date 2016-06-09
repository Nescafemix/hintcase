#Changelog

#1.0.2 (09/06/2016)
- Refactored the way the offset is configured (now is done from setTarget(...) instead of setShapeAnimators(...)))
- Fixed a Crash with Andoid N Beta.
- Fixed a bug with the use of the offset in Shapes and ContentHolders.

#1.0.1 (07/06/2016)
- Fixed a bug where the rectangular shape was not being fully animated.
- Fixed a bug where the corresponding parent was not being passed on to ExtraContentHolders.
- Added setStartDelay() method to ContentHolderAnimators and ShapeAnimators. Now you can concatenate animations based on duration and start time. 

#1.0.0 (31/05/2016)
- Initial release