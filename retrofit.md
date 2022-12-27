retrofit

1.添加retrofit依赖项

2.描述想与之交谈的端点 

3.设置JSON映射 

4.创建一个retrofit客户端 用于执行请求

step1 

##### (build.gradle(Module:app))添加retrofit依赖项 并设置JSON映射 

implementation 'com.google.code.gson:gson:2.8.0'(gson生成和解析库)
implementation 'com.squareup.okhttp3:okhttp:3.9.1'(开源的网络请求库)
implementation 'com.squareup.okhttp3:logging-interceptor:3.9.1'(支持okhttp跟踪到一个网络请求的所有状态，包括请求头、请求体、响应行、 响应体,方便调试)
implementation 'com.squareup.retrofit2:retrofit:2.3.0'(实现将HTTP请求转换为Java接口)
implementation 'com.squareup.retrofit2:adapter-rxjava:2.1.0'(配合Rxjava 使用)
implementation 'com.squareup.retrofit2:converter-gson:2.1.0'(转换器，请求结果转换成Model)
implementation 'io.reactivex:rxandroid:1.2.1'
implementation 'io.reactivex:rxjava:1.2.1'(一种帮助你做异步的框架. 类似于 AsyncTask. 但其灵活性和扩展性远远强于前者. 从能力上讲, 如果说 AsycnTask 是 DOS 操作系统, RxJava 是 Window 操作系统。)

````java
implementation 'com.squareup.retrofit2:retrofit:2.3.0'
implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
//转换器（java对象和JSON——github使用语言）
````

step2

##### (AndroidManifest) 添加网络权限

````java
<uses-permission android:name="android.permission.INTERNET"/>
````

##### 创建一个接口用于描述想交谈的端点 

retrofit将okhttp请求抽象成java的接口类，用注解描述和配置网络请求参数，封装Url地址和网络数据请求

- **@GET**               请求方法注解，get请求，括号内的是请求地址，Url的一部分

  

````java
public interface Api {
    //获取端点位置
     //fs-opensource是用户名
     //这是一个静态的URL
	@GET("/users/fs-opensource/repos")
     //更换为动态
    @GET("/users/{user}/repos")
	//reposForUser 方法名 向想要请求存储库的用户传递的参数String user
	//List<>是返回的东西 比如此处返回的是一个数据库
	//创建接受服务器返回数据的类GithunRepo
    //其中返回类型是Call<*>，*表示接收数据的类
    Call<List<GitHubRepo>>reposForUser(Srting user)
     //更换为动态 用String user替换GET中的user 
        Call<List<GitHubRepo>>reposForUser(@Path("user")Srting user)
}
````

##### 描述GitHub存储库

比如存储数据类型

````
public class GitHubRepo {
    private String  name;

    public String getName() {
        return Name;
    }
}
````

step3.

##### 创建Retrofit实例(Mainactivity)

设置baseUrl地址，Retrofit把网络请求的URL 分成了两部分设置： **第一部分**：在创建Retrofit实例时通过`.baseUrl()`设置，这个地址会和方法请求注解上面的地址拼接在一起，baseUrl地址必须以`/`结尾，否则会报错。**第二部分**：在网络请求接口的注解设置，就是在上面的APi接口中用GET注解的字符串,地址拼接后的完整地址为：https://api.github.com/users/fs-opensource/repos

````java
    //Retrofit构建器
    Retrofit.Bulider build= new Retrofit.Builder()
            //设置网络请求BaseUrl地址
            .baseUrl("https://api.github.com/")
            //设置数据转换器
            .addConverterFactory(GsonConverterFactory.create());
	//构建Retrofit实例
	Retrofit retrofit=builder.build();
````

调用Retrofit实例对象 `create()` 方法，传入网络接口类，得到接口对象实例，调用接口类中的方法。

````java
Api api=retrofit.create(Api.class);
//实现动态传递
Call <List<GitHubRepo>> call=api.reposForUser("fs-opensource")
````

- > **同步方法**调用一旦开始，调用者必须等到方法调用返回后，才能继续后续的行为。**异步方法**调用更像一个消息传递，一旦开始，方法调用就会立即返回，调用者就可以继续后续的操作。而，异步方法通常会在另外一个线程中，“真实”地执行着。整个过程，不会阻碍调用者的工作

  > ![image-20221227115148108](C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221227115148108.png)

异步执行

````java
 Call.enqueue(new Callback<List<GitHubRepo>>() {
     @Override
     public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
           Toast.makeText(MainActivity.this, "get回调成功:异步执行", Toast.LENGTH_SHORT).show();
         //body()是List<GitHubRepo>
         //访问服务器响应
           List<GitHubRepo> repos = response.body();
           
         listView.setAdapter
             (new GitHubRepoAdpter(MainActivity.this,repos));

      @Override
      public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
         Log.e(TAG, "回调失败：" + t.getMessage() + "," + t.toString());
          Toast.makeText(MainActivity.this, "回调失败", Toast.LENGTH_SHORT).show();
      }
 });
````



- **@Query**             请求参数注解，用于Get请求中的参数
- **"id"/"name"**         参数字段，与后台给的字段需要一致
- **long/String**          声明的参数类型
- **idLon/nameStr**       实际参数

添加参数在方法括号内添加@**Query**，后面是参数类型和参数字段，表示后面idLon的取值作为"id"的值，nameStr的取值作为"name"的值，其实就是键值对，Retrofit会把两个字段**拼接**到接口中，追加到"/user"后面。比如：baseUrl为 [api.github.com/](https://link.juejin.cn?target=https%3A%2F%2Fapi.github.com%2F) ，那么拼接网络接口注解中的地址后变为：[api.github.com/user](https://link.juejin.cn?target=https%3A%2F%2Fapi.github.com%2Fuser) ，我们需要传入的id=10006，name="刘亦菲"，那么拼接参数后就是完整的请求地址：`https://api.github.com/user?id=10006&name=刘亦菲`。

**@POST**            请求方法注解，表示采用post方法访问网络请求，括号后面是部分的URL地址

尝试添加新数据项比如发送用户对象

**@PUT**  			用于更新 不会创建用户只是更改现有用户  

<img src="C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221227153701521.png" alt="image-20221227153701521" style="zoom:67%;" />

后台所得到的数据  创建新用户

##### 创建网络请求接口

````java
public interface Api {
    //一个添加数据 一个更新数据
    @POST("user")
    @PUT("user")
    
    //返回一个user类
    //@Body标签：传入的就是一个你创建的对象，把key值设置成该对象的属性，然后value就是该属性对应的值。
    Call<User> createAccount(@Body User user);
}
````

##### 描述用户对象

````java
public class User{
    private int age;
    private String name;
    private String email;
    private String[] topics;
    //创建用户时获得一个整数
    private Integer id;

public User(int age, String name, String email, String[] topics) {
        this.age = age;
        this.name = name;
        this.email = email;
        this.topics = topics;
    }

    public Integer getId() {
        return id;
    }
}
````

> Integer是int的包装类；int是基本数据类型。
> Integer变量必须实例化后才能使用；int变量不需要。
> Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值。
> Integer的默认值是null；int的默认值是0
> 由于Integer变量实际上是对**一个Integer对象的引用**，所以两个通过new生成的Integer变量永远是不相等的（因为new生成的是两个对象，其内存地址不同）。Integer变量和int变量比较时，只要两个变量的值是向等的，则结果为true（因为包装类Integer和基本数据类型int比较时，**Java会自动拆包装为int**，然后进行比较，实际上就变为两个int变量的比较）

##### 将数据传回服务器

（MainActivity)

````java
User user=new User{
    //key值设置成该对象的属性 value就是该属性对应的值 这里是通过key访问value
 name.getText().toString(),
 email.getText().toString(),
    //把整数转换为字符串
 Integer.parseInt(age.getText.toString()),
 topics.getText.toString().spilt(",")
}
//传回服务器 封装成一个方法
sendNetworkRequest(user);

private void sendNetworkRequest(User user){
             //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://10.0.2.2:3000/api/") 
            // 设置网络请求baseUrl
                .addConverterFactory(GsonConverterFactory.create()) 
            //设置使用Gson解析
                .build();

        //创建网络请求接口的实例
        Api Client = retrofit.create(Api.class);
        //对发送请求进行封装
        Call<User> call = client.createAccount(user);

        //发送网络请求(异步)
        call.enqueue(new Callback<User>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //请求处理,输出结果
                //通过response.body()访问服务器
                Toast.makeText(MainActivity.this, "post回调成功:异步执行"+response.body().getId(), Toast.LENGTH_SHORT).show();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                Log.e(TAG, "post回调失败：" + throwable.getMessage() + "," + throwable.toString());
                Toast.makeText(MainActivity.this, "post回调失败", Toast.LENGTH_SHORT).show();
            }
        });
}
````

#### 日志：检查错误 

##### build.gradle(Module:app))添加okhttp日志依赖项

retrofit使用okhttp的默认实例作为网络层

更改了gradle的依赖项需要sync一下<img src="C:\Users\吕洵\AppData\Roaming\Typora\typora-user-images\image-20221227173720872.png" alt="image-20221227173720872" style="zoom:33%;" />

````java
implementation 'com.squareup.okhttp3:okhttp:4.9.1'
````

#####   Mainactivity添加日志拦截器

HttpLoggingInterceptor为我们提供了四种不同的Level，它们分别是NONE, BASIC, HEADERS, BODY
 分别表示

- NONE：没有记录
- BASIC：日志请求类型，URL，请求体的大小，响应状态和响应体的大小
- HEADERS：日志请求和响应头，请求类型，URL，响应状态
- BODY：日志请求和响应标头和正文

````java
private void sendNetworkRequest(User user){
    //创建一个OKHTTP客户端
    //OkHttpClient是一个API
    OkHttpClient.Builder okhttpclientBuilder = new  OkHttpClient.Builder();
    //创建一个日志拦截器
    HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
    //将日志拦截器添加到客户端
    //保证只在开发版本中才会记录 发布版本不会
    if(BuildConfig.DEBUG){
     okhttpclientBuilder.addInterceptor(logging);
}
    //设置日志记录级别
     logging.setlevel(Level.BODY)
  ···
}
````





