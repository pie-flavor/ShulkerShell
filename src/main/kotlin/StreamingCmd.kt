/*
 * Copyright (c) 2019 Adam Spofford <aspofford.as@gmail.com>
 *
 * Licensed under the MIT License <LICENSE-MIT> or the Apache License version 2.0 <LICENSE-APACHE>, at your option.
 * This file may not be copied, modified, or distributed except according to those terms.
 */

package flavor.pie.shulkershell

abstract class StreamingCmd : Cmd() {

    abstract var pipeVariable: Any?
    protected open suspend fun prepare() {}
    protected abstract suspend fun process()
    protected open suspend fun cleanup() {}

    protected suspend fun writeObject(obj: Any?) {
        TODO()
    }

    final override fun execute(stream: Sequence<Any?>): Sequence<Any?> = TODO()
}
