package ydxiaoyuan.myapplication.ui.stock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.orhanobut.logger.Logger;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;
import ydxiaoyuan.myapplication.R;
import ydxiaoyuan.myapplication.base.BaseFragment;
import ydxiaoyuan.myapplication.model.Contact;
import ydxiaoyuan.myapplication.model.KV;
import ydxiaoyuan.myapplication.ui.contact.ContactFragment;
import ydxiaoyuan.myapplication.util.TextFilter;

import static ydxiaoyuan.myapplication.R.id.contact;
import static ydxiaoyuan.myapplication.R.id.ref_addr;
import static ydxiaoyuan.myapplication.R.id.ref_name;
import static ydxiaoyuan.myapplication.R.id.ref_phone;

/**
 * Created by arilpan@qq.com on 2016/12/14 in project MyApplication4.
 * TODO: Add a class header comment!
 */
public class StockManagerFragment extends BaseFragment {
  private String id;
  private EditText stockDeskEditText;
  private EditText stockChairEditText;
  private Button btnSubmit;
  private String stock_desk;
  private String stock_chair;

  public static StockManagerFragment newInstance() {
    Bundle args = new Bundle();
    StockManagerFragment fragment = new StockManagerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public static StockManagerFragment newInstance(String id) {
    Bundle args = new Bundle();
    args.putString("contact_id", id);
    StockManagerFragment fragment = new StockManagerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onDestroy() {
    EventBus.getDefault().unregister(this);
    super.onDestroy();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    if (args != null) {
      id = args.getString("id");
    }
  }

  private View view;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_stock_manager, container, false);
    setTitleText(view, "库存管理");
    setBackSupport(view, true);
    initView(view);
    return view;
  }

  private ImageView btn_iv_right;

  public void initView(final View view) {
    btn_iv_right = (ImageView) view.findViewById(R.id.btn_iv_right);
    btn_iv_right.setVisibility(View.GONE);

    stockDeskEditText = (EditText) view.findViewById(R.id.stock_desk);
    stockChairEditText = (EditText) view.findViewById(R.id.stock_chair);
    btnSubmit = (Button) view.findViewById(R.id.btn_submit);
    btnSubmit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        changeStock();
      }
    });

    stockDeskEditText.setFilters(TextFilter.maxLength(4));
    stockChairEditText.setFilters(TextFilter.maxLength(4));
    List<KV> kvs2 = DataSupport.findAll(KV.class);
    Logger.e("all kvs size:"+kvs2.size());
    List<KV> kvs = DataSupport.where("key like ?", "%stock_%").order("id").find(KV.class);
    Logger.e("kvs size:"+kvs.size());
    for (KV kv : kvs) {
      Logger.e("key:"+kv.getKey()+",value:"+kv.getValue());
      if ("stock_desk".equals(kv.getKey())) {
        stockDeskEditText.setText(kv.getValue());
      }
      if ("stock_chair".equals(kv.getKey())) {
        stockChairEditText.setText(kv.getValue());
      }
    }
  }

  public void changeStock() {
    btnSubmit.setEnabled(false);
    try {
      stock_desk = stockDeskEditText.getText().toString();
      stock_chair = stockChairEditText.getText().toString();

      KV kv = new KV();
      kv.setValue(stock_desk);
      kv.updateAll("key = ?", "stock_desk");
      kv = new KV();
      kv.setValue(stock_chair);
      kv.updateAll("key = ?", "stock_chair");

      //提示成功，页面退出，跳转到联系人列表页
      Snackbar.make(view.findViewById(R.id.snack_parent), "修改成功！", Snackbar.LENGTH_SHORT).show();
      startWithPop(StockManagerFragment.newInstance());
    } catch (Exception e) {
      e.printStackTrace();
      Snackbar.make(view.findViewById(R.id.snack_parent), "修改失败！", Snackbar.LENGTH_SHORT).show();
    } finally {
      btnSubmit.setEnabled(true);
    }
  }

  @Override
  public boolean onBackPressedSupport() {
    Logger.e("on back press");
    return false;
  }
}
