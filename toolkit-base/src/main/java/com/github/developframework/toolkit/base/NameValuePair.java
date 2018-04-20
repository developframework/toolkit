package com.github.developframework.toolkit.base;

/**
 * @author qiuzhenhao
 */

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 键值对
 */
@Getter
@Setter
public class NameValuePair<N, V> {

    private N name;

    private V value;

    public NameValuePair() {
    }

    public NameValuePair(N name, V value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + " -> " + value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = hash * 31 + name.hashCode();
        hash = hash * 31 + value.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof NameValuePair) {
            NameValuePair otherNameValuePair = (NameValuePair) obj;
            Objects.requireNonNull(name, "name not be null.");
            Objects.requireNonNull(otherNameValuePair.getName(), "other name not be null.");
            if(otherNameValuePair.getName().equals(name)) {
                Object otherValue = otherNameValuePair.getValue();
                return otherValue == value || otherValue.equals(value);
            }
        }
        return false;
    }
}
