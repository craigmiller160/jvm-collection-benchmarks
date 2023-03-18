#!/bin/sh

if [ $# -ne 1 ]; then
  echo "Must specify project to run. (java, kotlin)"
  exit 1
fi

export WARMUP_ITERATIONS=2
export ITERATIONS=10
export FORK=5
export BATCH_SIZE=100
export BENCHMARK_MODE=AverageTime
export TIME_UNIT=ns
export COLLECTION_SIZE=1000000

cd $1
gradle jmh