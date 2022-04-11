
# MVVM(Model View ViewModel)
![alt text](https://user-images.githubusercontent.com/1812129/68319232-446cf900-00be-11ea-92cf-cad817b2af2c.png)

Model-View-ViewModel (ie MVVM) is a template of a client application architecture, proposed by John Gossman as an alternative to MVC and MVP patterns when using Data Binding technology. Its concept is to separate data presentation logic from business logic by moving it into particular class for a clear distinction.



## MVVM Best Pratice:

- Avoid references to Views in ViewModels if you do then it will leads to the memory leak
- Instead of pushing data to the UI, let the UI observe changes to it.
- if you are obsering the data in the fragment then dont pass the lifecycle of activity instead of pass lifecycle of fragment view using viewLifeCycleOwner
- Distribute responsibilities, add a domain layer if needed.
- Add a data repository as the single-point entry to your data.
- Expose information about the state of your data using a wrapper or another LiveData.
- Consider edge cases, leaks and how long-running operations can affect the instances in your architecture.
- Don’t put logic in the ViewModel that is critical to saving clean state or related to data. Any call you make from a ViewModel can be the last one.

## What is Coroutines ?
 Coroutines  Is light wight threads for asynchronous programming, Coroutines not only open the doors to asynchronous programming, but also provide a wealth of other possibilities such as concurrency, actors, etc.
