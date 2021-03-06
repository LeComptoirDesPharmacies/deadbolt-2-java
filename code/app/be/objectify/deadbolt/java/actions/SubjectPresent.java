/*
 * Copyright 2010-2016 Steve Chaloner
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
package be.objectify.deadbolt.java.actions;

import be.objectify.deadbolt.java.Constants;
import be.objectify.deadbolt.java.DeadboltHandler;
import play.mvc.With;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Target;

/**
 * A {@link be.objectify.deadbolt.java.models.Subject} must be provided by the {@link DeadboltHandler} to have access to the resource,
 * but no role checks are performed.
 *
 * @author Steve Chaloner (steve@objectify.be)
 */
@With(SubjectPresentAction.class)
@Repeatable(SubjectPresent.List.class)
@Retention(RUNTIME)
@Target({METHOD, TYPE})
@Documented
@Inherited
public @interface SubjectPresent
{
    /**
     * Indicates the expected response type.  Useful when working with non-HTML responses.  This is free text, which you
     * can use in {@link DeadboltHandler#onAuthFailure} to decide on how to handle the response.
     *
     * @return a content indicator
     */
    String content() default "";

    /**
     * Use a specific {@link DeadboltHandler} for this restriction in place of the global
     * one, identified by a key.
     *
     * @return the ky of the handler
     */
    String handlerKey() default Constants.DEFAULT_HANDLER_KEY;

    /**
     * If true, the annotation will only be run if there is a {@link DeferredDeadbolt} annotation at the class level.
     *
     * @return true iff the associated action should be deferred until class-level annotations are applied.
     */
    boolean deferred() default false;

    /**
     * Indicates if {@link DeadboltHandler#beforeAuthCheck} should be invoked before this constraint is applied.  Defaults to
     * false for historic and generally good reasons, namely that a handler that complains based on user presence can shortcut this constraint.
     *
     * @return true iff {@link DeadboltHandler#beforeAuthCheck} should be invoked prior to applying this constraint.
     */
    boolean forceBeforeAuthCheck() default false;

    /**
     * Defines several {@code SubjectPresent} annotations on the same element.
     */
    @Retention(RUNTIME)
    @Target({METHOD, TYPE})
    @Documented
    @Inherited
    public @interface List {
        SubjectPresent[] value();
    }
}