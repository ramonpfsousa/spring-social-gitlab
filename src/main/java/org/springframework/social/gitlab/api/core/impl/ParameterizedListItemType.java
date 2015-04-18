/*
 * Copyright 2015 p.hoeffling.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.gitlab.api.core.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 * @author p.hoeffling
 */
class ParameterizedListItemType implements ParameterizedType {
    private final Type rawType;
    private final Type[] actualTypeArguments;
    private final Type owner;

    ParameterizedListItemType(Type rawType, Type[] actualTypeArguments) {
        this.rawType = rawType;
        this.actualTypeArguments = actualTypeArguments;
        this.owner = null;
    }

    ParameterizedListItemType(Type rawType, Type[] actualTypeArguments, Type owner) {
        this.rawType = rawType;
        this.actualTypeArguments = actualTypeArguments;
        this.owner = owner;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    @Override
    public Type getRawType() {
        return rawType;
    }

    @Override
    public Type getOwnerType() {
        return owner;
    }
    
}
