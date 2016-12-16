package ydxiaoyuan.myapplication.util;

import android.text.InputFilter;

/**
 * Created by arilpan@qq.com on 2016/10/11 in project MyApplication.
 */

public class TextFilter {
  public static InputFilter[] maxLength(int length) {
    InputFilter[] filter = {
        new InputFilter.LengthFilter(length)
        //, new InputFilter.AllCaps()
    };
    return filter;
  }

  public static InputFilter[] maxLengthAllCaps(int length) {
    InputFilter[] filter = {
        new InputFilter.LengthFilter(length), new InputFilter.AllCaps()
    };
    return filter;
  }
}
