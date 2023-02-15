package org.jmolecules.example.axonframework.bank.domain.moneytransfer.event

import org.axonframework.serialization.Revision
import org.jmolecules.event.annotation.DomainEvent
import org.jmolecules.example.axonframework.bank.domain.moneytransfer.type.MoneyTransferId
import org.jmolecules.example.axonframework.bank.domain.moneytransfer.type.Reason

@Revision("1")
@DomainEvent(namespace = "axon.bank", name = "MoneyTransferCancelledEvent")
data class MoneyTransferCancelledEvent(
  val moneyTransferId: MoneyTransferId,
  val reason: Reason
)