package com.example.demo.sharedpreferencespackage.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.Map;

/**
 * Created by bruse on 2018/11/22.
 */

public class SharedPreferencesHelper {

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    public SharedPreferencesHelper(Context context,String fileName){
        sharedPreferences = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * save data
     */
    public void put(String key,Object object){
        if (object instanceof String){

            editor.putString(key,(String) object);
        }else if (object instanceof Integer){
            editor.putInt(key,(Integer)object);
        }else if (object instanceof Boolean){
            editor.putBoolean(key,(Boolean)object);
        }else if (object instanceof Float){
            editor.putFloat(key,(Float)object);
        }else if (object instanceof Long){
            editor.putLong(key,(Long)object);
        }else {
            editor.putString(key,object.toString());
        }
        editor.commit();
    }

    /**
     * give the data
     */
    public Object getSharedPreference(String key,Object defaultObject){
        if (defaultObject instanceof String){
            return sharedPreferences.getString(key,(String)defaultObject);
        }else if (defaultObject instanceof Integer){
            return sharedPreferences.getInt(key,(Integer)defaultObject);
        }else if (defaultObject instanceof Boolean){
            return sharedPreferences.getBoolean(key,(Boolean)defaultObject);
        }else if (defaultObject instanceof Float){
            return sharedPreferences.getFloat(key,(Float)defaultObject);
        }else if (defaultObject instanceof Long){
            return sharedPreferences.getLong(key,(Long)defaultObject);
        }else {
            return sharedPreferences.getString(key,null);
        }
    }

    /**
     * remove one key
     */
    public void remove(String key){
        editor.remove(key);
        editor.commit();
    }

    /**
     * clear data
     */
    public void clear(){
        editor.clear();
        editor.commit();
    }

    /**
     * query one key
     */
    public Boolean contain(String key){
        return sharedPreferences.contains(key);
    }

    /**
     * get all key
     */
    public Map<String,?> getAll(){
        return sharedPreferences.getAll();
    }

    /**
     * update data
     */
    public void syncValue(){
        editor.apply();
        try {
            Runtime.getRuntime().exec("sync");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
