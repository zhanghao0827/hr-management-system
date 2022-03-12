package per.hr.resource.manage.sys.utils;

import java.util.ArrayList;
import java.util.List;

public class PageUtils<T> {

    private int pageIndex;
    private int pageSize;
    private int pageCount;
    private int totalCount;
    private List<T> list;//每页数据集合
    private T t;//查询条件对象
    private int start;
    private int end;

    //序号的集合
    private List<Integer> numbers = new ArrayList<Integer>();

    public PageUtils(int pageIndex, int pageSize, int totalCount, List<T> list, T t) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.list = list;
        this.t = t;
        this.pageCount = (this.totalCount % this.pageSize) == 0 ? (this.totalCount / this.pageSize) : (this.totalCount / this.pageSize) + 1;

        if (this.pageCount <= 10) {
            this.start = 1;
            this.end = pageCount;
        } else {
            this.start = pageIndex - 4;
            this.end = pageIndex + 5;
            if (start < 1) {
                this.start = 1;
                this.end = 10;
            } else if (end > pageCount) {
                this.end = pageCount;
                this.start = pageCount - 9;
            }
        }

        // 一共显示10个页面 动态伸缩
        for (int i = start; i <= end; i++) {
            numbers.add(i);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
