/*
 * Copyright 2013 International Business Machines Corp.
 * 
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package org.apache.batchee.container.services.executor;

import org.apache.batchee.container.exception.BatchContainerServiceException;
import org.apache.batchee.spi.BatchThreadPoolService;

import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static org.apache.batchee.container.util.ClassLoaderAwareHandler.runnableLoaderAware;

public abstract class AbstractThreadPoolService implements BatchThreadPoolService {
    protected ExecutorService executorService;

    protected abstract ExecutorService newExecutorService(Properties batchConfig);

    @Override
    public void init(final Properties batchConfig) throws BatchContainerServiceException {
        executorService = newExecutorService(batchConfig);
    }

    @Override
    public void shutdown() throws BatchContainerServiceException {
        executorService.shutdownNow();
        executorService = null;
    }

    @Override
    public void executeTask(final Runnable work, final Object config) {
        executorService.execute(runnableLoaderAware(work));
    }
}
