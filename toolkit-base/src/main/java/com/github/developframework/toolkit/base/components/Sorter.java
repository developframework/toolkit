package com.github.developframework.toolkit.base.components;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 分拣器
 * @author qiuzhenhao
 */
public class Sorter<T> {

    private Map<Integer, Collection<T>> itemBox = new ConcurrentHashMap<>();

    @Setter
    private SortFunction<T> sortFunction;
    @Getter
    private int quantity;

    public Sorter(int quantity) {
        this.quantity = quantity;
        for (int i = 0; i < quantity; i++) {
            itemBox.put(i, new LinkedList<>());
        }
    }

    /**
     * 投递物品
     * @param index
     * @param item
     */
    private void postItem(int index, T item) {
        if(itemBox.containsKey(index)) {
            itemBox.get(index).add(item);
        }else{
            throw new IllegalArgumentException("index is not exist.");
        }
    }

    /**
     * 分拣
     * @param items
     */
    public void sort(Collection<T> items) {
        for (T item : items) {
            int index = sortFunction.sort(item);
            postItem(index, item);
        }
    }

    /**
     * 打开箱子
     * @param index
     * @return
     */
    public List<T> openBox(int index) {
        if(itemBox.containsKey(index)) {
            return new ArrayList<>(itemBox.get(index));
        }else{
            throw new IllegalArgumentException("index is not exist.");
        }
    }

    /**
     * 清空
     */
    public void clear() {
        for (int i = 0; i < quantity; i++) {
            itemBox.get(i).clear();
        }
    }

    @FunctionalInterface
    public interface SortFunction<E> {

        /**
         * 返回需要投递的箱子编号
         * @param item
         * @return
         */
        int sort(E item);
    }
}
