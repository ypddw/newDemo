package ydxiaoyuan.myapplication.model;

import org.litepal.crud.DataSupport;

/**
 * Created by arilpan@qq.com on 2016/12/1 in project MyApplication4.
 * TODO: Add a class header comment!
 */
public class KV extends DataSupport {
  private int id;
  private String key;
  private String value;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
