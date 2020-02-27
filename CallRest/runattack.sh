#!/bin/bash
OUT=output
rm -rf $OUT
mkdir -p $OUT
for i in {1..30}
  do
    echo "Unleash $i"
    ./run.sh >$OUT/out$i &
    ./run1.sh >$OUT/sec$i &
  done
echo "Wait"
wait
