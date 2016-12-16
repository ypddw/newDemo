package ydxiaoyuan.myapplication.ui.contact;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.orhanobut.logger.Logger;
import org.greenrobot.eventbus.EventBus;
import ydxiaoyuan.myapplication.R;
import ydxiaoyuan.myapplication.base.BaseFragment;
import ydxiaoyuan.myapplication.model.Contact;
import ydxiaoyuan.myapplication.util.TextFilter;

import static ydxiaoyuan.myapplication.R.id.add;
import static ydxiaoyuan.myapplication.R.id.btn_iv_right;
import static ydxiaoyuan.myapplication.R.id.progress_horizontal;

/**
 * Created by arilpan@qq.com on 2016/12/14 in project MyApplication4.
 * TODO: Add a class header comment!
 */
public class AddContactFragment extends BaseFragment {
  private String id;
  private EditText refNameEditText;
  private EditText refAddrEditText;
  private EditText refPhoneEditText;
  private Button btnSubmit;
  private String ref_name;
  private String ref_addr;
  private String ref_phone;

  public static AddContactFragment newInstance() {
    Bundle args = new Bundle();
    AddContactFragment fragment = new AddContactFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public static AddContactFragment newInstance(String id) {
    Bundle args = new Bundle();
    args.putString("contact_id", id);
    AddContactFragment fragment = new AddContactFragment();
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
    view = inflater.inflate(R.layout.fragment_add_contact, container, false);
    setTitleText(view, "添加联系人");
    setBackSupport(view, true);
    initView(view);
    return view;
  }

  private ImageView btn_iv_right;

  public void initView(final View view) {
    btn_iv_right = (ImageView) view.findViewById(R.id.btn_iv_right);
    btn_iv_right.setVisibility(View.GONE);

    refAddrEditText = (EditText) view.findViewById(R.id.ref_input_addr);
    refNameEditText = (EditText) view.findViewById(R.id.ref_input_name);
    refPhoneEditText = (EditText) view.findViewById(R.id.ref_input_phone);
    btnSubmit = (Button) view.findViewById(R.id.btn_submit);
    btnSubmit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        addContact();
      }
    });

    refPhoneEditText.setFilters(TextFilter.maxLength(11));
  }

  public void addContact() {
    btnSubmit.setEnabled(false);
    try {
      ref_addr = refAddrEditText.getText().toString();
      ref_name = refNameEditText.getText().toString();
      ref_phone = refPhoneEditText.getText().toString();
      Contact contact = new Contact();
      contact.setAddress(ref_addr);
      contact.setName(ref_name);
      contact.setPhone(ref_phone);
      contact.save();
      //提示成功，页面退出，跳转到联系人列表页
      Snackbar.make(view.findViewById(R.id.snack_parent), "添加推荐人成功！", Snackbar.LENGTH_SHORT).show();
      startWithPop(ContactFragment.newInstance( ));
    } catch (Exception e) {
      e.printStackTrace();
      Snackbar.make(view.findViewById(R.id.snack_parent), "添加推荐人失败！", Snackbar.LENGTH_SHORT).show();
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
