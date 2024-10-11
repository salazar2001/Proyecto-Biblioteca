/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class NavigationBean {

    public String irHome() {
        return "home?faces-redirect=true";
    }
        
    public String goToUsuarios() {
        return "usuario?faces-redirect=true";
    }

    public String goToAutores() {
        return "autor?faces-redirect=true";
    }
    
}