###### This is a sample project to find most popular tags in a paragraph and display them in descending order.

Download [apk](https://github.com/buddhasaikia/3FramesLabChallenge/blob/master/apk/MostPopularTags_BuddhaSaikia_20181116.apk)
## Screenshots

![](https://github.com/buddhasaikia/3FramesLabChallenge/blob/master/screenshots/device-2018-11-16-151815.png?raw=true)

![](https://github.com/buddhasaikia/3FramesLabChallenge/blob/master/screenshots/device-2018-11-16-151844.png?raw=true)

## Details

Language- **Kotlin**

Design patters- **MVVM**

Reactive extension- **RxJava2**

### Package structure

```
com.buddha.a3frameslabchallengeapp
|-config
|-data
|-home
  |-view
  |-viewmodel
|-utils
```
### A bit of explanation
Enter a paragraph in _EditText_ and click **PROCESS** button. It will trigger and execute the task in android [computation scheduler](http://reactivex.io/RxJava/javadoc/rx/schedulers/Schedulers.html#computation--) thread. During processing it should show a **progressbar**(if task takes longer time). Once finished, it will display the sorted list in a **RecyclerView**.
