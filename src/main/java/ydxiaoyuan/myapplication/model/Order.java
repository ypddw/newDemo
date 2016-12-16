package ydxiaoyuan.myapplication.model;

import org.litepal.crud.DataSupport;

/**
 * Created by arilpan@qq.com on 2016/12/5 in project MyApplication4.
 * TODO: Add a class header comment!
 */
public class Order extends DataSupport {
  private int id;
  private String data;
  private String address;
  private int state;//0未配送1已取消2已送达3已完成4异常
  private int contact_id;
  private String clientName;
  private String clientPhone;
  private int deskNum;
  private int chairNum;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public int getContact_id() {
    return contact_id;
  }

  public void setContact_id(int contact_id) {
    this.contact_id = contact_id;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getClientPhone() {
    return clientPhone;
  }

  public void setClientPhone(String clientPhone) {
    this.clientPhone = clientPhone;
  }

  public int getDeskNum() {
    return deskNum;
  }

  public void setDeskNum(int deskNum) {
    this.deskNum = deskNum;
  }

  public int getChairNum() {
    return chairNum;
  }

  public void setChairNum(int chairNum) {
    this.chairNum = chairNum;
  }
}
