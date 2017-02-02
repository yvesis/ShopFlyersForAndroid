package com.yvesis.flyers.core.models;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by louly on 2017-01-30.
 */

public class FlyerCategory implements Iterable<FlyerItem>, List<FlyerItem> {
    private List<FlyerItem> flyerItems;
    private String name;

    public FlyerCategory(String name){

        flyerItems = new ArrayList<>();
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int size() {
        return flyerItems.size();
    }

    @Override
    public boolean isEmpty() {
        return flyerItems.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return flyerItems.contains(o);
    }

    @Override
    public Iterator<FlyerItem> iterator() {
        return flyerItems.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return flyerItems.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] a) {
        return flyerItems.toArray(a);
    }

    @Override
    public boolean add(FlyerItem flyerItem) {
        return flyerItems.add(flyerItem);
    }

    @Override
    public boolean remove(Object o) {
        return flyerItems.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return flyerItems.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends FlyerItem> c) {
        return flyerItems.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends FlyerItem> c) {
        return flyerItems.addAll(index,c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return flyerItems.retainAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return flyerItems.retainAll(c);
    }

    @Override
    public void clear() {
        flyerItems.clear();
    }

    @Override
    public FlyerItem get(int index) {
        return flyerItems.get(index);
    }

    @Override
    public FlyerItem set(int index, FlyerItem element) {
        return flyerItems.set(index,element);
    }

    @Override
    public void add(int index, FlyerItem element) {
        flyerItems.add(index,element);
    }

    @Override
    public FlyerItem remove(int index) {
        return flyerItems.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return flyerItems.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return flyerItems.lastIndexOf(o);
    }

    @Override
    public ListIterator<FlyerItem> listIterator() {
        return flyerItems.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<FlyerItem> listIterator(int index) {
        return flyerItems.listIterator(index);
    }

    @NonNull
    @Override
    public List<FlyerItem> subList(int fromIndex, int toIndex) {
        return flyerItems.subList(fromIndex,toIndex);
    }
}
