# Android-Sample-Architecture

## What this is?

A sample template that can be used to create a new android project with ease,Includes basic dagger wiring and other useful utilities

## Architecture
MVVM

##FAQ's

###How to scope dependancies?
Use scoping using nav graph instead of scoping via dagger as its much easier

###How to handle repository data to be used with different view models
A new directory can be created called use cases containing all such use cases.
It will be Fragment->ViewModel->UseCase->Repository




