package com.baeldung.crud.DTO;

// for pagination in gallery
public class GalleryPagination {
    public int pageNum; // number of page
    public int pageRow; //  max row numbers
    public int pageTotal;  // total number of pages

    public GalleryPagination() {
    }

    public GalleryPagination(int pageNum, int pageRow, int pageTotal) {
        this.pageNum = pageNum;
        this.pageRow = pageRow;
        this.pageTotal = pageTotal;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageRow() {
        return pageRow;
    }

    public void setPageRow(int pageRow) {
        this.pageRow = pageRow;
    }
}
