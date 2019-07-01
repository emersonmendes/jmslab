package br.com.emersonmendes.impl;

import br.com.emersonmendes.api.*;

import javax.ejb.*;
 

 
@Stateless
@Remote(IRemoteMyEJB.class)
public class MyEJBBean implements IMyEJB {

	@Override
	public String showMessage() {
		System.out.println("#######################################################################");
		System.out.println("show EJB message");
		System.out.println("#######################################################################");
		return "show EJB message";
	}
	
}
 