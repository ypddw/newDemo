package ydxiaoyuan.myapplication.base;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.orhanobut.logger.Logger;
import me.yokeyword.fragmentation.SupportFragment;
import ydxiaoyuan.myapplication.MainActivity;
import ydxiaoyuan.myapplication.R;
import ydxiaoyuan.myapplication.ui.MainFragment;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by arilpan@qq.com on 2016/11/22 in project MyApplication4.
 * base fragment with back button & init toolbar
 */
public class BaseFragment extends SupportFragment {
  protected void initToolbarNav(Toolbar toolbar) {
    toolbar.setNavigationIcon(R.drawable.ic_right);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        _mActivity.onBackPressed();
        Logger.i("back img");
        //onBackPressedSupport();
      }
    });

    initToolbarMenu(toolbar);
  }

  protected void setTitle(View view, String title, boolean isSupportBack) {
    setTitleText(view, title);
    setBackSupport(view, true);
  }

  protected void setTitleText(View view, String title) {
    ((TextView) view.findViewById(R.id.title_middle)).setText(title);
  }

  protected void setBackSupport(View view, final boolean isSupport) {
    ((LinearLayout) view.findViewById(R.id.title_ll_left)).setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Logger.d("返回测试");
            if (isSupport) {
              _mActivity.onBackPressed();
            }
          }
        });
  }

  protected void initToolbarMenu(Toolbar toolbar) {
    toolbar.inflateMenu(R.menu.rightadd);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.add_contact:
            _mActivity.showFragmentStackHierarchyView();
            _mActivity.logFragmentStackHierarchy(TAG);
            break;
        }
        return true;
      }
    });
  }
  //File->Settings 或Ctrl + Alt +S 找到 Editor -> Colors &Fonts -> Android Logcat 或在上面的搜索框中输入Logcat 点中Verbose , Info, Debug等选项，然后在后面将Use Inberited attributes 去掉勾选 再将 Foreground 前的复选框选上，就可以双击后面的框框去选择颜色了 Apply–>OK

  /**
   * 处理回退事件
   */
  @Override
  public boolean onBackPressedSupport() {
    Logger.d(" base fragment onBackPressedSupport");
    BaseFragment baseFragment;
    if (getChildFragmentManager().getBackStackEntryCount() > 1) {
      popChild();
    } else {
      if (this instanceof MainFragment) {   // 如果是 第一个Fragment 则退出app
        _mActivity.finish();
        Logger.d(" first fragment onBackPressedSupport-->finish");
      } else {                                    // 如果不是,则回到第一个Fragment
        //_mBackToFirstListener.onBackToFirstFragment();
        Logger.d(" not first fragment onBackPressedSupport-->pop a fragment ");
      }
    }
    return false;
  }
}
