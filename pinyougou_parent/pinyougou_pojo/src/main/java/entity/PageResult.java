package entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果类
 */
public class PageResult implements Serializable {
    //将后台返回前端的数据定义为成员变量

    private long total;//总记录数
    private List rows;//当前页数      （不知道返回的是什么类型，不用泛型）

    public PageResult(long total, List rows) {
        super();       //
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
