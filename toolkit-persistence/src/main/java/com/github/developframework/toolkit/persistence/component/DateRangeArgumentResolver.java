package com.github.developframework.toolkit.persistence.component;

import lombok.Getter;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * 日期范围参数分解器
 * @author qiuzhenhao
 */
@Getter
@Component
public class DateRangeArgumentResolver implements HandlerMethodArgumentResolver {

    /* 开始时间键值 */
    private String startKey;
    /* 截止时间键值 */
    private String endKey;

    private String pattern;

    public DateRangeArgumentResolver() {
        this.pattern = "yyyy-MM-dd HH:mm:ss";
        this.startKey = "start_date";
        this.endKey = "end_date";
    }

    public DateRangeArgumentResolver(String pattern, String startKey, String endKey) {
        this.pattern = pattern;
        this.startKey = startKey;
        this.endKey = endKey;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return DateRange.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) throws Exception {
        String sk = null, ek = null, p = null;
        if (parameter.hasParameterAnnotation(CustomDateRange.class)) {
            CustomDateRange customDateRangeAnnotation = parameter.getMethodAnnotation(CustomDateRange.class);
            sk = customDateRangeAnnotation.startKey();
            ek = customDateRangeAnnotation.endKey();
            p = customDateRangeAnnotation.value();
        }
        String startDateStr = nativeWebRequest.getParameter(sk != null ? sk : startKey);
        String endDateStr = nativeWebRequest.getParameter(ek != null ? ek : endKey);
        return DateRange.parse(startDateStr, endDateStr, p != null ? p : pattern);
    }
}
