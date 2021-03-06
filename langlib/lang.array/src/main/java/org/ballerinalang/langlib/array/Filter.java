/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.langlib.array;

import org.ballerinalang.jvm.BRuntime;
import org.ballerinalang.jvm.scheduling.Strand;
import org.ballerinalang.jvm.types.BArrayType;
import org.ballerinalang.jvm.values.ArrayValue;
import org.ballerinalang.jvm.values.ArrayValueImpl;
import org.ballerinalang.jvm.values.FPValue;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.ReturnType;

import java.util.concurrent.atomic.AtomicInteger;

import static org.ballerinalang.util.BLangCompilerConstants.ARRAY_VERSION;

/**
 * Native implementation of lang.array:filter(Type[], function).
 *
 * @since 1.0
 */
@BallerinaFunction(
        orgName = "ballerina", packageName = "lang.array", version = ARRAY_VERSION, functionName = "filter",
        args = {@Argument(name = "arr", type = TypeKind.ARRAY), @Argument(name = "func", type = TypeKind.FUNCTION)},
        returnType = {@ReturnType(type = TypeKind.ARRAY)},
        isPublic = true
)
public class Filter {

    public static ArrayValue filter(Strand strand, ArrayValue arr, FPValue<Object, Boolean> func) {
        ArrayValue newArr = new ArrayValueImpl((BArrayType) arr.getType());
        int size = arr.size();
        AtomicInteger newArraySize = new AtomicInteger(-1);
        AtomicInteger index = new AtomicInteger(-1);
        BRuntime.getCurrentRuntime()
                .invokeFunctionPointerAsyncIteratively(func, size,
                                                       () -> new Object[]{strand, arr.get(index.incrementAndGet()),
                                                               true},
                                                       result -> {
                                                           if ((Boolean) result) {
                                                               newArr.add(newArraySize.incrementAndGet(),
                                                                          arr.get(index.get()));
                                                           }
                                                       }, () -> newArr);
        return newArr;
    }
}
