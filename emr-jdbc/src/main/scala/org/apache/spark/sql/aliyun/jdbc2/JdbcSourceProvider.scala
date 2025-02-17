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
package org.apache.spark.sql.aliyun.jdbc2

import org.apache.spark.internal.Logging
import org.apache.spark.sql.execution.streaming.{Sink, Source}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.execution.datasources.jdbc.{JDBCOptions, JDBCRelation, JdbcRelationProvider}
import org.apache.spark.sql.sources._
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.types.StructType

class JdbcSourceProvider extends JdbcRelationProvider
  with StreamSinkProvider
  with StreamSourceProvider
  with Logging {

  override def shortName(): String = "jdbc2"

  override def createSink(
      sqlContext: SQLContext,
      parameters: Map[String, String],
      partitionColumns: Seq[String],
      outputMode: OutputMode): Sink = {
    new JdbcSink(sqlContext, parameters, partitionColumns, outputMode)
  }

  override def sourceSchema(
      sqlContext: SQLContext,
      schema: Option[StructType],
      providerName: String,
      parameters: Map[String, String]): (String, StructType) = {
    val jdbcOptions = new JDBCOptions(parameters)
    val resolver = sqlContext.conf.resolver
    (shortName(), schema.getOrElse(JDBCRelation.getSchema(resolver, jdbcOptions)))
  }

  override def createSource(
      sqlContext: SQLContext,
      metadataPath: String,
      schema: Option[StructType],
      providerName: String,
      parameters: Map[String, String]): Source = {
    new JdbcSource(sqlContext, parameters, schema)
  }
}
