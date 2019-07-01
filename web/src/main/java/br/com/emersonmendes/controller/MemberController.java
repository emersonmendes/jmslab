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

import br.com.emersonmendes.api.IRemoteMyEJB;
import br.com.emersonmendes.jms.MemberProducer;
import br.com.emersonmendes.lookup.LookerUp;
import br.com.emersonmendes.model.Member;
import br.com.emersonmendes.service.MemberRegistration;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import static br.com.emersonmendes.lookup.LookerUp.SessionBeanType.STATELESS;

@Model
public class MemberController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private MemberRegistration memberRegistration;

    @Inject
    private MemberProducer memberProducer;

    private Member newMember;

    private LookerUp lookerup;

    @PostConstruct
    private void init() {
        newMember = new Member();
        lookerup = new LookerUp();
    }

    @Produces
    @Named
    public Member getNewMember() {
        return newMember;
    }

    public void register() {

        memberProducer.sendMessage(newMember);

        try {
            memberRegistration.register(newMember);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
            init();
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error :(", "Registration Unsuccessful"));
        }

    }

    public void showMessage(){
        IRemoteMyEJB bean = lookerup.getBean(STATELESS, "MyEJBBean", IRemoteMyEJB.class);
        String message = bean.showMessage();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", message));
    }

}
