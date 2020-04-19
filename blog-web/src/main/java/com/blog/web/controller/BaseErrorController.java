package com.blog.web.controller;

import com.blog.base.lang.MtonsException;
import com.blog.base.lang.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class BaseErrorController extends AbstractErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseErrorController.class);
    private static final String ERROR_PATH = "/error";

    public BaseErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping(value = ERROR_PATH)
    public String error(HttpServletRequest request, ModelMap model) {
        WebRequest webRequest = new ServletWebRequest(request);
        Throwable e = getError(webRequest);
        if (e == null) {
            Map<String, Object> attributes = getErrorAttributes(request, false);
            Object timestamp = attributes.get("timestamp");
            Object status = attributes.get("status");
            String error = attributes.get("error").toString();
            Object path = attributes.get("path");
            LOGGER.error("status {} error {} path{} timestamp {}", status, error, path, timestamp);
            model.put("error", Result.failure(Integer.parseInt(status.toString()), error));
            return "/" + status;
        }

        if (e instanceof MtonsException) {
            MtonsException mtonsException = (MtonsException) e;
            String message = String.format("[%s][%s]", mtonsException.getCode(), mtonsException.getMessage());
            model.put("data", message);
            int code = mtonsException.getCode();
            return "/" + code;
        } else {
            model.put("data", "系统繁忙，请稍后再试。");
            return "/500";
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    private Throwable getError(WebRequest webRequest) {
        return (Throwable) this.getAttribute(webRequest, "javax.servlet.error.exception");
    }

    private Object getAttribute(RequestAttributes requestAttributes, String name) {
        return requestAttributes.getAttribute(name, 0);
    }
}