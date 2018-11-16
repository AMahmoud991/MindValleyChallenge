Download [apk](https://github.com/buddhasaikia/MindValleyChallenge/blob/master/apk/MindValleyChallengeBuddhaS-0.1.0.225-20181116-prod-debug.apk)
## Screenshots

![](https://github.com/buddhasaikia/MindValleyChallenge/blob/master/screenshots/device-2018-11-16-183633.png?raw=true)

![](https://github.com/buddhasaikia/MindValleyChallenge/blob/master/screenshots/device-2018-11-16-190251.png?raw=true)

## Details of sample project

Language- **Mix of Kotlin and Java**

Architectural patters- **MVVM**

Reactive extension- **RxJava2**

Dependency Injection- **Dagger2**

Image library- **mindboardlib**

Network library- **Retrofit2**

UI component- **RecyclerView, FloatingActionButton**

Package structure- **Group by feature**

## Architecture diagram
![](https://github.com/buddhasaikia/MindValleyChallenge/blob/master/diagram/mindvalley_challenge_arch_diagram.png?raw=true)

## Details of mindboardlib library

Language- **Java**

Design patterns used- **Singleton pattern, Builder pattern**

### A bit of explanation
**How to initialize cache size**
Add the following line ```LruMemCache.getInstance().initCache(size);``` in ```onCreate``` method of your Application class.
