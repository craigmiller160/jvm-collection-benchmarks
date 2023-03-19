#!/bin/sh

function verify_args {
  if [ $# -lt 1 ]; then
    echo "Must specify project to run. (java, kotlin)"
    exit 1
  fi

  case $1 in
    java|kotlin) ;;
    *)
      echo "Invalid project name: $1"
    ;;
  esac
}

export WARMUP_ITERATIONS=2
export ITERATIONS=10
export FORK=5
export BATCH_SIZE=100
export BENCHMARK_MODE=AverageTime
export TIME_UNIT=ns
export COLLECTION_SIZE=1000000

verify_args $@
export INCLUDES="$2"

cd $1
gradle jmh