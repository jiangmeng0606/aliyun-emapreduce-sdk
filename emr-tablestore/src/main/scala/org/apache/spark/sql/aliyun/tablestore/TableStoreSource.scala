/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.sql.aliyun.tablestore

import org.apache.spark.internal.Logging
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.execution.streaming.{Offset, Source}
import org.apache.spark.sql.types.StructType

class TableStoreSource(
    parameters: Map[String, String],
    userSpecifiedschema: Option[StructType]) extends Source with Logging {
  override def schema: StructType = userSpecifiedschema.getOrElse(new StructType)

  override def getOffset: Option[Offset] = {
  throw new UnsupportedOperationException("unsupport tablestore datasource.")
}

  override def getBatch(start: Option[Offset], end: Offset): DataFrame = {
  throw new UnsupportedOperationException("unsupport tablestore datasource.")
}

  override def stop(): Unit = {}
}
