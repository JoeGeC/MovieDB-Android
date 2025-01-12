# MovieDB

A Movie and TV Show database written in Compose for Android using https://developer.themoviedb.org/

# Modularised by feature using Clean Architecture

**App**
* Main Activity
* Theme
* Navigation

**Core** - Holds common methods and classes for each layer
* Data
* Domain
* Presentation

**Feature** - Feature modules

Each feature is split into 3 - base, movies and tv. This allows shared logic and specific logic for each feature without making core a dumping ground
* **Data**
  * Gets data from API
  * Retrofit, Gson
* **Local**
  * Gets and stores data in local storage for caching
  * Room
* **Repository**
  * Handles logic for getting and converting data from local and data layers
* **Domain**
  * Business logic (use cases)
  * Entity models
* **Presentation**
  * ViewModels for getting data from the use case and converting for use in the UI
  * StateFlow, KotlinX Coroutines, AndroidX ViewModel
* **UI**
  * Jetpack Compose UI
 
[Check here if you want to see a project modularised by layer](https://github.com/JoeGeC/PokemonDbFlutter)


# Comprehensive unit testing in all modules.

<img width="500" src="https://github.com/user-attachments/assets/8e01c200-a48f-4732-8d63-6f3670b91dd7" />
<img width="500" src="https://github.com/user-attachments/assets/a793711e-1317-4952-b7f2-16e501d0795b" />

# Light and Dark modes

<img width="280" src="https://github.com/user-attachments/assets/abb57289-7b47-43bf-98cb-689638d71cc7" />
<img width="280" src="https://github.com/user-attachments/assets/bb77b646-9e34-49d1-8c90-4ba48dbb6d73" />

# Shimmer for loading states (Delayed for showcase)

<img width="280" src="https://github.com/user-attachments/assets/ac030206-264e-4c23-9f87-e85eefaddb83" />

# Caching images for smooth loading with SubcomposeAsyncImage

<img width="280" src="https://github.com/user-attachments/assets/5b75e038-73b0-48ee-ac4f-4eae31965251" />

# Fallbacks with SubcomposeAsyncImage

<img width="280" src="https://github.com/user-attachments/assets/e8a4a1b0-e4d8-4c0e-a80c-0249f90614bc" />
<img width="280" src="https://github.com/user-attachments/assets/4de906e1-c5d1-4712-ba00-243969908ffb" />

# Animations 

<img width="280" src="https://github.com/user-attachments/assets/508e0ff8-a24a-4c45-83ee-1c26af55cbdc" />

# Complex list design and custom scroll behaviour with paging using LazyVerticalStaggeredGrid and Pager3

<img width="280" src="https://github.com/user-attachments/assets/22389b44-8f17-46f5-805e-f5441795a4d2" />

# Sound effects with SoundPool

[sound_effect.webm](https://github.com/user-attachments/assets/3bbab1e1-ce9a-41cc-919d-a63e140dfa87)

# Error states that make sense and refreshing

<img width="280" src="https://github.com/user-attachments/assets/5ae26d57-8b3c-41ec-9915-522036b857ac" />
<img width="280" src="https://github.com/user-attachments/assets/398d8a90-46c0-40b3-a769-edd72ecbe36c" />

# Hilt for dependency injection

<img width="500" src="https://github.com/user-attachments/assets/7e2c9421-19be-4171-8e89-0609decaeca3" />
<img width="500" src="https://github.com/user-attachments/assets/a19a868f-d3a1-46f3-b696-7977fff5bec0" />

# String resources for localization and consistency

<img width="500" src="https://github.com/user-attachments/assets/7e46dbae-4184-48f4-8b28-6fe7fb5c44e1" />
<img width="500" src="https://github.com/user-attachments/assets/5ce69a57-cc13-4db2-8518-0628ba2be17a" />

# Accessibility with ContentDescriptions

<img width="500" src="https://github.com/user-attachments/assets/8cc89ff2-8b68-4f1b-9938-da2a35de61b8" />
<img width="500" src="https://github.com/user-attachments/assets/99badf14-44d2-4df5-b477-eab41d31b54a" />
