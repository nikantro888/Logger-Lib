
![app](https://i.imgur.com/8xJg4cf.png)



CI Status:
------
Uzbekistan [![Build Status](https://app.bitrise.io/app/8cd384248cd9a9cf/status.svg?token=EHRK4FxRfAdEewkcGggg3w)](https://app.bitrise.io/app/8cd384248cd9a9cf)

Georgia [![Build Status](https://app.bitrise.io/app/e9d352cdf43b2c04/status.svg?token=w-dp9NnmCjhm-ytadyyP2A)](https://app.bitrise.io/app/e9d352cdf43b2c04)

Germany [![Build Status](https://app.bitrise.io/app/8b54a1e58a161a99/status.svg?token=L4gBXILsbNt2cC_IKskgjQ)](https://app.bitrise.io/app/8b54a1e58a161a99)

# Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com/SpaceBank/Android-Space.git
```
# Configuration
### Keystores:
Create `app/keystore.gradle` with the following info:
```gradle
ext.key_alias='space/TBCUZ'
ext.key_password='Space1234%'
ext.store_password='Space1234%'
```
And place keystores under [keystores](keystores) directory:
- [`GeorgiaKeystore.keystore`](keystores/georgia)
- [`UzbekistanKeystore.keystore`](keystores/germany)
- [`SpaceGermany.keystore`](keystores/uzbekistan)

# Build variants
Use the Android Studio *Build Variants* button to choose between
- **Georgia**
- **Uzbekistan**
- **Germany**

flavors combined with debug, staging and release build types


# Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

# Technical Docs
- [`Gradle configuration`](https://spaceneobank.atlassian.net/wiki/spaces/ABP/pages/1303248921/Gradle+Config+Plugin)
- [`Modules`](https://spaceneobank.atlassian.net/wiki/spaces/ABP/pages/28213657/Modules)
- [`Presentation Layer`](https://spaceneobank.atlassian.net/wiki/spaces/ABP/pages/43745368/Presentation+Layer)
- [`Koin Approach`](https://spaceneobank.atlassian.net/wiki/spaces/ABP/pages/1401913455/Koin+as+the+way+of+simplicity)
- [`Dagger DI Approach`](https://spaceneobank.atlassian.net/wiki/spaces/ABP/pages/22675510/Dagger+DI+Approach)
- [`Variations`](https://spaceneobank.atlassian.net/wiki/spaces/ABP/pages/24379395/Variations)
- [`Navigation`](https://spaceneobank.atlassian.net/wiki/spaces/ABP/pages/27033664/Navigation)
- [`Architecture`](https://spaceneobank.atlassian.net/wiki/spaces/ABP/pages/28311589/Architecture)


# Code styles
Extends [Kotlin developers team code style](https://kotlinlang.org/docs/reference/coding-conventions.html) 

### Content
1. [Line length](#linelength)
2. [Naming rules](#naming)
3. [Modifier order](#modifier_order)
4. [Expression formating](#expression_formating)
5. [Functions](#function)
    * 5.1 [Single expression functions](#function_expression)
    * 5.2 [Formating function calling](#formating_function_calling)
    * 5.3 [Formating function declaration](#formating_function_declaration)
    * 5.4 [Calling function variable](#calling_function_variable)
6. [Classes](#classes)
7. [Annotations](#annotation)
8. [Class member order](#class_member_order)
9. [Lambda expressions formating](#lambda_formating)
10. [Condition operators](#condition_operator)
11. [Template header](#template_header)
12. [Files](#files)
13. [View Binding](#view_binding)
      * 13.1 [Implementation](#implementation)
      * 13.2 [Advices](#advices)
      * 13.3 [Migration](#migration)


### <a name='linelength'>Line length</a>
- Maximum line length: 120 characters.

### <a name='naming'>Naming rules</a>
- All class names should start with `SP`
- All XML layout file names shoould start with `sp_`
- For View fields from the Kotlin Extension, the lowerCamelCase style is used with the `view` suffix
- Packages are named in the style of lower_snake_case

### <a name='expression_formating'>Expression formating</a>

When transferred a chain of method calls on a new line, the character `.` or perator `?.` are transferred to the next line, property is allowed to be left on one line:

```kotlin
val collectionItem = source.collectionItems
    ?.dropLast(10)
    ?.sortedBy { it.progress }
```
------------

Elvis operator `?:` when breaking an expression, it is also transferred to a new line:

```kotlin
val promoItemDistanceTradeLink: String = promoItem.distanceTradeLinks?.appLink
   ?: String.EMPTY
```

------------

When describing a variable with a delegate that does not fit on one line, leave a description with an opening brace on one line, transferring the rest of the expression to the next line:

```kotlin
private val promoItem: MarkPromoItem by lazy {
    extractNotNull(BUNDLE_FEED_UNIT_KEY) as MarkPromoItem
}
```

### <a name='function'>Functions</a>
### <a name='function_expression'>Single expression functions</a>
* It is permissible to use a single expression function only if it is placed on one line and it returns a value.
* It is not permissible to use a one-line function in interface implementation without explicitly declaring return type.

### <a name='formating_function_calling'>Formating function calling</a>
* If function has argument list that not fits one line, each of arguments must be placed in separate line:

```kotlin
runOperation(
    method = operation::run,
    consumer = consumer,
    errorHandler = errorHandler,
    tag = tag,
    cache = cache,
    cacheMode = cacheMode
)
```

### <a name='formating_function_declaration'>Formating function declaration</a>
* If it is necessary to break a line, each argument of a function is moved to a new line with indentation and transfer of the closing parenthesis to the next line:

```kotlin
fun runOperation(
    method: Method,
    consumer: Consumer,
    errorHandler: ErrorHandler,
    tag: String,
    cache: Cache,
    cacheMode: CacheMode
)
```

### <a name='calling_function_variable'>Calling function variable</a>
* Always use the full variant with `invoke()` in the variable instead of using the shortened variant:

```kotlin
    val expression: () -> Unit
    //Bad
    expression()
    //Good
    expression.invoke()
```

### <a name='classes'>Classes</a>
- If a line break is required, each class parameter is moved to a new double-indented row and the parenthesis is moved to the next line:

```kotlin
data class CategoryStatistic(
   val id: String,
   val title: String,
   val imageUrl: String,
   val percent: Double
) : Serializable
```

- If the parent class does not fit on one row in the class description, each of its parameters is also moved to a new row, with a parenthesis closing to the next row:

```kotlin

```

- If the class description does not fit into one line and implements multiple interfaces, then apply the standard carry rules, i.e. make a transfer only when it is not placed on one line and continue listing the interfaces on the next line.

```kotlin

```

### <a name='annotation'>Annotations</a>
- Annotations are usually placed above the description of the class / field / method to which they apply.
- If there are multiple annotations for a class / field / method, place each annotation on a new line:

```kotlin
@JsonValue
@JvmField
var promoItem: PromoItem? = null
```

- If only one annotation without parameters is applied to a field / method, specify it above the field / method.


### <a name='class_member_order'>Class member order</a>
1. Fields: abstract, override, public, internal, protected, private
2. Initialization block: init, constructors
3. Abstract methods
4. Overridden methods of the parent class (in the same order in which they are followed in the parent class)
5. Implementation of interface methods (in the same order in which they are followed in the class description, while observing the order of describing these methods in the interface itself)
6. Public methods
7. Internal methods
8. Protected methods
9. Private methods
10. Inner classes
11. Companion object

### <a name='lambda_formating'>Lambda expressions formating</a>
- If possible, leave a lambda expression on a single line, using `it` as an argument.
- When using the lambda function as an argument, make it by parentheses if this parameter is single.
- When writing a lambda expression in more than one line, always use a named argument instead of `it`:

```kotlin
viewPager.adapter = QuestAdapter (quest, {quest ->
    onQuestClicked (quest)
})
```

- Always replace unused lambda expression parameters with the symbol `_`.
- Avoid using Destructuring Declaration in lambda expressions.

### <a name='condition_operator'>Condition operators</a>

- If conditions, not framed by brackets is **NOT** permitted

```kotlin
if (condition) foo() else bar()
```

```kotlin
if (condition) foo() 
else bar()
```

```kotlin
if (condition) foo()
```

### <a name='template_header'>Template header</a>
- Do not use Template Header for classes (applies to authorship and file creation date).

### <a name='files'>Files</a>
- It is permitted to describe several classes in one file **only** for `sealed` and `inner` classes.

### <a name='view_binding'>View Binding</a>
 We use View Binding in our project to replace deprecated kotlin synthetic. For activities and fragments, we have to use SPViewBindingExt.kt extension which handles everything by itself, this extension helps us to avoid memory leaks and make implementation much easier.
 
 ### <a name='implementation'>View Binding implementation</a>
 
 Activity:
 ```kotlin
 private val binding by viewBinding(ActivityExampleLayoutBinding::inflate) 
 
 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(binding.root)
 }
 ```
 
 Fragment:  
 ```kotlin
 override val screenLayout = R.layout.sp_Example_feature_fragment
 private val binding by viewBinding(SpExampleFeatureFragmentBinding::bind)
 ```
 
 Custom view:
 ```kotlin
 private val binding by lazy { SpCustomExampleBinding.inflate(LayoutInflater.from(context),this,true) }
 ```
 
 Adapter:
 ```kotlin
 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ExampleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
 ```
  ### <a name='advices'>Recomendations for view binding</a>
   
   * Binding
 ```kotlin
 binding.titleTextView.text = "space"
 ```
   
   * Multy binding
  ```kotlin
  with(binding){
       titleTextView.text = "space"
       descriptionTextView.text = "description"
  }
  ```
  * Include layout binding
 ```kotlin
 binding.includeLayoutId.titleTextView.text = "space"
 ```
  * Use root to make changes for include layout  
 ```kotlin
 binding.includeLayoutId.root.visibility = VISIBLE
 ```
 
  ### <a name='advices'>Recomendations for view binding</a>
   - For easier migration, remove kotlin synthetic imports from your class to see exactly which lines needs view binding. 
   

# Resources

**Naming.** Follow the convention of prefixing the type, as in `fragment_contact_details.xml`. Examples: `sp_contact_details_fragment.xml`, `sp_view_primary_button.xml`, `sp_main_activity.xml`.

**Organizing layout XMLs.** If you're unsure how to format a layout XML, the following convention may help.

- One attribute per line, indented by 4 spaces
- `android:id` as the first attribute always
- `android:layout_****` attributes at the top
- `style` attribute at the bottom
- Tag closer `/>` on its own line, to facilitate ordering and adding attributes.
- Rather than hard coding `android:text`, consider using [Designtime attributes](http://tools.android.com/tips/layout-designtime-attributes) available for Android Studio.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >

    <TextView
        android:id="@+id/name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:text="@string/name"
        style="@style/FancyText"
        />

    <include layout="@layout/reusable_part" />

</LinearLayout>
```

As a rule of thumb, attributes `android:layout_****` should be defined in the layout XML, while other attributes `android:****` should stay in a style XML. This rule has exceptions, but in general works fine. The idea is to keep only layout (positioning, margin, sizing) and content attributes in the layout files, while keeping all appearance details (colors, padding, font) in styles files.

The exceptions are:

- `android:id` should obviously be in the layout files
- `android:orientation` for a `LinearLayout` normally makes more sense in layout files
- `android:text` should be in layout files because it defines content
- Sometimes it will make sense to make a generic style defining `android:layout_width` and `android:layout_height` but by default these should appear in the layout files

<a name="styles"></a>
**Use styles.** Almost every project needs to properly use styles, because it is very common to have a repeated appearance for a view. At least you should have a common style for most text content in the application, for example:

```xml
<style name="ContentText">
    <item name="android:textSize">@dimen/font_normal</item>
    <item name="android:textColor">@color/basic_black</item>
</style>
```

Applied to TextViews:

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/price"
    style="@style/ContentText"
    />
```

You probably will need to do the same for buttons, but don't stop there yet. Go beyond and move a group of related and repeated `android:****` attributes to a common style.

<a name="splitstyles"></a>
**Split a large style file into other files.** You don't need to have a single `styles.xml` file. Android SDK supports other files out of the box, there is nothing magical about the name `styles`, what matters are the XML tags `<style>` inside the file. Hence you can have files `styles.xml`, `styles_home.xml`, `styles_item_details.xml`, `styles_forms.xml`. Unlike resource directory names which carry some meaning for the build system, filenames in `res/values` can be arbitrary.

<a name="colorsxml"></a>
**`colors.xml` is a color palette.** There should be nothing in your `colors.xml` other than a mapping from a color name to an RGBA value. This helps avoid repeating RGBA values and as such will make it easy to change or refactor colors, and also will make it explicit how many different colors are being used. Normally for a aesthetic UI, it is important to reduce the variety of colors being used.
 
*So, don't define your colors.xml like this:*

```xml
<resources>
    <color name="button_foreground">#FFFFFF</color>
    <color name="button_background">#2A91BD</color>
</resources>    
```

Instead, do this:

```xml
<resources>
    <!-- grayscale -->
    <color name="white">#FFFFFF</color>
   
    <!-- basic colors -->
    <color name="blue">#2A91BD</color>
</resources>
```

Ask the designer of the application for this palette. The names do not need to be plain color names as "green", "blue", etc. Names such as "brand_primary", "brand_secondary", "brand_negative" are totally acceptable as well.

By referencing the color palette from your styles allows you to abstract the underlying colors from their usage in the app, as per:

- `colors.xml` - defines only the color palette.
- `styles.xml` - defines styles which reference the color palette and reflects the color usage. (e.g. the button foreground is white).
- `activity_main.xml` - references the appropriate style in `styles.xml` to color the button.

If needed, even further separation between underlying colors and style usage can be achieved by defined an additional color resource file which references the color palette. As per:

```xml
<color name="button_foreground">@color/white</color> 
<color name="button_background">@color/blue</color> 
```

Then in styles.xml:

```xml
<style name="AcceptButton">
    <item name="android:foreground">@color/button_foreground</item>
    <item name="android:background">@color/button_background</item>
</style>
```

This approach offers improved color refactoring and more stable style definitions when multiple related styles share similar color and usage properties. However, it comes at the cost of maintaining another set of color mappings. 

**strings.xml**

Name your strings with keys that resemble namespaces, and don't be afraid of repeating a value for two or more keys. Languages are complex, so namespaces are necessary to bring context and break ambiguity.

**Bad**
```xml
<string name="network_error">Network error</string>
<string name="call_failed">Call failed</string>
<string name="map_failed">Map loading failed</string>
```

**Good**
```xml
<string name="error_message_network">Network error</string>
<string name="error_message_call">Call failed</string>
<string name="error_message_map">Map loading failed</string>
```

**The best**
```xml
<string name="module_error_message_network">Network error</string>
<string name="module_error_message_call">Call failed</string>
<string name="module_error_message_map">Map loading failed</string>
```

Don't write string values in all uppercase. Stick to normal text conventions (e.g., capitalize first character). If you need to display the string in all caps, then do that using for instance the attribute [`textAllCaps`](http://developer.android.com/reference/android/widget/TextView.html#attr_android:textAllCaps) on a TextView.

**Bad**
```xml
<string name="error_message_call">CALL FAILED</string>
```

**Good**
```xml
<string name="error_message_call">Call failed</string>
```

<a name="deephierarchy"></a>
**Avoid a deep hierarchy of views.** Sometimes you might be tempted to just add yet another LinearLayout, to be able to accomplish an arrangement of views. This kind of situation may occur:

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >

    <RelativeLayout
        ...
        >

        <LinearLayout
            ...
            >

            <LinearLayout
                ...
                >

                <LinearLayout
                    ...
                    >
                </LinearLayout>

            </LinearLayout>

        </LinearLayout>

    </RelativeLayout>

</LinearLayout>
```

Even if you don't witness this explicitly in a layout file, it might end up happening if you are inflating (in Java) views into other views.

A couple of problems may occur. You might experience performance problems, because there is a complex UI tree that the processor needs to handle. Another more serious issue is a possibility of [StackOverflowError](http://stackoverflow.com/questions/2762924/java-lang-stackoverflow-error-suspected-too-many-views).

Therefore, try to keep your views hierarchy as flat as possible: learn how to use [ConstraintLayout](https://developer.android.com/training/constraint-layout/index.html), how to [optimize your layouts](http://developer.android.com/training/improving-layouts/optimizing-layout.html) and to use the [`<merge>` tag](http://stackoverflow.com/questions/8834898/what-is-the-purpose-of-androids-merge-tag-in-xml-layouts).

<a name="webviews"></a>
**Beware of problems related to WebViews.** When you must display a web page, for instance for a news article, avoid doing client-side processing to clean the HTML, rather ask for a "*pure*" HTML from the backend programmers. [WebViews can also leak memory](http://stackoverflow.com/questions/3130654/memory-leak-in-webview) when they keep a reference to their Activity, instead of being bound to the ApplicationContext. Avoid using a WebView for simple texts or buttons, prefer the platform's widgets.


# Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Push your branch (git push origin my-new-feature)
5. Create a new Pull Request

# Maintainers
This project is mantained by:
* [Space Android Team](https://space.ge/aboutus)

# Modules

### app
[link](app) 
Module short description goes here it seems nobody bothered to drop few words. 


### SpaceCamera
[link](SpaceCamera) 
Module contains camera libraries, for selfies scanners and etc. 


### SpaceCardComponents
[link](SpaceCardComponents) 
Card picker and card selection components 


### SpaceCardOrder
[link](SpaceCardOrder) 
IDK 


### SpaceCards
[link](SpaceCards) 
Feature module for card management 


### SpaceCommon
[link](SpaceCommon) 
Module short description goes here it seems nobody bothered to drop few words. 


### SpaceComponents
[link](SpaceComponents) 
Common library of UI elements, try to keep all elements independent of other modules 


### SpaceContacts
[link](SpaceContacts) 
Contact picker 


### SpaceCoreAuth
[link](SpaceCoreAuth) 
Fingerprint and OTP auth 


### SpaceCoreNavigation
[link](SpaceCoreNavigation) 
This module is responsible for communicating between modules and navigation within module. 


### SpaceCoreNetworkService
[link](SpaceCoreNetworkService) 
This module is responsible for Network Calls 


### SpaceCoreStorage
[link](SpaceCoreStorage) 
Module is for storing application data current implementation is using SPApp class via interface 


### SpaceDeepLinking
[link](SpaceDeepLinking) 
Module short description goes here it seems nobody bothered to drop few words. 


### SpaceGamification
[link](SpaceGamification) 
Feature module 


### SpaceHome
[link](SpaceHome) 
Home screen module 


### SpaceIntro
[link](SpaceIntro) 
Intro screen  module 


### SpaceInviteFriend
[link](SpaceInviteFriend) 
Feature module 


### SpaceLifecycleComponents
[link](SpaceLifecycleComponents) 
Library that connects activities and view models  


### SpaceLoan
[link](SpaceLoan) 
Feature module 


### SpaceLoanRepository
[link](SpaceLoanRepository) 
IDK 


### SpaceLogin
[link](SpaceLogin) 
Login Screen module 


### SpaceModels
[link](SpaceModels) 
Common model classes used by all modules 


### SpaceNetworkService
[link](SpaceNetworkService) 
Temporary module for all services 


### SpaceOnBoarding
[link](SpaceOnBoarding) 
Waterboarding in guantanamo bay sound more fun than this module, but actually it is 


### SpacePayment
[link](SpacePayment) 
Feature module 


### SpaceProfile
[link](SpaceProfile) 
Feature module 


### SpacePushNotifications
[link](SpacePushNotifications) 
Module short description goes here it seems nobody bothered to drop few words. 


### SpaceSignUp
[link](SpaceSignUp) 
Feature module 


### SpaceSplash
[link](SpaceSplash) 
Feature module 


### SpaceStories
[link](SpaceStories) 
Module short description goes here it seems nobody bothered to drop few words. 


### SpaceSupport
[link](SpaceSupport) 
Feature module 


### SpaceTransaction
[link](SpaceTransaction) 
Module short description goes here it seems nobody bothered to drop few words. 


### SpaceTransfer
[link](SpaceTransfer) 
Module short description goes here it seems nobody bothered to drop few words. 


### SpaceUtil
[link](SpaceUtil) 
Module short description goes here it seems nobody bothered to drop few words. 



### SpaceVerification
[link](SpaceVerification) 
Module short description goes here it seems nobody bothered to drop few words. 

