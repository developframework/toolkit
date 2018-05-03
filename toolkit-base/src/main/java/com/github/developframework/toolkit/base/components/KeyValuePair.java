package com.github.developframework.toolkit.base.components;

/**
 * @author qiuzhenhao
 */

import lombok.Getter;
import lombok.Setter;

/**
 * 键值对
 */
@Getter
@Setter
public class KeyValuePair<K, V> {

    private K key;

    private V value;

    public KeyValuePair() {
    }

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " -> " + value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if(key != null) {
            hash = hash * 31 + key.hashCode();
        }
        if(value != null) {
            hash = hash * 31 + value.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof KeyValuePair) {
            KeyValuePair otherKeyValuePair = (KeyValuePair) obj;
            if((key == null && otherKeyValuePair.getKey() == null) || key.equals(otherKeyValuePair.getKey())) {
                Object otherValue = otherKeyValuePair.getValue();
                if(value == null && otherValue != null) {
                    return false;
                }
                return value == otherValue || value.equals(otherValue);
            }
        }
        return false;
    }
}
