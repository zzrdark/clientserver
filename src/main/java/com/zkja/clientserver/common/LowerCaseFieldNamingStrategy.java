package com.zkja.clientserver.common;

import com.google.gson.FieldNamingStrategy;

import java.lang.reflect.Field;

/**
 * @authon zzr
 */
public class LowerCaseFieldNamingStrategy
        implements FieldNamingStrategy
{
    @Override
    public String translateName(Field f)
    {
        return f.getName().toLowerCase();
    }
}
