source ./env.rc
echo $SERVER
PYTHONPATH=. exec python3 com/MainRun.py 1 $SERVER
