/*
 * Copyright 2012-2015 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.objectify.deadbolt.java;

import play.libs.concurrent.HttpExecution;
import scala.concurrent.ExecutionContext;

/**
 * The default implementation of {@link DeadboltExecutionContextProvider} uses Play's internal exeuction context support.
 *
 * @author Steve Chaloner (steve@objectify.be)
 */
public class DefaultDeadboltExecutionContextProvider implements DeadboltExecutionContextProvider
{
    @Override
    public ExecutionContext get()
    {
        return HttpExecution.defaultContext();
    }
}