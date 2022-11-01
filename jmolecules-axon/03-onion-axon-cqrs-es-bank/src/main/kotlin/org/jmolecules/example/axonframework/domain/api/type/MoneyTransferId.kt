package org.jmolecules.example.axonframework.domain.api.type

import org.jmolecules.ddd.annotation.ValueObject

/**
 * Represents a money transfer id.
 */
@JvmInline
@ValueObject
value class MoneyTransferId(val value: String) {
    init {
        require(value.isNotBlank()) { "Money transfer id must not be empty" }
    }
}