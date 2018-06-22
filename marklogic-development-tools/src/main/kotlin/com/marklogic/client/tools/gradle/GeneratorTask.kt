/*
 * Copyright 2018 MarkLogic Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marklogic.client.tools.gradle

import com.marklogic.client.tools.proxy.Generator
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

// @CacheableTask
open class GeneratorTask : DefaultTask() {
  val generator = Generator()

  var serviceBundleFilename: String = ""
  var javaBaseDirectory:     String = ""

  @TaskAction
  fun serviceBundleToJava() {
    if (serviceBundleFilename == "") {
      if (!project.hasProperty("serviceBundleFilename")) {
        throw IllegalArgumentException("serviceBundleFilename not specified")
      }
      serviceBundleFilename = project.property("serviceBundleFilename") as String
    }
    if (javaBaseDirectory == "") {
      if (!project.hasProperty("javaBaseDirectory")) {
        throw IllegalArgumentException("javaBaseDirectory not specified")
      }
      javaBaseDirectory = project.property("javaBaseDirectory") as String
    }
    generator.serviceBundleToJava(serviceBundleFilename, javaBaseDirectory)
  }
}