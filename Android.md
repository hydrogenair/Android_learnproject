Android

一般一个应用由Activity 和布局组成 布局只是应用界面 Activity是内部逻辑

![image-20221122000536240](C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221122000536240.png)

LinearLayout组件必须指定Android XML资源文件的命名空间属性，这里是 http://schemas. android.com/apk/res/android

都是父子类关系

match_parent:视图与父视图相同

wrap_parent:视图将根据其显示内容自动调整大小。

android：layout_gravity是设置该控件相对于父容器对齐方式；
android：gravity是设置子元素在该容器内的对齐方式。

android:orientation属性是决定两者的子组件 是水平放置还是垂直放置。

padding值的作用是：让控件里的内容距控件边上有一定的距离；父控件设置的是paddingBottom、paddingTop、paddingLeft、paddingRigh

android:text属性值不是字符串值，而是对字符串资源（string resource）的引用。(类似于drawback的那种) 字符串资源包含在一个独立的名叫strings的XML文件中（strings.xml），虽然可以硬编码设置 组件的文本属性值，如android:text="True"，但这通常不是个好主意。比较好的做法是：将文 字内容放置在独立的

请注意新增的两个成员（实例） 变量名称的m前缀。该前缀是Android编程应遵循的命名约定，本书将始终遵循该约定。

在activity中，可调用以下Activity方法引用已生成的组件： public View findViewById(int id)  

该方法以组件的资源ID作为参数，返回一个视图对象

必须将返回对象转为所需对象

Android应用属于典型的事件驱动类型。不像命令行或脚本程序，事件驱动型应用启动后， 即开始等待行为事件的发生，如用户点击某个按钮。（事件也可以由操作系统或其他应用触发， 但用户触发的事件更直观，如点击按钮。） 应用等待某个特定事件的发生，也可以说应用正在“监听”特定事件。为响应某个事件而创 建的对象叫作监听器（listener）。监听器会实现特定事件的监听器接口（listener interface）。---检测程序是否被触发 创建匿名内部类 抽象方法：toclick 创建一个对象重写抽象方法

里面放置链接对象 



Android的toast 是用来通知用户的简短弹出消息，用户无需输入什么，也不用做任何干预操作。--提示操作)

调用Toast类的以下方法，可创建toast： public static Toast makeText(Context context, int resId, int duration)  该方法的Context参数通常是Activity的一个实例（Activity本身就是Context的子类）。 第二个参数是toast要显示字符串消息的资源ID。Toast类必须借助Context才能找到并使用字符 串资源ID。第三个参数通常是两个Toast常量中的一个，用来指定toast消息的停留时间

创建toast后，可调用Toast.show()方法在屏幕上显示toast消息。可通过setgravity设置toast的具体位置

````java
Toast toast=  Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
toast.setGravity(Gravity.TOP, 0, 0);
toast.show();
//setGravity的方法如下
public void setGravity (int gravity, 
                int xOffset, 
                int yOffset)
````

![image-20221122182238044](C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221122182238044.png)

模型对象存储着应用的数据和业务逻辑。模型类通常用来映射与应用相关的一些事物，如户、商店里的商品、服务器上的图片或者一段电视节目，抑或GeoQuiz应用里的地理知识 问题。模型对象不关心用户界面，它为存储和管理应用数据而生。

视图对象知道如何在屏幕上绘制自己，以及如何响应用户的输入，如触摸动作等。一个 简单的经验法则是，凡是能够在屏幕上看见的对象，就是视图对象。

控制器对象含有应用的逻辑单元，是视图对象与模型对象的联系纽带。控制器对象响应 视图对象触发的各类事件，此外还管理着模型对象与视图层间的数据流动。

R.string.xml调出来的数据被定义为了int，R.drawable R.layout R.string等就是int类型,编译的时候生成R类，R文件就相当于将所有资源做了一个处理，内部又生成了drawable layout等类，然后资源文件会使用一个int类的静态常量代替

每个Activity实例都有其生命周期。在其生命周期内，activity在运行、暂停、停止和不存 在这四种状态间转换。每次状态转换时，都有相应的Activity方法发消息通知activity。图3-1显 示了activity的生命周期、状态以及状态切换时系统调用的方法

![image-20221124164952933](C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221124164952933.png)

借助图3-1所示的方法，Activity的子类可以在activity的生命周期状态发生关键性转换时完 成某些工作。这些方法通常被称为生命周期回调方法。 我们已熟悉这些方法中的onCreate(Bundle)方法。在创建activity实例后，但在此实例出现 在屏幕上之前，Android操作系统会调用该方法。

千万不要自己去调用onCreate(Bundle)方法或任何其他activity生命周期方法。为通 知activity状态变化，你只需在Activity子类里覆盖这些方法，Android会适时调用它们（看当前 用户状态以及系统运行情况）

这些覆盖方法会输出日志，告诉我们操作系统何时调用了它们。这样，伴随用户操作， QuizActivity的状态如何变化，就很清楚了。  Android的android.util.Log类能够向系统级共享日志中心发送日志信息。

 public static int d(String tag, String msg)  

d代表“debug”，用来表示日志信息的级别。 第一个参数是日志的来源，第二个参数是日志的具体内容

横屏处理

FrameLayout是最简单的 ViewGroup组件，它一概不管如何安排其子视图的位置。FrameLayout子视图的位置排列取决于 它们各自的android:layout_gravity属性。 因而，TextView、LinearLayout和Button都需要一个android:layout_gravity属性。这 里，LinearLayout里的Button子元素保持不变。

protected void onSaveInstanceState(Bundle outState)  

该方法通常在onStop()方法之前由系统调用，除非用户按后退键。（记住，按后退键就是告 诉Android，activity用完了。随后，该activity就完全从内存中被抹掉，自然，也就没有必要为重 建保存数据了。）方法onSaveInstanceState(Bundle)的默认实现要求所有activity视图将自身状态数据保存 在Bundle对象中。Bundle是存储字符串键与限定类型值之间映射关系（键值对）的一种结构。将一些数据保存在bundle中，然后在 onCreate(Bundle)方法中取回这些数据。

每次旋转设备，当前QuizActivity实例会完全销毁，实例中的mCurrentIndex当前值会从内存里被抹掉。旋转后，Android重新创建了QuizActivity新实例，mCurrentIndex在onCreate(Bundle)方法中被初始化为0。设备处于水平方向时，Android会找到并使用res/layout-land目录下的布局资源。其他情况下， 它会默认使用res/layout目录下的布局资源。在设备运行中发生配置变更时，若设备旋转，需想个办法保存以前的数据。

在Bundle中存储和恢复的数据类型只能是基本类型（primitive type）以及可以实现 Serializable或Parcelable接口的对象。在Bundle中保存定制类对象不是个好主意，因为你 取回的对象可能已经没用了。比较好的做法是，通过其他方式保存定制类对象，而在Bundle中 保存标识对象的基本类型数据。Bundle是存储字符串键与限定类型值之间映射关系（键(key (str))——值对）的一种结构

getIntExtra(key ,int *DefaultValue*) 作用:获取存放在intent对象中的键为key的int类型的数据。若获取不到,则附一个默认值. 

覆盖onSaveInstanceState(Bundle)方法，在Bundle对象中，保存当前activity的小的或 暂存状态的数据；覆盖onStop()方法，保存永久性数据，如用户编辑的文字等。onStop()方法 调用完，activity随时会被系统销毁，所以用它保存永久性数据。

![image-20221124194017212](C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221124194017212.png)

````java
//显示现在什么位置
Log.d(TAG, "Current question index: " + mCurrentIndex); 
Question question; 
try { 
 question = mQuestionBank[mCurrentIndex]; 
} catch (ArrayIndexOutOfBoundsException ex) { 
 // 超过数组的位置
 Log.e(TAG, "Index was out of bounds", ex); 
} 
````

bundle

相当于一种桥梁 链接key和值 可以通过key（都是字符串）找到值

.get类型（String）可以找到它所绑定的值 有时候会有两个参数 第二个参数是默认值 即找不到KEY背后的值是返回默认值 

.put类型（String,类型）将值和键绑在一起

intent对象是component用来与操作系统通信的一种媒介工具。目前为止，我们唯一见过的 component就是activity。实际上还有其他一些component：service、broadcast receiver以及content  provider。 intent是一种多用途通信工具。Intent类有多个构造方法，能满足不同的使用需求。 在GeoQuiz应用中，intent用来告诉ActivityManager该启动哪个activity，因此可使用以下构 造方法：

````
public Intent(Context packageContext, Class<?> cls)
````

传入该方法的Class类型参数告诉ActivityManager应该启动哪个activity；Context参数告 诉ActivityManager在哪里可以找到它

该值将作为extra信息，附加在传入startActivity(Intent)方法的Intent上发送出去。 extra信息可以是任意数据，它包含在Intent中，由启动方activity发送出去。

````
public Intent putExtra(String name, boolean value) 
````

activity可能启动自不同的地方，所以，应该在获取和使用extra信息的activity那里，为它定义 键。如代码清单5-8所示，记得使用包名修饰extra数据信息，这样，可避免来自不同应用的extra 间发生命名冲突 最好封装

需要从子activity获取返回信息时，可调用以下Activity方法

````
public void startActivityForResult(Intent intent, int requestCode)
````

   该方法的第一个参数同前述的intent。第二个参数是请求代码。请求代码是先发送给子 activity，然后再返回给父activity的整数值，由用户定义。在一个activity启动多个不同类型的子 activity，且需要判断消息回馈方时，就会用到该请求代码。

设置返回结果
实现子activity发送返回信息给父activity，有以下两种方法可用：

````
public final void setResult(int resultCode) 
public final void setResult(int resultCode, Intent data) 
````

一般来说，参数resultCode:Activity.RESULT_OK（表示调用Activity系统*返回*的结果，这是调用成功）, Activity.RESULT_CANCELED （调用失败/返回）。子activity可以不调用setResult(...)方法。如果不需要区分附加在intent上的结果或其他信 息，可让操作系统发送默认的结果代码。



![image-20221202184805198](C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221202184805198.png)

![image-20221202184936320](C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221202184936320.png)

答案是否正确：intent +extra--在cheat中getintent() 用户是否作弊—回调startActivityForResult 相当于从子类回到父类 传送是否作答 ----用onActivity接

将高API级别代码置于检查Android设备版本的条件语句

Build.VERSION.SDK_INT常量代表了Android设备的版本号。可将该常量同代表Lollipop版本 的常量进行比较。 现在动画特效代码只有在API 21级或更高版本的设备上运行应用才会被调用。

[`Fragment`](https://developer.android.google.cn/reference/androidx/fragment/app/Fragment?hl=zh-cn) 表示应用界面中可重复使用的一部分。Fragment 定义和管理自己的布局，具有自己的生命周期，并且可以处理自己的输入事件。fragment 不能独立存在，而是必须由 activity 或另一个 fragment 托管。fragment 的视图层次结构会成为宿主的视图层次结构的一部分，或附加到宿主的视图层次结构。

要使用AppCompat支持库，项目必须将其列入依赖关系。打开应用模块下的build.gradle文件。 每个项目都有两个build.gradle文件。一个用于整个项目，另一个用于应用模块。我们要编辑的是 app/build.gradle文件。

FrameLayout是服务于CrimeFragment的容器视图。注意该容器视图是个通用性视图，不单 单用于CrimeFragment类，你还可以用它托管其他的fragment

TextView：

1. 向用户显示文本和选择性地允许编辑它。一个TextView是一个完整的文本编辑器，但是基本的类配置不允许编辑。一般只是为了显示文本，如果要编辑用EditText配置文本视图编辑的一个子类。

2. 允许用户复制TextView价值的部分或者全部黏贴在其他地方，设置XML属性android：textSelectable为“true”或者setTextSelectable（true）。textSelectable标记TextView允许用户做出选择的手势，进而触发系统内置的复制/粘贴控制。

   EditText:（输入文字文本框）

   1.EditText是一个非常重要的组件，它是用户和Android应用进行数据传输的窗户，用户可以把数据传给Android应用，然后得到我们想要的数据。

   2.EditText是TextView的子类，所以TextView的方法和特性同样存在于EditText中

![image-20221216202958300](C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221216202958300.png)

![image-20221217223328321](C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221217223328321.png)

RecyclerView自己不创建ViewHolder。这个任 务实际是由Adapter来完成的。Adapter是一个控制器对象，从模型层获取数据，然后提供给 RecyclerView显示，是沟通的桥梁

首先，调用Adapter的getItemCount()方法，RecyclerView询问数组列表中包含多少个 对象。 接着，RecyclerView调用Adapter的onCreateViewHolder(ViewGroup, int)方法创建 ViewHolder及其要显示的视图。 最后，RecyclerView会传入ViewHolder及其位置，调用onBindViewHolder(ViewHolder,  int)方法。Adapter会找到目标位置的数据并将其绑定到ViewHolder的视图上。所谓绑定，就 是使用模型数据填充视图。 整个过程执行完毕，RecyclerView就能在屏幕上显示crime列表项了。需要注意的是，相对 于onBindViewHolder(ViewHolder, int)方法，onCreateViewHolder(ViewGroup, int)方 法的调用并不频繁。一旦有了够用的ViewHolder，RecyclerView就会停止调用onCreateViewHolder(...)方法。随后，它会回收利用旧的ViewHolder以节约时间和内存

边距属性是布局参数，决定了组件间的距离。由于组件对外界一无所知，因此边距必须由该 组件的父组件负责。 内边距不是布局参数。属性android:padding告诉组件：在绘制组件自身时，要比所含内容 大多少。

 DateFormat 是日期/时间格式化子类的抽象类，它以与语言无关的方式格式化并解析日期或时间。日期/时间格式化子类（如 SimpleDateFormat类）允许进行格式化（也就是日期 -> 文本）、解析（文本-> 日期）和标准化。我们通过这个类可以帮我们完成日期和文本之间的转换。DateFormat 可帮助进行格式化并解析任何语言环境的日期。对于月、星期，甚至日历格式（阴历和阳历），其代码可完全与语言环境的约定无关。

DateFormat类的作用：即可以将一个Date对象转换为一个符合指定格式的字符串，也可以将一个符合指定格式的字符串转为一个Date对象。指定格式的具体规则我们可参照SimpleDateFormat类的说明，这里做简单介绍，规则是在一个字符串中，会将以下字母替换成对应时间组成部分，剩余内容原样输出：

 步骤:
     *    1. 创建SimpleDateFormat对象
     *       在类构造方法中,写入字符串的日期格式 (自己定义)
     *    2. SimpleDateFormat调用方法format对日期进行格式化
     *         String format(Date date) 传递日期对象,返回字符串
     *    日期模式:
     *       yyyy    年份
     *       MM      月份
     *       dd      月中的天数
     *       HH       0-23小时
     *       mm      小时中的分钟
     *       ss      秒
     *       yyyy年MM月dd日 HH点mm分钟ss秒  汉字修改,: -  字母表示的每个字段不可以随便写
DateFormat df= new SimpleDateFormat("yyyy-MM-dd");//对日期进行格式化


Date date = new Date(1607616000000L);


String str_time = df.format(date);


System.out.println(str_time);//2020年12月11日



![image-20221223155754548](C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221223155754548.png)
