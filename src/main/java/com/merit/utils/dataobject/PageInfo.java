package com.merit.utils.dataobject;

import com.merit.utils.constants.CommonConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by R on 2018/7/12.
 */
public class PageInfo<T> implements Serializable {

    /**当前起始记录*/
    private long currentIndex = 0L;
    /*总记录条数*/
    private long totalCount = 0L;
    /*当前页码*/
    private long currentPage = 1L;
    /*总页码*/
    private long totalPage = 0L;
    /*默认每页大小*/
    private long pageSize = CommonConstants.PAGERESULTINFO_PAGE_SIZE;
    /*数据记录*/
    private List<T> records = new ArrayList(0);

    /**
     * <p>方法:getCurrentIndex 获取起始序号 </p>
     * <ul>
     * <li>@return long  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 17:11  </li>
     * </ul>
     */
    public long getCurrentIndex() {
        return this.currentIndex;
    }

    public void setCurrentIndex(long currentIndex) {
        this.currentIndex = currentIndex;
    }

    /**
     * <p>方法:getTotalCount 总记录 </p>
     * <ul>
     * <li>@return long  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 17:12  </li>
     * </ul>
     */
    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * <p>方法:getCurrentPage 当前页码 </p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return long  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 17:12  </li>
     * </ul>
     */
    public long getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * <p>方法:getTotalPage 总页码 </p>
     * <ul>
     * <li>@return long  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 17:12  </li>
     * </ul>
     */
    public long getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(long countPage) {
        this.totalPage = countPage;
    }

    /**
     * <p>方法:getPageSize 每页记录数 </p>
     * <ul>
     * <li>@return long  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 17:12  </li>
     * </ul>
     */
    public long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * <p>方法:getRecords 获取页内记录 </p>
     * <ul>
     * <li>@return java.util.List<T>  T类型的List列表 </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 17:08  </li>
     * </ul>
     */
    public List<T> getRecords() {
        return this.records;
    }

    /**
     * <p>方法:setRecords 具体数据记录，如果为空，则返回空List </p>
     * <ul>
     * <li> @param records </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 17:07  </li>
     * </ul>
     */
    public void setRecords(List<T> records) {
        if (records == null)
            this.records = new ArrayList(0);
        else
            this.records = records;
    }
}
