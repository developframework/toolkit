package com.github.developframework.toolkit.persistence.component;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Constructor;

@Component
@Slf4j
public class PagerArgumentResolver implements HandlerMethodArgumentResolver {

    private String indexKey;

    private String sizeKey;

    private Class<? extends Pager> pagerClass;

    public PagerArgumentResolver(Class<? extends Pager> pagerClass) {
        log.info("Application loaded PagerArgumentResolver.");
        this.indexKey = "pi";
        this.sizeKey = "ps";
        this.pagerClass = pagerClass;
    }

    public PagerArgumentResolver(Class<? extends Pager> pagerClass, String indexKey, String sizeKey) {
        log.info("Application loaded PagerArgumentResolver.");
        this.indexKey = indexKey;
        this.sizeKey = sizeKey;
        this.pagerClass = pagerClass;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Pager.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String pi = nativeWebRequest.getParameter(indexKey);
        String ps = nativeWebRequest.getParameter(sizeKey);
        int index = Pager.DEFAULT_INDEX;
        int size = Pager.DEFAULT_SIZE;

        if(StringUtils.isNotBlank(pi)) {
            try {
                index = Integer.valueOf(pi);
            }catch(NumberFormatException e) {}
        }
        if(StringUtils.isNotBlank(ps)) {
            try {
                size = Integer.valueOf(ps);
            }catch(NumberFormatException e) {}
        }
        Constructor<? extends Pager> constructor = pagerClass.getConstructor(int.class, int.class);
        return constructor.newInstance(index, size);
    }
}
