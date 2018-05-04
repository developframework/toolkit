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
public class City extends Region {

    private Province province;

    private Map<String, Area> areas = new TreeMap<>();

}
