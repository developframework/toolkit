package com.github.developframework.toolkit.base.region;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author qiuzhenhao
 */
@Getter
@Setter
public class Country extends Region {

    private Map<String, Province> provinces = new TreeMap<>();

    private Map<String, Region> allRegions = new HashMap<>();

    public List<Area> allAreas() {
        return allRegions.values().stream().filter(region -> region instanceof Area).map(region -> (Area) region).collect(Collectors.toList());
    }
}
