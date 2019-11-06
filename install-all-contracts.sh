#!/bin/bash
LESS_LOGS=-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn
CONTRACT_DIR=contracts/src/main/resources/contracts/pl/konradboniecki
GO_BACK_TO_ROOT=../../../../../../../../../../

install_contract() {
  mvn clean install -DskipTests=true -B $LESS_LOGS || exit
  cd $GO_BACK_TO_ROOT  || exit
}

cd contract-parent || exit
mvn clean install -B $LESS_LOGS || exit
cd .. || exit

#Install Account-management contracts
cd $CONTRACT_DIR/budget/account-management/0.3.0-SNAPSHOT || exit
install_contract

# Install Budget-management contracts
cd $CONTRACT_DIR/budget/budget-management/0.3.0-SNAPSHOT || exit
install_contract

# Install Family-management contracts
cd $CONTRACT_DIR/budget/family-management/0.3.0-SNAPSHOT || exit
install_contract

# Install Mail Service contracts
cd $CONTRACT_DIR/budget/mail/0.3.0-SNAPSHOT || exit
install_contract

# Install contracts
cd contracts || exit
mvn clean install -DskipTests=true -B $LESS_LOGS || exit
