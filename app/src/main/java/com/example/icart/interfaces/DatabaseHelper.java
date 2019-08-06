package com.example.icart.interfaces;

import android.database.Cursor;

import com.example.icart.models.data.Catagory;
import com.example.icart.models.data.Element;

public interface DatabaseHelper {

    Cursor getCategory();

    Cursor getElements(String catagory);

    boolean setLock(String password);

    boolean addElement(Element element , String catagory_name);

    boolean addCategory(Catagory catagory);


}
