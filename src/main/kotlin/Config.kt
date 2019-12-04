/*
 * Copyright (c) 2019 Adam Spofford <aspofford.as@gmail.com>
 *
 * Licensed under the MIT License <LICENSE-MIT> or the Apache License version 2.0 <LICENSE-APACHE>, at your option.
 * This file may not be copied, modified, or distributed except according to those terms.
 */

package flavor.pie.shulkershell

import ninja.leaping.configurate.objectmapping.Setting
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable

@ConfigSerializable
class Config(@Setting val version: Int = 1)

