/*
 * Copyright (c) 2019 Adam Spofford <aspofford.as@gmail.com>
 *
 * Licensed under the MIT License <LICENSE-MIT> or the Apache License version 2.0 <LICENSE-APACHE>, at your option.
 * This file may not be copied, modified, or distributed except according to those terms.
 */

package flavor.pie.shulkershell

import org.spongepowered.api.command.CommandSource

abstract class Cmd {
    internal var _src: CommandSource? = null
    protected val src: CommandSource
        get() = _src ?: throw IllegalStateException("Do not place command logic in the ShulkerCmd constructor")
    abstract fun execute(stream: Sequence<Any?>): Sequence<Any?>
}
