#!/bin/bash

# http://redsymbol.net/articles/unofficial-bash-strict-mode/
set -eo pipefail
IFS=$'\n\t'

# https://stackoverflow.com/questions/59895/how-do-i-get-the-directory-where-a-bash-script-is-located-from-within-the-script
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

export BOOTSTRAP_SERVER=localhost:9092
export KAFKA_HOME=$HOME/kafka

${KAFKA_HOME}/bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --topic tweets --create --if-not-exists
${KAFKA_HOME}/bin/kafka-topics.sh --bootstrap-server ${BOOTSTRAP_SERVER} --topic hashtags --create --if-not-exists

cat ${SCRIPT_DIR}/tweets.csv | ${KAFKA_HOME}/bin/kafka-console-producer.sh --bootstrap-server ${BOOTSTRAP_SERVER} --topic tweets --property parse.key=true --property key.separator=,

cat ${SCRIPT_DIR}/hashtags.csv | ${KAFKA_HOME}/bin/kafka-console-producer.sh --bootstrap-server ${BOOTSTRAP_SERVER} --topic hashtags --property parse.key=true --property key.separator=,
