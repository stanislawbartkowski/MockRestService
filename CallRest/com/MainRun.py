'''
Created on 27 lut 2019

@author: sbartkowski
'''


import time
import sys

print(sys.path)


from com.rest import Rest

# __runTest1
# number of iterations
#NO=1000
NO=1000

# __runTest2
# time elapsed
SEC = 5 * 60

#SERVERHOST="thinkde:8080"
#APPNAME="RestMockServer"


class runRest :
    
    def __init__(self,server):
        self.start = time.time()
        self.no = 0
        self.R = Rest.RestApi(server)
        
    def _printProgress(self):
        end = time.time()
        print("Number of calls:",self.no)
        print("Time elapsed:",(end -self.start))
        print("Calls/sec",self.no / (end-self.start))
        
    def __progress(self):
        if self.no % 500 == 0 :
            self._printProgress()
            print("-------------")
            
        
    def runIter(self):
        for self.no in range(NO) :
            self.restTest()
            self.__progress()
        self.no = NO
        
    def runTime(self):
        while time.time() - self.start < SEC :
            self.restTest()
            self.__progress()
            self.no = self.no + 1
        
    def printResult(self):
        print("=============")
        self._printProgress()
        
    def restTest(self):
#        cookies = {'enwiki_session': '17ab96bd8ffbe8ca58a78657a918558'}
        cookies = {
           'JSESSIONID':'0000pDrRQh9R7B5bYaq3s5WkWko:16jtsqutb',
           'xframeLocale' : 'de_DE',
           'TS011fbeb3' : '01201d70567460d184f9cc6c10dbc236ad9ae4e69b3da3e4ffd970e4936adeb63b047acac57f25de7e2f7d07c36de98404e6128190cdb03ff18b0e7f27564a98e8e376ae91',
           'wct' : 'yes',
           'TS018fd337' : '016c098834bfd834219f7abf8aa9a5af8fa748df5369d0a2bab9186acb038afca64b57c2a398b760624e18d0f746f55f1c2e5763d6; TS013a8b1e=016c098834973d3dfc76f6fd0e609cbbc2eb64278d7ac28cd03315a047c4f67efe610a836d7f49db642a6c4bff5fb3c333e3968f21267580bbae1d81250827050dc57522f4; TS01b5ad17=017dcaa0ab396e6071a37b45a5f267a2b7d7001e8abcd89d75494b807f21913d4f534c56eacff40095431f33eb2e22d74fe062ccc6ad793cee3bf805be2b92d62b1fbefc3e126f0027c081210d135417e505a4709ba538d9d43bd94d7adc5e2096add66d3c'
        } 
        res = self.R.postContent("Hello cookies",cookies)

def printHelp() :
    print("Parameters: /what/ /server/ ")
    print(" 1 : number of iterations: ",NO)
    print(" 2 : time elapsed. Number of seconds:",SEC)
    print("Example:")
    print(" MainRun.py 1 localhost:8080")
    sys.exit()
    
        
if __name__ == '__main__':
    if len(sys.argv) != 3 :
        printHelp()
        
    test = sys.argv[1]
    server = sys.argv[2]

    if test == "1" :
        r = runRest(server)
        r.runIter()
    elif test == "2":
        r = runRest(server)
        r.runTime()
    else :
        printHelp()
        
    r.printResult() 
        
        
    
        
