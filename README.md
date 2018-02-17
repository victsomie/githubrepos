# githubrepos
Android project to showcase MVVM with a little more complexity using Retrofit 2 to call GitHub API
### Steps:

Creating(Understand) the project structure, i.e the sceleton    
    
   i) Create the SERVICE PACKAGE (The retrofit client) Put the network calles here *This is where Models(Pojos) and the Repository will go*
    
   ii) Create the MODELS PACKAGE - Where your UI will go
    
   iii) Create VIEWMODEL PACKAGE 
   
 DISCLAIMER: Different people will implement the structure slightly differentrs BUT understand that the major concern is to get more organised as the [Android App Archtecture](https://developer.android.com/topic/libraries/architecture/guide.html#common_architectural_principles) helps you in 'Separation of concerns'



## MVVM has mainly the following layers:

#### i) Model
Model represents the data and business logic of the app. One of the recommended implementation strategies of this layer, is to expose its data through observables to be decoupled completely from ViewModel or any other observer/consumer (This will be illustrated in our MVVM sample app below).
    
### ii) ViewModel
ViewModel interacts with model and also prepares observable(s) that can be observed by a View. ViewModel can optionally provide hooks for the view to pass events to the model. 
    One of the important implementation strategies of this layer is to decouple it from the View, i.e, ViewModel should not be aware about the view who is interacting with.
   
### iii) View
Finally, the view role in this pattern is to observe (or subscribe to) a ViewModel observable to get data in order to update UI elements accordingly.

;

;

;

# References:
-

a) [Main implementation](https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1) - This is a good resource to learn this


b) [Android App Architecture (Official)](https://developer.android.com/topic/libraries/architecture/guide.html#common_architectural_principles) - This resource with give a good grasp of planning the LiveData, MutableLiveData

ViewModel, Models (Your POJO(s)) etc

# Credits
Thanks to [Hazem Saleh](https://github.com/hazems)
