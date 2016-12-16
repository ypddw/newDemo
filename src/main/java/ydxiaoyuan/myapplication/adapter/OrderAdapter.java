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
import ydxiaoyuan.myapplication.model.Order;

/**
 * Created by arilpan@qq.com on 2016/12/5 in project MyApplication4.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.VH> {
  private Context mContext;
  private LayoutInflater mInflater;
  private List<Order> mItems = new ArrayList<>();

  //private OnItemClickListener mClickListener;

  public OrderAdapter(Context context) {
    mContext = context;
    mInflater = LayoutInflater.from(context);
  }

  public void setDatas(List<Order> items) {
    mItems.clear();
    mItems.addAll(items);
    notifyDataSetChanged();
  }

  public void addTitle(Order bean) {
    mItems.add(bean);
    notifyItemInserted(mItems.size() - 1);
  }

  @Override
  public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.list_order, parent, false);
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
    Order item = mItems.get(position);
    holder.data.setText(item.getData());
    holder.address.setText(item.getAddress());
    holder.desk_num.setText(item.getDeskNum());
    holder.chair_num.setText(item.getChairNum());
    holder.state.setText(item.getState());
    holder.contact.setText(item.getContact_id());
    holder.client_name.setText(item.getClientName());
  }

  @Override
  public int getItemCount() {
    return mItems.size();
  }

  //public void setOnItemClickListener(OnItemClickListener listener) {
  //  mClickListener = listener;
  //}

  class VH extends RecyclerView.ViewHolder {
    private ImageView imgAvatar;
    private TextView data;
    private TextView address;
    private TextView desk_num;
    private TextView chair_num;
    private TextView state;
    private TextView contact;
    private TextView client_name;

    public VH(View itemView) {
      super(itemView);
      data = (TextView) itemView.findViewById(R.id.data);
      address = (TextView) itemView.findViewById(R.id.address);
      desk_num = (TextView) itemView.findViewById(R.id.desk_num);
      chair_num = (TextView) itemView.findViewById(R.id.chair_num);
      state = (TextView) itemView.findViewById(R.id.state);
      contact = (TextView) itemView.findViewById(R.id.contact);
      client_name = (TextView) itemView.findViewById(R.id.client_name);
    }
  }
}
