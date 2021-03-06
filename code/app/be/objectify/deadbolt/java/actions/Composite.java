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
import play.mvc.With;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Target;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@With(CompositeAction.class)
@Repeatable(Composite.List.class)
@Retention(RUNTIME)
@Target({METHOD, TYPE})
@Inherited
@Documented
public @interface Composite
{
    /**
     * The name of the composite constraint.
     *
     * @return the name of the resource
     */
    String value();

    /**
     * Additional information when deciding on access to the resource.  It's a free formatted string, so you can pass
     * simple data or more complex string such as foo=bar,hurdy=gurdy which can be parsed into a map>
     *
     * @return the meta information
     */
    String meta() default "";

    /**
     * Indicates the expected response type.  Useful when working with non-HTML responses.  This is free text, which you
     * can use in {@link be.objectify.deadbolt.java.DeadboltHandler#onAuthFailure} to decide on how to handle the response.
     *
     * @return a content indicator
     */
    String content() default "";

    /**
     * Use a specific {@link be.objectify.deadbolt.java.DeadboltHandler} for this restriction in place of the global
     * one, identified by a key.
     *
     * @return the key of the handler
     */
    String handlerKey() default Constants.DEFAULT_HANDLER_KEY;

    /**
     * If true, constraints will use the metadata defined in this annotation over any constraint-local
     * metadata.  If no metadata is defined, the local metadata - if any - will be used.
     *
     * Defaults to false.
     *
     * @return true iff this metadata should be preferred over constraint-local metadata, otherwise false
     */
    boolean preferGlobalMeta() default false;

    /**
     * If true, the annotation will only be run if there is a {@link DeferredDeadbolt} annotation at the class level.
     *
     * @return true iff the associated action should be deferred until class-level annotations are applied.
     */
    boolean deferred() default false;

    /**
     * Defines several {@code Composite} annotations on the same element.
     */
    @Retention(RUNTIME)
    @Target({METHOD, TYPE})
    @Documented
    @Inherited
    public @interface List {
        Composite[] value();
    }
}
