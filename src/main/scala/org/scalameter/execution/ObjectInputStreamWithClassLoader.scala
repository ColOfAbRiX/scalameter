package org.scalameter.execution

import java.io._

// See https://stackoverflow.com/a/64890595 and https://github.com/scala/bug/issues/9237
class ObjectInputStreamWithClassLoader(fis: FileInputStream) extends ObjectInputStream(fis) {
  override def resolveClass(desc: java.io.ObjectStreamClass): Class[_] =
    try {
      Class.forName(desc.getName, false, getClass.getClassLoader)
    } catch {
      case ex: ClassNotFoundException => super.resolveClass(desc)
    }
}
