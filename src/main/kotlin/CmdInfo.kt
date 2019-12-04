/*
 * Copyright (c) 2019 Adam Spofford <aspofford.as@gmail.com>
 *
 * Licensed under the MIT License <LICENSE-MIT> or the Apache License version 2.0 <LICENSE-APACHE>, at your option.
 * This file may not be copied, modified, or distributed except according to those terms.
 */

package flavor.pie.shulkershell

import com.google.common.collect.ImmutableMap
import kotlin.reflect.KType

data class CmdInfo(
    val executor: (Map<String, Any?>, Sequence<Any?>) -> Sequence<Any?>,
    val shortDescription: String?,
    val longDescription: String?,
    val overloads: List<CmdOverload>
)

data class CmdOverload(
    val parameters: List<ParamInfo>
) {
    val paramsByName: Map<String, ParamInfo> by lazy { parameters.associateBy { it.name } }
    val paramAliases: Map<String, String> by lazy {
        val builder = ImmutableMap.builder<String, String>()
        for (param in parameters) {
            for (alias in param.aliases) {
                builder.put(alias, param.name)
            }
        }
        builder.build()
    }
}

data class ParamInfo(
    val name: String,
    val aliases: List<String>,
    val type: KType,
    val optional: Boolean,
    val position: Int?,
    val shortDescription: String?,
    val longDescription: String?
)
