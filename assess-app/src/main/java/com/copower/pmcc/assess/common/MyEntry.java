package com.copower.pmcc.assess.common;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zch on 2020-2-3.
 */

public class MyEntry<K, V> implements Map.Entry<K, V>, Serializable, Comparable,Cloneable {
    private final K key;
    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyEntry<?, ?> myEntry = (MyEntry<?, ?>) o;

        if (key != null ? !key.equals(myEntry.key) : myEntry.key != null) return false;
        return value != null ? value.equals(myEntry.value) : myEntry.value == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        Integer a = hashCode();
        Integer b = o.hashCode();
        return a.compareTo(b);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "MyEntry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
