package com.example.gui;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {

    private Object[][] autorzy = {
            {"author1", "Robert Laski"},
            {"author2", "Kacper Pietrzak"}
    };

    @Override
    protected Object[][] getContents() {
        return autorzy;
    }
}
