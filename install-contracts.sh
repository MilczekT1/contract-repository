#!/bin/bash
LESS_LOGS=-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn
CONTRACT_DIR=contracts/src/main/resources/contracts/pl/konradboniecki
GO_BACK_TO_ROOT=../../../../../../../../../../

install_contract() {
  mvn clean install -DskipTests=true -B $LESS_LOGS || exit
  cd $GO_BACK_TO_ROOT  || exit
}

ACC_MGT_VERSION=0.3.0-SNAPSHOT
BUDGET_MGT_VERSION=0.3.0-SNAPSHOT
FAMILY_MGT_VERSION=0.3.0-SNAPSHOT
MAIL_VERSION=0.3.0-SNAPSHOT

for arg in "$@"
do
    if [[ "$arg" == *"ACC_MGT_V"* ]]
    then
        echo "Detected argument: $arg"
        ACC_MGT_VERSION="${arg/ACC_MGT_V=/}"
    fi
    if [[ "$arg" == *"BUDGET_MGT_V"* ]]
    then
        echo "Detected argument: $arg"
        BUDGET_MGT_VERSION="${arg/BUDGET_MGT_V=/}"
    fi
    if [[ "$arg" == *"FAMILY_MGT_V"* ]]
    then
        echo "Detected argument: $arg"
        FAMILY_MGT_VERSION="${arg/FAMILY_MGT_V=/}"
    fi
    if [[ "$arg" == *"MAIL_V"* ]]
    then
        echo "Detected argument: $arg"
        MAIL_VERSION="${arg/MAIL_V=/}"
    fi
done
echo "Using: ACC_MGT_VERSION=$ACC_MGT_VERSION"
echo "Using: BUDGET_MGT_VERSION=$BUDGET_MGT_VERSION"
echo "Using: FAMILY_MGT_VERSION=$FAMILY_MGT_VERSION"
echo "Using: MAIL_VERSION=$MAIL_VERSION"

cd contract-parent || exit
mvn clean install || exit
cd .. || exit

#Install Account-management contracts
cd $CONTRACT_DIR/budget/account-management/$ACC_MGT_VERSION || exit
install_contract

# Install Budget-management contracts
cd $CONTRACT_DIR/budget/budget-management/$BUDGET_MGT_VERSION || exit
install_contract

# Install Family-management contracts
cd $CONTRACT_DIR/budget/family-management/$FAMILY_MGT_VERSION || exit
install_contract

# Install Mail Service contracts
cd $CONTRACT_DIR/budget/mail/$MAIL_VERSION || exit
install_contract

# Install contracts
cd contracts || exit
mvn clean install -DskipTests=true -B $LESS_LOGS || exit
