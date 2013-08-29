/*
 * Copyright 2012 International Business Machines Corp.
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
package org.apache.batchee.jsl;

import org.apache.batchee.jaxb.JSLProperties;
import org.apache.batchee.jaxb.Property;

/**
 * @author skurz
 */
public class PropertiesToStringHelper {
    public static String getString(final JSLProperties props) {
        if (props == null) {
            return null;
        }

        final StringBuilder buf = new StringBuilder(150);
        for (final Property prop : props.getPropertyList()) {
            buf.append("name=").append(prop.getName()).append(",value=").append(prop.getValue()).append("\n");
        }
        return buf.toString();
    }
}