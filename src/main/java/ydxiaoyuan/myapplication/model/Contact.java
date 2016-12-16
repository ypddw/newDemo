package ydxiaoyuan.myapplication.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by arilpan@qq.com on 2016/12/1 in project MyApplication4.
 * TODO: Add a class header comment!
 */
public class Contact extends DataSupport {
  private int id;
  private String name;
  private String phone;
  private String address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
