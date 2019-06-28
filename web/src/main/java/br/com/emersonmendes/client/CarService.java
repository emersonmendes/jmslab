package br.com.emersonmendes.client;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;

import static javax.ejb.TransactionManagementType.BEAN;

@Stateless
@TransactionManagement(BEAN)
public interface CarService {

    void save(String name);

}