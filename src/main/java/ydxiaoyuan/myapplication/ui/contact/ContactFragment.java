package ydxiaoyuan.myapplication.ui.contact;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;
import ydxiaoyuan.myapplication.R;
import ydxiaoyuan.myapplication.adapter.ContactAdapter;
import ydxiaoyuan.myapplication.base.BaseFragment;
import ydxiaoyuan.myapplication.event.StartBrotherEvent;
import ydxiaoyuan.myapplication.model.Contact;

import static org.litepal.crud.DataSupport.where;
import static ydxiaoyuan.myapplication.R.id.btn_iv_right;
import static ydxiaoyuan.myapplication.R.id.start;

/**
 * Created by arilpan@qq.com on 2016/12/14 in project MyApplication4.
 * TODO: Add a class header comment!
 */
public class ContactFragment extends BaseFragment {
  private String id;
  private RecyclerView list;
  private ContactAdapter adapter;
  private List<Contact> contacts;

  public static ContactFragment newInstance() {
    Bundle args = new Bundle();
    ContactFragment fragment = new ContactFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public static ContactFragment newInstance(String id) {
    Bundle args = new Bundle();
    args.putString("contact_id", id);
    ContactFragment fragment = new ContactFragment();
    fragment.setArguments(args);
    return fragment;
  }

  Context context;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
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

  private ImageView btn_iv_right;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
    setTitleText(view, "推荐人列表");
    setBackSupport(view, true);
    initView(view);

    return view;
  }

  public void initView(final View view) {
    btn_iv_right = (ImageView) view.findViewById(R.id.btn_iv_right);
    btn_iv_right.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        go2AddFragment();
      }
    });

    list = (RecyclerView) view.findViewById(R.id.list);
    adapter = new ContactAdapter(context);
  }

  public void fillList() {
    //List<Contact> songs = DataSupport.where("name like ?", "song%").order("id").find(Contact.class);
    contacts = DataSupport.findAll(Contact.class);
    //解析并设置到recyclelist
    list.setLayoutManager(new LinearLayoutManager(_mActivity));
    list.setAdapter(adapter);
    adapter.setDatas(contacts);
  }

  public void go2AddFragment() {
    startWithPop(AddContactFragment.newInstance());
    //EventBus.getDefault().post(new StartBrotherEvent(AddContactFragment.newInstance("")));
  }

  @Override
  protected void onEnterAnimationEnd(Bundle savedInstanceState) {
    super.onEnterAnimationEnd(savedInstanceState);

    fillList();
  }

  @Override
  public boolean onBackPressedSupport() {
    Log.e("arilpan", "on back press");
    return false;
  }
}
