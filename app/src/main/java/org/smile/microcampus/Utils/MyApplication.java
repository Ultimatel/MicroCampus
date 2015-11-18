package org.smile.microcampus.Utils;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.bmob.v3.Bmob;
import io.rong.imkit.RongIM;

/**
 * Created by guang on 2015/11/15.
 */
public class MyApplication extends Application {
    public static String AppID = "7d6d05f636bb7ef6fe86d91ee0175db2";  //Bmob ID
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化融云
         */
        RongIM.init(this);
        Bmob.initialize(this, AppID);  //初始化 Bmob SDK
        initImageLoader(getApplicationContext());
    }
    /**初始化图片加载类配置信息**/
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions 内存缓存文件的最大长宽
                .threadPoolSize(5) // default  线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2) // default 设置当前线程的优先级
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .memoryCacheSizePercentage(13) // default
                        //.diskCache(new UnlimitedDiskCache(cacheDir)) // default 可以自定义缓存路径
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
                .diskCacheFileCount(100)  // 可以缓存的文件数量
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())  // default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
                .build(); //开始构建
        ImageLoader.getInstance().init(config);
    }
}
