package com.github.developframework.toolkit.base.region;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author qiuzhenhao
 */
@Getter
@Setter
public class Province extends Region {

    private Country country;

    private Map<String, City> cities = new TreeMap<>();

}
