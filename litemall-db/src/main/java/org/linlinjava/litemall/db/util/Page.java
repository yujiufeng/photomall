package org.linlinjava.litemall.db.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 查询显示页面数据类,用来存放分页查询中每一页的数据.
 * 页面数据类用来存放查询翻页过程中的数据，里面存放指定条数的数据，数据中包含哪了结果集中的部分数据。<br>
 * 页面数据类中包含数据信息以及页面属性信息,其中包括开始的记录数和总记录数,页面大小等信息.
 */

public class Page implements Serializable {

	final static int DEFAULTPAGESIZE = 10;

	private static final long serialVersionUID = 1L;

	public static final Page EMPTY_PAGE = new Page(Collections.EMPTY_LIST, 0,0,0);

	// 结果数据列表
	private List resultList = null;

	// 页面开始记录数
	private int start = 0;

	// 结果总记录数
	private int totalSize = 0;

	// 页面数据大小.
	private int pageSize = DEFAULTPAGESIZE;

	public Page() {
		
	}
	/**
	 * 通过给定的参数构建页面数据对象.
	 * 
	 * @param resultList
	 *            结果集数据列表
	 * @param start
	 *            数据集中第一个数据对象的编号．
	 * @param totalSize
	 *            总的数据记录数
	 * @param pageSize
	 *            页面数据大小
	 */
	public Page(List resultList, int start, int pageSize, int totalSize) {
		this.resultList = resultList;
		this.start = start;
		this.totalSize = totalSize;
		this.pageSize = pageSize;
	}

	/**
	 * 当前页面对象是否存在下一个页面对象.
	 * 
	 * @return boolean 是否存在下一个页面对象
	 */
	public boolean hasNextPage() {
		return (start + resultList.size()) < totalSize;
	}

	/**
	 * 当前页面对象是否存在前一个页面对象.
	 * 
	 * @return boolean 是否存在前一个页面对象
	 */
	public boolean hasPreviousPage() {
		return start > 0;
	}

	/**
	 * 当前页面对象的下一个页面对象的数据记录编号.
	 * 
	 * @return int 下一个页面对象的数据记录编号.
	 */
	public int getStartOfNextPage() {
		return start + resultList.size();
	}

	/**
	 * 取得当前页面对象的前一个对象页数.
	 * 
	 * @return int 前一个对象页数
	 */
	public int getStartOfPreviousPage() {
		return Math.max(start - pageSize, 0);
	}

	/**
	 * 取得总页数.
	 * 
	 * @return int 总页数
	 */
	public int getPageCount() {
		int ret = 0;
		if (pageSize > 0) {
			if (totalSize % pageSize == 0) {
				ret = totalSize / pageSize;
			} else {
				ret = totalSize / pageSize + 1;
			}

		}

		return ret;
	}

	/**
	 * 取得当前页数.
	 * 
	 * @return int 当前页数 
	 */
	public int getCurPageNum() {
		int ret = 0;
		if (pageSize > 0)
			ret = start / pageSize + 1;
		return ret;
	}

	/**
	 * 取得当前页数对象的数据列表的数据大小.
	 * 
	 * @return int 数据大小
	 */
	public int getSize() {
		return resultList.size();
	}

	/**
	 * 取得页面数据的大小.
	 * 
	 * @return int 页数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置页面数据的大小.
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 取得当前页面数据中的数据列表．
	 * 
	 * @return List 数据列表
	 */
	public List getResultList() {
		return resultList;
	}

	/**
	 * 设置数据对象中的数据列表．
	 * 
	 * @param resultList
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	/**
	 * 取得当前页数据对象的第一个数据的编号．
	 * 
	 * @return int 第一个数据的编号
	 */
	public int getStart() {
		return start;
	}

	/**
	 * 设置当前页数据对象的第一个对象的数据的编号．
	 * 
	 * @param start
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * 取得所有数据的总记录数．
	 * 
	 * @return int 所有数据的总记录数
	 */
	public int getTotalSize() {
		return totalSize;
	}

	/**
	 * 设置数据的总记录数．
	 * 
	 * @param totalSize
	 */
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}
