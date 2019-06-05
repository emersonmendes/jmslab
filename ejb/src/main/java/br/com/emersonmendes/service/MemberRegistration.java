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
package br.com.emersonmendes.service;

import br.com.emersonmendes.model.Member;
import org.jnosql.artemis.ConfigurationUnit;
import org.jnosql.artemis.document.DocumentTemplate;
import org.jnosql.diana.api.Settings;
import org.jnosql.diana.api.document.*;
import org.jnosql.diana.mongodb.document.MongoDBDocumentCollectionManager;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.logging.Logger;

@Stateless
public class MemberRegistration {

    public static final String DOCUMENT_COLLECTION = "member";

    @Inject
    @ConfigurationUnit(name = DOCUMENT_COLLECTION)
    private DocumentCollectionManagerFactory<MongoDBDocumentCollectionManager> managerFactory;

    @Inject
    private DocumentTemplate documentTemplate;

    @Inject
    private Event<Member> memberEventSrc;

    public void register(Member member) {

        Random random = new Random();
        member.setId(random.nextLong());

        documentTemplate.insert(member);

        memberEventSrc.fire(member);

    }

    @Produces
    public MongoDBDocumentCollectionManager getEntityManager() {
        return managerFactory.get("member");
    }

}
