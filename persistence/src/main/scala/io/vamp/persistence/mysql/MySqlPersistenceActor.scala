package io.vamp.persistence.mysql

import io.vamp.common.ClassMapper
import io.vamp.persistence.{ SqlPersistenceActor, SqlStatementProvider }

class MySqlPersistenceActorMapper extends ClassMapper {
  val name = "mysql"
  val clazz = classOf[MySqlPersistenceActor]
}

class MySqlPersistenceActor extends SqlPersistenceActor with SqlStatementProvider {

  override protected def info() = super.info().map(_ + ("type" → "mysql") + ("url" → url))

  override def getInsertStatement(content: Option[String]): String =
    content.map { _ ⇒
      "insert into Artifacts (`Version`, `Command`, `Type`, `Name`, `Definition`) values (?, ?, ?, ?, ?)"
    }.getOrElse("insert into Artifacts (`Version`, `Command`, `Type`, `Name`) values (?, ?, ?, ?)")

  override def getSelectStatement(lastId: Long): String =
    s"SELECT `ID`, `Command`, `Type`, `Name`, `Definition` FROM `Artifacts` WHERE `ID` > $lastId ORDER BY `ID` ASC"

  override val statementMinValue: Int = Integer.MIN_VALUE
}
