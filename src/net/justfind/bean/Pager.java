package net.justfind.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <br>
 * <b>功能：</b>Bean类 - 分页<br>
 * <b>作者：</b>xiaogl<br>
 * <b>日期：</b> 2015年5月21日 <br>
 * <b>版权所有：<b>版权所有(C) 2015，快找网络<br>
 */

public class Pager implements Serializable{
    private static final long serialVersionUID = 5666943270925530640L;
    public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

    // 排序方式（递增、递减）
    public enum Order{
        asc, desc
    }

    private int pageNumber = 1;// 当前页码
    private int pageSize = 10;// 每页记录数
    private String searchBy;// 查找字段
    private String keyword;// 查找关键字
    private String orderBy;// 排序字段
    private Order order;// 排序方式

    // 2012-06-21 许绍杰
    private String timeBy; // 时间查询条件
    private Date startTime; // 起始时间
    private Date endTime; // 结束时间

    private int totalCount;// 总记录数
    private List<?> result;// 返回结果

    // 获取总页数
    public int getPageCount() {
        int pageCount = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            pageCount++;
        }
        return pageCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        } else if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTimeBy() {
        return timeBy;
    }

    public void setTimeBy(String timeBy) {
        this.timeBy = timeBy;
    }
}
