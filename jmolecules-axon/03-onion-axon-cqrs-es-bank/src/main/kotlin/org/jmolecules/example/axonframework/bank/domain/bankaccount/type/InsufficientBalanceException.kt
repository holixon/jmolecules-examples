package org.jmolecules.example.axonframework.bank.domain.bankaccount.type

/**
 * Indicates insufficient balance.
 */
class InsufficientBalanceException(
  accountId: AccountId,
  currentBalance: Balance,
  withdrawAmount: Amount?,
  minimumBalance: Balance
) : RuntimeException(
  if (withdrawAmount == null) {
    "BankAccount[id=$accountId] can't be created, its balance $currentBalance would subceed min. balance of $minimumBalance"
  } else {
    "BankAccount[id=$accountId, currentBalance=$currentBalance]: Withdrawal of amount=$withdrawAmount not allowed, would subceed min. balance of $minimumBalance"
  }
)