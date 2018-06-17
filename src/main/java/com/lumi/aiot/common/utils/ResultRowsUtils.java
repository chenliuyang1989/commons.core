package com.lumi.aiot.common.utils;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;

public class ResultRowsUtils
{

    /**
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    public List<Map<String, Object>> getResultRows(Object object) throws IllegalArgumentException
    {
        if (object instanceof JSONObject)
        {
            JSONObject jsonObject = (JSONObject) object;
            return (List<Map<String, Object>>) jsonObject.get("result_rows");
        } else
        {
            throw new IllegalArgumentException(object.toString());
        }
    }
}
