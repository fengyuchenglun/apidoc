package com.github.fengyuchenglun.apidoc.core.schema;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 多个数据的组合
 *
 * @param <T> the type parameter
 * @author duanledexianxianxian
 */
@Data
public class Cell<T> {

    /**
     * The Values.
     */
    private List<T> values;

    /**
     * The Enable.
     */
    private boolean enable;

    /**
     * Instantiates a new Cell.
     *
     * @param values the values
     */
    @SafeVarargs
    public Cell(T... values) {
        this(true, values);
    }

    /**
     * Instantiates a new Cell.
     *
     * @param enable the enable
     * @param values the values
     */
    @SafeVarargs
    public Cell(boolean enable, T... values) {
        this(enable, Lists.newArrayList(values));
    }

    /**
     * Instantiates a new Cell.
     *
     * @param enable the enable
     * @param values the values
     */
    public Cell(boolean enable, List<T> values) {
        this.values = values;
        this.enable = enable;
    }

    /**
     * To list list.
     *
     * @return the list
     */
    public List<T> toList() {
        return values;
    }

    /**
     * Is enable boolean.
     *
     * @return the boolean
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Add.
     *
     * @param value the value
     */
    public void add(T value) {
        values.add(value);
    }


    /**
     * 指定位置插入元素.
     *
     * @param index the index
     * @param value the value
     */
    public void add(Integer index, T value) {
        values.add(index, value);
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return values.size();
    }

    /**
     * Set.
     *
     * @param index the index
     * @param t     the t
     */
    public void set(int index, T t) {
        values.set(index, t);
    }

    /**
     * Get t.
     *
     * @param index the index
     * @return the t
     */
    public T get(int index) {
        return values.get(index);
    }

    /**
     * Duplicate cell.
     *
     * @return the cell
     */
    public Cell<T> duplicate() {
        return new Cell<>(isEnable(), Lists.newArrayList(values));
    }

    /**
     * Sets enable.
     *
     * @param enable the enable
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
