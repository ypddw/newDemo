package ydxiaoyuan.myapplication.util;

import android.content.SharedPreferences;
import com.orhanobut.logger.Logger;
import java.util.List;
import org.litepal.crud.DataSupport;
import ydxiaoyuan.myapplication.BaseApplication;
import ydxiaoyuan.myapplication.model.KV;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by arilpan@qq.com on 2016/12/16 in project MyApplication4.
 * TODO: Add a class header comment!
 */
public class InitUtil {
  public static void init() {
    SharedPreferences sharedPreferences =
        BaseApplication.getInstance().getSharedPreferences("app", MODE_PRIVATE);
    if (sharedPreferences.getBoolean("is_first_loading", true)) {
      sharedPreferences.edit().putBoolean("is_first_loading", false).commit();
      initData();
    }
  }
  public static void initData()
  {
    Logger.e("init data ");
    KV kv = new KV();
    kv.setKey("stock_desk");
    kv.setValue("0");
    kv.save();
    kv = new KV();
    kv.setKey("stock_chair");
    kv.setValue("0");
    kv.save();
    List<KV> kvs2 = DataSupport.findAll(KV.class);
    Logger.e("all kvs size - in init:"+kvs2.size());
  }
}
