package com.example.icart.interfaces;

import android.database.Cursor;

import com.example.icart.models.data.Catagory;
import com.example.icart.models.data.Element;

public interface DatabaseHelper {

    Cursor getCategories();

    Cursor getElements(String catagory);

    boolean setLock(String password);

    boolean addElement(Element element, String catagory_name);

    boolean addCategory(Catagory catagory);

    boolean deleteCategory(String categoryName);

    boolean editCategory(String categoryName, Catagory catagory);

    boolean deleteElement(String elementName);

    boolean editElement(String oldElementName, Element newElement);


}
