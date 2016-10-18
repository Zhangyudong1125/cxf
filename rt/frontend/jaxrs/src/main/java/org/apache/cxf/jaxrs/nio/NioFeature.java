/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.jaxrs.nio;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.jaxrs.provider.ServerProviderFactory;

public class NioFeature extends AbstractFeature {
    @Override
    public void initialize(Server server, Bus bus) {
        final List<Object> providers = new ArrayList<>();
        providers.add(new NioMessageBodyWriter());

        server.getEndpoint().getInInterceptors().add(new NioInInterceptor());
        ((ServerProviderFactory)server.getEndpoint()
            .get(ServerProviderFactory.class.getName()))
            .setUserProviders(providers);
    }
}
