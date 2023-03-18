#!/bin/sh

if [ $# -ne 1 ]; then
  echo "Must specify project to run. (java)"
  exit 1
fi

export WARMUP_ITERATIONS=2
export ITERATIONS=10
export FORK=10
export BATCH_SIZE=1000
export BENCHMARK_MODE=AverageTime
export TIME_UNIT=ns
export COLLECTION_SIZE=1000000

cd $1
gradle jmh --info