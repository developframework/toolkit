package com.github.developframework.toolkit.base.region;

import com.github.developframework.toolkit.base.exception.ToolkitException;
import lombok.Getter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * 行政区模块
 * @author qiuzhenhao
 */
public class RegionModule {

    @Getter
    private Country country;

    public RegionModule() {
        try(ObjectInputStream ois = new ObjectInputStream(RegionModule.class.getResourceAsStream("/region.dat"))) {

            country = (Country) ois.readObject();

        } catch(IOException e) {
            throw new ToolkitException("read \"region.dat\" failed.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Map<String, Province> getAllProvincesInCountry() {
        return country.getProvinces();
    }

    public Map<String, City> getAllCitiesInProvince(String provinceCode) {
        return getProvince(provinceCode).getCities();
    }

    public Map<String, Area> getAllAreaInCity(String provinceCode, String cityCode) {
        return getProvince(provinceCode).getCities().get(cityCode).getAreas();
    }

    public Province getProvince(String provinceCode) {
        Province province = country.getProvinces().get(provinceCode);
        if (province == null) {
            throw new ToolkitException("province is not exist by code: " + provinceCode);
        }
        return province;
    }

    public Region getRegion(String code) {
        Region region = country.getAllRegions().get(code);
        if (region == null) {
            throw new ToolkitException("region is not exist by code: " + code);
        }
        return region;
    }

}
