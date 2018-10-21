package com.merit.model;

/**<p>方法 BaseUser : <p>
*<p>说明:用户基本类</p>
*<pre>
*@author JackZhou
*@date 2017/4/21 14:49
</pre>
*/
public class BaseUser implements java.io.Serializable {
    private String id;
    private String userId;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
