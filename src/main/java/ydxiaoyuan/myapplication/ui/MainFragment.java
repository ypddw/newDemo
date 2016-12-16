package ydxiaoyuan.myapplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import ydxiaoyuan.myapplication.Constant;
import ydxiaoyuan.myapplication.R;
import ydxiaoyuan.myapplication.adapter.OrderAdapter;
import ydxiaoyuan.myapplication.base.BaseFragment;
import ydxiaoyuan.myapplication.event.StartBrotherEvent;
import ydxiaoyuan.myapplication.model.Order;
import ydxiaoyuan.myapplication.ui.contact.AddContactFragment;
import ydxiaoyuan.myapplication.ui.contact.ContactFragment;
import ydxiaoyuan.myapplication.ui.stock.StockManagerFragment;

/**
 * Created by arilpan@qq.com on 2016/12/15 in project MyApplication4.
 * TODO: Add a class header comment!
 */
public class MainFragment extends BaseFragment {
  CalendarView calendar;
  RecyclerView info_list;

  Context context;

  public static MainFragment newInstance() {
    Bundle args = new Bundle();
    MainFragment fragment = new MainFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventBus.getDefault().register(this);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  public void initView(View view) {
    info_list = (RecyclerView) view.findViewById(R.id.relist);
    List<Order> datas = new ArrayList<>();
    Order order = new Order();
    order.setAddress("红旗一村");
    order.setData("20161208");
    order.setDeskNum(10);
    order.setChairNum(80);
    order.setContact_id(0);
    order.setClientName("张三");
    order.setClientPhone("15826666277");
    order.setState(Constant.STATA_COMPLETE);
    datas.add(order);
    OrderAdapter orderAdapter = new OrderAdapter(context);
    orderAdapter.setDatas(datas);

    calendar = (CalendarView) view.findViewById(R.id.calendar);
    calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
      @Override
      public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        // TODO Auto-generated method stub
        String date = year + "年" + month + 1 + "月" + dayOfMonth + "日";
        Toast.makeText(context, date, Toast.LENGTH_SHORT).show();
      }
    });
    //startActivity(new Intent(Intent.ACTION_VIEW).setDataAndType(null, MainActivity.MIME_TYPE));
    //startActivityForResult(new Intent(Intent.ACTION_PICK).
    //    setDataAndType(null, CalendarActivity.MIME_TYPE), 100);
  }

  public void initToolbar(final View view) {
    Toolbar myToolbar = (Toolbar) view.findViewById(R.id.my_toolbar);
    myToolbar.inflateMenu(R.menu.rightadd);//设置右上角的填充菜单
    myToolbar.setTitle("管理工具");

    //setSupportActionBar(myToolbar);
    myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        int menuItemId = item.getItemId();
        if (menuItemId == R.id.add_contact) {
          Logger.d("add_contact");
          //Snackbar.make(view.findViewById(R.id.snack_parent), "add_contact", Snackbar.LENGTH_SHORT)
          //    .show();
          EventBus.getDefault().post(new StartBrotherEvent(AddContactFragment.newInstance()));
        } else if (menuItemId == R.id.add_schedule) {
          Logger.d("add_schedule");
          Snackbar.make(view.findViewById(R.id.snack_parent), "add_schedule", Snackbar.LENGTH_SHORT)
              .show();
        } else if (menuItemId == R.id.list_contact) {
          Logger.d("list_contact");
          EventBus.getDefault().post(new StartBrotherEvent(ContactFragment.newInstance()));
          //Snackbar.make(view.findViewById(R.id.snack_parent), "list_contact", Snackbar.LENGTH_SHORT)
          //    .show();
        } else if (menuItemId == R.id.list_schedule) {
          //ContactFragment.newInstance("1");

          Logger.d("list_schedule");
          Snackbar.make(view.findViewById(R.id.snack_parent), "list_schedule",
              Snackbar.LENGTH_SHORT).show();
        } else if (menuItemId == R.id.list_stack) {
          Logger.d("list_stack");
          Snackbar.make(view.findViewById(R.id.activity_main), R.string.click_list_contact,
              Snackbar.LENGTH_SHORT).show();
        } else if (menuItemId == R.id.stock_manager) {
          Logger.d("stock_manager");
          //Snackbar.make(view.findViewById(R.id.activity_main), R.string.click_list_contact,
          //    Snackbar.LENGTH_SHORT).show();
          EventBus.getDefault().post(new StartBrotherEvent(StockManagerFragment.newInstance()));
        }
        return true;
      }
    });
  }

  @Subscribe
  public void startBrother(StartBrotherEvent event) {
    start(event.targetFragment);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    initToolbar(view);
    initView(view);
    return view;
  }
}
