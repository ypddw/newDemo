package ydxiaoyuan.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import ydxiaoyuan.myapplication.R;
import ydxiaoyuan.myapplication.model.Contact;

/**
 * Created by arilpan@qq.com on 2016/12/5 in project MyApplication4.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.VH> {
  private Context mContext;
  private LayoutInflater mInflater;
  private List<Contact> mItems = new ArrayList<>();

  //private OnItemClickListener mClickListener;

  public ContactAdapter(Context context) {
    mContext = context;
    mInflater = LayoutInflater.from(context);
  }

  public void setDatas(List<Contact> items) {
    mItems.clear();
    mItems.addAll(items);
    notifyDataSetChanged();
  }

  public void addTitle(Contact bean) {
    mItems.add(bean);
    notifyItemInserted(mItems.size() - 1);
  }

  @Override
  public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.list_contact, parent, false);
    final VH holder = new VH(view);
    //holder.itemView.setOnClickListener(new View.OnClickListener() {
    //  @Override
    //  public void onClick(View v) {
    //    if (mClickListener != null) {
    //      mClickListener.onItemClick(holder.getAdapterPosition(), v, holder);
    //    }
    //  }
    //});
    return holder;
  }

  @Override
  public void onBindViewHolder(VH holder, int position) {
    Contact item = mItems.get(position);
    //if (item != null) {
      holder.contact_addr.setText(item.getAddress());
      holder.contact_name.setText(item.getName());
      holder.contact_phone.setText(item.getPhone());
    //}
  }

  @Override
  public int getItemCount() {
    return mItems.size();
  }

  //public void setOnItemClickListener(OnItemClickListener listener) {
  //  mClickListener = listener;
  //}

  class VH extends RecyclerView.ViewHolder {
    private TextView contact_addr;
    private TextView contact_name;
    private TextView contact_phone;

    public VH(View itemView) {
      super(itemView);
      contact_phone = (TextView) itemView.findViewById(R.id.contact_phone);
      contact_addr = (TextView) itemView.findViewById(R.id.contact_addr);
      contact_name = (TextView) itemView.findViewById(R.id.contact_name);
    }
  }
}
