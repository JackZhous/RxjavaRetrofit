[![](https://www.jitpack.io/v/JackZhous/RxjavaRetrofit.svg)](https://www.jitpack.io/#JackZhous/RxjavaRetrofit)

# Rxjava2 Retrofit2 组成的网络框架和示范demo（可直接使用）

## app模块是demo

## netlibrary模块是库，可以直接在gradle里面添加如下使用

Step 1. Add the JitPack repository to your build file

```
	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```
dependencies {
	        compile 'com.github.JackZhous:RxjavaRetrofit:1.0'
	}
```

## 库使用方法简单介绍

1. 构建网络访问接口
```java

public interface HttpManager {

    String BASE_URL = "你的基础url";  //必填
    String COOKIE = "你的cookie";	//非必填

    @FormUrlEncoded
    @POST("xianshi/list")
    Observable<TaskListResponse> getTaskList(@Field("param") String param);

    final class Factory{

        public static HttpManager getHttpManager(){
            NetClient client = new NetClient(){
                @Override
                public String getCookies() {
                    return COOKIE;
                }

                @Override
                public String getBaseUrl() {
                    return BASE_URL;
                }
            };

            HttpManager manager = client.getNetClient(HttpManager.class);
            return  manager;
        }
    }

}
```

2. 使用网络接口访问

```java
	String param = "jqJJyn3G1T8o+GzKfuicOQ";
        HttpManager manager = HttpManager.Factory.getHttpManager();
        manager.getTaskList(param)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TaskListResponse value) {
                        tv.setText(value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        tv.setText("error");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
```
至此，完成；
