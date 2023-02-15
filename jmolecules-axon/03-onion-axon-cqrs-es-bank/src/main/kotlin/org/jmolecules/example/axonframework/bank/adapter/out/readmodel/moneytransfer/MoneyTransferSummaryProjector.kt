package org.jmolecules.example.axonframework.bank.adapter.out.readmodel.moneytransfer

import org.jmolecules.architecture.cqrs.annotation.QueryModel
import org.jmolecules.event.annotation.DomainEventHandler
import org.jmolecules.example.axonframework.bank.domain.moneytransfer.event.MoneyTransferCancelledEvent
import org.jmolecules.example.axonframework.bank.domain.moneytransfer.event.MoneyTransferCompletedEvent
import org.jmolecules.example.axonframework.bank.domain.moneytransfer.event.MoneyTransferRequestedEvent
import org.jmolecules.example.axonframework.bank.domain.moneytransfer.read.MoneyTransferSummary
import org.jmolecules.example.axonframework.bank.application.port.out.repository.MoneyTransferSummaryRepository
import org.springframework.stereotype.Component

@Component
@QueryModel // currently needed to map handler!
class MoneyTransferSummaryProjector(
  private val repository: MoneyTransferSummaryRepository
) {

  @DomainEventHandler(namespace = "axon.bank", name = "MoneyTransferRequestedEvent")
  fun on(evt: MoneyTransferRequestedEvent) {
    repository.save(
      MoneyTransferSummary(
        moneyTransferId = evt.moneyTransferId,
        sourceAccountId = evt.sourceAccountId,
        targetAccountId = evt.targetAccountId,
        amount = evt.amount,
        success = false
      )
    )
  }

  @DomainEventHandler(namespace = "axon.bank", name = "MoneyTransferCompletedEvent")
  fun on(evt: MoneyTransferCompletedEvent) {
    repository.findById(evt.moneyTransferId).ifPresent {
      repository.save(it.copy(success = true))
    }
  }

  @DomainEventHandler(namespace = "axon.bank", name = "MoneyTransferCancelledEvent")
  fun on(evt: MoneyTransferCancelledEvent) {
    repository.findById(evt.moneyTransferId).ifPresent {
      repository.save(it.copy(success = false, errorMessage = evt.reason))
    }
  }
}