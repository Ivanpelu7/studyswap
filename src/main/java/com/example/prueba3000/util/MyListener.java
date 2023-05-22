package com.example.prueba3000.util;

import com.example.prueba3000.model.Apunte;
import com.example.prueba3000.model.Usuario;

import java.sql.SQLException;

public interface MyListener {

    public void onClickListener(Apunte apunte);
    public void onclicklistener(Usuario Usuario) throws SQLException;
}
