package ydxiaoyuan.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by arilpan@qq.com on 2016/11/22 in project MyApplication4.
 * lazy fragment
 */
public abstract class BaseLazyFragment extends BaseFragment {
  private boolean mInited = false;
  protected OnBackToFirstListener _mBackToFirstListener;
  private Bundle mSavedInstanceState;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnBackToFirstListener) {
      _mBackToFirstListener = (OnBackToFirstListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement OnBackToFirstListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    _mBackToFirstListener = null;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mSavedInstanceState = savedInstanceState;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    if (savedInstanceState == null) {
      if (!isHidden()) {
        mInited = true;
        initLazyView(null);
      }
    } else {
      // isSupportHidden()仅在saveIns tanceState!=null时有意义,是库帮助记录Fragment状态的方法
      if (!isSupportHidden()) {
        mInited = true;
        initLazyView(savedInstanceState);
      }
    }
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!mInited && !hidden) {
      mInited = true;
      initLazyView(mSavedInstanceState);
    }
  }

  /**
   * 懒加载
   */
  protected abstract void initLazyView(@Nullable Bundle savedInstanceState);

  /**
   * 处理回退事件
   */
  @Override
  public boolean onBackPressedSupport() {
    if (getChildFragmentManager().getBackStackEntryCount() > 1) {
      popChild();
    } else {
      //if (this instanceof ZhihuFirstFragment) {   // 如果是 第一个Fragment 则退出app
      //  _mActivity.finish();
      //} else {                                    // 如果不是,则回到第一个Fragment
      //  _mBackToFirstListener.onBackToFirstFragment();
      //}
    }
    return true;
  }

  public interface OnBackToFirstListener {
    void onBackToFirstFragment();
  }
}
