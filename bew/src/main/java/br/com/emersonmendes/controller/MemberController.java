/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.emersonmendes.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.emersonmendes.dto.Car;
import br.com.emersonmendes.model.Member;
import br.com.emersonmendes.service.MemberRegistration;

import java.util.ArrayList;
import java.util.List;

@Model
public class MemberController {

    @Inject
    private FacesContext facesContext;

    private static List<Car> myList;


    public List<Car> getMyList() {
        return myList;
    }

    public void setMyList(List<Car> myList) {
        this.myList = myList;
    }

    @PostConstruct
    public void initNewMember() {
        myList = new ArrayList<>();
        myList.add(new Car("blue", 1994, "fusca"));
    }

}
