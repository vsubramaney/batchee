/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.batchee.camel;

import org.apache.batchee.extras.chain.Chain;
import org.apache.batchee.extras.locator.BeanLocator;
import org.apache.camel.Exchange;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;

public class CamelChainItemProcessor extends Chain<ItemProcessor> implements ItemProcessor {
    @Inject
    @BatchProperty
    private String unwrap;

    private BeanLocator locatorInstance;

    @Override
    public Object processItem(final Object item) throws Exception {
        if (locator == null) {
            locatorInstance = CamelLocator.INSTANCE;
        } else {
            locatorInstance = super.getBeanLocator();
        }
        return CamelTask.unwrapIfNeeded(unwrap, Exchange.class.cast(super.runChain(item)));
    }

    @Override
    protected BeanLocator getBeanLocator() {
        return locatorInstance;
    }

    @Override
    protected Object invoke(final BeanLocator.LocatorInstance<ItemProcessor> next, final Object current) throws Exception {
        return next.getValue().processItem(current);
    }
}
