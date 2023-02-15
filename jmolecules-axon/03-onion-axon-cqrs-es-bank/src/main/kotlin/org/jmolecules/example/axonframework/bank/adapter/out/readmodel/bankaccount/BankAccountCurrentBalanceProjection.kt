package org.jmolecules.example.axonframework.bank.adapter.out.readmodel.bankaccount

import mu.KLogging
import org.axonframework.queryhandling.QueryHandler
import org.jmolecules.architecture.cqrs.annotation.QueryModel
import org.jmolecules.example.axonframework.bank.domain.bankaccount.read.BankAccountCurrentBalance
import org.jmolecules.example.axonframework.bank.application.port.out.repository.BankAccountCurrentBalanceRepository
import org.springframework.stereotype.Component
import java.util.*

@QueryModel
@Component
class BankAccountCurrentBalanceProjection(
  private val repository: BankAccountCurrentBalanceRepository
) {

  companion object: KLogging()

  @QueryHandler
  fun query(query: BankAccountCurrentBalanceQuery): Optional<BankAccountCurrentBalance> {
    logger.info { "Querying account ${query.accountId}" }
    return repository.findById(query.accountId)
  }
}