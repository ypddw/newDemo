package ydxiaoyuan.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.CalendarView;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import ydxiaoyuan.myapplication.event.StartBrotherEvent;
import ydxiaoyuan.myapplication.ui.MainFragment;

/**
 * Toolbar: https://developer.android.com/reference/android/widget/Toolbar.html
 * snackbar: https://developer.android.com/training/snackbar/showing.html
 */
public class MainActivity extends SupportActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //EventBus.getDefault().register(MainActivity.this);

    if (savedInstanceState == null) {
      loadRootFragment(R.id.fl_container, MainFragment.newInstance());
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    //EventBus.getDefault().unregister(MainActivity.this);
  }

  @Override
  public void onBackPressedSupport() {
    // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
    super.onBackPressedSupport();
  }

  @Override
  public FragmentAnimator onCreateFragmentAnimator() {
    // 设置横向(和安卓4.x动画相同)
    return new DefaultHorizontalAnimator();
  }
}
