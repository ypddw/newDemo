package ydxiaoyuan.myapplication;

import android.app.Application;
import com.orhanobut.logger.Logger;
import org.litepal.LitePal;
import ydxiaoyuan.myapplication.util.InitUtil;

/**
 * Created by arilpan@qq.com on 2016/11/15 in project MyApplication4.
 * TODO: Add a class header comment!
 */
public class BaseApplication extends Application {
  private static String TAG = "arilpan";
  private static BaseApplication app;

  public static BaseApplication getInstance() {
    return app;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    app = this;
    InitUtil.init();
    Logger.init(TAG);
    Logger.d("BaseApplication跑起来！");
    LitePal.initialize(this);

    //初始化全局异常信息捕获，捕获异常后写入本地文件
    //CrashHandler crashHandler = CrashHandler.getInstance();
    //crashHandler.init(getApplicationContext());
  }
}
